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
 * 河道站防洪指标
 * </p>
 *
 * @author MoBai·杰
 * @since 2023-02-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ST_RVFCCH_B")
@ApiModel(value="StRvfcchBEntity对象", description="河道站防洪指标")
public class StRvfcchBEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "测站编码")
    @TableId(value = "STCD", type = IdType.ID_WORKER)
    private String stcd;

    @ApiModelProperty(value = "左堤高程")
    @TableField("LDKEL")
    private BigDecimal ldkel;

    @ApiModelProperty(value = "右堤高程")
    @TableField("RDKEL")
    private BigDecimal rdkel;

    @ApiModelProperty(value = "警戒水位")
    @TableField("WRZ")
    private BigDecimal wrz;

    @ApiModelProperty(value = "警戒流量")
    @TableField("WRQ")
    private BigDecimal wrq;

    @ApiModelProperty(value = "保证水位")
    @TableField("GRZ")
    private BigDecimal grz;

    @ApiModelProperty(value = "保证流量")
    @TableField("GRQ")
    private BigDecimal grq;

    @ApiModelProperty(value = "平滩流量")
    @TableField("FLPQ")
    private BigDecimal flpq;

    @ApiModelProperty(value = "实测最高水位")
    @TableField("OBHTZ")
    private BigDecimal obhtz;

    @ApiModelProperty(value = "实测最高水位出现时间")
    @TableField("OBHTZTM")
    private Date obhtztm;

    @ApiModelProperty(value = "调查最高水位")
    @TableField("IVHZ")
    private BigDecimal ivhz;

    @ApiModelProperty(value = "调查最高水位出现时间")
    @TableField("IVHZTM")
    private Date ivhztm;

    @ApiModelProperty(value = "实测最大流量")
    @TableField("OBMXQ")
    private BigDecimal obmxq;

    @ApiModelProperty(value = "实测最大流量出现时间")
    @TableField("OBMXQTM")
    private Date obmxqtm;

    @ApiModelProperty(value = "调查最大流量")
    @TableField("IVMXQ")
    private BigDecimal ivmxq;

    @ApiModelProperty(value = "调查最大流量出现时间")
    @TableField("IVMXQTM")
    private Date ivmxqtm;

    @ApiModelProperty(value = "历史最大含沙量")
    @TableField("HMXS")
    private BigDecimal hmxs;

    @ApiModelProperty(value = "历史最大含沙量出现时间")
    @TableField("HMXSTM")
    private Date hmxstm;

    @ApiModelProperty(value = "历史最大断面平均流速")
    @TableField("HMXAVV")
    private BigDecimal hmxavv;

    @ApiModelProperty(value = "历史最大断面平均流速出现时间")
    @TableField("HMXAVVTM")
    private Date hmxavvtm;

    @ApiModelProperty(value = "历史最低水位")
    @TableField("HLZ")
    private BigDecimal hlz;

    @ApiModelProperty(value = "历史最低水位出现时间")
    @TableField("HLZTM")
    private Date hlztm;

    @ApiModelProperty(value = "历史最小流量")
    @TableField("HMNQ")
    private BigDecimal hmnq;

    @ApiModelProperty(value = "历史最小流量出现时间")
    @TableField("HMNQTM")
    private Date hmnqtm;

    @ApiModelProperty(value = "高水位告警值")
    @TableField("TAZ")
    private BigDecimal taz;

    @ApiModelProperty(value = "大流量告警值")
    @TableField("TAQ")
    private BigDecimal taq;

    @ApiModelProperty(value = "低水位告警值")
    @TableField("LAZ")
    private BigDecimal laz;

    @ApiModelProperty(value = "小流量告警值")
    @TableField("LAQ")
    private BigDecimal laq;

    @ApiModelProperty(value = "启动预报水位标准")
    @TableField("SFZ")
    private BigDecimal sfz;

    @ApiModelProperty(value = "启动预报流量标准")
    @TableField("SFQ")
    private BigDecimal sfq;

    @ApiModelProperty(value = "时间戳")
    @TableField("MODITIME")
    private Date moditime;


}
