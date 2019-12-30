package com.fenghuang.job.service;

import com.fenghuang.job.request.ReqProject;
import com.fenghuang.job.request.ReqProjectStatus;
import com.fenghuang.job.view.ProjectView;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2019/12/22 12:11
 * @Version: 1.0
 * @Email: 15290810931@163.com
 */
public interface ProjectService {
    /**
     * 创建项目
     * @param reqProject
     * @return
     */
    int insertProject(ReqProject reqProject);

    /**
     * 根据id更新项目相关字段
      * @param reqProject
     * @return
     */
    int modifyProject(ReqProject reqProject);

    /**
     *根据id更新项目状态
     * @param reqProjectStatus
     * @return
     */
    int modifyProjectStatus(ReqProjectStatus reqProjectStatus);

    /**
     *根据条件查询项目信息
     * @param reqProject
     * @return
     */
    List<ProjectView> findProject(ReqProject reqProject);

    /**
     *根据条件进行查询项目相关信息且分页
      * @param reqProject
     * @return
     */
    PageInfo<ProjectView> findProjectPage(ReqProject reqProject);
}
