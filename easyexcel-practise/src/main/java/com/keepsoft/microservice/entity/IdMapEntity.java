package com.keepsoft.microservice.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * ID映射
 *
 * @Date 2022/12/22 14:28
 */
@TableName(value = "t_id_map", autoResultMap = true)
@Data
public class IdMapEntity implements Serializable {
    private static final long serialVersionUID = -1242493304307174690L;

    /**
     * 主键id
     */
    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 属性条目全码
     */
    @ApiModelProperty("属性条目全码")
    @TableField(value = "attr_item_full_code")
    private String attrItemFullCode;

    /**
     * 条目名称
     */
    @ApiModelProperty("条目名称")
    @TableField(value = "attr_item_name")
    private String attrItemName;

    /**
     * 系统来源
     */
    @ApiModelProperty("系统来源")
    @TableField(value = "data_type")
    private String dataType;

    /**
     * 条目名称
     */
    @ApiModelProperty("数据编码")
    @TableField(value = "send_id")
    private String sendId;

    /**
     * 创建人
     */
    @ApiModelProperty("创建人")
    @TableField(value = "create_user")
    private String createUser;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    @TableField(value = "create_time")
    private Date createTime;
}
