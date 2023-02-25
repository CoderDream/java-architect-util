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

@TableName(value = "object_meta_info")
@ApiModel("对象元数据信息")
@Data
@EqualsAndHashCode(callSuper = false)
public class ObjectMetaEntity extends BaseEntity {
    private static final long serialVersionUID = -66879404475172690L;

    /**
     * 主键id
     */
    @ApiModelProperty("主键id")
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /**
     * 对象名称
     */
    @ApiModelProperty("对象名称")
    @TableField(value = "object_name")
    private String objectName;

    /**
     * 对象标识
     */
    @ApiModelProperty("对象标识")
    @TableField(value = "object_label")
    private String objectLabel;

    /**
     * 对象编码
     */
    @ApiModelProperty("对象编码")
    @TableField(value = "object_code")
    private String objectCode;

    /**
     * 对象编码
     */
    @ApiModelProperty("对象全码")
    @TableField(value = "object_full_code")
    private String objectFullCode;

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
     * 对象描述
     */
    @ApiModelProperty("对象描述")
    @TableField(value = "remark")
    private String remark;

    /**
     * 对象所属类型全码
     */
    @ApiModelProperty("对象所属类型全码")
    @TableField(value = "object_type_full_code")
    private String objectTypeFullCode;

    /**
     * 对象所属类型名称
     */
    @ApiModelProperty("对象所属类型名称")
    @TableField(exist = false)
    private String objectTypeName;

    /**
     * 对象的空间维度等级，分为L1、L2、L3
     */
    @ApiModelProperty("对象的空间维度等级")
    @TableField(value = "space_level")
    private String spaceLevel;
}
