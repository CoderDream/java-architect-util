package com.coderdream.interfaces.controller;

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
public interface LinkController {

    @RequestMapping("/shortlink")
    @ResponseBody
    public String getShortLink();
}
