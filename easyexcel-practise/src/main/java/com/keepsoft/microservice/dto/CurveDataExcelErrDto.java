package com.keepsoft.microservice.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class CurveDataExcelErrDto extends CurveDataExcelDto {

    /**
     *  错误提示
     */
    @ExcelProperty(index = 7,value = "错误提示")
    @ColumnWidth(50)
    private String errMsg;

}
