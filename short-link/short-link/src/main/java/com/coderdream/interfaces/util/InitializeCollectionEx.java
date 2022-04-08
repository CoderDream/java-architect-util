package com.coderdream.interfaces.util;

//import com.google.common.collect.ImmutableMap;
//import com.google.common.collect.Lists;
//import java.util.List;
//import java.util.Map;
//import java.util.List;


import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.Map;

/**
 * @author ：CoderDream
 * @date ：Created in 2022/4/8 21:15
 * @description：
 * @modified By：CoderDream
 * @version: $
 */

public class InitializeCollectionEx {


    private Cache<Object, Object> cache;

    public static void main(String[] args) {

        Map items = ImmutableMap.of("coin", 3, "glass", 4, "pencil", 1);

        items.entrySet()
                .stream()
                .forEach(System.out::println);

        List<String> fruits = Lists.newArrayList("orange", "banana", "kiwi",
                "mandarin", "date", "quince");

        for (String fruit: fruits) {
            System.out.println(fruit);
        }
    }
}
