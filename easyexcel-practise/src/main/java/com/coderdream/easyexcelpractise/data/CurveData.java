package com.coderdream.easyexcelpractise.data;

import com.alibaba.excel.metadata.data.CellData;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * 特征曲线
 *
 * @author Jiaju Zhuang
 **/
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class CurveData {

    /**
     * curveid
     */
    private CellData<String> curveid;

    /**
     * v0
     */
    private CellData<Double> v0;

    /**
     * v1
     */
    private CellData<Double> v1;

    /**
     * v2
     */
    private CellData<Double> v2;

    /**
     * v3
     */
    private CellData<Double> v3;

    /**
     * version
     */
    private CellData<String> version;

}
