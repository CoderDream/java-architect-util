package com.coderdream.freeapps.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.coderdream.freeapps.model.BossJobLogEntity;
import com.coderdream.freeapps.service.AsyncInvokeService;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.function.Function;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

/**
 * @author CoderDream
 */

@Service
public class AsyncInvokeServiceImpl {

    @Async("threadPoolTaskExecutor")
    public Future<Boolean> exec1(String name) {
        System.out.println("子线程 name -->" + Thread.currentThread().getName());
        System.out.println(name);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return new AsyncResult<>(true);
    }

    @Async("threadPoolTaskExecutor")
    public Future<Boolean> exec2(String phone) {
        System.out.println("子线程 name -->" + Thread.currentThread().getName());
        System.out.println(phone);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return new AsyncResult<>(true);
    }

}
