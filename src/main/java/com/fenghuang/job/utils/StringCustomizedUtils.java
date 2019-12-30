package com.fenghuang.job.utils;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2019/12/19 17:50
 * @Email: 15290810931@163.com
 */
public class StringCustomizedUtils {

    /**
     * string 字符串去掉左右空格
     * @param str
     * @return
     */
    public static String stringTrim(String str){
        str = str.trim();
        //这里判断是不是全角空格
        while (str.startsWith("　")) {
            str = str.substring(1, str.length()).trim();
        }
        while (str.endsWith("　")) {
            str = str.substring(0, str.length() - 1).trim();
        }
        return str;
    }
}
