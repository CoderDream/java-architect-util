package com.keepsoft.microservice.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 相同属性下所有条目及值的列表
 */
@Data
@ApiModel("属性信息树响应DTO")
public class AttrItemTreeRespDto implements Serializable {
    private static final long serialVersionUID = -66879323475172690L;

    /**
     * 属性编码
     */
    @ApiModelProperty("属性编码")
    private String attrCode;

    /**
     * 属性编码
     */
    @ApiModelProperty("属性全码")
    private String attrFullCode;

    /**
     * 属性名称
     */
    @ApiModelProperty("属性名称")
    private String attrName;

    /**
     * 属性条目及值的列表
     */
    @ApiModelProperty("属性条目及值的列表")
    private List<AttrItemValueRespDto> attrItemList;

}
