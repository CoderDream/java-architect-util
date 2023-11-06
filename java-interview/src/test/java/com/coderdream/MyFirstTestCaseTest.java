package com.coderdream;


import org.junit.jupiter.api.*;

/**
 * @author ：CoderDream
 * @date ：Created in 2021/9/2 10:02
 * @description：我的第一个测试用例
 * @modified By：CoderDream
 * @version: 1.0$
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MyFirstTestCaseTest {

    @BeforeAll
    public static void init() {
        System.out.println("初始化数据");
    }

    @AfterAll
    public static void cleanUp() {
        System.out.println("清理数据");
    }

    @BeforeEach
    public void tearUp() {
        System.out.println("当前测试方法开始");
    }

    @AfterEach
    public void tearDown() {
        System.out.println("当前测试方法结束");
    }

    @DisplayName("我的第一个测试")
    @Order(1)
    @Test
    void testFirstTest() {
        System.out.println("我的第一个测试开始测试");
    }

    @DisplayName("我的第二个测试")
    @Order(2)
    @Test
    void testSecondTest() {
        System.out.println("我的第二个测试开始测试");
    }

    @DisplayName("我的第三个测试")
    @Disabled
    @Order(3)
    @Test
    void testThirdTest() {
        System.out.println("我的第三个测试开始测试");
    }
}
