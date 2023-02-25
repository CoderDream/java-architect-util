package com.keepsoft.microservice.dto;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ObjectBPointReqDto {
    /**
     * 主键id
     */
    @ApiModelProperty("主键id")
    private Integer id;

    /**
     * 属性条目全码
     */
    @ApiModelProperty("属性条目全码")
    private String attrItemFullCode;

    /**
     * 地理信息
     */
    @ApiModelProperty("地理信息")
    private JSONObject geometry;

    /**
     * 地理坐标
     */
    @ApiModelProperty("属性")
    private JSONObject properties;
}
