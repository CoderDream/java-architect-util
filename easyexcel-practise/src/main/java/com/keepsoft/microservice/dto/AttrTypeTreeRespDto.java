package com.keepsoft.microservice.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@ApiModel("属性类型DTO")
public class AttrTypeTreeRespDto implements Serializable {
    private static final long serialVersionUID = -66879324475172690L;

    /**
     * 属性类型编码
     */
    @ApiModelProperty("属性类型编码")
    private String attrTypeCode;

    /**
     * 属性类型全码
     */
    @ApiModelProperty("属性类型全码")
    private String attrTypeFullCode;

    /**
     * 属性类型名称
     */
    @ApiModelProperty("属性类型名称")
    private String attrTypeName;

    /**
     * 属性列表
     */
    @ApiModelProperty("属性列表")
    private List<AttrRespDto> attrMetaRespDtoList;
}
