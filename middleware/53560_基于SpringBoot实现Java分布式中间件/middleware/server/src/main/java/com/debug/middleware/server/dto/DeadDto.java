package com.debug.middleware.server.dto;/**
 * Created by Administrator on 2019/5/2.
 */

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 用于充当redisson死信队列中的消息
 * @Author:debug (SteadyJack)
 * @Date: 2019/5/2 17:21
 **/
@Data
@ToString
public class DeadDto implements Serializable{
    private Integer id;
    private String name;
    //空的构造方法
    public DeadDto() {
    }
    //包含所有字段的构造方法
    public DeadDto(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}