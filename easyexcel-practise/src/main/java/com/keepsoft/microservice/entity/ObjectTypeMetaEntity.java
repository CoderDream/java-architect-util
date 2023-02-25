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

@TableName(value = "OBJECT_TYPE_META_INFO")
@ApiModel("对象类型元数据信息")
@Data
@EqualsAndHashCode(callSuper = false)
public class ObjectTypeMetaEntity extends BaseEntity {
    private static final long serialVersionUID = -66879404475172690L;

    /**
     * 主键id
     */
    @ApiModelProperty("主键id")
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /**
     * 对象类型名称
     */
    @ApiModelProperty("对象类型名称")
    @TableField(value = "object_type_name")
    private String objectTypeName;

    /**
     * 对象类型标识
     */
    @ApiModelProperty("对象类型标识")
    @TableField(value = "object_type_label")
    private String objectTypeLabel;

    /**
     * 对象类型编码
     */
    @ApiModelProperty("对象类型编码")
    @TableField(value = "object_type_code")
    private String objectTypeCode;

    /**
     * 对象类型全码
     */
    @ApiModelProperty("对象类型全码")
    @TableField(value = "object_type_full_code")
    private String objectTypeFullCode;

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
     * 对象类型描述
     */
    @ApiModelProperty("对象类型描述")
    @TableField(value = "remark")
    private String remark;
}
