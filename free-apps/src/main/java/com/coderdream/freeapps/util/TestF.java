package com.coderdream.freeapps.util;

public class TestF {

    public static void main(String[] args) {

        String objectFullCode = "0200010000000";
        String attrFullCode = "020000B010300";
        String attrItemFullCode = objectFullCode.substring(0, 6) + attrFullCode.substring(6, 13);
        System.out.println(attrItemFullCode);
    }

}
