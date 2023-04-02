package com.coderdream.freeapps;

import com.coderdream.freeapps.service.*;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class SnapshotTest {
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(SnapshotTest.class);
    @Autowired
    private AppService appService;

    @Autowired
    private FreeHistoryService freeHistoryService;

    @Resource
    private CrawlerHistoryService crawlerHistoryService;

    @Resource
    private PriceHistoryService priceHistoryService;
    @Resource
    private SnapshotService snapshotService;

    @Test
    public void testDailyProcess() {
        snapshotService.dailyProcess();
    }
}
