package com.coderdream.freeapps;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.coderdream.freeapps.dto.AppQueryPageDTO;
import com.coderdream.freeapps.dto.TopList;
import com.coderdream.freeapps.model.AppEntity;
import com.coderdream.freeapps.model.CrawlerHistory;
import com.coderdream.freeapps.model.FreeHistory;
import com.coderdream.freeapps.model.PriceHistory;
import com.coderdream.freeapps.service.*;
import com.coderdream.freeapps.util.CdConstants;
import com.coderdream.freeapps.util.JSoupUtil;
import com.coderdream.freeapps.util.CdListUtils;
import com.coderdream.freeapps.util.ppt.excelutil.CdExcelUtils;
import com.coderdream.freeapps.vo.AppVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.junit.jupiter.api.Test;

import javax.annotation.Resource;

@SpringBootTest
@Slf4j
public class AppServiceTest {

    @Autowired
    private AppService appService;

    @Autowired
    private FreeHistoryService freeHistoryService;

    @Resource
    private CrawlerHistoryService crawlerHistoryService;

    @Resource
    private PriceHistoryService priceHistoryService;

    //    @Test
//    public void testGetCount() {
//        //查询总记录数
//        //SELECT COUNT( * ) FROM user
//        long count = userService.count();
//        System.out.println("总记录数：" + count);
//    }
//
    @Test
    public void testInsert() {
        //批量添加
        //INSERT INTO user ( id,title, age, email ) VALUES ( ?, ?, ?, ? )
        List<AppEntity> list = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            AppEntity app = new AppEntity();
            app.setAppId("user" + i);

            int b = appService.insertSelective(app);  //boolean 操作是否成功
            System.out.println("结果：" + b);
        }
    }

    @Test
    public void testInsertOrUpdateBatch() {
        //批量添加
        //INSERT INTO user ( id,title, age, email ) VALUES ( ?, ?, ?, ? )
        List<AppEntity> list = new ArrayList<>();
        for (int i = 1; i <= 200; i++) {
            AppEntity app = new AppEntity();
//            app.setAppId("id" +  String.format("%09d",  new Random().nextInt(999999999)));
            app.setAppId("id" + String.format("%09d", new Random().nextInt(99)));
            app.setTitle("title" + new Random().nextInt(100));
            list.add(app);
        }
        int b = appService.insertOrUpdateBatch(list);  //boolean 操作是否成功
        System.out.println("结果：" + b);
    }

    @Test
    public void testInsertOrUpdateBatch_total() {
        List<AppEntity> list = new ArrayList<>();
        AppEntity app;

        FreeHistory freeHistoryReqDto = new FreeHistory();
        List<FreeHistory> freeHistoryList = freeHistoryService.selectList(freeHistoryReqDto);
        if (!CollectionUtils.isEmpty(freeHistoryList)) {
            for (FreeHistory freeHistory :
                freeHistoryList) {
                app = new AppEntity();
                BeanUtils.copyProperties(freeHistory, app);
                list.add(app);
            }

            int b = appService.insertOrUpdateBatch(list);  //boolean 操作是否成功
            System.out.println("结果：" + b);
        }
    }

    @Test
    public void queryPage() {
        List<AppEntity> list = new ArrayList<>();
        AppEntity app;
        AppQueryPageDTO dto = new AppQueryPageDTO();
        dto.setSize(5);
        dto.setCurrent(1);
        List<AppVO> appVOList;

        IPage<AppVO> appVOIPage = appService.queryPage(dto);
        if (appVOIPage != null) {
            appVOList = appVOIPage.getRecords();
            if (!CollectionUtils.isEmpty(appVOList)) {
                for (AppVO appVO : appVOList) {
                    System.out.println(appVO);
//                    app = new App();
//                    BeanUtils.copyProperties(appVO, app);
//                    list.add(app);
                }

//                int b = appService.insertOrUpdateBatch(list);  //boolean 操作是否成功
//                System.out.println("结果：" + b);
            }
        }
    }

    @Test
    public void testCrawlerApp() {
        String appId = "id1443533088"; //
        appId = "id441878713"; // 有应用内购买
//        appId = "id460661291";
//        appId = "id1514091454";
//        appId = "id1315296783";
        appId = "id1065802380";//有排名 ranking
        appId = "id1443533088";
        AppEntity app = JSoupUtil.crawlerApp(appId, null);
        appService.insertOrUpdateBatch(Arrays.asList(app));
    }

    @Test
    public void testUpdateAppList() {
        List<String> list = Arrays.asList(
//
//                "id1493379610",
//                "id1495547377",
//                "id6444689318",
//                "id6443585604",
//                "id1330314351",
//                "id1050297004",
//                "id6443426852",
//                "id1583550659",
            "id1423546743"
        );
        List<AppEntity> newList;
        AppEntity appNew;
        if (!CollectionUtils.isEmpty(list)) {
            newList = new ArrayList<>();
            for (String appId : list) {
                appNew = JSoupUtil.crawlerApp(appId, null);
                newList.add(appNew);

                Integer period = new Random().nextInt(2000) + 1000;
                try {
                    Thread.sleep(period);   // 休眠3秒
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            if (!CollectionUtils.isEmpty(newList)) {
                System.out.println("###");
                appService.insertOrUpdateBatch(newList);
            }
        }
    }

    @Test
    public void testSelectNoUsFlag_01() {
        List<AppEntity> noSnapshotListApp = appService.selectNoUsFlag();
        List<AppEntity> newList;

        if (!CollectionUtils.isEmpty(noSnapshotListApp)) {
            log.info("本次任务开始前有效的应用数: " + noSnapshotListApp.size());
            // 分批处理
            List<List<AppEntity>> lists = CdListUtils.splitTo(noSnapshotListApp, CdConstants.BATCH_INSERT_UPDATE_ROWS);
            for (List<AppEntity> list : lists) {
                if (!CollectionUtils.isEmpty(list)) {
                    newList = new ArrayList<>();
                    if (!CollectionUtils.isEmpty(list)) {
                        newList = new ArrayList<>();
                        for (AppEntity tempApp : list) {
                            AppEntity appNew = JSoupUtil.crawlerApp(tempApp.getAppId(), tempApp.getUsFlag());
                            newList.add(appNew);

//                            Integer period = new Random().nextInt(2000) + 500;
//                            try {
//                                Thread.sleep(period);   // 休眠3秒
//                            } catch (InterruptedException e) {
//                                throw new RuntimeException(e);
//                            }
                        }

//                        Integer period = new Random().nextInt(4000) + 500;
//                        try {
//                            Thread.sleep(period);   // 休眠3秒
//                        } catch (InterruptedException e) {
//                            throw new RuntimeException(e);
//                        }
                        if (!CollectionUtils.isEmpty(newList)) {
                            System.out.println("###");
                            appService.insertOrUpdateBatch(newList);
                        }
                    }
                }
            }
        }
    }

    @Test
    public void testCrawlerAppList_01() {
        List<AppEntity> noSnapshotListApp = appService.selectNoSnapshot();
        List<AppEntity> newList;

        if (!CollectionUtils.isEmpty(noSnapshotListApp)) {
            log.info("本次任务开始前有效的应用数: " + noSnapshotListApp.size());
            // 分批处理
            List<List<AppEntity>> lists = CdListUtils.splitTo(noSnapshotListApp, CdConstants.BATCH_INSERT_UPDATE_ROWS);
            for (List<AppEntity> list : lists) {
                if (!CollectionUtils.isEmpty(list)) {
                    newList = new ArrayList<>();
                    if (!CollectionUtils.isEmpty(list)) {
                        newList = new ArrayList<>();
                        for (AppEntity tempApp : list) {
                            AppEntity appNew = JSoupUtil.crawlerApp(tempApp.getAppId(), tempApp.getUsFlag());
                            newList.add(appNew);

                            Integer period = new Random().nextInt(2000) + 500;
                            try {
                                Thread.sleep(period);   // 休眠3秒
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }

                        Integer period = new Random().nextInt(4000) + 500;
                        try {
                            Thread.sleep(period);   // 休眠3秒
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        if (!CollectionUtils.isEmpty(newList)) {
                            System.out.println("###");
                            appService.insertOrUpdateBatch(newList);
                        }
                    }
                }
            }
        }
    }
    //

    @Test
    public void testCrawlerAppList_02() {

        List<TopList> totalTopList = CdExcelUtils.genTotalTopList();
        List<AppEntity> newList;

        //a
        List<AppEntity> appList = appService.selectList(new AppEntity());
        Set<String> appIdSet = new LinkedHashSet<>();
        if (CollectionUtils.isNotEmpty(appList)) {
            for (AppEntity app : appList) {
                appIdSet.add(app.getAppId());
            }
        }

        if (!CollectionUtils.isEmpty(totalTopList)) {
            log.info("本次任务开始前有效的应用数: " + totalTopList.size());
            // 分批处理
            List<List<TopList>> lists = CdListUtils.splitTo(totalTopList, CdConstants.BATCH_SNAPSHOT_ROWS);
            for (List<TopList> list : lists) {
                if (!CollectionUtils.isEmpty(list)) {
                    newList = new ArrayList<>();
                    for (TopList tempApp : list) {
                        if(appIdSet.contains(tempApp.getAppId())) {
                            log.error("应用已存在");
                            continue;
                        }
                        AppEntity appNew = JSoupUtil.crawlerApp(tempApp.getAppId(), null);
                        newList.add(appNew);

//                            Integer period = new Random().nextInt(2000) + 500;
//                            try {
//                                Thread.sleep(period);   // 休眠3秒
//                            } catch (InterruptedException e) {
//                                throw new RuntimeException(e);
//                            }
                    }

//                        Integer period = new Random().nextInt(4000) + 500;
//                        try {
//                            Thread.sleep(period);   // 休眠3秒
//                        } catch (InterruptedException e) {
//                            throw new RuntimeException(e);
//                        }
                    if (!CollectionUtils.isEmpty(newList)) {
                        System.out.println("###");
                        appService.insertOrUpdateBatch(newList);
                    }
                }
            }
        }
    }


    @Test
    public void testCrawlerNoAppIconUrlAppList() {
        List<AppEntity> noSnapshotListApp = appService.selectNoAppIconUrl();
        List<AppEntity> newList;

        if (!CollectionUtils.isEmpty(noSnapshotListApp)) {
            log.info("本次任务开始前无截图的应用数: " + noSnapshotListApp.size());
            // 分批处理
            List<List<AppEntity>> lists = CdListUtils.splitTo(noSnapshotListApp, CdConstants.BATCH_INSERT_UPDATE_ROWS);
            for (List<AppEntity> list : lists) {
                if (!CollectionUtils.isEmpty(list)) {
                    newList = new ArrayList<>();
                    for (AppEntity tempApp : list) {
                        AppEntity appNew = JSoupUtil.crawlerApp(tempApp.getAppId(), tempApp.getUsFlag());
                        newList.add(appNew);

                        Integer period = new Random().nextInt(400) + 100;
                        try {
                            Thread.sleep(period);   // 休眠3秒
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    Integer period = new Random().nextInt(4000) + 500;
                    try {
                        Thread.sleep(period);   // 休眠3秒
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    if (!CollectionUtils.isEmpty(newList)) {
                        System.out.println("###");
                        appService.insertOrUpdateBatch(newList);
                    }
                }
            }
        }
    }

    @Test
    public void testCrawlerNoSnapshotAppList() {
        List<AppEntity> noSnapshotListApp = appService.selectNoSnapshot();
        List<AppEntity> newList;

        if (!CollectionUtils.isEmpty(noSnapshotListApp)) {
            log.info("本次任务开始前无截图的应用数: " + noSnapshotListApp.size());
            // 分批处理
            List<List<AppEntity>> lists = CdListUtils.splitTo(noSnapshotListApp, CdConstants.BATCH_INSERT_UPDATE_ROWS);
            for (List<AppEntity> list : lists) {
                if (!CollectionUtils.isEmpty(list)) {
                    newList = new ArrayList<>();
                    for (AppEntity tempApp : list) {
                        AppEntity appNew = JSoupUtil.crawlerApp(tempApp.getAppId(), tempApp.getUsFlag());
                        newList.add(appNew);

                        Integer period = new Random().nextInt(400) + 100;
                        try {
                            Thread.sleep(period);   // 休眠3秒
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    Integer period = new Random().nextInt(4000) + 500;
                    try {
                        Thread.sleep(period);   // 休眠3秒
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    if (!CollectionUtils.isEmpty(newList)) {
                        System.out.println("###");
                        appService.insertOrUpdateBatch(newList);
                    }
                }
            }
        }
    }

    @Test
    public void testDeletedList_01() {
        List<AppEntity> noSnapshotListApp = appService.selectDeletedAppList();
        List<AppEntity> newList;

        if (!CollectionUtils.isEmpty(noSnapshotListApp)) {
            log.info("本次任务开始前已标识为下架的应用数: " + noSnapshotListApp.size());
            // 分批处理
            List<List<AppEntity>> lists = CdListUtils.splitTo(noSnapshotListApp, CdConstants.BATCH_INSERT_UPDATE_ROWS);
            for (List<AppEntity> list : lists) {
                if (!CollectionUtils.isEmpty(list)) {
                    newList = new ArrayList<>();
                    for (AppEntity tempApp : list) {
                        AppEntity appNew = JSoupUtil.crawlerApp(tempApp.getAppId(), tempApp.getUsFlag());
                        newList.add(appNew);

                        Integer period = new Random().nextInt(400) + 100;
                        try {
                            Thread.sleep(period);   // 休眠3秒
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    Integer period = new Random().nextInt(4000) + 500;
                    try {
                        Thread.sleep(period);   // 休眠3秒
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    if (!CollectionUtils.isEmpty(newList)) {
                        System.out.println("###");
                        appService.insertOrUpdateBatch(newList);
                    }
                }
            }
        }
    }


    @Test
    public void testCrawlerAppList() {
        List<String> list = Arrays.asList(
//                "id1526996611",
//                "id1478275612",
//                "id1568656727",
//                "id1598731910",
//                "id1448666661",
//                "id1483377531",
//                "id543747638",
//                "id1539930489",
//                "id1637413102",
//                "id1550800427",
//                "id950326851",
//                "id931188326",
//                "id1536924612",
//                "id1454412797", // del_flag 为空
//                "id1443533088" ,// 搜韵
//                "id1097323003","id1546838683"
//                "id1400641344" // 无中英文
//                ,
//               "id1095539172" // Rating 1.2K id1095539172
            "id688983474"
        );
        List<AppEntity> newList;
        List<PriceHistory> priceHistoryList;
        PriceHistory priceHistory;
        if (!CollectionUtils.isEmpty(list)) {
            newList = new ArrayList<>();
            priceHistoryList = new ArrayList<>();
            for (String appId : list) {
                AppEntity appNew = JSoupUtil.crawlerApp(appId, null);
                newList.add(appNew);

                priceHistory = new PriceHistory();
                BeanUtils.copyProperties(appNew, priceHistory);
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String dateStr = dateFormat.format(new Date());
                try {
                    priceHistory.setCrawlerDate(dateFormat.parse(dateStr));
                    priceHistoryList.add(priceHistory);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }

                Integer period = new Random().nextInt(3000) + 2000;
                try {
                    Thread.sleep(period);   // 休眠3秒
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            if (!CollectionUtils.isEmpty(newList)) {
                System.out.println("###");
                appService.insertOrUpdateBatch(newList);
            }

            if (!CollectionUtils.isEmpty(priceHistoryList)) {
                System.out.println("###");
                priceHistoryService.insertOrUpdateBatch(priceHistoryList);
            }
        }
    }

    @Test
    public void testCrawlerAppTotal() {
        List<AppEntity> list;
        List<AppEntity> newList;
        List<PriceHistory> priceHistoryList;
        PriceHistory priceHistory;
        int size = 300;
        Page<AppEntity> page;
        for (int i = 1; i < size; i++) {
            page = new Page<>(i, 20);//这里有 limit 后面两个参数 当前也起始索引index pageSize每页显示的条数
            appService.selectPage(page);//selectPage方法有两个参数，第一个分页对象，第二个参数Wrapper条件构造器
            if (page != null) {
                list = page.getRecords();
                if (!CollectionUtils.isEmpty(list)) {
                    newList = new ArrayList<>();
                    priceHistoryList = new ArrayList<>();
                    for (AppEntity app : list) {

                        AppEntity appNew = JSoupUtil.crawlerApp(app);
                        newList.add(appNew);
                        priceHistory = new PriceHistory();
                        BeanUtils.copyProperties(appNew, priceHistory);
                        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        String dateStr = dateFormat.format(new Date());
                        try {
                            priceHistory.setCrawlerDate(dateFormat.parse(dateStr));
                            priceHistoryList.add(priceHistory);
                        } catch (ParseException e) {
                            throw new RuntimeException(e);
                        }
                        System.out.println(app.getAppId());
                        Integer period = new Random().nextInt(2000) + 500;
                        try {
                            Thread.sleep(period);   // 休眠3秒
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    if (!CollectionUtils.isEmpty(newList)) {
                        appService.insertOrUpdateBatch(newList);
                    }
                    if (!CollectionUtils.isEmpty(priceHistoryList)) {
                        priceHistoryService.insertOrUpdateBatch(priceHistoryList);
                    }
                    Integer period = new Random().nextInt(3000) + 1000;
                    try {
                        Thread.sleep(period);   // 休眠3秒
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    @Test
    public void testCrawlerAppPrice() {
        List<AppEntity> list;
        Page<AppEntity> page = new Page<>(2, 200);//这里有 limit 后面两个参数 当前也起始索引index pageSize每页显示的条数
        appService.selectPage(page);//selectPage方法有两个参数，第一个分页对象，第二个参数Wrapper条件构造器

        if (page != null) {
            list = page.getRecords();
            List<CrawlerHistory> crawlerHistoryList;
            if (!CollectionUtils.isEmpty(list)) {
                crawlerHistoryList = new ArrayList<>();
                CrawlerHistory crawlerHistory;
                for (AppEntity app : list) {
                    System.out.println(app.getAppId());
                    Integer period = new Random().nextInt(5) + 3;
                    try {
                        Thread.sleep(1000 * period);   // 休眠3秒
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    AppEntity appNew = JSoupUtil.crawlerApp(app);
                    crawlerHistory = new CrawlerHistory();
                    BeanUtils.copyProperties(appNew, crawlerHistory);
                    System.out.println(appNew);
                    crawlerHistory.setCreatedDate(new Date());
                    crawlerHistoryList.add(crawlerHistory);
                }

//                if (!CollectionUtils.isEmpty(crawlerHistoryList)) {
//                    crawlerHistoryService.insertOrUpdateBatch(crawlerHistoryList);
//                }
            }
        }
    }

    @Test
    public void testInitTopFlag() {
        int result = appService.initTopFlag();
        log.info(result + "");
    }

    @Test
    public void testProcessWechat() {
        int result = appService.processWechat();
        log.info(result + "");
    }

    @Test
    public void testGenDailyPpt() {
        int result = appService.genDailyPpt();
        log.info(result + "");
    }
}
