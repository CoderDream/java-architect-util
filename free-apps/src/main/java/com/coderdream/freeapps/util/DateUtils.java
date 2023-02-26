package com.coderdream.freeapps.util;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.TimeZone;

/**
 * @author hresh
 * @博客 https://juejin.cn/user/2664871918047063
 * @网站 https://www.hreshhao.com/
 * @date 2022/11/23 5:22 下午
 */
public class DateUtils {

  public static final String DATE_FORMAT_WITHOUT_HYPHEN = "yyyyMMdd";
  public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
  private static final ZoneId ZONE_ID = TimeZone.getDefault().toZoneId();

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
