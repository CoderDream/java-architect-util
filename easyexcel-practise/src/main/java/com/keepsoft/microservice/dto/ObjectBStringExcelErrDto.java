package com.keepsoft.microservice.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class ObjectBStringExcelErrDto extends ObjectBStringExcelDto {

    /**
     *  错误提示
     */
    @ExcelProperty(index = 3,value = "错误提示")
    @ColumnWidth(50)
    private String errMsg;

}
