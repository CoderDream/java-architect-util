package com.coderdream.freeapps.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coderdream.freeapps.controller.SyncTaskReqDto;
import com.coderdream.freeapps.mapper.SyncTaskMapper;
import com.coderdream.freeapps.model.App;
import com.coderdream.freeapps.model.Description;
import com.coderdream.freeapps.model.FreeHistory;
import com.coderdream.freeapps.model.SyncTaskEntity;
import com.coderdream.freeapps.service.AppService;
import com.coderdream.freeapps.service.DescriptionService;
import com.coderdream.freeapps.service.FreeHistoryService;
import com.coderdream.freeapps.service.SyncTaskService;
import com.coderdream.freeapps.util.CdDateUtils;
import com.coderdream.freeapps.util.CdFileUtils;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.core.io.ClassPathResource;
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
    public void dailyProcess(SyncTaskReqDto syncTaskReqDto) {
        QueryWrapper<SyncTaskEntity> queryWrapper = new QueryWrapper<>();
        List<SyncTaskEntity> syncTaskEntityList = list(queryWrapper);
        for (SyncTaskEntity syncTaskEntity : syncTaskEntityList) {
            log.info(syncTaskEntity.toString());
            Date lastProcessDate = syncTaskEntity.getLastProcessDate();
            String lastProcessDateStr = "";
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            DateFormat dateFormatYear = new SimpleDateFormat("yyyy");
            DateFormat dateFormatMonth = new SimpleDateFormat("yyyyMM");
            try {
                String folderPath =
                    CdFileUtils.getResourceRealPath() + File.separatorChar + "data" + File.separatorChar + "1024";
                folderPath = folderPath + File.separatorChar + dateFormatYear.format(new Date());
                folderPath = folderPath + File.separatorChar + dateFormatMonth.format(new Date());
                List<String> stringList = new ArrayList<>();
                List<Date> dateList = CdDateUtils.getDatesBetweenUsingJava7(lastProcessDate, new Date());
                for (Date processDate : dateList) {
                    //                    构造日期，生成文件名列表
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
                    List<App> appList;
                    List<Description> descriptionList;
                    App app;
                    Description description;
                    if (!CollectionUtils.isEmpty(freeHistoryList)) {
                        appList = new ArrayList<>();
                        descriptionList = new ArrayList<>();
                        for (FreeHistory freeHistory : freeHistoryList) {
                            app = new App();
                            BeanUtils.copyProperties(freeHistory, app);
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
                            descriptionService.insertOrUpdateBatch(descriptionList);
                        }
                    }
                }

                syncTaskEntity.setLastProcessDate(dateFormat.parse(lastProcessDateStr));
                boolean updateById = this.updateById(syncTaskEntity);
                log.info("##### updateById result: " + updateById);
                log.info("##### updateById: " + syncTaskEntity);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

        }
    }
}




