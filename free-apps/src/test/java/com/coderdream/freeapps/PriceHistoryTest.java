package com.coderdream.freeapps;

import com.coderdream.freeapps.model.PriceHistory;
import com.coderdream.freeapps.service.AppService;
import com.coderdream.freeapps.service.CrawlerHistoryService;
import com.coderdream.freeapps.service.FreeHistoryService;
import com.coderdream.freeapps.service.PriceHistoryService;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import org.springframework.util.CollectionUtils;

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
    public void testSelectList() {
        List<PriceHistory> priceHistoryList = priceHistoryService.selectList(new PriceHistory());
        String appId;
        String urlCn;
        String dateStr;
        String tempAppId;
        Set<String> dateSet = new LinkedHashSet<>();

        Map<String, Integer> dateMap = new LinkedHashMap<>();
//        Map<String, List<String>> appIdMap = new LinkedHashMap<>();
        Integer count;
        if (!CollectionUtils.isEmpty(priceHistoryList)) {
            for (PriceHistory priceHistory : priceHistoryList) {
                appId = priceHistory.getAppId();
                urlCn = priceHistory.getUrlCn();
                tempAppId = urlCn.substring(urlCn.lastIndexOf("/") + 1, urlCn.lastIndexOf("?"));
                if (!appId.equals(tempAppId)) {
                    dateStr = new SimpleDateFormat("yyyy-MM-dd").format(priceHistory.getCrawlerDate());
//                    logger.error("\t" + priceHistory.getId() + "\t" + priceHistory.getAppId() + "\t" + dateStr);
                    if (dateStr.equals("2023-03-05")) {
                        logger.error("\t" + priceHistory.getId());
                    }
                    dateSet.add(dateStr);
                    count = dateMap.get(dateStr);
                    if (count == null) {
                        count = 1;
                    } else {
                        count += 1;
                    }
                    dateMap.put(dateStr, count);
                }
            }
        }
        for (String str : dateSet) {
            logger.error(str);
        }
        for (Map.Entry<String, Integer> entry : dateMap.entrySet()) {
            String mapKey = entry.getKey();
            Integer mapValue = entry.getValue();
            System.out.println(mapKey + "：" + mapValue);
        }
    }

    @Test
    public void testDailyPriceHandler() {
        priceHistoryService.dailyProcess();
    }

    @Test
    public void testDailyPriceHandlerV2() {
        priceHistoryService.dailyProcessV2();
    }

    @Test
    public void testDailyProcessSimple() {
        priceHistoryService.dailyProcessSimple();
    }

    @Test
    public void testProcessPriceNone() {
        priceHistoryService.processPriceNone();
    }


    @Test
    public void testUpdateTodayFreeAppPrice() {
        priceHistoryService.updateTodayFreeAppPrice();
    }

    @Test
    public void testProcessPriceFault() {
        priceHistoryService.processPriceFault();
    }

    @Test
    public void testProcessPriceTopList() {
        priceHistoryService.processPriceTopList();
    }

}
