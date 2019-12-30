package com.fenghuang.job.controller;

import com.fenghuang.job.entity.Result;
import com.fenghuang.job.request.ReqLoginLog;
import com.fenghuang.job.service.LoginLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2019/12/19 16:00
 * @Email: 15290810931@163.com
 */
@Api(value = "登录日志相关接口",description = "登录日志相关接口")
@RestController
public class LoginLogController {

    @Autowired
    LoginLogService loginLogService;

    @ApiOperation(value = "根据userId|状态| 日期|ip 条件进行查询登录日志")
    @PostMapping("/findLoginLog")
    public Result findLoginLog(@RequestBody ReqLoginLog reqLoginLog){
      return Result.success(loginLogService.findLogin(reqLoginLog));
    }

    @ApiOperation(value = "根据条件查询日志信息且分页")
    @PostMapping("/findLoginLogPage")
    public Result findLoginLogPage(@RequestBody ReqLoginLog reqLoginLog){
       return Result.success(loginLogService.findLoginLogPage(reqLoginLog));
    }

}
