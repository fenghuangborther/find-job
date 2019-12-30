package com.fenghuang.job.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@ApiModel(value = "充值信息表")
public class Recharge implements Serializable{
    private Integer id;

    private String rechargeOrderId;

    private Integer userId;

    private BigDecimal rechargeAmount;

    private String rechargeAccount;

    private Integer rechargeAccountType;

    private Date rechargeDate;

    private Integer rechargeOrderStatus;

    private String threadCallbackParams;

    private String founder;

    private String modifier;

    private Date createDate;

    private Date updateDate;

}