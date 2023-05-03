package com.coderdream.freeapps.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author hresh
 * @博客 https://juejin.cn/user/2664871918047063
 * @网站 https://www.hreshhao.com/
 * @date 2022/11/23 5:22 下午
 */
public class CdDateUtils {

    public static final String DATE_FORMAT_WITHOUT_HYPHEN = "yyyyMMdd";
    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final ZoneId ZONE_ID = TimeZone.getDefault().toZoneId();

    public static void main(String[] args) {

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = dateFormat.format(new Date());
        try {
            Date currentDate = dateFormat.parse(dateStr);

            Date startDate = dateFormat.parse("2023-04-01");
            Date endDate = dateFormat.parse("2023-04-05");

            List<Date> dateList = CdDateUtils.getDatesBetweenUsingJava7(startDate, endDate);
            for (Date d : dateList) {
                System.out.println(dateFormat.format(d));
            }

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Date> getDatesBetweenUsingJava7(Date startDate, Date endDate) {
        List<Date> datesInRange = new ArrayList<>();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(startDate);

        Calendar endCalendar = new GregorianCalendar();
        endCalendar.setTime(endDate);

        while (calendar.before(endCalendar)) {
            calendar.add(Calendar.DATE, 1);
            Date result = calendar.getTime();
            datesInRange.add(result);
        }
        return datesInRange;
    }

    /**
     * 含尾不含头
     * @param startDateStr
     * @param endDateStr
     * @return
     */
    public static List<String> getDatesBetween(String startDateStr, String endDateStr) {
        List<String> datesInRange = new ArrayList<>();
        try {
            Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(startDateStr);
            Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(endDateStr);

            Calendar calendar = new GregorianCalendar();
            calendar.setTime(startDate);

            Calendar endCalendar = new GregorianCalendar();
            endCalendar.setTime(endDate);

            while (calendar.before(endCalendar)) {
                calendar.add(Calendar.DATE, 1);
                Date result = calendar.getTime();
                datesInRange.add(new SimpleDateFormat("yyyy-MM-dd").format(result));
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return datesInRange;
    }

    public static List<LocalDate> getDatesBetweenUsingJava8(LocalDate startDate, LocalDate endDate) {
        long numOfDaysBetween = ChronoUnit.DAYS.between(startDate, endDate);
        return IntStream.iterate(0, i -> i + 1)
            .limit(numOfDaysBetween)
            .mapToObj(i -> startDate.plusDays(i))
            .collect(Collectors.toList());
    }

    public static long toEpochMilli(LocalDateTime localDateTime) {
        if (null == localDateTime) {
            return 0L;
        }
        return localDateTime.atZone(ZONE_ID).toInstant().toEpochMilli();
    }

    public static String getDay(LocalDateTime dateTime) {
        return formatDateByPattern(dateTime, DATE_FORMAT_WITHOUT_HYPHEN);
    }

    public static String formatDateByPattern(LocalDateTime dateTime, String dateFormat) {
        long localDateTime = toEpochMilli(dateTime);
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        String formatTimeStr = null;
        if (dateTime != null) {
            formatTimeStr = sdf.format(localDateTime);
        }
        return formatTimeStr;
    }

    public static String getCron(LocalDateTime dateTime) {
        String dateFormat = "ss mm HH dd MM ? yyyy";
        return formatDateByPattern(dateTime, dateFormat);
    }

    public static String formatDate(LocalDateTime dateTime) {
        return formatDateByPattern(dateTime, DATE_FORMAT);
    }

    public static String formatDate(Long timestamp) {
        Instant instant = Instant.ofEpochMilli(timestamp);
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZONE_ID);
        return formatDate(localDateTime);
    }

}
