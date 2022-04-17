package com.debug.middleware.server.entity;/**
 * Created by Administrator on 2019/3/16.
 */

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Author:debug (SteadyJack)
 * @Date: 2019/3/16 23:25
 **/
@Data
@ToString
public class Student implements Serializable{

    private String id;
    private String userName;
    private String name;

    public Student() {
    }

    public Student(String id, String userName, String name) {
        this.id = id;
        this.userName = userName;
        this.name = name;
    }
}