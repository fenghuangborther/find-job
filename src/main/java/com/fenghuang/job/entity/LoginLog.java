package com.fenghuang.job.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@ApiModel(value = "登录日志表")
public class LoginLog implements Serializable{
    private Integer id;

    private Integer userId;

    private Integer loginStatus;

    private Date loginDate;

    private String failRemark;

    private String loginIp;

    private String founder;

    private String modifier;

    private Date createDate;

    private Date updateDate;

}