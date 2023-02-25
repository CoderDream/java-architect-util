package com.keepsoft.microservice.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 属性与关联表字段
 */
@Data
@ToString
public class EasyExcelSyncBasicDataDto extends EasyExcelSyncDataBaseDto {

    @ExcelProperty(value = "表字段名")
    private String columnName;

    @ExcelProperty(value = "字段标识")
    private String columnLabel;

    @ExcelProperty(value = "所属表")
    private String tableName;

    @ExcelProperty(value = "表标识")
    private String tableLabel;

    @ExcelProperty(index = 15, value = "备注")
    private String comment;

}
