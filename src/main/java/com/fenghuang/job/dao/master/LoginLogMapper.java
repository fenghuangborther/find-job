package com.fenghuang.job.dao.master;

import com.fenghuang.job.entity.LoginLog;
import com.fenghuang.job.request.ReqLoginLog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LoginLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LoginLog record);

    int insertSelective(LoginLog record);

    LoginLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LoginLog record);

    int updateByPrimaryKey(LoginLog record);

    List<LoginLog> findLogin(ReqLoginLog reqLoginLog);
}