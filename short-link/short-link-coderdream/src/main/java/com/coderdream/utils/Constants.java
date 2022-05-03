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
}
