package com.coderdream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

/**
 * @author ：CoderDream
 * @date ：Created in 2021/9/2 11:46
 * @description：新的断言
 * @modified By：CoderDream
 * @version: 1.0$
 */
public class NewAssertionsUnitTest {
    @Test
    void testGroupAssertions() {
        int[] numbers = {0, 1, 2, 3, 4};
        Assertions.assertAll("numbers",
                () -> Assertions.assertEquals(numbers[1], 1),
                () -> Assertions.assertEquals(numbers[3], 3),
                () -> Assertions.assertEquals(numbers[4], 4)
        );
    }

//    @Test
//    @DisplayName("超时方法测试")
//    void test_should_complete_in_one_second() {
//        Assertions.assertTimeoutPreemptively(Duration.of(1, ChronoUnit.SECONDS), () -> Thread.sleep(2000));
//    }

    @Test
    @DisplayName("测试捕获的异常")
    void assertThrowsException() {
        String str = null;
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Integer.valueOf(str);
        });
    }

//    @Test
//    @DisplayName("测试捕获的异常")
//    void assertThrowsExceptionTwo() {
//        String str = "A";
//        Integer.valueOf(str);
//        Assertions.assertThrows(IllegalArgumentException.class, () -> {
//            Integer.valueOf(str);
//            System.out.println("ABC");
//        });
//    }
}
