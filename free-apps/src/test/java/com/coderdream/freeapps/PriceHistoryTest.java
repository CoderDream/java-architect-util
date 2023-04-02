package com.coderdream.freeapps;

import com.coderdream.freeapps.service.AppService;
import com.coderdream.freeapps.service.CrawlerHistoryService;
import com.coderdream.freeapps.service.FreeHistoryService;
import com.coderdream.freeapps.service.PriceHistoryService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class PriceHistoryTest {
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(PriceHistoryTest.class);
    @Autowired
    private AppService appService;

    @Autowired
    private FreeHistoryService freeHistoryService;

    @Resource
    private CrawlerHistoryService crawlerHistoryService;

    @Resource
    private PriceHistoryService priceHistoryService;

    @Test
    public void testDailyPriceHandler() {
        priceHistoryService.dailyProcess();
    }
}
