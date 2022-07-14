package com.coderdream.controller;

import com.coderdream.pojo.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

@Api("Hello控制类")
@RestController
public class HelloController {

    //    @ApiOperation("Hello控制类")
//    @RequestMapping(value = "/hello", method = {RequestMethod.GET})
    @GetMapping(value = "/hello")
    public String hello() {
        return "hello";
    }


    //    @ApiOperation("Hello控制类")
    // 只要我们的接口中返回值中存在实体类，他就会被扫描到Swagger中
    @PostMapping(value = "/user")
    public User user() {
        return new User();
    }

    @ApiOperation("Hello控制类")
    @RequestMapping(value = "/hello2", method = {RequestMethod.POST}) // 指定方法，只显示1种请求类型
    public String hello2(@ApiParam("用户名") String username) {
        return "hello" + username;
    }

    @ApiOperation("Hello控制类")
    @RequestMapping(value = "/error", method = {RequestMethod.POST}) // 指定方法，只显示1种请求类型
    public String getError(@ApiParam("用户名") String username) {
    //    int i = 5 / 0;
        return "hello" + username;
    }

    @GetMapping("/getUser")
    public User getUser(User user) {
        User result = new User();
        result.setUsername(user.getUsername());
        result.setPassword(user.getPassword());
        return result;
    }

//    @ApiOperation("Hello控制类")
//    @RequestMapping(value = "/hello3") // 直接用RequestMapping，不用GetMapping或者PostMapping，会显示7种请求
//    public String hello3(@ApiParam("用户名") String username) {
//        return "hello" + username;
//    }
}
