package com.fenghuang.job.service;

import com.fenghuang.job.request.ReqOrderInfo;
import com.fenghuang.job.view.OrderInfoView;
import com.github.pagehelper.PageInfo;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2019/12/20 21:21
 * @Version: 1.0
 * @Email: 15290810931@163.com
 */
public interface OrderInfoService {
    /**
     * 生成订单且返回订单号
     * @param reqOrderInfo
     * @return
     */
    String createOrder(ReqOrderInfo reqOrderInfo);

    /**
     * 根据条件进行查询订单信息表且分页
     * @param reqOrderInfo
     * @return
     */
    PageInfo<OrderInfoView> findOrderInfoPage(ReqOrderInfo reqOrderInfo);

    /**
     * 根据订单id 获取订单详情
     * @param id
     * @return
     */
    OrderInfoView queryOrderDetailsById(Integer id);

    /**
     * 更新订单状态
     * @param reqOrderInfo
     * @return
     */
    int modifyOrderStatus(ReqOrderInfo reqOrderInfo);
}
