package com.keepsoft.microservice.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@ApiModel("对象类型信息响应DTO")
public class ObjectTypeInfoRespDto implements Serializable {
    private static final long serialVersionUID = -66879324475172690L;

    /**
     * 对象类型编码
     */
    @ApiModelProperty("对象类型编码")
    private String objectTypeCode;

    /**
     * 对象类型全码
     */
    @ApiModelProperty("对象类型全码")
    private String objectTypeFullCode;

    /**
     * 对象类型名称
     */
    @ApiModelProperty("对象类型名称")
    private String objectTypeName;


    /**
     * 对象列表
     */
    @ApiModelProperty("对象列表")
    private List<ObjectInfoRespDto> childList;

}
