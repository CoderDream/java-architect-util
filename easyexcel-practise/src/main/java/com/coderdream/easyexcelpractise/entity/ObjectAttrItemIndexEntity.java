package com.coderdream.easyexcelpractise.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author ZJ
 * @date 2022/10/8 16:15
 */
@TableName(value = "object_attr_item_index")
@Data
public class ObjectAttrItemIndexEntity {
    /**
     * 主键id
     */
    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 对象全码
     */
    @ApiModelProperty("对象全码")
    @TableField(value = "object_full_code")
    private String objectFullCode;

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

}
