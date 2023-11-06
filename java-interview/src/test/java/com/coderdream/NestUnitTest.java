package com.coderdream;

import org.junit.jupiter.api.*;

/**
 * @author ：CoderDream
 * @date ：Created in 2021/9/2 11:22
 * @description：内嵌测试类
 * @modified By：CoderDream
 * @version: 1.0$
 */
@DisplayName("内嵌测试类")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class NestUnitTest {
    @BeforeEach
    void init() {
        System.out.println("测试方法执行前准备");
    }

    @Nested
    @DisplayName("第一个内嵌测试类")
    class FirstNestTest {
        @Test
        @Order(1)
        void test() {
            System.out.println("第一个内嵌测试类执行测试");
        }
    }

    @Nested
    @DisplayName("第二个内嵌测试类")
    class SecondNestTest {
        @Test
        @Order(2)
        void test() {
            System.out.println("第二个内嵌测试类执行测试");
        }
    }
}
