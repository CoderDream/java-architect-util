package com.example.demo.p01.dp04.builder.factory;

/**
 * 根据客户是否需要后院，创建不同的房子。
 */
public class Director {
    public House BuildHouse(boolean bBackyard) {
        if (bBackyard) {
            return new SingleFamilyHome();
        } else {
            return new Apartment();
        }
    }
}
