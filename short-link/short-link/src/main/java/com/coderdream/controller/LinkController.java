package com.coderdream.controller;

import com.coderdream.common.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：CoderDream
 * @date ：Created in 2022/4/12 22:30
 * @description：
 * @modified By：CoderDream
 * @version: $
 */
@RestController
@RequestMapping("/api/v1")
@EnableAutoConfiguration
@Api(value = "短链接控制器")
public interface LinkController {

    @RequestMapping("/shortLink")
    @ResponseBody
    @ApiOperation(value = "获取短链接")
    public Result<String> getShortLink(@ApiParam("长链接地址") String longLink);

    @RequestMapping("/longLink")
    @ResponseBody
    @ApiOperation(value = "获取长链接")
    public Result<String> getLongLink(@ApiParam("短链接地址") String shortLink);
}