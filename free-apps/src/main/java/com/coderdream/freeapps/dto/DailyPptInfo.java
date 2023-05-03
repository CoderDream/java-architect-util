package com.coderdream.freeapps.dto;

import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/**
 */
@Data
@Builder
@ToString
public class DailyPptInfo {

  private String appId;//
  private String title;//
  private String rateAmount;//
  private Integer yesterdayPrice;//
  private Integer todayPrice;//当前气温
  private String airQuality;//pm2.5
  private String humidity; // 湿度
  private String windDirection;

}
