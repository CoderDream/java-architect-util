package com.coderdream.freeapps.dto;

import lombok.Data;

/**
 */
@Data
public class RecommendApp {
  private String appId;//应用ID
  private String title;//应用名称
  private String rateAmount; //投票数
  private String usFlag;// 美区标志
  private String cnFlag;// 美区标志
  /**
   * 美区应用简介
   */
  private String descriptionUs;

  /**
   * 国区应用简介
   */
  private String descriptionCn;

  /**
   * 个人修饰应用简介
   */
  private String descriptionMy;

  /**
   * 一句话应用简介，用于字幕
   */
  private String descriptionZm;

  /**
   * 一句话应用简介，用于字幕
   */
  private String description;
  private String yesterdayPrice;//昨日价格
  private String todayPrice;//今日价格
}
