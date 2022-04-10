package com.example.demo.jacksondemo.service.impl;

import com.example.demo.jacksondemo.data.User;
import com.example.demo.jacksondemo.service.UserService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author ：CoderDream
 * @date ：Created in 2022/4/10 15:59
 * @description：
 * @modified By：CoderDream
 * @version: $
 */
@Service
@CacheConfig(cacheNames = {"user_cache"})
public class UserServiceImpl implements UserService {
    /**
     * 调用方法：
     *  看缓存有没有？
     *      有：返回缓存中的结果
     *      没有：执行方法，并把结果放入缓存
     *
     * @param id
     * @return
     */
    @Override
    @Cacheable(key = "#id")
    public User getById(Long id) {
        System.out.println("模拟去DB查询");
        User user = new User();
        user.setId(id);
        user.setFirstname("老赵" + id);
        return user;
    }
}
