package com.fenghuang.job.controller;

import com.fenghuang.job.entity.Result;
import com.fenghuang.job.request.ReqMessageCount;
import com.fenghuang.job.request.ReqMessageCountQuery;
import com.fenghuang.job.request.ReqMessageCountQuery2;
import com.fenghuang.job.service.MessageCountService;
import com.fenghuang.job.utils.BusinessUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2019/12/25 17:49
 * @Email: 15290810931@163.com
 */
@Api(value = "短信发送记录统计相关接口",description = "短信发送记录统计相关接口")
@RestController
public class MessageCountController {

    @Autowired
    MessageCountService messageCountService;

    @ApiOperation(value = "插入短信统计表")
    @PostMapping("/insertMessageCount")
    public Result insertMessageCount(@RequestBody ReqMessageCount reqMessageCount, HttpServletRequest request){
        String ip = BusinessUtils.getIpAddress(request);
        reqMessageCount.setSendIp(ip);
        return Result.success(messageCountService.insertMessageCount(reqMessageCount));
    }

    @ApiOperation(value = "根据条件进行查询短信统计且分页")
    @PostMapping("/findMessageCountPage")
    public Result findMessageCountPage(@RequestBody ReqMessageCountQuery messageCountQuery){
        return Result.success(messageCountService.findMessageCountPage(messageCountQuery));
    }

    @ApiOperation(value = "根据条件统计一个人一小时发送短信的条数")
    @PostMapping("/findMessageCount")
    public Result findMessageCount (@RequestBody ReqMessageCountQuery2 messageCountQuery2) throws ParseException {
        return Result.success(messageCountService.findMessageCount(messageCountQuery2));
    }

}
