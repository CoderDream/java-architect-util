package com.interview.algorithms.recursion;

import java.util.HashMap;
import java.util.Map;

/**
 * @author CoderDream
 */
public class D03Fibonacci {

    public static void main(String[] args) {
        for(int n = 1; n < 30; n++) {

            long start = System.currentTimeMillis();
            System.out.println(n + "\t|\t"+ fibonacci(n));
            long period = System.currentTimeMillis() - start;
            System.out.println("耗时毫秒数1：" + period);

            start = System.currentTimeMillis();
            System.out.println(n + "\t|\t"+ fib(n));
            period = System.currentTimeMillis() - start;
            System.out.println("耗时毫秒数2：" + period);
        }
    }

    public static int fibonacci(int n) {
        long start = System.currentTimeMillis();
        if (n < 2) {
            return n;
        } else {
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
    }

    static Map<Integer, Integer> cache = new HashMap<>();

    public static int fib(int n) {
        if(cache.containsKey(n)){
            System.out.println("#1#");
            return cache.get(n);
        }
        int result;
        if (n < 2) {
            result = n;
        } else {
            result = fibonacci(n - 1) + fibonacci(n - 2);
        }
        if(n <= 2) {
            System.out.println("#2#");
            // 保存中间结果到cache中
            cache.put(n, result);
        }
        return result;
    }

}

