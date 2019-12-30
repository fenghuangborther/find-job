package com.fenghuang.job.dao.master;

import com.fenghuang.job.entity.CashWithdrawal;
import com.fenghuang.job.request.ReqCashWithdrawal;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CashWithdrawalMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CashWithdrawal record);

    int insertSelective(CashWithdrawal record);

    CashWithdrawal selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CashWithdrawal record);

    int updateByPrimaryKey(CashWithdrawal record);

    List<CashWithdrawal> findCashWithdrawal(ReqCashWithdrawal reqCashWithdrawal);
}