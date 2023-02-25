package com.keepsoft.microservice.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;

@ApiModel("发电流量小时")
@Data
@EqualsAndHashCode(callSuper = false)
public class HistoryHourDataRespDto implements Serializable {
    private static final long serialVersionUID = -66879404475172690L;

    /**
     * 主键id
     */
    @ApiModelProperty("主键id")
    private Integer id;

    /**
     * 属性条目全码
     */
    @ApiModelProperty("属性条目全码")
    private String attrItemFullCode;


    /**
     * 数据时间
     */
    @ApiModelProperty("数据时间")
    private String dataTime;

    /**
     * 流量值
     */
    @ApiModelProperty("流量值")
    private BigDecimal value;

    /**
     * 流量平均值
     */
    @ApiModelProperty("流量平均值")
    private BigDecimal avgValue;

    /**
     * 流量最大值
     */
    @ApiModelProperty("流量最大值")
    private BigDecimal maxv;

    /**
     * 流量最大值发生时间
     */
    @ApiModelProperty("流量最大值发生时间")
    private String maxValueTime;

    /**
     * 流量最小值
     */
    @ApiModelProperty("流量最小值")
    private BigDecimal minValue;

    /**
     * 流量最小值发生时间
     */
    @ApiModelProperty("流量最小值发生时间")
    private String minValueTime;

    /**
     * s
     */
    @ApiModelProperty("s")
    private BigDecimal s;

    /**
     * avgs
     */
    @ApiModelProperty("avgs")
    private BigDecimal avgs;

    /**
     * maxs
     */
    @ApiModelProperty("maxs")
    private BigDecimal maxs;

    /**
     * mins
     */
    @ApiModelProperty("mins")
    private BigDecimal mins;

    /**
     * span
     */
    @ApiModelProperty("span")
    private BigDecimal span;

    /**
     * dq
     */
    @ApiModelProperty("dq")
    private BigDecimal dq;

}
