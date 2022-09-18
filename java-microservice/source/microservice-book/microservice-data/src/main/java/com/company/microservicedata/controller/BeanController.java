package com.company.microservicedata.controller;


import com.company.microservicedata.service.impl.MessageQueueReceiveServiceImpl;
import com.company.microservicedata.util.ApplicationContextUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api")
@Api( tags = "Bean测试")
@RestController
public class BeanController {

    @Autowired
    private ApplicationContextUtil applicationContextUtil;

    @RequestMapping(value = "/beans", method = RequestMethod.GET)
    @ApiOperation("获取所有Bean")
    public String[] getAllBeans() {
        return applicationContextUtil.getAllBeans();
    }

    @RequestMapping(value = "/beans/byType", method = RequestMethod.GET)
    @ApiOperation("通过类型获取所有Bean")
    public String[] getAllBeansByType() {
        return applicationContextUtil.getBeansByType(MessageQueueReceiveServiceImpl.class);
    }
}
