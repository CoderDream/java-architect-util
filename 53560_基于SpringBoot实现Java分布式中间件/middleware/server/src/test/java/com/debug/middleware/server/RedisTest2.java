package com.debug.middleware.server;/**
 * Created by Administrator on 2019/3/15.
 */

import com.debug.middleware.server.entity.Fruit;
import com.debug.middleware.server.entity.Person;
import com.debug.middleware.server.entity.PhoneUser;
import com.debug.middleware.server.entity.Student;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @Author:debug (SteadyJack)
 * @Date: 2019/3/15 8:36
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RedisTest2 {

    private static final Logger log= LoggerFactory.getLogger(RedisTest2.class);

    @Autowired
    private RedisTemplate redisTemplate;

    //json解析组件
    @Autowired
    private ObjectMapper objectMapper;

    //字符串类型
    @Test
    public void one() throws Exception{
        //构造用户个人实体对象
        Person p=new Person(10013,23,"修罗","debug","火星");

        //定义key与即将存入缓存中的value
        final String key="redis:test:1";
        String value=objectMapper.writeValueAsString(p);

        //写入缓存中
        log.info("存入缓存中的用户实体对象信息为：{} ",p);
        redisTemplate.opsForValue().set(key,value);

        //从缓存中获取用户实体信息
        Object res=redisTemplate.opsForValue().get(key);
        if (res!=null){
            Person resP=objectMapper.readValue(res.toString(),Person.class);
            log.info("从缓存中读取信息：{} ",resP);
        }
    }


    //列表类型
    @Test
    public void two() throws Exception{
        //构造已经排好序的用户对象列表
        List<Person> list=new ArrayList<>();
        list.add(new Person(1,21,"修罗", "debug", "火星"));
        list.add(new Person(2,22,"大圣","jack","水帘洞"));
        list.add(new Person(3,23,"盘古","Lee","上古"));
        log.info("构造已经排好序的用户对象列表: {} ",list);

        //将列表数据存储至Redis的List中
        final String key="redis:test:2";
        ListOperations listOperations=redisTemplate.opsForList();
        for (Person p:list){
            //往列表中添加数据-从队尾中添加
            listOperations.leftPush(key,p);
        }

        //获取Redis中List的数据-从队头中获取
        log.info("--获取Redis中List的数据-从队头中获取--");
        Object res=listOperations.rightPop(key);
        Person resP;
        while (res!=null){
            resP= (Person) res;
            log.info("当前数据：{} ",resP);
            res=listOperations.rightPop(key);
        }
    }

    //集合类型
    @Test
    public void three() throws Exception{
        //构造一组用户姓名列表
        List<String> userList=new ArrayList<>();
        userList.add("debug");
        userList.add("jack");
        userList.add("修罗");
        userList.add("大圣");
        userList.add("debug");
        userList.add("jack");
        userList.add("steadyheart");
        userList.add("修罗");
        userList.add("大圣");

        log.info("待处理的用户姓名列表：{} ",userList);

        //遍历访问，剔除相同姓名的用户并塞入集合中，最终存入缓存中
        final String key="redis:test:3";
        SetOperations setOperations=redisTemplate.opsForSet();
        for (String str:userList){
            setOperations.add(key,str);
        }

        //从缓存中获取已剔除的用户集合
        Object res=setOperations.pop(key);
        while (res!=null){
            log.info("从缓存中获取的用户集合-当前用户：{} ",res);
            res=setOperations.pop(key);
        }
    }

    //有序集合
    @Test
    public void four() throws Exception{
        //构造一组无序的用户手机充值对象列表
        List<PhoneUser> list=new ArrayList<>();
        list.add(new PhoneUser("103",130.0));
        list.add(new PhoneUser("101",120.0));
        list.add(new PhoneUser("102",80.0));
        list.add(new PhoneUser("105",70.0));
        list.add(new PhoneUser("106",50.0));
        list.add(new PhoneUser("104",150.0));
        log.info("构造一组无序的用户手机充值对象列表:{}",list);

        //遍历访问充值对象列表，将信息塞入Redis的有序集合中
        final String key="redis:test:4";
        //因为zSet在add元素进入缓存后，下次就不能进行更新了，故而为了测试方便,
        //进行操作之前先清空该缓存(当然实际生产环境中不建议这么使用)
        redisTemplate.delete(key);

        ZSetOperations zSetOperations=redisTemplate.opsForZSet();
        for (PhoneUser u:list){
            zSetOperations.add(key,u,u.getFare());
        }

        //前端获取访问充值排名靠前的用户列表
        Long size=zSetOperations.size(key);
        //从小到大排序
        Set<PhoneUser> resSet=zSetOperations.range(key,0L,size);
        //从大到小排序
        //Set<PhoneUser> resSet=zSetOperations.reverseRange(key,0L,size);
        for (PhoneUser u:resSet){
            log.info("从缓存中读取手机充值记录排序列表，当前记录：{} ",u);
        }
    }

    //Hash哈希存储
    @Test
    public void five() throws Exception{
        //构造学生对象列表,水果对象列表
        List<Student> students=new ArrayList<>();
        List<Fruit> fruits=new ArrayList<>();

        students.add(new Student("10010","debug","大圣"));
        students.add(new Student("10011","jack","修罗"));
        students.add(new Student("10012","sam","上古"));

        fruits.add(new Fruit("apple","红色"));
        fruits.add(new Fruit("orange","橙色"));
        fruits.add(new Fruit("banana","黄色"));

        //分别遍历不同对象队列，并采用Hash哈希存储至缓存中
        final String sKey="redis:test:5";
        final String fKey="redis:test:6";

        HashOperations hashOperations=redisTemplate.opsForHash();
        for (Student s:students){
            hashOperations.put(sKey,s.getId(),s);
        }
        for (Fruit f:fruits){
            hashOperations.put(fKey,f.getName(),f);
        }

        //获取学生对象列表与水果对象列表
        Map<String,Student> sMap=hashOperations.entries(sKey);
        log.info("获取学生对象列表：{} ",sMap);

        Map<String,Fruit> fMap=hashOperations.entries(fKey);
        log.info("获取水果对象列表：{} ",fMap);

        //获取指定的学生对象、水果对象
        String sField="10012";
        Student s= (Student) hashOperations.get(sKey,sField);
        log.info("获取指定的学生对象：{} -> {} ",sField,s);

        String fField="orange";
        Fruit f= (Fruit) hashOperations.get(fKey,fField);
        log.info("获取指定的水果对象：{} -> {} ",fField,f);
    }


    //Key失效一
    @Test
    public void six() throws Exception{
        //构造key与redis操作组件
        final String key1="redis:test:6";
        ValueOperations valueOperations=redisTemplate.opsForValue();

        //第一种方法：在往缓存中set数据时,提供一个ttl,表示ttl时间一到,缓存中的key将自动失效,即被清理
        //在这里TTL是10秒
        valueOperations.set(key1,"expire操作",10L, TimeUnit.SECONDS);

        //等待5秒-判断key是否还存在
        Thread.sleep(5000);
        Boolean existKey1=redisTemplate.hasKey(key1);
        Object value=valueOperations.get(key1);
        log.info("等待5秒-判断key是否还存在:{} 对应的值:{}",existKey1,value);

        //再等待5秒-再判断key是否还存在
        Thread.sleep(5000);
        existKey1=redisTemplate.hasKey(key1);
        value=valueOperations.get(key1);
        log.info("再等待5秒-再判断key是否还存在:{} 对应的值:{}",existKey1,value);
    }

    //Key失效二
    @Test
    public void seven() throws Exception{
        //构造key与redis操作组件
        final String key2="redis:test:7";
        ValueOperations valueOperations=redisTemplate.opsForValue();

        //第二种方法：在往缓存中set数据后,采用redisTemplate的expire方法失效该key
        valueOperations.set(key2,"expire操作-2");
        redisTemplate.expire(key2,10L,TimeUnit.SECONDS);

        //等待5秒-判断key是否还存在
        Thread.sleep(5000);
        Boolean existKey=redisTemplate.hasKey(key2);
        Object value=valueOperations.get(key2);
        log.info("等待5秒-判断key是否还存在:{} 对应的值:{}",existKey,value);

        //再等待5秒-再判断key是否还存在
        Thread.sleep(5000);
        existKey=redisTemplate.hasKey(key2);
        value=valueOperations.get(key2);
        log.info("再等待5秒-再判断key是否还存在:{} 对应的值:{}",existKey,value);
    }





}








































