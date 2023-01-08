package com.coderdream.demo.neo4j.entity;

import lombok.Data;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

//表示节点类型
@NodeEntity(label="User")
@Data
public class UserNode {

    @Id
    private Long nodeId;

    @Property
    private String userId;

    @Property
    private String name;

    @Property
    private int age;

}
