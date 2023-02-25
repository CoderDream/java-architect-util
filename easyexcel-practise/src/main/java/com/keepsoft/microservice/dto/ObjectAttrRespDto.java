package com.keepsoft.microservice.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@ApiModel("对象属性响应")
@Data
@EqualsAndHashCode(callSuper = false)
public class ObjectAttrRespDto extends BaseDto implements Serializable {
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

    /**
     * 对象类型
     */
    @ApiModelProperty("对象类型")
    private String dataType;

    /**
     * 属性值
     */
    @ApiModelProperty("属性值")
    private Object value;
//    private T value;
}
