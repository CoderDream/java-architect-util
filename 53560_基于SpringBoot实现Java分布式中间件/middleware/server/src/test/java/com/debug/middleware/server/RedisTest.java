package com.debug.middleware.server;
/**
 * Created by Administrator on 2019/3/13.
 */

import com.debug.middleware.server.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Author:debug (SteadyJack)
 * @Date: 2019/3/13 20:50
 * @Link：微信-debug0868 QQ-1948831260
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RedisTest {

    private static final Logger log= LoggerFactory.getLogger(RedisTest.class);

    @Autowired
    private RedisTemplate redisTemplate;

    //采用RedisTemplate将一字符串信息写入缓存中，并读取出来
    @Test
    public void one(){
        log.info("------开始RedisTemplate操作组件实战----");

        //定义字符串内容以及存入缓存的key
        final String content="RedisTemplate实战字符串信息";
        final String key="redis:template:one:string";

        //Redis通用的操作组件
        ValueOperations valueOperations=redisTemplate.opsForValue();

        //将字符串信息写入缓存中
        log.info("写入缓存中的内容：{} ",content);
        valueOperations.set(key,content);

        //从缓存中读取内容
        Object result=valueOperations.get(key);
        log.info("读取出来的内容：{} ",result);
    }


    @Autowired
    private ObjectMapper objectMapper;

    //采用RedisTemplate将一对象信息序列化为Json格式字符串后写入缓存中，
    //然后将其读取出来，最后反序列化解析其中的内容并展示在控制台
    @Test
    public void two() throws Exception{
        log.info("------开始RedisTemplate操作组件实战----");

        //构造对象信息
        User user=new User(1,"debug","阿修罗");

        //Redis通用的操作组件
        ValueOperations valueOperations=redisTemplate.opsForValue();

        //将序列化后的信息写入缓存中
        final String key="redis:template:two:object";
        final String content=objectMapper.writeValueAsString(user);

        valueOperations.set(key,content);
        log.info("写入缓存对象的信息：{} ",user);

        //从缓存中读取内容
        Object result=valueOperations.get(key);
        if (result!=null){
            User resultUser=objectMapper.readValue(result.toString(),User.class);
            log.info("读取缓存内容并反序列化后的结果：{} ",resultUser);
        }
    }

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    //采用StringRedisTemplate将一字符串信息写入缓存中，并读取出来
    @Test
    public void three(){
        log.info("------开始StringRedisTemplate操作组件实战----");

        //定义字符串内容以及存入缓存的key
        final String content="StringRedisTemplate实战字符串信息";
        final String key="redis:three";

        //Redis通用的操作组件
        ValueOperations valueOperations=stringRedisTemplate.opsForValue();

        //将字符串信息写入缓存中
        log.info("写入缓存中的内容：{} ",content);
        valueOperations.set(key,content);

        //从缓存中读取内容
        Object result=valueOperations.get(key);
        log.info("读取出来的内容：{} ",result);
    }


    //采用StringRedisTemplate将一对象信息序列化为Json格式字符串后写入缓存中，
    //然后将其读取出来，最后反序列化解析其中的内容并展示在控制台
    @Test
    public void four() throws Exception{
        log.info("------开始StringRedisTemplate操作组件实战----");

        //构造对象信息
        User user=new User(2,"SteadyJack","阿修罗");

        //Redis通用的操作组件
        ValueOperations valueOperations=redisTemplate.opsForValue();

        //将序列化后的信息写入缓存中
        final String key="redis:four";
        final String content=objectMapper.writeValueAsString(user);

        valueOperations.set(key,content);
        log.info("写入缓存对象的信息：{} ",user);

        //从缓存中读取内容
        Object result=valueOperations.get(key);
        if (result!=null){
            User resultUser=objectMapper.readValue(result.toString(),User.class);
            log.info("读取缓存内容并反序列化后的结果：{} ",resultUser);
        }
    }
}























