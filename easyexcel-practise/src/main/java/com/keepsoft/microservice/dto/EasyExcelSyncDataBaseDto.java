package com.keepsoft.microservice.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 属性与关联表字段
 */
@Data
@ToString
public class EasyExcelSyncDataBaseDto {

    @ExcelProperty(value = "类型")
    private String objectTypeName;

    @ExcelProperty(value = "类型结构")
    private String structureTypeName;

    @ExcelProperty(value = "属性类型")
    private String attrTypeName;

    @ExcelProperty(value = "属性类型简码")
    private String attrTypeCode;

    @ExcelProperty(value = "属性")
    private String attrName;

    @ExcelProperty(value = "属性简码")
    private String attrCode;

    @ExcelProperty(value = "属性全码")
    private String attrFullCode;

    @ExcelProperty(value = "是否公共")
    private String commonFlag;

    @ExcelProperty(value = "英文标识")
    private String label;

    @ExcelProperty(value = "是否显示")
    private String displayFlag;

    @ExcelProperty(value = "数据类型")
    private String dataType;

    @ExcelProperty(index = 10, value = "备注")
    private String remark;
}
