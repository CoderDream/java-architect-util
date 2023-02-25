package com.keepsoft.microservice.entity;

import lombok.Data;

import java.util.List;

@Data
//@NodeEntity(label = "dataSource")
public class DataSource {

//    @Id
//    @GeneratedValue
    private Long id;

    //@Property(name = "type")
    private Integer type;

    //@Property(name = "identification")
    private String identification;

    //@Property(name = "instanceName")
    private String instanceName;

    //@Property(name = "ip")
    private String ip;

    //@Property(name = "port")
    private Integer port;

    //@Property(name = "username")
    private String username;

    //@Property(name = "password")
    private String password;

    //@Property(name = "createTime")
    private String createTime;

    //@Property(name = "updateTime")
    private String updateTime;

    //@Property(name = "dbName")
    private String dbName;

    //@Property(name = "enable")
    private Integer enable = 0;//默认可用

    //@Transient
    private Long graphId;

    //@Transient
    private String tableName;

    //@Transient
    private List<Table> dbTableList;
}
