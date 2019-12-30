package com.fenghuang.job.view;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2019/12/19 16:05
 * @Email: 15290810931@163.com
 */
@Data
@ApiModel(value = "登录日志表")
public class LoginLogView implements Serializable{

    @ApiModelProperty("id")
    private Integer id;

    @ApiModelProperty("用户id")
    private Integer userId;

    @ApiModelProperty("登陆状态：1成功 2 失败")
    private Integer loginStatus;

    @ApiModelProperty("登陆日期")
    private Date loginDate;

    @ApiModelProperty("失败原因")
    private String failRemark;

    @ApiModelProperty("登陆ip")
    private String loginIp;

    @ApiModelProperty("创建人")
    private String founder;

    @ApiModelProperty("修改人")
    private String modifier;

    @ApiModelProperty("创建时间")
    private Date createDate;

    @ApiModelProperty("修改时间")
    private Date updateDate;
}
