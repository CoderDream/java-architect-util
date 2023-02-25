package com.keepsoft.microservice.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@ApiModel("对象属性条目索引请求")
@Data
@EqualsAndHashCode(callSuper = false)
public class ObjectAttrItemIndexReqDto extends BaseDto implements Serializable {
    private static final long serialVersionUID = -66873404475172690L;

    /**
     * 对象编码
     */
    @ApiModelProperty("对象全码")
    @NotNull(message = "对象全码不能为空")
    private String objectFullCode;

    /**
     * 属性条目全码
     */
    @ApiModelProperty("属性条目全码")
    @NotNull(message = "属性条目全码不能为空")
    private String attrItemFullCode;
}
