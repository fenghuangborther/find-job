package com.fenghuang.job.controller;

import com.fenghuang.job.entity.Result;
import com.fenghuang.job.request.ReqBanner;
import com.fenghuang.job.request.ReqBannerStatus;
import com.fenghuang.job.service.BannerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2019/12/23 17:48
 * @Email: 15290810931@163.com
 */
@Api(value = "轮播图信息表相关接口",description = "轮播图信息表相关接口")
@RestController
public class BannerController {

    @Autowired
    BannerService bannerService;

    @ApiOperation(value = "添加轮播图banner")
    @PostMapping("/insertBanner")
    public Result insertBanner(@RequestBody ReqBanner reqBanner){
        return Result.success(bannerService.insertBanner(reqBanner));
    }

    @ApiOperation(value = "根据条件查找banner")
    @PostMapping("/findBanner")
    public Result findBanner(@RequestBody ReqBanner reqBanner){
        return Result.success(bannerService.findBanner(reqBanner));
    }
    @ApiOperation(value = "根据ID更新轮播图状态")
    @PostMapping("/modifyBannerStatus")
    public Result modifyBannerStatus(@RequestBody ReqBannerStatus reqBannerStatus){
        return Result.success(bannerService.modifyBannerStatus(reqBannerStatus));
    }

    @ApiOperation(value = "根据id 查询banner 详情")
    @GetMapping("/findBannerById")
    public Result findBannerById(@RequestParam("id") Integer id){
        return Result.success(bannerService.findBannerById(id));
    }
}
