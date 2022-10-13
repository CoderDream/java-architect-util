package com.coderdream.easyexcelpractise.data;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 基础数据类.这里的排序和excel里面的排序一致
 *
 * @author Jiaju Zhuang
 **/
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class AttrInfoData {
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
