package com.keepsoft.microservice.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class AttrDataExcelRespDto extends AttrDataExcelDto {

    private static final long serialVersionUID = 1L;

    /**
     * 类型简码
     */
    private String objectTypeCode;

    /**
     * 类型全码
     */
    private String objectTypeFullCode;

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
}
