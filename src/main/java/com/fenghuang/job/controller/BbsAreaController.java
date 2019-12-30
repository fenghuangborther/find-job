package com.fenghuang.job.controller;

import com.fenghuang.job.entity.Result;
import com.fenghuang.job.request.ReqBbsArea;
import com.fenghuang.job.service.BbsAreaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2019/12/30 14:46
 * @Email: 15290810931@163.com
 */
@Api(value = "省市区地址相关接口",description = "省市区地址相关接口")

@RestController
public class BbsAreaController {

    @Autowired
    BbsAreaService bbsAreaService;

    @ApiOperation("根据条件查询地址相关信息")
    @PostMapping("/findBbsArea")
    public Result findBbsArea(@RequestBody ReqBbsArea reqBbsArea){
        return Result.success(bbsAreaService.findBbsArea(reqBbsArea));
    }
}
