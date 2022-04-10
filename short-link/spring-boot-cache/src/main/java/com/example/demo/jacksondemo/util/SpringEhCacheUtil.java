package com.example.demo.jacksondemo.util;


import com.example.demo.jacksondemo.data.User;
//import net.sf.ehcache.Cache;
//import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Collection;

/**
 * @author ：CoderDream
 * @date ：Created in 2022/4/9 23:51
 * @description：
 * @modified By：CoderDream
 * @version: $
 */
@ContextConfiguration(value = {"classpath:ehcache/spring-ehcache.xml"})
@Component
public class SpringEhCacheUtil {
//    @Resource
//    private CacheManager cacheManager;
//
//    /**
//     * ehcache 与 Spring 集成
//     * AnnotationConfigApplicationContext
//     * xml
//     * 编程式操作 Spring 的缓存抽象 API
//     */
//    public void operateUserCache() {
////        CacheManager cacheManager = new CacheManager() {
////            @Override
////            public Cache getCache(String name) {
////                return null;
////            }
////
////            @Override
////            public Collection<String> getCacheNames() {
////                return null;
////            }
////        };
//        Cache userCache = cacheManager.getCache("user_cache");
//        // 往 userCache 里放入一个 user
//        User user = new User();
//        user.setId(1L);
//        user.setFirstname("Coder");
//        userCache.put(user.getId(), user);
//        // 获取
//        User resultUser = userCache.get(1L, User.class);
//        System.out.println(resultUser);
//
//
////        EhCacheManagerFactoryBean ehCacheManagerFactoryBean ;
////        org.springframework.cache.CacheManager cacheManager;
////        String absPath = this.getClass().getClassLoader().getResource("").getPath() + "ehcache.xml";
////        // 用来管理多个Cache，user_cache、item_cache、store_cache
////        CacheManager cacheManager = CacheManager.create(absPath);
////        // 获取到 CacheManager 管理的所有 cache
////        String[] cacheNames = cacheManager.getCacheNames();
////        System.out.println("CacheManager管理的所有的cache的名字" + Arrays.toString(cacheNames));
////        // 通过 cache 的名字获取具体的 Cache
////        Cache userCache = cacheManager.getCache("user_cache");
////        // 往 userCache 里放入一个 user
////        User user = new User();
////        user.setId(1L);
////        user.setFirstname("Coder");
////        Element element = new Element(user.getId(), user);
////        userCache.put(element);
////        // 通过 key 取出缓存的对象
////        Element resultEle = userCache.get(1L);
////        System.out.println("获取到的resultEle：" + resultEle);
////        System.out.println("获取到的resultEle的value：" + resultEle.getObjectValue());
//    }

}
