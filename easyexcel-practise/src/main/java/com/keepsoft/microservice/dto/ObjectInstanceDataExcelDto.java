package com.keepsoft.microservice.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class ObjectInstanceDataExcelDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 类型
     */
    @ExcelProperty(index = 0,value = "类型")
    @ColumnWidth(30)
    private String objectTypeName;

    /**
     * 对象名称
     */
    @ExcelProperty(index = 1,value = "对象名称")
    @ColumnWidth(20)
    // @Length(max = 20)
    private String objectName;

    /**
     * 对象全码
     */
    @ExcelProperty(index = 2,value = "对象码全码")
    @ColumnWidth(20)
    // @Length(max = 20)
    private String objectFullCode;

    /**
     * 对象标识
     */
    @ExcelProperty(index = 3,value = "英文标识")
    @ColumnWidth(20)
    // @Length(max = 20)
    private String objectLabel;

    /**
     * 备注
     */
    @ExcelProperty(index = 4,value = "备注")
    @ColumnWidth(50)
    // @Length(max = 50)
    private String remark;

}
