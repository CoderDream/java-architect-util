package com.debug.middleware.server.entity;/**
 * Created by Administrator on 2019/3/16.
 */

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Author:debug (SteadyJack)
 * @Date: 2019/3/16 23:26
 **/
@Data
@ToString
public class Fruit implements Serializable{

    private String name;
    private String color;

    public Fruit() {
    }

    public Fruit(String name, String color) {
        this.name = name;
        this.color = color;
    }
}
