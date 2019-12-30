package com.fenghuang.job.controller;

import com.fenghuang.job.entity.Result;
import com.fenghuang.job.request.ReqCashWithdrawal;
import com.fenghuang.job.service.CashWithdrawalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2019/12/20 10:28
 * @Email: 15290810931@163.com
 */
@Api(value = "提现订单信息表相关接口",description = "提现订单信息表相关接口")
@RestController
public class CashWithdrawalController {

    @Autowired
    CashWithdrawalService cashWithdrawalService;

    @ApiOperation(value = "申请提现接口")
    @PostMapping("/applyCashWithdrawal")
    public Result applyCashWithdrawal(@RequestBody ReqCashWithdrawal reqCashWithdrawal){
        return Result.success(cashWithdrawalService.insertCashWithdrawal(reqCashWithdrawal));
    }

    @ApiOperation(value = "根据条件查询且分页提现订单申请信息表")
    @PostMapping("/findCashWithdrawalPage")
    public Result findCashWithdrawalPage(@RequestBody ReqCashWithdrawal reqCashWithdrawal){
        return Result.success(cashWithdrawalService.findCashWithdrawalPage(reqCashWithdrawal));
    }

    @ApiOperation(value = "根据订单id更新提现订单信息表状态")
    @PostMapping("/modifyCashWithdrawalStatus")
    public Result modifyCashWithdrawalStatus(@RequestBody ReqCashWithdrawal reqCashWithdrawal){
        return Result.success(cashWithdrawalService.modifyCashWithdrawalStatus(reqCashWithdrawal));
    }

    @ApiOperation( value = "根据Id 查询提现订单相关信息 ")
    @GetMapping("/findCashWithdrawalById")
    public Result findCashWithdrawalById(@RequestParam("id") Integer id){
        return Result.success( cashWithdrawalService.findCashWithdrawalById(id) );
    }

}
