package com.fenghuang.job.view;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2019/12/30 15:29
 * @Email: 15290810931@163.com
 */
@Data
public class RechargeView implements Serializable{

    private Integer id;

    @ApiModelProperty("订单id")
    private String rechargeOrderId;

    @ApiModelProperty("用户ID")
    private Integer userId;

    @ApiModelProperty("充值金额")
    private BigDecimal rechargeAmount;

    @ApiModelProperty("充值账号")
    private String rechargeAccount;

    @ApiModelProperty("充值账号类型：1 支付宝 2 微信 3 qq 4 银行 5 红包 6转账")
    private Integer rechargeAccountType;

    @ApiModelProperty("支付时间")
    private Date rechargeDate;

    @ApiModelProperty("充值订单状态：1 已成功 2 已失败 3 处理中")
    private Integer rechargeOrderStatus;

    @ApiModelProperty("第三方回调参数")
    private String threadCallbackParams;

    @ApiModelProperty("创建人")
    private String founder;

    @ApiModelProperty("修改人")
    private String modifier;

    @ApiModelProperty("创建时间")
    private Date createDate;

    @ApiModelProperty("修改时间")
    private Date updateDate;

}
