# SpringBoot集成Mybatis-Plus

发布于2021-03-05 22:11:07

### 概述

[地址](https://mp.baomidou.com/)

> **目标**:MybatisPlus,为简化Mybatis开发而生,只做增强,不做改变



### 简介

> [MyBatis-Plus](https://github.com/baomidou/mybatis-plus)（简称 MP）是一个 [MyBatis](http://www.mybatis.org/mybatis-3/) 的增强工具，在 MyBatis 的基础上只做增强不做改变，为简化开发、提高效率而生。简单来说就是简化JDBC操作 愿景 我们的愿景是成为 MyBatis 最好的搭档，就像 [魂斗罗](https://mp.baomidou.com/img/contra.jpg) 中的 1P、2P，基友搭配，效率翻倍。



![img](https://ask.qcloudimg.com/developer-images/article-audit/8352478/qnqprbsp76.png?imageView2/2/w/1620)

### 特性

- **无侵入**：只做增强不做改变，引入它不会对现有工程产生影响，如丝般顺滑
- **损耗小**：启动即会自动注入基本 CURD，性能基本无损耗，直接面向对象操作
- **强大的 CRUD 操作**：内置通用 Mapper、通用 Service，仅仅通过少量配置即可实现单表大部分 CRUD 操作，更有强大的条件构造器，满足各类使用需求
- **支持 Lambda 形式调用**：通过 Lambda 表达式，方便的编写各类查询条件，无需再担心字段写错
- **支持主键自动生成**：支持多达 4 种主键策略（内含分布式唯一 ID 生成器 - Sequence），可自由配置，完美解决主键问题
- **支持 ActiveRecord 模式**：支持 ActiveRecord 形式调用，实体类只需继承 Model 类即可进行强大的 CRUD 操作
- **支持自定义全局通用操作**：支持全局通用方法注入（ Write once, use anywhere ）
- **内置代码生成器**：采用代码或者 Maven 插件可快速生成 Mapper 、 Model 、 Service 、 Controller 层代码，支持模板引擎，更有超多自定义配置等您来使用
- **内置分页插件**：基于 MyBatis 物理分页，开发者无需关心具体操作，配置好插件之后，写分页等同于普通 List 查询
- **分页插件支持多种**[**数据库**](https://cloud.tencent.com/solution/database?from=10680)：支持 [MySQL](https://cloud.tencent.com/product/cdb?from=10680)、[MariaDB](https://cloud.tencent.com/product/tdsql?from=10680)、Oracle、DB2、H2、HSQL、SQLite、Postgre、SQLServer 等多种数据库
- **内置性能分析插件**：可输出 Sql 语句以及其执行时间，建议开发测试时启用该功能，能快速揪出慢查询
- **内置全局拦截插件**：提供全表 delete 、 update 操作智能分析阻断，也可自定义拦截规则，预防误操作

### 支持的数据库

> 几乎包含市面上所有主流数据库

- mysql 、 mariadb 、 oracle 、 db2 、 h2 、 hsql 、 sqlite 、 postgresql 、 sqlserver 、 presto
- 达梦数据库 、 虚谷数据库 、 人大金仓数据库

### 框架结构



![img](https://ask.qcloudimg.com/developer-images/article-audit/8352478/zjkklq578u.png?imageView2/2/w/1620)

### 快速开始

> 官方地址:[传送门](https://mybatis.plus/guide/quick-start.html) 核心步骤: 使用第三方组件:

1. 导入对应的依赖
2. 研究依赖如何配置
3. 代码如何编写
4. 提高扩展技术能力

- 创建表

```javascript
DROP TABLE IF EXISTS USER;
CREATE TABLE USER (
    id BIGINT ( 20 ) NOT NULL COMMENT '主键ID',
    NAME VARCHAR ( 30 ) NULL DEFAULT NULL COMMENT '姓名',
    age INT ( 11 ) NULL DEFAULT NULL COMMENT '年龄',
    email VARCHAR ( 50 ) NULL DEFAULT NULL COMMENT '邮箱',
    PRIMARY KEY ( id ) 
);
DELETE FROM USER;
INSERT INTO USER ( id, NAME, age, email )
VALUES
    ( 1, 'Jone', 18, 'test1@baomidou.com' ),
    ( 2, 'Jack', 20, 'test2@baomidou.com' ),
    ( 3, 'Tom', 28, 'test3@baomidou.com' ),
    ( 4, 'Sandy', 21, 'test4@baomidou.com' ),
    ( 5, 'Billie', 24, 'test5@baomidou.com' );
-- 真实开发中，version（乐观锁）、deleted（逻辑删除）、gmt_create、gmt_modified
```

复制

- 创建`spring-boot`项目,导入依赖,初始化项目

```javascript
<dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <!--简化实体类-->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <!--MySQL驱动-->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>5.1.43</version>
        </dependency>
        <!--mybatis-plus启动器-->
        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-boot-starter</artifactId>
            <version>3.0.5</version>
        </dependency>
        <!--spring-boot-test-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
            <exclusions>
                <exclusion>
                    <groupId>org.junit.vintage</groupId>
                    <artifactId>junit-vintage-engine</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
</dependencies>
```

复制

> 注意:在使用mybatis-plus可以简化我们大量的代码,不可以在同一个项目下导入mybatis依赖和mybatis-plus,会有版本冲突!!!

- 配置数据库连接相关内容

```javascript
# MySQL5
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/index
    driver-class-name: com.mysql.jdbc.Driver
    username: root
    password: root
# MySQL8配置
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/mybatis_plus?useSSL=false&useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8
    driver-class-name: com.mysql.cj.jdbc.Driver
    username: root
    password: root   
```

复制

> 注意:

1. MySQL5和MySQL8驱动不同
   - MySQL-5:`com.mysql.jdbc.Driver`
   - MySQL-8:`com.mysql.cj.jdbc.Driver`
2. MySQL8需要增加时区配置`serverTimezone=GMT%2B8`

- 创建实体类

```javascript
@Getter
@Setter
@ToString
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;
}
```

复制

- 创建`dao`接口

```javascript
/**
 * Software：IntelliJ IDEA 2020.1 x64
 * Author: MoBai·杰
 * Date: 2020/6/8 14:05
 * ClassName:UserDao
 * 使用mybatis-plus,每个mapper接口都需要继承BaseMapper
 * BaseMapper类描述： Mapper继承该接口后，无需编写 mapper.xml 文件，即可获得CRUD功能
 */
@Repository // 表示持久层
public interface UserDao extends BaseMapper<User> {
    
}
```

复制

- 全局启动类添加`扫描mapper接口`

```javascript
/**
 * 全局启动类
 */
// 表示扫描mapper包下的所有接口
@MapperScan("com.mobai.mybatis_plus.dao")
@SpringBootApplication
public class MybatisPlusApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisPlusApplication.class, args);
    }

}
```

复制

- 测试类

```javascript
@SpringBootTest
class MybatisPlusApplicationTests {

    // 注入Dao接口,继承BaseMapper
    @Autowired
    private UserDao userDao;

    @Test
    void contextLoads() {
        // 调用BaseMapper查询方法<selectList>
        List<User> list = userDao.selectList(null);
        list.forEach(System.out::println);
    }
}
```

复制

- 效果



![img](https://ask.qcloudimg.com/developer-images/article-audit/8352478/r668jcpgjq.png?imageView2/2/w/1620)

#### 思考

> 

1. SQL是谁帮我们写的?
   - Mybatis-plus帮我们写好了
2. 方法哪里来的?
   - Mybatis-plus帮我们写的

### 配置日志输出

- `yml配置添加日志处理`

```javascript
# 配置日志输出 使用默认控制台打印
mybatis-plus:
  configuration:
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
```

复制

- 测试



![img](https://ask.qcloudimg.com/developer-images/article-audit/8352478/4z11a7qc3v.png?imageView2/2/w/1620)

### 插入测试和雪花算法

- 添加

```javascript
/**
 * 添加数据
 */
@Test
public void testInsert() {
    User user = new User();
    user.setName("墨白君");
    user.setAge(25);
    user.setEmail("mobaijun8@163.com");
    // mybatis-plus会自动帮助我们生成主键ID
    int insert = userDao.insert(user);
    // 被影响的行数
    System.out.println("insert = " + insert);
    // ID会自动回填
    System.out.println("user = " + user);
}
```

复制

> 数据库插入的ID是全局唯一ID

### 主键生成策略

> `mybats-plus`默认策略为: `ID_WORKER` 扩展:分布式系统唯一ID生成:[传送门](https://www.cnblogs.com/haoxinyue/p/5208136.html)

#### 雪花算法

> **Twitter的snowflake算法** snowflake是Twitter开源的分布式ID生成算法，结果是一个long型的ID。其核心思想是：使用41bit作为毫秒数，10bit作为机器的ID（5个bit是数据中心，5个bit的机器ID），12bit作为毫秒内的流水号（意味着每个节点在每毫秒可以产生 4096 个 ID），最后还有一个符号位，永远是0。以上实现思路基本可以保证主键ID为全球唯一

#### **Mybatis-Plus提供的所有策略**

看`IdType`源码

```javascript
public enum IdType {
    /**
     * 数据库ID自增
     */
    AUTO(0),
    /**
     * 该类型为未设置主键类型
     */
    NONE(1),
    /**
     * 用户输入ID
     * 该类型可以通过自己注册自动填充插件进行填充
     */
    INPUT(2),

    /* 以下3种类型、只有当插入对象ID 为空，才自动填充。 */
    /**
     * 全局唯一ID (idWorker)
     */
    ID_WORKER(3),
    /**
     * 全局唯一ID (UUID)
     */
    UUID(4),
    /**
     * 字符串全局唯一ID (idWorker 的字符串表示)
     */
    ID_WORKER_STR(5);
}
```

复制

### 不同的主键策略测试

> `IdType.AUTO`:主键自增长

- 数据表添加自增长策略

```javascript
-- 已有数据表添加自增策略
ALTER TABLE `user` CHANGE id LONG NOT NULL AUTO_INCREMENT PRIMARY KEY;
```

复制

- 实体类添加`主键自增长策略`

```javascript
@Getter
@Setter
@ToString
public class User {
    // 常见的主键生成策略(UUID/MySQL自增长/雪花算法/redis/zookeeper...)
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private Integer age;
    private String email;
}
```

复制

- 测试/查看日志

```javascript
/**
 * 添加数据
 */
@Test
public void testInsert() {
    User user = new User();
    user.setName("墨白");
    user.setAge(21);
    user.setEmail("mobaijun8@163.com");
    // mybatis-plus会自动帮助我们生成主键ID
    int insert = userDao.insert(user);
    // 被影响的行数
    System.out.println("insert = " + insert);
    // ID会自动回填
    System.out.println("user = " + user);
}
```

复制



![img](https://ask.qcloudimg.com/developer-images/article-audit/8352478/tljhurduqo.png?imageView2/2/w/1620)

- 查看数据表是否自增



![img](https://ask.qcloudimg.com/developer-images/article-audit/8352478/r9dz17xnrr.png?imageView2/2/w/1620)

### 更新操作

- 测试类

```javascript
/**
 * 根据ID修改操作
 */
@Test
public void testUpdate() {
    User user = new User();
    user.setId(1269882840871374849L);
    user.setName("小柠檬");
    user.setEmail("mobaijun8@163.com");
    // 根据用户ID修改
    int i = userDao.updateById(user);
    System.out.println("i = " + i);
}
```

复制

> 注意:`updateById()`名字虽然是ById,但源码里面的参数是一个对象!!! 注意:`Mybatis-Plus会通过条件帮我们自动拼接动态SQL`,比`mybatis手动编写动态SQL标签灵活`

- 结果



![img](https://ask.qcloudimg.com/developer-images/article-audit/8352478/or8zhw234x.png?imageView2/2/w/1620)

### 自动填充处理

> 比如一些测试`log`,创建时间和修改时间,这些操作一般都是通过自动化完成的,一般我们不希望手动操作!!! 扩展:阿里巴巴开发手册规定:所有的数据库表都要包含两个字段:`gmt_create`和`gmt_modified`,几乎所有的表都要配置上,并且需要自动化

**自动填充有两种方式**

#### 方式一:数据库级别

```javascript
-- 已有数据表添加新字段
alter table user add gmt_create date; -- 开始时间
alter table user add gmt_modified date; -- 结束时间
```

复制

- 实体类添加属性

```javascript
@Getter
@Setter
@ToString
public class User {
    // 常见的主键生成策略(UUID/MySQL自增长/雪花算法/redis/zookeeper...)
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private Integer age;
    private String email;
    // 开始时间
    private Date gmtCreate;
    // 结束时间
    private Date gmrModified;
}
```

复制

- 测试

```javascript
 /**
  * 添加数据
  */
 @Test
 public void testInsert() {
     User user = new User();
     user.setName("框架师");
     user.setAge(21);
     user.setEmail("mobaijun8@163.com");
     // mybatis-plus会自动帮助我们生成主键ID
     int insert = userDao.insert(user);
     // 被影响的行数
     System.out.println("insert = " + insert);
     // ID会自动回填
     System.out.println("user = " + user);
 }
```

复制

#### 方式二:代码级别

- 字段添加填充内容

```javascript
// 开始时间
// 插入填充字段
@TableField(fill = FieldFill.INSERT)
private Date gmtCreate;
// 结束时间
// 更新填充字段
@TableField(fill = FieldFill.INSERT_UPDATE)
private Date gmtModified;
```

复制

- 源码内容

```javascript
public enum FieldFill {
    /**
     * 默认不处理
     */
    DEFAULT,
    /**
     * 插入填充字段
     */
    INSERT,
    /**
     * 更新填充字段
     */
    UPDATE,
    /**
     * 插入和更新填充字段
     */
    INSERT_UPDATE
}
```

复制

- 创建自定义元对象处理器接口

```javascript
@Slf4j // 添加日志
@Component // 注入Spring容器中
public class MyMetaObjectHandler implements MetaObjectHandler {
    /**
     * 插入时的填充策略
     *
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        // 添加日志
        log.info("start insert fill............");
        /**
         * String fieldName: 需要插入的字段
         * Object fieldVal: 需要插入的类型
         * MetaObject metaObject: 需要给那个数据处理
         */
        this.setFieldValByName("gmtCreate", new Date(), metaObject);
        this.setFieldValByName("gmtModified", new Date(), metaObject);
    }

    /**
     * 更新时的填充策略
     *
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("end update fill............");
        /**
         * 更新的时候只需要更新的字段
         */
        this.setFieldValByName("gmtModified", new Date(), metaObject);
    }
}
```

复制

> 自定义元对象处理器接口必须要实现`MetaObjectHandler`接口

- 测试

```javascript
 /**
  * 添加数据
  */
 @Test
 public void testInsert() {
     User user = new User();
     user.setName("小狐狸");
     user.setAge(21);
     user.setEmail("mobaijun@163.com");
     // mybatis-plus会自动帮助我们生成主键ID
     int insert = userDao.insert(user);
     // 被影响的行数
     System.out.println("insert = " + insert);
     // ID会自动回填
     System.out.println("user = " + user);
 }
```

复制

### 乐观锁处理

> 

- 乐观锁 : 故名思意十分乐观，它总是认为不会出现问题，无论干什么不去上锁！如果出现了问题， 再次更新值测试
- 悲观锁：故名思意十分悲观，它总是认为总是出现问题，无论干什么都会上锁！再去操作！
- 乐观锁实现方式：
  - 取出记录时，获取当前 version
  - 更新时，带上这个version
  - 执行更新时， set version = newVersion where version = oldVersion
  - 如果version不对，就更新失败

```javascript
乐观锁：1、先查询，获得版本号 version = 1
-- A
update user set name = "kuangshen", version = version + 1
where id = 2 and version = 1
-- B 线程抢先完成，这个时候 version = 2，会导致 A 修改失败！
update user set name = "kuangshen", version = version + 1
where id = 2 and version = 1
```

复制

- 在数据库中添加`version`字段



![img](https://ask.qcloudimg.com/developer-images/article-audit/8352478/0fxwrypq96.png?imageView2/2/w/1620)

- 实体类

```javascript
// 配置乐观锁插件
@Version // 乐观锁注解
private Integer version;
```

复制

- 新建`config`包,创建`MyBatisPlusConfig`配置类

```javascript
/**
 * Software：IntelliJ IDEA 2020.1 x64
 * Author: MoBai·杰
 * Date: 2020/6/8 16:52
 * ClassName:MyBatisPlusConfig
 * 类描述： Spring配置类
 */
// 表示扫描mapper包下的所有接口
@MapperScan("com.mobai.mybatis_plus.dao")
@EnableTransactionManagement // 开启自动事务
@Configuration // 表示这个类是全局配置类
public class MyBatisPlusConfig {

    /**
     * 注册乐观锁插件
     *
     * @return
     */
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }
}
```

复制

- 乐观锁成功测试

```javascript
/**
 * 测试乐观锁成功
 */
@Test
public void testOptimisticLocker() {
    // 1.查询用户信息
    User user = userDao.selectById(1L);
    // 2.修改用户信息
    user.setName("墨白科技");
    user.setEmail("mobaijun8@163.com");
    user.setAge(21);
    // 3.执行更新
    userDao.updateById(user);
}
```

复制

> \# 控制台打印sql UPDATE user SET name=?, age=?, email=?, version=?, gmt_create=?, gmt_modified=? WHERE id=? AND version=?

- 乐观锁失败测试

```javascript
/**
 * 测试乐观锁失败！多线程下
 */
@Test
public void testOptimisticLocker2() {
    /**
     * 线程1
     */
    User user = userDao.selectById(1L);
    user.setName("墨白科技111");
    user.setEmail("mobaijun8@163.com");
    user.setAge(21);
    /**
     * 线程2
     * 模拟另外一个线程执行了插队操作
     */
    User user2 = userDao.selectById(1L);
    user2.setName("墨白科技222");
    user2.setEmail("mobaijun8@163.com");
    user2.setAge(21);
    userDao.updateById(user2);
    // 自旋锁来多次尝试提交！
    userDao.updateById(user); // 如果没有乐观锁就会覆盖插队线程的值！
}
```

复制

### 查询操作

```javascript
/**
 * 测试查询
 */
@Test
public void testSelectById() {
    User id = userDao.selectById(1L);
    System.out.println("id = " + id);
}

/**
 * 批量查询
 */
@Test
public void testSelectByBatchId() {
    List<User> users = userDao.selectBatchIds(Arrays.asList(1, 2, 3));
    System.out.println("users = " + users);
}

 /**
  * 按条件查询
  */
 @Test
 public void testSelectByBatchIdS() {
     HashMap<String, Object> map = new HashMap<>();
     // 自定义条件
     map.put("name", "Jack");
     map.put("age", 20);
     List<User> users = userDao.selectByMap(map);
     users.forEach(System.out::println);
 }
```

复制

### 分页查询

> 应用场景:分页在浏览场景或一些web网站非常常见

- 常用的分页方式
  - 使用MySQL原始`limit`进行分页
  - `Mybatis`插件`PageHelper`
  - `MybatisPlus`其实也内置了分页插件
- 在`MyBatisPlusConfig`中配置拦截器组件即可

```javascript
 /**
  * 分页拦截器
  *
  * @return
  */
 @Bean
 public PaginationInterceptor paginationInterceptor() {
     // 开启 count 的 join 优化,只针对部分 left join
     return new PaginationInterceptor();
 }
```

复制

- 测试:直接使用Page对象即可！

```javascript
/**
 * 测试分页查询
 */
@Test
public void testPage() {
    /**
     * 参数一: 当前页
     * 参数一: 页大小
     * 使用了分页插件以后,所有的分页操作也变得非常简单
     */
    Page<User> page = new Page<>(2, 4);
    // 调用selectPage进行分页
    userDao.selectPage(page, null);
    page.getRecords().forEach(System.out::println);
    // 获取记录总数
    System.out.println("page = " + page.getTotal());
}
```

复制

### 删除操作

```javascript
/**
 * 测试删除
 */
@Test
public void testDeleteById() {
    userDao.deleteById(1269882840871374854L);
}
/**
 * 批量删除
 */
@Test
public void testDeleteList() {
    userDao.deleteBatchIds(Arrays.asList(1269882840871374853L,
            1269882840871374852L,
            1269882840871374851L,
            1269882840871374850L,
            1269882840871374849L));
}
/**
 * 通过集合删除
 */
@Test
public void testDeleteMap() {
    HashMap<String, Object> map = new HashMap<>();
    map.put("name", "墨白科技222");
    userDao.deleteByMap(map);
}
```

复制

### 逻辑删除

> 

- 物理删除 ：从数据库中直接移除
- 逻辑删除 ：在数据库中没有被移除，而是通过一个变量来让他失效！ deleted = 0 => deleted = 1 管理员可以查看被删除的记录！防止数据的丢失，类似于回收站！
- 在表中新增一个字段`deleted`逻辑删除

![img](https://ask.qcloudimg.com/developer-images/article-audit/8352478/pbfumeesvy.png?imageView2/2/w/1620)

- 实体类

```javascript
// 逻辑删除
@TableLogic // 逻辑删除注解
private Integer deleted;
```

复制

- 在`MyBatisPlusConfig`添加逻辑删除组件

```javascript
/**
 * 逻辑删除组件
 */
@Bean
public ISqlInjector sqlInjector() {
    return new LogicSqlInjector();
}
```

复制

- `yml`配置逻辑删除

```javascript
# 配置逻辑删除 
global-config:
  db-config:
    logic-delete-value: 1
    logic-not-delete-value: 0
```

复制

- 测试

```javascript
/**
 * 逻辑删除
 * 相当于回收站
 */
@Test
public void testDeleted() {
    userDao.deleteById(6L);
}
```

复制



- 数据库字段值变为`0`

> 记录依旧在数据库，但是值确已经变化了！



![img](https://ask.qcloudimg.com/developer-images/article-audit/8352478/az507u8o1b.png?imageView2/2/w/1620)

### 性能分析插件

- 我们在平时的开发中，会遇到一些慢sql。测试！ druid,,,,,
- 作用：性能分析拦截器，用于输出每条 SQL 语句及其执行时间
- MP也提供性能分析插件，如果超过这个时间就停止运行！
- `MyBatisPlusConfig`导入插件

```javascript
/**
 * SQL执行效率插件
 */
@Bean
@Profile({"dev", "test"}) // 设置dev test环境开启,保证开发效率
public PerformanceInterceptor performanceInterceptor() {
    PerformanceInterceptor performanceInterceptor =
            new PerformanceInterceptor();
    // 设置执行时间和写入日志
    Properties properties = new Properties();
    properties.setProperty("format", "true");
    // 设置SQL执行时间和写入日志
    performanceInterceptor.setProperties(properties);
    return performanceInterceptor;
}
```

复制

- 第二种写法(单独配置)

```javascript
// 设置SQL最大执行时间,如果超过了则不执行
performanceInterceptor.setMaxTime(10);
// 设置SQL是否格式化
performanceInterceptor.setFormat(true);
```

复制

- `application.yml`配置插件

```javascript
# 数据库配置
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/index?characterEncoding=utf-8
    driver-class-name: com.mysql.jdbc.Driver
    username: root
    password: root
  # 设置性能分析插件  
  profiles:
    active: dev
```

复制

- 测试

```javascript
/**
 * 测试性能分析插件
 */
@Test
public void contextLoads2() {
    List<User> users = userDao.selectList(null);
    users.forEach(System.out::println);
}
```

复制



![img](https://ask.qcloudimg.com/developer-images/article-audit/8352478/anf54ut5dt.png?imageView2/2/w/1620)

> 使用性能分析插件，可以帮助我们提高效率！

### 条件查询wrapper

[条件构造器传送门](https://mp.baomidou.com/guide/wrapper.html#abstractwrapper)

> 十分重要：Wrapper

- 我们写一些复杂的sql就可以使用它来替代！
- 测试类

```javascript
@SpringBootTest
public class WrapperTest {

    @Autowired
    private UserDao userDao;


    /**
     * 条件查询
     */
    @Test
    public void wrapper1() {
        // 查询name不为空和email不为空并且年龄大于等于12
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        // 链式编程
        wrapper.isNotNull("name").
                isNotNull("email").
                ge("age", 20);
        userDao.selectList(wrapper).forEach(System.out::println);
    }


    /**
     * 根据名称查询
     */
    @Test
    public void wrapper2() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("name", "MoBai");
        // selectOne(wrapper);查询一个数据
        User user = userDao.selectOne(wrapper);
        System.out.println("user = " + user);
    }

    /**
     * 查询区间
     */
    @Test
    public void wrapper3() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        // 查询年龄在15-25之间的数据
        wrapper.between("age", 15, 25);
        // selectOne(wrapper);查询一个数据
        Integer count = userDao.selectCount(wrapper);
        System.out.println(count);
    }

    /**
     * 模糊查询
     */
    @Test
    public void test4() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.
                // 查询name不包含e的字段
                notLike("name", "e").
                // 左和右 t%
                likeRight("email", "t");
        List<Map<String, Object>> maps = userDao.selectMaps(wrapper);
        maps.forEach(System.out::println);
    }

    /**
     * 模糊查询
     */
    @Test
    public void test5() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        // id在子查询中查出来
        wrapper.inSql("id", "select id from user where id < 3");
        List<Object> list = userDao.selectObjs(wrapper);
        list.forEach(System.out::println);
    }

    /**
     * 模糊查询
     */
    @Test
    public void test6() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        // 通过ID进行排序
        wrapper.orderByAsc("id");
        List<User> list = userDao.selectList(wrapper);
        list.forEach(System.out::println);
    }
}
```

复制

> 其余的方法多练习,多记笔记

### 代码自动生成器

> AutoGenerator 是 MyBatis-Plus 的代码生成器，通过 AutoGenerator 可以快速生成 Entity、Mapper、Mapper XML、Service、Controller 等各个模块的代码，极大的提升了开发效率。

[官方文档](https://mp.baomidou.com/config/)

- 引入插件包

```javascript
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.3.0.RELEASE</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>
    <description>Demo project for Spring Boot</description>

    <properties>
        <java.version>1.8</java.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <!--简化实体类-->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <!--MySQL驱动-->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>5.1.43</version>
        </dependency>
        <!--mybatis-plus启动器-->
        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-boot-starter</artifactId>
            <version>3.0.5</version>
        </dependency>
        <dependency>
            <groupId>org.apache.velocity</groupId>
            <artifactId>velocity-engine-core</artifactId>
            <version>2.0</version>
        </dependency>
        <!--spring-boot-test-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
            <exclusions>
                <exclusion>
                    <groupId>org.junit.vintage</groupId>
                    <artifactId>junit-vintage-engine</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
    </dependencies>
</project>
```

复制

- 测试类

```javascript
package com.mobai.mybatis_plus;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;

/**
 * Software：IntelliJ IDEA 2020.1 x64
 * Author: MoBai·杰
 * Date: 2020/6/9 11:38
 * ClassName:MoBaiCode
 * 类描述： MybatisPlus代码生成器
 */
public class MoBaiCode {
    public static void main(String[] args) {
        // MybatisPlus代码生成器必须要new一个AutoGenerator对象
        AutoGenerator mpg = new AutoGenerator();
        /**
         * 配置策略
         * 1.全局配置
         */
        GlobalConfig gc = new GlobalConfig();
        // 获取当前资源路径
        String projectPath = System.getProperty("user.dir");
        // 生成的代码输出路径
        gc.setOutputDir(projectPath + "/src/main/java");
        // 生成作者信息
        gc.setAuthor("MoBai·杰");
        // 是否打开输出目录
        gc.setOpen(false);
        // 是否覆盖文件
        gc.setFileOverride(false);
        // 取消生成以后I前缀
        gc.setServiceName("%sService");
        // 指定生成的主键的ID类型
        gc.setIdType(IdType.ID_WORKER);
        // 只使用 java.util.date 代替
        gc.setDateType(DateType.ONLY_DATE);
        // 开启 swagger2 模式
        gc.setSwagger2(true);
        mpg.setGlobalConfig(gc);


        /**
         * 2.配置数据源相关
         * DataSourceConfig:数据库配置
         */
        DataSourceConfig ds = new DataSourceConfig();
        ds.setDriverName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/index?characterEncoding=utf-8");
        ds.setUsername("root");
        ds.setPassword("root");
        // 数据库类型
        ds.setDbType(DbType.MYSQL);
        mpg.setDataSource(ds);


        /**
         * 3.设置包的配置
         */
        PackageConfig pc = new PackageConfig();
        // 项目名称
        pc.setModuleName("cxdz");
        // 项目路径
        pc.setParent("com.mobai");
        // 实体类包名
        pc.setEntity("pojo");
        // Dao包名
        pc.setMapper("dao");
        // service包名
        pc.setService("service");
        // service实现类包名
        pc.setServiceImpl("service.impl");
        // controller包名
        pc.setController("controller");
        mpg.setPackageInfo(pc);

        /**
         * 4.策略配置
         */
        StrategyConfig sc = new StrategyConfig();
        // 需要映射的表
        sc.setInclude("user");
        // 从数据库表到文件的命名策略/下划线转驼峰命名
        sc.setNaming(NamingStrategy.underline_to_camel);
        sc.setColumnNaming(NamingStrategy.underline_to_camel);
        // 是否为lombok模型/默认false
        sc.setEntityLombokModel(true);
        // 设置逻辑删除字段
        sc.setLogicDeleteFieldName("deleted");

        /**
         * 5.设置自动填充
         */
        TableFill gmtCreate = new TableFill("gmt_create", FieldFill.INSERT);
        TableFill gmtModified = new TableFill("gmt_modified", FieldFill.INSERT_UPDATE);
        ArrayList<TableFill> list = new ArrayList<>();
        list.add(gmtCreate);
        list.add(gmtModified);
        // 表填充字段
        sc.setTableFillList(list);
        // 设置乐观锁字段
        sc.setVersionFieldName("version");
        // 生成@RestController控制器
        sc.setRestControllerStyle(true);
        // 驼峰转连字符
        sc.setControllerMappingHyphenStyle(true);
        mpg.setStrategy(sc);
        // 执行
        mpg.execute();
    }
}
```

### 总结

> MybatisPlus是对mybatis的增强,只做增强,不做修改

# 多数据源

### 依赖

```xml
<!-- oracle -->
<dependency>
    <groupId>com.oracle</groupId>
    <artifactId>ojdbc8</artifactId>
    <version>8.0.0.1</version>
    <type>jar</type>
    <scope>system</scope>
    <systemPath>${project.basedir}/src/main/resources/jar/ojdbc8.jar</systemPath>
</dependency>

<dependency>
    <groupId>com.baomidou</groupId>
    <artifactId>dynamic-datasource-spring-boot-starter</artifactId>
    <version>3.5.2</version>
</dependency>
```

### 配置

```yaml
spring:
  datasource:
    dynamic:
      primary: mysql #设置默认的数据源或者数据源组,默认值即为mysql
      strict: false #严格匹配数据源,默认false. true未匹配到指定数据源时抛异常,false使用默认数据源
      datasource:
        mysql:
          url: jdbc:mysql://192.168.3.4:33016/mybatis_plus?useSSL=false&useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8
          username: root
          password: 123456
          driver-class-name: com.mysql.jdbc.Driver # 3.2.0开始支持SPI可省略此配置
        oracle:
          url: jdbc:oracle:thin:@192.168.3.4:31521/helowin
          username: hospital
          password: hospital
          driver-class-name: oracle.jdbc.driver.OracleDriver
```

### Service

```java
@Service
@DS("mysql")
public class UserServiceImpl implements UserService {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Map<String, Object>> selectAll() {
        return  jdbcTemplate.queryForList("select * from user");
    }

    @Override
    @DS("oracle")
    public List<Map<String, Object>> selectByCondition() {
        return  jdbcTemplate.queryForList("select * from usertest"); //  where age >10
    }
}
```

### 控制器

```java
@Slf4j
@Api(tags = "用户")
@RestController
@RequestMapping("user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping(value = "/allUser")
    @ApiOperation(value = "导出所有的表的数据")
    public Result<Map<String, Object>> exportAllUser(HttpServletResponse response) throws Exception {
        System.out.println("Call @@@@");
        Map<String, Object> result = new LinkedHashMap<>();

        List<Map<String, Object>> listA = userService.selectAll();
        for (Map<String, Object> map : listA) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                String mapKey = entry.getKey();
                Object mapValue = entry.getValue();
                System.out.println("Data from MySQL: " + mapKey + "：" + mapValue);
            }
        }
        System.out.println();
        result.put("mysql", listA);
        List<Map<String, Object>> listB = userService.selectByCondition();
        for (Map<String, Object> map : listB) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                String mapKey = entry.getKey();
                Object mapValue = entry.getValue();
                System.out.println("Data from Oracle: " + mapKey + "：" + mapValue);
            }
        }
        result.put("oracle", listB);
        return new Result<>(result);
    }

}
```

### 运行结果

 ![image-20230207143405804](assets\image-20230207143405804.png)

### 参考文档

1. [多数据源](https://baomidou.com/pages/a61e1b/#dynamic-datasource)
