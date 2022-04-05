package com.debug.middleware.server.service.redisson.praise;

import com.debug.middleware.model.entity.Praise;
import com.debug.middleware.model.mapper.PraiseMapper;
import com.debug.middleware.server.dto.PraiseDto;
import com.debug.middleware.model.dto.PraiseRankDto;
import com.debug.middleware.server.service.IPraiseService;
import com.debug.middleware.server.service.IRedisPraise;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**点赞处理接口-实现类
 * @author: zhonglinsen
 * @date: 2019/1/15
 */
@Service
public class PraiseService implements IPraiseService {
    //定义日志
    private static final Logger log= LoggerFactory.getLogger(PraiseService.class);
    //定义点赞博客时加分布式锁对应的Key
    private static final String keyAddBlogLock="RedisBlogPraiseAddLock";
    //定义读取环境变量的实体env
    @Autowired
    private Environment env;
    //点赞信息实体操作接口Mapper
    @Autowired
    private PraiseMapper praiseMapper;
    //定义缓存博客点赞Redis服务
    @Autowired
    private IRedisPraise redisPraise;
    //创建Redisson客户端操作实例
    @Autowired
    private RedissonClient redissonClient;

    /**
     * 点赞博客-无锁
     * @param dto
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addPraise(PraiseDto dto) throws Exception {
        //根据博客id-用户id查询当前用户的点赞记录
        Praise p=praiseMapper.selectByBlogUserId(dto.getBlogId(),dto.getUserId());
        //判断是否有点赞记录
        if (p==null){
            //如果没有点赞记录-则创建博客点赞实体信息
            p=new Praise();
            //将前端提交的博客点赞请求相应的字段取值复制到新创建的点赞实体相应的字段取值中
            BeanUtils.copyProperties(dto,p);
            //定义点赞时间
            Date praiseTime=new Date();
            p.setPraiseTime(praiseTime);
            //设置点赞记录的状态-1为正常点赞
            p.setStatus(1);

            //插入用户点赞记录
            int total=praiseMapper.insertSelective(p);
            //判断是否成功插入
            if (total>0){
                //如果成功插入博客点赞记录，则输出打印相应的信息，并将用户点赞记录添加至缓存中
                log.info("---点赞博客-{}-无锁-插入点赞记录成功---",dto.getBlogId());
                redisPraise.cachePraiseBlog(dto.getBlogId(),dto.getUserId(),1);


                //触发更新缓存中的排行榜
                this.cachePraiseTotal();
            }
        }
    }

    /**
     * 点赞博客-加分布式锁-针对同一用户高并发重复点赞的情况
     * @param dto 请求信息
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addPraiseLock(PraiseDto dto) throws Exception {
        //定义用于获取分布式锁的Redis的Key
        final String lockName=keyAddBlogLock+dto.getBlogId()+"-"+dto.getUserId();
        //获取一次性锁对象
        RLock lock=redissonClient.getLock(lockName);
        //上锁-并在10秒钟自动释放-可用于避免Redis节点宕机时出现死锁
        lock.lock(10L, TimeUnit.SECONDS);
        try {
            //TODO:
            //根据博客id-用户id查询当前用户的点赞记录
            Praise p=praiseMapper.selectByBlogUserId(dto.getBlogId(),dto.getUserId());
            //判断是否有点赞记录
            if (p==null){
                //如果没有点赞记录-则创建博客点赞实体信息
                p=new Praise();
                //将前端提交的博客点赞请求相应的字段取值复制到新创建的点赞实体相应的字段取值中
                BeanUtils.copyProperties(dto,p);
                //定义点赞时间
                Date praiseTime=new Date();
                p.setPraiseTime(praiseTime);
                //设置点赞记录的状态-1为正常点赞
                p.setStatus(1);

                //插入用户点赞记录
                int total=praiseMapper.insertSelective(p);
                //判断是否成功插入
                if (total>0){
                    //如果成功插入博客点赞记录，则输出打印相应的信息，并将用户点赞记录添加至缓存中
                    log.info("---点赞博客-{}-加分布式锁-插入点赞记录成功---",dto.getBlogId());
                    redisPraise.cachePraiseBlog(dto.getBlogId(),dto.getUserId(),1);


                    //触发更新缓存中的排行榜
                    this.cachePraiseTotal();
                }
            }
        }catch (Exception e){
            //如果出现异常-则直接抛出
            throw e;
        }finally {
            if (lock!=null){
                //操作完成-主动释放锁
                lock.unlock();
            }
        }
    }





    /**
     * 取消点赞博客
     * @param dto
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void cancelPraise(PraiseDto dto) throws Exception {
        //判断当前参数的合法性
        if (dto.getBlogId()!=null && dto.getUserId()!=null){
            //当前用户取消点赞博客-更新相应的记录信息
            int result=praiseMapper.cancelPraiseBlog(dto.getBlogId(),dto.getUserId());
            //判断是否更新成功
            if (result>0){
                //result>0表示更新成功，则同时更新缓存中相应博客的用户点赞记录
                log.info("---取消点赞博客-{}-更新点赞记录成功---",dto.getBlogId());
                redisPraise.cachePraiseBlog(dto.getBlogId(),dto.getUserId(),0);


                //触发更新缓存中的排行榜
                this.cachePraiseTotal();
            }
        }
    }

    /**
     * 获取博客的点赞数量
     * @param blogId 博客id
     * @return
     * @throws Exception
     */
    @Override
    public Long getBlogPraiseTotal(Integer blogId) throws Exception {
        //直接调用Redis服务封装好的“获取博客点赞数量”的方法即可
        return redisPraise.getCacheTotalBlog(blogId);
    }



    /**
     * 获取博客点赞总数排行榜-采用缓存
     * @return 返回排行榜
     * @throws Exception
     */
    @Override
    public Collection<PraiseRankDto> getRankWithRedisson() throws Exception {
        return redisPraise.getBlogPraiseRank();
    }

    /**
     * 获取博客点赞总数排行榜-不采用缓存
     * @return 返回排行榜
     * @throws Exception
     */
    @Override
    public Collection<PraiseRankDto> getRankNoRedisson() throws Exception {
        return praiseMapper.getPraiseRank();
    }

    /**
     * 将当前博客id对应的点赞总数构造为实体，并添加进SortedSet中 - 构造排行榜
     * 记录当前博客id-点赞总数-实体排行榜
     */
    private void cachePraiseTotal(){
        try {
            redisPraise.rankBlogPraise();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}







































