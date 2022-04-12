package com.coderdream;

import com.coderdream.bean.User;
import com.coderdream.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * spring/springboot集成任何第三方框架：
 * 1. spring: xml或在@Configuration中@bean注入
 * 2. springboot : starter或@Configuration中@bean注入
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = {"classpath:guava/spring-guava-cache.xml"})
public class SpringGuavaCacheTest {
    @Resource
    private CacheManager cacheManager;

    @Resource
    UserService userService;

    /**
     * 测试自定义KeyGenerator
     */
    @Test
    public void test2() {
        User queryParam = new User();
        queryParam.setId(666L);
        queryParam.setName("小军");
        int[] arr = new int[2];
        arr[0] = 1;
        arr[1] = 2;
        User user = userService.getUser(queryParam, arr, "abc");
        System.out.println(user);
        System.out.println(userService.getUser(queryParam, arr, "abc"));
        System.out.println(userService.getUser(queryParam, arr, "abc"));
    }

    /**
     * 测试自定义CacheManager
     * 1. 编写com.roadjava.cache.guava.GuavaCacheCacheManager
     * 2. 编写com.roadjava.cache.guava.GuavaCache
     * 3. 配置spring-guava-cache.xml
     */
    @Test
    public void test1() {
        /*
        拿到
         com.roadjava.cache.guava.GuavaCacheCacheManager
        就可以就行编程式的缓存api使用了
         */
        System.out.println(cacheManager.getClass());
        /*
         * 注解式使用
         */
        User user = userService.getById(333L);
        System.out.println(user);
        System.out.println(userService.getById(333L));
        System.out.println(userService.getById(333L));
    }
}
