package com.debug.middleware.server.entity;/**
 * Created by Administrator on 2019/3/13.
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * 对象信息
 * @Author:debug (SteadyJack)
 * @Date: 2019/3/13 21:41
 **/
@Data
@ToString
public class User {
    private Integer id;
    private String userName;
    private String name;

    //空构造器与所有字段构造器
    public User() {
    }

    public User(Integer id, String userName, String name) {
        this.id = id;
        this.userName = userName;
        this.name = name;
    }
}





















