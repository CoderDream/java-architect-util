package com.example.demo;

import java.util.UUID;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
/**
 * author    : toto 11111111@qq.com
 * CFMS    ：Computer files management system
 * version  ：1.0 2013-2-19 下午01:26:04
 *
 * 此类用于测试单元测试中的几个方法
 */
public class TestJunit {
    private String flag;

    /**
     * 在本类加载前执行，注意的是有关键字：static
     */
    @BeforeClass
    public static void testBeforeClass() {
        System.out.println("public static void testBeforeClass()");
    }

    /**
     * 在执行目标测试方法testTest()前执行
     */
    @Before
    public void testBefore() {
        System.out.println("-------------------------------------");
        System.out.println("public void testBefore()");
        flag = UUID.randomUUID().toString();
        System.out.println("-------------------------------------");
    }

    /**
     * 目标测试方法testTest()
     */
    @Test
    public void testTest() {
        System.out.println("public void testTest()");
        System.out.println(flag);
        flag = null;
    }

    /**
     * 目标测试方法testTest2()
     */
    @Test
    public void testTest2() {
        System.out.println("public void testTest2()");
        System.out.println(flag);
        flag = null;
    }

    /**
     * 在执行目标测试方法testTest()执行
     */
    @After
    public void testAfter() {
        System.out.println("-------------------------------------");
        System.out.println("public void testAfter()");
        System.out.println(flag);
        System.out.println("-------------------------------------");
    }

    /**
     * 在本类加载后执行，注意的是有关键字：static
     */
    @AfterClass
    public static void testAfterClass() {
        System.out.println("public static void testAfterClass()");
    }
}
