package com.fenghuang.job.service.impl;

import com.alibaba.fastjson.JSON;
import com.fenghuang.job.dao.master.ActivityMapper;
import com.fenghuang.job.entity.Activity;
import com.fenghuang.job.enums.ActivityStatusEnum;
import com.fenghuang.job.enums.BusinessEnum;
import com.fenghuang.job.enums.ExamineStatusEnum;
import com.fenghuang.job.exception.BusinessException;
import com.fenghuang.job.request.ReqActivity;
import com.fenghuang.job.request.ReqActivityQuery;
import com.fenghuang.job.request.ReqActivityUpdate;
import com.fenghuang.job.service.ActivityService;
import com.fenghuang.job.view.ActivityView;
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
 * @Date: 2019/12/25 14:30
 * @Email: 15290810931@163.com
 */
@Service
@Slf4j
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    ActivityMapper activityMapper;
    /**
     * 新增活动
     *
     * @param reqActivity
     * @return
     */
    @Override
    public int insertActivity(ReqActivity reqActivity) {
        log.info("新增活动请求参数：{}", JSON.toJSONString(reqActivity));
        //新增活动时：相同名字且状态为待审核| 进行中的活动不能创建
        Activity queryActivity = activityMapper.queryActivityByTitle(reqActivity.getActivityTitle());
        if (queryActivity != null){
            throw new BusinessException( BusinessEnum.RECORD_ALREADY_EXISTS.getCode(), BusinessEnum.RECORD_ALREADY_EXISTS.getMsg());
        }
        Activity activity = new Activity();
        BeanCopier beanCopier = BeanCopier.create(ReqActivity.class, Activity.class,false);
        beanCopier.copy(reqActivity,activity,null);
        activity.setActivityStatus( ActivityStatusEnum.INIT.getCode());
        activity.setExamineStatus( ExamineStatusEnum.AUDITED.getCode());
        activity.setCreateDate(new Date());
        activity.setUpdateDate(new Date());
        return activityMapper.insertSelective(activity);
    }

    /**
     * 根据ID修改活动相关信息
     *
     * @param reqActivityUpdate
     * @return
     */
    @Override
    public int modifyActivity(ReqActivityUpdate reqActivityUpdate) {
        log.info("根据ID修改活动相关信息 请求参数：{}",JSON.toJSONString(reqActivityUpdate));
        if (StringUtils.isEmpty(reqActivityUpdate.getId())){
            throw new BusinessException( BusinessEnum.MISSING_PARAMETERS.getCode(), BusinessEnum.MISSING_PARAMETERS.getMsg());
        }
        Activity activity = new Activity();
        BeanCopier beanCopier = BeanCopier.create(ReqActivity.class, Activity.class,false);
        beanCopier.copy(reqActivityUpdate,activity,null);
        activity.setUpdateDate(new Date());
        return activityMapper.updateByPrimaryKeySelective(activity);
    }

    /**
     * 根据id 查询活动详情
     *
     * @param id
     * @return
     */
    @Override
    public ActivityView findActivityById(Integer id) {
        log.info("根据id 查询活动详情 请求参数：{}",id);
        Activity activity = activityMapper.selectByPrimaryKey(id);
        if (activity == null){
            return null;
        }
        ActivityView view = new ActivityView();
        BeanCopier copier = BeanCopier.create( Activity.class, ActivityView.class,false);
        copier.copy(activity,view,null);
        return view;
    }

    /**
     * 根据条件查询活动且分页
     *
     * @param reqActivityQuery
     * @return
     */
    @Override
    public PageInfo<ActivityView> findActivityPage(ReqActivityQuery reqActivityQuery) {
        log.info("根据条件查询活动且分页 请求参数：{}",JSON.toJSONString(reqActivityQuery));
        PageInfo <ActivityView> pageInfo = null;
        try{
            Page<?> page = PageHelper.startPage(reqActivityQuery.getPageNum(),reqActivityQuery.getPageSize());
            List<Activity> queryActivities = activityMapper.findActivityPage(reqActivityQuery);
            if (CollectionUtils.isEmpty(queryActivities)){
                pageInfo = new PageInfo<>(new ArrayList<>(16));
            }else{
                List<ActivityView> views = new ArrayList<>();
                queryActivities.stream().forEach(activity -> {
                    ActivityView view = new ActivityView();
                    BeanCopier beanCopier = BeanCopier.create( Activity.class, ActivityView.class,false);
                    beanCopier.copy(activity,view,null);
                    views.add(view);
                });
                pageInfo = new PageInfo<>(views);
            }
            log.info("总共有:{}",page.getTotal()+"条数据,实际返回{}:",page.size()+"两条数据!");
        }catch (Exception e){
            log.info("根据条件查询活动且分页 返回结果：{}",e.getMessage());
        }
        return pageInfo;
    }
}
