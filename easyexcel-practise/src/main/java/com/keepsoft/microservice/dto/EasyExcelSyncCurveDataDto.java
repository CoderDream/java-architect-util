package com.keepsoft.microservice.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 属性与关联表字段（预报数据）
 */
@Data
@ToString
public class EasyExcelSyncCurveDataDto extends EasyExcelSyncDataBaseDto {
    @ExcelProperty(value = "维度")
    private String columnDimLabel;

    @ExcelProperty(value = "V0名称")
    private String columnV0NameLabel;

    @ExcelProperty(value = "V1名称")
    private String columnV1NameLabel;

    @ExcelProperty(value = "V2名称")
    private String columnV2NameLabel;

    @ExcelProperty(value = "V3名称")
    private String columnV3NameLabel;

    @ExcelProperty(value = "测站编码")
    private String columnStcdLabel;

    @ExcelProperty(value = "V0")
    private String columnV0Label;

    @ExcelProperty(value = "V1")
    private String columnV1Label;

    @ExcelProperty(value = "V2")
    private String columnV2Label;

    @ExcelProperty(value = "V3")
    private String columnV3Label;

    @ExcelProperty(value = "所属表")
    private String tableName;

    @ExcelProperty(value = "表标识")
    private String tableLabel;
}
