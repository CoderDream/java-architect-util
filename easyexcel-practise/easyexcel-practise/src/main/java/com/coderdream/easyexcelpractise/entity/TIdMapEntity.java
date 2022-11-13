package com.coderdream.easyexcelpractise.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author zj
 * @Date 2022/10/13 14:23
 */
@TableName(value = "t_id_map",autoResultMap=true)
@Data
public class TIdMapEntity {

    /**
     * id
     */
    @ApiModelProperty("id")
    @TableField(value = "id")
    private Integer id;

    /**
     * 属性条目全码
     */
    @ApiModelProperty("attr_item_full_code")
    @TableField(value = "attr_item_full_code")
    private String attrItemFullCode;

    /**
     * 属性条目名称
     */
    @ApiModelProperty("attr_item_name")
    @TableField(value = "attr_item_name")
    private String attrItemName;

    /**
     * sendId
     */
    @ApiModelProperty("send_id")
    @TableField(value = "send_id")
    private String sendId;

}
