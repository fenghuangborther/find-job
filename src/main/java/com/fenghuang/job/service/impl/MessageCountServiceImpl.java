package com.fenghuang.job.service.impl;

import com.alibaba.fastjson.JSON;
import com.fenghuang.job.constant.Constants;
import com.fenghuang.job.dao.master.MessageCountMapper;
import com.fenghuang.job.entity.MessageCount;
import com.fenghuang.job.enums.BusinessEnum;
import com.fenghuang.job.exception.BusinessException;
import com.fenghuang.job.request.ReqMessageCount;
import com.fenghuang.job.request.ReqMessageCountQuery;
import com.fenghuang.job.request.ReqMessageCountQuery2;
import com.fenghuang.job.service.MessageCountService;
import com.fenghuang.job.utils.DateUtil;
import com.fenghuang.job.view.MessageCountView;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2019/12/25 18:08
 * @Email: 15290810931@163.com
 */
@Slf4j
@Service
public class MessageCountServiceImpl implements MessageCountService {

    @Autowired
    MessageCountMapper messageCountMapper;
    /**
     * 插入短信统计表
     *
     * @param reqMessageCount
     * @return
     */
    @Override
    public int insertMessageCount(ReqMessageCount reqMessageCount) {
        log.info("插入短信统计表 请求参数：{}", JSON.toJSONString(reqMessageCount));
        MessageCount messageCount = new MessageCount();
        BeanCopier beanCopier = BeanCopier.create(ReqMessageCount.class, MessageCount.class,false);
        beanCopier.copy(reqMessageCount,messageCount,null);
        messageCount.setCreateDate(new Date());
        messageCount.setUpdateDate(new Date());
        messageCount.setSendDate(new Date());
        messageCount.setFounder(reqMessageCount.getFounder());
        messageCount.setModifier(reqMessageCount.getFounder());
        return messageCountMapper.insertSelective(messageCount);
    }

    /**
     * 根据条件进行查询短信统计且分页
     *
     * @param messageCountQuery
     * @return
     */
    @Override
    public PageInfo<MessageCountView> findMessageCountPage(ReqMessageCountQuery messageCountQuery) {
        log.info("根据条件进行查询短信统计且分页 请求参数：{}",JSON.toJSONString(messageCountQuery));
        PageInfo<MessageCountView> pageInfo = null;
        try{
            Page<?> page = PageHelper.startPage(messageCountQuery.getPageNum(),messageCountQuery.getPageSize());
            List<MessageCount> queryMessageCount = messageCountMapper.findMessageCountPage(messageCountQuery);
            if (CollectionUtils.isEmpty( queryMessageCount )){
                pageInfo = new PageInfo<>( new ArrayList<>(  ) );
            }else{
                List<MessageCountView> views = new ArrayList<>();
                convertView(queryMessageCount, views);
                pageInfo = new PageInfo<>( views );
            }
            log.info("总共有:{}",page.getTotal()+"条数据,实际返回{}:",page.size()+"两条数据!");
        }catch (Exception e){
            log.info( "根据条件进行查询短信统计且分页 查询异常:{}",e.getMessage() );
        }
        return pageInfo;
    }

    /**
     * 根据条件统计一个人一小时发送短信的条数
     *
     * @param reqMessageCountQuery2
     */
    @Override
    public List<MessageCountView> findMessageCount(ReqMessageCountQuery2 reqMessageCountQuery2){
        log.info(" 根据条件统计一个人30分钟之内发送短信的条数 请求参数：{}",JSON.toJSONString(reqMessageCountQuery2));
        try {
            reqMessageCountQuery2.setOneHourAgoDate( DateUtil.subMinute(DateUtils.parseDate(reqMessageCountQuery2.getCurrentSendDate(),"yyyy-MM-dd hh:mm:ss"), Constants.MESSAGE_MINUTE));
        } catch (ParseException e) {
            log.info("根据条件统计一个人30分钟之内发送短信的条数 时间转换异常：{}",e.getMessage());
        }
        List<MessageCount> queryMessageCount = messageCountMapper.findMessageCount(reqMessageCountQuery2);
        log.info("根据条件统计一个人30分钟之内发送短信的条数 返回记录：{}",JSON.toJSONString(queryMessageCount));
        if (queryMessageCount.size() > Constants.MESSAGE_COUNT){
            throw new BusinessException( BusinessEnum.FREQUENT_OPERATION_PLEASE_TRY_AGAIN_LATER.getCode(), BusinessEnum.FREQUENT_OPERATION_PLEASE_TRY_AGAIN_LATER.getMsg());
        }
        List<MessageCountView> views = new ArrayList<>();
        convertView(queryMessageCount, views);
        return views;
    }

    private void convertView(List<MessageCount> queryMessageCount, List<MessageCountView> views) {
        queryMessageCount.stream().forEach(messageCount -> {
            MessageCountView view = new MessageCountView();
            BeanCopier beanCopier = BeanCopier.create( MessageCount.class, MessageCountView.class, false);
            beanCopier.copy(messageCount,view,null);
            views.add(view);
        });
    }
}
