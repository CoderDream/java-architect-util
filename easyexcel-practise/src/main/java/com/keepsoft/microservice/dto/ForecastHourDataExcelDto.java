package com.keepsoft.microservice.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.keepsoft.microservice.easyexcel.ExcelPatternMsg;
import com.keepsoft.microservice.valid.ExcelValid;
import lombok.Data;

import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
public class ForecastHourDataExcelDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 条目名称
     */
    @ExcelProperty(index = 0,value = "条目名称")
    @ColumnWidth(30)
    private String attrItemName;

    /**
     * 条目编码
     */
    @ExcelProperty(index = 1,value = "条目编码")
    @ColumnWidth(20)
    // @Length(max = 20)
    private String attrItemFullCode;

    /**
     * 步长
     */
    @ExcelProperty(index = 2,value = "步长")
    @ColumnWidth(4)
    // @Length(max = 2)
    private String stepLength;

    /**
     * 依据时间
     */
    @ExcelProperty(index = 3,value = "依据时间")
    @ColumnWidth(20)
//    // @Length(max = 20)
//    @Pattern(regexp = ExcelPatternMsg.DATE_TIME1,message = ExcelPatternMsg.DATE_TIME1_MSG)
    private String forecastTime;

    /**
     * 时间
     */
    @ExcelProperty(index = 4,value = "时间")
    @ColumnWidth(20)
//    // @Length(max = 20)
//    @Pattern(regexp = ExcelPatternMsg.DATE_TIME1,message = ExcelPatternMsg.DATE_TIME1_MSG)
    private String futureTime;

    /**
     * 值
     */
    @ExcelProperty(index = 5,value = "值")
    @ExcelValid(message = "值不能为空")
    @ColumnWidth(50)
    @Pattern(regexp = ExcelPatternMsg.DECIMAL,message = ExcelPatternMsg.DECIMAL_MSG)
    private String value;
}
