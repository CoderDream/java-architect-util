package com.keepsoft.microservice.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@TableName(value = "object_curve_data")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class ObjectCurveDataEntity {
    private static final long serialVersionUID = -66879404475372690L;

    /**
     * 属性条目全码
     */
    @TableField(value = "attr_item_full_code")
    private String attrItemFullCode;

    /**
     * V0
     */
    @TableField(value = "v0")
    private Double v0;

    /**
     * V1
     */
    @TableField(value = "v1")
    private Double v1;

    /**
     * V2
     */
    @TableField(value = "v2")
    private Double v2;

    /**
     * V3
     */
    @TableField(value = "v3")
    private Double v3;
}
