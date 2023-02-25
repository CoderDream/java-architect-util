package com.keepsoft.microservice.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@ApiModel("对象属性条目索引响应")
@Data
@EqualsAndHashCode(callSuper = false)
public class ObjectAttrItemIndexRespDto extends BaseDto implements Serializable {
    private static final long serialVersionUID = -66873404475172690L;

    /**
     * 对象编码
     */
    @ApiModelProperty("对象全码")
    private String objectFullCode;

    /**
     * 属性条目全码
     */
    @ApiModelProperty("属性条目全码")
    private String attrItemFullCode;
}
