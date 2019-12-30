package com.fenghuang.job.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2019/12/20 21:16
 * @Version: 1.0
 * @Email: 15290810931@163.com
 */
public class OrderIdUtils {

    /**
     * 生成orderId 精确到纳秒级
     * @param id
     * @return
     */
    public static String generateOrderSn(String id) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("YYYYMMddHHmmssSSS");
        String time = dtf.format(now);
        String nano = Math.abs(System.nanoTime()) + "";
        int index = ThreadLocalRandom.current().nextInt(nano.length() - 6);
        nano = nano.substring(index, index + 6);
        return time + id + nano;
    }
}
