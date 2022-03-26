package com.shanghai.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：CoderDream
 * @date ：Created in 2022/3/21 20:10
 * @description：
 * @modified By：CoderDream
 * @version: $
 */

@RestController
public class TestController {

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String test(){
        System.out.println("这是控制台日志！");
        //log.info("这是输出到文件的日志");
        return   "HELLO-BUG！！！！！！！！！！";
    }
}
