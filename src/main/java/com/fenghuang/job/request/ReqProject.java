package com.fenghuang.job.request;

import com.fenghuang.job.utils.PageHelps;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2019/12/22 12:16
 * @Version: 1.0
 * @Email: 15290810931@163.com
 */
@Data
public class ReqProject extends PageHelps implements Serializable {

    private Integer id;

    @ApiModelProperty("项目类型id")
    private Integer projectTypeId;

    @ApiModelProperty("项目类型名称")
    private String projectTypeName;

    @ApiModelProperty("创建人id")
    private String createUserId;

    @ApiModelProperty("创建人姓名")
    private String createUserName;

    @ApiModelProperty("项目名称")
    private String projectTitle;

    @ApiModelProperty("项目简介")
    private String projectContent;

    @ApiModelProperty("项目金额min")
    private BigDecimal projectMinPrice;

    @ApiModelProperty("项目金额max")
    private BigDecimal projectMaxPrice;

    @ApiModelProperty("项目状态： 1 初始化 2进行中 3 结束")
    private Integer projectStatus;

    @ApiModelProperty("审核状态 1待审核 2 已通过 3 已驳回")
    private Integer examineStatus;

    @ApiModelProperty("项目需要人数")
    private Integer projectNeedPeople;

    @ApiModelProperty("是否直接雇佣：1 是 2 否")
    private Integer isDirectHire;

    @ApiModelProperty("项目是否托管： 1 托管 2 不托管，3 已付定金")
    private Integer isTrusteeship;

    @ApiModelProperty("项目托管金额")
    private BigDecimal trusteeshipAmount;

    @ApiModelProperty("排序")
    private Integer rankField;

    @ApiModelProperty("项目创建时间")
    private Date projectCreateDate;

    @ApiModelProperty("项目截止时间")
    private Date projectEndDate;

    @ApiModelProperty("排序字段")
    private String sortField;

    @ApiModelProperty("排序类型：1升序 2 降序")
    private String sort;

    @ApiModelProperty("按项目创建时间查询：开始时间")
    private String projectCreateDateBegin;

    @ApiModelProperty("按项目创建时间查询：结束时间")
    private String projectCreateDateEnd;

    @ApiModelProperty("按项目截止时间查询：开始时间")
    private String projectEndDateBegin;

    @ApiModelProperty("按项目截止时间查询：结束时间")
    private String projectEndDateEnd;

    @ApiModelProperty("按项目时间查询：开始时间")
    private String projectDateBegin;

    @ApiModelProperty("按项目时间查询：开始时间")
    private String projectDateEnd;
}
