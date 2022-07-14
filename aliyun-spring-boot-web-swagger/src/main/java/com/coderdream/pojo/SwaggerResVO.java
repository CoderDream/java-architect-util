package com.coderdream.pojo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @className: SwaggerResVO
 * @description:
 * @author: sh.Liu
 * @date: 2022-03-22 19:20
 */
@Data
@ApiModel("创建Swagger响应结果")
public class SwaggerResVO {

    @ApiModelProperty("id")
    private Integer id;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("性别")
    private Integer gender;

    @ApiModelProperty("啥啥")
    private String what;
}
