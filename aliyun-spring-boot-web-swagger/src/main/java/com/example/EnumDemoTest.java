package com.example;

public class EnumDemoTest {
    public static void main(String[] args) {
        System.out.println(EnumDemo.ShareOperateType.getNameByCode("share"));
        System.out.println(EnumDemo.ShareOperateType.getNameByCode("unshared"));
        System.out.println(EnumDemo.ShareOperateType.getNameByCode("download"));
        System.out.println(EnumDemo.ShareOperateType.getNameByCode("XXXX"));
    }
}
