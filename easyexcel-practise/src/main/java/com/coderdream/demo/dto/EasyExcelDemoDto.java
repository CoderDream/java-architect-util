package com.coderdream.demo.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class EasyExcelDemoDto {

    @ExcelProperty(value = "姓名")
    private String name;

    @ExcelProperty(value = "性别")
    private String sex;

    @ExcelProperty(value = "年龄")
    private int age;
}
