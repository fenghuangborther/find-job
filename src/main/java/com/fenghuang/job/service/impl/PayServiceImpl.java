package com.fenghuang.job.service.impl;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.fenghuang.job.alipay.Alipay;
import com.fenghuang.job.alipay.AlipayBean;
import com.fenghuang.job.service.PayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2019/12/27 16:14
 * @Email: 15290810931@163.com
 */
@Slf4j
@Service
public class PayServiceImpl implements PayService {

    @Autowired
    private Alipay alipay;
    /**
     * 支付宝支付接口
     *
     * @param alipayBean
     * @return
     * @throws AlipayApiException
     */
    @Override
    public String aliPay(AlipayBean alipayBean) throws AlipayApiException {
        log.info("alipay 在线支付请求参数：{}", JSON.toJSONString(alipayBean));
        return alipay.pay(alipayBean);
    }
}
