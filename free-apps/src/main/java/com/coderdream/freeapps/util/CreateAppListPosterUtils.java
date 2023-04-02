package com.coderdream.freeapps.util;


import com.coderdream.freeapps.model.AppBrief;
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
public class CreateAppListPosterUtils {

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
        createAppListImage(appBriefList);
    }

    /**
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * @Author fengfanli
     * @Description //TODO 创建海报，画字、画图
     * @Date 17:41 2021/4/8
     * @Param []
     **/
    public static void createAppListImage(List<AppBrief> appBriefList) {

        List<List<AppBrief>> lists = BaseUtils.splitAppBriefList(3, appBriefList);

        int size = appBriefList.size();
        String singlePosterPath1;
        String singlePosterPath2;
        String singlePosterPath3;
        // 背景模板图
        String background = "D:\\12_iOS_Android\\base\\base_page_v1.png";

        int i = 0;
        for (List<AppBrief> list : lists) {
            i++;
            int listSize = list.size();

            AppBrief appBrief1;
            AppBrief appBrief2;
            AppBrief appBrief3;
            switch (listSize) {
                case 1:
                    //语句
                    appBrief1 = list.get(0);
                    singlePosterPath1 = appBrief1.getSinglePosterPath();

                    try {
                        // 初始化模板图
                        BufferedImage bufferedImage = PosterUtil.drawInit(background, null);
                        File file = new File(appBrief1.getListAppsPath());
                        if (!file.exists()) {
                            file.mkdirs();
                        }

                        // 画 二维码 并改变大小
                        InputStream singlePosterInputStream1 = new FileInputStream(new File(singlePosterPath1));
                        // 2. 初始化并的改变大小
                        BufferedImage singlePosterImage1 = PosterUtil.drawInitAndChangeSize(null, singlePosterInputStream1, 417, 1000);
                        // 3. 画二维码
                        PosterUtil.drawImage(bufferedImage, singlePosterImage1, 100, 20); // 532, 1108);

                        // 海报 保存到本地
                        String filename = appBrief1.getListAppsPath() + "list_" + String.format("%02d", i) + ".png";
                        System.out.println("保存到本地: " + filename);
                        PosterUtil.save(bufferedImage, "png", filename);
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("Test4_Main_CreatePosterAndEchart createPoster error:" + e.getLocalizedMessage());
                    }

                    break; //可选
                case 2:
                    appBrief1 = list.get(0);
                    appBrief2 = list.get(1);

                    singlePosterPath1 = appBrief1.getSinglePosterPath();
                    singlePosterPath2 = appBrief2.getSinglePosterPath();

                    try {
                        // 初始化模板图
                        BufferedImage bufferedImage = PosterUtil.drawInit(background, null);

                        File file = new File(appBrief1.getListAppsPath());
                        if (!file.exists()) {
                            file.mkdirs();
                        }

                        // 画 二维码 并改变大小
                        InputStream singlePosterInputStream1 = new FileInputStream(new File(singlePosterPath1));
                        InputStream singlePosterInputStream2 = new FileInputStream(new File(singlePosterPath2));
                        // 2. 初始化并的改变大小
                        BufferedImage singlePosterImage1 = PosterUtil.drawInitAndChangeSize(null, singlePosterInputStream1, 417, 1000);
                        BufferedImage singlePosterImage2 = PosterUtil.drawInitAndChangeSize(null, singlePosterInputStream2, 417, 1000);
                        // 3. 画二维码
                        PosterUtil.drawImage(bufferedImage, singlePosterImage1, 100, 20); // 532, 1108);
                        PosterUtil.drawImage(bufferedImage, singlePosterImage2, 730, 20); // 532, 1108);

                        // 海报 保存到本地
                        String filename = appBrief1.getListAppsPath() + "list_" + String.format("%02d", i) + ".png";
                        System.out.println("保存到本地: " + filename);
                        PosterUtil.save(bufferedImage, "png", filename);
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("Test4_Main_CreatePosterAndEchart createPoster error:" + e.getLocalizedMessage());
                    }
                    //语句
                    break; //可选
                //你可以有任意数量的case语句
                default: //可选
                    //语句
                    appBrief1 = list.get(0);
                    appBrief2 = list.get(1);
                    appBrief3 = list.get(2);

                    singlePosterPath1 = appBrief1.getSinglePosterPath();
                    singlePosterPath2 = appBrief2.getSinglePosterPath();
                    singlePosterPath3 = appBrief3.getSinglePosterPath();
                    try {
                        // 初始化模板图
                        BufferedImage bufferedImage = PosterUtil.drawInit(background, null);

                        File file = new File(appBrief1.getListAppsPath());
                        if (!file.exists()) {
                            file.mkdirs();
                        }

                        // 画 二维码 并改变大小
                        InputStream singlePosterInputStream1 = new FileInputStream(new File(singlePosterPath1));
                        InputStream singlePosterInputStream2 = new FileInputStream(new File(singlePosterPath2));
                        InputStream singlePosterInputStream3 = new FileInputStream(new File(singlePosterPath3));
                        // 2. 初始化并的改变大小
                        BufferedImage singlePosterImage1 = PosterUtil.drawInitAndChangeSize(null, singlePosterInputStream1, 417, 1000);
                        BufferedImage singlePosterImage2 = PosterUtil.drawInitAndChangeSize(null, singlePosterInputStream2, 417, 1000);
                        BufferedImage singlePosterImage3 = PosterUtil.drawInitAndChangeSize(null, singlePosterInputStream3, 417, 1000);
                        // 3. 画二维码
                        PosterUtil.drawImage(bufferedImage, singlePosterImage1, 100, 20); // 532, 1108);
                        PosterUtil.drawImage(bufferedImage, singlePosterImage2, 730, 20); // 532, 1108);
                        PosterUtil.drawImage(bufferedImage, singlePosterImage3, 1360, 20); // 532, 1108);

                        // 海报 保存到本地
                        String filename = appBrief1.getListAppsPath() + "list_" + String.format("%02d", i) + ".png";
                        System.out.println("保存到本地: " + filename);
                        PosterUtil.save(bufferedImage, "png", filename);
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("Test4_Main_CreatePosterAndEchart createPoster error:" + e.getLocalizedMessage());
                    }
            }
        }
    }

}
