package com.fenghuang.job.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@ApiModel(value = "短信统计信息表")
public class MessageCount implements Serializable{
    private Integer id;

    private Integer userId;

    private String mobile;

    private Integer messageType;

    private String sendContent;

    private Date sendDate;

    private String sendIp;

    private String founder;

    private String modifier;

    private Date createDate;

    private Date updateDate;

}