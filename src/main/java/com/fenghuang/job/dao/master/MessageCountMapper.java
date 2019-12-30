package com.fenghuang.job.dao.master;

import com.fenghuang.job.entity.MessageCount;
import com.fenghuang.job.request.ReqMessageCountQuery;
import com.fenghuang.job.request.ReqMessageCountQuery2;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MessageCountMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MessageCount record);

    int insertSelective(MessageCount record);

    MessageCount selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MessageCount record);

    int updateByPrimaryKey(MessageCount record);

    List<MessageCount> findMessageCountPage(ReqMessageCountQuery messageCountQuery);

    List<MessageCount> findMessageCount(ReqMessageCountQuery2 reqMessageCountQuery2);
}