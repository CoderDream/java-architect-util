package com.coderdream.easyexcelpractise.data.os;

import com.alibaba.excel.metadata.data.CellData;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 属性条目
 *
 * @author Jiaju Zhuang
 **/
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class ObjectBStringData {
    /**
     * 对象名称
     */
    private CellData<String> objectName;

    /**
     * 条目
     */
    private CellData<String> attrItemName;

    /**
     * 属性条目全码
     */
    private CellData<String> attrItemFullCode;

    /**
     * 值
     */
    private CellData<String> value;

}
