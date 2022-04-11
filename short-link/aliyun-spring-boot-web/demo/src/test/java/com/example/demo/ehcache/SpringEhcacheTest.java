package com.example.demo.ehcache;

import com.example.demo.bean.User;
import com.example.demo.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @author zhaodaowen
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = {"classpath:ehcache/spring-ehcache.xml"})
public class SpringEhcacheTest {

    @Resource
    private CacheManager cacheManager;
    @Resource
    UserService userService;

    /**
     * 注解的方式
     */
    @Test
    public void test2() {
        User user = userService.getById(333L);
        System.out.println(user);
        System.out.println(userService.getById(333L));
        System.out.println(userService.getById(333L));
    }

    /**
     * ehcache与spring集成
     * AnnotationConfigApplicationContext
     * xml
     * 使用spring的缓存抽象的方式：
     * 第一种方法：编程式操作spring的缓存抽象api
     * 第二种方法: 注解的方式，如@Cacheable
     * 序列化报错:
     * 第一种方法： <persistence strategy="none"/>
     * 第二种方法： User实现序列化接口
     */
    @Test
    public void test1() {
        Cache userCache = cacheManager.getCache("user_cache");
        /*
         往userCache放入一个user
         */
        User user = new User();
        user.setId(1L);
        user.setName("乐之者java");
        userCache.put(user.getId(), user);
        // 获取
        User resultUser = userCache.get(1L, User.class);
        System.out.println("获取到结果:" + resultUser);
    }
}
