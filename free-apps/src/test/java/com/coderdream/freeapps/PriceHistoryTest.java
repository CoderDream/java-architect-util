package com.coderdream.freeapps;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.coderdream.freeapps.dto.AppQueryPageDTO;
import com.coderdream.freeapps.handler.DailyPriceHandler;
import com.coderdream.freeapps.model.App;
import com.coderdream.freeapps.model.CrawlerHistory;
import com.coderdream.freeapps.model.FreeHistory;
import com.coderdream.freeapps.model.PriceHistory;
import com.coderdream.freeapps.service.AppService;
import com.coderdream.freeapps.service.CrawlerHistoryService;
import com.coderdream.freeapps.service.FreeHistoryService;
import com.coderdream.freeapps.service.PriceHistoryService;
import com.coderdream.freeapps.util.Constants;
import com.coderdream.freeapps.util.JSoupUtil;
import com.coderdream.freeapps.util.ListUtils;
import com.coderdream.freeapps.vo.AppVO;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

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
        long startTime = System.currentTimeMillis();
        List<App> newList;
        List<PriceHistory> priceHistoryList;
        PriceHistory priceHistory;
        Set<String> doneAppIdSet = new LinkedHashSet<>();
        List<PriceHistory> priceHistoryDoneList = priceHistoryService.selectDoneList(null);
        if (!CollectionUtils.isEmpty(priceHistoryDoneList)) {
            for (PriceHistory priceHistoryTemp : priceHistoryDoneList) {
                doneAppIdSet.add(priceHistoryTemp.getAppId());
            }
        }
        logger.info("本次任务开始前已完成数: " + doneAppIdSet.size());
        List<App> selectTodoList = appService.selectTodoList(null);

        if (!CollectionUtils.isEmpty(selectTodoList)) {
            logger.info("本次任务开始前有效的应用数: " + selectTodoList.size());
            // 分批处理
            List<List<App>> lists = ListUtils.splitTo(selectTodoList, Constants.BATCH_INSERT_UPDATE_ROWS);
            for (List<App> list : lists) {
                if (!CollectionUtils.isEmpty(list)) {
                    newList = new ArrayList<>();
                    priceHistoryList = new ArrayList<>();
                    for (App app : list) {
                        if (doneAppIdSet.contains(app.getAppId())) {
                            continue; // 如果已有记录，则跳过
                        }

                        App appNew = JSoupUtil.crawlerApp(app);
                        newList.add(appNew);
                        logger.info("appNew: " + appNew);
                        // 如果已下架，就不再插入价格历史表
                        if (appNew.getDelFlag() == null || appNew.getDelFlag() == 0) {
                            priceHistory = new PriceHistory();
                            BeanUtils.copyProperties(appNew, priceHistory);
                            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                            String dateStr = dateFormat.format(new Date());
                            try {
                                priceHistory.setCrawlerDate(dateFormat.parse(dateStr));
                                priceHistoryList.add(priceHistory);
                            } catch (ParseException e) {
                                logger.error(e.getMessage());
                                throw new RuntimeException(e);
                            }
                            System.out.println(app.getAppId());
                        }
                        Integer period = new Random().nextInt(3000) + 1000;
                        try {
                            Thread.sleep(period);   // 休眠3秒
                        } catch (InterruptedException e) {
                            logger.error(e.getMessage());
                            throw new RuntimeException(e);
                        }
                    }

                    if (!CollectionUtils.isEmpty(newList)) {
                        int insertOrUpdateBatchResult = appService.insertOrUpdateBatch(newList);
                        logger.info("appService.insertOrUpdateBatch: " + insertOrUpdateBatchResult);
                    }
                    if (!CollectionUtils.isEmpty(priceHistoryList)) {
                        int insertOrUpdateBatchResult = priceHistoryService.insertOrUpdateBatch(priceHistoryList);
                        logger.info("priceHistoryService.insertOrUpdateBatch: " + insertOrUpdateBatchResult);
                    }
                }
            }
        }
        long endTime = System.currentTimeMillis();
        long period = endTime - startTime;

        final long milliseconds = period;
        final long day = TimeUnit.MILLISECONDS.toDays(milliseconds);

        final long hours = TimeUnit.MILLISECONDS.toHours(milliseconds)
                - TimeUnit.DAYS.toHours(TimeUnit.MILLISECONDS.toDays(milliseconds));

        final long minutes = TimeUnit.MILLISECONDS.toMinutes(milliseconds)
                - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(milliseconds));

        final long seconds = TimeUnit.MILLISECONDS.toSeconds(milliseconds)
                - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(milliseconds));

        final long ms = TimeUnit.MILLISECONDS.toMillis(milliseconds)
                - TimeUnit.SECONDS.toMillis(TimeUnit.MILLISECONDS.toSeconds(milliseconds));

//        System.out.println("milliseconds :-" + milliseconds);
        String message = String.format("%d Days %d Hours %d Minutes %d Seconds %d Milliseconds",
                day, hours, minutes, seconds, ms);
        logger.info("本次任务耗时: " + period + " 毫秒");
        logger.info("本次任务耗时: " + message);
    }
}
