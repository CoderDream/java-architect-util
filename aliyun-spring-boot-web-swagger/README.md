# 工程简介



# 延伸阅读

1. []()

```xml


```





2. [idea无法下载源码Sources not found for:org.springframework](https://blog.csdn.net/weixin_43183496/article/details/117388567)

```sh
mvn dependency:resolve -Dclassifier=sources
```



### springboot @value 默认值_SpringBoot整合Swagger2，再也不用维护接口文档了！

```java
@RestController
@Api(tags = "用户管理相关接口")
@RequestMapping("/user")
public class UserController {
    @PostMapping("/")
    @ApiOperation("添加用户的接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", defaultValue = "李四"),
            @ApiImplicitParam(name = "address", value = "用户地址", defaultValue = "深圳", required = true)
    }
    )
    public RespBean addUser(String username, @RequestParam(required = true) String address) {
        return new RespBean();
    }
    @GetMapping("/")
    @ApiOperation("根据id查询用户的接口")
    @ApiImplicitParam(name = "id", value = "用户id", defaultValue = "99", required = true)
    public User getUserById(@PathVariable Integer id) {
        User user = new User();
        user.setId(id);
        return user;
    }
    @PutMapping("/{id}")
    @ApiOperation("根据id更新用户的接口")
    public User updateUserById(@RequestBody User user) {
        return user;
    }
}
```





1.[Swagger详解（SpringBoot+Swagger集成）](https://blog.csdn.net/ai_miracle/article/details/82709949)
2.[通过swagger生成接口的.json文件](https://blog.csdn.net/qq_41955582/article/details/107540368)
3.[超详细！使用swagger自动生成Api文档（swagger-ui）](https://blog.csdn.net/zhanggonglalala/article/details/98070986)
4.[Swagger UI 详细讲解(Swagger3)](https://blog.csdn.net/ljcgit/article/details/86608039)
5.[SpringBoot教程(十六) | SpringBoot集成swagger（全网最全）](https://blog.csdn.net/lsqingfeng/article/details/123678701)

6.[springboot @value 默认值_SpringBoot整合Swagger2，再也不用维护接口文档了！](https://blog.csdn.net/weixin_39757122/article/details/111395501)



### Postman 导入 Swagger 的json

* 进入swagger-ui页面，F12打开【Network】面板，选择【api-doc】请求，过滤选【Fetch/XHR】，然后复制【Response】，另存为json文件

 ![image-20220712162141082](image\image-20220712162141082.png)

* 导入，在Postman菜单栏点击【File】-【Import】，在弹出的面板选择【Upload File】：

![image-20220712161927711](image\image-20220712161754593.png)

* 设置变量（新增和修改）

![image-20220712163818996](image\image-20220712163416487.png)

* 使用变量

![image-20220712163625471](image\image-20220712163625471.png)







# END

