package com.keepsoft.microservice.dto.os;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class BStringDto {
    private static final long serialVersionUID = -66879444375372690L;

    /**
     * 属性条目全码
     */
    private String attrItemFullCode;

    /**
     * 值
     */
    private String value;


}
