package com.fenghuang.job.service;

import com.fenghuang.job.request.ReqRecharge;
import com.fenghuang.job.view.RechargeView;
import com.github.pagehelper.PageInfo;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2019/12/30 15:24
 * @Email: 15290810931@163.com
 */
public interface RechargeService {

    /**
     * 新增用户充值订单信息
     * @param reqRecharge
     * @return
     */
    String insertRecharge(ReqRecharge reqRecharge);

    /**
     * 根据条件查询用户充值订单信息带分页
     * @param reqRecharge
     * @return
     */
    PageInfo<RechargeView> findRechargePage(ReqRecharge reqRecharge);
}
