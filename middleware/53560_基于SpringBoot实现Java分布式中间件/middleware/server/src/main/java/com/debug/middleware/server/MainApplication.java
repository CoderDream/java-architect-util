package com.debug.middleware.server;
/**
 * Created by Administrator on 2019/3/2.
 */

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

/**
 * 应用启动类-入口
 * @Author:debug (SteadyJack)
 * @Date: 2019/3/2 17:58
 **/
@SpringBootApplication
@MapperScan(basePackages = "com.debug.middleware.model")
public class MainApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return super.configure(builder);
    }

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class,args);
    }

    //读取环境变量的实例
    @Autowired
    private Environment env;

    //自定义注入Bean-ZooKeeper高度封装过的客户端Curator实例
    @Bean
    public CuratorFramework curatorFramework(){
        //创建CuratorFramework实例
        //（1）创建的方式是采用工厂模式进行创建；
        //（2）指定了客户端连接到ZooKeeper服务端的策略：这里是采用重试的机制(5次，每次间隔1s)
        CuratorFramework curatorFramework= CuratorFrameworkFactory.builder().connectString(env.getProperty("zk.host")).namespace(env.getProperty("zk.namespace"))
                .retryPolicy(new RetryNTimes(5,1000)).build();
        curatorFramework.start();
        //返回CuratorFramework实例
        return curatorFramework;
    }
}