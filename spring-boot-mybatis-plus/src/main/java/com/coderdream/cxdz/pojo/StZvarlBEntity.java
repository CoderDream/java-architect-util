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
 * 库（湖）容曲线
 * </p>
 *
 * @author MoBai·杰
 * @since 2023-02-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ST_ZVARL_B")
@ApiModel(value="StZvarlBEntity对象", description="库（湖）容曲线")
public class StZvarlBEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "测站编码")
    @TableField("STCD")
    private String stcd;

    @ApiModelProperty(value = "施测时间")
    @TableField("MSTM")
    private Date mstm;

    @ApiModelProperty(value = "点序号")
    @TableField("PTNO")
    private BigDecimal ptno;

    @ApiModelProperty(value = "库水位")
    @TableField("RZ")
    private BigDecimal rz;

    @ApiModelProperty(value = "蓄水量")
    @TableField("W")
    private BigDecimal w;

    @ApiModelProperty(value = "水面面积")
    @TableField("WSFA")
    private BigDecimal wsfa;

    @ApiModelProperty(value = "时间戳")
    @TableField("MODITIME")
    private Date moditime;


}
