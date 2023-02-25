package com.keepsoft.microservice.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CurveIndexExcelErrDto extends CurveIndexExcelDto {

    /**
     * 错误提示
     */
    @ExcelProperty(index = 8, value = "错误提示")
    @ColumnWidth(50)
    private String errMsg;

}
