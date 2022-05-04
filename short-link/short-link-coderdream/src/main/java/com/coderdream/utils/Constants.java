package com.coderdream.utils;

/**
 * @author ：CoderDream
 * @date ：Created in 2022/4/28 20:10
 * @description：
 * @modified By：CoderDream
 * @version: $
 */
public class Constants {
    /**
     * 布隆过滤器的容量 100000000
     */
    public static final Integer BLOOM_FILTER_INSERTION = 1000000;

    /**
     * 期望的误判率
     */
    public static final double BLOOM_FILTER_FPP = 0.01;

    /**
     * Guava Cache 并发级别为8，并发级别是指可以同时写缓存的线程数
     */
    public static final Integer CONCURRENCY_LEVEL = 8;

    /**
     * Guava Cache 缓存容器的初始容量为 10
     */
    public static final Integer INITIAL_CAPACITY = 10;

    /**
     * Guava Cache 设置缓存最大容量为 100000000
     */
    public static final Integer MAXIMUM_SIZE = 100000000;

}
