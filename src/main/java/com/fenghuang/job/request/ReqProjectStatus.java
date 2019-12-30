package com.fenghuang.job.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2019/12/23 17:23
 * @Email: 15290810931@163.com
 */
@Data
public class ReqProjectStatus implements Serializable {

    private Integer id;

    @ApiModelProperty("项目状态： 1 初始化 2进行中 3 结束")
    private Integer projectStatus;

    @ApiModelProperty("审核状态 1待审核 2 已通过 3 已驳回")
    private Integer examineStatus;

}
