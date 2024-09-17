

## 4.3　“红包金额”随机生成算法实战

### 4.3.5　红包随机金额生成算法自测

1. 二倍均值法生成红包随机金额的流程 

 ![image-20220417181932344](images\image-20220417181932344.png)

2. 源代码

```java
/**
* 发红包算法，金额参数以分为单位
*
* @param totalAmount 红包总金额（单位为分）
* @param totalPeopleNum 总人数
* @return
*/
public static List<Integer> divideRedPackage(Integer totalAmount, Integer totalPeopleNum) {
    // 用于存储每次产生的小红包随机金额列表，金额单位为分
    List<Integer> amountList = new ArrayList<>();
    // 判断总金额和总人数参数的合法性
    if (totalAmount > 0 && totalPeopleNum > 0) {
        // 记录剩余的总金额，初始化时即为红包的总金额
        Integer restAmount = totalAmount;
        // 记录剩余的总人数，初始化时即为红包的总人数
        Integer restPeopleNum = totalPeopleNum;
        // 定义产生随机数的实例对象
        Random random = new Random();
        // 不断循环遍历、迭代更新地产生随机金额，直到 N-1 ≤ 0
        for (int i = 0; i < totalPeopleNum - 1; i++) {
            // 随机范围：[1，剩余人均金额的两倍)，左闭右开，amount 即为产生的随机金额 R，单位为分
            int amount = random.nextInt(restAmount / restPeopleNum * 2 - 1) + 1;
            // 更新剩余的总金额 M = M - R
            restAmount -= amount;
            // 更新剩余的总人数 N = N - 1
            restPeopleNum--;
            // 将产生的随机金额添加进列表中
            amountList.add(amount);
        }
        // 循环完毕，剩余的金额即为最后一个随机金额，也需要将其添加进列表中
        amountList.add(restAmount);
    }
    // 将最终产生的随机金额列表返回
    return amountList;
}
```



```java
public void one() throws Exception {
    // 总金额单位为分，在这里假设总金额为 1000 分，即 10 元
    Integer amout = 1000;
    // 总人数，即红包总个数，在这里假设为10个
    Integer total = 10;
    // 得到随机金额列表
    List<Integer> list = RedPacketUtil.divideRedPackage(amout, total);
    log.info("总金额={}分，总个数={}个", amout, total);

    // 用于统计生成的随机金额之和是否等于总金额
    Integer sum = 0;
    // 遍历输出每个随机金额
    for (Integer i : list) {
        log.info("随机金额为：{}分，即 {}元", i, new BigDecimal(i.toString()).divide(new BigDecimal(100)));
        sum += i;
    }
    log.info("所有随机金额叠加之和={}分", sum);
}
```

3. 运行结果

![image-20220417180524353](images\image-20220417180524353.png)

## 4.4　“发红包”模块实战

### 4.4.1　业务模块分析

 ![image-20220417200447775](images\image-20220417200447775.png)

### 4.4.2　整体流程实战

1. 源代码 

   RedPacketController.java

```java
/**
* 发红包请求，请求方法为 POST，请求参数采用 JSON 格式进行提交
*/
@RequestMapping(value = prefix + "/hand/out", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
public BaseResponse handOut(@Validated @RequestBody RedPacketDto dto, BindingResult result) {
    // 参数校验
    if (result.hasErrors()) {
        return new BaseResponse(StatusCode.InvalidParams);
    }
    BaseResponse response = new BaseResponse(StatusCode.Success);
    try {
        // 核心业务逻辑处理服务，最终返回红包全局唯一标识串
        String redId = redPacketService.handOut(dto);
        // 将红包全局唯一标识串返回前端
        response.setData(redId);
    } catch (Exception e) {
        // 如果报异常，则输出日志并返回相应的错误信息
        log.error("发红包发生异常：dto={} ", dto, e.fillInStackTrace());
        response = new BaseResponse(StatusCode.Fail.getCode(), e.getMessage());
    }
    return response;
}
```

 RedPacketService.java

```java
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
```



```java
/**
 * 发红包记录
 *
 * @param dto   红包总金额+个数
 * @param redId 红包全局唯一标识串
 * @param list  红包随机金额列表
 * @throws Exception
 */
@Override
@Async
@Transactional(rollbackFor = Exception.class)
public void recordRedPacket(RedPacketDto dto, String redId, List<Integer> list) throws Exception {
    // 定义实体类对象
    RedRecord redRecord = new RedRecord();
    // 设置字段的取值信息
    redRecord.setUserId(dto.getUserId());
    redRecord.setRedPacket(redId);
    redRecord.setTotal(dto.getTotal());
    redRecord.setAmount(BigDecimal.valueOf(dto.getAmount()));
    redRecord.setCreateTime(new Date());
    // 将对象信息插入数据库
    redRecordMapper.insertSelective(redRecord);
    // 定义红包随机金额明细实体类对象
    RedDetail detail;
    // 遍历随机金额列表，将金额等信息设置到相应的字段中
    for (Integer i : list) {
        detail = new RedDetail();
        detail.setRecordId(redRecord.getId());
        detail.setAmount(BigDecimal.valueOf(i));
        detail.setCreateTime(new Date());
        // 将对象信息插入数据库
        redDetailMapper.insertSelective(detail);
    }
}
```



### 4.4.3　业务模块自测

1. request

```json
{
    "userId": 10010,
    "total": 10,
    "amount": 1000
}
```

2. response

```json
{
    "code": 0,
    "msg": "成功",
    "data": "redis:red:packet:10010:172056319457200"
}
```

3. Postman

 ![image-20220417201949065](images\image-20220417201949065.png)



4. red_record

 ![image-20220417202136062](images\image-20220417202136062.png)

5. red_detail

 ![image-20220417202219320](images\image-20220417202219320.png)

6. amount

> SELECT sum(amount) FROM `red_detail` WHERE record_id=17 and is_active=1

 ![image-20220417202302625](images\image-20220417202302625.png)

## 4.5　“抢红包”模块实战

### 4.5.1　业务模块分析

 ![image-20220417203122248](images\image-20220417203122248.png)

### 4.5.2　整体流程实战

1. 源代码 

*  RedPacketController.java

```java
/**
 * 处理抢红包请求：接收当前用户 ID 和红包全局唯一标识串参数
 */
@RequestMapping(value = prefix + "/rob", method = RequestMethod.GET)
public BaseResponse rob(@RequestParam Integer userId, @RequestParam String redId) {
    // 定义相应对象
    BaseResponse response = new BaseResponse(StatusCode.Success);
    try {
        // 调用红包业务逻辑处理接口中的抢红包方法，最终返回抢到的红包的金额
        // 单位为元（不为Null时表示抢到了，否则代表已经被抢完了
        BigDecimal result = redPacketService.rob(userId, redId);
        if (result != null) {
            // 将抢到的红包金额返回到前端
            response.setData(result);
        } else {
            // 没有抢到红包，即已经被抢完了
            response = new BaseResponse(StatusCode.Fail.getCode(), "红包已被抢完!");
        }
    } catch (Exception e) {
        // 处理过程如果发生异常，则输出异常信息并返回给前端
        log.error("抢红包发生异常：userId={} redId={}", userId, redId, e.fillInStackTrace());
        response = new BaseResponse(StatusCode.Fail.getCode(), e.getMessage());
    }
    // 返回处理结果给前端
    return response;
}
```

* RedPacketService.java

```java
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
```

* RedService.java

```java
/**
 * 抢红包记录
 * 成功抢到红包时将当前用户账号信息以及对应的红包金额等信息记录进数据库中
 *
 * @param userId 用户 ID
 * @param redId  红包全局唯一标识串
 * @param amount 抢到的红包金额
 * @throws Exception
 */
@Override
@Async
public void recordRobRedPacket(Integer userId, String redId, BigDecimal amount) throws Exception {
    // 定义记录抢到红包时录入相关信息的实体对象，并设置相应字段的取值
    RedRobRecord redRobRecord = new RedRobRecord();
    redRobRecord.setUserId(userId); // 设置用户 ID
    redRobRecord.setRedPacket(redId); // 设置红包全局唯一标识串
    redRobRecord.setAmount(amount); // 设置抢到的红包金额
    redRobRecord.setRobTime(new Date());// 设置抢到的时间
    // 将实体对象信息插入数据库中
    redRobRecordMapper.insertSelective(redRobRecord);
}
```

* 执行第1次

![image-20220417211748317](images\image-20220417211748317.png)

* 执行第11次

![image-20220417211805340](images\image-20220417211805340.png)

* 控制台信息

![image-20220417211727298](images\image-20220417211727298.png)

* red_detail 红包生成记录

 ![image-20220417212045325](images\image-20220417212045325.png)

* red_rob_record 抢红包记录

 ![image-20220417212111657](images\image-20220417212111657.png)

## 4.6　JMeter压测高并发抢红包

单击“文件”创建一个测试计划，然后在该测试计划下新建线程组，最后在线程组下新建“HTTP请求”，“CSV数据文件设置”以及查看结果树。

> 抢红包系统高并发测试
>
> 

* HTTP请求

![image-20220419223411765](images\image-20220419223411765.png)

* CSV数据文件及设置

![image-20220419223533119](images\image-20220419223533119.png)

* 控制台信息

![image-20220419223250342](images\image-20220419223250342.png)

* 数据库信息

![image-20220419224151058](images\image-20220419224151058.png)



# END