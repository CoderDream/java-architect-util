package com.debug.middleware.server.rabbitmq.entity;/**
 * Created by Administrator on 2019/3/31.
 */

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Author:debug (SteadyJack)
 * @Date: 2019/3/31 15:09
 **/
@Data
@ToString
public class Person implements Serializable{

    private Integer id;
    private String name;
    private String userName;

    public Person() {
    }

    public Person(Integer id, String name, String userName) {
        this.id = id;
        this.name = name;
        this.userName = userName;
    }
}


























