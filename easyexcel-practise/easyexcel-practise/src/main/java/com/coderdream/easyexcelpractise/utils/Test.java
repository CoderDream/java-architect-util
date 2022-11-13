package com.coderdream.easyexcelpractise.utils;

public class Test {
    public static void main(String[] args) {
        String objectTypeLabel = "zong_ku_rong_（_yi_ m";
        objectTypeLabel = objectTypeLabel.replaceAll("[^_a-zA-Z]", ""); // 去掉非大小写字母和下划线的字符
        System.out.println(objectTypeLabel);
    }
}
