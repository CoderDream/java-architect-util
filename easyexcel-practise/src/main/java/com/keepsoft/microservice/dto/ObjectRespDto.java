package com.keepsoft.microservice.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("对象DTO")
public class ObjectRespDto implements Serializable {
    private static final long serialVersionUID = -66879324475172690L;

    /**
     * 对象编码
     */
    @ApiModelProperty("对象编码")
    private String objectCode;

    /**
     * 对象全码
     */
    @ApiModelProperty("对象全码")
    private String objectFullCode;

    /**
     * 类型名称
     */
    @ApiModelProperty("对象名称")
    private String objectName;

    /**
     * 对象类型编码
     */
    @ApiModelProperty("对象类型编码")
    private String parentObjectCode;

    /**
     * 对象类型全码
     */
    @ApiModelProperty("对象类型全码")
    private String parentObjectFullCode;

    /**
     * 类型类型名称
     */
    @ApiModelProperty("对象类型名称")
    private String parentObjectName;

    @ApiModelProperty("树节点id")
    private String id;

    @ApiModelProperty("树节点 等级")
    private Integer nodeType;

    @ApiModelProperty("树节点名称")
    private String label;

    @ApiModelProperty("树节点 是否选中")
    private boolean checked = false;
}
