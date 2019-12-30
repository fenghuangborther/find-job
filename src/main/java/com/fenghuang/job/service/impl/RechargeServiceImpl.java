package com.fenghuang.job.service.impl;

import com.alibaba.fastjson.JSON;
import com.fenghuang.job.dao.master.RechargeMapper;
import com.fenghuang.job.entity.Recharge;
import com.fenghuang.job.enums.RechargeOrderStatusEnum;
import com.fenghuang.job.request.ReqRecharge;
import com.fenghuang.job.service.RechargeService;
import com.fenghuang.job.utils.OrderIdUtils;
import com.fenghuang.job.view.RechargeView;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2019/12/30 15:30
 * @Email: 15290810931@163.com
 */
@Service
@Slf4j
public class RechargeServiceImpl implements RechargeService {

    @Autowired
    RechargeMapper rechargeMapper;
    /**
     * 新增用户充值订单信息
     *
     * @param reqRecharge
     * @return
     */
    @Override
    public String insertRecharge(ReqRecharge reqRecharge) {
        log.info("新增用户充值订单信息 请求参数：{}", JSON.toJSONString(reqRecharge));
        Recharge recharge = new Recharge();
        BeanCopier beanCopier = BeanCopier.create(ReqRecharge.class, Recharge.class,false);
        beanCopier.copy(reqRecharge,recharge,null);
        recharge.setCreateDate(new Date());
        recharge.setUpdateDate(new Date());
        recharge.setRechargeOrderStatus( RechargeOrderStatusEnum.PROCESSING.getCode());
        recharge.setFounder(reqRecharge.getFounder());
        recharge.setRechargeOrderId( OrderIdUtils.generateOrderSn( reqRecharge.getUserId().toString() ) );
        rechargeMapper.insertSelective(recharge);
        String rechargeOrderId = recharge.getRechargeOrderId();
        log.info( "生成订单返回的订单号为：{}",rechargeOrderId );
        return rechargeOrderId;
    }

    /**
     * 根据条件查询用户充值订单信息带分页
     *
     * @param reqRecharge
     * @return
     */
    @Override
    public PageInfo<RechargeView> findRechargePage(ReqRecharge reqRecharge) {
        log.info("根据条件查询用户充值订单信息带分页 请求参数：{}",JSON.toJSONString(reqRecharge));
        PageInfo <RechargeView> pageInfo = null;
        try {
            Page<?> page = PageHelper.startPage(reqRecharge.getPageNum(),reqRecharge.getPageSize());
            List<Recharge> queryRecharge = rechargeMapper.findRechargePage(reqRecharge);
            if (CollectionUtils.isEmpty(queryRecharge)){
                pageInfo = new PageInfo<>(new ArrayList<>(16));
            }else{
                List<RechargeView> views = new ArrayList<>(16);
                queryRecharge.stream().forEach(recharge -> {
                    RechargeView view = new RechargeView();
                    BeanCopier beanCopier = BeanCopier.create( Recharge.class,RechargeView.class,false);
                    beanCopier.copy(recharge,view,null);
                    views.add(view);
                });
                pageInfo = new PageInfo<>(views);
            }
            log.info("总共有:{}",page.getTotal()+"条数据,实际返回{}:",page.size()+"两条数据!");
        }catch (Exception e){
            log.info("根据条件查询用户充值订单信息带分页 查询异常：{}",e.getMessage());
        }
        return pageInfo;
    }
}
