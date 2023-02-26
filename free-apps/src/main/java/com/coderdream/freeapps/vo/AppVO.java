package com.coderdream.freeapps.vo;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppVO {

  private String appId;
  private String name;
  private Integer priceCn;
  private BigDecimal priceUs;
  private String priceStr;
  private String priceStrCn;
  private String priceStrUs;
  private Integer usFlag;
  private String urlCn;
  private String urlUs;
  private Date createTime;
  private Date updateTime;
}
