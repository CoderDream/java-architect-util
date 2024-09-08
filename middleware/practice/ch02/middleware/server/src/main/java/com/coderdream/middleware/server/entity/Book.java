package com.coderdream.middleware.server.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author:
 **/
@Data
@ApiModel
public class Book {
    @ApiModelProperty(value = "书籍编号",position = 1)
    private Integer bookNo;

    @ApiModelProperty(value = "书籍名称", position = 2)
    private String name;


    @ApiModelProperty(value = "f", position = 3)
    private String f;

    @ApiModelProperty(value = "e", position = 4)
    private String e;
}
