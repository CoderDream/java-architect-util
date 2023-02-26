package com.coderdream.freeapps.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.coderdream.freeapps.common.model.CoreBase;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.Date;


/**
 *.
 *
 * @author hresh
 * @since 2023-02-26 13:26:24
*/
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_free_history")
@Schema(name = "t_free_history对象", description = "")
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class FreeHistory extends CoreBase {

  private static final long serialVersionUID = 1L;

  @TableField("app_id")
  @Schema(name = "")
  private String appId;

  @TableField("name")
  @Schema(name = "")
  private String name;

  @TableField("price_cn")
  @Schema(name = "")
  private Integer priceCn;

  @TableField("price_us")
  @Schema(name = "")
  private BigDecimal priceUs;

  @TableField("price_str")
  @Schema(name = "")
  private String priceStr;

  @TableField("price_str_cn")
  @Schema(name = "")
  private String priceStrCn;

  @TableField("price_str_us")
  @Schema(name = "")
  private String priceStrUs;

  @TableField("us_flag")
  @Schema(name = "")
  private Integer usFlag;

  @TableField("url_cn")
  @Schema(name = "")
  private String urlCn;

  @TableField("url_us")
  @Schema(name = "")
  private String urlUs;

  @TableField("free_date")
  @Schema(name = "限免日期")
  private Date freeDate;

  @TableField("create_time")
  @Schema(name = "")
  private Date createTime;

  @TableField("update_time")
  @Schema(name = "")
  private Date updateTime;

}
