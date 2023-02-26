package com.coderdream.freeapps.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/**
 * @author hresh
 * @博客 https://juejin.cn/user/2664871918047063
 * @网站 https://www.hreshhao.com/
 * @date 2022/11/23 5:19 下午
 */
@Data
@Builder
@ToString
public class WeatherInfo {

  private String date;//时间
  private String cityName;//城市名
  private String weather;//天气
  private String temperature;//气温
  private String currentTemperature;//当前气温
  private String airQuality;//pm2.5
  private String humidity; // 湿度
  private String windDirection;
  private String notice;

}
