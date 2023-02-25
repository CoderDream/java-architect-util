package com.keepsoft.microservice.entity;//package com.keepsoft.microservice.entity;
//
//import lombok.Data;
//import org.springframework.data.annotation.Transient;
//import org.springframework.data.neo4j.core.schema.GeneratedValue;
//import org.springframework.data.neo4j.core.schema.Id;
//import org.springframework.data.neo4j.core.schema.Property;
//
//
//@Data
////@NodeEntity(label = "graphDataBase")
//public class GraphDataBase {
//
//
//    @Id
//    @GeneratedValue
//    private Long id;
//
//    @Property(name = "type")
//    private Integer type;
//
//    @Property(name = "identification")
//    private String identification;
//
//    @Property(name = "instanceName")
//    private String instanceName;
//
//    @Property(name = "ip")
//    private String ip;
//
//    @Property(name = "port")
//    private Integer port;
//
//    @Property(name = "username")
//    private String username;
//
//    @Property(name = "password")
//    private String password;
//
//    @Property(name = "createTime")
//    private String createTime;
//
//    @Property(name = "updateTime")
//    private String updateTime;
//
//    @Transient
//    private Long graphId;
//}
