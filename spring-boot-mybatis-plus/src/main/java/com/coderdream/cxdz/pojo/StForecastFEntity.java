package com.coderdream.cxdz.pojo;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @author MoBai·杰
 * @since 2023-02-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ST_FORECAST_F")
@ApiModel(value="StForecastFEntity对象", description="")
public class StForecastFEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "STCD", type = IdType.ID_WORKER)
    private String stcd;

    @TableField("UNITNAME")
    private String unitname;

    @TableField("PLCD")
    private String plcd;

    @TableField("FYMDH")
    private Date fymdh;

    @TableField("IYMDH")
    private Date iymdh;

    @TableField("YMDH")
    private Date ymdh;

    @TableField("Z")
    private BigDecimal z;

    @TableField("Q")
    private BigDecimal q;


}
