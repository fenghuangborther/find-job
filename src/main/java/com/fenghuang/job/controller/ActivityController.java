package com.fenghuang.job.controller;

import com.fenghuang.job.entity.Result;
import com.fenghuang.job.request.ReqActivity;
import com.fenghuang.job.request.ReqActivityQuery;
import com.fenghuang.job.request.ReqActivityUpdate;
import com.fenghuang.job.service.ActivityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2019/12/25 14:16
 * @Email: 15290810931@163.com
 */
@Api(value = "活动信息表相关接口",description = "活动信息表相关接口")
@RestController
public class ActivityController {

    @Autowired
    ActivityService activityService;

    @ApiOperation(value = "新增活动")
    @PostMapping(value = "/insertActivity")
    public Result insertActivity(@RequestBody ReqActivity reqActivity){
        return Result.success(activityService.insertActivity(reqActivity));
    }

    @ApiOperation(value = "根据ID修改活动相关信息")
    @PostMapping("/modifyActivity")
    public Result modifyActivity(@RequestBody ReqActivityUpdate reqActivityUpdate){
        return Result.success(activityService.modifyActivity(reqActivityUpdate));
    }

    @ApiOperation(value = "根据id 查询活动详情")
    @GetMapping("/findActivityById")
    public Result findActivityById(@RequestParam("id") Integer id){
        return Result.success(activityService.findActivityById(id));
    }

    @ApiOperation(value = "根据条件查询活动且分页")
    @PostMapping("/findActivityPage")
    public Result findActivityPage(@RequestBody ReqActivityQuery reqActivityQuery){
        return Result.success(activityService.findActivityPage(reqActivityQuery));
    }

}
