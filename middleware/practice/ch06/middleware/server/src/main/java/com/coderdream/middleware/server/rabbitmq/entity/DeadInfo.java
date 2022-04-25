package com.coderdream.middleware.server.rabbitmq.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class DeadInfo implements Serializable {

    private Integer id;
    private String msg;

    public DeadInfo() {
    }

    public DeadInfo(Integer id, String msg) {
        this.id = id;
        this.msg = msg;
    }
}