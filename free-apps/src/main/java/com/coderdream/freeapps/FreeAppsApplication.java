package com.coderdream.freeapps;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.coderdream.freeapps.mapper")
public class FreeAppsApplication {

    public static void main(String[] args) {
        SpringApplication.run(FreeAppsApplication.class, args);
    }

}
