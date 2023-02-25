package com.keepsoft.microservice.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@ApiModel("类型元数据信息响应数据传输对象")
public class ObjectTypeTreeRespDto implements Serializable {
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
     * 类型类型名称
     */
    @ApiModelProperty("对象类型名称")
    private String objectTypeName;

    /**
     * 类型名称
     */
    @ApiModelProperty("对象名称")
    private String objectName;

    /**
     * 对象元数据列表
     */
    @ApiModelProperty("对象元数据列表")
    private List<ObjectRespDto> objectRespDtoList;

    @ApiModelProperty("树节点id")
    private String id;

    @ApiModelProperty("树节点 等级")
    private Integer nodeType;

    @ApiModelProperty("树节点名称")
    private String label;
}
