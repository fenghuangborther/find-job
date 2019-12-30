package com.fenghuang.job.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2019/12/25 18:02
 * @Email: 15290810931@163.com
 */
@Data
public class ReqMessageCountQuery2 implements Serializable{

    @ApiModelProperty("用户id")
    private Integer userId;

    @ApiModelProperty("用户手机号")
    private String mobile;

    @ApiModelProperty("短信类型：1 注册 2 找回密码 3.登录")
    private Integer messageType;

    @ApiModelProperty("短信当前发送时间")
    private String currentSendDate;

    @ApiModelProperty("短信半小时发送时间")
    private String oneHourAgoDate;

    @ApiModelProperty("短信发送IP")
    private String sendIp;

}
