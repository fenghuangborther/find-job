package com.fenghuang.job.dao.master;

import com.fenghuang.job.entity.ProjectType;
import com.fenghuang.job.request.ReqProjectType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProjectTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProjectType record);

    int insertSelective(ProjectType record);

    ProjectType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProjectType record);

    int updateByPrimaryKey(ProjectType record);

    List<ProjectType> findProjectTypePage(ReqProjectType reqProjectType);

    List<ProjectType> findProjectType(ReqProjectType reqProjectType);
}