package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
class DemoApplicationTests {

    /**
     * 编译以下代码，执行时jvm参数设置为-Xms20m -Xmx20m
     * 堆溢出
     * count is: 660
     * Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
     */
    @Test
    void testCase01() {
        List<byte[]> list = new ArrayList<>();
        int i = 0;
        while (true) {
            list.add(new byte[5 * 1024 * 1024]);
            System.out.println("count is: " + (++i));
        }
    }


    static class Key {
        Integer id;

        Key(Integer id) {
            this.id = id;
        }

        @Override
        public boolean equals(Object o) {
            boolean response = false;
            if (o instanceof Key) {
                response = (((Key) o).id).equals(this.id);
            }
            return response;
        }

        @Override
        public int hashCode() {
            return id.hashCode();
        }
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Test
    void testCase02() {
        Map map = new HashMap<>();
        while (true) {
            for (int i = 0; i < 10000000; i++) {
                if(!map.containsKey(new Key(i))) {
                    System.out.println("Number: " + i);
                    map.put(new Key(i), "Number: " + i);
                }
            }
        }
    }

    /**
     * 垃圾回收超时内存溢出
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    @Test
    void testCase03() {
        Map map = System.getProperties();
        Random random = new Random();
        while (true) {
            map.put(random.nextInt(), "微信公众号：CoderDream");
        }
    }
}