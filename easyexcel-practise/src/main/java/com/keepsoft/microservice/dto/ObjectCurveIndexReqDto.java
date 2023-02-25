package com.keepsoft.microservice.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ObjectCurveIndexReqDto {

    @ApiModelProperty("编号")
    private String objClass;

    @ApiModelProperty("编号")
    private String objName;

    @ApiModelProperty("编号")
    private String objAttrClassId;

    @ApiModelProperty("编号")
    private String objAttrClassSubD;

    @ApiModelProperty("曲线名称（条目名称）")
    private String curveName;

    @ApiModelProperty("条目全码")
    private String attrItemFullCode;

    @ApiModelProperty("编号")
    private String pid;

    @ApiModelProperty("维度")
    private String dim;

    @ApiModelProperty("维度信息详情")
    private String dimName;

}
