package com.fenghuang.job.service.impl;

import com.alibaba.fastjson.JSON;
import com.fenghuang.job.dao.master.LoginLogMapper;
import com.fenghuang.job.entity.LoginLog;
import com.fenghuang.job.request.ReqLoginLog;
import com.fenghuang.job.service.LoginLogService;
import com.fenghuang.job.view.LoginLogView;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2019/12/19 16:10
 * @Email: 15290810931@163.com
 */
@Service
@Slf4j
public class LoginLogServiceImpl implements LoginLogService {

    @Autowired
    LoginLogMapper loginLogMapper;

    /**
     * 根据条件查询日志信息且分页
     * @param reqLoginLog
     * @return
     */
    @Override
    public PageInfo<LoginLogView> findLoginLogPage(ReqLoginLog reqLoginLog) {
        log.info("根据条件查询日志信息且分页 请求参数：{}",JSON.toJSONString(reqLoginLog));
        PageInfo <LoginLogView> pageInfo = null;
        try {
            Page<?> page = PageHelper.startPage(reqLoginLog.getPageNum(),reqLoginLog.getPageSize());
            List<LoginLog> queryLoginLog = loginLogMapper.findLogin(reqLoginLog);
            if (CollectionUtils.isEmpty(queryLoginLog)){
                pageInfo = new PageInfo<>(new ArrayList<>());
            }else{
                List<LoginLogView> views = new ArrayList<>();
                convertLoginLogView(queryLoginLog, views);
                pageInfo = new PageInfo<>(views);
            }
            log.info("总共有:{}",page.getTotal()+"条数据,实际返回{}:",page.size()+"两条数据!");
        }catch (Exception e){
            log.info("根据条件查询日志信息且分页异常：{}",e);
        }
        return pageInfo;
    }

    private void convertLoginLogView(List<LoginLog> queryLoginLog, List<LoginLogView> views) {
        queryLoginLog.stream().forEach(loginLog -> {
            LoginLogView view = new LoginLogView();
            BeanCopier beanCopier = BeanCopier.create( LoginLog.class, LoginLogView.class, false);
            beanCopier.copy(loginLog,view,null);
            views.add(view);
        });
    }

    /**
     * 根据userId|状态| 日期|ip 条件进行查询登录日志
     *
     * @param reqLoginLog
     * @return
     */
    @Override
    public List<LoginLogView> findLogin(ReqLoginLog reqLoginLog) {
        log.info("根据userId|状态| 日期|ip 条件进行查询登录日志请求参数：{}",JSON.toJSON(reqLoginLog));
        List<LoginLog> queryLoginLogs = loginLogMapper.findLogin(reqLoginLog);
        if (CollectionUtils.isEmpty(queryLoginLogs)){
            return new ArrayList<>();
        }
        List<LoginLogView> views = new ArrayList<>();
        convertLoginLogView(queryLoginLogs, views);
        return views;
    }

    /**
     * 添加登录日志
     *
     * @param reqLoginLog
     * @return
     */
    @Override
    public int insertLoginLog(ReqLoginLog reqLoginLog) {
        log.info("添加登录日志请求参数：{}", JSON.toJSONString(reqLoginLog));
        LoginLog loginLog = new LoginLog();
        BeanCopier beanCopier = BeanCopier.create(ReqLoginLog.class, LoginLog.class, false);
        beanCopier.copy(reqLoginLog,loginLog,null);
        loginLog.setCreateDate(new Date());
        loginLog.setUpdateDate(new Date());
        loginLog.setFounder(reqLoginLog.getUserId().toString());
        loginLog.setModifier(reqLoginLog.getUserId().toString());
        return loginLogMapper.insertSelective(loginLog);
    }
}
