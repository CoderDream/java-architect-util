package com.gupaoedu.springcloud.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2227324689
 * http://www.gupaoedu.com
 **/
@RestController
public class TestController {

    @Qualifier
    @Autowired
    List<TestClass> testClassList= Collections.emptyList();

    @GetMapping("/test")
    public Object test(){
        return testClassList;
    }

}
