package com.debug.middleware.server.service.lock;/**
 * Created by Administrator on 2019/4/17.
 */

import com.debug.middleware.model.entity.*;
import com.debug.middleware.model.mapper.*;
import com.debug.middleware.server.controller.lock.dto.BookRobDto;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 书籍抢购服务
 * @Author:debug (SteadyJack)
 * @Date: 2019/4/21 23:36
 **/
@Service
public class BookRobService {
    //定义日志实例
    private static final Logger log= LoggerFactory.getLogger(BookRobService.class);
    //定义书籍库存实体操作接口Mapper实例
    @Autowired
    private BookStockMapper bookStockMapper;
    //定义书籍抢购实体操作接口Mapper实例
    @Autowired
    private BookRobMapper bookRobMapper;

    /**
     * 处理书籍抢购逻辑-不加分布式锁
     * @param dto
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public void robWithNoLock(BookRobDto dto) throws Exception{
        //根据书籍编号查询记录
        BookStock stock=bookStockMapper.selectByBookNo(dto.getBookNo());
        //统计每个用户每本书的抢购数量
        int total=bookRobMapper.countByBookNoUserId(dto.getUserId(),dto.getBookNo());

        //商品记录存在、库存充足，而且用户还没抢购过本书，则代表当前用户可以抢购
        if (stock!=null && stock.getStock()>0 && total<=0){
            log.info("---处理书籍抢购逻辑-不加分布式锁---,当前信息：{} ",dto);

            //当前用户抢购到书籍，库存减一
            int res=bookStockMapper.updateStock(dto.getBookNo());
            //更新库存成功后，需要添加抢购记录
            if (res>0){
                //创建书籍抢购记录实体信息
                BookRob entity=new BookRob();
                //将提交的用户抢购请求实体信息中对应的字段取值
                //复制到新创建的书籍抢购记录实体的相应字段中
                BeanUtils.copyProperties(dto,entity);
                //设置抢购时间
                entity.setRobTime(new Date());
                //插入用户注册信息
                bookRobMapper.insertSelective(entity);
            }
        }else {
            //如果不满足上述的任意一个if条件，则抛出异常
            throw new Exception("该书籍库存不足!");
        }
    }





    //定义ZooKeeper客户端CuratorFramework实例
    @Autowired
    private CuratorFramework client;
    //ZooKeeper分布式锁的实现原理是由ZNode节点的创建与删除跟监听机制构成的
    //而ZNoe节点将对应一个具体的路径-跟Unix文件夹路径类似-需要以 / 开头
    private static final String pathPrefix="/middleware/zkLock/";

    /**
     * 处理书籍抢购逻辑-加ZooKeeper分布式锁
     * @param dto
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public void robWithZKLock(BookRobDto dto) throws Exception{
        //创建ZooKeeper互斥锁组件实例，需要将CuratorFramework实例、精心构造的共享资源 作为构造参数
        InterProcessMutex mutex=new InterProcessMutex(client,pathPrefix+dto.getBookNo()+dto.getUserId()+"-lock");
        try {
            //采用互斥锁组件尝试获取分布式锁-其中尝试的最大时间在这里设置为15s
            //当然,具体的情况需要根据实际的业务而定
            if (mutex.acquire(15L, TimeUnit.SECONDS)){
                //TODO：真正的核心处理逻辑

                //根据书籍编号查询记录
                BookStock stock=bookStockMapper.selectByBookNo(dto.getBookNo());
                //统计每个用户每本书的抢购数量
                int total=bookRobMapper.countByBookNoUserId(dto.getUserId(),dto.getBookNo());

                //商品记录存在、库存充足，而且用户还没抢购过本书，则代表当前用户可以抢购
                if (stock!=null && stock.getStock()>0 && total<=0){
                    log.info("---处理书籍抢购逻辑-加ZooKeeper分布式锁---,当前信息：{} ",dto);

                    //当前用户抢购到书籍，库存减一
                    int res=bookStockMapper.updateStock(dto.getBookNo());
                    //更新库存成功后，需要添加抢购记录
                    if (res>0){
                        //创建书籍抢购记录实体信息
                        BookRob entity=new BookRob();
                        //将提交的用户抢购请求实体信息中对应的字段取值
                        //复制到新创建的书籍抢购记录实体的相应字段中
                        entity.setUserId(dto.getUserId());
                        entity.setBookNo(dto.getBookNo());
                        //设置抢购时间
                        entity.setRobTime(new Date());
                        //插入用户注册信息
                        bookRobMapper.insertSelective(entity);
                    }
                }else {
                    //如果不满足上述的任意一个if条件，则抛出异常
                    throw new Exception("该书籍库存不足!");
                }

            }else{
                throw new RuntimeException("获取ZooKeeper分布式锁失败!");
            }
        }catch (Exception e){
            throw e;
        }finally {
            //TODO：不管发生何种情况，在处理完核心业务逻辑之后，需要释放该分布式锁
            mutex.release();
        }
    }

    //定义Redisson的客户端操作实例
    @Autowired
    private RedissonClient redissonClient;

    /**
     * 处理书籍抢购逻辑-加Redisson分布式锁
     * @param dto
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public void robWithRedisson(BookRobDto dto) throws Exception{
        final String lockName="redissonTryLock-"+dto.getBookNo()+"-"+dto.getUserId();
        RLock lock=redissonClient.getLock(lockName);
        try {
            Boolean result=lock.tryLock(100,10,TimeUnit.SECONDS);
            if (result){
                //TODO：真正的核心处理逻辑

                //根据书籍编号查询记录
                BookStock stock=bookStockMapper.selectByBookNo(dto.getBookNo());
                //统计每个用户每本书的抢购数量
                int total=bookRobMapper.countByBookNoUserId(dto.getUserId(),dto.getBookNo());

                //商品记录存在、库存充足，而且用户还没抢购过本书，则代表当前用户可以抢购
                if (stock!=null && stock.getStock()>0 && total<=0){
                    //当前用户抢购到书籍，库存减一
                    int res=bookStockMapper.updateStockWithLock(dto.getBookNo());
                    //如果允许商品超卖-达成饥饿营销的目的，则可以调用下面的方法
                    //int res=bookStockMapper.updateStock(dto.getBookNo());

                    //更新库存成功后，需要添加抢购记录
                    if (res>0){
                        //创建书籍抢购记录实体信息
                        BookRob entity=new BookRob();
                        //将提交的用户抢购请求实体信息中对应的字段取值
                        //复制到新创建的书籍抢购记录实体的相应字段中
                        entity.setBookNo(dto.getBookNo());
                        entity.setUserId(dto.getUserId());
                        //设置抢购时间
                        entity.setRobTime(new Date());
                        //插入用户注册信息
                        bookRobMapper.insertSelective(entity);

                        log.info("---处理书籍抢购逻辑-加Redisson分布式锁---,当前线程成功抢到书籍：{} ",dto);
                    }
                }else {
                    //如果不满足上述的任意一个if条件，则抛出异常
                    throw new Exception("该书籍库存不足!");
                }
            }else{
                throw new Exception("----获取Redisson分布式锁失败!----");
            }
        }catch (Exception e){
            throw e;
        }finally {
            //TODO：不管发生何种情况，在处理完核心业务逻辑之后，需要释放该分布式锁
            if (lock!=null){
                lock.unlock();

                //在某些严格的业务场景下，也可以调用强制释放分布式锁的方法
                //lock.forceUnlock();
            }
        }
    }




}
































