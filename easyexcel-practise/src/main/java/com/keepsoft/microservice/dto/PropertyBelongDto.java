package com.keepsoft.microservice.dto;

import lombok.Data;

@Data
public class PropertyBelongDto {

    /**
     * 所属类型 0-实体,1-关系
     */
    private Integer belongType;

    private Long belongId;
}
