package com.cyberoller.cloud.base.abc.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("ST_REGLAT_F")
@ApiModel(value="StReglatF对象", description="")
public class StReglatF implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("STCD")
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

    @TableField("W")
    private BigDecimal w;

    @TableField("OTQ")
    private BigDecimal otq;


}
