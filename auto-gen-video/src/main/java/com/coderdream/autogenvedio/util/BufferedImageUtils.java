package com.coderdream.autogenvedio.util;

import com.coderdream.autogenvedio.entity.AppBrief;
import com.google.common.util.concurrent.SimpleTimeLimiter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.internal.Require;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

// icon
//        int x = 1165;// 大：下移
//                int y = 398; // 大：下移
//                int width = 360;
//                int height = 360;
public class BufferedImageUtils {

    public static void main(String[] args) {
        String srcImageFileName = "D:\\12_iOS_Android\\202301\\20230102\\snapshot\\1_id1592844577.png";
        String destImageFileName = "D:\\12_iOS_Android\\202301\\20230102\\snapshot\\1_id1592844577_new.png";

        BaseUtils.getPath();
        cut4K(srcImageFileName, destImageFileName);
        List<String> urlList = GenerateAppInfo.genUrlCnList();
        int x = 1165;// 大：下移
        int y = 398; // 大：下移
        int width = 360;
        int height = 360;
        String destImageFile;
        String singleImageFile;
        String appId;
        // 截图文件夹
        String snapshotPath = BaseUtils.getPath() + File.separator + "snapshot";
        File snapshotDir = new File(snapshotPath);
        if(!snapshotDir.exists()) {
            snapshotDir.mkdirs();
        }

        String finalPath = BaseUtils.getPath() + File.separator + "final";

        String singlePath = BaseUtils.getPath() + File.separator + "single";
        File finalDir = new File(finalPath);
        if(!finalDir.exists()) {
            finalDir.mkdirs();
        }

        String iconPath = BaseUtils.getPath() + File.separator + "icon";
        File iconDir = new File(iconPath);
        if(!iconDir.exists()) {
            iconDir.mkdirs();
        }

        File singleDir = new File(singlePath);
        if(!singleDir.exists()) {
            singleDir.mkdirs();
        }
        // 获取截图文件列表
        List<String> filePathList = getFileList(snapshotPath);
        int i = 0;
        System.out.println(filePathList.size() + ":" + urlList.size());
        if(filePathList.size() == urlList.size()) {
            for (String filePath : filePathList) {
                appId = StringUtils.parseAppId(urlList.get(i));
                System.out.println(filePath);
                x = 1165;// 大：右移
                y = 398; // 大：下移
                width = 360;
                height = 360;
                destImageFile = iconPath + File.separator + "03_" + String.format("%02d", i) + "_" + appId + ".png";
                cut(new File(filePath), x, y, width, height, new File(destImageFile));
                x = 1115;// 大：右移
                y = 398; // 大：下移
                width = 1600;
                height = 1600;

                singleImageFile = singlePath + File.separator + "04_" + String.format("%02d", i) + "_" + appId + ".png";
                cut(new File(filePath), x, y, width, height, new File(singleImageFile));
                i++;
            }
        }

    }

    public static List<String> getFileList(String path) {
        List<String> filePathList = new ArrayList<>();
        // 路径
//        String path = "/Users/wudi/Documents/java";
        File f = new File(path);

// 路径不存在
        if (!f.exists()) {
            System.out.println(path + " not exists");
            return null;
        }

        File result[] = f.listFiles();
//  循环遍历
        for (int i = 0; i < result.length; i++) {
            File fs = result[i];
            if (fs.isFile()) {
                System.out.println(fs.getName());
                filePathList.add(fs.getAbsolutePath());
            }
        }

        return filePathList;
    }


    public static boolean cut4K(String srcImageFileName, String destImageFileName) {
        File srcImageFile = new File(srcImageFileName);
        int x = 770;
        int y = 155;
        int width = 1000;
        int height = 777;
        File destImageFile = new File(destImageFileName);
        return cut(srcImageFile, x, y, width, height, destImageFile);
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
            System.out.println(formatName + ":" + destImageFile);
            ImageIO.write(image, formatName, destImageFile);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static class SeleniumSnapShot {

        public static void main(String[] args) {

        }

        public static void snapshot(List<AppBrief> appBriefList) throws InterruptedException {
            String driverPath = Thread.currentThread().getContextClassLoader().getResource("static/chromedriver.exe").getPath();
            System.setProperty("webdriver.chrome.driver", driverPath);   //设置chrome驱动程序的路径

            System.out.println(System.getProperty("webdriver.chrome.driver"));

            WebDriver driver = new ChromeDriver();   //初始化一个chrome驱动实例，保存到driver中
            // WebDriver driver =  = new FirefoxDriver();
            Require r;
            driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS); //隐式等待10秒
            //最大化窗口
            driver.manage().window().maximize();  //最大化窗口
            //设置隐性等待时间
            SimpleTimeLimiter abc;
            driver.get("https://www.runoob.com/html/html-tutorial.html");
            Thread.sleep(3000);  //等待页面加载完成，后续写代码注意，如果页面加载未完成，可能导致页面元素找不到
            System.out.println("当前打开页面的标题是： " + driver.getTitle());

            //设置浏览器弹窗页面的大小
            driver.manage().window().maximize();
            //使用getScreenshotAs进行截取屏幕
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            try {
                FileUtils.copyFile(srcFile, new File("E:\\jjjj.png"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            //关闭并退出浏览器
            driver.quit();
        }
    }
}
