package com.debug.middleware.server.entity;/**
 * Created by Administrator on 2019/3/16.
 */

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 用户个人信息实体
 * @Author:debug (SteadyJack)
 * @Date: 2019/3/16 9:21
 **/
@Data
@ToString
public class Person implements Serializable{

    private Integer id;
    private Integer age;
    private String name;
    private String userName;
    private String location;

    public Person() {
    }

    public Person(Integer id, Integer age, String name, String userName, String location) {
        this.id = id;
        this.age = age;
        this.name = name;
        this.userName = userName;
        this.location = location;
    }
}























