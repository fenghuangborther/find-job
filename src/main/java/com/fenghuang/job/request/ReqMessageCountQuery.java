package com.fenghuang.job.request;

import com.fenghuang.job.utils.PageHelps;
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
public class ReqMessageCountQuery extends PageHelps implements Serializable{

    private Integer id;

    @ApiModelProperty("用户id")
    private Integer userId;

    @ApiModelProperty("用户手机号")
    private String mobile;

    @ApiModelProperty("发送内容")
    private String sendContent;

    @ApiModelProperty("发送时间")
    private Date sendDate;

    @ApiModelProperty("发送ip")
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
