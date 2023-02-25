package com.keepsoft.microservice.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.keepsoft.microservice.easyexcel.ExcelPatternMsg;
import com.keepsoft.microservice.valid.ExcelValid;
import lombok.Data;

import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
public class CurveDataExcelDto implements Serializable {

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
    private String sendId;

    /**
     * V0
     */
    @ExcelProperty(index = 3, value = "V0")
    @ExcelValid(message = "V0值不能为空")
    @ColumnWidth(20)
    // @Length(max = 20)
    @Pattern(regexp = ExcelPatternMsg.DECIMAL,message = ExcelPatternMsg.DECIMAL_MSG)
    private String V0;

    /**
     * V1
     */
    @ExcelProperty(index = 4, value = "V1")
    @ExcelValid(message = "V1值不能为空")
    @ColumnWidth(20)
    // @Length(max = 20)
    @Pattern(regexp = ExcelPatternMsg.DECIMAL,message = ExcelPatternMsg.DECIMAL_MSG)
    private String V1;

    /**
     * V2
     */
    @ExcelProperty(index = 5, value = "V2")
    @ColumnWidth(20)
    // @Length(max = 20)
    @Pattern(regexp = ExcelPatternMsg.DECIMAL,message = ExcelPatternMsg.DECIMAL_MSG)
    private String V2;

    /**
     * V3
     */
    @ExcelProperty(index = 6, value = "V3")
    @ColumnWidth(20)
    // @Length(max = 20)
    @Pattern(regexp = ExcelPatternMsg.DECIMAL,message = ExcelPatternMsg.DECIMAL_MSG)
    private String V3;
}
