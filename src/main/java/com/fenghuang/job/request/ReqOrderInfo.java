package com.fenghuang.job.request;

import com.fenghuang.job.utils.PageHelps;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2019/12/20 21:22
 * @Version: 1.0
 * @Email: 15290810931@163.com
 */
@Data
public class ReqOrderInfo extends PageHelps implements Serializable {

    private Integer id;

    @ApiModelProperty("订单id,使用规则生成")
    private String orderId;

    @ApiModelProperty("商户ID")
    private Integer businessId;

    @ApiModelProperty("用户ID")
    private Integer userId;

    @ApiModelProperty("项目类型id")
    private Integer projectTypeId;

    @ApiModelProperty("项目类型名称")
    private String projectTypeName;

    @ApiModelProperty("项目金额")
    private BigDecimal projectAmount;

    @ApiModelProperty("订单金额")
    private BigDecimal orderAmount;

    @ApiModelProperty("项目ID")
    private Integer projectId;

    @ApiModelProperty("项目名称")
    private String projectName;

    @ApiModelProperty("订单状态:1待开始 2进行中 3交易成功 4订单取消[用户] 5订单关闭[商家]")
    private Integer orderStatus;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("订单生成时间")
    private Date orderDate;

    @ApiModelProperty("订单支付时间")
    private Date orderPayDate;

    @ApiModelProperty("订单开始时间")
    private String orderDateBegin;

    @ApiModelProperty("订单结束时间")
    private String orderDateEnd;

    @ApiModelProperty("订单支付开始时间")
    private String orderPayDateBegin;

    @ApiModelProperty("订单支付结束时间")
    private String orderPayDateEnd;

}
