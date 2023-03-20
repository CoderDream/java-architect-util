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
    private AppService appService; //这里可能爆红，但是运行没问题

    @Autowired
    private FreeHistoryService freeHistoryService; //这里可能爆红，但是运行没问题

    @Resource
    private CrawlerHistoryService crawlerHistoryService; //这里可能爆红，但是运行没问题

    @Resource
    private PriceHistoryService priceHistoryService; //这里可能爆红，但是运行没问题

    @Test
    public void testDailyPriceHandler() {
        priceHistoryService.dailyProcess();
    }
}
