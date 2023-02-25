package com.keepsoft.microservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class CurveDataReqDto {
    private static final long serialVersionUID = -66879404475372690L;

    /**
     * 属性条目全码
     */
    private String attrItemFullCode;

    /**
     * V0
     */
    private String v0;

    /**
     * V1
     */
    private String v1;

    /**
     * V2
     */
    private String v2;

    /**
     * V3
     */
    private String v3;
}
