package com.fenghuang.job.service.impl;

import com.alibaba.fastjson.JSON;
import com.fenghuang.job.dao.master.BannerMapper;
import com.fenghuang.job.entity.Banner;
import com.fenghuang.job.enums.BannerImgStatusEnum;
import com.fenghuang.job.enums.BusinessEnum;
import com.fenghuang.job.exception.BusinessException;
import com.fenghuang.job.request.ReqBanner;
import com.fenghuang.job.request.ReqBannerStatus;
import com.fenghuang.job.service.BannerService;
import com.fenghuang.job.view.BannerView;
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
 * @Date: 2019/12/23 18:00
 * @Email: 15290810931@163.com
 */
@Slf4j
@Service
public class BannerServiceImpl implements BannerService {

    @Autowired
    BannerMapper bannerMapper;
    /**
     * 添加轮播图banner
     *
     * @param reqBanner
     * @return
     */
    @Override
    public int insertBanner(ReqBanner reqBanner) {
        log.info("添加轮播图banner 请求参数：{}", JSON.toJSONString(reqBanner));
        Banner banner = new Banner();
        BeanCopier beanCopier = BeanCopier.create( ReqBanner.class, Banner.class,false);
        beanCopier.copy(reqBanner,banner,null);
        banner.setBannerImgStatus( BannerImgStatusEnum.NORMAL.getCode());
        banner.setCreateDate(new Date());
        banner.setUpdateDate(new Date());
        return bannerMapper.insertSelective(banner);
    }

    /**
     * 根据条件查找banner
     *
     * @param reqBanner
     * @return
     */
    @Override
    public List<BannerView> findBanner(ReqBanner reqBanner) {
        log.info("根据条件查找banner 请求参数：{}",JSON.toJSONString(reqBanner));
        reqBanner.setBannerImgStatus( BannerImgStatusEnum.NORMAL.getCode() );
        List<Banner> queryBanner = bannerMapper.findBanner(reqBanner);
        if (CollectionUtils.isEmpty(queryBanner)){
            return new ArrayList<>(16);
        }
        List<BannerView> views = new ArrayList<>();
        queryBanner.stream().forEach(banner -> {
            BannerView view = new BannerView();
            BeanCopier beanCopier = BeanCopier.create( Banner.class, BannerView.class, false);
            beanCopier.copy(banner,view,null);
            views.add(view);
        });
        log.info("根据条件查找banner 返回结果：{}",JSON.toJSONString(views));
        return views;
    }

    /**
     * 根据ID更新轮播图状态
     *
     * @param reqBannerStatus
     * @return
     */
    @Override
    public int modifyBannerStatus(ReqBannerStatus reqBannerStatus) {
        log.info("根据ID更新轮播图状态 请求参数：{}",JSON.toJSONString(reqBannerStatus));
        if (StringUtils.isEmpty(reqBannerStatus.getId())){
            throw new BusinessException( BusinessEnum.MISSING_PARAMETERS.getCode(), BusinessEnum.MISSING_PARAMETERS.getMsg());
        }
        Banner banner = new Banner();
        BeanCopier beanCopier = BeanCopier.create( ReqBannerStatus.class, Banner.class,false);
        beanCopier.copy(reqBannerStatus,banner,null);
        banner.setUpdateDate(new Date());
        return bannerMapper.updateByPrimaryKeySelective(banner);
    }

    /**
     * 根据id 查询banner 详情
     *
     * @param id
     * @return
     */
    @Override
    public BannerView findBannerById(Integer id) {
        log.info("根据id 查询banner 详情 请求参数：{}",id);
        Banner banner = new Banner();
        banner.setId(id);
        Banner queryBanner = bannerMapper.selectByPrimaryKey(id);
        if (queryBanner == null){
            return null;
        }
        BannerView view = new BannerView();
        BeanCopier beanCopier = BeanCopier.create( Banner.class, BannerView.class,false);
        beanCopier.copy(queryBanner,view,null);
        log.info("根据id 查询banner 详情返回结果：{}",JSON.toJSONString(view));
        return view;
    }
}
