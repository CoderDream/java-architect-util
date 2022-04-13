package com.debug.middleware.server.service.redisson.praise;

import com.debug.middleware.model.dto.PraiseRankDto;
import com.debug.middleware.model.mapper.PraiseMapper;
import com.debug.middleware.server.service.IRedisPraise;
import org.assertj.core.util.Strings;
import org.redisson.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 博客点赞处理服务
 * @author: zhonglinsen
 * @date: 2019/1/15
 */
@Component
public class RedisPraise implements IRedisPraise {
    //定义日志
    private static final Logger log= LoggerFactory.getLogger(RedisPraise.class);
    //定义点赞博客时缓存存储时的Key
    private static final String keyBlog= "RedisBlogPraiseMap";
    //创建Redisson客户端操作实例
    @Autowired
    private RedissonClient redissonClient;

    /**
     * 缓存当前用户点赞博客的记录
     * @param blogId 博客id
     * @param uId 用户id
     * @param status 点赞状态（1=正常点赞；0=取消点赞）
     * @throws Exception
     */
    @Override
    public void cachePraiseBlog(Integer blogId, Integer uId, Integer status) throws Exception {
        //创建用于获取分布式锁的Key
        final String lockName=new StringBuffer("blogRedissonPraiseLock").append(blogId).append(uId).append(status).toString();
        //获取分布式锁实例
        RLock rLock=redissonClient.getLock(lockName);
        //尝试获取分布式锁（可重入锁）
        Boolean res=rLock.tryLock(100,10, TimeUnit.SECONDS);
        try {
            //res为true代表已经获取到了锁
            if (res){
                //判断参数的合法性
                if (blogId!=null && uId!=null && status!=null){
                    //精心设计并构造存储至缓存中的key：为了具有唯一性，这里采用“博客id+用户id”的拼接作为key
                    final String key=blogId+":"+uId;
                    //定义Redisson的RMap映射数据结构实例
                    RMap<String,Integer> praiseMap=redissonClient.getMap(keyBlog);
                    //如果status=1，则代表当前用户执行点赞操作;
                    //如果status=0，则代表当前用户执行取消点赞操作
                    if (1==status){
                        //点赞操作-需要将对应的信息-这里是用户的id-添加进RMap中
                        praiseMap.putIfAbsent(key,uId);
                    }else if (0==status){
                        //取消点赞操作-需要将对应的信息-这里是唯一的那个key-从RMap中进行移除
                        praiseMap.remove(key);
                    }
                }
            }
        }catch (Exception e){
            throw e;
        }finally {
            //操作完毕，直接释放该锁
            if (rLock!=null){
                rLock.forceUnlock();
            }
        }
    }

    /**
     * 获取博客总的点赞数
     * @param blogId 博客id
     * @return
     * @throws Exception
     */
    @Override
    public Long getCacheTotalBlog(Integer blogId) throws Exception {
        //定义最终的返回值，初始时为0
        Long result=0L;
        //判断参数的合法性
        if (blogId!=null){
            //定义Redisson的RMap映射数据结构实例
            RMap<String,Integer> praiseMap=redissonClient.getMap(keyBlog);
            //获取RMap中所有的key-value，即键值对列表-map
            Map<String,Integer> dataMap=praiseMap.readAllMap();
            //判断取出来的键值对列表是否有值
            if (dataMap!=null && dataMap.keySet()!=null){
                //获取该map所有的 键  列表 - 每个 键 的取值是由 “博客id:用户id” 这样的格式构成
                Set<String> set=dataMap.keySet();
                Integer bId;
                //循环遍历其中所有的 键 列表，查看是否有以当前博客id开头的数据记录
                for (String key:set){
                    if (!Strings.isNullOrEmpty(key)){
                        //由于 每个 键 的取值是由 “博客id:用户id” 这样的格式构成;
                        //故而可以通过分隔 分隔符 得到：博客id 跟 用户id参数的值
                        String[] arr=key.split(":");
                        if (arr!=null && arr.length>0){
                            bId=Integer.valueOf(arr[0]);
                            //判断当前取出的 键 对应的 博客id 是否跟当前待比较的 博客id 相等，
                            //如果是，代表有一条点赞记录，则结果需要加1
                            if (blogId.equals(bId)){
                                result += 1;
                            }
                        }
                    }
                }
            }
        }
        //返回最终的统计结果
        return result;
    }








    //点赞实体Mapper操作接口实例
    @Autowired
    private PraiseMapper praiseMapper;

    /**
     * 博客点赞总数排行榜
     * @throws Exception
     */
    @Override
    public void rankBlogPraise() throws Exception {
        //定义用于缓存排行榜的Key
        final String key="praiseRankListKey";
        //根据数据库查询语句得到已经排好序的博客实体对象列表
        List<PraiseRankDto> list=praiseMapper.getPraiseRank();
        //判断列表中是否有数据
        if (list!=null && list.size()>0){
            //获取Redisson的列表组件RList实例
            RList<PraiseRankDto> rList=redissonClient.getList(key);
            //先清空缓存中的列表数据
            rList.clear();
            //将得到的最新的排行榜更新至缓存中
            rList.addAll(list);
        }
    }
    /**
     * 获得博客点赞排行榜
     * @throws Exception
     */
    @Override
    public List<PraiseRankDto> getBlogPraiseRank() throws Exception {
        //定义用于缓存排行榜的Key
        final String key="praiseRankListKey";
        //获取Redisson的列表组件RList实例
        RList<PraiseRankDto> rList=redissonClient.getList(key);
        //获取缓存中最新的排行榜
        return rList.readAll();
    }
}
































