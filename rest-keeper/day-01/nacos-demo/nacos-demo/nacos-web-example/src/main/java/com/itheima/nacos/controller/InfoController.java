package com.itheima.nacos.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InfoController {

    @Value("${server.port}")
    private String port;

    @Value("${key:''}")
    private String key;

    @Value("${commonKey:''}")
    private String commonKey;

    @Value("${shareInfo:''}")
    private String shareInfo;

    @GetMapping(value = "/echo/{message}")
    public String echo(@PathVariable(value = "message") String message) {
        return "Hello Nacos Discovery " + message + ", i am from port " + port;
    }

    @GetMapping(value = "/config")
    public String config() {
        return "Hello Nacos Config get "+key +"       getCommonValue:  "+commonKey+"      getShareInfo:  "+shareInfo;
    }
}
