package com.coderdream.easyexcelpractise.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@TableName(value = "t_fdll_hour2")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class SampleEntity {
    private static final long serialVersionUID = -66879404475172690L;

    /**
     * 主键id
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /**
     * 编号
     */
    @TableField(value = "senid")
    private String sendId;

    /**
     * 数据时间
     */
    @TableField(value = "data_time")
    private Date dataTime;

    /**
     * 流量值
     */
    @TableField(value = "v")
    private BigDecimal value;

    /**
     * 流量平均值
     */
    @TableField(value = "avgv")
    private BigDecimal avgValue;

    /**
     * 流量最大值
     */
    @TableField(value = "maxv")
    private BigDecimal maxValue;

    /**
     * 流量最大值发生时间
     */
    @TableField(value = "maxt")
    private Date maxValueTime;

    /**
     * 流量最小值
     */
    @TableField(value = "minv")
    private BigDecimal minValue;

    /**
     * 流量最小值发生时间
     */
    @TableField(value = "mint")
    private Date minValueTime;

    /**
     * s
     */
    @TableField(value = "s")
    private BigDecimal s;

    /**
     * avgs
     */
    @TableField(value = "avgs")
    private BigDecimal avgs;

    /**
     * maxs
     */
    @TableField(value = "maxs")
    private BigDecimal maxs;

    /**
     * mins
     */
    @TableField(value = "mins")
    private BigDecimal mins;

    /**
     * span
     */
    @TableField(value = "span")
    private BigDecimal span;

    /**
     * dq
     */
    @TableField(value = "dq")
    private BigDecimal dq;

}
