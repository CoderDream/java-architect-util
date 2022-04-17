package com.coderdream.middleware.server.service.redis;

import com.coderdream.middleware.server.dto.RedPacketDto;
import com.coderdream.middleware.server.service.IRedPacketService;
import com.coderdream.middleware.server.service.IRedService;
import com.coderdream.middleware.server.utils.RedPacketUtil;
import com.coderdream.middleware.server.utils.SnowFlake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 红包业务逻辑处理接口，实现类
 */
@Service
public class RedPacketService implements IRedPacketService {
    /**
     * 日志
     */
    private static final Logger log = LoggerFactory.getLogger(RedPacketService.class);

    /**
     * 存储至缓存系统 Redis 时定义的 Key 前缀
     */
    private static final String keyPrefix = "redis:red:packet:";

    /**
     * 雪花
     */
    private final SnowFlake snowFlake = new SnowFlake(2, 3);

    /**
     * 定义 Redis 操作 Bean 组件
     */
    @Resource
    private RedisTemplate redisTemplate;

    /**
     * 自动注入红包业务逻辑处理过程数据记录接口服务
     */
    @Resource
    private IRedService redService;

    /**
     * 发红包
     *
     * @throws Exception
     */
    @Override
    public String handOut(RedPacketDto dto) throws Exception {
        // 判断参数的合法性
        if (dto.getTotal() > 0 && dto.getAmount() > 0) {
            // 采用二倍均值法生成随机金额列表
            List<Integer> list = RedPacketUtil.divideRedPackage(dto.getAmount(), dto.getTotal());
            // 生成红包全局唯一标识，并将随机金额、个数存入缓存
            String timestamp = String.valueOf(System.nanoTime());
            // 根据缓存 key 的前缀与其他信息拼接成一个新的用于存储随机金额列表的 key
            String redId = new StringBuffer(keyPrefix).append(dto.getUserId()).append(":").append(timestamp).toString();
            // 将随机金额列表存入缓存列表中
            redisTemplate.opsForList().leftPushAll(redId, list);
            // 根据缓存 key 的前缀与其他信息拼接成一个新的用于存储红包总数的 Key
            String redTotalKey = redId + ":total";
            // 将红包总数存入缓存中
            redisTemplate.opsForValue().set(redTotalKey, dto.getTotal());
            // 异步记录红包的全局唯一标识串、红包个数与随机金额列表存入数据库
            redService.recordRedPacket(dto, redId, list);
            // 将红包的全局唯一标识串返回给前端
            return redId;
        } else {
            throw new Exception("系统异常-分发红包-参数不合法!");
        }
    }

    /**
     * 加分布式锁的情况
     * 抢红包-分“点”与“抢”处理逻辑
     *
     * @param userId 当前用户 ID
     * @param redId 红包全局唯一标识串
     * @return 返回抢到的红包金额或者抢不到红包金额的 Null
     * @throws Exception
     */
//    @Override
//    public BigDecimal rob(Integer userId, String redId) throws Exception {
//        // 定义 Redis 操作组件的值操作方法
//        ValueOperations valueOperations = redisTemplate.opsForValue();
//        // 在处理用户抢红包之前，需要先判断一下当前用户是否已经抢过该红包了
//        // 如果已经抢过了，则直接返回红包金额，并在前端显示出来
//        Object obj = valueOperations.get(redId + userId + ":rob");
//        if (obj != null) {
//            return new BigDecimal(obj.toString());
//        }
//        // “点红包”业务逻辑，主要用于判断缓存系统中是否仍然有红包，即红包剩余个数是否大于 0
//        Boolean res = click(redId);
//        // 表示 res 为 true，则可以进入“拆红包”业务逻辑
//        if (res) {
//            // 上锁:一个红包每个人只能抢一次随机金额；一个人每次只能抢到红包的一次随机金额  即要永远保证 1对1 的关系
//            final String lockKey = redId + userId + "-lock";
//            Boolean lock = valueOperations.setIfAbsent(lockKey, redId);
//            redisTemplate.expire(lockKey, 24L, TimeUnit.HOURS);
//            try {
//                if (lock) {
//
//                    //"抢红包"-且红包有钱
//                    Object value = redisTemplate.opsForList().rightPop(redId);
//                    if (value != null) {
//                        //红包个数减一
//                        String redTotalKey = redId + ":total";
//
//                        Integer currTotal = valueOperations.get(redTotalKey) != null ? (Integer) valueOperations.get(redTotalKey) : 0;
//                        valueOperations.set(redTotalKey, currTotal - 1);
//
//                        //将红包金额返回给用户的同时，将抢红包记录入数据库与缓存
//                        BigDecimal result = new BigDecimal(value.toString()).divide(new BigDecimal(100));
//                        redService.recordRobRedPacket(userId, redId, new BigDecimal(value.toString()));
//
//                        valueOperations.set(redId + userId + ":rob", result, 24L, TimeUnit.HOURS);
//
//                        log.info("当前用户抢到红包了：userId={} key={} 金额={} ", userId, redId, result);
//                        return result;
//                    }
//
//                }
//            } catch (Exception e) {
//                throw new Exception("系统异常-抢红包-加分布式锁失败!");
//            }
//        }
//        return null;
//    }

    /**
     * 不加分布式锁的情况
     * 抢红包-分“点”与“抢”处理逻辑
     *
     * @param userId 当前用户 ID
     * @param redId  红包全局唯一标识串
     * @return 返回抢到的红包金额或者抢不到红包金额的 Null
     * @throws Exception
     */
    @Override
    public BigDecimal rob(Integer userId, String redId) throws Exception {
        // 定义 Redis 操作组件的值操作方法
        ValueOperations valueOperations = redisTemplate.opsForValue();
        // 在处理用户抢红包之前，需要先判断一下当前用户是否已经抢过该红包了
        // 如果已经抢过了，则直接返回红包金额，并在前端显示出来
        Object obj = valueOperations.get(redId + userId + ":rob");
        if (obj != null) {
            return new BigDecimal(obj.toString());
        }
        // “点红包”业务逻辑，主要用于判断缓存系统中是否仍然有红包，即红包剩余个数是否大于 0
        Boolean res = click(redId);
        // 表示 res 为 true，则可以进入“拆红包”业务逻辑
        if (res) {
            // "抢红包"-且红包有钱
            // 首先从小红包随机金额列表中弹出一个随机金额
            Object value = redisTemplate.opsForList().rightPop(redId);
            // value 不为 null，则表示当前弹出的红包金额不为 Null，即红包金额不为0，进而表示当前用户抢到了一个红包了，则可以进入后续的更新缓存
            // 与记录信息入数据库了
            if (value != null) {
                // 构造红包记录Key
                String redTotalKey = redId + ":total";
                // 首先更新缓存系统中剩余红包个数，即红包个数减 1
                Integer currTotal = valueOperations.get(redTotalKey) != null ? (Integer) valueOperations.get(redTotalKey) : 0;
                valueOperations.set(redTotalKey, currTotal - 1);
                // 将红包金额返回给用户的同时，将抢红包记录入数据库与缓存
                BigDecimal result = new BigDecimal(value.toString()).divide(new BigDecimal(100));
                // 记录抢到红包时用户的账号信息以及抢到的金额等信息入数据库
                redService.recordRobRedPacket(userId, redId, new BigDecimal(value.toString()));
                // 将当前抢到红包的用户信息放置进缓存系统中，用于表示当前用户已经抢过红包了
                valueOperations.set(redId + userId + ":rob", result, 24L, TimeUnit.HOURS);
                // 输出当前用户抢到红包的记录信息
                log.info("当前用户抢到红包了：userId={} key={} 金额={} ", userId, redId, result);
                // 将结果返回
                return result;
            }
        }
        // null 则表示当前用户没有抢到红包
        return null;
    }

    /**
     * 点红包-返回true，则代表红包还有，个数>0
     *
     * @param redId 红包全局唯一标识串
     * @return 是否还有红包
     * @throws Exception
     */
    private Boolean click(String redId) throws Exception {
        // 定义 Redis 的 Bean 操作组件（值操作组件）
        ValueOperations valueOperations = redisTemplate.opsForValue();
        // 定义用于查询缓存系统中红包剩余个数的 Key
        String redTotalKey = redId + ":total";
        // 获取缓存系统 Redis 中红包剩余个数
        Object total = valueOperations.get(redTotalKey);
        // 判断红包剩余个数 total 是否大于 0 ，如果大于 0， 则返回 true，表示还有红包；返回 false，则表示已经没有红包可抢了
        return total != null && Integer.valueOf(total.toString()) > 0;
    }
}