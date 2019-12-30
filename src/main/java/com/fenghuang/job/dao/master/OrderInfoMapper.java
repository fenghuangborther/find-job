package com.fenghuang.job.dao.master;

import com.fenghuang.job.entity.OrderInfo;
import com.fenghuang.job.request.ReqOrderInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderInfo record);

    int insertSelective(OrderInfo record);

    OrderInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderInfo record);

    int updateByPrimaryKey(OrderInfo record);

    List<OrderInfo> findOrderInfoPage(ReqOrderInfo reqOrderInfo);
}