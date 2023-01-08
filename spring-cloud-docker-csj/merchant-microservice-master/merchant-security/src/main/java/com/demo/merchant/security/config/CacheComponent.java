package com.demo.merchant.security.config;

import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Component
public class CacheComponent {

    @Resource(name = "redisTemplate")
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 添加数据对象到缓存
     *
     * @param key      主KEY
     * @param childKey 子KEY
     * @param object   数据对象
     * @param timeout  缓存时间：秒
     */
    public void put(String key, String childKey, Object object, long timeout) {
        putForExpire(key, childKey, object, timeout);
    }

    /**
     * 从缓存中获取数据对象
     *
     * @param key      主KEY
     * @param childKey 子KEY
     * @return 数据对象
     */
    public Object get(String key, String childKey) {
        return getForExpire(key, childKey);
    }

    /**
     * 从缓存中移除数据对象
     *
     * @param key      主KEY
     * @param childKey 子KEY
     */
    public void remove(String key, String childKey) {
        removeForExpire(key, childKey);
    }

    /**
     * 添加数据对象到缓存
     *
     * @param key      主KEY
     * @param childKey 子KEY
     * @param value    数据对象
     * @param timeout  缓存时间：秒
     */
    public void putForExpire(String key, String childKey, Object value, long timeout) {
        BoundValueOperations<String, Object> boundValueOperations = redisTemplate.boundValueOps(getFinallyKey(key, childKey));
        boundValueOperations.set(value, timeout, TimeUnit.SECONDS);
    }

    /**
     * 取通过 putForExpire方法添加的缓存值
     *
     * @param key
     * @param childKey
     */
    public Object getForExpire(String key, String childKey) {
        return redisTemplate.boundValueOps(getFinallyKey(key, childKey)).get();
    }

    /**
     * 删除通过 putForExpire方法添加的缓存值
     *
     * @param key
     * @param childKey
     */
    public void removeForExpire(String key, String childKey) {
        redisTemplate.delete(getFinallyKey(key, childKey));
    }

    private String getFinallyKey(String key, String childKey) {
        return key + childKey;
    }
}