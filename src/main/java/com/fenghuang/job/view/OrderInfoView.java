package com.fenghuang.job.view;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2019/12/20 21:34
 * @Version: 1.0
 * @Email: 15290810931@163.com
 */
@ApiModel(value = "订单信息表")
@Data
public class OrderInfoView implements Serializable {

    @ApiModelProperty("id")
    private Integer id;

    @ApiModelProperty("订单id,使用规则生成")
    private String orderId;

    @ApiModelProperty("商户ID")
    private Integer businessId;

    @ApiModelProperty("用户id")
    private Integer userId;

    @ApiModelProperty("项目类型id")
    private Integer projectTypeId;

    @ApiModelProperty("项目类型名称")
    private String projectTypeName;

    @ApiModelProperty("项目金额")
    private Long projectAmount;

    @ApiModelProperty("订单价格")
    private Long orderAmount;

    @ApiModelProperty("项目ID")
    private Integer projectId;

    @ApiModelProperty("项目名称")
    private String projectName;

    @ApiModelProperty("订单状态:1待开始 2进行中 3交易成功 4订单取消[用户] 5订单关闭[商家]")
    private Integer orderStatus;

    @ApiModelProperty("原因")
    private String remark;

    @ApiModelProperty("订单生成时间")
    private Date orderDate;

    @ApiModelProperty("支付时间")
    private Date orderPayDate;

    @ApiModelProperty("创建人")
    private String founder;

    @ApiModelProperty("修改人")
    private String modifier;

    @ApiModelProperty("创建时间")
    private Date createDate;

    @ApiModelProperty("修改时间")
    private Date updateDate;

}
