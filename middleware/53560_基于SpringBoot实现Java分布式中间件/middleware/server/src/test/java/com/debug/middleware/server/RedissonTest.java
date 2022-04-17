package com.debug.middleware.server;/**
 * Created by Administrator on 2019/3/29.
 */

import com.debug.middleware.model.dto.PraiseRankDto;
import com.debug.middleware.server.dto.DeadDto;
import com.debug.middleware.server.dto.UserLoginDto;
import com.debug.middleware.server.entity.BloomDto;
import com.debug.middleware.server.entity.RMapDto;
import com.debug.middleware.server.entity.RSetComparator;
import com.debug.middleware.server.entity.RSetDto;
import com.debug.middleware.server.service.redisson.UserLoginPublisher;
import com.debug.middleware.server.service.redisson.delayQueue.MqDelayQueuePublisher;
import com.debug.middleware.server.service.redisson.praise.PraiseTotalComparator;
import com.debug.middleware.server.service.redisson.queue.QueuePublisher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;


/**
 * @Author:debug (SteadyJack)
 * @Date: 2019/3/29 16:10
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RedissonTest {
    //定义日志
    private static final Logger log= LoggerFactory.getLogger(RedissonTest.class);
    //定义操作Redisson的客户端实例
    @Autowired
    private RedissonClient redissonClient;

    @Test
    public void test1() throws Exception{
        log.info("redisson的配置信息：{}",redissonClient.getConfig().toJSON());
    }

    @Test
    public void test2() throws Exception{
        //定义Redis中的Key
        final String key="myBloomFilterDataV2";
        //初始化构造数组容量的大小
        Long total=100000L;
        //创建布隆过滤器组件
        RBloomFilter<Integer> bloomFilter=redissonClient.getBloomFilter(key);
        //初始化布隆过滤器，预计统计元素数量为100000，期望误差率为0.01
        bloomFilter.tryInit(total, 0.01);
        //初始化遍历往布隆过滤器添加元素
        for (int i=1;i<=total;i++){
            bloomFilter.add(i);
        }
        //检查、打印并输出特定的元素在布隆过滤器是否存在
        log.info("该布隆过滤器是否包含数据1：{}",bloomFilter.contains(1));
        log.info("该布隆过滤器是否包含数据-1：{}",bloomFilter.contains(-1));
        log.info("该布隆过滤器是否包含数据10000：{}",bloomFilter.contains(10000));
        log.info("该布隆过滤器是否包含数据100000000：{}",bloomFilter.contains(100000000));
    }

    @Test
    public void test3() throws Exception{
        //定义Redis中的Key
        final String key="myBloomFilterDataV4";
        //创建布隆过滤器组件
        RBloomFilter<BloomDto> bloomFilter=redissonClient.getBloomFilter(key);
        //初始化布隆过滤器，预计统计元素数量为1000，期望误差率为0.01
        bloomFilter.tryInit(1000, 0.01);
        //初始化遍历往布隆过滤器添加对象
        BloomDto dto1=new BloomDto(1,"1");
        BloomDto dto2=new BloomDto(10,"10");
        BloomDto dto3=new BloomDto(100,"100");
        BloomDto dto4=new BloomDto(1000,"1000");
        BloomDto dto5=new BloomDto(10000,"10000");
        //往布隆过滤器添加对象
        bloomFilter.add(dto1);
        bloomFilter.add(dto2);
        bloomFilter.add(dto3);
        bloomFilter.add(dto4);
        bloomFilter.add(dto5);
        //检查、打印并输出特定的元素在布隆过滤器是否存在
        log.info("该布隆过滤器是否包含数据(1,\"1\")：{}",bloomFilter.contains(new BloomDto(1,"1")));
        log.info("该布隆过滤器是否包含数据(100,\"2\")：{}",bloomFilter.contains(new BloomDto(100,"2")));
        log.info("该布隆过滤器是否包含数据(1000,\"3\")：{}",bloomFilter.contains(new BloomDto(1000,"3")));
        log.info("该布隆过滤器是否包含数据(10000,\"10000\")：{}",bloomFilter.contains(new BloomDto(10000,"10000")));
    }


    //创建用户登录成功后发送消息的生产者实例
    @Autowired
    private UserLoginPublisher userLoginPublisher;

    @Test
    public void test4() throws Exception{
        UserLoginDto dto=new UserLoginDto();
        dto.setUserId(90001);
        dto.setUserName("a-xiu-luo");
        dto.setPassword("123456");
        userLoginPublisher.sendMsg(dto);
    }














    /**
     * 功能组件Map-RMap
     * @throws Exception
     */
    @Test
    public void test5() throws Exception{
        //定义存储于缓存中间件Redis的Key
        final String key="myRedissonRMap";
        //构造对象实例
        RMapDto dto1=new RMapDto(1,"map1");
        RMapDto dto2=new RMapDto(2,"map2");
        RMapDto dto3=new RMapDto(3,"map3");
        RMapDto dto4=new RMapDto(4,"map4");
        RMapDto dto5=new RMapDto(5,"map5");
        RMapDto dto6=new RMapDto(6,"map6");
        RMapDto dto7=new RMapDto(7,"map7");
        RMapDto dto8=new RMapDto(8,"map8");

        //获取映射RMap功能组件实例，并采用多种不同的方式将对象实例添加进映射RMap中
        RMap<Integer, RMapDto> rMap=redissonClient.getMap(key);
        //正常的添加元素
        rMap.put(dto1.getId(),dto1);
        //异步的方式添加元素
        rMap.putAsync(dto2.getId(),dto2);
        //添加元素之前判断是否存在,如果不存在才添加元素;否则不添加
        rMap.putIfAbsent(dto3.getId(),dto3);
        //添加元素之前判断是否存在,如果不存在才添加元素;否则不添加 - 异步的方式
        rMap.putIfAbsentAsync(dto4.getId(),dto4);
        //正常的添加元素-快速的方式
        rMap.fastPut(dto5.getId(),dto5);
        //正常的添加元素-快速异步的方式
        rMap.fastPutAsync(dto6.getId(),dto6);
        //添加元素之前判断是否存在,如果不存在才添加元素;否则不添加-异步的方式
        rMap.fastPutIfAbsent(dto7.getId(),dto7);
        //添加元素之前判断是否存在,如果不存在才添加元素;否则不添加 - 异步快速的方式
        rMap.fastPutIfAbsentAsync(dto8.getId(),dto8);

        log.info("---往映射数据结构RMap中添加数据元素完毕---");
    }

    @Test
    public void test6() throws Exception{
        log.info("---从映射数据结构RMap中获取数据元素开始---");

        //定义存储于缓存中间件Redis的Key
        final String key="myRedissonRMap";
        //获取映射RMap功能组件实例，并采用多种不同的方式将对象实例添加进映射RMap中
        RMap<Integer, RMapDto> rMap=redissonClient.getMap(key);
        //遍历获取并输出映射RMap数据结构中的元素-第一种方式
        Set<Integer> ids=rMap.keySet();
        Map<Integer,RMapDto> map=rMap.getAll(ids);
        log.info("元素列表：{} ",map);

        //待移除的元素Id
        final Integer removeId=6;
        rMap.remove(removeId);
        map=rMap.getAll(rMap.keySet());
        log.info("移除元素{}后的数据列表：{} ",removeId,map);

        //待移除的元素Id列表
        final Integer[] removeIds=new Integer[]{1,2,3};
        rMap.fastRemove(removeIds);
        map=rMap.getAll(rMap.keySet());
        log.info("移除元素{}后的数据列表：{} ",removeIds,map);
    }
    /**
     * 功能组件Map-RMap
     * @throws Exception
     */


    /**
     * 元素淘汰机制 + 本地缓存机制
     * @throws Exception
     */
    @Test
    public void test7() throws Exception{
        //定义存储于缓存中间件Redis的Key
        final String key="myRedissonMapCache";
        //获取映射缓存RMapCache功能组件实例
        RMapCache<Integer, RMapDto> rMap=redissonClient.getMapCache(key);

        //本地缓存-功能组件的获取方法
        //RLocalCachedMap<Integer,RMapDto> rMap=redisson.getLocalCachedMap(key, LocalCachedMapOptions.defaults());

        //构造对象实例
        RMapDto dto1=new RMapDto(1,"map1");
        RMapDto dto2=new RMapDto(2,"map2");
        RMapDto dto3=new RMapDto(3,"map3");
        RMapDto dto4=new RMapDto(4,"map4");


        //将对象元素添加进MapCache组件中
        rMap.putIfAbsent(dto1.getId(),dto1);
        //将对象元素添加进MapCache组件中-有效时间 ttl 设置为 10秒钟
        rMap.putIfAbsent(dto2.getId(),dto2,10, TimeUnit.SECONDS);
        //将对象元素添加进MapCache组件中
        rMap.putIfAbsent(dto3.getId(),dto3);
        //将对象元素添加进MapCache组件中-有效时间 ttl 设置为 5秒钟
        rMap.putIfAbsent(dto4.getId(),dto4,5,TimeUnit.SECONDS);

        //获取MapCache组件的所有Key
        Set<Integer> set=rMap.keySet();
        //获取MapCache组件存储的所有元素
        Map<Integer,RMapDto> resMap=rMap.getAll(set);
        log.info("元素列表：{} ",resMap);

        //等待5秒钟-MapCache存储的数据元素列表
        Thread.sleep(5000);
        resMap=rMap.getAll(rMap.keySet());
        log.info("等待5秒钟-元素列表：{} ",resMap);

        //等待10秒钟-MapCache存储的数据元素列表
        Thread.sleep(10000);
        resMap=rMap.getAll(rMap.keySet());
        log.info("等待10秒钟-元素列表：{} ",resMap);
    }

    /**
     * 集合Set-保证元素的唯一性 -RSortedSet
     * @throws Exception
     */

    @Test
    public void test8() throws Exception{
        //定义存储于缓存中间件Redis的Key
        //保证了元素的有序性
        final String key="myRedissonSortedSetV2";
        //创建对象实例
        RSetDto dto1=new RSetDto(1,"N1",20);
        RSetDto dto2=new RSetDto(2,"N2",18);
        RSetDto dto3=new RSetDto(3,"N3",21);
        RSetDto dto4=new RSetDto(4,"N4",19);
        RSetDto dto5=new RSetDto(5,"N5",22);

        //定义有序集合SortedSet实例
        RSortedSet<RSetDto> rSortedSet=redissonClient.getSortedSet(key);
        //设置有序集合SortedSet的元素比较器
        rSortedSet.trySetComparator(new RSetComparator());
        //将对象元素往集合中添加
        rSortedSet.add(dto1);
        rSortedSet.add(dto2);
        rSortedSet.add(dto3);
        rSortedSet.add(dto4);
        rSortedSet.add(dto5);

        //查看此时有序集合Set的元素列表
        Collection<RSetDto> result=rSortedSet.readAll();
        log.info("此时有序集合Set的元素列表：{} ",result);

    }

    /**
     * 集合Set-保证元素的唯一性 -ScoredSortedSet
     * @throws Exception
     */
    @Test
    public void test9() throws Exception{
        //定义存储于缓存中间件Redis的Key
        final String key="myRedissonScoredSortedSet";
        //创建对象实例
        RSetDto dto1=new RSetDto(1,"N1",10.0D);
        RSetDto dto2=new RSetDto(2,"N2",2.0D);
        RSetDto dto3=new RSetDto(3,"N3",8.0D);
        RSetDto dto4=new RSetDto(4,"N4",6.0D);

        //定义得分排序集合ScoredSortedSet实例
        RScoredSortedSet<RSetDto> rScoredSortedSet=redissonClient.getScoredSortedSet(key);

        //往得分排序集合ScoredSortedSet添加对象元素
        rScoredSortedSet.add(dto1.getScore(),dto1);
        rScoredSortedSet.add(dto2.getScore(),dto2);
        rScoredSortedSet.add(dto3.getScore(),dto3);
        rScoredSortedSet.add(dto4.getScore(),dto4);

        //查看此时得分排序集合ScoredSortedSet的元素列表
        //可以通过SortOrder指定读取出的元素是正序还是倒序
        Collection<RSetDto> result=rScoredSortedSet.readSortAlpha(SortOrder.DESC);
        log.info("此时得分排序集合ScoredSortedSet的元素列表-从大到小：{} ",result);

        //获取对象元素在集合中的位置-相当于获取排名
        //rank()方法默认采用的是“正序”的方式
        //而且得到的排序值是从0开始算的,可以 加1
        log.info("获取对象元素的排名：对象元素={},从大到小排名={} ",dto1,rScoredSortedSet.revRank(dto1)+1);
        log.info("获取对象元素的排名：对象元素={},从大到小排名={} ",dto2,rScoredSortedSet.revRank(dto2)+1);
        log.info("获取对象元素的排名：对象元素={},从大到小排名={} ",dto3,rScoredSortedSet.revRank(dto3)+1);
        log.info("获取对象元素的排名：对象元素={},从大到小排名={} ",dto4,rScoredSortedSet.revRank(dto4)+1);

        log.info("\n");
        //获取对象元素在排名集合中的得分
        log.info("获取对象元素在排名集合中的得分：对象元素={},得分={} ",dto1,rScoredSortedSet.getScore(dto1));
        log.info("获取对象元素在排名集合中的得分：对象元素={},得分={} ",dto2,rScoredSortedSet.getScore(dto2));
        log.info("获取对象元素在排名集合中的得分：对象元素={},得分={} ",dto3,rScoredSortedSet.getScore(dto3));
        log.info("获取对象元素在排名集合中的得分：对象元素={},得分={} ",dto4,rScoredSortedSet.getScore(dto4));
    }


    @Autowired
    private MqDelayQueuePublisher mqDelayQueuePublisher;

    /**
     * @throws Exception
     */
    @Test
    public void test10() throws Exception{
        log.info("首次的结果： ");

        /*final String key="testSortedSetKey1";
        RSortedSet<PraiseRankDto> set=redissonClient.getSortedSet(key);
        set.trySetComparator(new PraiseTotalComparator());

        PraiseRankDto dto1=new PraiseRankDto(1,10L);
        PraiseRankDto dto2=new PraiseRankDto(2,15L);
        PraiseRankDto dto3=new PraiseRankDto(3,20L);
        set.add(dto1);
        set.add(dto2);
        set.add(dto3);*/
    }

    @Test
    public void test11() throws Exception{


    }

    @Test
    public void test12() throws Exception{


    }







}





























