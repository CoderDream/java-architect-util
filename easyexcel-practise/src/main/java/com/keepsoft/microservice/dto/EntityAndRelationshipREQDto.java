package com.keepsoft.microservice.dto;

import lombok.Data;

@Data
public class EntityAndRelationshipREQDto {

    /**
     * 0-全部,1-只查实体
     */
    private Integer searchType;

    private Long graphId;
}
