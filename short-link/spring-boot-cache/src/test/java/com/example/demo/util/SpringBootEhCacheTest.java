package com.example.demo.util;

import com.example.demo.jacksondemo.data.User;
import com.example.demo.jacksondemo.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author ：CoderDream
 * @date ：Created in 2022/4/9 23:52
 * @description：
 * @modified By：CoderDream
 * @version: $
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootEhCacheTest {
    @Resource
    private CacheManager cacheManager;

    @Resource
    UserService userService;

    /**
     * 注解方式
     */
    @Test
    public void test1() {
        // 获取
        System.out.println(cacheManager.getClass());
    }

}