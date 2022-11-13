package com.coderdream.easyexcelpractise.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author zj
 * @Date 2022/10/8 20:28
 */
@TableName(value = "object_b_string",autoResultMap=true)
@Data
public class ObjectBStringEntity implements Serializable {
    private static final long serialVersionUID = -1242493306307174690L;

    /**
     * 属性条目全码
     */
    @ApiModelProperty("属性条目全码")
    @TableId(value = "attr_item_full_code")
    private String attrItemFullCode;

    /**
     * 值
     */
    @ApiModelProperty("值")
    @TableField(value = "value")
    private String value;

}
