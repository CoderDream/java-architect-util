package com.keepsoft.microservice.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.keepsoft.microservice.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@TableName(value = "attr_item_meta_info")
@ApiModel("属性条目元数据信息")
@Data
@EqualsAndHashCode(callSuper = false)
public class AttrItemMetaEntity extends BaseEntity {
    private static final long serialVersionUID = -66279404475172690L;

    /**
     * 主键id
     */
    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 属性条目名称
     */
    @ApiModelProperty("属性条目名称")
    @TableField(value = "attr_item_name")
    private String attrItemName;

    /**
     * 属性条目标识
     */
    @ApiModelProperty("属性条目标识")
    @TableField(value = "attr_item_label")
    private String attrItemLabel;

    /**
     * 属性条目编码
     */
    @ApiModelProperty("属性条目编码")
    @TableField(value = "attr_item_code")
    private String attrItemCode;

    /**
     * 属性条目全码
     */
    @ApiModelProperty("属性条目全码")
    @TableField(value = "attr_item_full_code")
    private String attrItemFullCode;

    /**
     * 数据类型
     */
    @ApiModelProperty("数据类型")
    @TableField(value = "data_type")
    private String dataType;

    /**
     * 修改时间
     */
    @ApiModelProperty("修改时间")
    @TableField(value = "update_time")
    private Date updateTime;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 属性条目描述
     */
    @ApiModelProperty("属性条目描述")
    @TableField(value = "remark")
    private String remark;

    /**
     * 属性条目所属对象的编码
     */
    @ApiModelProperty("属性条目所属对象的全码")
    @TableField(value = "object_full_code")
    private String objectFullCode;

    /**
     * 属性条目所属对象的名称
     */
    @ApiModelProperty("属性条目所属对象的名称")
    @TableField(exist = false)
    private String objectName;

    /**
     * 属性条目所属属性的编码
     */
    @ApiModelProperty("属性条目所属属性的全码")
    @TableField(value = "attr_full_code")
    private String attrFullCode;

    /**
     * 属性条目所属属性的名称
     */
    @ApiModelProperty("属性条目所属属性的名称")
    @TableField(exist = false)
    private String attrName;

    /**
     * 属性的算力级别，分为采集：collection、计算：calc、指标：key、决策：decision；
     */
    @ApiModelProperty("属性的算力级别")
    @TableField(value = "hash_rate_level")
    private String hashRateLevel;

    /**
     * 数据来源: info_system:系统来源；model:模型来源；literature: 文献来源；web:网络来源
     */
    @ApiModelProperty("数据来源")
    @TableField(value = "data_source")
    private String dataSource;

    /**
     * 属性类型所属结构类型
     */
    @ApiModelProperty("属性类型所属结构类型")
    @TableField(exist = false)
    private String structureTypeCode;

}
