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





```xml
<mysql.version>8.0.20</mysql.version>

<!--mysql-->
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>${mysql.version}</version>
</dependency>

```

### 数据库访问配置

```properties
#数据库访问配置
spring.datasource.url=jdbc:mysql://localhost:3306/db_middleware?useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=123456
```




### 二倍均值法生成红包

```
剩余总金额M	剩余总人数N	（N-1）是否大于0	随机区间（0，M/N*2）	随机金额R	剩余总金额（M=M-R）	此时总人数（N=N-1)
100	5	4	40	38	62	4
62	4	3	31	25	37	3
37	3	2	24.66666667	20	17	2
17	2	1	17	15	2	1

```
