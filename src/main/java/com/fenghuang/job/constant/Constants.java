package com.fenghuang.job.constant;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2019/12/17 14:59
 * @Email: 15290810931@163.com
 */
public class Constants {

    //秘钥串
    public static final String  SECRET_KEY = "1A2B3C4D5E6F7G8H9I0J0K9L8M7N6O1P2Q3R4S1TUVWXYZ";
    //发送短信时,如果当前手机号| ip 30分钟内频繁的发送短信超过MESSAGE_COUNT条，则视为用户进行恶意攻击
    public static final Integer MESSAGE_MINUTE = -30;
    //发送短信时,如果当前手机号| ip 30分钟内频繁的发送短信超过5条，则视为用户进行恶意攻击
    public static final Integer MESSAGE_COUNT  = 5;
}
