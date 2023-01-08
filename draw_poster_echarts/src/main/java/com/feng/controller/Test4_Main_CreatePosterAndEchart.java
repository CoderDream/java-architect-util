package com.feng.controller;

import com.feng.ErWeiMaUtils.ErWeiMaUtil;
import com.feng.bean.BakeEventList;
import com.feng.echartsUtils.EchartsUtil;
import com.feng.posterUtils.PosterUtil;
import com.feng.uploadUtils.UploadImageUtil;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;

/**
 * @ClassName Main
 * @Description TODO
 * @Author admin
 * @Date 2021/3/30 15:21
 * @Version 1.0
 */
public class Test4_Main_CreatePosterAndEchart {

    public static void main(String[] args) {
        // 生成海报
        Map<String, Object> result = createPoster();
        System.out.println(result);
        // 生成 带echarts图的海报
        Map<String, Object> posterDrawEchartTable = createPosterDrawEchartTable();
        System.out.println(posterDrawEchartTable);
    }

    /**
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * @Author fengfanli
     * @Description //TODO 创建海报，画字、画图
     * @Date 17:41 2021/4/8
     * @Param []
     **/
    public static Map<String, Object> createPoster() {
        String nickName = "爱吃鱼的猫";
        // headUrl
        String headUrl = "https://img-blog.csdn.net/20180529211243786?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3plbmdyZW55dWFu/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/7";
        // 背景模板图
        String background = "https://xkximg.xiaokaxia.com/wx/1/9dfb96f770e64642835289912cc13c46.png";
        // 统一字体 格式
        String wordsFont = "平方-简";

        Map<String, Object> map = new HashMap<>();
        try {
            // 初始化模板图
            BufferedImage bufferedImage = PosterUtil.drawInit(background, null);
            // 将头像图片处理成圆形
            BufferedImage head = PosterUtil.drawImgToCircle(headUrl, 92);
            // 画 微信头像
            PosterUtil.drawImage(bufferedImage, head, 49, 49);

            // 下面三个数据都可以从数据库中动态获取
            String title = "中国好BURUNDI中国好NGOZI2020-2021中国好GEISHA中国好";
            String provider = "生豆商信息生豆商信息生豆商信息生豆商信息生豆商信息生豆商信息生豆商信息生豆商信息生豆商信息"; // 42
            if (provider.length() > 42) {
                provider = provider.substring(0, 42) + "...";
            }
            String remark = "备注信息备注信息备注信息备注信息备注信息备注信息备注信息备注信息备注信息备注信息";
            if (remark.length() > 28) {
                remark = remark.substring(0, 28) + "...";
            }
            String rawInfoMood = "为您诚挚推荐XX信息";

            // 画 二维码 并改变大小
            // 1. 先 获取二维码(二维码携带一个参数)
//            InputStream erWeiMa =  ErWeiMaUtil.getErWeiMa(1, "");
            InputStream erWeiMa =  new FileInputStream(new File("D:\\12_iOS_Android\\202301\\20230102\\qr\\1_qr_id1512838461.jpg"));
            // 2. 初始化并的改变大小
            BufferedImage logoImg = PosterUtil.drawInitAndChangeSize(null, erWeiMa, 130, 130);
            // 将二维码保存到本地
            PosterUtil.save(logoImg, "png", "d:\\test5555.png");
            // 3. 画二维码
            PosterUtil.drawImage(bufferedImage, logoImg, 532, 1108);

            // 自定义咖啡背景图片
            String customCoffeeImageUrl = "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1789442356,2769602647&fm=26&gp=0.jpg";
            BufferedImage coffeeImage = PosterUtil.drawInitAndChangeSize(customCoffeeImageUrl, null, 575, 431);
            PosterUtil.drawImage(bufferedImage, coffeeImage, 87, 379);

            // 画字：nickName
            PosterUtil.drawWords(bufferedImage, nickName, false, wordsFont, 28, 158, 90, 1000, 26);
            // 画字：rawInfoMood:为您诚挚推荐生豆信息
            PosterUtil.drawWords(bufferedImage, rawInfoMood, false, wordsFont, 20, 158, 125, 1000, 19);
            // 画字：title
            PosterUtil.drawWords(bufferedImage, title, true, wordsFont, 36, 88, 265, 552, 119);
            // 画字：provider
            PosterUtil.drawWords(bufferedImage, provider, false, wordsFont, 30, 89, 870, 552, 100);
            // 画字：remark
            PosterUtil.drawWords(bufferedImage, remark, false, wordsFont, 24, 88, 1000, 552, 70);

            // 海报 保存到本地
            PosterUtil.save(bufferedImage, "png", "d:\\singleRawBeanBackground.png");

            // 上传到 OSS
//            ByteArrayOutputStream os = new ByteArrayOutputStream();
//            ImageIO.write(bufferedImage, "png", os);
//            InputStream inputStream = new ByteArrayInputStream(os.toByteArray());
//            map = UploadImageUtil.uploadShareSeaImg(inputStream, "bakeShare");
//            return map;
            return new LinkedHashMap<>();
        } catch (Exception e) {
            System.out.println("Test4_Main_CreatePosterAndEchart createPoster error:" + e.getLocalizedMessage());
            return null;
        }
    }


    /**
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * @Author fengfanli
     * @Description //TODO 创建海报，画字，画图，画echarts图
     * @Date 17:41 2021/4/8
     * @Param []
     **/
    public static Map<String, Object> createPosterDrawEchartTable() {
        String nickName = "爱吃鱼的猫";
        // headUrl
        String headUrl = "https://img-blog.csdn.net/20180529211243786?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3plbmdyZW55dWFu/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/7";
        // 单品背景模板图
        String background = "https://xkximg.xiaokaxia.com/wx/1/fb16ea06f66644009f54b8efbb670bd2.png";
        // 统一字体 格式
        String wordsFont = "平方-简";

        Map<String, Object> map = new HashMap<>();
        try {
            // 初始化模板图
            BufferedImage bufferedImage = PosterUtil.drawInit(background, null);
            // 将头像图片处理成圆形
            BufferedImage head = PosterUtil.drawImgToCircle(headUrl, 92);
            // 画 微信头像
            PosterUtil.drawImage(bufferedImage, head, 49, 49);

            // 下面三个数据都可以从数据库中动态获取
            String title = "中国好BURUNDI中国好NGOZI2020-2021瑰夏GEISHA中国好中国好";
            String rawInfoMood = "为您诚挚推荐XX信息";

            // 画 二维码 并改变大小
            // 1. 先 获取二维码(二维码携带一个参数)
//            InputStream erWeiMa = ErWeiMaUtil.getErWeiMa(1, "");

            InputStream erWeiMa =  new FileInputStream(new File("D:\\12_iOS_Android\\202301\\20230102\\qr\\1_qr_id1512838461.jpg"));
            // 2. 初始化并的改变大小
            BufferedImage logoImg = PosterUtil.drawInitAndChangeSize(null, erWeiMa, 130, 130);
            // 将二维码保存到本地
            PosterUtil.save(logoImg, "png", "d:\\test6666.png");
            // 3. 画二维码
            PosterUtil.drawImage(bufferedImage, logoImg, 532, 1108);

            // 自定义咖啡背景图片
            String customCoffeeImageUrl = "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1789442356,2769602647&fm=26&gp=0.jpg";
            BufferedImage coffeeImage = PosterUtil.drawInitAndChangeSize(customCoffeeImageUrl, null, 575, 287);
            PosterUtil.drawImage(bufferedImage, coffeeImage, 87, 379);

            // 画字：nickName
            PosterUtil.drawWords(bufferedImage, nickName, false, wordsFont, 28, 158, 90, 1000, 26);
            // 画字：rawInfoMood:为您诚挚推荐生豆信息
            PosterUtil.drawWords(bufferedImage, rawInfoMood, false, wordsFont, 20, 158, 125, 1000, 19);
            // 画字：title
            PosterUtil.drawWords(bufferedImage, title, true, wordsFont, 36, 88, 265, 552, 119);
            // 画表格
            List<BakeEventList> bakeEventLists = new ArrayList<>();
            bakeEventLists.add(new BakeEventList("12:01", 10.10));
            bakeEventLists.add(new BakeEventList("13:01", 15.10));
            bakeEventLists.add(new BakeEventList("14:01", 18.10));
            bakeEventLists.add(new BakeEventList("15:01", 5.10));
            bakeEventLists.add(new BakeEventList("16:01", 4.10));
            try {
                EchartsUtil.createEcharts(bufferedImage, bakeEventLists);
            } catch (Exception e) {
                System.out.println("PosterController createPoster bake draw table error:" + e);
            }

            // 海报 保存到本地
            PosterUtil.save(bufferedImage, "png", "d:\\singleRawBeanBackground.png");

            // 上传值 OSS
//            ByteArrayOutputStream os = new ByteArrayOutputStream();
//            ImageIO.write(bufferedImage, "png", os);
//            InputStream inputStream = new ByteArrayInputStream(os.toByteArray());
//            map = UploadImageUtil.uploadShareSeaImg(inputStream, "bakeShare");
//            return map;
            return new LinkedHashMap<>();
        } catch (Exception e) {
            System.out.println("Test4_Main_CreatePosterAndEchart createPictorial error:" + e.getLocalizedMessage());
            return null;
        }
    }
}
