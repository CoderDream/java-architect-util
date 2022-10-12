package com.coderdream.easyexcelpractise.data;

import com.alibaba.excel.metadata.data.CellData;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * 基础数据类，这里的排序和excel里面的排序一致
 *
 * @author Jiaju Zhuang
 **/
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class SampleCellDataReadDemoData {
//    private CellData<String> string;
//
//    // 这里注意 虽然是日期 但是 类型 存储的是number 因为excel 存储的就是number
//    private CellData<Date> date;
//
//    private CellData<Double> doubleData;
//
//    // 这里并不一定能完美的获取 有些公式是依赖性的 可能会读不到 这个问题后续会修复
//    private CellData<String> formulaValue;


    /**
     * 编号
     */
    private CellData<String> senid;

    /**
     * 数据时间
     */
    private CellData<Date> time;

    /**
     * 流量值
     */
    private CellData<Double> v;

    /**
     * 流量平均值
     */
    private CellData<Double> avgv;

    /**
     * 流量最大值
     */
    private CellData<Double> maxv;

    /**
     * 流量最大值发生时间
     */
    private CellData<Date> maxt;

    /**
     * 流量最小值
     */
    private CellData<Double> minv;

    /**
     * 流量最小值发生时间
     */
    private CellData<Date> mint;

    /**
     * s
     */
    private CellData<Double> s;

    /**
     * avgs
     */
    private CellData<Double> avgs;

    /**
     * maxs
     */
    private CellData<Double> maxs;

    /**
     * mins
     */
    private CellData<Double> mins;

    /**
     * span
     */
    private CellData<Double> span;

    /**
     * dq
     */
    private CellData<Double> dq;
}
