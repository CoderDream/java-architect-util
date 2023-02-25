package com.keepsoft.microservice.entity;

import lombok.Data;

import java.util.List;

@Data
//@NodeEntity(label = "entity")
public class Entity {

//    @Id
//    @GeneratedValue
    private Long id;

    //@Property(name = "enName")
    private String enName;

    //@Property(name = "name")
    private String name;

    //@Property(name = "desc")
    private String desc;

    //@Property(name = "color")
    private String color;

    //@Property(name = "createTime")
    private String createTime;

    //@Property(name = "updateTime")
    private String updateTime;

    /**
     * 相当于图数据库节点的名字（实体对应的存放数据的库名）
     */
    //@Property(name = "labelName")
    private String labelName;

    //@Transient
    private Long graphId;

    //@Transient
    private Integer belongType = 0;

    //@Transient
    private Long primaryPropertyId;

    //@Transient
    private Long displayPropertyId;

    //@Transient
    private IProperty primaryProperty;

    //@Transient
    private IProperty displayProperty;

    //@Transient
    private List<Long> searchNoDisplayPropertyIdList;

    //@Transient
    private List<Long> searchDisplayPropertyIdList;

    //@Transient
    private List<IProperty> searchNoDisplayPropertyList;

    //@Transient
    private List<IProperty> searchDisplayPropertyList;

    //@Transient
    private List<Long> ids;

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
