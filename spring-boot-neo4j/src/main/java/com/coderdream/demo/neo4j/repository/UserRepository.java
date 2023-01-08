package com.coderdream.demo.neo4j.repository;

import com.coderdream.demo.neo4j.entity.UserNode;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserRepository extends Neo4jRepository<UserNode, Long> {

    /**
     * 查询所有节点
     *
     * @return
     */
    @Query("MATCH (n:User) RETURN n ")
    List<UserNode> getUserNodeList();

    /**
     * 创建节点
     *
     * @param userId
     * @param name
     * @param age
     * @return
     */
    @Query("create (n:User{userId:$userId,age:$age,name:$name}) RETURN n ")
    List<UserNode> addUserNodeList(@Param("userId") String userId, @Param("name") String name, @Param("age") int age);
}
