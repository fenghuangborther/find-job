package com.fenghuang.job.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@ApiModel(value = "项目信息表")
public class Project implements Serializable{
    private Integer id;

    private Integer projectTypeId;

    private String projectTypeName;

    private String createUserId;

    private String createUserName;

    private String projectTitle;

    private String projectContent;

    private Long projectMinPrice;

    private Long projectMaxPrice;

    private Integer projectStatus;

    private Integer examineStatus;

    private Integer projectNeedPeople;

    private Integer isDirectHire;

    private Integer isTrusteeship;

    private Long trusteeshipAmount;

    private Integer rankField;

    private Date projectCreateDate;

    private Date projectEndDate;

    private String founder;

    private String modifier;

    private Date createDate;

    private Date updateDate;

}