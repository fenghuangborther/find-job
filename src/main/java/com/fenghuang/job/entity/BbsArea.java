package com.fenghuang.job.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "地址信息")
public class BbsArea implements Serializable{
    private Integer areaId;

    private String title;

    private Integer pid;

    private Integer sort;

}