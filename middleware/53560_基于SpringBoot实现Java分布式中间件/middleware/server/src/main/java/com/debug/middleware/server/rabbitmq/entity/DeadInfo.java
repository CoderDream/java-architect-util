package com.debug.middleware.server.rabbitmq.entity;/**
 * Created by Administrator on 2019/4/9.
 */

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Author:debug (SteadyJack)
 * @Date: 2019/4/9 23:18
 **/
@Data
@ToString
public class DeadInfo implements Serializable{

    private Integer id;
    private String msg;

    public DeadInfo() {
    }

    public DeadInfo(Integer id, String msg) {
        this.id = id;
        this.msg = msg;
    }
}