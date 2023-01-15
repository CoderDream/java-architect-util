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





```
root
root

```



## springboot+vue前后端分离项目（后台管理系统）

https://blog.csdn.net/qq_52050769/article/details/119685283



学习笔记

学习资源来自于B站UP，up他讲的非常详细，对于熟悉两大框架很有用。

我的作业源代码在文章末尾，欢迎有需要的同学，学习参考使用，内置SQL文件，导入后，开启springboot和vue服务即可使用，注意更改自己的数据库信息配置，一起学习，一起进步哦！！

### 一、所使用的环境配置：
编译器：IDEA
后台框架：SpringBoot
Mybatis-Plus
数据库：Mysql8.0
数据库工具：Navicat premium
前端框架：Vue
Element UI
引用的富文本编辑器：wangEditor

###  二、项目简介

这是一个基于SpringBoot和Vue的后台管理系统。
主要功能：
1.实现用户信息的CRUD，以及页面的显示。
2.用户权限的分配，不同权限的用户锁能看到的的界面信息和能进行的操作是不同的。
3.实现图片，文件的上传和下载。
4.实现页面富文本编译器的使用与信息的CRUD。
5.跨域配置，MybatisPlus配置。
6.用户的登录注册，拦截器。
7.查询功能。
。。。。
项目展示：（图片）

* 1.登录界面

![在这里插入图片描述](assets\01.png)

* 2.注册页面这两个页面可以自由切换

![在这里插入图片描述](assets\02.png)

3.root登录后的默认页面以及高亮显示

![在这里插入图片描述](assets\03.png)

4.几个页面的展示

![在这里插入图片描述](assets\04.png)

5.root账户所能进行的CRUD操作和能查看的用户信息页面

![在这里插入图片描述](assets\05.png)

修改

![在这里插入图片描述](assets\06.png)

6.个人信息修改，以及退出

![在这里插入图片描述](assets\07.png)

7.普通用户登录

![在这里插入图片描述](assets\08.png)

这里只做了图书页面的权限限制和用户信息的限制

### 三、知识点总结（代码和配置）
配置文件：
SpringBoot：
#### 1.Mybatis-Plus配置文件，实现分页查询：MybatisPlusConfig
参考官网：MybatisPlus

```java
package com.wen.common;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//Spring boot方式
@Configuration
@MapperScan("com.wen.mapper")//这里所扫描的是项目中mapper文件的位置！
public class MybatisPlusConfig {

    // 旧版，官网的旧版视乎无法使用
    
    // 最新版
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }

}
```


#### 2.跨域配置文件：CorsConfig

```java
package com.wen.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {
    private static final long Max_AGE = 24*60*60;//连接时间
    private CorsConfiguration buildConfig(){
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        //定义所允许的请求头，方法等。*代表所有
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.setMaxAge(Max_AGE);
        return corsConfiguration;
    }
    @Bean
    public CorsFilter corsFilter(){
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",buildConfig());;//允许访问后台的所有接口
        return new CorsFilter(source);
    }
}
```


#### 3.请求返回类：Result
这里算是一个重点，解放了我平时后端coding的思维，非常感谢，没有想到get，set这么方便。
将所有的请求放回统一定义，根据项目所规定的code进行再定义与返回，达到项目通用的效果，非常实用！

```java
package com.wen.common;

public class Result<T> {
    private String code;
    private String msg;
    private T data;//定义泛型，用于接受数据。

    public String getCode() {
        return code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    
    public String getMsg() {
        return msg;
    }
    
    public void setMsg(String msg) {
        this.msg = msg;
    }
    
    public T getData() {
        return data;
    }
    
    public void setData(T data) {
        this.data = data;
    }
    public Result(){
    
    }
    
    public Result(T data) {
        this.data = data;
    }
    public static Result success(){
        Result result = new Result<>();
        result.setCode("0");
        result.setMsg("成功");
        return result;
    }
    
    public static <T> Result<T> success(T data){
        Result<T> result = new Result<>(data);
        result.setCode("0");
        result.setMsg("成功");
        return result;
    }
    public static Result error(String code,String msg){
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

}
```




#### 4.pom.xml配置文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.5.3</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>
    <groupId>com.wen</groupId>
    <artifactId>demo</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>demo</name>
    <description>Demo project for Spring Boot</description>
    <properties>
        <java.version>1.8</java.version>
    </properties>
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <!--spring mybatis-->
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <version>2.2.0</version>
        </dependency>

<!--        mybatis-plus-->
        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-boot-starter</artifactId>
            <version>3.4.3.1</version>
        </dependency>
<!--一个后端工具库-->
        <dependency>
            <groupId>cn.hutool</groupId>
            <artifactId>hutool-all</artifactId>
            <version>5.7.7</version>
        </dependency>

    </dependencies>
    
    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <excludes>
                        <exclude>
                            <groupId>org.projectlombok</groupId>
                            <artifactId>lombok</artifactId>
                        </exclude>
                    </excludes>
                </configuration>
            </plugin>
        </plugins>
    </build>

</project>
```

### Vue：

1.这里为解决未登录用户页面拦截的问题，同时封装了axios便于请求使用，在Vue中创建了一工具类/utils/：request.js

```js
import axios from 'axios'
import router from "@/router";

const request = axios.create({
    //baseUrl:'/api'
    timeout: 5000
})
// request 拦截器
// 可以自请求发送前对请求做一些处理
// 比如统一加token，对请求参数统一加密
request.interceptors.request.use(config => {
    config.headers['Content-Type'] = 'application/json;charset=utf-8';
    // config.headers['token'] = user.token;  // 设置请求头
    //取出sessionStorage里面的用户信息
    let userJson = sessionStorage.getItem("user");
    if (!userJson){
        router.push("/login");
    }
    return config
}, error => {
    return Promise.reject(error)
});
// response 拦截器
// 可以在接口响应后统一处理结果
request.interceptors.response.use(
    response => {
        let res = response.data;
        // 如果是返回的文件
        if (response.config.responseType === 'blob') {
            return res
        }
        // 兼容服务端返回的字符串数据
        if (typeof res === 'string') {
            res = res ? JSON.parse(res) : res
        }
        return res;
    },
    error => {
        console.log('err' + error) // for debug
        return Promise.reject(error)
    }
)
export default request
```


2.为解决跨域问题：在vue文件下新建vue.config.js文件

```js
// 跨域配置
module.exports = {
    devServer: {                //记住，别写错了devServer//设置本地默认端口  选填
        port: 9876,//设置的本项目端口
        proxy: {                 //设置代理，必须填
            '/api': {              //设置拦截器  拦截器格式   斜杠+拦截器名字，名字可以自己定
                target: 'http://localhost:9090/',     //代理的目标地址
                changeOrigin: true,              //是否设置同源，输入是的
                pathRewrite: {                   //路径重写
                    '/api': ''                     //选择忽略拦截器里面的单词
                }
            }
        }
    }
}
```

其余知识点总结：
SpringBoot后端文件上传和下载的Controller：FileController

```java
package com.wen.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.wen.common.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

@RestController
@RequestMapping("/files")
public class FileController {
    @Value("${server.port}")
    private String port;

    private static final String ip = "http://localhost";
    
    /**
     * 上传接口
     * @param file
     * @return
     */
    
    @PostMapping("/upload")
    public Result<?> upload(MultipartFile file){
        String originalFilename = file.getOriginalFilename();//获取源文件的名称

//        定义文件的唯一标识（前缀）
        String flag = IdUtil.fastSimpleUUID();

        String rootFilePath = System.getProperty("user.dir")+"/springboot/src/main/resources/files/"+flag+"_"+originalFilename;//获取文件上传的路径
        try {
            FileUtil.writeBytes(file.getBytes(),rootFilePath);//把文件写入该路径
        } catch (IOException e) {
            e.printStackTrace();
        }
        String url = ip+":"+port+"/files/"+flag;
        return Result.success(url);//返回结果url
    }
    
    /**
     * 下载接口
     * @param flag
     * @param response
     */
    @GetMapping("/{flag}")
    public void getFiles(@PathVariable String flag, HttpServletResponse response){
        OutputStream os;//新建一个输出对象
        String basePath = System.getProperty("user.dir")+"/springboot/src/main/resources/files/";//文件路径
        List<String> fileNames = FileUtil.listFileNames((basePath));//获取所有的文件名称
        String fileName = fileNames.stream().filter(name -> name.contains(flag)).findAny().orElse("");//找到根参数一致的文件
    
        try {
            if (StrUtil.isNotEmpty(fileName)){
                response.addHeader("Content-Disposition","attachment;filename="+ URLEncoder.encode(fileName,"UTF-8"));
                response.setContentType("application/octet-stream");
                byte[] bytes = FileUtil.readBytes(basePath + fileName);//通过文件路径读取文字节流
                os = response.getOutputStream();//通过输出流返回文件
                os.write(bytes);
                os.flush();
                os.close();
            }
        }catch (Exception e){
            System.out.println("文件下载失败");
        }
    }
    /**
     * 富文本上传接口
     * @param file
     * @return
     */
    
    @PostMapping("editor/upload")
    public JSON editorUpload(MultipartFile file){
        String originalFilename = file.getOriginalFilename();//获取源文件的名称

//        定义文件的唯一标识（前缀）
        String flag = IdUtil.fastSimpleUUID();

        String rootFilePath = System.getProperty("user.dir")+"/springboot/src/main/resources/files/"+flag+"_"+originalFilename;//获取文件上传的路径
        try {
            FileUtil.writeBytes(file.getBytes(),rootFilePath);//把文件写入该路径
        } catch (IOException e) {
            e.printStackTrace();
        }
        String url = ip+":"+port+"/files/"+flag;
        JSONObject jsonObject = new JSONObject();
        jsonObject.set("errno",0);
        JSONArray arr = new JSONArray();
        JSONObject data = new JSONObject();
        arr.add(data);
        data.set("url",url);
        jsonObject.set("data",arr);
        return jsonObject;//返回结果url
    }

}
```




### 总结：

@Value：获取配置文件中指定的数据（这里的server.port存在于项目文件中application.yaml文件中），存入下方定义的变量中。
MultipartFile：用于接收上传文件的类，推荐文章，其中包含了该类的许多用法，很详细。
IdUtil.fastSimpleUUID()：使用的是hutool中的方法，用于生成唯一标识的UUID，加在上传图片的前面，用于唯一区别，避免了相同文件名上传后覆盖的问题。
System.getProperty(“user.dir”)：获取当前项目的根目录，在本项目中也就是springboot-vue-demo目录了。
HttpServletResponse：http请求的响应。（学习重点，自己也不是很熟啦，加强学习！）
response.addHeader(“Content-Disposition”,“attachment;filename=”+ URLEncoder.encode(fileName,“UTF-8”));：添加相应头，定义文件下载后的名字。
response.setContentType(“application/octet-stream”);：定义文件下载的格式，二进制流。
关于Mybatis-Plus: 总之就是非常方便，结合lombok进行开发极大的简化了后端的实体定义和数据库相关的操作问题。
SpringBoot中：

SpringBoot通过maven引入MybatisPlus

```xml
<dependency>
    <groupId>com.baomidou</groupId>
    <artifactId>mybatis-plus-boot-starter</artifactId>
    <version>mybatis-plus-latest-version</version>//这里记得更改成版本号，这样是无法导入的！
</dependency>
```


配置只需要通过@MapperScan注解即可使用

```java
@MapperScan("com.baomidou.mybatisplus.samples.quickstart.mapper")//这里是项目中mapper存放的文件路径。
```

需要使用的注解：官网
例如：在实体上使用@TableName(“user”)，即是将实体与数据库中相应的表格相对应，@TableId，即是数据库中的主键。
定义接口，即是mapper层或者service层：继承BaseMapper<相应的实体名>即可使用BaseMapper中的方法，包括各种CRUD操作，如果有定义自身的XXmapper.xml文件的话，就会使用xml文件中相应的CRUD方法。官方中的所有接口

```java
public interface BookMapper extends BaseMapper<Book> {
}
```


关于wangEditor：
哈哈哈，解决了重复创建编辑器的问题！
学习时错误如下：
问题描述：由于编辑器的节点只有在弹窗创建之后才能生成，也就是才能获取，在项目中，原本的代码会导致新增和修改弹窗重复创建编辑器。

解决办法：

```js
let editor;
method:{
creatDom(){
      editor = new E('#div1');//富文本编辑器创建，获取节点
      // 配置 server 接口地址
      editor.config.uploadImgServer = 'http://localhost:9090/files/editor/upload';
      editor.config.uploadFileName = 'file';//设置文件上传的名字
      editor.create();//创建。
    },
    //这里是新增弹窗
    add(){
      this.dialogVisible = true;
      this.form = {};
      //由于只有在弹窗启动之后，div节点才会被创建，那么创建富文本编辑器也只能在其之后。
      this.$nextTick(()=>{
        if (editor==null){
          this.creatDom();
        }else {
          editor.destroy();//这里做了一次判断，判断编辑器是否被创建，如果创建了就先销毁。
          this.creatDom();
        }
      });
    },
    //这里是修改弹窗
    handleEdit(row){
      this.form = JSON.parse((JSON.stringify(row)));
      this.dialogVisible = true;
      this.$nextTick(()=>{
        if (editor==null){
          this.creatDom();
          editor.txt.html(row.content);
        }else {
          editor.destroy();//这里做了一次判断，判断编辑器是否被创建，如果创建了就先销毁。
          this.creatDom();
          editor.txt.html(row.content);
        }
      });
    },
}
```


关于Vue中：
使用sessionStorage获取登录用户的信息，用于判断用户是否登录，以及从中获取用户的权限、昵称、年龄等信息用于页面的显示，若用户为进行登录则重定向转到登录界面。
所需用的代码如下：非同一页面使用

```js
sessionStorage.removeItem("user");//登录界面加载前先清楚已有的用户信息（若有）
sessionStorage.setItem("user",JSON.stringify(res.data));//缓存用户信息

let userStr =sessionStorage.getItem("user") || '{}';//从sessionStorage中获取用户信息
this.user = JSON.parse(userStr);//将JSON转为JavaScript对象，再赋值给user对象
```


Vue的路由：

```js
import { createRouter, createWebHistory } from 'vue-router'
import Layout from '../layout/Layout.vue'

const routes = [
  {
    path: '/',
    name: 'Layout',
    component: Layout,
    redirect: "/news",
    children: [
      {
        path: 'user',
        name: 'User',
        component: () => import("@/views/User"),
      },
      {
        path: '/book',
        name: 'Book',
        component: ()=>import("@/views/Book")
      },
      {
        path: '/person',
        name: 'Person',
        component: ()=>import("@/views/Person")
      },
      {
        path: '/news',
        name: 'News',
        component: ()=>import("@/views/News")
      },
    ]
  },
  {
    path: '/login',
    name: 'Login',
    component: ()=>import("@/views/Login")
  },
  {
    path: '/register',
    name: 'Register',
    component: ()=>import("@/views/Register")
  },

]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
```


其中： 主路由为/，访问时重定向到/news页面，在主路由下有许多子路由，及children中的数组对象，即是同一页面，不同子页面。

项目源代码：Github仓库

Talk is cheap，show me the code！—
————————————————
版权声明：本文为CSDN博主「先养只猫」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
原文链接：https://blog.csdn.net/qq_52050769/article/details/119685283





# END
