package com.coderdream.freeapps.handler;

import com.coderdream.freeapps.service.AppService;
import com.coderdream.freeapps.service.PriceHistoryService;
import com.coderdream.freeapps.service.SyncTaskService;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.XxlJob;
import javax.annotation.Resource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

/**
 * @author hresh
 * @博客 https://juejin.cn/user/2664871918047063
 * @网站 https://www.hreshhao.com/
 * @date 2022/11/27 4:28 下午
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class SyncTaskClHandler extends IJobHandler {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(SyncTaskClHandler.class);

    @Resource
    private SyncTaskService snapshotService;

    @XxlJob(value = "syncTaskClHandler")
    @Override
    public void execute() throws Exception {

        snapshotService.dailyProcess(null);
    }
}
