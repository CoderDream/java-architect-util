package com.coderdream.freeapps;

import com.coderdream.freeapps.service.*;
import com.coderdream.freeapps.util.CdDateUtils;
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
    private TopPriceService topPriceService;

    @Autowired
    private FreeHistoryService freeHistoryService;

    @Resource
    private CrawlerHistoryService crawlerHistoryService;

    @Resource
    private PriceHistoryService priceHistoryService;

    @Test
    public void testDailyPriceHandler() {
        topPriceService.process("2023-03-03");
    }


    @Test
    public void testDailyPriceHandlerTotal() {

        List<String> list = Arrays.asList(
                "2023-04-09"
//            ,
//                "2023-03-06"
//                ,
//            "2023-03-07",
//            "2023-03-08",
//            "2023-03-09",
//            "2023-03-10",
//            "2023-03-11",
//            "2023-03-12",
//            "2023-03-13"
        );

        list = CdDateUtils.getDatesBetween("2023-04-11", "2023-04-23");
        if (!CollectionUtils.isEmpty(list)) {
            for (String dateStr : list) {
                topPriceService.process(dateStr);
            }
        }

//        String dateStr = "";
//        topPriceService.process(dateStr);

    }
}
