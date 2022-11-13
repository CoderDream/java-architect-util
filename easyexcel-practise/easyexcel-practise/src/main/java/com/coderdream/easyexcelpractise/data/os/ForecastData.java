package com.coderdream.easyexcelpractise.data.os;

import com.alibaba.excel.metadata.data.CellData;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * 属性条目
 *
 * @author Jiaju Zhuang
 **/
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class ForecastData {
    /**
     * regId
     */
    private CellData<String> regId;

    /**
     * 依据时间（发出预报的时间）
     */
    private CellData<Date> forecastTime;

    /**
     * 预报时间（未来发生的时间）
     */
    private CellData<Date> futureTime;

    /**
     * 预报值
     */
    private CellData<Double> forecastValue;

}
