package com.coderdream.controller;

import com.coderdream.pojo.RespBean;
import com.coderdream.pojo.User2;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

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
        RespBean respBean = new RespBean();
        respBean.setUsername(username);
        respBean.setAddress(address);
        System.out.println("ABCD");
        return respBean;
    }
    @GetMapping("/")
    @ApiOperation("根据id查询用户的接口")
    @ApiImplicitParam(name = "id", value = "用户id", defaultValue = "99", required = true)
    public User2 getUserById(@PathVariable Integer id) {
        User2 user = new User2();
        user.setId(id);
        return user;
    }
    @PutMapping("/{id}")
    @ApiOperation("根据id更新用户的接口")
    public User2 updateUserById(@RequestBody User2 user) {
        System.out.println("####");
        return user;
    }
}