package com.coderdream.freeapps.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coderdream.freeapps.mapper.PriceHistoryMapper;
import com.coderdream.freeapps.model.AppEntity;
import com.coderdream.freeapps.model.PriceHistory;
import com.coderdream.freeapps.service.AppService;
import com.coderdream.freeapps.service.PriceHistoryService;
import com.coderdream.freeapps.util.other.CdConstants;
import com.coderdream.freeapps.util.other.JSoupUtil;
import com.coderdream.freeapps.util.other.CdListUtils;
import com.coderdream.freeapps.util.multithread.Demo02;
import com.coderdream.freeapps.util.other.CdExcelUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author CoderDream
 * @description 针对表【t_price_history】的数据库操作Service实现
 * @createDate 2023-03-03 08:38:02
 */
@Service
@Slf4j
public class PriceHistoryServiceImpl extends ServiceImpl<PriceHistoryMapper, PriceHistory>
    implements PriceHistoryService {

    @Resource
    private PriceHistoryMapper priceHistoryMapper;

    @Resource
    private AppService appService;

    @Override
    public int insertSelective(PriceHistory priceHistory) {
        return priceHistoryMapper.insertSelective(priceHistory);
    }

    @Override
    public int insertOrUpdateBatch(List<PriceHistory> priceHistoryList) {
        return priceHistoryMapper.insertOrUpdateBatch(priceHistoryList);
    }

    @Override
    public List<PriceHistory> selectList(PriceHistory priceHistory) {
        QueryWrapper<PriceHistory> queryWrapper = new QueryWrapper<>();
        if (StrUtil.isNotEmpty(priceHistory.getAppId())) {
            queryWrapper.eq("app_id", priceHistory.getAppId());
        }
        if (priceHistory.getCrawlerDate() != null) {
            queryWrapper.eq("crawler_date", priceHistory.getCrawlerDate());
        }
        queryWrapper.orderByAsc("app_id");
        queryWrapper.orderByDesc("crawler_date");
        List<PriceHistory> result = priceHistoryMapper.selectList(queryWrapper);
        return result;
    }

    @Override
    public List<PriceHistory> selectDoneList(PriceHistory priceHistory) {
        QueryWrapper<PriceHistory> queryWrapper = new QueryWrapper<>();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = dateFormat.format(new Date());
        try {
            queryWrapper.eq("crawler_date", dateFormat.parse(dateStr));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        queryWrapper.isNotNull("price_str");
//        queryWrapper.isNull("delete_flag");
//        queryWrapper.ne("delete_flag", 1);
        List<PriceHistory> priceHistoryList = priceHistoryMapper.selectList(queryWrapper);
        return priceHistoryList;
    }


    @Override
    public IPage<PriceHistory> selectPage(Page<PriceHistory> page) {
        QueryWrapper<PriceHistory> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("delete_flag", 0);
        return priceHistoryMapper.selectPage(page, queryWrapper);
    }

    @Override
    public void dailyProcess() {
        long startTime = System.currentTimeMillis();
        List<AppEntity> newList;
        List<PriceHistory> priceHistoryList;
        PriceHistory priceHistory;
        Set<String> doneAppIdSet = new LinkedHashSet<>();
        List<PriceHistory> priceHistoryDoneList = selectDoneList(null);
        if (!CollectionUtils.isEmpty(priceHistoryDoneList)) {
            for (PriceHistory priceHistoryTemp : priceHistoryDoneList) {
                doneAppIdSet.add(priceHistoryTemp.getAppId());
            }
        }
        log.info("本次任务开始前已完成数: " + doneAppIdSet.size());
        List<AppEntity> selectTodoList = appService.selectTodoList(null);

        if (!CollectionUtils.isEmpty(selectTodoList)) {
            log.info("本次任务开始前有效的应用数: " + selectTodoList.size());
            // 分批处理
            List<List<AppEntity>> lists = CdListUtils.splitTo(selectTodoList, CdConstants.BATCH_INSERT_UPDATE_ROWS);
            for (List<AppEntity> list : lists) {
                if (!CollectionUtils.isEmpty(list)) {
                    newList = new ArrayList<>();
                    priceHistoryList = new ArrayList<>();
                    for (AppEntity app : list) {
                        if (doneAppIdSet.contains(app.getAppId())) {
                            continue; // 如果已有记录，则跳过
                        }

                        AppEntity appNew = JSoupUtil.crawlerApp(app);
                        newList.add(appNew);
                        log.info("appNew: " + appNew);
                        // 如果已下架，就不再插入价格历史表
                        if (appNew.getDeleteFlag() == null || appNew.getDeleteFlag() == 0) {
                            priceHistory = new PriceHistory();
                            BeanUtils.copyProperties(appNew, priceHistory);
                            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                            String dateStr = dateFormat.format(new Date());
                            try {
                                priceHistory.setCrawlerDate(dateFormat.parse(dateStr));
                                priceHistoryList.add(priceHistory);
                            } catch (ParseException e) {
                                log.error(e.getMessage());
                                throw new RuntimeException(e);
                            }
                            System.out.println(app.getAppId());
                        }
//                        Integer period = new Random().nextInt(10) + 10;
//                        try {
//                            Thread.sleep(period);   // 休眠3秒
//                        } catch (InterruptedException e) {
//                            log.error(e.getMessage());
//                            throw new RuntimeException(e);
//                        }
                    }

                    if (!CollectionUtils.isEmpty(newList)) {
                        int insertOrUpdateBatchResult = appService.insertOrUpdateBatch(newList);
                        log.info("appService.insertOrUpdateBatch: " + insertOrUpdateBatchResult);
                    }
                    if (!CollectionUtils.isEmpty(priceHistoryList)) {
                        int insertOrUpdateBatchResult = insertOrUpdateBatch(priceHistoryList);
                        log.info("priceHistoryService.insertOrUpdateBatch: " + insertOrUpdateBatchResult);
                    }
//                    Integer period = new Random().nextInt(500) + 100;
//                    try {
//                        Thread.sleep(period);   // 休眠3秒
//                    } catch (InterruptedException e) {
//                        log.error(e.getMessage());
//                        throw new RuntimeException(e);
//                    }
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
        log.info("本次任务耗时: " + period + " 毫秒");
        log.info("本次任务耗时: " + message);
    }

    @Override
    public void dailyProcessSimple() {
        long startTime = System.currentTimeMillis();
        List<AppEntity> newList;
        List<PriceHistory> priceHistoryList;
        PriceHistory priceHistory;
        Set<String> doneAppIdSet = new LinkedHashSet<>();
        List<PriceHistory> priceHistoryDoneList = selectDoneList(null);
        if (!CollectionUtils.isEmpty(priceHistoryDoneList)) {
            for (PriceHistory priceHistoryTemp : priceHistoryDoneList) {
                doneAppIdSet.add(priceHistoryTemp.getAppId());
            }
        }
        log.info("本次任务开始前已完成数: " + doneAppIdSet.size());
        List<AppEntity> selectTodoList = appService.selectTodoList(null);

        if (!CollectionUtils.isEmpty(selectTodoList)) {
            log.info("本次任务开始前有效的应用数: " + selectTodoList.size());
            // 分批处理
            List<List<AppEntity>> lists = CdListUtils.splitTo(selectTodoList, CdConstants.BATCH_SNAPSHOT_ROWS);
            for (List<AppEntity> list : lists) {
                if (!CollectionUtils.isEmpty(list)) {
                    newList = new ArrayList<>();
                    priceHistoryList = new ArrayList<>();
                    for (AppEntity app : list) {
                        if (doneAppIdSet.contains(app.getAppId())) {
                            continue; // 如果已有记录，则跳过
                        }

                        priceHistory = JSoupUtil.crawlerAppPrice(app.getAppId(), app.getUsFlag());

                        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        String dateStr = dateFormat.format(new Date());
                        try {
                            priceHistory.setCrawlerDate(dateFormat.parse(dateStr));
                            priceHistoryList.add(priceHistory);
                            log.info("priceHistory: " + priceHistory);
                        } catch (ParseException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    if (!CollectionUtils.isEmpty(priceHistoryList)) {
                        int insertOrUpdateBatchResult = insertOrUpdateBatch(priceHistoryList);
                        log.info("priceHistoryService.insertOrUpdateBatch: " + insertOrUpdateBatchResult);
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
        log.info("本次任务耗时: " + period + " 毫秒");
        log.info("本次任务耗时: " + message);
    }

    @Override
    public void dailyProcessV2() { // TODO
        long startTime = System.currentTimeMillis();
        List<AppEntity> newList;
        List<PriceHistory> priceHistoryList;
        PriceHistory priceHistory;
        Set<String> doneAppIdSet = new LinkedHashSet<>();
//        List<PriceHistory> priceHistoryDoneList = selectDoneList(null);

        QueryWrapper<PriceHistory> queryWrapper = new QueryWrapper<>();

//        queryWrapper.and(wrapper -> wrapper.like("price_str", "Free").or().like("price_str", "免费"));

        queryWrapper.and(wrapper -> wrapper.eq("price_str", "Free").or().eq("price_str", "免费"));

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = dateFormat.format(new Date());
        try {
            queryWrapper.eq("crawler_date", dateFormat.parse(dateStr));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
//        queryWrapper.isNotNull("price_str");
        queryWrapper.select("app_id", "us_flag");
        queryWrapper.last("limit 18");
//        queryWrapper.isNull("delete_flag");
//        queryWrapper.ne("delete_flag", 1);
        List<PriceHistory> freePriceHistoryList = priceHistoryMapper.selectList(queryWrapper);

        if (!CollectionUtils.isEmpty(freePriceHistoryList)) {
//            for (PriceHistory priceHistoryTemp : freePriceHistoryList) {
//                doneAppIdSet.add(priceHistoryTemp.getAppId());
//            }

            List<PriceHistory> priceHistoryListNew = Demo02.batchCrawlerAppPriceInfo(freePriceHistoryList);

            if (!CollectionUtils.isEmpty(priceHistoryListNew)) {
                int insertOrUpdateBatchResult = insertOrUpdateBatch(priceHistoryListNew);
                log.info("priceHistoryService.insertOrUpdateBatch: " + insertOrUpdateBatchResult);
            }

//            List<PriceHistory> priceHistories = ConcurrentProcessAppUtils.process(freePriceHistoryList);
//            for (PriceHistory priceHistoryTemp : priceHistories) {
////                System.out.println(priceHistoryTemp);
//
//                System.out.println(priceHistoryTemp.getAppId() + "#########" + priceHistoryTemp.getPriceStr());
////                System.out.println(); priceHistoryTemp.getPriceStr()
//            }
        }
//        log.info("本次任务开始前已完成数: " + doneAppIdSet.size());
//        List<AppEntity> selectTodoList = appService.selectTodoList(null);
//
//        if (!CollectionUtils.isEmpty(selectTodoList)) {
//            log.info("本次任务开始前有效的应用数: " + selectTodoList.size());
//            // 分批处理
//            List<List<AppEntity>> lists = CdListUtils.splitTo(selectTodoList, CdConstants.BATCH_SNAPSHOT_ROWS);
//            for (List<AppEntity> list : lists) {
//                if (!CollectionUtils.isEmpty(list)) {
//                    newList = new ArrayList<>();
//                    priceHistoryList = new ArrayList<>();
//                    for (AppEntity app : list) {
//                        if (doneAppIdSet.contains(app.getAppId())) {
//                            continue; // 如果已有记录，则跳过
//                        }
//
//                        // TODO
//                        priceHistory = JSoupUtil.crawlerAppPrice(app.getAppId(), app.getUsFlag());
//
//                        dateStr = dateFormat.format(new Date());
//                        try {
//                            priceHistory.setCrawlerDate(dateFormat.parse(dateStr));
//                            priceHistoryList.add(priceHistory);
//                            log.info("priceHistory: " + priceHistory);
//                        } catch (ParseException e) {
//                            throw new RuntimeException(e);
//                        }
//                    }
//
//                    if (!CollectionUtils.isEmpty(priceHistoryList)) {
//                        int insertOrUpdateBatchResult = insertOrUpdateBatch(priceHistoryList);
//                        log.info("priceHistoryService.insertOrUpdateBatch: " + insertOrUpdateBatchResult);
//                    }
//                }
//            }
//        }
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
        log.info("本次任务耗时: " + period + " 毫秒");
        log.info("本次任务耗时: " + message);
    }

    @Override
    public void processPriceNone() {
        long startTime = System.currentTimeMillis();
        List<AppEntity> newList;
        List<PriceHistory> priceHistoryList;
        PriceHistory priceHistory;
        QueryWrapper<PriceHistory> priceHistoryQueryWrapper = new QueryWrapper<>();
        priceHistoryQueryWrapper.isNull("price");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String crawlerDate = dateFormat.format(new Date());
        priceHistoryQueryWrapper.eq("crawler_date", crawlerDate);
        List<PriceHistory> priceHistoryDoneList = priceHistoryMapper.selectList(priceHistoryQueryWrapper);
        if (!CollectionUtils.isEmpty(priceHistoryDoneList)) {
            log.info("本次任务开始前有效的应用数: " + priceHistoryDoneList.size());
            // 分批处理
            List<List<PriceHistory>> lists = CdListUtils.splitTo(priceHistoryDoneList, CdConstants.BATCH_SNAPSHOT_ROWS);
            for (List<PriceHistory> list : lists) {
                if (!CollectionUtils.isEmpty(list)) {
                    newList = new ArrayList<>();
                    priceHistoryList = new ArrayList<>();
                    for (PriceHistory app : list) {
                        priceHistory = JSoupUtil.crawlerAppPrice(app.getAppId(), app.getUsFlag());
                        if (priceHistory != null) {
                            String dateStr = dateFormat.format(new Date());
                            try {
                                priceHistory.setCrawlerDate(dateFormat.parse(dateStr));
                                priceHistoryList.add(priceHistory);
                                log.info("priceHistory: " + priceHistory);
                            } catch (ParseException e) {
                                throw new RuntimeException(e);
                            }
                        } else {
                            log.error(app.getAppId() + "爬虫失败！");
                        }
                    }

                    if (!CollectionUtils.isEmpty(priceHistoryList)) {
                        int insertOrUpdateBatchResult = insertOrUpdateBatch(priceHistoryList);
                        log.info("priceHistoryService.insertOrUpdateBatch: " + insertOrUpdateBatchResult);
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
        log.info("本次任务耗时: " + period + " 毫秒");
        log.info("本次任务耗时: " + message);
    }

    @Override
    public void processPriceFault() {
        long startTime = System.currentTimeMillis();
        List<AppEntity> newList;
        List<PriceHistory> priceHistoryList;
        PriceHistory priceHistory;
        QueryWrapper<PriceHistory> priceHistoryQueryWrapper = new QueryWrapper<>();
        priceHistoryQueryWrapper.eq("price", 0);
        priceHistoryQueryWrapper.notLike("price_str", "免费");
        priceHistoryQueryWrapper.notLike("price_str", "Free");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String crawlerDate = dateFormat.format(new Date());
        priceHistoryQueryWrapper.eq("crawler_date", crawlerDate);
        List<PriceHistory> priceHistoryDoneList = priceHistoryMapper.selectList(priceHistoryQueryWrapper);
        if (!CollectionUtils.isEmpty(priceHistoryDoneList)) {
            log.info("本次任务开始前有效的应用数: " + priceHistoryDoneList.size());
            // 分批处理
            List<List<PriceHistory>> lists = CdListUtils.splitTo(priceHistoryDoneList, CdConstants.BATCH_SNAPSHOT_ROWS);
            for (List<PriceHistory> list : lists) {
                if (!CollectionUtils.isEmpty(list)) {
                    newList = new ArrayList<>();
                    priceHistoryList = new ArrayList<>();
                    for (PriceHistory history : list) {
                        priceHistory = JSoupUtil.crawlerAppPrice(history.getAppId(), 0);
                        if (priceHistory != null) {
                            String dateStr = dateFormat.format(new Date());
                            try {
                                priceHistory.setCrawlerDate(dateFormat.parse(dateStr));
                                priceHistoryList.add(priceHistory);
                                log.info("priceHistory: " + priceHistory);
                            } catch (ParseException e) {
                                throw new RuntimeException(e);
                            }
                        } else {
                            log.error(history.getAppId() + "爬虫失败！");
                        }
                    }

                    if (!CollectionUtils.isEmpty(priceHistoryList)) {
                        int insertOrUpdateBatchResult = insertOrUpdateBatch(priceHistoryList);
                        log.info("priceHistoryService.insertOrUpdateBatch: " + insertOrUpdateBatchResult);
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
        log.info("本次任务耗时: " + period + " 毫秒");
        log.info("本次任务耗时: " + message);
    }

    @Override
    public void updateTodayFreeAppPrice() {
        long startTime = System.currentTimeMillis();
        List<AppEntity> newList;
        List<PriceHistory> priceHistoryList;
        PriceHistory priceHistory;
        QueryWrapper<PriceHistory> priceHistoryQueryWrapper = new QueryWrapper<>();
        priceHistoryQueryWrapper.eq("price", 0);
//        priceHistoryQueryWrapper.notLike("price_str", "免费");
//        priceHistoryQueryWrapper.notLike("price_str", "Free");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String crawlerDate = dateFormat.format(new Date());
        priceHistoryQueryWrapper.eq("crawler_date", crawlerDate);
        List<PriceHistory> priceHistoryDoneList = priceHistoryMapper.selectList(priceHistoryQueryWrapper);
        if (!CollectionUtils.isEmpty(priceHistoryDoneList)) {
            log.info("本次任务开始前有效的应用数: " + priceHistoryDoneList.size());
            // 分批处理
            List<List<PriceHistory>> lists = CdListUtils.splitTo(priceHistoryDoneList, CdConstants.BATCH_SNAPSHOT_ROWS);
            for (List<PriceHistory> list : lists) {
                if (!CollectionUtils.isEmpty(list)) {
                    newList = new ArrayList<>();
                    priceHistoryList = new ArrayList<>();
                    for (PriceHistory history : list) {
                        priceHistory = JSoupUtil.crawlerAppPrice(history.getAppId(), 0);
                        if (priceHistory != null) {
                            String dateStr = dateFormat.format(new Date());
                            try {
                                priceHistory.setCrawlerDate(dateFormat.parse(dateStr));
                                priceHistoryList.add(priceHistory);
                                log.info("priceHistory: " + priceHistory);
                            } catch (ParseException e) {
                                throw new RuntimeException(e);
                            }
                        } else {
                            log.error(history.getAppId() + "爬虫失败！");
                        }
                    }

                    if (!CollectionUtils.isEmpty(priceHistoryList)) {
                        int insertOrUpdateBatchResult = insertOrUpdateBatch(priceHistoryList);
                        log.info("priceHistoryService.insertOrUpdateBatch: " + insertOrUpdateBatchResult);
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
        log.info("本次任务耗时: " + period + " 毫秒");
        log.info("本次任务耗时: " + message);
    }

    @Override
    public void processPriceTopList() {
        long startTime = System.currentTimeMillis();

        Set<String> appIdSet = CdExcelUtil.genTotalTopAppIdSet();
        List<AppEntity> newList;
        List<PriceHistory> priceHistoryList;
        PriceHistory priceHistory;
        QueryWrapper<PriceHistory> priceHistoryQueryWrapper = new QueryWrapper<>();
        priceHistoryQueryWrapper.eq("price", 0);
        // A and (B or C)
        //.eq("a", "A").and(i -> i.eq("b", "B").or().eq("c", "C"));

        priceHistoryQueryWrapper.and(i -> i.eq("price_str", "Free").or().eq("price_str", "免费"));
//        priceHistoryQueryWrapper.eq("price_str", "Free");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String crawlerDate = dateFormat.format(new Date());
        priceHistoryQueryWrapper.eq("crawler_date", crawlerDate);
        List<PriceHistory> priceTodayFreeList = priceHistoryMapper.selectList(priceHistoryQueryWrapper);
        int i = 0;
        if (!CollectionUtils.isEmpty(priceTodayFreeList)) {
            log.info("本次任务开始前有效的应用数: " + priceTodayFreeList.size());
            for (PriceHistory priceHistoryTemp : priceTodayFreeList) {
                if (appIdSet.contains(priceHistoryTemp.getAppId()) && priceHistoryTemp.getPrice() == 0) {
                    i++;
                    log.info(
                        i + "##\t" + priceHistoryTemp.getAppId() + "\t###\t" + priceHistoryTemp.getTitle() + "\t###\t"
                            + priceHistoryTemp.getPrice() + "\t###\t" + priceHistoryTemp.getPriceStr());
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
        log.info("本次任务耗时: " + period + " 毫秒");
        log.info("本次任务耗时: " + message);
    }
}




