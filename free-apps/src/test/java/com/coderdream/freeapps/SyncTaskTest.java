package com.coderdream.freeapps;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.coderdream.freeapps.model.SyncTaskEntity;
import com.coderdream.freeapps.service.AppService;
import com.coderdream.freeapps.service.CrawlerHistoryService;
import com.coderdream.freeapps.service.DescriptionService;
import com.coderdream.freeapps.service.FreeHistoryService;
import com.coderdream.freeapps.service.MinioService;
import com.coderdream.freeapps.service.PriceHistoryService;
import com.coderdream.freeapps.service.SnapshotService;
import com.coderdream.freeapps.service.SyncTaskService;
import com.coderdream.freeapps.service.TopPriceService;
import com.coderdream.freeapps.util.CdDateUtils;
import com.coderdream.freeapps.util.ppt.excelutil.CdExcelUtils;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;

@SpringBootTest
public class SyncTaskTest {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(SyncTaskTest.class);
    @Autowired
    private SyncTaskService syncTaskService;


    @Resource
    private MinioService minioService;
    @Resource
    private SnapshotService snapshotService;

    @Resource
    private PriceHistoryService priceHistoryService;

    @Resource
    private TopPriceService topPriceService;
    @Resource
    private AppService appService;

    @Resource
    private DescriptionService descriptionService;

    @Test
    public void testList() {
        QueryWrapper<SyncTaskEntity> queryWrapper = new QueryWrapper<>();
        List<SyncTaskEntity> syncTaskEntityList = syncTaskService.list(queryWrapper);
        for (SyncTaskEntity syncTaskEntity : syncTaskEntityList) {
            logger.info(syncTaskEntity.toString());
        }
    }

    @Test
    public void testDailyProcess() {
        syncTaskService.dailyProcess(null);
        priceHistoryService.dailyProcess();
//        snapshotService.dailyProcess();
//        minioService.processDaily();
        descriptionService.dailyProcess();
        topPriceService.process(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        // 处理没有是否美区限免的应用
        appService.processNoUsFlag();
    }

    @Test
    public void testRecommend() {
        CdExcelUtils.m1();
    }

}
