package com.coderdream.autogenvedio.util;


import com.coderdream.autogenvedio.entity.AppBrief;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @ClassName Main
 * @Description TODO
 * @Author admin
 * @Date 2021/3/30 15:21
 * @Version 1.0
 */
public class CreateAppTotalPosterUtils {

    public static void main(String[] args) {
        // 20220102 TODO
        String dateStr = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String monthStr = new SimpleDateFormat("yyyyMM").format(new Date());
        String basePath = "D:" + File.separator + "12_iOS_Android" + File.separator + monthStr + File.separator + dateStr;

        AppBrief appBrief = new AppBrief();
        appBrief.setName("File Explorer & Player [Pro]");
        appBrief.setAppId("id1095142462");
        appBrief.setPrice(30);
        appBrief.setUrlCn("https://apps.apple.com/cn/app/id1095142462");
        appBrief.setBrief("把你的iPhone或iPad变成你的Mac的无线闪存盘。完全访问你的Mac文件-使用你的iOS设备流媒体视频，查看照片和文件，在家里的任何地方。");
        appBrief.setContent(new ArrayList<>(Arrays.asList("把你的iPhone或iPad变成你的Mac的无线闪存盘。", "完全访问你的Mac文件-使用你的iOS设备流媒体视频，查看照片和文件，在家里的任何地方。")));
        appBrief.setQrUrl("D:\\12_iOS_Android\\202301\\20230102\\qr\\1_qr_id1512838461.jpg");
        appBrief.setSinglePosterPath("D:\\12_iOS_Android\\202301\\20230102\\single_1_id1512838461.jpg");

        List<AppBrief> appBriefList = new ArrayList<>(Arrays.asList(appBrief));

        // 生成海报
        createAppTotalImage(appBriefList);
    }

    /**
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * @Author fengfanli
     * @Description //TODO 创建海报，画字、画图
     * @Date 17:41 2021/4/8
     * @Param []
     **/
    public static void createAppTotalImage(List<AppBrief> appBriefList) {
        int size = appBriefList.size();
        String singlePosterPath;
        String detailPath;
        String pagePosterPath;
        // 背景模板图
        String background = "D:\\12_iOS_Android\\base\\base_page_v5.png";

        int i = 0;
        for (AppBrief appBrief : appBriefList) {
            i++;

            singlePosterPath = appBrief.getSinglePosterPath();
            detailPath = appBrief.getDetailPath();
            pagePosterPath = appBrief.getPagePosterPath();

            try {
                // 初始化模板图
                BufferedImage bufferedImage = PosterUtil.drawInit(background, null);
                File file = new File(pagePosterPath);
                if (!file.exists()) {
                    file.mkdirs();
                }

                // 画 二维码 并改变大小
                InputStream singlePosterInputStream = new FileInputStream(new File(singlePosterPath));
                InputStream detailPathInputStream = new FileInputStream(new File(detailPath));
                // 2. 初始化并的改变大小
                BufferedImage singlePosterImage = PosterUtil.drawInitAndChangeSize(null, singlePosterInputStream, 417, 1000);
                BufferedImage detailPathImage = PosterUtil.drawInitAndChangeSize(null, detailPathInputStream, 1000, 1000);
                // 3. 画二维码
                PosterUtil.drawImage(bufferedImage, singlePosterImage, 100, 20); // 532, 1108);
                PosterUtil.drawImage(bufferedImage, detailPathImage, 730, 20); // 532, 1108);

                // 海报 保存到本地  + "page_" + fileName + ".png"
                String filename = appBrief.getPagePosterPath() + "page_" + String.format("%02d", i) + ".png";
                System.out.println("保存到本地: " + filename);
                PosterUtil.save(bufferedImage, "png", filename);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Test4_Main_CreatePosterAndEchart createPoster error:" + e.getLocalizedMessage());
            }
        }
    }

}
