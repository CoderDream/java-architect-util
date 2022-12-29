# springboot-vue-demo

## Project setup
```
npm install
```

### Compiles and hot-reloads for development
```
npm run serve
```

### Compiles and minifies for production
```
npm run build
```

### Customize configuration
See [Configuration Reference](https://cli.vuejs.org/config/).



### 后端打包

```shell
# 1.1
mvn clean


# 1.2
mvn install -Dmaven.test.skip=true

# 1.3
docker build -t coderdream/spring-boot:1.0.0 .

# 1.4
docker push coderdream/spring-boot:1.0.0 # :1.0.0指定标签

# 1.5
docker run \
--name coderdream_spring-boot -d \
-p 9090:9090 \
--privileged=true \
--restart=always \
coderdream/spring-boot:1.0.0

```



### 前端打包上传

```shell
# 前端打包
# 2.1 
npm install

# 2.2
npm run build

# 2.3
docker build -t coderdream/vue:1.0.0 .

# 2.4
docker push coderdream/vue:1.0.0

# 2.5
docker run \
--name coderdream_vue -d \
-p 39876:9876 \
--privileged=true \
--restart=always \
coderdream/vue:1.0.0

```



```
docker push coderdream/vue-element-admin # 不指定标签，相当于:latest

docker tag vue-element-admin:1.0.0 coderdream/vue-element-admin:1.0.0 

docker tag 4f0459f6a1cb coderdream/vue:1.0.0 

docker push coderdream/vue:1.0.0

http://172.16.104.61:9876

172.16.104.61:9090

http://59.172.75.156:39876
```



```
Request URL: http://192.168.3.4:39876/api/user/login
Request Method: POST
Status Code: 405 Not Allowed
Remote Address: 192.168.3.4:39876
Referrer Policy: strict-origin-when-cross-origin
```

