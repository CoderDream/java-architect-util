package com.coderdream.freeapps;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.coderdream.freeapps.model.SyncTaskEntity;
import com.coderdream.freeapps.service.AppService;
import com.coderdream.freeapps.service.CrawlerHistoryService;
import com.coderdream.freeapps.service.FreeHistoryService;
import com.coderdream.freeapps.service.PriceHistoryService;
import com.coderdream.freeapps.service.SnapshotService;
import com.coderdream.freeapps.service.SyncTaskService;
import java.util.List;
import javax.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SyncTaskTest {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(SyncTaskTest.class);
    @Autowired
    private SyncTaskService snapshotService;

    @Test
    public void testList() {
        QueryWrapper<SyncTaskEntity> queryWrapper = new QueryWrapper<>();
        List<SyncTaskEntity> syncTaskEntityList = snapshotService.list(queryWrapper);
        for (SyncTaskEntity syncTaskEntity : syncTaskEntityList) {
            logger.info(syncTaskEntity.toString());
        }
    }
    //

    @Test
    public void testDailyProcess() {
        snapshotService.dailyProcess();
    }
}
