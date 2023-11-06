package com.coderdream.freeapps.util.excel.bigdata;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.format.NumberFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.Lists;

/**
 * @author dz
 * @date 2021-11-06 上午 9:14
 */
@Accessors(chain = true)
@Data
@Slf4j
public class ExcelBean {

    @ExcelProperty("主键id")
    private String id;

    @ExcelProperty("姓名")
    private String name;

    @ExcelProperty("地址")
    private String address;

    @ExcelProperty("年龄")
    private Integer age;

    @ExcelProperty("数量")
    private Integer number;

    @NumberFormat("#.##")
    @ExcelProperty("身高")
    private Double high;

    @ExcelProperty("距离")
    private Double distance;

    @DateTimeFormat("yyyy-MM-dd HH:mm:ss")
    @ExcelProperty("开始时间")
    private Date startTime;

    @ExcelProperty("结束时间")
    private Date endTime;


}
