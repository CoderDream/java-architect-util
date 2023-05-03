package com.coderdream.freeapps.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coderdream.freeapps.mapper.AppIconMapper;
import com.coderdream.freeapps.model.App;
import com.coderdream.freeapps.model.AppIcon;
import com.coderdream.freeapps.service.AppService;
import com.coderdream.freeapps.service.AppIconService;
import com.coderdream.freeapps.util.CdListUtils;
import com.coderdream.freeapps.util.Constants;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 * @author CoderDream
 * @description 针对表【t_appIcon】的数据库操作Service实现
 * @createDate 2023-03-11 10:37:56
 */
@Service
public class AppIconServiceImpl extends ServiceImpl<AppIconMapper, AppIcon>
        implements AppIconService {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(AppIconServiceImpl.class);

    @Resource
    private AppService appService;

    @Resource
    private AppIconMapper appIconMapper;

    @Override
    public int insertOrUpdateBatch(List<AppIcon> appIconList) {
        int result = 0;
        if (!CollectionUtils.isEmpty(appIconList)) {
            logger.info("本批次处理的记录数: " + appIconList.size());
            // 分批处理
            List<List<AppIcon>> lists = CdListUtils.splitTo(appIconList, Constants.BATCH_UPDATE_ROWS);
            for (List<AppIcon> list : lists) {
                if (!CollectionUtils.isEmpty(list)) {
                    result += appIconMapper.insertOrUpdateBatch(appIconList);
                }
            }
        }

        return result;
    }

    @Override
    public List<AppIcon>
    selectList(AppIcon appIcon) {
        QueryWrapper<AppIcon> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("download_flag", appIcon.getDownloadFlag());
        List<AppIcon> appIconList = appIconMapper.selectList(queryWrapper);
        return appIconList;
    }


    @Override
    public List<AppIcon> selectTodoList(AppIcon appIcon) {
        QueryWrapper<AppIcon> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("download_flag", 0);
        List<AppIcon> appIconList = appIconMapper.selectList(queryWrapper);
        return appIconList;
    }

    @Override
    public List<AppIcon> selectDoneList(AppIcon appIcon) {
        QueryWrapper<AppIcon> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("download_flag", 1);
        List<AppIcon> appIconList = appIconMapper.selectList(queryWrapper);
        return appIconList;
    }

    @Override
    public void dailyProcess() {
        long startTime = System.currentTimeMillis();
        List<App> newList;
        List<AppIcon> appIconList;
        AppIcon appIcon;
        Set<String> doneAppIdSet = new LinkedHashSet<>();
        List<AppIcon> appIconDoneList = selectDoneList(null);
        if (!CollectionUtils.isEmpty(appIconDoneList)) {
            for (AppIcon appIconTemp : appIconDoneList) {
                doneAppIdSet.add(appIconTemp.getAppId());
            }
        }
        logger.info("本次任务开始前已完成数: " + doneAppIdSet.size());

        List<App> selectTodoList = appService.selectTodoList(null);
        if (!CollectionUtils.isEmpty(selectTodoList)) {
            logger.info("本次任务开始前有效的应用数: " + selectTodoList.size());

            appIconList = new ArrayList<>();
            for (App appNew : selectTodoList) {
                if (doneAppIdSet.contains(appNew.getAppId())) {
                    continue; // 如果已有记录，则跳过
                }

//                App appNew = JSoupUtil.crawlerApp(app);
                logger.info("appNew: " + appNew);

                // 参考：https://blog.csdn.net/CatEatApple/article/details/83926237
                Object appIconUrlObject = appNew.getAppIconUrl();
//                JSONObject jsonObjectAppIconUrl = null;
//                if(appIconUrl instanceof  JSONObject) {
//                    jsonObjectAppIconUrl =   (JSONObject)appNew.getAppIconUrl();
//                }

                String filename = "";
                String appIconUrl;
                if (appIconUrlObject != null) {
                    Map maps = (Map) JSON.parse(appIconUrlObject.toString());
                    int indexPrefixPng;
                    int indexPrefixJpg;

                    System.out.println("这个是用JSON类来解析JSON字符串!!!");
                    for (Object map : maps.entrySet()) {
                        System.out.println(((Map.Entry) map).getKey() + "     " + ((Map.Entry) map).getValue());
                        String tempAppId = (String) ((Map.Entry) map).getKey();
                        Object tempValue = ((Map.Entry) map).getValue();
                        if (tempValue instanceof JSONArray) {
                            System.out.println("list");
                            JSONArray jsonArray = (JSONArray) tempValue;
                            if (jsonArray.size() > 0) {
                                for (Object json : jsonArray) {
                                    appIcon = new AppIcon();
                                    appIcon.setAppId(tempAppId);
                                    String jsonStr = json.toString();
                                    // 找到第一个.png或者jpg
                                    indexPrefixPng = jsonStr.indexOf(".png");
                                    indexPrefixJpg = jsonStr.indexOf(".jpg");
//                                filename = jsonStr.substring(jsonStr.lastIndexOf("/") + 1);
                                    if (indexPrefixPng != -1) {
                                        filename = parseFilename(indexPrefixPng, jsonStr);
                                    }
                                    if (indexPrefixJpg != -1) {
                                        filename = parseFilename(indexPrefixJpg, jsonStr);
                                        System.out.println(filename);
                                    }
                                    appIcon.setFilename(filename);
                                    appIconUrl = jsonStr;
                                    appIcon.setAppIconUrl(appIconUrl);
                                    appIcon.setDownloadFlag(0);
                                    appIcon.setCreatedDate(new Date());
                                    appIconList.add(appIcon);
                                }
                            }
                        }
                    }
                }
            }

            if (!CollectionUtils.isEmpty(appIconList)) {
                int insertOrUpdateBatchResult = insertOrUpdateBatch(appIconList);
                logger.info("appIconService.insertOrUpdateBatch: " + insertOrUpdateBatchResult);
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

    @NotNull
    private static String parseFilename(int indexPrefixJpg, String jsonStr) {
        int endIndex = indexPrefixJpg + 4;
        String tempStr = jsonStr.substring(0, indexPrefixJpg + 4);
        String tempStr2 = tempStr.substring(0, tempStr.lastIndexOf("/"));
        int beginIndex = tempStr2.lastIndexOf("/");
        String tempStr3 = tempStr.substring(beginIndex + 1, endIndex);
        tempStr3 = tempStr3.replaceAll("/", "_");
        return tempStr3;
    }
}




