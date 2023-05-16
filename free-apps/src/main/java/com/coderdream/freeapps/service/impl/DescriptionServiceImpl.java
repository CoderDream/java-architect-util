package com.coderdream.freeapps.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coderdream.freeapps.mapper.AppMapper;
import com.coderdream.freeapps.mapper.DescriptionMapper;
import com.coderdream.freeapps.model.AppEntity;
import com.coderdream.freeapps.model.Description;
import com.coderdream.freeapps.model.FreeHistory;
import com.coderdream.freeapps.service.AppService;
import com.coderdream.freeapps.service.DescriptionService;
import com.coderdream.freeapps.service.FreeHistoryService;
import com.coderdream.freeapps.util.CdDateTimeUtils;
import com.coderdream.freeapps.util.CdDateUtils;
import com.coderdream.freeapps.util.CdFileUtils;
import com.coderdream.freeapps.util.CdListUtils;
import com.coderdream.freeapps.util.CdConstants;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 * @author CoderDream
 * @description 针对表【t_description】的数据库操作Service实现
 * @createDate 2023-03-11 10:37:56
 */
@Service
@Slf4j
public class DescriptionServiceImpl extends ServiceImpl<DescriptionMapper, Description> implements DescriptionService {

    @Resource
    private DescriptionMapper descriptionMapper;
    @Resource
    private AppMapper appMapper;

    @Resource
    private AppService appService;

    @Resource
    private FreeHistoryService freeHistoryService;

    @Override
    public int insertOrUpdateBatch(List<Description> descriptionList) {
        int count = 0;
        if (!CollectionUtils.isEmpty(descriptionList)) {
            log.info("本次批量执行的记录条数: " + descriptionList.size());
            // 分批处理
            List<List<Description>> lists = CdListUtils.splitTo(descriptionList, CdConstants.BATCH_INSERT_UPDATE_ROWS);
            for (List<Description> list : lists) {
                count += descriptionMapper.insertOrUpdateBatch(list);
            }
        }

        return count;
    }

    @Override
    public int insertOrUpdateBatchMy(List<Description> descriptionList) {
        //    QueryWrapper<App> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("del_flag", 0);
//    List<App> appList = appMapper.selectList(queryWrapper);
//    List<Description> descriptionList = new ArrayList<>();
//        if (!CollectionUtils.isEmpty(appList)) {
//        for (App app : appList) {
//            UpdateWrapper updateWrapper = new UpdateWrapper();
//            updateWrapper.eq("app_id", app.getAppId());
//            updateWrapper.set("status",  1);
//            updateWrapper.set("nickname",  "张三");
//            descriptionMapper.update(null, updateWrapper);
//        }
//    }
        return 0;
    }

    @Override
    public int dailyProcess() {
        QueryWrapper<AppEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("del_flag", 0);
        List<AppEntity> appList = appMapper.selectList(queryWrapper);
        List<Description> descriptionList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(appList)) {
            Description description;
            for (AppEntity app : appList) {
                description = new Description();
                BeanUtils.copyProperties(app, description);
                descriptionList.add(description);
            }
        }

        int count = 0;
        if (!CollectionUtils.isEmpty(descriptionList)) {
            log.info("本次批量执行的记录条数: " + descriptionList.size());
            // 分批处理
            List<List<Description>> lists = CdListUtils.splitTo(descriptionList, CdConstants.BATCH_INSERT_UPDATE_ROWS);
            for (List<Description> list : lists) {
                count += descriptionMapper.insertOrUpdateBatchBaseDescription(list);
            }
        }

        return count;
    }

    @Override
    public int processHistoryData() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        long startTime = System.currentTimeMillis();
        int recordAmount = 0;
//          String lastProcessDateStr = "2022-06-28";
//            Date lastProcessDate = "2022-06-28";
        try {
            String lastProcessDateStr = "2022-06-28";
            Date lastProcessDate = dateFormat.parse(lastProcessDateStr);
            DateFormat dateFormatYear = new SimpleDateFormat("yyyy");
            DateFormat dateFormatMonth = new SimpleDateFormat("yyyyMM");
            DateFormat dateFormatDay = new SimpleDateFormat("yyyyMMdd");

            String folderPath;
//                folderPath = folderPath + File.separatorChar + dateFormatDay.format(new Date());
            List<String> stringList = new ArrayList<>();
            List<Date> dateList = CdDateUtils.getDatesBetweenUsingJava7(lastProcessDate, new Date());
            for (Date processDate : dateList) {
                folderPath =
                    CdFileUtils.getResourceRealPath() + File.separatorChar + "data" + File.separatorChar + "1024";
                folderPath = folderPath + File.separatorChar + dateFormatYear.format(processDate);
                folderPath = folderPath + File.separatorChar + dateFormatMonth.format(processDate);
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

                    recordAmount += freeHistoryList.size();
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
                        app.setDelFlag(0);
                        app.setCreatedDate(new Date());
                        appList.add(app);

                        description = new Description();
                        BeanUtils.copyProperties(freeHistory, description);
                        description.setDescriptionCl(freeHistory.getDescription());
                        description.setDelFlag(0);
                        description.setCreatedDate(new Date());
                        descriptionList.add(description);
                    }
                    if (!CollectionUtils.isEmpty(appList)) {
                        appService.insertOrUpdateBatch(appList);
                    }
                    if (!CollectionUtils.isEmpty(descriptionList)) {
                        insertOrUpdateBatch(descriptionList);
                    }
                }
            }

            if (StrUtil.isEmpty(lastProcessDateStr)) {
                log.error("lastProcessDateStr为空，本次不用同步。");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        long endTime = System.currentTimeMillis();
        long period = endTime - startTime;

        log.error("本次记录条数" + recordAmount + ", 耗时" + CdDateTimeUtils.genMessage(period) + "。");

        return 0;
    }

}




