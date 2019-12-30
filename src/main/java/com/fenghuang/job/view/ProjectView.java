package com.fenghuang.job.view;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2019/12/22 12:25
 * @Version: 1.0
 * @Email: 15290810931@163.com
 */
@Data
@ApiModel(value = "项目信息表")
public class ProjectView implements Serializable {

    @ApiModelProperty("id")
    private Integer id;

    @ApiModelProperty("project_type_id")
    private Integer projectTypeId;

    @ApiModelProperty("project_type_name")
    private String projectTypeName;

    @ApiModelProperty("创建人ID")
    private String createUserId;

    @ApiModelProperty("创建人姓名")
    private String createUserName;

    @ApiModelProperty("项目标题")
    private String projectTitle;

    @ApiModelProperty("项目简述")
    private String projectContent;

    @ApiModelProperty("项目价格min")
    private Long projectMinPrice;

    @ApiModelProperty("项目价格max")
    private Long projectMaxPrice;

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
    private Long trusteeshipAmount;

    @ApiModelProperty("排名次")
    private Integer rankField;

    @ApiModelProperty("项目创建时间")
    private Date projectCreateDate;

    @ApiModelProperty("项目截至时间")
    private Date projectEndDate;

    @ApiModelProperty("创建人")
    private String founder;

    @ApiModelProperty("修改人")
    private String modifier;

    @ApiModelProperty("创建时间")
    private Date createDate;

    @ApiModelProperty("修改时间")
    private Date updateDate;
}
