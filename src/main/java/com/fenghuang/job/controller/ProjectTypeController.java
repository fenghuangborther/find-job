package com.fenghuang.job.controller;

import com.fenghuang.job.entity.Result;
import com.fenghuang.job.request.ReqProjectType;
import com.fenghuang.job.service.ProjectTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2019/12/20 23:23
 * @Version: 1.0
 * @Email: 15290810931@163.com
 */
@Api(value = "项目类型信息表相关接口",description = "项目类型信息表相关接口")
@RestController
public class ProjectTypeController {

    @Autowired
    ProjectTypeService projectTypeService;

    @ApiOperation( value = "新增项目类型")
    @PostMapping("/insertProjectType")
    public Result insertProjectType(@RequestBody ReqProjectType reqProjectType){
        return Result.success( projectTypeService.insertProjectType(reqProjectType) );
    }
    @ApiOperation( value = "更新项目类型字段")
    @PostMapping("/modifyProjectType")
    public Result modifyProjectType(@RequestBody ReqProjectType reqProjectType){
        return Result.success( projectTypeService.modifyProjectType(reqProjectType) );
    }
    @ApiOperation( value = "根据id 查询项目类型相关信息")
    @GetMapping("/findProjectTypeById")
    public Result findProjectTypeById(@RequestParam("id") Integer id){
        return Result.success( projectTypeService.findProjectTypeById(id) );
    }
    @ApiOperation( value = "根据条件查询项目类型 且分页")
    @PostMapping("/findProjectTypePage")
    public Result findProjectTypePage(@RequestBody ReqProjectType reqProjectType){
        return Result.success( projectTypeService.findProjectTypePage(reqProjectType) );
    }



}
