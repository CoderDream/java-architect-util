package com.coderdream.demo.neo4j.controller;

import com.coderdream.demo.neo4j.entity.UserNode;
import com.coderdream.demo.neo4j.entity.UserRelation;
import com.coderdream.demo.neo4j.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import org.springframework.data.neo4j.annotation.Query;

@RestController
public class Neo4jController {

    @Resource
    private UserService userService;

    @RequestMapping("/saveUser")
    public String saveUserNode() {

        UserNode node = new UserNode();
        node.setNodeId(1l);
        node.setUserId("2");
        node.setName("赵六");
        node.setAge(26);

        userService.addUserNode(node);
        return "success";
    }

    @RequestMapping("/saveUser5")
    public String saveUserNode5() {

        UserNode node = new UserNode();
        node.setNodeId(2l);
        node.setUserId("1");
        node.setName("王五");
        node.setAge(22);

        userService.addUserNode(node);
        return "success";
    }



    @RequestMapping("/saveShip")
    public String saveShip() {
        userService.addRelationship("1", "2");
        return "success";
    }

    @RequestMapping("/initRelation")
    public List<UserRelation>  initRelation() {

        UserNode userNode3 = new UserNode();
        userNode3.setNodeId(3l);
        userNode3.setUserId("3");
        userNode3.setName("张三");
        userNode3.setAge(26);

        userService.addUserNode(userNode3);

        UserNode userNode4 = new UserNode();
        userNode4.setNodeId(4l);
        userNode4.setUserId("4");
        userNode4.setName("李四");
        userNode4.setAge(22);

        userService.addUserNode(userNode4);

        userService.addRelationship("3", "4");


        List<UserRelation> relations =  userService.findUserRelationByEachId("3", "4");
        return relations;
    }

    @RequestMapping("/findShip")
    public List<UserRelation> findShip() {
        List<UserRelation> relations =  userService.findUserRelationByEachId("1", "2");
        return relations;
    }

}
