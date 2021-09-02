package com.coderdream.bean;

import com.coderdream.Excel;
import lombok.Data;

/**
 * @author ：CoderDream
 * @date ：Created in 2021/9/2 17:27
 * @description：
 * @modified By：CoderDream
 * @version: $
 */
@Data
public class User {

    @Excel(name = "用户id")
    private Integer id;

    @Excel(name = "用户姓名")
    private String name;

    @Excel(name = "性别",readConverterExp = "2=男,1=女,0=未知")
    private Integer sex;
}
