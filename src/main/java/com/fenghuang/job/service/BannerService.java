package com.fenghuang.job.service;

import com.fenghuang.job.request.ReqBanner;
import com.fenghuang.job.request.ReqBannerStatus;
import com.fenghuang.job.view.BannerView;

import java.util.List;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2019/12/23 17:50
 * @Email: 15290810931@163.com
 */
public interface BannerService {

    /**
     * 添加轮播图banner
     * @param reqBanner
     * @return
     */
    int insertBanner(ReqBanner reqBanner);

    /**
     * 根据条件查找banner
     * @param reqBanner
     * @return
     */
    List<BannerView> findBanner(ReqBanner reqBanner);

    /**
     * 根据ID更新轮播图状态
      * @param reqBannerStatus
     * @return
     */
    int modifyBannerStatus(ReqBannerStatus reqBannerStatus);

    /**
     * 根据id 查询banner 详情
     * @param id
     * @return
     */
    BannerView findBannerById(Integer id);
}
