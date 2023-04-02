package com.coderdream.freeapps.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coderdream.freeapps.mapper.PriceHistoryMapper;
import com.coderdream.freeapps.model.App;
import com.coderdream.freeapps.model.PriceHistory;
import com.coderdream.freeapps.service.AppService;
import com.coderdream.freeapps.service.PriceHistoryService;
import com.coderdream.freeapps.util.Constants;
import com.coderdream.freeapps.util.JSoupUtil;
import com.coderdream.freeapps.util.CdListUtils;
import org.slf4j.Logger;
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
public class PriceHistoryServiceImpl extends ServiceImpl<PriceHistoryMapper, PriceHistory>
    implements PriceHistoryService{

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(PriceHistoryServiceImpl.class);

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
        if (priceHistory.getCrawlerDate()!=null) {
            queryWrapper.eq("crawler_date", priceHistory.getCrawlerDate());
        }
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
//        queryWrapper.isNull("del_flag");
//        queryWrapper.ne("del_flag", 1);
        List<PriceHistory> priceHistoryList = priceHistoryMapper.selectList(queryWrapper);
        return priceHistoryList;
    }



    @Override
    public IPage<PriceHistory> selectPage(Page<PriceHistory> page) {
        QueryWrapper<PriceHistory> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("del_flag", 0);
        return priceHistoryMapper.selectPage(page, queryWrapper);
    }

    @Override
    public void dailyProcess() {
        long startTime = System.currentTimeMillis();
        List<App> newList;
        List<PriceHistory> priceHistoryList;
        PriceHistory priceHistory;
        Set<String> doneAppIdSet = new LinkedHashSet<>();
        List<PriceHistory> priceHistoryDoneList = selectDoneList(null);
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
            List<List<App>> lists = CdListUtils.splitTo(selectTodoList, Constants.BATCH_INSERT_UPDATE_ROWS);
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
                        Integer period = new Random().nextInt(200) + 100;
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
                        int insertOrUpdateBatchResult = insertOrUpdateBatch(priceHistoryList);
                        logger.info("priceHistoryService.insertOrUpdateBatch: " + insertOrUpdateBatchResult);
                    }
                    Integer period = new Random().nextInt(4000) + 500;
                    try {
                        Thread.sleep(period);   // 休眠3秒
                    } catch (InterruptedException e) {
                        logger.error(e.getMessage());
                        throw new RuntimeException(e);
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




