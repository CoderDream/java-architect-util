package com.keepsoft.microservice.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class AttrItemDataExcelDto implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 属性条目
     */
    @ExcelProperty(index = 0, value = "属性条目")
    @ColumnWidth(30)
    private String attrItemName;

    /**
     * 属性条目简码
     */
    @ExcelProperty(index = 1, value = "属性条目简码")
    @ColumnWidth(30)
    private String attrItemCode;

    /**
     * 属性条目全码
     */
    @ExcelProperty(index = 2, value = "属性条目全码")
    @ColumnWidth(30)
    private String attrItemFullCode;

    /**
     * 英文标识
     */
    @ExcelProperty(index = 3, value = "英文标识")
    @ColumnWidth(30)
    private String attrItemLabel;

    /**
     * 类型
     */
    @ExcelProperty(index = 4, value = "类型")
    @ColumnWidth(30)
    private String objectTypeName;

    /**
     * 对象
     */
    @ExcelProperty(index = 5, value = "对象")
    @ColumnWidth(30)
    private String objectName;

    /**
     * 类型结构
     */
    @ExcelProperty(index = 6, value = "类型结构")
    @ColumnWidth(30)
    private String structureTypeName;

    /**
     * 属性类型
     */
    @ExcelProperty(index = 7, value = "属性类型")
    @ColumnWidth(20)
    // @Length(max = 20)
    private String attrTypeName;

    /**
     * 属性类型简码
     */
    @ExcelProperty(index = 8, value = "属性类型简码")
    @ColumnWidth(20)
    // @Length(max = 20)
    private String attrTypeCode;

    /**
     * 属性
     */
    @ExcelProperty(index = 9, value = "属性")
    @ColumnWidth(20)
    // @Length(max = 20)
    private String attrName;

    /**
     * 属性简码
     */
    @ExcelProperty(index = 10, value = "属性简码")
    @ColumnWidth(20)
    // @Length(max = 20)
    private String attrCode;

    /**
     * 数据类型
     */
    @ExcelProperty(index = 11, value = "数据类型")
    @ColumnWidth(20)
    private String dataType;

    /**
     * 算力层级
     */
    @ExcelProperty(index = 12, value = "算力层级")
    @ColumnWidth(20)
    // @Length(max = 20)
    private String hashRateLevel;

    /**
     * 数据来源
     */
    @ExcelProperty(index = 13, value = "数据来源")
    @ColumnWidth(20)
    // @Length(max = 20)
    private String dataSource;

    /**
     * 备注
     */
    @ExcelProperty(index = 14, value = "备注")
    @ColumnWidth(50)
    // @Length(max = 50)
    private String remark;

}
