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

@TableName(value = "attr_meta_info")
@ApiModel("属性元数据信息")
@Data
@EqualsAndHashCode(callSuper = false)
public class AttrMetaEntity extends BaseEntity {
    private static final long serialVersionUID = -66879404475172690L;

    /**
     * 主键id
     */
    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 属性名称
     */
    @ApiModelProperty("属性名称")
    @TableField(value = "attr_name")
    private String attrName;

    /**
     * 属性标识
     */
    @ApiModelProperty("属性标识")
    @TableField(value = "attr_label")
    private String attrLabel;

    /**
     * 属性编码
     */
    @ApiModelProperty("属性编码")
    @TableField(value = "attr_code")
    private String attrCode;

    /**
     * 属性编码
     */
    @ApiModelProperty("属性全码")
    @TableField(value = "attr_full_code")
    private String attrFullCode;

    /**
     * 数据类型
     */
    @ApiModelProperty("数据类型")
    @TableField(value = "data_type")
    private String dataType;

    /**
     * 公共标识，默认1公共，0私有
     */
    @ApiModelProperty("公共标识")
    @TableField(value = "common_flag")
    private Integer commonFlag;

    /**
     * 显示标识，默认1显示，0隐藏
     */
    @ApiModelProperty("显示标识")
    @TableField(value = "display_flag")
    private Integer displayFlag = 1;

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
     * 属性描述
     */
    @ApiModelProperty("属性描述")
    @TableField(value = "remark")
    private String remark;

    /**
     * 属性类型编码
     */
    @ApiModelProperty("属性类型编码")
    @TableField(value = "attr_type_full_code")
    private String attrTypeFullCode;

    /**
     * 属性所属类型名称
     */
    @ApiModelProperty("属性所属类型名称")
    @TableField(exist = false)
    private String attrTypeName;
}
