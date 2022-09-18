package com.company.microserviceuser.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.scheduling.annotation.Async;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import com.company.microserviceuser.service.IThreadPoolService;

/**
 * Threadpool service implements. 
 * 线程池实现
 * @author xindaqi
 * @since 2020-10-16
 */
@Service
public class ThreadPoolServiceImpl implements IThreadPoolService{

    static Logger logger = LoggerFactory.getLogger(ThreadPoolServiceImpl.class);

    /**
     * 批量数据处理，
     * 通过@Async注解，调用线程池
     *
     * @param params 参数列表
     */
    @Override 
    @Async 
    public void dataProcessBatch(List<Integer> params) {
        logger.info("Start asynchronized task");
        try {
            List<Integer> processData = new ArrayList<>();
            // 使用Lambda表达式遍历参数列表
            params.forEach(data -> {
                Integer process = data + 1;
                processData.add(process);
            });
            // 时间延迟：8秒
            TimeUnit.SECONDS.sleep(8);
            // 控制台输出处理结果
            logger.info("计算结果：{}", processData);
        }catch(Exception e) {
            logger.error("异常：", e);
        }
        logger.info("End asynchronized task");
    }
    
}