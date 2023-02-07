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
 * 河道水情
 * </p>
 *
 * @author MoBai·杰
 * @since 2023-02-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ST_RIVER_R")
@ApiModel(value="StRiverREntity对象", description="河道水情")
public class StRiverREntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "测站编码")
    @TableId(value = "STCD", type = IdType.ID_WORKER)
    private String stcd;

    @ApiModelProperty(value = "时间")
    @TableField("TM")
    private Date tm;

    @ApiModelProperty(value = "水位")
    @TableField("Z")
    private BigDecimal z;

    @ApiModelProperty(value = "流量")
    @TableField("Q")
    private BigDecimal q;

    @ApiModelProperty(value = "断面过水面积")
    @TableField("XSA")
    private BigDecimal xsa;

    @ApiModelProperty(value = "断面平均流速")
    @TableField("XSAVV")
    private BigDecimal xsavv;

    @ApiModelProperty(value = "断面最大流速")
    @TableField("XSMXV")
    private BigDecimal xsmxv;

    @ApiModelProperty(value = "河水特征码")
    @TableField("FLWCHRCD")
    private String flwchrcd;

    @ApiModelProperty(value = "水势")
    @TableField("WPTN")
    private String wptn;

    @ApiModelProperty(value = "测流方法")
    @TableField("MSQMT")
    private String msqmt;

    @ApiModelProperty(value = "测积方法")
    @TableField("MSAMT")
    private String msamt;

    @ApiModelProperty(value = "测速方法")
    @TableField("MSVMT")
    private String msvmt;

    @TableField("QC")
    private BigDecimal qc;


}
