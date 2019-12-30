package com.fenghuang.job.service.impl;

import com.alibaba.fastjson.JSON;
import com.fenghuang.job.dao.master.ProjectMapper;
import com.fenghuang.job.entity.Project;
import com.fenghuang.job.enums.BusinessEnum;
import com.fenghuang.job.enums.ExamineStatusEnum;
import com.fenghuang.job.enums.ProjectStatusEnum;
import com.fenghuang.job.enums.SortEnum;
import com.fenghuang.job.exception.BusinessException;
import com.fenghuang.job.request.ReqProject;
import com.fenghuang.job.request.ReqProjectStatus;
import com.fenghuang.job.service.ProjectService;
import com.fenghuang.job.view.ProjectView;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2019/12/22 12:27
 * @Version: 1.0
 * @Email: 15290810931@163.com
 */
@Service
@Slf4j
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    ProjectMapper projectMapper;
    /**
     * 创建项目
     *
     * @param reqProject
     * @return
     */
    @Override
    public int insertProject(ReqProject reqProject) {
        log.info( "创建项目 请求参数：{}", JSON.toJSONString(reqProject) );
        Project projects = projectMapper.findProjectParams(reqProject);
        if (projects != null){
            throw new BusinessException( BusinessEnum.RECORD_ALREADY_EXISTS.getCode(), BusinessEnum.RECORD_ALREADY_EXISTS.getMsg());
        }
        Project project = new Project();
        BeanCopier beanCopier = BeanCopier.create( ReqProject.class, Project.class,false  );
        beanCopier.copy( reqProject,project,null );
        project.setProjectStatus( ProjectStatusEnum.INIT.getCode());
        project.setExamineStatus( ExamineStatusEnum.AUDITED.getCode());
        project.setProjectCreateDate(new Date());
        project.setCreateDate(new Date());
        project.setUpdateDate(new Date());
        project.setFounder(reqProject.getCreateUserId());
        project.setModifier(reqProject.getCreateUserId());
        return projectMapper.insertSelective( project );
    }

    /**
     * 根据id更新项目相关字段
     *
     * @param reqProject
     * @return
     */
    @Override
    public int modifyProject(ReqProject reqProject) {
        log.info( "根据id更新项目相关字段 请求参数：{}",JSON.toJSONString( reqProject ) );
        if (StringUtils.isEmpty(reqProject.getId())){
            throw new BusinessException( BusinessEnum.MISSING_PARAMETERS.getCode(), BusinessEnum.MISSING_PARAMETERS.getMsg());
        }
        Project project = new Project();
        BeanCopier beanCopier = BeanCopier.create( ReqProject.class, Project.class,false );
        beanCopier.copy( reqProject,project,null );
        return projectMapper.updateByPrimaryKeySelective( project );
    }

    /**
     * 根据id更新项目状态
     *
     * @param reqProjectStatus
     * @return
     */
    @Override
    public int modifyProjectStatus(ReqProjectStatus reqProjectStatus) {
        log.info( "根据id更新项目状态 请求参数：{}",JSON.toJSONString( reqProjectStatus ) );
        if (StringUtils.isEmpty(reqProjectStatus.getId())){
            throw new BusinessException( BusinessEnum.MISSING_PARAMETERS.getCode(), BusinessEnum.MISSING_PARAMETERS.getMsg());
        }
        Project project = projectMapper.selectByPrimaryKey(reqProjectStatus.getId());
        if (project == null){
            throw new BusinessException( BusinessEnum.RECORD_NOT_EXIST.getCode(), BusinessEnum.RECORD_NOT_EXIST.getMsg());
        }
        Project projectParam = new Project();
        //如果审核状态没有则只修改项目状态 ; 否则就是管理员在审核，审核应该修改相应的项目状态
        projectParam.setId(reqProjectStatus.getId());
        if (StringUtils.isEmpty(reqProjectStatus.getExamineStatus())){
            projectParam.setProjectStatus(reqProjectStatus.getProjectStatus());
        }else{
            if (reqProjectStatus.getExamineStatus().equals( ExamineStatusEnum.PASSED.getCode())){
                projectParam.setProjectStatus( ProjectStatusEnum.CONDUCTING.getCode());
                projectParam.setExamineStatus( ExamineStatusEnum.PASSED.getCode());
            }else{
                projectParam.setExamineStatus( ExamineStatusEnum.REJECTED.getCode());
            }
        }
        return projectMapper.updateByPrimaryKeySelective(projectParam);
    }

    /**
     * 根据条件查询项目信息
     *
     * @param reqProject
     * @return
     */
    @Override
    public List<ProjectView> findProject(ReqProject reqProject) {
        log.info( "根据条件查询项目信息 请求参数：{}",JSON.toJSONString( reqProject ) );
        convertSort(reqProject);
        List<Project> queryProject  =  projectMapper.findProject(reqProject);
        if (CollectionUtils.isEmpty( queryProject )){
            return new ArrayList<>(  );
        }
        List<ProjectView>  views = new ArrayList<>(  );
        convertView( queryProject, views );
        log.info( "根据条件查询项目信息 返回结果：{}",JSON.toJSONString( views ) );
        return views;
    }

    /**
     * 根据条件进行查询项目相关信息且分页
     *
     * @param reqProject
     * @return
     */
    @Override
    public PageInfo<ProjectView> findProjectPage(ReqProject reqProject) {
        log.info( "根据条件进行查询项目相关信息且分页 请求参数：{}",JSON.toJSONString( reqProject ) );
        PageInfo<ProjectView> pageInfo = null;
        try {
            Page<?> page = PageHelper.startPage(reqProject.getPageNum(),reqProject.getPageSize());
            List<Project> queryProject  =  projectMapper.findProjectPage(reqProject);
            if (CollectionUtils.isEmpty( queryProject )){
                pageInfo = new PageInfo<>(new ArrayList<>());
            }else{
                List<ProjectView>  views = new ArrayList<>(  );
                convertView( queryProject, views );
                pageInfo = new PageInfo<>(views);
            }
            log.info("总共有:{}",page.getTotal()+"条数据,实际返回{}:",page.size()+"两条数据!");
        }catch (Exception e){
            log.info( "根据条件进行查询项目相关信息且分页 查询异常：{}",e.getMessage() );
        }
        return pageInfo;
    }

    private void convertSort(ReqProject reqProject) {
        if (StringUtils.isEmpty( reqProject.getSortField() )){
            reqProject.setSortField( "project_create_date" );
            reqProject.setSort( SortEnum.DESC.getMsg() );
        }else {
            if (StringUtils.isEmpty( reqProject.getSort() )){
                reqProject.setSort( SortEnum.DESC.getMsg() );
            }else{
                if (reqProject.getSort() .equals(  SortEnum.ASC.getCode().toString()) ){
                    reqProject.setSort( SortEnum.ASC.getMsg() );
                }else if (reqProject.getSort().equals( SortEnum.DESC.getCode().toString())){
                    reqProject.setSort( SortEnum.DESC.getMsg() );
                }
            }
        }
    }

    private void convertView(List<Project> queryProject, List<ProjectView> views) {
        queryProject.stream().forEach( project -> {
            ProjectView view = new ProjectView();
            BeanCopier beanCopier = BeanCopier.create( Project.class, ProjectView.class, false );
            beanCopier.copy( project, view, null );
            views.add( view );
        } );
    }
}
