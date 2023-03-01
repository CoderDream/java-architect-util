package com.coderdream.freeapps.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FreeHistoryDTO {

  @Schema(name = "")
  private String appId;

  @Schema(name = "")
  private String name;

  @Schema(name = "")
  private Integer priceCn;

  @Schema(name = "")
  private BigDecimal priceUs;

  @Schema(name = "")
  private String priceStr;

  @Schema(name = "")
  private String priceStrCn;

  @Schema(name = "")
  private String priceStrUs;

  @Schema(name = "")
  private Boolean usFlag;

  @Schema(name = "")
  private String urlCn;

  @Schema(name = "")
  private String urlUs;

  @Schema(name = "限免日期")
  private Date freeDate;

  @Schema(name = "")
  private Date createTime;

  @Schema(name = "")
  private Date updateTime;

}
