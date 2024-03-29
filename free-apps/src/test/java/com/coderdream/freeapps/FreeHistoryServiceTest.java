package com.coderdream.freeapps;

import com.coderdream.freeapps.model.AppEntity;
import com.coderdream.freeapps.model.AppBrief;
import com.coderdream.freeapps.model.Description;
import com.coderdream.freeapps.model.FreeHistory;
import com.coderdream.freeapps.service.AppService;
import com.coderdream.freeapps.service.DescriptionService;
import com.coderdream.freeapps.service.FreeHistoryService;
import com.coderdream.freeapps.util.other.BaseUtils;
import com.coderdream.freeapps.util.other.CdFileUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@SpringBootTest
public class FreeHistoryServiceTest {

    @Resource
    private FreeHistoryService freeHistoryService;

    @Resource
    private AppService appService;
    @Resource
    private DescriptionService descriptionService;

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
        List<FreeHistory> list = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            FreeHistory freeHistory = new FreeHistory();
            freeHistory.setAppId("user" + i);

            int b = freeHistoryService.insertSelective(freeHistory);  //boolean 操作是否成功
            System.out.println("结果：" + b);
        }
    }

    @Test
    public void testInsertOrUpdateBatch() {
        //批量添加
        //INSERT INTO user ( id,title, age, email ) VALUES ( ?, ?, ?, ? )
        List<FreeHistory> list = new ArrayList<>();
        for (int i = 1; i <= 200; i++) {
            FreeHistory freeHistory = new FreeHistory();
//            freeHistory.setAppId("id" +  String.format("%09d",  new Random().nextInt(999999999)));
            freeHistory.setAppId("id" + String.format("%09d", new Random().nextInt(99)));
            freeHistory.setTitle("title" + new Random().nextInt(100));
            list.add(freeHistory);
        }
        int b = freeHistoryService.insertOrUpdateBatch(list);  //boolean 操作是否成功
        System.out.println("结果：" + b);
    }

    @Test
    public void testUpdateRecommendFlagBatch() {
        List<FreeHistory> list = new ArrayList<>();
        List<AppBrief> appBriefList = BaseUtils.genBrief();

        FreeHistory freeHistory;
        for (AppBrief appBrief : appBriefList) {
            freeHistory = new FreeHistory();
            freeHistory.setAppId(appBrief.getAppId());
            freeHistory.setDataSource("cl");
            freeHistory.setFreeDate(new Date());
            freeHistory.setRecommendFlag(1);
            list.add(freeHistory);
        }

        int b = freeHistoryService.updateRecommendFlagBatch(list);  //boolean 操作是否成功
        System.out.println("结果：" + b);
    }


    @Test
    public void testInsertOrUpdateBatch_2022() {
        //批量添加
        //INSERT INTO user ( id,title, age, email ) VALUES ( ?, ?, ?, ? )
//        List<FreeHistory> list = new ArrayList<>();
        String fileName = "D:\\12_iOS_Android\\1024_data\\2022-06-29.txt";
        fileName = "D:\\04_GitHub\\java-architect-util\\free-apps\\src\\main\\resources\\data\\1024\\2023";
        fileName += File.separatorChar + "202304" + File.separatorChar + "2023-04-13.txt";
        List<FreeHistory> freeHistoryList = CdFileUtils.getFreeHistoryFromCL(fileName);
        for (FreeHistory freeHistory : freeHistoryList) {
            System.out.println(freeHistory);
        }
//        for (int i = 1; i <= 200; i++) {
//            FreeHistory freeHistory = new FreeHistory();
////            freeHistory.setAppId("id" +  String.format("%09d",  new Random().nextInt(999999999)));
//            freeHistory.setAppId("id" +  String.format("%09d",  new Random().nextInt(99)));
//            freeHistory.setName("title" + new Random().nextInt(100));
//            freeHistoryList.add(freeHistory);
//        }
        int b = freeHistoryService.insertOrUpdateBatch(freeHistoryList);  //boolean 操作是否成功
        System.out.println("结果：" + b);
    }

    @Test
    public void testInsertOrUpdateBatch_02() {
        //批量添加
        //INSERT INTO user ( id,title, age, email ) VALUES ( ?, ?, ?, ? )
//        List<FreeHistory> list = new ArrayList<>();
        String fileName = "D:\\12_iOS_Android\\1024_data\\2022-06-29.txt";
        fileName = "D:\\04_GitHub\\java-architect-util\\free-apps\\src\\main\\resources\\data\\1024\\2023";
        fileName += File.separatorChar + "202302" + File.separatorChar + "2023-02-18.txt";
        List<FreeHistory> freeHistoryList = CdFileUtils.getFreeHistoryFromCL(fileName);
        for (FreeHistory freeHistory : freeHistoryList) {
            System.out.println(freeHistory);
        }
//        for (int i = 1; i <= 200; i++) {
//            FreeHistory freeHistory = new FreeHistory();
////            freeHistory.setAppId("id" +  String.format("%09d",  new Random().nextInt(999999999)));
//            freeHistory.setAppId("id" +  String.format("%09d",  new Random().nextInt(99)));
//            freeHistory.setName("title" + new Random().nextInt(100));
//            freeHistoryList.add(freeHistory);
//        }
        int b = freeHistoryService.insertOrUpdateBatch(freeHistoryList);  //boolean 操作是否成功
        System.out.println("结果：" + b);
    }

    @Test
    public void testInsertOrUpdateBatch_20220630() {
        //批量添加
        //INSERT INTO user ( id,title, age, email ) VALUES ( ?, ?, ?, ? )
//        List<FreeHistory> list = new ArrayList<>();
        String fileName = "D:\\12_iOS_Android\\1024_data\\2022-06-29.txt";
        fileName = "D:\\04_GitHub\\java-architect-util\\free-apps\\src\\main\\resources\\data\\1024\\2023";
        fileName += File.separatorChar + "202305" + File.separatorChar + "2023-05-02.txt";
        List<FreeHistory> freeHistoryList = CdFileUtils.getFreeHistoryFromCL(fileName);
        for (FreeHistory freeHistory : freeHistoryList) {
            System.out.println(freeHistory);
        }
        int b = freeHistoryService.insertOrUpdateBatch(freeHistoryList);  //boolean 操作是否成功
        System.out.println("结果：" + b);
    }

    @Test
    public void testInsertOrUpdateBatch_20230321() {
        //批量添加
        //INSERT INTO user ( id,title, age, email ) VALUES ( ?, ?, ?, ? )
//        List<FreeHistory> list = new ArrayList<>();
        String fileName = "D:\\12_iOS_Android\\1024_data\\2022-06-29.txt";
        fileName = "D:\\04_GitHub\\java-architect-util\\free-apps\\src\\main\\resources\\data\\1024\\2023";
        fileName += File.separatorChar + "202308" + File.separatorChar + "2023-08-09.txt";
        List<FreeHistory> freeHistoryList = CdFileUtils.getFreeHistoryFromCL(fileName);
        for (FreeHistory freeHistory : freeHistoryList) {
            System.out.println(freeHistory);
        }
        int b = freeHistoryService.insertOrUpdateBatch(freeHistoryList);  //boolean 操作是否成功
        System.out.println("结果：" + b);
    }

    @Test
    public void testInsertOrUpdateBatch_total() {
        String folderPath = "D:\\04_GitHub\\java-architect-util\\free-apps\\src\\main\\resources\\data\\1024";
        folderPath = folderPath + File.separatorChar + "2023";
        folderPath = folderPath + File.separatorChar + "202304";
//        folderPath = folderPath + File.separatorChar + "2023-03-31.txt";
        List<String> stringList = new ArrayList<>();
        CdFileUtils.getFiles(folderPath, stringList);
        for (String fileName : stringList) {
            System.out.println(fileName);
            List<FreeHistory> freeHistoryList = CdFileUtils.getFreeHistoryFromCL(fileName);
            int b = freeHistoryService.insertOrUpdateBatch(freeHistoryList);  //boolean 操作是否成功
            System.out.println("结果：" + b);
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
                appService.insertOrUpdateBatch(appList);
                descriptionService.insertOrUpdateBatch(descriptionList);
            }
        }
    }

    @Test
    public void testInsertOrUpdateBatch_202206() {
        String folderPath = "D:\\04_GitHub\\java-architect-util\\free-apps\\src\\main\\resources\\data\\1024";
        folderPath = folderPath + File.separatorChar + "2022";
        folderPath = folderPath + File.separatorChar + "202206";
        List<String> stringList = new ArrayList<>();
        CdFileUtils.getFiles(folderPath, stringList);
        for (String fileName : stringList) {
            System.out.println(fileName);
            List<FreeHistory> freeHistoryList = CdFileUtils.getFreeHistoryFromCL(fileName);
            int b = freeHistoryService.insertOrUpdateBatch(freeHistoryList);  //boolean 操作是否成功
            System.out.println("结果：" + b);
        }
    }

    @Test
    public void testProcessWechat() {
        int b = freeHistoryService.processWechat();  //boolean 操作是否成功
        System.out.println("结果：" + b);
    }
}
