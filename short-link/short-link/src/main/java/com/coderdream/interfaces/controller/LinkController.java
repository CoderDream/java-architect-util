package com.coderdream.interfaces.controller;

import com.coderdream.common.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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

    @RequestMapping("/shortLink")
    @ResponseBody
    @ApiOperation(value = "获取短链接")
    public Result<String> getShortLink(@ApiParam("长链接地址") String longLink);

    @RequestMapping("/longLink")
    @ResponseBody
    @ApiOperation(value = "获取短链接")
    public Result<String> getLongLink(@ApiParam("短链接地址") String shortLink);
}
