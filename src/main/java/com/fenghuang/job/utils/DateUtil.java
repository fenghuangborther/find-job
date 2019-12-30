package com.fenghuang.job.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2019/12/26 14:40
 * @Email: 15290810931@163.com
 */
public class DateUtil {


    public static String subDate(){
        Date date = new Date();//当前日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//格式化对象
        Calendar calendar = Calendar.getInstance();//日历对象
        calendar.setTime(date);//设置当前日期
        calendar.add(Calendar.MONTH, -2);//月份减一
        return sdf.format(calendar.getTime());
    }

    /**
     * 在指定时间上减去多少分钟
     * @param date
     * @param minute
     * @return
     */
    public static String subMinute(Date date,int minute){
        //格式化对象
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //日历对象
        Calendar calendar = Calendar.getInstance();
        //设置当前日期
        calendar.setTime(date);
        //月份减一
        calendar.add(Calendar.MINUTE, minute);
        return sdf.format(calendar.getTime());
    }

    /**
     * data to string
     * @return
     */
    public static String dateToString(Date date){
        return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(date);
    }


    public static void main(String[] args) throws ParseException {
      /*  String time = subMinute(new Date(),-60);
        System.out.println(time);*/
       /* Date date = DateUtils.parseDate("2019-12-26 15:06:00", "yyyy-MM-dd hh:mm:ss");
        System.out.println("得到的时间："+date);*/

        System.out.println(dateToString(new Date()));

    }


}
