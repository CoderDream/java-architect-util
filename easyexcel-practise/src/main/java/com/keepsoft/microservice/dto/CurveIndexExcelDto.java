package com.keepsoft.microservice.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.keepsoft.microservice.valid.ExcelValid;
import lombok.Data;

import java.io.Serializable;

@Data
public class CurveIndexExcelDto implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 条目名称
     */
    @ExcelProperty(index = 0, value = "条目名称")
    @ColumnWidth(30)
    private String attrItemName;

    /**
     * 来源系统
     */
    @ExcelProperty(index = 1, value = "来源系统")
    @ColumnWidth(20)
    // @Length(max = 20)
    private String sourceSystem;

    /**
     * 源数据编码
     */
    @ExcelProperty(index = 2, value = "源数据编码")
    @ColumnWidth(20)
    // @Length(max = 20)
    private String sourceId;

    /**
     * 维度
     */
    @ExcelProperty(index = 3, value = "维度")
    @ColumnWidth(10)
    // @Length(max = 10)
    private String dim;

    /**
     * V0名称
     */
    @ExcelProperty(index = 4, value = "V0名称")
    @ExcelValid(message = "V0名称不能为空")
    @ColumnWidth(20)
    private String v0Name;

    /**
     * V1名称
     */
    @ExcelProperty(index = 5, value = "V1名称")
    @ExcelValid(message = "V1名称不能为空")
    @ColumnWidth(20)
    private String v1Name;

    /**
     * V2名称
     */
    @ExcelProperty(index = 6, value = "V2名称")
    @ColumnWidth(20)
    private String v2Name;

    /**
     * V3名称
     */
    @ExcelProperty(index = 7, value = "V3名称")
    @ColumnWidth(20)
    private String v3Name;
}
