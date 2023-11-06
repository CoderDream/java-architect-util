package com.coderdream.freeapps.util.other;

import com.coderdream.freeapps.model.AppBrief;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;

public class CutImageUtils {

    /**
     * JAVA裁剪图片
     *
     * @param srcImageFile  需要裁剪的图片
     * @param destImageFile  裁剪生成的图片
     * @return
     */
    public static boolean cutForQr(File srcImageFile, File destImageFile) {
        try {
            int x = 884;
            int y = 86;
            int width = 180;
            int height = 180;
            //使用ImageIO的read方法读取图片
            BufferedImage read = ImageIO.read(srcImageFile);
            //调用裁剪方法
            BufferedImage image = read.getSubimage(x, y, width, height);
            //获取到文件的后缀名
            String fileName = srcImageFile.getName();
            String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);
            //使用ImageIO的write方法进行输出
            ImageIO.write(image, formatName, destImageFile);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean cutForQrV2(File srcImageFile, File destImageFile) {
        try {
            int x = 26;
            int y = 26;
            int width = 265;
            int height = 265;
            //使用ImageIO的read方法读取图片
            BufferedImage read = ImageIO.read(srcImageFile);
            //调用裁剪方法
            BufferedImage image = read.getSubimage(x, y, width, height);
            //获取到文件的后缀名
            String fileName = srcImageFile.getName();
            String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);
            //使用ImageIO的write方法进行输出
            ImageIO.write(image, formatName, destImageFile);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * JAVA裁剪图片
     *
     * @param srcImageFile  需要裁剪的图片
     * @param x             裁剪时x的坐标（左上角）
     * @param y             裁剪时y的坐标（左上角）
     * @param width         裁剪后的图片宽度
     * @param height        裁剪后的图片高度
     * @param destImageFile 裁剪后的图片
     * @return
     */
    public static boolean cut(File srcImageFile, int x, int y, int width, int height, File destImageFile) {
        try {
            //使用ImageIO的read方法读取图片
            BufferedImage read = ImageIO.read(srcImageFile);
            //调用裁剪方法
            BufferedImage image = read.getSubimage(x, y, width, height);
            //获取到文件的后缀名
            String fileName = srcImageFile.getName();
            String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);
            //使用ImageIO的write方法进行输出
            ImageIO.write(image, formatName, destImageFile);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    public static void cutImages(List<AppBrief> appBriefList) {

//        List<String> urlList = GenerateAppInfo.genUrlCnList();
        int x = 1165;// 大：下移
        int y = 398; // 大：下移
        int width = 360;
        int height = 360;
        String detailImageFile;
        String iconImageFile;
        String singleImageFile;
//        String appId;
        String singleFilename;
        String filename;
        // 截图文件夹
//        String snapshotPath = BaseUtils.getPath() + File.separator + "snapshot";
//        File snapshotDir = new File(snapshotPath);
//        if(!snapshotDir.exists()) {
//            snapshotDir.mkdirs();
//        }
        // app详情
        String detailPath = BaseUtils.getPath() + File.separator + "detail";
        File detailDir = new File(detailPath);
        if(!detailDir.exists()) {
            detailDir.mkdirs();
        }

        String iconPath = BaseUtils.getPath() + File.separator + "icon";
        File iconDir = new File(iconPath);
        if(!iconDir.exists()) {
            iconDir.mkdirs();
        }

        String singlePath = BaseUtils.getPath() + File.separator + "single";
        File singleDir = new File(singlePath);
        if(!singleDir.exists()) {
            singleDir.mkdirs();
        }
        // 获取截图文件列表
//        List<String> filePathList = getFileList(snapshotPath);
        int i = 0;
        String snapshotPath;
//        System.out.println(filePathList.size() + ":" + urlList.size());
//        if(filePathList.size() == urlList.size()) {
        for (AppBrief appBrief : appBriefList) {
            snapshotPath = appBrief.getSnapshotPath();
//                appId = StringUtils.parseAppId(urlList.get(i));
            singleFilename = appBrief.getSingleFilename();
            filename = appBrief.getFilename();
//                appId = snapshotPath.substring(snapshotPath.lastIndexOf(File.separator) + 3, snapshotPath.lastIndexOf("."));
            System.out.println("filename: " + filename);

            File file = new File(snapshotPath);
            if(file.exists()) {
                System.out.println(snapshotPath);
                x = 1405;// 大：右移
                y = 160; // 大：下移
                width = 250;
                height = 250;
                // 保存应用图标
                cut(new File(snapshotPath), x, y, width, height, new File(appBrief.getIconUrl()));
                x = 1405;// 大：右移
                y = 160; // 大：下移
                width = 930;
                height = 880;

                // 保存应用详情
                cut(new File(snapshotPath), x, y, width, height, new File(appBrief.getDetailPath()));
                i++;
            } else {
                System.out.println("File not exist " + snapshotPath);
            }
        }
//        }
    }

//    /**
//     * JAVA裁剪图片
//     *
//     * @param srcImageFile  需要裁剪的图片
//     * @param x             裁剪时x的坐标（左上角）
//     * @param y             裁剪时y的坐标（左上角）
//     * @param width         裁剪后的图片宽度
//     * @param height        裁剪后的图片高度
//     * @param destImageFile 裁剪后的图片
//     * @return
//     */
//    public static boolean cut(File srcImageFile, int x, int y, int width, int height, File destImageFile) {
//        try {
//            //使用ImageIO的read方法读取图片
//            BufferedImage read = ImageIO.read(srcImageFile);
//            //调用裁剪方法
//            BufferedImage image = read.getSubimage(x, y, width, height);
//            //获取到文件的后缀名
//            String fileName = srcImageFile.getName();
//            String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);
//            //使用ImageIO的write方法进行输出
//            ImageIO.write(image, formatName, destImageFile);
//        } catch (IOException e) {
//            e.printStackTrace();
//            return false;
//        }
//        return true;
//    }
}
