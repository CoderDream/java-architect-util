package com.keepsoft.microservice.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class CurveIndexReqDto implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 条目名称
     */
    @ExcelProperty(index = 0, value = "条目名称")
    private String attrItemName;

    /**
     * 条目编码
     */
    @ExcelProperty(index = 1, value = "条目编码")
    private String attrItemFullCode;

    /**
     * 维度
     */
    @ExcelProperty(index = 2, value = "维度")
    private String dim;

    /**
     * V0名称
     */
    @ExcelProperty(index = 3, value = "V0名称")
    private String v0Name;

    /**
     * V1名称
     */
    @ExcelProperty(index = 4, value = "V1名称")
    private String v1Name;

    /**
     * V2名称
     */
    @ExcelProperty(index = 5, value = "V2名称")
    private String v2Name;

    /**
     * V3名称
     */
    @ExcelProperty(index = 6, value = "V3名称")
    private String v3Name;
}
