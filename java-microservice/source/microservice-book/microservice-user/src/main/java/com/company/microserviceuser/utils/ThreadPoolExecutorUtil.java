package com.company.microserviceuser.utils;

import com.company.microserviceuser.config.ThreadPoolConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.annotation.Resource;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Thread pool execute. 
 * 线程池初始化，
 * 创建Bean交给Spring管理，
 * 方便调用
 * @author xindaqi
 * @since 2020-10-17
 */
@Configuration
@EnableAsync
public class ThreadPoolExecutorUtil {
    @Resource
    private ThreadPoolConfig threadPoolConfig;

    @Bean 
    public Executor threadPoolExecutorExe() {
        //创建线程池
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 设置核心线程数
        executor.setCorePoolSize(threadPoolConfig.getCorePoolSize());
        // 设置最大线程数
        executor.setMaxPoolSize(threadPoolConfig.getMaxPoolSize());
        // 设置线程活跃时间
        executor.setKeepAliveSeconds(threadPoolConfig.getKeepAliveSeconds());
        //设置任务队列尺寸
        executor.setQueueCapacity(threadPoolConfig.getQueueCapacity());
        // 设置线程前缀名称
        executor.setThreadNamePrefix("MyThreadPool-");
        // 设置任务拒绝句柄
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 初始化
        executor.initialize();
        return executor;
    }
}