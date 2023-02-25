package com.keepsoft.microservice.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@ApiModel("对象属性响应")
@Data
@EqualsAndHashCode(callSuper = false)
public class ObjectCurveDataRespDto {

    @ApiModelProperty("属性条目全码")
    private String attrItemFullCode;

    @ApiModelProperty("V0")
    private String v0;

    @ApiModelProperty("V1")
    private String v1;

    @ApiModelProperty("V2")
    private String v2;

    @ApiModelProperty("V3")
    private String v3;

    /**
     * 维度
     */
    @ApiModelProperty("维度")
    private String dimension;

    /**
     * 维度名称
     */
    @ApiModelProperty("维度名称")
    private String dimensionName;

}
