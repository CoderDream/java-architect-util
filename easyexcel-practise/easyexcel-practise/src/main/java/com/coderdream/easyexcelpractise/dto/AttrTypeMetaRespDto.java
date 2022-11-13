package com.coderdream.easyexcelpractise.dto;

import com.coderdream.easyexcelpractise.validator.group.AddGroup;
import com.coderdream.easyexcelpractise.validator.group.UpdateGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
@ApiModel("属性类型元数据信息响应数据传输对象")
public class AttrTypeMetaRespDto implements Serializable {
    private static final long serialVersionUID = -66879324475172690L;

    /**
     * 主键id
     */
    @ApiModelProperty("主键id")
    private Integer id;

    /**
     * 属性类型名称
     */
    @ApiModelProperty("属性类型名称")
    private String attrTypeName;

    /**
     * 属性类型标识
     */
    @ApiModelProperty("属性类型标识")
    private String attrTypeLabel;

    /**
     * 属性类型编码
     */
    @ApiModelProperty("属性类型编码")
    private String attrTypeCode;

    /**
     * 属性类型编码
     */
    @ApiModelProperty("属性类型全码")
    private String attrTypeFullCode;

    /**
     * 公共标识
     */
    @ApiModelProperty("公共标识")
    private Integer commonFlag;

    /**
     * 修改时间
     */
    @ApiModelProperty("修改时间")
    private Date updateTime;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private Date createTime;

    /**
     * 属性类型描述
     */
    @ApiModelProperty("属性类型描述")
    private String remark;

    /**
     * 对象类型全码
     */
    @ApiModelProperty("对象类型全码")
    @NotNull(message = "对象类型全码不能为空")
    private String objectTypeFullCode;

    /**
     * 对象类型名称
     */
    @ApiModelProperty("对象类型名称")
    @NotNull(message = "对象类型全码不能为空")
    private String objectTypeName;

    /**
     * 属性类型所属结构类型，基础属性 or 数据属性
     */
    @ApiModelProperty("属性类型所属结构类型")
    private String structureTypeCode;

    /**
     * 属性类型所属结构类型，基础属性 or 数据属性
     */
    @ApiModelProperty("属性类型所属结构类型")
    private String structureTypeName;
}
