package com.fenghuang.job.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2019/12/16 15:34
 * @Email: 15290810931@163.com
 */
@Api(value = "首页相关接口",description = "首页相关接口")
@RestController
public class IndexController {

    @ApiOperation(value = "这是首页")
    @GetMapping("/index")
    public String index(){
        return "index";
    }
}
