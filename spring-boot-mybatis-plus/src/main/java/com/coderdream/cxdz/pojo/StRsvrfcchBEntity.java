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
 * 库（湖）站防洪指标
 * </p>
 *
 * @author MoBai·杰
 * @since 2023-02-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ST_RSVRFCCH_B")
@ApiModel(value="StRsvrfcchBEntity对象", description="库（湖）站防洪指标")
public class StRsvrfcchBEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "测站编码")
    @TableId(value = "STCD", type = IdType.ID_WORKER)
    private String stcd;

    @ApiModelProperty(value = "坝顶高程")
    @TableField("DAMEL")
    private BigDecimal damel;

    @ApiModelProperty(value = "校核洪水位")
    @TableField("CKFLZ")
    private BigDecimal ckflz;

    @ApiModelProperty(value = "设计洪水位")
    @TableField("DSFLZ")
    private BigDecimal dsflz;

    @ApiModelProperty(value = "正常高水位")
    @TableField("NORMZ")
    private BigDecimal normz;

    @ApiModelProperty(value = "死水位")
    @TableField("DDZ")
    private BigDecimal ddz;

    @ApiModelProperty(value = "兴利水位")
    @TableField("ACTZ")
    private BigDecimal actz;

    @ApiModelProperty(value = "总库容")
    @TableField("TTCP")
    private BigDecimal ttcp;

    @ApiModelProperty(value = "防洪库容")
    @TableField("FLDCP")
    private BigDecimal fldcp;

    @ApiModelProperty(value = "兴利库容")
    @TableField("ACTCP")
    private BigDecimal actcp;

    @ApiModelProperty(value = "死库容")
    @TableField("DDCP")
    private BigDecimal ddcp;

    @ApiModelProperty(value = "历史最高库水位")
    @TableField("HHRZ")
    private BigDecimal hhrz;

    @ApiModelProperty(value = "历史最高库水位（蓄水量）出现时间")
    @TableField("HHRZTM")
    private Date hhrztm;

    @ApiModelProperty(value = "历史最大入流")
    @TableField("HMXINQ")
    private BigDecimal hmxinq;

    @ApiModelProperty(value = "历史最大入流时段长")
    @TableField("RSTDR")
    private BigDecimal rstdr;

    @ApiModelProperty(value = "历史最大入流出现时间")
    @TableField("HMXINQTM")
    private Date hmxinqtm;

    @ApiModelProperty(value = "历史最大蓄水量")
    @TableField("HMXW")
    private BigDecimal hmxw;

    @TableField("HMXWTM")
    private Date hmxwtm;

    @TableField("RHMXOTQ")
    private BigDecimal rhmxotq;

    @TableField("RHMXOTQTM")
    private Date rhmxotqtm;

    @ApiModelProperty(value = "历史最低库水位")
    @TableField("HLRZ")
    private BigDecimal hlrz;

    @ApiModelProperty(value = "历史最低库水位出现时间")
    @TableField("HLRZTM")
    private Date hlrztm;

    @ApiModelProperty(value = "历史最小日均入流")
    @TableField("HMNINQ")
    private BigDecimal hmninq;

    @ApiModelProperty(value = "历史最小日均入流出现时间")
    @TableField("HMNINQTM")
    private Date hmninqtm;

    @TableField("TAZ")
    private BigDecimal taz;

    @TableField("TAQ")
    private BigDecimal taq;

    @ApiModelProperty(value = "低水位告警值")
    @TableField("LAZ")
    private BigDecimal laz;

    @ApiModelProperty(value = "时间戳")
    @TableField("MODITIME")
    private Date moditime;

    @ApiModelProperty(value = "水库类型")
    @TableField("RSVRTP")
    private String rsvrtp;

    @ApiModelProperty(value = "历史最大出流")
    @TableField("HMXOTQ")
    private BigDecimal hmxotq;

    @ApiModelProperty(value = "历史最大出流出现时间")
    @TableField("HMXOTQTM")
    private Date hmxotqtm;

    @ApiModelProperty(value = "启动预报流量标准")
    @TableField("SFQ")
    private BigDecimal sfq;


}
