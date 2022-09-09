package com.chen.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;


/**
 * @author chenouba
 */
public class DateFormatUtil {
    public static void main(String[] args) throws ParseException {
        long time = System.currentTimeMillis();
        System.out.println(time);
        System.out.println(timeToFormat(time));
        System.out.println(timeToSecond(timeToFormat(time)));
    }
    /**
     * 13位毫秒时间戳  -->  yyyy-MM-dd HH:mm:ss
     */
    public static String timeToFormat(long time) {
        //设置日期格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(time);
    }

    /**
     * yyyy-MM-dd HH:mm:ss  -->  13位毫秒时间戳
     * @param date
     * @return
     * @throws ParseException
     */
    public static long timeToSecond(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.parse(date).getTime();
    }
}
