package com.fenghuang.job.service;

import com.fenghuang.job.request.ReqMessageCount;
import com.fenghuang.job.request.ReqMessageCountQuery;
import com.fenghuang.job.request.ReqMessageCountQuery2;
import com.fenghuang.job.view.MessageCountView;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2019/12/25 17:49
 * @Email: 15290810931@163.com
 */
public interface MessageCountService {

    /**
     *插入短信统计表
     * @param reqMessageCount
     * @return
     */
    int insertMessageCount(ReqMessageCount reqMessageCount);

    /**
     * 根据条件进行查询短信统计且分页
     * @param messageCountQuery
     * @return
     */
    PageInfo<MessageCountView> findMessageCountPage(ReqMessageCountQuery messageCountQuery);
    /**
     * 根据条件统计一个人30分钟发送短信的条数
     */
    List<MessageCountView> findMessageCount(ReqMessageCountQuery2 reqMessageCountQuery2);
}
