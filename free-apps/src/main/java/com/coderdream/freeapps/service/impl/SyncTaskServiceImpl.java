package com.coderdream.freeapps.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coderdream.freeapps.controller.SyncTaskReqDto;
import com.coderdream.freeapps.dto.RecommendApp;
import com.coderdream.freeapps.mapper.SyncTaskMapper;
import com.coderdream.freeapps.model.AppEntity;
import com.coderdream.freeapps.model.Description;
import com.coderdream.freeapps.model.FreeHistory;
import com.coderdream.freeapps.model.SyncTaskEntity;
import com.coderdream.freeapps.service.AppService;
import com.coderdream.freeapps.service.DescriptionService;
import com.coderdream.freeapps.service.FreeHistoryService;
import com.coderdream.freeapps.service.SyncTaskService;
import com.coderdream.freeapps.util.other.CdDateUtils;
import com.coderdream.freeapps.util.other.CdFileUtils;
import com.coderdream.freeapps.util.other.CdExcelUtil;
import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import javax.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 * @author CoderDream
 * @description 针对表【t_sync_task】的数据库操作Service实现
 * @createDate 2023-04-05 21:23:43
 */
@Service
@Slf4j
public class SyncTaskServiceImpl extends ServiceImpl<SyncTaskMapper, SyncTaskEntity>
    implements SyncTaskService {

    @Resource
    private FreeHistoryService freeHistoryService;

    @Resource
    private AppService appService;
    @Resource
    private DescriptionService descriptionService;

    @Override
    @Transactional
    public void dailyProcess(SyncTaskReqDto syncTaskReqDto) {
        long startTime = System.currentTimeMillis();

        QueryWrapper<SyncTaskEntity> queryWrapper = new QueryWrapper<>();
        List<SyncTaskEntity> syncTaskEntityList = list(queryWrapper);
        for (SyncTaskEntity syncTaskEntity : syncTaskEntityList) {
            log.info(syncTaskEntity.toString());
            Date lastProcessDate = syncTaskEntity.getLastProcessDate();
            String lastProcessDateStr = "";
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            DateFormat dateFormatYear = new SimpleDateFormat("yyyy");
            DateFormat dateFormatMonth = new SimpleDateFormat("yyyyMM");
            DateFormat dateFormatDay = new SimpleDateFormat("yyyyMMdd");
            try {
                String folderPath =
                    CdFileUtils.getResourceRealPath() + File.separatorChar + "data" + File.separatorChar + "1024";
                folderPath = folderPath + File.separatorChar + dateFormatYear.format(new Date());
                folderPath = folderPath + File.separatorChar + dateFormatMonth.format(new Date());
//                folderPath = folderPath + File.separatorChar + dateFormatDay.format(new Date());
                List<String> stringList = new ArrayList<>();
                List<Date> dateList = CdDateUtils.getDatesBetweenUsingJava7(lastProcessDate, new Date());
                for (Date processDate : dateList) {
                    // 构造日期，生成文件名列表
                    stringList.add(folderPath + File.separatorChar + dateFormat.format(processDate) + ".txt");
                }

                for (String fileName : stringList) {
                    System.out.println(fileName);
                    List<FreeHistory> freeHistoryList = CdFileUtils.getFreeHistoryFromCL(fileName);
                    if (CollectionUtils.isEmpty(freeHistoryList)) {
                        log.error("限免列表为空：" + fileName);
                        continue;
                    } else {
                        // 更新最后操作日期字段
                        lastProcessDateStr = fileName.substring(fileName.lastIndexOf(File.separatorChar) + 1,
                            fileName.lastIndexOf("."));
                        log.error("lastProcessDateStr：" + lastProcessDateStr);
                    }
                    int b = freeHistoryService.insertOrUpdateBatch(freeHistoryList);  //boolean 操作是否成功
                    log.info("结果：" + b);
                    List<AppEntity> appList;
                    List<Description> descriptionList;
                    AppEntity app;
                    Description description;
                    if (!CollectionUtils.isEmpty(freeHistoryList)) {
                        appList = new ArrayList<>();
                        descriptionList = new ArrayList<>();
                        for (FreeHistory freeHistory : freeHistoryList) {
                            app = new AppEntity();
                            BeanUtils.copyProperties(freeHistory, app);
                            app.setDescriptionCl(freeHistory.getDescription());
                            app.setDeleteFlag(0);
                            app.setCreatedDate(new Date());
                            appList.add(app);

                            description = new Description();
                            BeanUtils.copyProperties(freeHistory, description);
                            description.setDescriptionCl(freeHistory.getDescription());
                            description.setDeleteFlag(0);
                            description.setCreatedDate(new Date());
                            descriptionList.add(description);
                        }
                        if (!CollectionUtils.isEmpty(appList)) {
                            appService.insertOrUpdateBatch(appList);
                        }
                        if (!CollectionUtils.isEmpty(descriptionList)) {
                            descriptionService.insertOrUpdateBatch(descriptionList);
                        }
                    }
                }

                if(StrUtil.isEmpty(lastProcessDateStr)) {
                    log.error("lastProcessDateStr为空，本次不用同步。");
                    continue;
                }
                syncTaskEntity.setLastProcessDate(dateFormat.parse(lastProcessDateStr));
                boolean updateById = this.updateById(syncTaskEntity);
                log.info("##### updateById result: " + updateById);
                log.info("##### updateById: " + syncTaskEntity);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

        }

        // 处理没有是否美区限免的应用
        appService.processNoUsFlag();
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
    public void dailyRecommend() {
        List<RecommendApp> recommendAppList = CdExcelUtil.genRecommendAppList();
        if (com.baomidou.mybatisplus.core.toolkit.CollectionUtils.isNotEmpty(recommendAppList)) {
            for (RecommendApp recommendApp : recommendAppList) {
                System.out.println(recommendApp.getTitle());
                System.out.println(recommendApp.getYesterdayPrice()+"➱0");
                if(recommendApp.getUsFlag().equals("1")) {
                    System.out.println("美区限免。"+recommendApp.getDescriptionUs());
                    System.out.println("https://apps.apple.com/us/app/"+recommendApp.getAppId());
                } else {
                    System.out.println(recommendApp.getDescriptionCn());
                    System.out.println("https://apps.apple.com/cn/app/"+recommendApp.getAppId());
                }
                System.out.println();
            }
        }

    }
}




