package com.example.demo.service.impl;

import com.example.demo.bean.User;
import com.example.demo.service.UserService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author zhaodaowen
 * @see <a href="http://www.roadjava.com">乐之者java</a>
 */
@Service
@CacheConfig(cacheNames = {"user_cache"})
public class UserServiceImpl implements UserService {
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
