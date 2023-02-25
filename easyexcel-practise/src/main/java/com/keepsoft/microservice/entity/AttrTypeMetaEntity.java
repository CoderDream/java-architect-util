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

@TableName(value = "attr_type_meta_info")
@ApiModel("属性类型元数据信息")
@Data
@EqualsAndHashCode(callSuper = false)
public class AttrTypeMetaEntity extends BaseEntity {
    private static final long serialVersionUID = -66879204475172690L;

    /**
     * 主键id
     */
    @ApiModelProperty("主键id")
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /**
     * 属性类型名称
     */
    @ApiModelProperty("属性类型名称")
    @TableField(value = "attr_type_name")
    private String attrTypeName;

    /**
     * 属性类型标识
     */
    @ApiModelProperty("属性类型标识")
    @TableField(value = "attr_type_label")
    private String attrTypeLabel;

    /**
     * 属性类型编码
     */
    @ApiModelProperty("属性类型编码")
    @TableField(value = "attr_type_code")
    private String attrTypeCode;

    /**
     * 属性类型全码
     */
    @ApiModelProperty("属性类型全码")
    @TableField(value = "attr_type_full_code")
    private String attrTypeFullCode;

    /**
     * 公共标识
     */
    @ApiModelProperty("公共标识")
    @TableField(value = "common_flag")
    private Integer commonFlag;

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
     * 属性类型描述
     */
    @ApiModelProperty("属性类型描述")
    @TableField(value = "remark")
    private String remark;

    /**
     * 对象类型全码
     */
    @ApiModelProperty("对象类型全码")
    @TableField(value = "object_type_full_code")
    private String objectTypeFullCode;

    /**
     * 属性类型所属结构类型，基础属性 or 数据属性
     */
    @ApiModelProperty("属性类型所属结构类型")
    @TableField(value = "structure_type_code")
    private String structureTypeCode;
}
