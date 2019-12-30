package com.fenghuang.job.request;

import com.fenghuang.job.utils.PageHelps;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2019/12/20 10:56
 * @Email: 15290810931@163.com
 */
@Data
public class ReqCashWithdrawal extends PageHelps implements Serializable{

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

    @ApiModelProperty("提现时间")
    private Date cashWithdrawalDate;

    @ApiModelProperty("审核时间")
    private Date examineDate;

    @ApiModelProperty("提现开始时间")
    private String cashWithdrawalDateBegin;

    @ApiModelProperty("提现结束时间")
    private String cashWithdrawalDateEnd;

    @ApiModelProperty("审核开始时间")
    private String examineDateBegin;

    @ApiModelProperty("审核结束时间")
    private String examineDateEnd;
}
