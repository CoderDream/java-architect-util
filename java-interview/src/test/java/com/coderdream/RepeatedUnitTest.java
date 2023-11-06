package com.coderdream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;

/**
 * @author ：CoderDream
 * @date ：Created in 2021/9/2 11:40
 * @description：自定义名称重复测试
 * @modified By：CoderDream
 * @version: 1.0$
 */
public class RepeatedUnitTest {
    @DisplayName("重复测试")
    @RepeatedTest(value = 3)
    public void i_am_a_repeated_test() {
        System.out.println("执行测试");
    }

    @DisplayName("自定义名称重复测试")
    @RepeatedTest(value = 3, name = "{displayName} 第 {currentRepetition} 次")
    public void i_am_a_repeated_test_2() {
        System.out.println("执行测试");
    }

}
