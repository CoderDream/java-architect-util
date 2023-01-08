package com.coderdream.autogenvedio.util;


import com.coderdream.autogenvedio.entity.AppBrief;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @ClassName Main
 * @Description TODO
 * @Author admin
 * @Date 2021/3/30 15:21
 * @Version 1.0
 */
public class CreatePoster {

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
        String result = createSingleAppImage(appBrief);

    }

    /**
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * @Author fengfanli
     * @Description //TODO 创建海报，画字、画图
     * @Date 17:41 2021/4/8
     * @Param []
     **/
    public static String createSingleAppImage(AppBrief appBrief) {
        String nickName = appBrief.getName();
        // headUrl
        String headUrl = "https://img-blog.csdn.net/20180529211243786?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3plbmdyZW55dWFu/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/7";
        // 背景模板图
        String background = "D:\\12_iOS_Android\\base\\e.png";
        // 统一字体 格式
        String wordsFont = "平方-简";
        String nameFont = "黑体";

        Map<String, Object> map = new HashMap<>();
        try {
            // 初始化模板图
            BufferedImage bufferedImage = PosterUtil.drawInit(background, null);
            // 将头像图片处理成圆形
            //BufferedImage head = PosterUtil.drawImgToCircle(headUrl, 92);
            // 画 微信头像
            //PosterUtil.drawImage(bufferedImage, head, 49, 49);

            // 下面三个数据都可以从数据库中动态获取
            String brief = "应用简介：";// appBrief.getBrief();// "中国好BURUNDI中国好NGOZI2020-2021中国好GEISHA中国好";
            List<String> content = appBrief.getContent();
            for (String con : content) {
                brief += con;
            }
//            if (title.length() > 42) {
//                title = title.substring(0, 42) + "...";
//            }


//            String provider = "生豆商信息生豆商信息生豆商信息生豆商信息生豆商信息生豆商信息生豆商信息生豆商信息生豆商信息"; // 42
//            if (provider.length() > 42) {
//                provider = provider.substring(0, 42) + "...";
//            }
//            String remark = "备注信息备注信息备注信息备注信息备注信息备注信息备注信息备注信息备注信息备注信息";
//            if (remark.length() > 28) {
//                remark = remark.substring(0, 28) + "...";
//            }
            String rawInfoMood = "原价：" + appBrief.getPrice() + "元";// "为您诚挚推荐XX信息";

            // 画 二维码 并改变大小
            // 1. 先 获取二维码(二维码携带一个参数)
//            InputStream erWeiMa =  ErWeiMaUtil.getErWeiMa(1, "");
            String qrUrl = appBrief.getQrUrl();
            System.out.println("Create Post, qrUrl: " + qrUrl);
            InputStream erWeiMa = new FileInputStream(new File(qrUrl));
            // 2. 初始化并的改变大小
            BufferedImage logoImg = PosterUtil.drawInitAndChangeSize(null, erWeiMa, 330, 330);
            // 将二维码保存到本地
//            PosterUtil.save(logoImg, "png", "d:\\test5555.png");
            // 3. 画二维码
            PosterUtil.drawImage(bufferedImage, logoImg, 70, 800); // 532, 1108);

            // 自定义咖啡背景图片
//            String customCoffeeImageUrl = "D:\\12_iOS_Android\\202301\\20230102\\qrid1080129732.png";
//            BufferedImage coffeeImage = PosterUtil.drawInitAndChangeSize(customCoffeeImageUrl, null, 575, 431);
//            PosterUtil.drawImage(bufferedImage, coffeeImage, 87, 379);

            // 画字：nickName
//            PosterUtil.drawWords(bufferedImage, nickName, false, nameFont, 36, 30, 90, 800, 26);
            PosterUtil.drawWords(bufferedImage, nickName, false, nameFont, 36, 30, 90, 402, 119);
            // 画字：rawInfoMood:为您诚挚推荐生豆信息
            PosterUtil.drawWords(bufferedImage, rawInfoMood, false, wordsFont, 32, 30, 225, 800, 19);
            // 画字：brief
            PosterUtil.drawWords(bufferedImage, brief, true, wordsFont, 32, 30, 335, 402, 119);
//            // 画字：provider
//            PosterUtil.drawWords(bufferedImage, provider, false, wordsFont, 30, 89, 870, 452, 100);
//            // 画字：remark
//            PosterUtil.drawWords(bufferedImage, remark, false, wordsFont, 24, 88, 1000, 452, 70);

            // 海报 保存到本地
            String filename = appBrief.getSinglePosterPath();
            System.out.println("保存到本地: " + filename);
            PosterUtil.save(bufferedImage, "png", filename);

            return filename;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Test4_Main_CreatePosterAndEchart createPoster error:" + e.getLocalizedMessage());
            return null;
        }
    }

}
