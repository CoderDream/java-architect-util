package com.keepsoft.microservice.entity;

import lombok.Data;

import java.util.List;

@Data
//@NodeEntity(label = "graphSpace")
public class GraphSpace {

//    @Id
//    @GeneratedValue
    private Long id;

    //@Property(name = "name")
    private String name;

    //@Property(name = "enName")
    private String enName;

    //@Property(name = "desc")
    private String desc;

    //@Property(name = "industry")
    private String industry;

    //@Property(name = "ownerId")
    private Long ownerId;

    //@Property(name = "createTime")
    private String createTime;

    /**
     * 0-正常
     */
    //@Property(name = "status")
    private Integer status;

    //@Transient
    private List<Long> ids;

}
