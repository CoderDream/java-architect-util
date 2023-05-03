package com.coderdream.freeapps.dto;

import lombok.Data;

/**
 * @date 2022/11/23 5:19 下午
 */
@Data
public class TopList {

  private String raking;//排名
  private String appId;//应用ID
  private String title;//应用名称
  private String topRaking;//总排名
  private String category;//分类
  private String categoryRaking;//分类排名
  private String author; // 开发者
  private String compareYesterday;// 	相比昨日

}
