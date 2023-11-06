package com.coderdream.freeapps.util.excel.bigdata;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class AttrItemExportExcelDto {

    private static final long serialVersionUID = 2L;

    /**
     * 1	属性条目
     */
    private String attrItemName;

    /**
     * 2	属性条目简码
     */
    private String attrItemCode;

    /**
     * 3	属性条目全码
     */
    private String attrItemFullCode;

    /**
     * 4	英文标识
     */
    private String attrItemLabel;

    /**
     * 5	类型
     */
    private String objectTypeName;

    /**
     * 6	对象名称
     */
    private String objectName;

    /**
     * 7	对象简码
     */
    private String objectCode;

    /**
     * 8	类型结构
     */
    private String structureTypeName;

    /**
     * 9	属性类型
     */
    private String attrTypeName;

    /**
     * 10	属性类型简码
     */
    private String attrTypeCode;

    /**
     * 11	属性
     */
    private String attrName;

    /**
     * 12	属性简码
     */
    private String attrCode;

    /**
     * 13	数据类型
     */
    private String dataType;

    /**
     * 14	算力层级
     */
    private String hashRateLevel;

    /**
     * 15	数据来源
     */
    private String dataSource;

    /**
     * 16	备注
     */
    private String remark;

}
