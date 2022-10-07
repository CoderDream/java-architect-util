package com.coderdream.easyexcelpractise.demo.read;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 基础数据类.这里的排序和excel里面的排序一致
 *
 * @author Jiaju Zhuang
 **/
@Getter
@Setter
@EqualsAndHashCode
public class SampleData {
    /**
     * 编号
     */
    private String senid;

    /**
     * 数据时间
     */
    private Date time;

    /**
     * 流量值
     */
    private BigDecimal v;

    /**
     * 流量平均值
     */
    private BigDecimal avgv;

    /**
     * 流量最大值
     */
    private BigDecimal maxv;

    /**
     * 流量最大值发生时间
     */
    private Date maxt;

    /**
     * 流量最小值
     */
    private BigDecimal minv;

    /**
     * 流量最小值发生时间
     */
    private Date mint;

    /**
     * s
     */
    private BigDecimal s;

    /**
     * avgs
     */
    private BigDecimal avgs;

    /**
     * maxs
     */
    private BigDecimal maxs;

    /**
     * mins
     */
    private BigDecimal mins;

    /**
     * span
     */
    private BigDecimal span;

    /**
     * dq
     */
    private BigDecimal dq;
}
