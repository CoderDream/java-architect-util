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
 * 水库水情
 * </p>
 *
 * @author MoBai·杰
 * @since 2023-02-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ST_RSVR_R")
@ApiModel(value="StRsvrREntity对象", description="水库水情")
public class StRsvrREntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "测站编码")
    @TableId(value = "STCD", type = IdType.ID_WORKER)
    private String stcd;

    @ApiModelProperty(value = "时间")
    @TableField("TM")
    private Date tm;

    @ApiModelProperty(value = "库上水位")
    @TableField("RZ")
    private BigDecimal rz;

    @ApiModelProperty(value = "入库流量")
    @TableField("INQ")
    private BigDecimal inq;

    @ApiModelProperty(value = "蓄水量")
    @TableField("W")
    private BigDecimal w;

    @ApiModelProperty(value = "出库流量")
    @TableField("OTQ")
    private BigDecimal otq;

    @ApiModelProperty(value = "库水特征码")
    @TableField("RWCHRCD")
    private String rwchrcd;

    @ApiModelProperty(value = "库水水势")
    @TableField("RWPTN")
    private String rwptn;

    @ApiModelProperty(value = "入流时段长")
    @TableField("INQDR")
    private BigDecimal inqdr;

    @ApiModelProperty(value = "测流方法")
    @TableField("MSQMT")
    private String msqmt;

    @ApiModelProperty(value = "库下水位")
    @TableField("BLRZ")
    private BigDecimal blrz;


}
