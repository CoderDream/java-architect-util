package com.coderdream.freeapps.handler;

import com.coderdream.freeapps.service.*;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author hresh
 * @博客 https://juejin.cn/user/2664871918047063
 * @网站 https://www.hreshhao.com/
 * @date 2022/11/27 4:28 下午
 */
@Component
@RequiredArgsConstructor
public class DailyPriceHandler extends IJobHandler {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(DailyPriceHandler.class);

    @Resource
    private AppService appService;

    @Resource
    private PriceHistoryService priceHistoryService;

    @XxlJob(value = "dailyPriceHandler")
    @Override
    public void execute() throws Exception {
        priceHistoryService.dailyProcess();
    }
}
