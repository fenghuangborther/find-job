package com.fenghuang.job.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2019/12/30 14:39
 * @Email: 15290810931@163.com
 */
@Data
@ApiModel(value = "地址信息")
public class ReqBbsArea implements Serializable{

    @ApiModelProperty("id")
    private Integer areaId;

    @ApiModelProperty("城市名称")
    private String title;

    @ApiModelProperty("父级id")
    private Integer pid;

    @ApiModelProperty("排序")
    private Integer sort;
}
