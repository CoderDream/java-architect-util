package com.coderdream.easyexcelpractise.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@TableName(value = "t_id_map")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class IdMapEntity {
    private static final long serialVersionUID = -66879404475172690L;

    /**
     * 主键id
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /**
     * 属性条目全码
     */
    @TableField(value = "attr_item_full_code")
    private String attrItemFullCode;

    /**
     * 属性条目名称
     */
    @TableField(value = "attr_item_name")
    private String attrItemName;

    /**
     * 编号
     */
    @TableField(value = "send_id")
    private String sendId;

}
