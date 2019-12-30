package com.fenghuang.job.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@ApiModel(value = "订单信息表")
@Data
public class OrderInfo implements Serializable{
    private Integer id;

    private String orderId;

    private Integer businessId;

    private Integer userId;

    private Integer projectTypeId;

    private String projectTypeName;

    private Long projectAmount;

    private Long orderAmount;

    private Integer projectId;

    private String projectName;

    private Integer orderStatus;

    private String remark;

    private Date orderDate;

    private Date orderPayDate;

    private String founder;

    private String modifier;

    private Date createDate;

    private Date updateDate;

}