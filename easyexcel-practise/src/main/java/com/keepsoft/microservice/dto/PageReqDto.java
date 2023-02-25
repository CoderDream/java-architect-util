package com.keepsoft.microservice.dto;

import com.keepsoft.microservice.entity.DataSource;
import lombok.Data;

import java.util.List;

@Data
public class PageReqDto {

    private Long graphId;

    private Integer pageSize;

    private Integer pageNum;

    private String name;

    private Integer belongType;

    private Long belongId;

    private Long dataSourceId;

    private Integer relationType;

    private List<String> filterTableName;

    private DataSource dataSource;

    private Integer ontologyType;

    private Long ontologyId;

    private String columnName;

    private Integer taskType;

    private Integer status;
}
