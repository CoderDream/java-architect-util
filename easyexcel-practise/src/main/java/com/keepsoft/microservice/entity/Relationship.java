package com.keepsoft.microservice.entity;

import lombok.Data;

import java.util.List;

@Data
//@NodeEntity(label = "relationship")
public class Relationship {

//    @Id
//    @GeneratedValue
    private Long id;

    //@Property(name = "name")
    private String name;

    //@Property(name = "enName")
    private String enName;

    //@Property(name = "desc")
    private String desc;

    //@Property(name = "weight")
    private Integer weight;

    //@Property(name = "createTime")
    private String createTime;

    //@Property(name = "updateTime")
    private String updateTime;

    //@Property(name = "color")
    private String color;

    //@Property(name = "rsName")
    private String rsName;

    //@Transient
    private Long sourceEntityId;

    //@Transient
    private Long targetEntityId;

    //@Transient
    private Integer belongType = 1;

    //@Transient
    private Long graphId;

    //@Transient
    private Long primaryPropertyId;

    //@Transient
    private List<Long> noDisplayPropertyIdList;

    //@Transient
    private List<Long> displayPropertyIdList;

    //@Transient
    private Entity sourceEntity;

    //@Transient
    private Entity targetEntity;

    //@Transient
    private IProperty primaryProperty;

    //@Transient
    private List<IProperty> noDisplayPropertyList;

    //@Transient
    private List<IProperty> displayPropertyList;

    //@Transient
    private List<Long> ids;

    //@Transient
    private IProperty sourcePrimaryProperty;

    //@Transient
    private IProperty targetPrimaryProperty;

    //@Transient
    private List<IProperty> hasMappedPropertyList;

    //@Transient
    private Table table;

    //@Transient
    private DataSource dataSource;

    //@Transient
    private List<IProperty> propertyList;

    //@Transient
    private String filter;

    //@Transient
    private Integer index;
}
