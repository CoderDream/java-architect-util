package com.keepsoft.microservice.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ObjectCurveIndexRespDto {

    @ApiModelProperty("编号")
    private String objClass;

    @ApiModelProperty("编号")
    private String objName;

    @ApiModelProperty("编号")
    private String objAttrClassId;

    @ApiModelProperty("编号")
    private String objAttrClassSubD;

    @ApiModelProperty("编号")
    private String curveName;

    @ApiModelProperty("nid")
    private String attrItemFullCode;

    @ApiModelProperty("编号")
    private String pid;

    @ApiModelProperty("dim")
    private String dim;

    @ApiModelProperty("dimName")
    private String dimName;

}
