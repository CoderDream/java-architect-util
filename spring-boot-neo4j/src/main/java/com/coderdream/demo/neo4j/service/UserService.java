package com.coderdream.demo.neo4j.service;

import com.coderdream.demo.neo4j.entity.UserNode;
import com.coderdream.demo.neo4j.entity.UserRelation;
import com.coderdream.demo.neo4j.repository.UserRelationRepository;
import com.coderdream.demo.neo4j.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {

    @Resource
    private UserRepository userRepository;

    @Resource
    private UserRelationRepository userRelationRepository;

    public void addUserNode(UserNode userNode) {
        userRepository.addUserNodeList(userNode.getUserId(),userNode.getName(), userNode.getAge());
    }

    public void addRelationship(String firstId,String secondId){
        userRelationRepository.addUserRelation(firstId,secondId);
    }

    public List<UserRelation> findUserRelationByEachId(String firstId, String secondId){
        return userRelationRepository.findUserRelationByEachId(firstId, secondId);
    }
}
