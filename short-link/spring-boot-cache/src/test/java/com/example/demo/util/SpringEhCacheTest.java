package com.example.demo.util;

import com.example.demo.jacksondemo.data.User;
import com.example.demo.jacksondemo.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @author ：CoderDream
 * @date ：Created in 2022/4/9 23:52
 * @description：
 * @modified By：CoderDream
 * @version: $
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = {"classpath:ehcache/spring-ehcache.xml"})
public class SpringEhCacheTest {
    @Resource
    private CacheManager cacheManager;

    @Resource
    UserService userService;

    /**
     * 注解方式
     */
    @Test
    public void testOperateUserCache2() {
        // 获取
        User resultUser = userService.getById(333L);
        System.out.println(resultUser);
        System.out.println(userService.getById(333L));
        System.out.println(userService.getById(333L));
    }

    @Test
    public void testOperateUserCache() {
        Cache userCache = cacheManager.getCache("user_cache");
        // 往 userCache 里放入一个 user
        User user = new User();
        user.setId(1L);
        user.setFirstname("Coder");
        userCache.put(user.getId(), user);
        // 获取
        User resultUser = userCache.get(1L, User.class);
        System.out.println(resultUser);
    }

}