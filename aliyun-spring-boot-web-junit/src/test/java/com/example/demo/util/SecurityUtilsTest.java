package com.example.demo.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class SecurityUtilsTest {

    @Test
    public void encryptPassword() {
        String pwd = "Xulin12345";
        // 加密
        String md5 = SecurityUtils.md5(pwd);
        // 解密
        String md52 = SecurityUtils.encryptPassword(pwd);
        System.out.println(md5);
        System.out.println(md52);
        System.out.println(SecurityUtils.md5("694dc7e414aa15a941eb5ca3528470e9"));
        // 7571b6a6b9418fefee4ad4d25c13581c

//        d79b5365170794752c2ef95613d2c6ba
//                d79b5365170794752c2ef95613d2c6ba
//        90e153483b7340d262708ae7ec85b372
        //c3284d0f94606de1fd2af172aba15bf3

        System.out.println(SecurityUtils.md5("admin"));
        System.out.println(SecurityUtils.encryptPassword("admin"));
        System.out.println(SecurityUtils.encryptPassword(SecurityUtils.md5("admin")));
    }

    @Test
    public void matchesPassword() {

        // 0a63c9e7711928fbf7ece4c6eba7514a
        // 0a63c9e7711928fbf7ece4c6eba7514a
        String pwd = "xulin12345";
        // 加密
        String md5 = SecurityUtils.md5(pwd);
        // 解密
        String md52 = SecurityUtils.encryptPassword(pwd);
        System.out.println(md5);
        System.out.println(md52);
        boolean result = SecurityUtils.matchesPassword(md5, md52);
        System.out.println(result);
    }
}