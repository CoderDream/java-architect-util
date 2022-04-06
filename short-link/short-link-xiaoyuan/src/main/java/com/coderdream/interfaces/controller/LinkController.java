package com.coderdream.interfaces.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author ：CoderDream
 * @date ：Created in 2022/4/6 23:18
 * @description：链接控制器
 * @modified By：CoderDream
 * @version: $
 */
@Controller
@EnableAutoConfiguration
@Api(value = "用户的增删改查")
public interface LinkController {

    @RequestMapping("/shortlink")
    @ResponseBody
    @ApiOperation(value = "获取短链接")
    public String getShortLink();
}
