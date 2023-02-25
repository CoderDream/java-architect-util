package com.keepsoft.microservice.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class AttrItemDataExcelRespDto extends AttrItemDataExcelDto {

    private static final long serialVersionUID = 2L;

    /**
     * 类型简码
     */
    private String objectTypeCode;

    /**
     * 类型全码
     */
    private String objectTypeFullCode;

    /**
     * 对象简码
     */
    private String objectCode;

    /**
     * 对象全码
     */
    private String objectFullCode;

    /**
     * 结构类型编码
     */
    private String structureTypeCode;

    /**
     * 属性类型简码
     */
    private String attrTypeCode;

    /**
     * 属性类型全码
     */
    private String attrTypeFullCode;

    /**
     * 属性简码
     */
    private String attrCode;

    /**
     * 属性全码
     */
    private String attrFullCode;

    /**
     * 属性条目简码
     */
    private String attrItemCode;

    /**
     * 属性条目全码
     */
    private String attrItemFullCode;

}
