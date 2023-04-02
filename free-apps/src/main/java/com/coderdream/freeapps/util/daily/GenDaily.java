package com.coderdream.freeapps.util.daily;

import com.coderdream.freeapps.model.AppBrief;
import com.coderdream.freeapps.util.BaseUtils;
import com.coderdream.freeapps.util.CreateAppListPosterUtils;
import com.coderdream.freeapps.util.CreateAppTotalPosterUtils;
import com.coderdream.freeapps.util.CreateSinglePosterUtils;
import com.coderdream.freeapps.util.CutImageUtils;
import com.coderdream.freeapps.util.GenerateAppInfo;
import com.coderdream.freeapps.util.QrCodeUtils;
import java.io.File;
import java.util.List;

public class GenDaily {

    public static void main(String[] args) {
        // 1.
        com.fasterxml.jackson.datatype.jsr310.ser.ZoneIdSerializer z;
        // jackson-datatype-jsr310-2.13.4.jar
        // jackson-datatype-jdk8:2.13.4.jar
        gen();
    }

    public static void gen() {
        String path = BaseUtils.getPath();
        List<AppBrief> appBriefList = BaseUtils.genBrief();
        // 1. 截屏
//        boolean res = SnapshotUtils.snapshot(appBriefList);

//        if(!res) {
//            return;
//        }

//        appBriefList.get(0).setSnapshotPath("D:\\12_iOS_Android\\202301\\20230105\\snapshot\\01_id1163814608.png");
//        appBriefList.get(1).setSnapshotPath("D:\\12_iOS_Android\\202301\\20230105\\snapshot\\02_id793344899.png");
//        appBriefList.get(2).setSnapshotPath("D:\\12_iOS_Android\\202301\\20230105\\snapshot\\03_id6444249607.png");
//        appBriefList.get(3).setSnapshotPath("D:\\12_iOS_Android\\202301\\20230105\\snapshot\\04_id1513210383.png");
//        appBriefList.get(4).setSnapshotPath("D:\\12_iOS_Android\\202301\\20230105\\snapshot\\05_id1353515249.png");
//        appBriefList.get(5).setSnapshotPath("D:\\12_iOS_Android\\202301\\20230105\\snapshot\\06_id1635383253.png");
//        appBriefList.get(6).setSnapshotPath("D:\\12_iOS_Android\\202301\\20230105\\snapshot\\07_id6443908307.png");
//        appBriefList.get(7).setSnapshotPath("D:\\12_iOS_Android\\202301\\20230105\\snapshot\\08_id1493379610.png");
//        for (AppBrief appBrief : appBriefList) {
//            System.out.println(appBrief.getSnapshotPath());
//        }

        // 3. 生成二维码
        for (AppBrief appBrief : appBriefList) {
            String fileName = appBrief.getFilename() + ".png";
            System.out.println("fileName: " + fileName);
            QrCodeUtils.createQrCode(appBrief.getUrlCn(), path + File.separator + "qr", "qr_" + fileName);
        }

        // 2. 剪图
        CutImageUtils.cutImages(appBriefList); // 小梦

        // 4. 生成单张简介
        for (AppBrief appBrief : appBriefList) {
            System.out.println(appBrief.getQrUrl());
            CreateSinglePosterUtils.createSingleAppImage(appBrief);
        }

        // 5. 生成应用列表海报
        CreateAppListPosterUtils.createAppListImage(appBriefList);

        // 6. 生成应用详情海报
        CreateAppTotalPosterUtils.createAppTotalImage(appBriefList);

        // 7. 生成解说字幕
        List<String> todayContentList = GenerateAppInfo.genTodayContent();
        for (String todayContent : todayContentList) {
            System.out.println(todayContent);
        }

        // 8. 生成文章
//        System.out.println("立省78 元立省66元，苹果限免日报1月23日更新");

        System.out.println("#############################################");
        // 9. 文章简介
        List<String> articleContentList = GenerateAppInfo.genArticleContent();
        for (String articleContent : articleContentList) {
            System.out.println(articleContent);
        }

        System.out.println("#############################################");

        // 8. 生成简介
        List<String> briefContentList = GenerateAppInfo.genBriefContent();
        for (String briefContent : briefContentList) {
            System.out.println(briefContent);
        }

        // 9. 生成名称价格列表
        System.out.println("应用名称" + "\t" + "原价");
        for (AppBrief appBrief : appBriefList) {
            System.out.println(appBrief.getName() + "\t" + appBrief.getPrice() + "元");
        }
    }

}
