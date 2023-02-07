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

@TableName(value = "t_fdll_hour2")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class AttrInfoEntity {
    /**
     * 属性or类型
     */
    private String attrCategory;

    /**
     * 名称
     */
    private String name;

    /**
     * 编码
     */
    private String code;

    /**
     * 标识
     */
    private String label;

    /**
     * 类型
     */
    private String dataType;

    /**
     * 是否公共
     */
    private String commonFlag;

    /**
     * 描述
     */
    private String remark;
}
