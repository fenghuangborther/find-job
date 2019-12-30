package com.fenghuang.job.dao.master;

import com.fenghuang.job.entity.Project;
import com.fenghuang.job.request.ReqProject;

import java.util.List;

public interface ProjectMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Project record);

    int insertSelective(Project record);

    Project selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Project record);

    int updateByPrimaryKey(Project record);

    List<Project> findProjectPage(ReqProject reqProject);

    Project findProjectParams(ReqProject reqProject);

    List<Project> findProject(ReqProject reqProject);
}