package com.fenghuang.job.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@ApiModel(value = "提现订单信息表")
public class CashWithdrawal implements Serializable{
    private Integer id;

    private String cashWithdrawalOrderId;

    private Integer userId;

    private Long cashWithdrawalAmount;

    private String cashWithdrawalAccount;

    private Integer cashWithdrawalAccountType;

    private Integer cashWithdrawalStatus;

    private Integer examineStatus;

    private Date cashWithdrawalDate;

    private Date examineDate;

    private String founder;

    private String modifier;

    private Date createDate;

    private Date updateDate;

}