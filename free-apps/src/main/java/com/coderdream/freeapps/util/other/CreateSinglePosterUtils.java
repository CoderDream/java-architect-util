package com.coderdream.freeapps.util.other;

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
public class CreateSinglePosterUtils {

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

        // 生成海报
        createSingleAppImage(appBrief);
    }

    /**
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * @Author fengfanli
     * @Description //TODO 创建海报，画字、画图
     * @Date 17:41 2021/4/8
     * @Param []
     **/
    public static void createSingleAppImage(AppBrief appBrief) {
        String name = appBrief.getName();
        String iconUrl = appBrief.getIconUrl();
        // 背景模板图
        String background = "D:\\12_iOS_Android\\base\\e.png";
        // 统一字体 格式
        String wordsFont = "平方-简";
        String nameFont = "黑体";
        try {
            // 初始化模板图
            BufferedImage bufferedImage = PosterUtil.drawInit(background, null);

            // 下面三个数据都可以从数据库中动态获取
            String brief = "应用简介：";
            List<String> content = appBrief.getContent();
            for (String con : content) {
                brief += con;
            }

            if(brief.length() > 99) {
                brief = brief.substring(0, 99) + "...";
            }

            String priceInfo = "原价：" + appBrief.getPrice() + "元";

            InputStream iconImageInputStream = new FileInputStream(new File(iconUrl));
            // 2. 初始化并的改变大小
            BufferedImage iconImage = PosterUtil.drawInitAndChangeSize(null, iconImageInputStream, 160, 160);
            // 3. 画应用图标
            PosterUtil.drawImage(bufferedImage, iconImage, 300, 130); // 532, 1108);

            // 画 二维码 并改变大小
            // 1. 先 获取二维码(二维码携带一个参数)
            String qrUrl = appBrief.getQrUrl();
            System.out.println("Create Post, qrUrl: " + qrUrl);
            InputStream erWeiMa = new FileInputStream(new File(qrUrl));
            // 2. 初始化并的改变大小
            BufferedImage qrImage = PosterUtil.drawInitAndChangeSize(null, erWeiMa, 350, 350);
            // 3. 画二维码
            PosterUtil.drawImage(bufferedImage, qrImage, 80, 750); // 532, 1108);

            // 画字：name
            PosterUtil.drawWords(bufferedImage, name, false, nameFont, 36, 30, 90, 402, 119);
            // 画字：priceInfo
            PosterUtil.drawWords(bufferedImage, priceInfo, false, wordsFont, 32, 30, 170, 800, 19);
            // 画字：brief
            PosterUtil.drawWords(bufferedImage, brief, true, wordsFont, 32, 30, 355, 410, 149);

            // 海报 保存到本地
            String filename = appBrief.getSinglePosterPath();
            System.out.println("保存到本地: " + filename);
            PosterUtil.save(bufferedImage, "png", filename);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("createSingleAppImage error:" + e.getLocalizedMessage());
        }
    }

}
