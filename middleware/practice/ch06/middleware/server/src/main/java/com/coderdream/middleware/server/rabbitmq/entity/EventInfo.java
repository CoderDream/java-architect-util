package com.coderdream.middleware.server.rabbitmq.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 对象实体信息
 *
 * @Author:debug (SteadyJack)
 * @Date: 2019/3/31 21:50
 **/
@Data
@ToString
public class EventInfo implements Serializable {

    private Integer id;
    private String module;
    private String name;
    private String desc;

    public EventInfo() {
    }

    public EventInfo(Integer id, String module, String name, String desc) {
        this.id = id;
        this.module = module;
        this.name = name;
        this.desc = desc;
    }
}