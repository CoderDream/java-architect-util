package com.coderdream.freeapps.dto.constant;

/**
 * @author hresh
 * @博客 https://juejin.cn/user/2664871918047063
 * @网站 https://www.hreshhao.com/
 * @date 2022/11/23 3:27 下午
 */
public enum WeatherConstant {
  BEI_JING("北京", "101010100"),
  WU_HAN("武汉", "101200101"),
  SHI_YAN("十堰", "101201101"),
  ;


  private String cityName;
  private String code;

  WeatherConstant(String cityName, String code) {
    this.cityName = cityName;
    this.code = code;
  }

  public String getCityName() {
    return cityName;
  }

  public String getCode() {
    return code;
  }
}
