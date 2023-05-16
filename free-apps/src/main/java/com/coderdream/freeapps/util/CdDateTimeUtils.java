package com.coderdream.freeapps.util;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CdDateTimeUtils {

    /**
     * @param path
     * @return
     */
    public static List<String> fileTraversalNotRecursion(String path) {//非递归
        List<String> names = new ArrayList<>();
        File file = new File(path);
        if (null != file) {
            if (file.exists()) {
                LinkedList<String> list = new LinkedList<>();//利用 LinkedList 的属性,链表结构
                File[] files = file.listFiles();
                if (null == files || 0 == files.length) {
                    // logger.info("该文件夹下面没有文件");
                } else {
                    for (int i = 0, size = files.length; i < size; i++) {
                        File temp = files[i];
                        if (temp.isFile()) {
                            names.add(temp.toString());
                        } else {
                            list.add(temp.getAbsolutePath());
                        }
                    }

                    //遍历文件夹下面的所有文件
                    while (!list.isEmpty()) {
                        String tempPath = list.removeFirst();
                        File temp = new File(tempPath);
                        File[] tf = temp.listFiles();
                        for (File ff : tf) {
                            if (ff.isFile()) {
                                names.add(ff.toString());
                            } else {
                                list.add(ff.getAbsolutePath());
                            }
                        }
                    }
                }
            }
        }
        return names;
    }


    /**
     * 标准日期时间格式，精确到秒：yyyy-MM-dd HH:mm:ss
     */
    public static final String NORM_DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static String dateToStr(Date date) {
        if (date == null) {
            return "";
        }
        DateFormat dateFormat = new SimpleDateFormat(NORM_DATETIME_PATTERN);
        return dateFormat.format(date);
    }

    public static Date genNewDate(String str) {
        DateFormat dateFormat = new SimpleDateFormat(NORM_DATETIME_PATTERN);
        try {
            return dateFormat.parse(str);
        } catch (ParseException e) {
            return null;
        }
    }

    public static Date genNewDate(Date date, Integer hours) {
        long t = date.getTime();
        t += hours * 60 * 60 * 1000;
        if (t > 0) {
            return new Date(t);
        }
        return null;
    }

    public static String genMessage(Long milliseconds) {
        final long day = TimeUnit.MILLISECONDS.toDays(milliseconds);

        final long hours = TimeUnit.MILLISECONDS.toHours(milliseconds)
            - TimeUnit.DAYS.toHours(TimeUnit.MILLISECONDS.toDays(milliseconds));

        final long minutes = TimeUnit.MILLISECONDS.toMinutes(milliseconds)
            - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(milliseconds));

        final long seconds = TimeUnit.MILLISECONDS.toSeconds(milliseconds)
            - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(milliseconds));

        final long ms = TimeUnit.MILLISECONDS.toMillis(milliseconds)
            - TimeUnit.SECONDS.toMillis(TimeUnit.MILLISECONDS.toSeconds(milliseconds));

        String message = String.format("%d 天 %d 小时 %d 分 %d 秒 %d 毫秒",
            day, hours, minutes, seconds, ms);
        if (day == 0) {
            message = String.format("%d 小时 %d 分 %d 秒 %d 毫秒",
                hours, minutes, seconds, ms);
            if (hours == 0) {
                message = String.format("%d 分 %d 秒 %d 毫秒",
                    minutes, seconds, ms);
                if (minutes == 0) {
                    message = String.format("%d 秒 %d 毫秒",
                        seconds, ms);
                }
            }
        }
        return message;
    }
}
