package com.coderdream.demo.neo4j.repository;

import com.coderdream.demo.neo4j.entity.UserRelation;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface UserRelationRepository extends Neo4jRepository<UserRelation,Long> {

    /**
     * 通过id 查询关系
     * @param firstUserId
     * @param secondUserId
     * @return
     */
    @Query("match p=(n:User)<-[r:FRIEND]->(n1:User) where n.userId=$firstUserId and n1.userId=$secondUserId return p")
    List<UserRelation> findUserRelationByEachId(@Param("firstUserId") String firstUserId, @Param("secondUserId") String secondUserId);

    /**
     * 添加关系
     * @param firstUserId
     * @param secondUserId
     * @return
     */
    @Query("match (fu:User),(su:User) where fu.userId=$firstUserId and su.userId=$secondUserId create p=(fu)-[r:FRIEND]->(su) return p")
    List<UserRelation> addUserRelation(@Param("firstUserId") String firstUserId, @Param("secondUserId") String secondUserId);

}

