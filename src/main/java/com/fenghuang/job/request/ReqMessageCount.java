package com.fenghuang.job.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2019/12/25 18:02
 * @Email: 15290810931@163.com
 */
@Data
public class ReqMessageCount implements Serializable{

    private Integer id;

    @ApiModelProperty("用户id")
    private Integer userId;

    @ApiModelProperty("用户手机号")
    private String mobile;

    @ApiModelProperty("短信类型: 1 注册 2 找回密码 3.登录")
    private Integer messageType;

    @ApiModelProperty("短信发送内容")
    private String sendContent;

    @ApiModelProperty("短信发送日期")
    private Date sendDate;

    @ApiModelProperty("短信发送ip")
    private String sendIp;

    @ApiModelProperty("创建人")
    private String founder;

    @ApiModelProperty("修改人")
    private String modifier;

    @ApiModelProperty("创建时间")
    private Date createDate;

    @ApiModelProperty("修改时间")
    private Date updateDate;
}
