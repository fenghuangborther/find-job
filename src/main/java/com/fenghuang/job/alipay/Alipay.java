package com.fenghuang.job.alipay;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2019/12/27 11:27
 * @Email: 15290810931@163.com
 */
@Component
@Slf4j
public class Alipay {

    public String pay(AlipayBean alipayBean) throws AlipayApiException {
        // 1、获得初始化的AlipayClient
        String serverUrl = AlipayProperties.getGatewayUrl();
        log.info("获取的serverUrl:{}",serverUrl);
        String appId = AlipayProperties.getAppId();
        log.info("获取的appId:{}",appId);
        String privateKey = AlipayProperties.getPrivateKey();
        String format = "json";
        String charset = AlipayProperties.getCharset();
        log.info("获取的字符编码集:{}",charset);
        String alipayPublicKey = AlipayProperties.getPublicKey();
        String signType = AlipayProperties.getSignType();
        String returnUrl = AlipayProperties.getReturnUrl();
        log.info("获取的returnUrl：{}",returnUrl);
        String notifyUrl = AlipayProperties.getNotifyUrl();
        log.info("获取的notifyUrl：{}",notifyUrl);
        AlipayClient alipayClient = new DefaultAlipayClient(serverUrl, appId, privateKey, format, charset, alipayPublicKey, signType);
        log.info("调用alipayClient得到的结果：{}",JSON.toJSONString(alipayClient));
        // 2、设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        // 页面跳转同步通知页面路径
        alipayRequest.setReturnUrl(returnUrl);
        // 服务器异步通知页面路径
        alipayRequest.setNotifyUrl(notifyUrl);
        // 封装参数
        alipayRequest.setBizContent(JSON.toJSONString(alipayBean));
        // 3、请求支付宝进行付款，并获取支付结果
        String result = alipayClient.pageExecute(alipayRequest).getBody();
        log.info("请求支付宝进行付款，并获取支付结果：{}",JSON.toJSONString(result));
        return result;
    }
}
