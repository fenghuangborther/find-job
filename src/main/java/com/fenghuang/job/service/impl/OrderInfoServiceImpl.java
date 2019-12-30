package com.fenghuang.job.service.impl;

import com.alibaba.fastjson.JSON;
import com.fenghuang.job.dao.master.OrderInfoMapper;
import com.fenghuang.job.entity.OrderInfo;
import com.fenghuang.job.enums.BusinessEnum;
import com.fenghuang.job.enums.OrderStatusEnum;
import com.fenghuang.job.exception.BusinessException;
import com.fenghuang.job.request.ReqOrderInfo;
import com.fenghuang.job.service.OrderInfoService;
import com.fenghuang.job.utils.OrderIdUtils;
import com.fenghuang.job.view.OrderInfoView;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2019/12/20 21:59
 * @Version: 1.0
 * @Email: 15290810931@163.com
 */
@Slf4j
@Service
public class OrderInfoServiceImpl implements OrderInfoService {

    @Autowired
    OrderInfoMapper orderInfoMapper;
    /**
     * 生成订单且返回订单号
     *
     * @param reqOrderInfo
     * @return
     */
    @Override
    public String createOrder(ReqOrderInfo reqOrderInfo) {
        log.info( "生成订单 请求参数:{}", JSON.toJSONString( reqOrderInfo ) );
        OrderInfo orderInfo = new OrderInfo();
        BeanCopier beanCopier = BeanCopier.create( ReqOrderInfo.class, OrderInfo.class, false );
        beanCopier.copy( reqOrderInfo,orderInfo,null );
        orderInfo.setOrderStatus( OrderStatusEnum.WAIT_BEGIN.getCode() );
        orderInfo.setOrderId( OrderIdUtils.generateOrderSn( reqOrderInfo.getUserId().toString() ) );
        orderInfo.setOrderDate( new Date(  ) );
        orderInfo.setFounder(reqOrderInfo.getUserId().toString());
        orderInfo.setModifier(reqOrderInfo.getUserId().toString());
        orderInfo.setCreateDate(new Date());
        orderInfo.setUpdateDate(new Date());
        orderInfoMapper.insertSelective( orderInfo );
        String orderId = orderInfo.getOrderId();
        log.info( "生成订单返回的订单号为：{}",orderId );
        return orderId;
    }

    /**
     * 根据条件进行查询订单信息表且分页
     *
     * @param reqOrderInfo
     * @return
     */
    @Override
    public PageInfo<OrderInfoView> findOrderInfoPage(ReqOrderInfo reqOrderInfo) {
        log.info( "根据条件进行查询订单信息表且分页 请求参数：{}",JSON.toJSONString( reqOrderInfo ) );
        PageInfo<OrderInfoView> pageInfo = null;
        try{
            Page<?> page = PageHelper.startPage(reqOrderInfo.getPageNum(),reqOrderInfo.getPageSize());
            List<OrderInfo> queryOrderInfo = orderInfoMapper.findOrderInfoPage(reqOrderInfo);
            if (CollectionUtils.isEmpty( queryOrderInfo )){
                pageInfo = new PageInfo<>( new ArrayList<>(  ) );
            }else{
                List<OrderInfoView> views = new ArrayList<>(  );
                queryOrderInfo.stream().forEach( orderInfo -> {
                    OrderInfoView view = new OrderInfoView();
                    BeanCopier copier = BeanCopier.create( OrderInfo.class, OrderInfoView.class, false );
                    copier.copy( orderInfo,view,null );
                    views.add( view );
                } );
                pageInfo = new PageInfo<>( views );
            }
            log.info("总共有:{}",page.getTotal()+"条数据,实际返回{}:",page.size()+"两条数据!");
        }catch (Exception e){
           log.info( "根据条件进行查询订单信息表且分页 查询异常:{}",e.getMessage() );
        }
        return pageInfo;
    }

    /**
     * 根据订单id 获取订单详情
     *
     * @param id
     * @return
     */
    @Override
    public OrderInfoView queryOrderDetailsById(Integer id) {
        log.info( "根据订单id 获取订单详情 请求参数:{}",id );
        OrderInfo queryOrderInfo = orderInfoMapper.selectByPrimaryKey( id );
        if (queryOrderInfo == null){
            return null;
        }
        OrderInfoView orderInfoView = new OrderInfoView();
        BeanCopier copier = BeanCopier.create( OrderInfo.class, OrderInfoView.class, false );
        copier.copy( queryOrderInfo,orderInfoView,null );
        log.info( "根据订单id 获取订单详情 返回结果：{}",JSON.toJSONString( queryOrderInfo ) );
        return orderInfoView;
    }

    /**
     * 更新订单状态
     *
     * @param reqOrderInfo
     * @return
     */
    @Override
    public int modifyOrderStatus(ReqOrderInfo reqOrderInfo) {
        log.info( "更新订单状态 请求参数：{}",JSON.toJSONString( reqOrderInfo ) );
        if (StringUtils.isEmpty(reqOrderInfo.getId())){
            throw new BusinessException( BusinessEnum.MISSING_PARAMETERS.getCode(), BusinessEnum.MISSING_PARAMETERS.getMsg());
        }
        OrderInfo orderInfo = new OrderInfo();
        BeanCopier copier = BeanCopier.create( ReqOrderInfo.class, OrderInfo.class, false );
        copier.copy( reqOrderInfo,orderInfo,null );
        orderInfo.setUpdateDate(new Date());
        orderInfo.setModifier(reqOrderInfo.getUserId().toString());
        return orderInfoMapper.updateByPrimaryKeySelective( orderInfo );
    }
}
