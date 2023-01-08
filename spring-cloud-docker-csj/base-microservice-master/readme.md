#微服务基础工程
#配置管理
config
#注册中心
eureka
#监控仪表
hystrix
#聚合监控
turbine
#服务跟踪
zipkin



系统要用JDK 1.8，不能用JDK 11



```
http://localhost:8989/


http://localhost:8989/turbine.stream



```



### Docker 安装 MySQL 5.7

```
docker run --name mysql5.7 -p 33056:3306 -e MYSQL_ROOT_PASSWORD=123456 -d mysql:5.7

```





```
    url: jdbc:mysql://59.172.75.156:33056/myzipkin?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B8&
    username: root
    password: 123456
```







```
Invocation of init method failed; nested exception is java.lang.NoClassDefFoundError: javax/xml/bind/JAXBException
```

