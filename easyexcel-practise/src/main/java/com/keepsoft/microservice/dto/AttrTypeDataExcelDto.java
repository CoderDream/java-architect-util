package com.keepsoft.microservice.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class AttrTypeDataExcelDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 类型
     */
    @ExcelProperty(index = 0, value = "类型")
    @ColumnWidth(6)
    private String objectTypeName;

    /**
     * 类型结构
     */
    @ExcelProperty(index = 1, value = "类型结构")
    @ColumnWidth(8)
    private String structureTypeName;

    /**
     * 属性类型
     */
    @ExcelProperty(index = 2, value = "属性类型")
    @ColumnWidth(20)
    // @Length(max = 20)
    private String attrTypeName;

    /**
     * 属性类型简码
     */
    @ExcelProperty(index = 3, value = "属性类型简码")
    @ColumnWidth(10)
    // @Length(max = 20)
    private String attrTypeCode;

    /**
     * 属性类型全码
     */
    @ExcelProperty(index = 4, value = "属性类型全码")
    @ColumnWidth(20)
    // @Length(max = 20)
    private String attrTypeFullCode;

    /**
     * 是否公共
     */
    @ExcelProperty(index = 5, value = "是否公共")
    @ColumnWidth(5)
    // @Length(max = 20)
    private String commonFlag;

    /**
     * 对象标识
     */
    @ExcelProperty(index = 6, value = "英文标识")
    @ColumnWidth(20)
    // @Length(max = 20)
    private String attrTypeLabel;

    /**
     * 备注
     */
    @ExcelProperty(index = 7, value = "备注")
    @ColumnWidth(50)
    // @Length(max = 50)
    private String remark;

}
