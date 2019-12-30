package com.fenghuang.job.service;

import com.fenghuang.job.request.ReqCashWithdrawal;
import com.fenghuang.job.view.CashWithdrawalView;
import com.github.pagehelper.PageInfo;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2019/12/20 10:46
 * @Email: 15290810931@163.com
 */
public interface CashWithdrawalService {

    /**
     * 提现申请
     * @param reqCashWithdrawal
     * @return
     */
    int insertCashWithdrawal(ReqCashWithdrawal reqCashWithdrawal);

    /**
     * 根据条件查询且分页提现订单申请信息表
     * @param reqCashWithdrawal
     * @return
     */
    PageInfo<CashWithdrawalView> findCashWithdrawalPage(ReqCashWithdrawal reqCashWithdrawal);

    /**
     * 根据订单id更新提现订单信息表状态
     * @param reqCashWithdrawal
     * @return
     */
    int modifyCashWithdrawalStatus(ReqCashWithdrawal reqCashWithdrawal);

    /**
     * 根据Id 查询提现订单相关信息
     * @param id
     * @return
     */
    CashWithdrawalView findCashWithdrawalById(Integer id);
}
