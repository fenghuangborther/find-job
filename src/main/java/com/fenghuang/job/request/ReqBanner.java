package com.fenghuang.job.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2019/12/23 17:51
 * @Email: 15290810931@163.com
 */
@Data
public class ReqBanner implements Serializable {

    private Integer id;

    @ApiModelProperty("活动ID")
    private Integer activityId;

    @ApiModelProperty("活动图片地址url")
    private String activityImg;

    @ApiModelProperty("排序")
    private Integer rankField;

    @ApiModelProperty("轮播图图片状态：1 正常 2失效")
    private Integer bannerImgStatus;
}
