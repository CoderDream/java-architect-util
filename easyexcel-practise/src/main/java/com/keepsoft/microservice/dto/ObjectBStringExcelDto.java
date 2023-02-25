package com.keepsoft.microservice.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.keepsoft.microservice.valid.ExcelValid;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

@Data
public class ObjectBStringExcelDto implements Serializable {

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
    @ColumnWidth(30)
    @Length(max = 20)
    private String attrItemFullCode;

    /**
     * 值
     */
    @ExcelProperty(index = 2,value = "值")
    @ExcelValid(message = "值不能为空")
    @ColumnWidth(50)
    private String value;
}
