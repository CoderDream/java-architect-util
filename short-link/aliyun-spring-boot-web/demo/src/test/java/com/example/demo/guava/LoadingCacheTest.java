package com.example.demo.guava;

import com.example.demo.bean.User;
import com.google.common.cache.*;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * @author zhaodaowen
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
public class LoadingCacheTest {
    /**
     * 单独使用guava的cache，guava的cache分为两种:
     * 第一种: Cache<---LoadingCache<---com.google.common.cache.LocalCache.LocalLoadingCache
     * 特点: 缓存中获取不到值，会根据指定的loader进行加载，加载后自动放入缓存
     * 第二种: Cache<---com.google.common.cache.LocalCache.LocalManualCache
     * 特点：类似ehcache
     */
    @Test
    public void test1() throws InterruptedException {
        LoadingCache<Long, User> loadingCache = CacheBuilder.newBuilder()
                // 指定并发级别
                .concurrencyLevel(8)
                // 初始化大小,配合concurrencyLevel做分段锁
                .initialCapacity(60)
                // 缓存中最多可放多少个元素
                .maximumSize(10)
                // 从写入开始计算，10s过期
                .expireAfterWrite(3, TimeUnit.SECONDS)
                // 统计命中率
                .recordStats()
                // 缓存中的元素被驱逐出去后会自动回调到这里
                .removalListener((RemovalListener<Long, User>) notification -> {
                    Long key = notification.getKey();
                    RemovalCause cause = notification.getCause();
                    System.out.println("key:" + key + "被移出缓存,原因:" + cause);
                })
                // 缓存中获取不到值，会回调到这里
                .build(new CacheLoader<Long, User>() {
                    // key:将来loadingCache.get(k)获取不到传来的k
                    @Override
                    public User load(Long key) {
                        // 可以在这里就行数据的加载
                        System.out.println("去存储中加载");
                        User user = new User();
                        user.setId(key);
                        user.setName("http://www.roadjava.com" + key);
                        return user;
                    }
                });
        for (long i = 0; i < 20; i++) {
            // get方法抛异常
//            User user = loadingCache.get(i);
            User user = loadingCache.getUnchecked(999L);
            System.out.println(user);
            TimeUnit.SECONDS.sleep(1);
        }

        System.out.println(loadingCache.stats().toString());
    }
}
