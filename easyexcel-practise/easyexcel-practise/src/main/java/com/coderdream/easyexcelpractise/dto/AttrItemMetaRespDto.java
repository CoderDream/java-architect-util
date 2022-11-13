package com.coderdream.easyexcelpractise.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("属性条目元数据信息响应DTO")
public class AttrItemMetaRespDto extends BaseDto implements Serializable {
    private static final long serialVersionUID = -66879324475172690L;

    /**
     * 主键id
     */
    @ApiModelProperty("主键id")
    private Integer id;

    /**
     * 属性条目名称
     */
    @ApiModelProperty("属性条目名称")
    private String attrItemName;

    /**
     * 属性条目标识
     */
    @ApiModelProperty("属性条目标识")
    private String attrItemLabel;

    /**
     * 属性条目编码
     */
    @ApiModelProperty("属性条目编码")
    private String attrItemCode;

    /**
     * 属性条目全码
     */
    @ApiModelProperty("属性条目全码")
    private String attrItemFullCode;

    /**
     * 属性编码
     */
    @ApiModelProperty("属性编码")
    private String attrCode;

    /**
     * 属性条目编码
     */
    @ApiModelProperty("属性全码")
    private String attrFullCode;

    /**
     * 属性条目所属属性的名称
     */
    @ApiModelProperty("属性条目所属属性的名称")
    private String attrName;


    /**
     * 属性条目所属属性的数据类型
     */
    @ApiModelProperty("属性条目所属属性的数据类型")
    private String dataType;

    /**
     * 对象类型编码
     */
    @ApiModelProperty("对象类型编码")
    private String objectCode;

    /**
     * 对象名称
     */
    @ApiModelProperty("对象名称")
    private String objectName;

    /**
     * 对象类型全码
     */
    @ApiModelProperty("对象类型全码")
    private String objectFullCode;

    /**
     * 属性条目所属对象的编码
     */
    @ApiModelProperty("属性条目所属对象的编码")
    private String[] objectFullCodes;

    /**
     * 对象类型编码
     */
    @ApiModelProperty("对象类型编码")
    private String objectTypeCode;

    /**
     * 对象类型全码
     */
    @ApiModelProperty("对象类型全码")
    private String objectTypeFullCode;

    /**
     * 对象类型名称
     */
    @ApiModelProperty("对象类型名称")
    private String objectTypeName;

    /**
     * 属性条目所属属性的编码
     */
    @ApiModelProperty("属性条目所属属性类型的编码")
    private String attrTypeFullCode;

    /**
     * 属性条目所属属性的编码
     */
    @ApiModelProperty("属性条目所属属性的编码")
    private String[] attrFullCodes;

    /**
     * 属性条目所属属性的名称
     */
    @ApiModelProperty("属性条目所属属性类型的名称")
    private String attrTypeName;

    /**
     * 属性条目所属属性类型的编码列表
     */
    @ApiModelProperty("属性条目所属属性类型的编码列表")
    private String[] attrTypeFullCodes;

    /**
     * 属性的算力级别，分为采集：collection、计算：calc、指标：key、决策：decision；
     */
    @ApiModelProperty("属性的算力级别")
    private String hashRateLevel;

    /**
     * 数据来源: info_system:系统来源；model:模型来源；literature: 文献来源；web:网络来源
     */
    @ApiModelProperty("数据来源")
    private String dataSource;

    /**
     * 属性类型所属的类型结构编码
     */
    @ApiModelProperty("属性类型所属的类型结构编码")
    private String structureTypeCode;

    /**
     * 属性类型所属的类型结构名称
     */
    @ApiModelProperty("属性类型所属的类型结构名称")
    private String structureTypeName;

    /**
     * 属性条目描述
     */
    @ApiModelProperty("属性条目描述")
    private String remark;
}
