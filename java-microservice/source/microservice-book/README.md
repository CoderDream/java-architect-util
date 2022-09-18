# 微服务项目
## 1 项目组成
- 注册中心：Eureka
- 网关：Gateway
- 监控：Admin
- 用户模块：User
- 数据处理模块：Data
## 2 环境
- Visual Studio Code
- Maven3.6
- Java8
- SpringCloud：Hoxton.SR5
- SpringBoot：2.2.8.RELEASE
## 3 运行
### 3.1 初始化项目

（1）运行SQL脚本，microservice-user模块/resources/sql/book.sql

（2）新建数据库和数据表

### 3.2 VSCode运行
（1）全套启动：分别进入不同的项目，使用Maven命令运行对应的服务，启动顺序：
Eureka-->Gateway-->Admin-->User-->Data
```
mvn spring-boot:run -D maven.test.skip=true
```
（2）单体服务启动：进入对应项目，直接运行

（3）application.yml中配置运行的环境，通过参数active指定环境
### 3.3 jar包运行
```
java -jar xxx.jar
```
### 3.4 microservice-user接口
（1）user模块接口使用JWT做鉴权，因此请求接口需要添加Token

（2）user模块只有登录接口无需Token，通过登录接口获取Token

（3）初始化用户名：admin，密码：admin

（4）JWT路径拦截配置在/config/WebMvcHandler.java，不使用JWT拦截，使用registration.excludePathPatterns进行配置

## 4 打包
```
mvn clean package -D maven.test.skip=true
```