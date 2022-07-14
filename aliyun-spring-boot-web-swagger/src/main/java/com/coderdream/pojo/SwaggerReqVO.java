package com.coderdream.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @className: SwaggerReqVO
 * @description:
 * @author: sh.Liu
 * @date: 2022-03-22 19:19
 */
@Data
@ApiModel("创建Swagger请求参数")
public class SwaggerReqVO {

    @ApiModelProperty("id")
    private Integer id;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("性别")
    private Integer gender;
}
