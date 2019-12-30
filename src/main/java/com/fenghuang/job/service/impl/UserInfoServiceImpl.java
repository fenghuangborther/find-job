package com.fenghuang.job.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fenghuang.job.constant.Constants;
import com.fenghuang.job.dao.cluster.UserInfoClusterMapper;
import com.fenghuang.job.dao.master.UserInfoMapper;
import com.fenghuang.job.entity.UserInfo;
import com.fenghuang.job.enums.*;
import com.fenghuang.job.exception.BusinessException;
import com.fenghuang.job.request.*;
import com.fenghuang.job.service.LoginLogService;
import com.fenghuang.job.service.MessageCountService;
import com.fenghuang.job.service.UserInfoService;
import com.fenghuang.job.utils.AesUtil;
import com.fenghuang.job.utils.DateUtil;
import com.fenghuang.job.utils.SmsSenderUtil;
import com.fenghuang.job.utils.StringCustomizedUtils;
import com.fenghuang.job.view.JSONMessage;
import com.fenghuang.job.view.MessageCountView;
import com.fenghuang.job.view.MessageView;
import com.fenghuang.job.view.UserInfoView;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2019/12/17 11:13
 * @Email: 15290810931@163.com
 */
@Service
@Slf4j
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    UserInfoMapper userInfoMapper;

    @Autowired
    UserInfoClusterMapper userInfoClusterMapper;

    @Autowired
    LoginLogService loginLogService;

    @Autowired
    SmsSenderUtil smsSenderUtil;

    @Autowired
    MessageCountService messageCountService;

    /**
     * 根据用户名字获取记录[可能有重名的人]
     *
     * @param userName
     * @return
     */
    @Override
    public List<UserInfoView> findUserInfoByUserName(String userName) {
        log.info("根据用户名字获取一条记录");
        if (StringUtils.isEmpty(userName)){
            throw new BusinessException( BusinessEnum.MISSING_PARAMETERS.getCode(), BusinessEnum.MISSING_PARAMETERS.getMsg());
        }
        List<UserInfo> userInfos = userInfoMapper.findUserInfoByUserName(userName);
        if (CollectionUtils.isEmpty(userInfos)) {
            return new ArrayList<>();
        }
        List<UserInfoView> userInfoViews = new ArrayList<>();
        convertToUserInfoView(userInfos, userInfoViews);

        log.info("根据用户名字获取记录返回结果：{}",userInfoViews);
        return userInfoViews;
    }

    private void convertToUserInfoView(List<UserInfo> userInfos, List<UserInfoView> userInfoViews) {
        userInfos.stream().forEach( userInfo -> {
            UserInfoView view = new UserInfoView();
            BeanCopier beanCopier = BeanCopier.create( UserInfo.class, UserInfoView.class, false);
            beanCopier.copy(userInfo, view, null);
            userInfoViews.add(view);
        });
    }

    /**
     * 注册新用户
     *
     * @param reqUserInfo
     * @return
     */
    @Override
    public int insertUser(ReqUserInfo reqUserInfo) {
        log.info("注册新用户，请求参数：{}", JSON.toJSONString(reqUserInfo));
        ReqUserInfoQuery reqUserInfoQuery = new ReqUserInfoQuery();
        reqUserInfoQuery.setUserStatus( UserInfoStatusEnum.NORMAL.getCode() );
        reqUserInfoQuery.setUserNickname(reqUserInfo.getUserNickname());
        reqUserInfoQuery.setMobile(reqUserInfo.getMobile());
        reqUserInfo.setIdCard(reqUserInfo.getIdCard());
        UserInfo queryUserInfo = userInfoMapper.findUserInfo(reqUserInfoQuery);
        if (queryUserInfo != null){
            throw new BusinessException( BusinessEnum.RECORD_ALREADY_EXISTS.getCode(), BusinessEnum.RECORD_ALREADY_EXISTS.getMsg());
        }
        UserInfo userInfo = new UserInfo();
        BeanCopier beanCopier = BeanCopier.create(ReqUserInfo.class, UserInfo.class, false);
        beanCopier.copy(reqUserInfo,userInfo,null);
        userInfo.setPassword(AesUtil.encrypt( Constants.SECRET_KEY,reqUserInfo.getPassword()));
        userInfo.setUserStatus( UserInfoStatusEnum.NORMAL.getCode());
        userInfo.setFounder(reqUserInfo.getMobile());
        userInfo.setModifier(reqUserInfo.getMobile());
        userInfo.setCreateDate(new Date());
        userInfo.setUpdateDate(new Date());
        return userInfoMapper.insertSelective(userInfo);
    }

    /**
     * 更新用户信息
     *
     * @param reqUserInfoUpdate
     * @return
     */
    @Override
    public int modifyUserInfo(ReqUserInfoUpdate reqUserInfoUpdate) {
        log.info("更新用户信息请求参数：{}", JSON.toJSONString(reqUserInfoUpdate));
        UserInfo queryUserInfo = userInfoMapper.selectByPrimaryKey(reqUserInfoUpdate.getId());
        if (queryUserInfo == null){
            throw new BusinessException( BusinessEnum.RECORD_NOT_EXIST.getCode(), BusinessEnum.RECORD_NOT_EXIST.getMsg());
        }
        UserInfo userInfo = new UserInfo();
        BeanCopier beanCopier = BeanCopier.create(ReqUserInfoUpdate.class, UserInfo.class, false);
        beanCopier.copy(reqUserInfoUpdate,userInfo,null);
        if (!StringUtils.isEmpty(reqUserInfoUpdate.getPassword())){
            userInfo.setPassword(AesUtil.encrypt( Constants.SECRET_KEY,reqUserInfoUpdate.getPassword()));
        }
        return userInfoMapper.updateByPrimaryKeySelective(userInfo);
    }

    /**
     * 根据用户id|用户昵称|用户手机号|身份证 获取唯一一条用户信息记录
     * @param reqUserInfoQuery
     * @return
     */
    @Override
    public UserInfoView findUserInfo(ReqUserInfoQuery reqUserInfoQuery) {
        log.info("根据用户id|用户昵称|用户手机号|身份证 获取唯一一条记录 请求参数：{}",JSON.toJSON(reqUserInfoQuery));
        UserInfo queryUserInfo = userInfoMapper.findUserInfo(reqUserInfoQuery);
        if (queryUserInfo == null){
            return null;
        }
        UserInfoView view = new UserInfoView();
        BeanCopier beanCopier = BeanCopier.create(ReqUserInfo.class, UserInfo.class, false);
        beanCopier.copy(queryUserInfo,view,null);
        return view;
    }

    /**
     * 根据条件进行查询用户信息且进行分页
     *
     * @param reqUserInfoQuery
     * @return
     */
    @Override
    public PageInfo<UserInfoView> findUserInfoPage(ReqUserInfoQuery reqUserInfoQuery) {
        log.info("根据条件进行查询且进行分页请求参数：{}",JSON.toJSON(reqUserInfoQuery));
        PageInfo<UserInfoView> pageInfo = null;
        try {
            Page<?> page = PageHelper.startPage(reqUserInfoQuery.getPageNum(),reqUserInfoQuery.getPageSize());
            List<UserInfo> userInfoList = userInfoMapper.findUserInfoPage(reqUserInfoQuery);
            if (CollectionUtils.isEmpty(userInfoList)){
                pageInfo = new PageInfo<>(new ArrayList<>());
            }else{
                List<UserInfoView> userInfoViews = new ArrayList<>();
                convertToUserInfoView(userInfoList, userInfoViews);
                pageInfo = new PageInfo<>(userInfoViews);
            }
            log.info("总共有:{}",page.getTotal()+"条数据,实际返回{}:",page.size()+"两条数据!");
        } catch (Exception e) {
            log.error("根据条件进行查询用户信息且进行分页 查询异常"+e.getMessage());
        }
        return pageInfo;
    }

    /**
     * 用户进行修改密码
     *
     * @param reqUserInfoUpdate
     * @return
     */
    @Override
    public int changePassword(ReqUserInfoUpdate reqUserInfoUpdate) {
        log.info("用户进行修改密码 请求参数：{}",JSON.toJSON(reqUserInfoUpdate));
        UserInfo queryUserInfo = userInfoMapper.findUserInfoByUserNameAndPassword(reqUserInfoUpdate.getUserName(),AesUtil.encrypt( Constants.SECRET_KEY,reqUserInfoUpdate.getPassword()));
        if (queryUserInfo == null){
            throw new BusinessException( BusinessEnum.RECORD_NOT_EXIST.getCode(), BusinessEnum.RECORD_NOT_EXIST.getMsg());
        }
        reqUserInfoUpdate.setNewPassword(AesUtil.encrypt( Constants.SECRET_KEY,reqUserInfoUpdate.getNewPassword()));
        return userInfoMapper.changePassword(reqUserInfoUpdate);
    }

    /**
     * 用户短信注册，发送验证码
     *
     * @param reqMessage
     * @return
     */
    @Override
    public MessageView messageRegister(ReqMessage reqMessage) {
        log.info("用户短信注册，发送验证码 请求参数：{}",JSON.toJSONString(reqMessage));
        if (StringUtils.isEmpty(reqMessage.getMobile())){
            throw new BusinessException( BusinessEnum.MISSING_PARAMETERS.getCode(), BusinessEnum.MISSING_PARAMETERS.getMsg());
        }
        ReqUserInfoQuery reqUserInfoQuery = new ReqUserInfoQuery();
        reqUserInfoQuery.setMobile(reqMessage.getMobile());
        //如果当前手机号已在系统中存在，则进行提示：该手机号用户已存在，不能重复注册
        UserInfo userInfo = userInfoMapper.findUserInfo(reqUserInfoQuery);
        if (userInfo != null){
            throw new BusinessException( BusinessEnum.USERINFO_NOT_EXIST.getCode(), BusinessEnum.USERINFO_NOT_EXIST.getMsg());
        }
        //发送短信为注册时,如果当前手机号| ip 30分钟内频繁的发送短信超过5条，则视为用户进行恶意攻击
        ReqMessageCountQuery2 messageCountQuery2 = new ReqMessageCountQuery2();
        messageCountQuery2.setMessageType( MessageTypeEnum.REGISTER.getCode());
        messageCountQuery2.setSendIp(reqMessage.getIp());
        messageCountQuery2.setMobile(reqMessage.getMobile());
        messageCountQuery2.setCurrentSendDate( DateUtil.dateToString(new Date()));
        List<MessageCountView> messageCount = messageCountService.findMessageCount(messageCountQuery2);
        log.info("当前手机号 | ip 30分钟之内发送的短信条数为：{}",messageCount.size());
        String sendMsm = smsSenderUtil.sendMsm(reqMessage.getMobile(), reqMessage.getSignId(), reqMessage.getMessageId());
        //如果调用发送短信返回信息为空，则抛出错误信息
        if (StringUtils.isEmpty(sendMsm)){
            throw new BusinessException( BusinessEnum.CALL_SEND_MSM_NULL.getCode(), BusinessEnum.CALL_SEND_MSM_NULL.getMsg());
        }
        JSONObject json = JSON.parseObject(sendMsm);
        JSONMessage jsonMessage = JSONObject.toJavaObject(json, JSONMessage.class);
        MessageView messageView = new MessageView();
        sendMessageByType(reqMessage, sendMsm, jsonMessage, messageView, MessageTypeEnum.REGISTER.getCode());
        return messageView;
    }

    /**
     * 用户短信注册，输入密码并校验验证码，验证通过则注册成功，验证失败则注册失败
     *
     * @param registerCodeView
     * @return
     */
    @Override
    public MessageView checkRegisterCode(ReqRegisterCode registerCode) {
        if (StringUtils.isEmpty(registerCode.getMobile())
                || StringUtils.isEmpty(registerCode.getPassword())
                || StringUtils.isEmpty(registerCode.getRepeatPassword())
                || StringUtils.isEmpty(registerCode.getRegisterCode())){
            throw new BusinessException( BusinessEnum.MISSING_PARAMETERS.getCode(), BusinessEnum.MISSING_PARAMETERS.getMsg());
        }
        if (!StringCustomizedUtils.stringTrim(registerCode.getPassword()).equals(StringCustomizedUtils.stringTrim(registerCode.getRepeatPassword()))){
            throw new BusinessException( BusinessEnum.PASSWORDS_INCONSISTENT.getCode(), BusinessEnum.PASSWORDS_INCONSISTENT.getMsg());
        }

        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes)ra).getRequest();

        String mobile = (String) request.getSession(true).getAttribute("mobile");
        String VerificationCode = (String) request.getSession(true).getAttribute("messageVerificationCode");

        if (!registerCode.getMobile().equals(mobile)){
            throw new BusinessException( BusinessEnum.REGISTER_VERIFICATION_MOBILE_DIFFERENT.getCode(), BusinessEnum.REGISTER_VERIFICATION_MOBILE_DIFFERENT.getMsg());
        }else if (!registerCode.getRegisterCode().equals(VerificationCode)){
            throw new BusinessException( BusinessEnum.VERIFICATION_CODE_ERROR_PLEASE_TRY_AGAIN.getCode(), BusinessEnum.VERIFICATION_CODE_ERROR_PLEASE_TRY_AGAIN.getMsg());
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setMobile(registerCode.getMobile());
        userInfo.setPassword(AesUtil.encrypt( Constants.SECRET_KEY,registerCode.getPassword()));
        userInfo.setUserStatus( UserInfoStatusEnum.NORMAL.getCode());
        int registerNum = userInfoMapper.insertSelective(userInfo);

        MessageView messageView = new MessageView();
        if (registerNum > 0){
            messageView.setCode( SystemCodeEnum.SUCCESS.getCode());
            messageView.setDesc("恭喜您注册成功");
        }else{
            messageView.setCode( SystemCodeEnum.ERROR.getCode());
            messageView.setDesc("对不起，注册失败，请重新注册");
        }
        return messageView;
    }

    /**
     * 根据[用户名&密码]|[用户昵称&密码]|[手机号&密码]|[身份证号&密码]进行登录
     *
     * @param reqLoginUserInfo
     * @return
     */
    @Override
    public UserInfoView login(ReqLoginUserInfo reqLoginUserInfo) {
        if (StringUtils.isEmpty(reqLoginUserInfo.getLoginType())){
            throw new BusinessException( BusinessEnum.LOGIN_TYPE_NULL.getCode(), BusinessEnum.LOGIN_TYPE_NULL.getMsg());
        }
        Integer loginType = reqLoginUserInfo.getLoginType();
        //首先去判断数据库有没有该用户 & 登录者的账号是否是正常账号
        ReqUserInfo reqUserInfo = new ReqUserInfo();
        BeanCopier copier = BeanCopier.create(ReqLoginUserInfo.class, ReqUserInfo.class, false);
        copier.copy(reqLoginUserInfo,reqUserInfo,null);
        UserInfo queryUserInfo = userInfoMapper.loginQueryUserInfo(reqUserInfo);

        if (queryUserInfo == null){
            insertLoginLog(reqLoginUserInfo);
        }else if(queryUserInfo != null & queryUserInfo.getUserStatus().equals( UserInfoStatusEnum.FROZEN.getCode())){
            ReqLoginLog loginLog = new ReqLoginLog();
            loginLog.setLoginDate(new Date());
            loginLog.setLoginIp(reqLoginUserInfo.getLoginIp());
            loginLog.setUserId(queryUserInfo.getId());
            loginLog.setLoginStatus( LoginStatus.FAIL.getCode());
            loginLog.setFailRemark( BusinessEnum.USERINFO_FROZEN.getMsg());
            log.info("记录登录日志请求参数：{}");
            loginLogService.insertLoginLog(loginLog);
            throw new BusinessException( BusinessEnum.USERINFO_FROZEN.getCode(), BusinessEnum.USERINFO_FROZEN.getMsg());
        }

        UserInfo userInfo = null;
        UserInfoView userInfoView = new UserInfoView();
        switch (loginType){
            case 1:
                userInfo = userInfoMapper.findUserByUserNameAndPassword(reqLoginUserInfo.getUserName(),AesUtil.encrypt( Constants.SECRET_KEY,reqLoginUserInfo.getPassword()));
                break;
            case 2:
                userInfo = userInfoMapper.findUserByUserNicknameAndPassword(reqLoginUserInfo.getUserNickname(),AesUtil.encrypt( Constants.SECRET_KEY,reqLoginUserInfo.getPassword()));
                break;
            case 3:
                userInfo = userInfoMapper.findMobileAndPassword(reqLoginUserInfo.getMobile(),AesUtil.encrypt( Constants.SECRET_KEY,reqLoginUserInfo.getPassword()));
                break;
            case 4:
                userInfo = userInfoMapper.findIdCardAndPassword(reqLoginUserInfo.getIdCard(),AesUtil.encrypt( Constants.SECRET_KEY,reqLoginUserInfo.getPassword()));
                break;
            default:
                userInfo = userInfoMapper.findUserByUserNameAndPassword(reqLoginUserInfo.getUserName(),AesUtil.encrypt( Constants.SECRET_KEY,reqLoginUserInfo.getPassword()));
                break;
        }
        if (userInfo == null){
            ReqLoginLog loginLog = new ReqLoginLog();
            loginLog.setLoginDate(new Date());
            loginLog.setLoginIp(reqLoginUserInfo.getLoginIp());
            loginLog.setUserId(queryUserInfo.getId());
            loginLog.setLoginStatus( LoginStatus.FAIL.getCode());
            loginLog.setFailRemark( BusinessEnum.LOGIN_ERROR.getMsg());
            log.info("记录登录日志请求参数：{}");
            loginLogService.insertLoginLog(loginLog);
            throw new BusinessException( BusinessEnum.LOGIN_ERROR.getCode(), BusinessEnum.LOGIN_ERROR.getMsg());
        }else{
            ReqLoginLog loginLog = new ReqLoginLog();
            loginLog.setLoginDate(new Date());
            loginLog.setLoginIp(reqLoginUserInfo.getLoginIp());
            loginLog.setUserId(queryUserInfo.getId());
            loginLog.setLoginStatus( LoginStatus.SUCCESS.getCode());
            loginLog.setFailRemark("登录成功");
            log.info("记录登录日志请求参数：{}",JSON.toJSONString(loginLog));
            loginLogService.insertLoginLog(loginLog);
        }
        BeanCopier beanCopier = BeanCopier.create( UserInfo.class, UserInfoView.class, false);
        beanCopier.copy(userInfo,userInfoView,null);
        return userInfoView;
    }

    /**
     * 根据Id 获取用户记录详情
     *
     * @param id
     * @return
     */
    @Override
    public UserInfoView findUserById(Integer id) {
        log.info("根据Id 获取用户记录详情 请求参数：{}",id);
        if (StringUtils.isEmpty(id.toString())){
            throw new BusinessException( BusinessEnum.MISSING_PARAMETERS.getCode(), BusinessEnum.MISSING_PARAMETERS.getMsg());
        }
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(id);
        if (userInfo == null){
            return null;
        }
        UserInfoView view = new UserInfoView();
        BeanCopier beanCopier = BeanCopier.create( UserInfo.class,UserInfoView.class,false);
        beanCopier.copy(userInfo,view,null);
        return view;
    }

    /**
     * 使用短信进行登录，发送验证码
     *
     * @param reqMessage
     * @return
     */
    @Override
    public MessageView loginByMessage(ReqMessage reqMessage) {
        log.info("使用短信进行登录，发送验证码 请求参数：{}",JSON.toJSONString(reqMessage));
        String sendMsm = smsSenderUtil.sendMsm(reqMessage.getMobile(), reqMessage.getSignId(), reqMessage.getMessageId());
        //如果调用发送短信返回信息为空，则抛出错误信息
        if (StringUtils.isEmpty(sendMsm)){
            throw new BusinessException( BusinessEnum.CALL_SEND_MSM_NULL.getCode(), BusinessEnum.CALL_SEND_MSM_NULL.getMsg());
        }
        JSONObject json = JSON.parseObject(sendMsm);
        JSONMessage jsonMessage = JSONObject.toJavaObject(json, JSONMessage.class);
        MessageView messageView = new MessageView();
        sendMessageByType(reqMessage, sendMsm, jsonMessage, messageView, MessageTypeEnum.LOGIN.getCode());
        return messageView;
    }

    /**
     * 用户短信登录，输入验证码，验证通过则登录成功，验证失败则登录失败
     *
     * @param reqLoginUserInfo
     * @return
     */
    @Override
    public UserInfoView checkLoginCode(ReqLoginUserInfo reqLoginUserInfo) {
        if (StringUtils.isEmpty(reqLoginUserInfo.getMobile())
                || StringUtils.isEmpty(reqLoginUserInfo.getVerificationCode())){
            throw new BusinessException( BusinessEnum.MISSING_PARAMETERS.getCode(), BusinessEnum.MISSING_PARAMETERS.getMsg());
        }

        UserInfoView userInfoView = new UserInfoView();

        //首先去判断数据库有没有该用户 & 登录者的账号是否是正常账号
        ReqUserInfo reqUserInfo = new ReqUserInfo();
        BeanCopier copier = BeanCopier.create(ReqLoginUserInfo.class, ReqUserInfo.class, false);
        copier.copy(reqLoginUserInfo,reqUserInfo,null);
        UserInfo queryUserInfo = userInfoMapper.loginQueryUserInfo(reqUserInfo);
        if (queryUserInfo == null){
            return insertLoginLog(reqLoginUserInfo);
        }else{
            RequestAttributes ra = RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = ((ServletRequestAttributes)ra).getRequest();

            String mobile = (String) request.getSession(true).getAttribute("mobile");
            String VerificationCode = (String) request.getSession(true).getAttribute("messageVerificationCode");

            if (!reqLoginUserInfo.getMobile().equals(mobile)){
                throw new BusinessException( BusinessEnum.LOGIN_VERIFICATION_MOBILE_DIFFERENT.getCode(), BusinessEnum.LOGIN_VERIFICATION_MOBILE_DIFFERENT.getMsg());
            }else if (!reqLoginUserInfo.getVerificationCode().equals(VerificationCode)){
                throw new BusinessException( BusinessEnum.VERIFICATION_CODE_ERROR_PLEASE_TRY_AGAIN.getCode(), BusinessEnum.VERIFICATION_CODE_ERROR_PLEASE_TRY_AGAIN.getMsg());
            }else{
                BeanCopier beanCopier = BeanCopier.create( UserInfo.class,UserInfoView.class,false);
                beanCopier.copy(queryUserInfo,userInfoView,null);

                ReqLoginLog loginLog = new ReqLoginLog();
                loginLog.setLoginDate(new Date());
                loginLog.setLoginIp(reqLoginUserInfo.getLoginIp());
                loginLog.setUserId(0);
                loginLog.setLoginStatus( LoginStatus.SUCCESS.getCode());
                loginLog.setFailRemark("短信登录成功");
                log.info("记录登录日志请求参数：{}");
                loginLogService.insertLoginLog(loginLog);
            }
        }
        return userInfoView;
    }

    private UserInfoView insertLoginLog(ReqLoginUserInfo reqLoginUserInfo) {
        ReqLoginLog loginLog = new ReqLoginLog();
        loginLog.setLoginDate(new Date());
        loginLog.setLoginIp(reqLoginUserInfo.getLoginIp());
        loginLog.setUserId(0);
        loginLog.setLoginStatus( LoginStatus.FAIL.getCode());
        loginLog.setFailRemark( BusinessEnum.USERINFO_EXIST.getMsg());
        log.info("记录登录日志请求参数：{}");
        loginLogService.insertLoginLog(loginLog);
        throw new BusinessException( BusinessEnum.USERINFO_EXIST.getCode(), BusinessEnum.USERINFO_EXIST.getMsg());
    }

    private void sendMessageByType(ReqMessage reqMessage, String sendMsm, JSONMessage jsonMessage, MessageView messageView, Integer messageType) {
        if (jsonMessage.getCode().equals( SystemCodeEnum.SUCCESS.getCode())){
            messageView.setCode( SystemCodeEnum.SUCCESS.getCode());
            messageView.setDesc("短信发送成功");
            //发送短信成功，则往短信统计记录表中插入相关数据
            ReqMessageCount reqMessageCount = new ReqMessageCount();
            reqMessageCount.setSendIp(reqMessage.getIp());
            reqMessageCount.setCreateDate(new Date());
            reqMessageCount.setUpdateDate(new Date());
            reqMessageCount.setFounder(reqMessage.getMobile());
            reqMessageCount.setModifier(reqMessage.getMobile());
            reqMessageCount.setMobile(reqMessage.getMobile());
            if (messageType.equals( MessageTypeEnum.REGISTER.getCode())){
                reqMessageCount.setMessageType( MessageTypeEnum.REGISTER.getCode());
                reqMessageCount.setSendContent("您正在进行使用短信注册新账号：{}"+ JSON.toJSONString(sendMsm));
            }else if(messageType.equals( MessageTypeEnum.LOGIN.getCode())){
                reqMessageCount.setMessageType( MessageTypeEnum.LOGIN.getCode());
                reqMessageCount.setSendContent("您正在进行使用短信登录账号：{}"+ JSON.toJSONString(sendMsm));
            }
            messageCountService.insertMessageCount(reqMessageCount);
        }else if (jsonMessage.getCode().equals( SystemCodeEnum.EXCEPTION.getCode()) ){
            messageView.setCode( SystemCodeEnum.ERROR.getCode());
            messageView.setDesc("短信发送异常");
        }else{
            messageView.setCode( SystemCodeEnum.ERROR.getCode());
            messageView.setDesc("网络问题，请稍后重试");
        }
    }


}
