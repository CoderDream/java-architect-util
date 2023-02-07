# Docker部署若依系统

花了三天的时间终于研究出来如何通过docker容器把若依这个开源项目（前后端分离版）部署到服务器上(本地虚拟机上跑的一个Linux系统)，刚开始使用war包的形式部署，发现后端怎么也访问不到，后来又改成jar包形式部署。

用到了mysql容器，redis容器，nginx容器，若依后端打包成的容器，暂时没有用到docker-compose把所有的东西集成一个镜像，直接一键部署，到后面再学习。

### 首先整体看一下整个目录结构

#### 没有展开前

![img](assets\16870dd59c9447d087a662b942d2c7a4.png)

#### 展开后

![img](assets\2eb2dab9836545ac8d4804907e243135.png) 

废话不多说，直接开整！！！！

### 第一步：首先在服务器上搭建整个文件结构

我是在服务器上的root目录下新建一个ruoyi文件夹，然后进入ruoyi文件夹再新建如下的文件夹

![img](assets\7ac8a585b4a94470a758f75a313cba3e.png)

 具体每个文件夹里面新建的文件夹如下

 ![img](assets\e5904694138b43f99fde3b81772993a1.png)

 

 ![img](assets\6f75b9d8cf86463799ac434bca52d5d9.png)

 ![img](assets\03ed0bf6efc64c419782ec4379024912.png) 

### 第二步：开始拉取镜像并把相关的文件挂载到上面新建的各个目录下

#### 1，首先是mysql

1.1，先新建一个my.cnf文件，把它放到服务器root/ruoyi/mysql/conf里面，这个是数据库的配置文件，内容如下

```cnf
[client]
default-character-set = utf8

[mysql]
default-character-set = utf8

[mysqld]
init_connect = 'SET NAMES utf8'
init_connect = 'SET collation_connection = utf8_unicode_ci'
character-set-server = utf8
collation-server = utf8_unicode_ci
skip-character-set-client-handshake
skip-name-resolve
pid-file = /var/run/mysqld/mysqld.pid
socket = /var/run/mysqld/mysqld.sock
datadir = /var/lib/mysql
secure-file-priv = /var/lib/mysql


# Disabling symbolic-links is recommended to prevent assorted security risks

symbolic-links = 0

# Custom config should go here

!includedir /etc/mysql/conf.d/
```



1.2，然后拉取mysql的镜像并创建成容器

```sh
docker run -p 3306:3306 --name mysql01 \
-v /root/ruoyi/mysql/log:/var/log/mysql \
-v /root/ruoyi/mysql/conf/my.cnf:/etc/mysql/my.cnf \
-v /root/ruoyi/mysql/data:/var/lib/mysql \
-e MYSQL_ROOT_PASSWORD=123456 \
-d mysql:5.7
```

 查数据库容器是否跑起来

![img](assets\5efd093759cf4553b27dae9f82097d42.png)

上面要配置你数据库的密码，我指定的mysql版本为5.7，挂载的目录就是上面新建的目录

 拉取镜像并创建容器成功后，在笔记本上用navicate连接一下这个数据库看下是否成功

![img](assets\7907a40d42624dee951622278d58dd67.png)

 应该是没啥问题，然后在这个数据里面新建一个数据库命名为ry-vue,然后把若依系统运行必须的数据导入进去，将那两个SQL文件内容复制到navicate直接执行就可以了

 ![img](assets\7b5ba399acd54355a1b06dcc267b91da.png)

  ![img](assets\011d790aa57945c8b756e63ef54048cd.png)

####  2，然后是redis

2.1，新建一个redis.conf配置文件，然后放到root/ruoyi/redis/conf文件路径下

内容如下，它主要用来数据持久化的

```
appendonly yes
```


 2.2，拉取redis镜像并创建成容器

```sh
docker run -p 6379:6379 --name redis01 \
-v /root/ruoyi/redis/data:/data \
-v /root/ruoyi/redis/conf/redis.conf:/etc/redis/redis.conf \
-d redis redis-server /etc/redis/redis.conf
```


上面指定了redis的端口，密码没有设置，然后用可视化软件redis-desktop-manager连接一下，看看是否有问题

![img](assets\94ac1cecb57c4b52ad8c7731b1a793f1.png)

连接redis成功，就没啥问题了

#### 3，其次是nginx

 由于nginx的配置文件内容比较多，不再通过新建配置文件，自己往里面写配置内容的方式了，改成先把redis镜像拉取下来跑起来容器以后把容器里面redis的配置文件拷贝到对应的挂载目录里，然后再对其内容进行修改，再把nginx容器停止并删除，再把其镜像删除，重新再拉取

##### 3.1，先拉取nginx镜像

```
docker run --name nginx01 -p 80:80 -d nginx
```

 容器跑起来后，把里面的配置文件复制出来，这一步要回退到服务器的root目录下执行

```
docker cp 1bc5078f9e87:/etc/nginx/nginx.conf /root/ruoyi/nginx/conf
```

执行上面的命令

![img](assets\dc0b9e023375436480c588d5ab407098.png)

确定对应目录里面多出一个新的nginx.conf配置文件，然后把刚刚的nginx容器停掉并删除，其对应的镜像也要删除掉。

重新拉取nginx镜像并挂载对应目录

```sh
docker run --name nginx01 -p 80:80 \
-v /root/ruoyi/nginx/conf/nginx.conf:/etc/nginx/nginx.conf \
-v /root/ruoyi/nginx/html:/usr/share/nginx/html \
-v /root/ruoyi/nginx/logs:/var/log/nginx \
-d nginx
```

### 第三步：制作后端镜像

#### 1，首先把若依系统的代码修改一下，具体要修改的文件如下

![img](assets\1dfeb941d8ff45cfad3b8fd4577cb561.png)

 配置你自己的数据库地址和redis地址以及打包的形式为jar包形式，然后直接打包成jar包放到服务器上root/ruoyi路径下

#### 2，编写dockerFile文件，大致就是后端使用的Java版本，以及后端暴露的端口号

```dockerfile
FROM java:8
# 维护者
MAINTAINER CoderDream<coderdream@gmail.com>
# 创建目录
RUN mkdir -p /volume1/docker/docker_data/ruoyi
# 指定路径
WORKDIR /volume1/docker/docker_data/ruoyi
# 暴露端口
EXPOSE 8080
# 复制jar文件到路径
COPY ./target/ruoyi-admin.jar /volume1/docker/docker_data/ruoyi/ruoyi-admin.jar
# 设置时区
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
RUN echo 'Asia/Shanghai' >/etc/timezone
# 启动系统服务
ENTRYPOINT ["java","-jar","ruoyi-admin.jar"]

```

 并把这个文件放到和jar包同一路径下，具体如下

 ![image-20230122125600345](assets\image-20230122125600345.png)

####  3，开始构建后端镜像，进入到root/ruoyi路径下，执行下面的命令，大致意思就是把当前文件夹下的Dockerfile文件和jar包构建成ruoyi-admin镜像版本为1.0

```
docker build -t ruoyi-admin:1.0 .

docker tag ruoyi-admin:1.0 coderdream/ruoyi-admin:1.0
docker push coderdream/ruoyi-admin:1.0

```

然后开始启动后端容器，启动之前一定要确保redis和mysql运行成功，并把系统运行必须的数据导入到数据库了

```
docker run -it -d --name ruoyi-admin -p 8080:8080 ruoyi-admin:1.0

docker run -it -d --name ruoyi-admin -p 38880:8080 coderdream/ruoyi-admin:1.0

http://coderme.myds.me:38880/captchaImage
```

到此后端算是部署完成了 



### 第四步：部署前端页面

#### 1，把系统前端页面打包，然后放到服务器root/ruoyi/nginx/html路径下，其中dist文件夹就是前端打包生成的，这个就不介绍了，自行打包就可以了

 ![img](assets\d2c4922157b249069a868c36274583d7.png)

 然后修改nginx的配置文件，让我们能够访问到这个前端页面，打开nginx的配置文件，在#gzip on后面添加如下内容

```conf
server{
    listen  80;
    server_name 172.28.27.110;

    #charset koi8-r;
    #access_log logs/host.access.log main;

    location / {
        root /usr/share/nginx/html/dist;
        index index.html index.htm;
    }
}
```

保存以后，重启nginx容器，并访问172.28.27.110:80，出现系统页面表示前端部署成功

#### 2，前后端打通，在nginx的配置文件里面添加如下内容，然后再重启nginx就可以了

```
location /prod-api/{
    proxy_set_header Host $http_host;
    proxy_set_header X-Real-IP $remote_addr;
    proxy_set_header REMOTE-HOST $remote_addr;
    proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
    proxy_pass http://172.28.27.110:8080/;
}
```

整个nginx的配置文件内容如下

```nginx
user  nginx;
worker_processes  auto;

error_log  /var/log/nginx/error.log notice;
pid        /var/run/nginx.pid;

events {
    worker_connections  1024;
}

http {
    include       /etc/nginx/mime.types;
    default_type  application/octet-stream;

    log_format  main  '$remote_addr - $remote_user [$time_local] "$request" '
                      '$status $body_bytes_sent "$http_referer" '
                      '"$http_user_agent" "$http_x_forwarded_for"';
     
    access_log  /var/log/nginx/access.log  main;
     
    sendfile        on;
    #tcp_nopush     on;
     
    keepalive_timeout  65;
     
    #gzip  on;
    server{
      listen  80;
      server_name 172.28.27.110;
      
      #charset koi8-r;
      #access_log logs/host.access.log main;
      
      location / {
           root /usr/share/nginx/html/dist;
    	index index.html index.htm;
           }
     
      location /prod-api/{
           proxy_set_header Host $http_host;
           proxy_set_header X-Real-IP $remote_addr;
           proxy_set_header REMOTE-HOST $remote_addr;
           proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
           proxy_pass http://172.28.27.110:8080/;
           }
      
    }

    include /etc/nginx/conf.d/*.conf;
}
```



最后访问172.28.27.110:80





####  3，开始构建后端镜像，进入到root/ruoyi路径下，执行下面的命令，大致意思就是把当前文件夹下的Dockerfile文件和jar包构建成ruoyi-admin镜像版本为1.0

```
docker build -t coderdream/ruoyi-ui:1.0 .

docker tag ruoyi-admin:1.0 coderdream/ruoyi-admin:1.0

docker push coderdream/ruoyi-ui:1.0


docker run -it -d --name ruoyi-ui -p 39877:9876 coderdream/ruoyi-ui:1.0



docker run -it -d --name ruoyi-ui -p 8089:8089 coderdream/ruoyi-ui:1.0

http://127.0.0.1:8080/captchaImage


http://127.0.0.1:10086/prod-api/captchaImage

http://127.0.0.1:8089/


http://localhost:39876/prod-api/captchaImage



http://127.0.0.1:8080/captchaImage

http://coderme.myds.me:38880/captchaImage

```

然后开始启动后端容器，启动之前一定要确保redis和mysql运行成功，并把系统运行必须的数据导入到数据库了

```
docker run -it -d --name ruoyi-admin -p 8080:8080 ruoyi-admin:1.0

docker run -it -d --name ruoyi-admin -p 38880:8080 coderdream/ruoyi-admin:1.0

http://coderme.myds.me:38880/captchaImage

http://localhost:8080/captchaImage

```

到此后端算是部署完成了 

————————————————
版权声明：本文为CSDN博主「Richard_wg」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
原文链接：https://blog.csdn.net/Richard_wg/article/details/126340760