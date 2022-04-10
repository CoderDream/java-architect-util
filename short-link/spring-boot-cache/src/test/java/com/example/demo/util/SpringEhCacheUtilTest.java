package com.example.demo.util;

import com.example.demo.jacksondemo.data.User;
import com.example.demo.jacksondemo.util.EhCacheUtil;
import com.example.demo.jacksondemo.util.SpringEhCacheUtil;
import org.junit.Before;
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
public class SpringEhCacheUtilTest {

//    private SpringEhCacheUtil springEhCacheUtil;
//
//    @Before
//    public void setUp() {
//        springEhCacheUtil = new SpringEhCacheUtil();
//    }
//
//    @Test
//    public void testOperateUserCache() {
//        springEhCacheUtil.operateUserCache();
//    }

}