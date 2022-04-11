package com.example.demo.ehcache;

import com.example.demo.bean.User;
import com.example.demo.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author zhaodaowen
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootEhcacheTest {
    @Resource
    private CacheManager cacheManager;
    @Resource
    UserService userService;

    /**
     * ehcache与springboot集成
     * 1.在application.yml中指定ehcache的配置文件
     * 2.@EnableCaching
     */
    @Test
    public void test1() {
        /*
        拿到
         org.springframework.cache.ehcache.EhCacheCacheManager
        就可以就行编程式的缓存api使用了
         */
        System.out.println(cacheManager.getClass());
        /**
         * 注解式使用
         */
        User user = userService.getById(333L);
        System.out.println(user);
        System.out.println(userService.getById(333L));
        System.out.println(userService.getById(333L));
    }
}
