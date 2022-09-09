package com.chen.controller;

import com.chen.entity.User;
import com.chen.entity.UserEntity;
import com.chen.mapper.db1.Db1UserMapper;
import com.chen.mapper.db2.Db2UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author chenouba
 * @create 2021-06-11 16:51
 */
@RestController
public class UserController {

    @Resource
    Db1UserMapper db1UserMapper;

    @Resource
    Db2UserMapper db2UserMapper;

    /**
     * 通过ID查询User(Mysql数据库)
     * @return
     */
    @RequestMapping("/getUser")
    public User getUser(){
        return db1UserMapper.getUser(1);
    }

    /**
     * 通过ID查询User(GP数据库)
     * @return
     */
    @RequestMapping("/getUserById")
    public List<UserEntity> getUserbyId(){
        return db2UserMapper.getUserbyId(1L);
    }
}
