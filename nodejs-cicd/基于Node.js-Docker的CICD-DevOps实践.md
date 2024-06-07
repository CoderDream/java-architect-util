## NAS版本文档

实验步骤：

### 1、搭建一个 git 服务

#### 1.1 拉取 gogs 镜像：

```
docker pull gogs/gogs
```

> root@DS920plus:~# docker pull gogs/gogs
> Using default tag: latest
> latest: Pulling from gogs/gogs
> 97518928ae5f: Pull complete
> d20a5437c0b4: Pull complete
> ec9934e62f5f: Pull complete
> 2a880a4f6876: Pull complete
> 7bb13647629b: Pull complete
> 641b202f48eb: Pull complete
> 8f12f9a5cd7d: Pull complete
> Digest: sha256:fbae7c126411d3fa3f8fdca17a65b196887ab23e3b7375f682a13daf5806bb19
> Status: Downloaded newer image for gogs/gogs:latest
> docker.io/gogs/gogs:latest

#### 1.2 创建 gogs 存储目录：

```
mkdir /volume1/docker/docker_data/gogs
```

#### 1.3 所有者为当前用户

```
chown -R ${USER} /volume1/docker/docker_data/gogs
```

#### 1.4 创建 docker 网络便于容器之间通信，

```
docker network create cicd_net
```

> root@DS920plus:~# sudo mkdir /volume1/docker/docker_data/gogs
> root@DS920plus:~# sudo chown -R ${USER} /volume1/docker/docker_data/gogs
> root@DS920plus:~# docker network create cicd_net
> 9917b10733fba6430000daf795f618ed76d4b11e0d6008b239c6d416fc561b74

#### 1.5 启动 gogs 容器

```
docker run -d --name=gogs \
        --network cicd_net \
        -p 28033:3000 -p 23022:22 \
        -v /volume1/docker/docker_data/gogs:/data \
        gogs/gogs
```

>         root@DS920plus:~# docker run -d --name=gogs \
>         	--network cicd_net \
>         	-p 28033:3000 -p 23022:22 \
>         	-v /volume1/docker/docker_data/gogs:/data \
>         	gogs/gogs
>         2cdf5be288a40b0ff8b651af9aff9f08cb4a25df414a4e2d3de7591390dca2c9
>         root@DS920plus:~#

#### 1.6 浏览器首次访问 gogs，配置安装 gogs

> http://192.168.3.4:28033/

![image-20240607102339044](基于Node.js-Docker的CICD-DevOps实践/image-20240607102339044.png)

![image-20240607102526255](基于Node.js-Docker的CICD-DevOps实践/image-20240607102526255.png)

![image-20240607102617530](基于Node.js-Docker的CICD-DevOps实践/image-20240607102617530.png)

#### 1.7 注册 gogs 网站账号

> http://192.168.3.4:28033/user/login

![image-20240607102820037](基于Node.js-Docker的CICD-DevOps实践/image-20240607102820037.png)

> http://192.168.3.4:28033/user/sign_up

![image-20240607102923481](基于Node.js-Docker的CICD-DevOps实践/image-20240607102923481.png)

#### 1.8 登录 gogs

![image-20240607102949587](基于Node.js-Docker的CICD-DevOps实践/image-20240607102949587.png)

#### 1.9 添加 SSH 密钥

![image-20240607103052924](基于Node.js-Docker的CICD-DevOps实践/image-20240607103052924.png)

> ssh-keygen -t rsa -C "coderdream@gmail.com"

![image-20240607103300015](基于Node.js-Docker的CICD-DevOps实践/image-20240607103300015.png)

![image-20240607103237394](基于Node.js-Docker的CICD-DevOps实践/image-20240607103237394.png)

![image-20240607103442448](基于Node.js-Docker的CICD-DevOps实践/image-20240607103442448.png)

因为要使用NAS下的docker，所以NAS的ssh也要添加

 ![image-20240607105306107](基于Node.js-Docker的CICD-DevOps实践/image-20240607105306107.png)

![image-20240607105249193](基于Node.js-Docker的CICD-DevOps实践/image-20240607105249193.png)

#### 1.10 创建 git 仓库

![image-20240607103507952](基于Node.js-Docker的CICD-DevOps实践/image-20240607103507952.png)

![image-20240607103548830](基于Node.js-Docker的CICD-DevOps实践/image-20240607103548830.png)

![image-20240607103608381](基于Node.js-Docker的CICD-DevOps实践/image-20240607103608381.png)

### 2、编写 web 服务提交 git

#### 2.1 克隆仓库

更改IP，克隆项目

> http://192.168.3.4:28033/CoderDream/cicd_web.git

![image-20240607103839359](基于Node.js-Docker的CICD-DevOps实践/image-20240607103839359.png)

#### 2.2 编写 web 服务

```js
const http=require('http')
http.createServer((req,res)=>{
    res.end('I am web V4.0')
}).listen(3000, ()=>{
    console.log('webserver start')
})
```

#### 2.3 提交代码

> PS D:\04_GitHub\java-architect-util\nodejs-cicd\cicd_web> git add .
> PS D:\04_GitHub\java-architect-util\nodejs-cicd\cicd_web> git commit -m 'first c'
> [master (root-commit) 049ed9e] first c
> 1 file changed, 0 insertions(+), 0 deletions(-)
> create mode 100644 index.js
> PS D:\04_GitHub\java-architect-util\nodejs-cicd\cicd_web> git push
> Enumerating objects: 3, done.
> Counting objects: 100% (3/3), done.
> Writing objects: 100% (3/3), 206 bytes | 51.00 KiB/s, done.
> Total 3 (delta 0), reused 0 (delta 0), pack-reused 0
> Username for 'http://192.168.3.4:28033': CoderDream
> Password for 'http://CoderDream@192.168.3.4:28033':
> To http://192.168.3.4:28033/CoderDream/cicd_web.git
>
>  * [new branch]      master -> master
> PS D:\04_GitHub\java-architect-util\nodejs-cicd\cicd_web>

![image-20240607104103667](基于Node.js-Docker的CICD-DevOps实践/image-20240607104103667.png)

#### 2.4 gogs 上查看提交代码

![image-20240607104207339](基于Node.js-Docker的CICD-DevOps实践/image-20240607104207339.png)

### 3、web 服务生成 docker 镜像

#### 3.1 为 web 服务编写 Dockerfile

![image-20240607104543198](基于Node.js-Docker的CICD-DevOps实践/image-20240607104543198.png)

Dockerfile
```dockerfile
FROM node:12.13-alpine

WORKDIR /usr/src/app

COPY package*.json ./

RUN npm install --only=production

COPY . .

CMD [ "node", "index.js" ]
```

package.json

```
PS D:\04_GitHub\java-architect-util\nodejs-cicd\cicd_web> npm init -y
Wrote to D:\04_GitHub\java-architect-util\nodejs-cicd\cicd_web\package.json:

{
  "name": "cicd_web",
  "version": "1.0.0",
  "description": "",
  "main": "index.js",
  "scripts": {
    "test": "echo \"Error: no test specified\" && exit 1"
  },
  "keywords": [],
  "author": "",
  "license": "ISC"
}


PS D:\04_GitHub\java-architect-util\nodejs-cicd\cicd_web> 
```

![image-20240607105625552](基于Node.js-Docker的CICD-DevOps实践/image-20240607105625552.png)

#### 3.2 构建 web 镜像

在NAS上构建镜像

![image-20240607105723001](基于Node.js-Docker的CICD-DevOps实践/image-20240607105723001.png)

> docker build -t cicd-web .

![image-20240607110027447](基于Node.js-Docker的CICD-DevOps实践/image-20240607110027447.png)

#### 3.3 启动 web 容器

> docker run --name=cicdweb -d -p 23300:3000 cicd-web

![image-20240607110112993](基于Node.js-Docker的CICD-DevOps实践/image-20240607110112993.png)

![image-20240607110306079](基于Node.js-Docker的CICD-DevOps实践/image-20240607110306079.png)

#### 3.4 访问 web 容器

> http://192.168.3.4:23300

![image-20240607110346523](基于Node.js-Docker的CICD-DevOps实践/image-20240607110346523.png)

### 4、编写一个类似 jenkins 的 deploy 服务

![image-20240607110759329](基于Node.js-Docker的CICD-DevOps实践/image-20240607110759329.png)



> git clone http://192.168.3.4:28033/CoderDream/deploy.git



deploy ->index.js 端口4000

```js
const http=require('http')
const cp=require('child_process')
http.createServer((req,res)=>{
    let proc=cp.exec('./deploy.sh',()=>{})
    proc.stdout.pipe(process.stdout)
    proc.stderr.pipe(process.stderr)
    res.end('I can deploy server')
}).listen(4000, ()=>{
    console.log('deploy webserver start')
})
```

deploy->deploy.sh

```
#! /bin/sh

docker build -t cicd-web .
docker run --name=cicdweb -d -p 23333:3000 cicd-web
```

构建Docker镜像前拉取仓库

在NAS拉取代码

> git clone http://192.168.3.4:28033/CoderDream/deploy.git



> chmod +x deploy.sh

 ![image-20240607114530176](基于Node.js-Docker的CICD-DevOps实践/image-20240607114530176.png)

#### 4.2 提交代码

> git config --global user.email "coderdream@gmail.com"
> git config --global user.name "CoderDream"

> root@DS920plus:/volume1/home/deploy# chmod +x deploy.sh
> root@DS920plus:/volume1/home/deploy# git add .
> root@DS920plus:/volume1/home/deploy# git commit -m 'xxx'
> git push

![image-20240607120526235](基于Node.js-Docker的CICD-DevOps实践/image-20240607120526235.png)



![image-20240607144520230](基于Node.js-Docker的CICD-DevOps实践/image-20240607144520230.png)



```
#! /bin/sh
pjName='cicd-web'

if [ ! -d "www/${pjName}" ]; then
    echo git clone
    cd www
    git clone http://192.168.3.4:28033/CoderDream/${pjName}
    cd ${pjName}
else
    echo 'git pull'
    cd www/${pjName}
    git pull

fi

docker stop cicdweb
docker rm -f cicdweb
docker rmi -f cicd-web

docker build -t cicd-web .
docker run --name=cicdweb -d -p 23333:3000 cicd-web
```

#### 4.3 build deploy 容器

> docker build -t cicd-deploy .

```

root@DS920plus:/volume1/home/deploy# docker build -t cicd-deploy .
Sending build context to Docker daemon  74.75kB
Step 1/7 : FROM node:12.13-alpine
 ---> 3fb8a14691d9
Step 2/7 : RUN apk add git
 ---> Running in 682011be94f3
fetch http://dl-cdn.alpinelinux.org/alpine/v3.10/main/x86_64/APKINDEX.tar.gz
fetch http://dl-cdn.alpinelinux.org/alpine/v3.10/community/x86_64/APKINDEX.tar.gz
(1/6) Installing ca-certificates (20191127-r2)
(2/6) Installing nghttp2-libs (1.39.2-r1)
(3/6) Installing libcurl (7.66.0-r4)
(4/6) Installing expat (2.2.8-r0)
(5/6) Installing pcre2 (10.33-r0)
(6/6) Installing git (2.22.5-r0)
Executing busybox-1.30.1-r2.trigger
Executing ca-certificates-20191127-r2.trigger
OK: 22 MiB in 22 packages
Removing intermediate container 682011be94f3
 ---> 4617cadb68b8
Step 3/7 : WORKDIR /usr/src/app
 ---> Running in ea70a219c908
Removing intermediate container ea70a219c908
 ---> c183b81110b6
Step 4/7 : COPY package*.json ./
 ---> 36c22fac3412
Step 5/7 : RUN npm install --only=production
 ---> Running in 8d74d4e61042
npm notice created a lockfile as package-lock.json. You should commit this file.
npm WARN deploy@1.0.0 No description
npm WARN deploy@1.0.0 No repository field.

up to date in 1.01s
found 0 vulnerabilities

Removing intermediate container 8d74d4e61042
 ---> 4c5a7fa3bb02
Step 6/7 : COPY . .
 ---> 97a5223f080c
Step 7/7 : CMD [ "node", "index.js" ]
 ---> Running in aca19d99474d
Removing intermediate container aca19d99474d
 ---> 9960da5bfd2d
Successfully built 9960da5bfd2d
Successfully tagged cicd-deploy:latest
root@DS920plus:/volume1/home/deploy#
```



![image-20240607162031702](基于Node.js-Docker的CICD-DevOps实践/image-20240607162031702.png)

#### 4.4 deploy web 服务启动子进程，执行 shell 脚本

```
#! /bin/sh
pjName='cicd_web'

if [ ! -d "www/${pjName}" ]; then
    echo 'git clone'
    cd www
    git clone http://192.168.3.4:28033/CoderDream/${pjName}
    cd ${pjName}
else
    echo 'git pull'
    cd www/${pjName}
    git pull
fi

docker stop cicdweb
docker rm -f cicdweb
docker rmi -f cicd-web

docker build -t cicd-web .
docker run --name=cicdweb -d -p 23333:3000 cicd-web
```



- shell 脚本完成以下工作：
  - web 项目代码克隆或者拉取
  - 删除 web 旧容器和镜像
  - 生成新的 web 镜像
- 给 deply web 创建 Dockerfile
- 生成 deploy web 服务镜像
- 启动 deploy web 容器

![image-20240607145549877](基于Node.js-Docker的CICD-DevOps实践/image-20240607145549877.png)

> ll /usr/local/bin/docker
>
> ll /var/run/docker.sock
>
> 
>
> docker run -d --name=deploy --network cicd_net -p 24444:4000 -v /usr/local/bin/docker:/usr/bin/docker -v /var/run/docker.sock:/var/run/docker.sock --user root cicd-deploy

![image-20240607162229959](基于Node.js-Docker的CICD-DevOps实践/image-20240607162229959.png)

![image-20240607162308177](基于Node.js-Docker的CICD-DevOps实践/image-20240607162308177.png)





```
http://192.168.3.4:24444/
```

![image-20240607165018596](基于Node.js-Docker的CICD-DevOps实践/image-20240607165018596.png)

```
http://192.168.3.4:23333/
```

![image-20240607165031664](基于Node.js-Docker的CICD-DevOps实践/image-20240607165031664.png)

![image-20240607165237012](基于Node.js-Docker的CICD-DevOps实践/image-20240607165237012.png)

```

Successfully tagged cicd-web:latest

docker: Error response from daemon: Conflict. The container name "/cicdweb" is already in use by container "c6a42d20e6cdffa0d96cf2772ae34b5152a2f179dc3633c79044a41503348746". You have to remove (or rename) that container to be able to reuse that name.

See 'docker run --help'.

docker: Error response from daemon: pull access denied for cicd-web, repository does not exist or may require 'docker login': denied: requested access to the resource is denied.

See 'docker run --help'.

docker: Error response from daemon: pull access denied for cicd-web, repository does not exist or may require 'docker login': denied: requested access to the resource is denied.

```



![image-20240607165413319](基于Node.js-Docker的CICD-DevOps实践/image-20240607165413319.png)

```

```



```dockerfile
FROM node:12.13-alpine
RUN sed -i 's/dl-cdn.alpinelinux.org/mirrors.aliyun.com/g' /etc/apk/repositories
RUN apk add git

WORKDIR /usr/src/app

COPY package*.json ./

RUN npm install --only=production

COPY . .

CMD [ "node", "index.js" ]

```

> dockerfile RUN apk add 卡住问题解决
> https://blog.csdn.net/zhangzhen02/article/details/112217348

#### 4.5 git hook 让 deploy 自动构建镜像生成 web 容器

- 配置 gogs 的 web hook，仓库提交代码时，推送 deploy web 服务

![image-20240607173833127](基于Node.js-Docker的CICD-DevOps实践/image-20240607173833127.png)

#### 4.6 成功自动发布

```
git pull
From http://192.168.3.4:28033/CoderDream/cicd_web
   3379a7e..acccb88  master     -> origin/master
Updating 3379a7e..acccb88
      
Fast-forward
 index.js | 2 +-
 1 file changed, 1 insertion(+), 1 deletion(-)
cicdweb
cicdweb
Untagged: cicd-web:latest
Deleted: sha256:e998366c5bb4140075de349bacb516049766cb01b043ded2cef5fe1e5f530673
Sending build context to Docker daemon  78.34kB
Step 1/6 : FROM node:12.13-alpine
 ---> 3fb8a14691d9
Step 2/6 : WORKDIR /usr/src/app
 ---> Using cache
 ---> 683f46e5efdf
Step 3/6 : COPY package*.json ./
 ---> Using cache
 ---> 49619e4c1053
Step 4/6 : RUN npm install --only=production
 ---> Using cache
 ---> 1dab17574e6a
Step 5/6 : COPY . .
 ---> 682371c58994
Step 6/6 : CMD [ "node", "index.js" ]
 ---> Running in 312edcbfb7a9
Removing intermediate container 312edcbfb7a9
 ---> 17de5269462d
Successfully built 17de5269462d
Successfully tagged cicd-web:latest
6e7ff44d92a65d3dc8506831ffb7a821047dfe61aa1d70a12d47239ea270bf14
```

修改web应用提交到git后，deploy自动发布：

 ![image-20240607174315936](基于Node.js-Docker的CICD-DevOps实践/image-20240607174315936.png)

### 原始文档

https://www.bilibili.com/video/BV1jf4y127jT/?spm_id_from=333.337.search-card.all.click&vd_source=503207d99395b6ec89fb3e289a9be411

实验步骤：
1. 搭建一个 git 服务
- 拉取 gogs 镜像：`docker pull gogs/gogs`
- 创建 gogs 存储目录：`sudo mkdir /var/gogs`
- 所有者为当前用户，`sudo chown -R ${USER} /var/gogs`
- 创建 docker 网络便于容器之间通信，`docker network create cicd_net`
-  启动 gogs 容器
```
docker run -d --name=gogs \
        --network cicd_net \
        -p 8080:3000 -p 3000:22 \
        -v /var/gogs:/data \
        gogs/gogs
```
- 浏览器首次访问 gogs，配置安装 gogs
- 注册 gogs 网站账号
- 登录 gogs
- 添加 SSH 密钥
- 创建 git 仓库

2. 编写 web 服务提交 git
- 克隆仓库
- 编写 web 服务
- 提交代码
- gogs 上查看提交代码

3. web 服务生成 docker 镜像
- 为 web 服务编写 Dockerfile
- 构建 web 镜像
- 启动 web 容器
- 访问 web 容器

4. 编写一个类似 jenkins 的 deploy 服务
- deploy web 服务启动子进程，执行 shell 脚本
- shell 脚本完成以下工作：
  - web 项目代码克隆或者拉取
  - 删除 web 旧容器和镜像
  - 生成新的 web 镜像
- 给 deply web 创建 Dockerfile
- 生成 deploy web 服务镜像
- 启动 deploy web 容器

5. git hook 让 deploy 自动构建镜像生成 web 容器
- 配置 gogs 的 web hook，仓库提交代码时，推送 deploy web 服务

```
带大家快速实践下基于node.dokkar的CICD DevOps开发流程
视频演示内容
首先搭建一个Gate服务器
然后编写一个web服务
提交到Gate
然后为web服务编写Dokkar file生成镜像
编写一个类似jinkx的Deploy服务
Gatehook让Deploy自动构建镜像生成web容器
首先搭建一个Gate服务器
拉取Gogos镜像
搭建Gate服务
创建Gogos存储目录
所有者为当前用户
创建Dokkar网络便与容器进行通信
启动Gogos容器
首次访问Gogos 配置安装
注册Gate账号
登录Gate账号
添加一个密钥
创建一个Gate仓库
克隆项目
完成第一次提交
Gogos上查看提交内容
编写web服务
为web服务编写Dokkar File
构建web镜像
生成web容器
访问web服务
编写一个类似jinkx的自动化任务
Deploy服务
新建执行Shale任务脚本
Deploy服务执行Shale
紫进程输出流输出到主进程
Shale完成构建web镜像生成web容器的功能
构建Dokkar镜像前拉取仓库
部署前先删除旧的容器和镜像
Deploy需要安装Gate环境
Build Deploy镜像
下载Gate工具 Run Deploy容器
Deploy需要使用宿主机Dokkar命令构建Dokkar镜像
可以看到生成的Deploy容器
删除之前的web容器
访问Destroy容器服务
进入Destroy容器查看信息
可以看到找不到Dokkar File
Gate服务没有Dokkar File
提交Dokkar File
提交成功
再次访问Deploy容器服务
Deploy自动部署web容器成功
这里也可以看到自动部署的web容器
可以看到部署web内容
修改web重新提交
访问Deploy重新构建镜像运行容器
可以看到我们新的修改效果
我们使用Gate Hook让Deploy自动化
填写Deploy访问地址
修改项目重新提交
可以看到推送记录
访问web容器已经自动部署成功
再次提案一把
完全没问题
如果Deploy服务和生产环境不在一个Dokkar速度机
Deploy应该publish Docker镜像到远程镜像仓库
然后SSH远程登录执行Shall
生产环境执行拉取镜像
构建镜像和运行容器的任务
```

