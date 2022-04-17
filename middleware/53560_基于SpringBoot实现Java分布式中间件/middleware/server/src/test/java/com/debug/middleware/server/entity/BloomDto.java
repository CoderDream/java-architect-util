package com.debug.middleware.server.entity;/**
 * Created by Administrator on 2019/4/28.
 */

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

/**
 * 用于布隆过滤器存储的对象
 * @Author:debug (SteadyJack)
 * @Date: 2019/4/28 20:41
 **/
@Data
@ToString
@EqualsAndHashCode
public class BloomDto implements Serializable{
    private Integer id; //id
    private String msg; //描述信息

    //空构造方法
    public BloomDto() {
    }
    //包含所有字段的构造方法
    public BloomDto(Integer id, String msg) {
        this.id = id;
        this.msg = msg;
    }
}