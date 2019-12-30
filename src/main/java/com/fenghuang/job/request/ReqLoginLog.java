package com.fenghuang.job.request;

import com.fenghuang.job.utils.PageHelps;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2019/12/19 16:03
 * @Email: 15290810931@163.com
 */
@Data
public class ReqLoginLog extends PageHelps implements Serializable{

    private Integer id;

    @ApiModelProperty("用户ID")
    private Integer userId;

    @ApiModelProperty("登陆状态：1成功 2 失败")
    private Integer loginStatus;

    @ApiModelProperty("登陆日期")
    private Date loginDate;

    @ApiModelProperty("登录备注")
    private String failRemark;

    @ApiModelProperty("登陆ip")
    private String loginIp;

    @ApiModelProperty("查询登陆开始日期")
    private String loginDateBegin;

    @ApiModelProperty("查询登陆结束日期")
    private String loginDateEnd;
}
