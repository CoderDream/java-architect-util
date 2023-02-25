package com.keepsoft.microservice.dto;

import lombok.Data;
import lombok.ToString;

/**
 * 增加编码，用于中间流转
 */
@Data
@ToString
public class ObjectInstanceDataExcelRespDto extends ObjectInstanceDataExcelDto {

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
     * 对象简码
     */
    private String objectCode;

}
