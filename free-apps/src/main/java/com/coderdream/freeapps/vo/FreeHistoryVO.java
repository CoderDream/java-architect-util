package com.coderdream.freeapps.vo;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FreeHistoryVO {

  private String appId;
  private String name;
  private Integer priceCn;
  private BigDecimal priceUs;
  private String priceStr;
  private String priceStrCn;
  private String priceStrUs;
  private Boolean usFlag;
  private String urlCn;
  private String urlUs;
  private Date freeDate;
  private Date createTime;
  private Date updateTime;
}
