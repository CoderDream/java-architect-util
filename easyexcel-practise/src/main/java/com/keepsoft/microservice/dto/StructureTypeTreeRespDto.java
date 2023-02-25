package com.keepsoft.microservice.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@ApiModel("结构类型DTO")
public class StructureTypeTreeRespDto implements Serializable {
    private static final long serialVersionUID = -66879324475172690L;

    /**
     * 属性类型编码
     */
    @ApiModelProperty("结构类型编码")
    private String structureTypeCode;

    /**
     * 属性类型名称
     */
    @ApiModelProperty("结构类型名称")
    private String structureTypeName;

    /**
     * 属性列表
     */
    @ApiModelProperty("属性类型列表")
    private List<AttrTypeTreeRespDto> attrTypeRespDtoList;
}
