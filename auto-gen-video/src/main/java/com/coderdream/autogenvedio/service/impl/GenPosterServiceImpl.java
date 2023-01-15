package com.coderdream.autogenvedio.service.impl;

import com.coderdream.autogenvedio.entity.AppBrief;
import com.coderdream.autogenvedio.util.*;

import java.io.File;
import java.util.List;

public class GenPosterServiceImpl {

    public static void main(String[] args) {
        // 1.
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
        CutImageUtils.cutImages(appBriefList);

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
        for (AppBrief appBrief : appBriefList) {
            System.out.println("应用名称：" + appBrief.getName());
            System.out.println("原价：" + appBrief.getPrice() + "元");
            System.out.println("应用简介：" + appBrief.getContent().toString());
            System.out.println("下载地址：" + appBrief.getUrlCn());
            System.out.println();
        }

    }
}
