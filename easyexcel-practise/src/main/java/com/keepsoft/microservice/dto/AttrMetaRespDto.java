package com.keepsoft.microservice.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@ApiModel("属性元数据信息响应数据传输对象")
public class AttrMetaRespDto implements Serializable {
    private static final long serialVersionUID = -66879324475172690L;

    /**
     * 主键id
     */
    @ApiModelProperty("主键id")
    private Integer id;

    /**
     * 属性名称
     */
    @ApiModelProperty("属性名称")
    private String attrName;

    /**
     * 属性标识
     */
    @ApiModelProperty("属性标识")
    private String attrLabel;

    /**
     * 属性编码
     */
    @ApiModelProperty("属性编码")
    private String attrCode;

    /**
     * 属性编码
     */
    @ApiModelProperty("属性全码")
    private String attrFullCode;

    /**
     * 数据类型
     */
    @ApiModelProperty("数据类型")
    private String dataType;

    /**
     * 公共标识，默认1公共，0私有
     */
    @ApiModelProperty("公共标识")
    private Integer commonFlag;

    /**
     * 显示标识，默认1显示，0隐藏
     */
    @ApiModelProperty("显示标识")
    private Integer displayFlag;

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
     * 属性描述
     */
    @ApiModelProperty("属性描述")
    private String remark;

    /**
     * 属性类型编码
     */
    @ApiModelProperty("属性类型编码")
    private String attrTypeCode;

    /**
     * 属性类型全码
     */
    @ApiModelProperty("属性类型全码")
    private String attrTypeFullCode;

    /**
     * 属性所属类型名称
     */
    @ApiModelProperty("属性所属类型名称")
    private String attrTypeName;

    /**
     * 属性类型所属结构类型，基础属性 or 数据属性
     */
    @ApiModelProperty("属性类型所属结构类型")
    private String structureTypeCode;

    /**
     * 属性所属类型名称
     */
    @ApiModelProperty("属性所属结构类型名称")
    private String structureTypeName;

    /**
     * 对象类型编码
     */
    @ApiModelProperty("对象类型编码")
    private String objectTypeCode;

    /**
     * 对象类型名称
     */
    @ApiModelProperty("对象类型名称")
    private String objectTypeName;

    /**
     * 对象类型全码
     */
    @ApiModelProperty("对象类型全码")
    private String objectTypeFullCode;

//    /**
//     * 对象编码
//     */
//    @ApiModelProperty("对象编码")
//    private String objectCode;
//
//
//    /**
//     * 对象名称
//     */
//    @ApiModelProperty("对象名称")
//    private String objectName;
//
//
//    /**
//     * 对象全码
//     */
//    @ApiModelProperty("对象全码")
//    private String objectFullCode;
}
