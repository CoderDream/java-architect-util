package com.keepsoft.microservice.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("属性DTO")
public class AttrRespDto implements Serializable {
    private static final long serialVersionUID = -66879324475172690L;

    /**
     * 属性编码
     */
    @ApiModelProperty("属性编码")
    private String attrCode;

    /**
     * 属性全码
     */
    @ApiModelProperty("属性全码")
    private String attrFullCode;

    /**
     * 属性名称
     */
    @ApiModelProperty("属性名称")
    private String attrName;
}
