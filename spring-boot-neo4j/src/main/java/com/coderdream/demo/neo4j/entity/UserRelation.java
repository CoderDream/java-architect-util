package com.coderdream.demo.neo4j.entity;

import lombok.Data;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

//表示关系类型
@RelationshipEntity(type="UserRelation")
@Data
public class UserRelation {

    @Id
    private Long id;

    @StartNode
    private UserNode startNode;

    @EndNode
    private UserNode endNode;
}
