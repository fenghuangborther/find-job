package com.fenghuang.job.view;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2019/12/20 10:28
 * @Email: 15290810931@163.com
 */
@Data
@ApiModel(value = "提现订单信息表")
public class CashWithdrawalView implements Serializable{

    @ApiModelProperty("id")
    private Integer id;

    @ApiModelProperty("提现订单号")
    private String cashWithdrawalOrderId;

    @ApiModelProperty("用户id")
    private Integer userId;

    @ApiModelProperty("提现金额")
    private BigDecimal cashWithdrawalAmount;

    @ApiModelProperty("提现账号")
    private String cashWithdrawalAccount;

    @ApiModelProperty("提现账号类型：1 支付宝 2 微信 3 qq 4 银行卡 5 红包 6 转账")
    private Integer cashWithdrawalAccountType;

    @ApiModelProperty("提现状态：1 处理中 2 成功 3 失败")
    private Integer cashWithdrawalStatus;

    @ApiModelProperty("审核状态：1 待审核 2 已通过 3已驳回")
    private Integer examineStatus;

    @ApiModelProperty("提现时间")
    private Date cashWithdrawalDate;

    @ApiModelProperty("审核时间")
    private Date examineDate;

    @ApiModelProperty("创建人")
    private String founder;

    @ApiModelProperty("修改人")
    private String modifier;

    @ApiModelProperty("创建时间")
    private Date createDate;

    @ApiModelProperty("修改时间")
    private Date updateDate;

}
