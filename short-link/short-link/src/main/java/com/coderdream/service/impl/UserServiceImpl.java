package com.coderdream.service.impl;

import com.coderdream.bean.User;
import com.coderdream.service.UserService;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 */
@Service
@CacheConfig(cacheNames = {"user_cache"})
//@ContextConfiguration(value = {"classpath:guava/spring-guava-cache.xml"})
public class UserServiceImpl implements UserService {
    @Resource
    private CacheManager cacheManager;

    @Resource
    UserService userService;

    @Override
    @Cacheable
    public String putLink(String longLink) {

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

        return null;
    }

    @Override
    public String getLink(String shortLink) {
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



        return null;
    }

    /**
     * 调用方法:
     * 看缓存有没有？
     * 有: 返回缓存中的结果
     * 没有： 执行方法，并把结果放入缓存
     *
     * @param id
     * @return
     */
    @Override
    @Cacheable(key = "#id")
    public User getById(Long id) {
        System.out.println("模拟去db查询");
        User user = new User();
        user.setId(id);
        user.setName("老赵" + id);
        return user;
    }

    /**
     * key遵循spring  的spel语法
     * 最终的key:com.roadjava.cache.service.impl.UserServiceImpl.getUser.-5641300162034056251-1,2,-abc-
     */
    @Override
    @Cacheable
    public User getUser(User queryParam, int[] arr, String str) {
        System.out.println("模拟去db查询--测试自定义KeyGenerator");
        User user = new User();
        user.setId(999L);
        user.setName("乐之者java999");
        return user;
    }
}
