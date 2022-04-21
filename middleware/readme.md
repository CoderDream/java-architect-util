### RabbitMQ
1. [erlang下载](https://github.com/erlang/otp/releases/tag/OTP-24.3.3)
2. [RabbitMQ下载页面](https://www.rabbitmq.com/install-windows.html)
3. [rabbitmq-server-3.9.15 下载地址](https://github.com/rabbitmq/rabbitmq-server/releases/download/v3.9.15/rabbitmq-server-3.9.15.exe)
4. [Win10 安装rabbitMQ详细步骤](https://blog.csdn.net/qq_39915083/article/details/107034747)



### MySQL


1. [已解决：com.mysql.jdbc.exceptions.jdbc4.MySQLNonTransientConnectionException](https://blog.csdn.net/ray2580/article/details/106863659)


### JMeter
1. [Jmeter发送Json请求](https://www.cnblogs.com/testway/p/9448851.html)
2. [jmeter之发送json数据的post请求](https://www.jianshu.com/p/20fac18f478f)
3. [jmeter将上一个接口返回值作为下一个接口的请求参数](https://blog.csdn.net/steven_ing/article/details/119890198)
4. [jmeter json提取器的用法(上一个接口返回值作为下一个接口的请求参数)](https://blog.csdn.net/qq_20737561/article/details/122146747)



### 抢红包-加分布式锁

```java
/**
 * 加分布式锁的情况
 * 抢红包-分“点”与“抢”处理逻辑
 *
 * @param userId 当前用户 ID
 * @param redId 红包全局唯一标识串
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
        // 上锁:一个红包每个人只能抢一次随机金额；一个人每次只能抢到红包的一次随机金额  即要永远保证 1对1 的关系
        // 加分布式锁：一个红包每个人只能抢到随机金额。即要永远保证一对一的构造缓存中的 Key（加入userId）
        final String lockKey = redId + userId + "-lock";
        // 调用 serIfAbsent() 方法，间接实现分布式锁
        Boolean lock = valueOperations.setIfAbsent(lockKey, redId);
        // 设定改分布式锁的过期时间为 24 小时
        redisTemplate.expire(lockKey, 24L, TimeUnit.HOURS);
        try {
            // 表示当前线程获取到了该分布式锁
            if (lock) {
                //"抢红包"-且红包有钱
                Object value = redisTemplate.opsForList().rightPop(redId);
                // value 不为 null，则表示当前弹出的红包金额不为 Null，即红包金额不为0，进而表示当前用户抢到了一个红包了，则可以进入后续的更新缓存
                // 与记录信息入数据库了
                if (value != null) {
                    // 构造红包记录Key
                    String redTotalKey = redId + ":total";
                    // 首先更新缓存系统中剩余红包个数，即红包个数减 1
                    Integer currTotal = valueOperations.get(redTotalKey) != null ? (Integer) valueOperations.get(
                        redTotalKey) : 0;
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
        } catch (Exception e) {
            throw new Exception("系统异常-抢红包-加分布式锁失败!");
        }
    }
    return null;
}
```



### 二倍均值法生成红包

```
剩余总金额M	剩余总人数N	（N-1）是否大于0	随机区间（0，M/N*2）	随机金额R	剩余总金额（M=M-R）	此时总人数（N=N-1)
100	5	4	40	38	62	4
62	4	3	31	25	37	3
37	3	2	24.66666667	20	17	2
17	2	1	17	15	2	1

```
