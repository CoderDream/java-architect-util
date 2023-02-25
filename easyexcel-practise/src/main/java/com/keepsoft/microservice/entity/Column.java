package com.keepsoft.microservice.entity;

import lombok.Data;

import java.util.List;

@Data
//@NodeEntity(label = "column")
public class Column {

//    @Id
//    @GeneratedValue
    private Long id;

    //@Property(name = "name")
    private String name;

    //@Property(name = "columnType")
    private String columnType;

    //@Property(name = "createTime")
    private String createTime;

    //@Property(name = "updateTime")
    private String updateTime;

    //@Transient
    private Integer relationType;

    //@Transient
    private Long tableId;

    //@Transient
    private Long propertyId;

    //@Transient
    private String desc;

    //@Transient
    private Integer ontologyType;

    //@Transient
    private Long ontologyId;

    //@Transient
    private List<IProperty> mappedPropertyList;

    //@Transient
    private List<IProperty> propertyList;
}
