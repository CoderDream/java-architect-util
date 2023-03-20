package com.coderdream.freeapps;

import com.coderdream.freeapps.model.App;
import com.coderdream.freeapps.service.*;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class TopPriceTest {
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(TopPriceTest.class);
    @Autowired
    private TopPriceService topPriceService; //这里可能爆红，但是运行没问题

    @Autowired
    private FreeHistoryService freeHistoryService; //这里可能爆红，但是运行没问题

    @Resource
    private CrawlerHistoryService crawlerHistoryService; //这里可能爆红，但是运行没问题

    @Resource
    private PriceHistoryService priceHistoryService; //这里可能爆红，但是运行没问题

    @Test
    public void testDailyPriceHandler() {
        topPriceService.process("2023-03-03");
    }


    @Test
    public void testDailyPriceHandlerTotal() {

        List<String> list = Arrays.asList(
                "2023-03-04",
                "2023-03-05",
                "2023-03-06",
                "2023-03-07",
                "2023-03-08",
                "2023-03-09",
                "2023-03-10",
                "2023-03-11",
                "2023-03-12",
                "2023-03-13"
        );
        List<App> newList;
        App appNew;
        if (!CollectionUtils.isEmpty(list)) {
            for (String dateStr : list) {

                topPriceService.process(dateStr);
            }
        }

    }
}
