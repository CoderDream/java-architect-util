package com.example.demo.util;

public class Demo {
    public static void main(String[] args) {
        String attrItemFullCode = "14 2908 B 01 09 00";
        String objectCodeHalf = attrItemFullCode.substring(0, 7);
        String attrCodeHalf = attrItemFullCode.substring(10, 15);
        System.out.println(objectCodeHalf + ":" + attrCodeHalf);
    }
}
