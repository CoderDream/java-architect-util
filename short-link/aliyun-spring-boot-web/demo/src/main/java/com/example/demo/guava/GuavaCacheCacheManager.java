package com.example.demo.guava;

import com.google.common.cache.CacheBuilder;
import org.springframework.cache.Cache;
import org.springframework.cache.support.AbstractCacheManager;

import java.util.Collection;
import java.util.LinkedHashSet;

/**
 * 因为spring没有自带的guava cache的实现，这里自定义
 *
 * @author zhaodaowen
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
public class GuavaCacheCacheManager extends AbstractCacheManager {
    /**
     * 用来加载当前CacheManager要管理哪些cache
     *
     */
    @Override
    protected Collection<? extends Cache> loadCaches() {
        /*
        获取所有的cache
         */
        com.google.common.cache.Cache<Object, Object> userCache = CacheBuilder.newBuilder()
                .maximumSize(100)
                .build();
        GuavaCache guavaUserCache = new GuavaCache("user_cache", userCache);
//        new GuavaCache("book_cache",bookCache);

        Collection<Cache> caches = new LinkedHashSet<>();
        caches.add(guavaUserCache);
        return caches;
    }
}
