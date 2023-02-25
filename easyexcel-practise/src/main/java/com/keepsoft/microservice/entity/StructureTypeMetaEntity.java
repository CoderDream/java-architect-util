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

@TableName(value = "structure_type_meta_info")
@ApiModel("结构元数据信息")
@Data
@EqualsAndHashCode(callSuper = false)
public class StructureTypeMetaEntity extends BaseEntity {
    private static final long serialVersionUID = -66879404475172690L;

    /**
     * 主键id
     */
    @ApiModelProperty("主键id")
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /**
     * 结构名称
     */
    @ApiModelProperty("结构名称")
    @TableField(value = "structure_type_name")
    private String structureTypeName;

    /**
     * 结构标识
     */
    @ApiModelProperty("结构标识")
    @TableField(value = "structure_type_label")
    private String structureTypeLabel;

    /**
     * 结构编码
     */
    @ApiModelProperty("结构编码")
    @TableField(value = "structure_type_code")
    private String structureTypeCode;

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
     * 结构描述
     */
    @ApiModelProperty("结构描述")
    @TableField(value = "remark")
    private String remark;
}
