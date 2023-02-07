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
 * 测站基本属性
 * </p>
 *
 * @author will
 * @since 2023-02-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ST_STBPRP_B")
@ApiModel(value="StStbprpB对象", description="测站基本属性")
public class StStbprpB implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "测站编码")
    @TableField("STCD")
    private String stcd;

    @ApiModelProperty(value = "测站名称")
    @TableField("STNM")
    private String stnm;

    @ApiModelProperty(value = "河流名称")
    @TableField("RVNM")
    private String rvnm;

    @ApiModelProperty(value = "水系名称")
    @TableField("HNNM")
    private String hnnm;

    @ApiModelProperty(value = "流域名称")
    @TableField("BSNM")
    private String bsnm;

    @ApiModelProperty(value = "经度")
    @TableField("LGTD")
    private BigDecimal lgtd;

    @ApiModelProperty(value = "纬度")
    @TableField("LTTD")
    private BigDecimal lttd;

    @ApiModelProperty(value = "站址")
    @TableField("STLC")
    private String stlc;

    @ApiModelProperty(value = "行政区划码")
    @TableField("ADDVCD")
    private String addvcd;

    @ApiModelProperty(value = "基面名称")
    @TableField("DTMNM")
    private String dtmnm;

    @ApiModelProperty(value = "基面高程")
    @TableField("DTMEL")
    private BigDecimal dtmel;

    @ApiModelProperty(value = "基面修正值")
    @TableField("DTPR")
    private BigDecimal dtpr;

    @ApiModelProperty(value = "站类")
    @TableField("STTP")
    private String sttp;

    @ApiModelProperty(value = "报汛等级")
    @TableField("FRGRD")
    private String frgrd;

    @ApiModelProperty(value = "建站年月")
    @TableField("ESSTYM")
    private String esstym;

    @ApiModelProperty(value = "始报年月")
    @TableField("BGFRYM")
    private String bgfrym;

    @ApiModelProperty(value = "隶属行业单位")
    @TableField("ATCUNIT")
    private String atcunit;

    @ApiModelProperty(value = "信息管理单位")
    @TableField("ADMAUTH")
    private String admauth;

    @ApiModelProperty(value = "交换管理单位")
    @TableField("LOCALITY")
    private String locality;

    @ApiModelProperty(value = "测站岸别")
    @TableField("STBK")
    private String stbk;

    @ApiModelProperty(value = "测站方位")
    @TableField("STAZT")
    private BigDecimal stazt;

    @ApiModelProperty(value = "至河口距离")
    @TableField("DSTRVM")
    private BigDecimal dstrvm;

    @ApiModelProperty(value = "集水面积")
    @TableField("DRNA")
    private BigDecimal drna;

    @ApiModelProperty(value = "拼音码")
    @TableField("PHCD")
    private String phcd;

    @ApiModelProperty(value = "启用标志")
    @TableField("USFL")
    private String usfl;

    @ApiModelProperty(value = "备注")
    @TableField("COMMENTS")
    private String comments;

    @ApiModelProperty(value = "时间戳")
    @TableField("MODITIME")
    private LocalDateTime moditime;

    @TableField("EDFRYM")
    private String edfrym;

    @TableField("LGTD_OLD")
    private String lgtdOld;

    @TableField("LTTD_OLD")
    private String lttdOld;

    @TableField("FRITM")
    private String fritm;


}
