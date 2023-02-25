package com.keepsoft.microservice.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("对象关系请求")
public class ObjectRelationReqDto implements Serializable {
    private static final long serialVersionUID = -66879324475172690L;

    /**
     * 上游站码
     */
    @ApiModelProperty("上游站码")
    private String upStcd;

    /**
     * 上游类型名称
     */
    @ApiModelProperty("上游类型名称")
    private String upObjectTypeName;

    /**
     * 对象类型全码
     */
    @ApiModelProperty("下游站码")
    private String dwStcd;

    /**
     * 下游类型名称
     */
    @ApiModelProperty("下游类型名称")
    private String dwObjectTypeName;
}
