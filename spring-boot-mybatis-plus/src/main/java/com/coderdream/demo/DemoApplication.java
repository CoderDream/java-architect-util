package com.coderdream.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 全局启动类
 */
// 表示扫描mapper包下的所有接口
@MapperScan({"com.coderdream.demo.dao","com.coderdream.cxdz.dao"})
@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
