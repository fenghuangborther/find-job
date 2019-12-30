package com.fenghuang.job.controller;

import com.fenghuang.job.entity.Result;
import com.fenghuang.job.request.ReqRecharge;
import com.fenghuang.job.service.RechargeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2019/12/30 15:23
 * @Email: 15290810931@163.com
 */
@RestController
@Api(value = "用户充值信息表相关接口",description = "用户充值信息表相关接口")
public class RechargeController  {

    @Autowired
    RechargeService rechargeService;

    @PostMapping("/insertRecharge")
    @ApiOperation(value = "新增用户充值订单信息")
    public Result insertRecharge(@RequestBody ReqRecharge reqRecharge){
        return Result.success(rechargeService.insertRecharge(reqRecharge));
    }

    @PostMapping("/findRechargePage")
    @ApiOperation(value = "根据条件查询用户充值订单信息带分页")
    public Result findRechargePage(@RequestBody ReqRecharge reqRecharge){
        return Result.success(rechargeService.findRechargePage(reqRecharge));
    }
}
