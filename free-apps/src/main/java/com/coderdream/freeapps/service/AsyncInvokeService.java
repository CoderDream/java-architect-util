package com.coderdream.freeapps.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.coderdream.freeapps.model.BossJobLogEntity;
import java.util.concurrent.Future;
import org.springframework.scheduling.annotation.Async;

/**
 * @author CoderDream
 */
public interface AsyncInvokeService extends IService<BossJobLogEntity> {

    /**
     *
     * @param name
     * @return
     */
    @Async("threadPoolTaskExecutor")
     Future<Boolean> exec1(String name);

    /**
     *
     * @param phone
     * @return
     */
    @Async("threadPoolTaskExecutor")
     Future<Boolean> exec2(String phone) ;
}
