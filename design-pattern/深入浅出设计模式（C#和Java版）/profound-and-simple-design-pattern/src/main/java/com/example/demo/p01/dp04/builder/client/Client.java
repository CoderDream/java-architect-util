package com.example.demo.p01.dp04.builder.client;

import com.example.demo.p01.dp04.builder.factory.Director;
import com.example.demo.p01.dp04.builder.factory.House;

/**
 * 客户程序，通过调用Director的BuildHouse办法建造房屋,传入参数决定房屋类型:
 */
public class Client {
    public static void main(String[] argv) {
        Director director = new Director();
        House house = director.BuildHouse(false);
        System.out.println(house.Description());
    }
}
