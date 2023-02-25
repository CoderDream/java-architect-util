package com.keepsoft.microservice.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 属性与关联表字段（预报数据）
 */
@Data
@ToString
public class EasyExcelSyncForecastDataDto extends EasyExcelSyncDataBaseDto {

    @ExcelProperty(value = "测站编码")
    private String columnStcdLabel;

    @ExcelProperty(value = "步长")
    private String columnStepLengthLabel;

    @ExcelProperty(value = "依据时间")
    private String columnForecastTimeLabel;

    @ExcelProperty(value = "时间")
    private String columnTmLabel;

    @ExcelProperty(value = "值")
    private String columnValueLabel;

    @ExcelProperty(value = "所属表")
    private String tableName;

    @ExcelProperty(value = "表标识")
    private String tableLabel;
}
