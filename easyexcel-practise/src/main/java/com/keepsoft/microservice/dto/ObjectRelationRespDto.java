package com.keepsoft.microservice.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("对象关系响应")
public class ObjectRelationRespDto implements Serializable {
    private static final long serialVersionUID = -66879324475172690L;

    /**
     * 上游对象
     */
    @ApiModelProperty("上游对象")
    private ObjectSimpleInfoRespDto upObject;


    /**
     * 下游对象
     */
    @ApiModelProperty("下游对象")
    private ObjectSimpleInfoRespDto dwObject;

}
