package com.cyberoller.cloud.base.abc.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author will
 * @since 2023-02-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ST_FORECAST_F")
@ApiModel(value="StForecastF对象", description="")
public class StForecastF implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("STCD")
    private String stcd;

    @TableField("UNITNAME")
    private String unitname;

    @TableField("PLCD")
    private String plcd;

    @TableField("FYMDH")
    private LocalDateTime fymdh;

    @TableField("IYMDH")
    private LocalDateTime iymdh;

    @TableField("YMDH")
    private LocalDateTime ymdh;

    @TableField("Z")
    private BigDecimal z;

    @TableField("Q")
    private BigDecimal q;


}
