package com.fenghuang.job.dao.master;

import com.fenghuang.job.entity.Recharge;
import com.fenghuang.job.request.ReqRecharge;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RechargeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Recharge record);

    int insertSelective(Recharge record);

    Recharge selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Recharge record);

    int updateByPrimaryKey(Recharge record);

    List<Recharge> findRechargePage(ReqRecharge reqRecharge);
}