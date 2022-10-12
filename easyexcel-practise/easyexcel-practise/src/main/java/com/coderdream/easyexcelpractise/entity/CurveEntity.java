package com.coderdream.easyexcelpractise.entity;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@TableName(value = "object_b_curve")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class CurveEntity {
    private static final long serialVersionUID = -66879404475372690L;

    /**
     * 属性条目全码
     */
    @TableField(value = "attr_item_full_code")
    private String attrItemFullCode;

    /**
     * version
     */
    @TableField(value = "value", typeHandler = FastjsonTypeHandler.class)
    private JSONArray value;
}
