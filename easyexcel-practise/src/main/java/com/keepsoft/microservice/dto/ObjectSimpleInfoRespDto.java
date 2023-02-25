package com.keepsoft.microservice.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("对象元数据信息返回数据传输对象")
public class ObjectSimpleInfoRespDto implements Serializable {
    private static final long serialVersionUID = -66879324472172690L;

    /**
     * 对象编码
     */
    @ApiModelProperty("对象全码")
    private String objectFullCode;


    /**
     * 测站编码
     */
    @ApiModelProperty("测站编码")
    private String stcd;
}
