package com.coderdream.easyexcelpractise.dto;

import com.coderdream.easyexcelpractise.validator.group.AddGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@ApiModel("对象属性请求")
@Data
@EqualsAndHashCode(callSuper = false)
public class ObjectAttrReqDto implements Serializable {
    private static final long serialVersionUID = -66873404475172690L;

    /**
     * 对象编码
     */
    @ApiModelProperty("对象全码")
    @NotNull(message = "对象全码不能为空")
    private String objectFullCode;

    /**
     * 属性类型全码
     */
    @ApiModelProperty("属性类型全码")
    private String attrTypeFullCode;

    /**
     * 结构类型
     */
    @ApiModelProperty("结构类型")
    private String structureTypeCode;

    /**
     * 属性条目全码
     */
    @ApiModelProperty("属性条目全码")
    @NotNull(message = "属性条目全码不能为空", groups = {AddGroup.class})
    private String attrItemFullCode;
}
