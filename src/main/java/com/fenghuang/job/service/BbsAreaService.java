package com.fenghuang.job.service;

import com.fenghuang.job.request.ReqBbsArea;
import com.fenghuang.job.view.BbsAreaView;

import java.util.List;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2019/12/30 14:46
 * @Email: 15290810931@163.com
 */
public interface BbsAreaService {
    /**
     * 根据条件查询地址相关信息
     * @param reqBbsArea
     * @return
     */
    List<BbsAreaView> findBbsArea(ReqBbsArea reqBbsArea);
}
