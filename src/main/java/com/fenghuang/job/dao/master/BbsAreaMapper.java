package com.fenghuang.job.dao.master;

import com.fenghuang.job.entity.BbsArea;
import com.fenghuang.job.request.ReqBbsArea;

import java.util.List;

public interface BbsAreaMapper {
    int deleteByPrimaryKey(Integer areaId);

    int insert(BbsArea record);

    int insertSelective(BbsArea record);

    BbsArea selectByPrimaryKey(Integer areaId);

    int updateByPrimaryKeySelective(BbsArea record);

    int updateByPrimaryKey(BbsArea record);

    List<BbsArea> findBbsArea(ReqBbsArea reqBbsArea);
}