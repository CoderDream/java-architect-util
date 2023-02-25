package com.keepsoft.microservice.entity;

import com.keepsoft.microservice.dto.PropertyBelongDto;
import lombok.Data;

import java.util.List;

@Data
//@NodeEntity(label = "property")
public class IProperty {

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

    //@Property(name = "type")
    private Integer type;

    //@Property(name = "createTime")
    private String createTime;

    //@Property(name = "updateTime")
    private String updateTime;

    //@Transient
    private List<PropertyBelongDto> belong;

    //@Transient
    private Long graphId;

    //@Transient
    private List<Entity> entityList;

    //@Transient
    private List<Relationship> relationshipList;

    //@Transient
    private List<Long> ids;

    //@Transient
    private boolean mapped;

    //@Transient
    private Integer relationType;

    //@Transient
    private boolean primary;

    //@Transient
    private Boolean checked;

    //@Transient
    private Long parentId;

    //@Transient
    private Integer index;

    //@Transient
    private Column column;
}
