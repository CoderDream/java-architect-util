package com.coderdream.freeapps;

import com.coderdream.freeapps.model.AppEntity;
import com.coderdream.freeapps.model.AppIcon;
import com.coderdream.freeapps.service.AppService;
import com.coderdream.freeapps.service.CrawlerHistoryService;
import com.coderdream.freeapps.service.FreeHistoryService;
import com.coderdream.freeapps.service.PriceHistoryService;
import com.coderdream.freeapps.service.AppIconService;
import com.coderdream.freeapps.util.CdFileUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;

@SpringBootTest
@Slf4j
public class AppIconTest {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(AppIconTest.class);
    @Autowired
    private AppService appService;

    @Autowired
    private FreeHistoryService freeHistoryService;

    @Resource
    private CrawlerHistoryService crawlerHistoryService;

    @Resource
    private PriceHistoryService priceHistoryService;
    @Resource
    private AppIconService appIconService;

    @Test
    public void testInsertOrUpdateBatch() {
        List<AppIcon> appIconList = new ArrayList<>();
        AppIcon appIcon;
        List<AppEntity> appList = appService.selectTodoList(new AppEntity());

        String filename = "";
        String appIconUrl;
        int indexPrefixPng;
        int indexPrefixJpg;
        if (!CollectionUtils.isEmpty(appList)) {
            for (AppEntity app : appList) {
                log.error(app.getAppId());
                appIcon = new AppIcon();
                BeanUtils.copyProperties(app, appIcon);
                appIconUrl = app.getAppIconUrl();
                // 找到第一个.png或者jpg
                indexPrefixPng = appIconUrl.indexOf(".png");
                indexPrefixJpg = appIconUrl.indexOf(".jpg");
//                                filename = jsonStr.substring(jsonStr.lastIndexOf("/") + 1);
                if (indexPrefixPng != -1) {
                    filename = CdFileUtils.parseFilename(indexPrefixPng, appIconUrl);
                    System.out.println(filename);
                }
                if (indexPrefixJpg != -1) {
                    filename = CdFileUtils.parseFilename(indexPrefixJpg, appIconUrl);
                    System.out.println(filename);
                }
                appIcon.setFilename(filename);
                appIcon.setDownloadFlag(0);
                appIcon.setCreatedDate(new Date());

                appIconList.add(appIcon);
            }
        }

        appIconService.insertOrUpdateBatch(appIconList);
    }

    @Test
    public void testDailyProcess() {

        List<AppEntity> appList = appService.selectNoAppIconUrl();
        if (!CollectionUtils.isEmpty(appList)) {
            for (AppEntity app : appList) {
                log.error(app.getAppId());
            }
        }

        //       appIconService.dailyProcess();
    }
}
