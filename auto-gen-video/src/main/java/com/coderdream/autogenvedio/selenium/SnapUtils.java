package com.coderdream.autogenvedio.selenium;

import com.coderdream.autogenvedio.util.StringUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.List;

public class SnapUtils {

    public static void main(String[] args) {
//        String url = "https://www.baidu.com";
        String imgName = "baidu";
        String type ="";
        String resultId ="";

        List<String> urlList = new ArrayList<>(Arrays.asList("https://apps.apple.com/cn/app/id1592844577",
                "https://apps.apple.com/cn/app/id1578843767",
                "https://apps.apple.com/cn/app/id1536585848",
                "https://apps.apple.com/cn/app/id425893570",
                "https://apps.apple.com/cn/app/id1510078277",
                "https://apps.apple.com/cn/app/id1255192598",
                "https://apps.apple.com/cn/app/id598710611",
                "https://apps.apple.com/cn/app/id6443737201"));

        for (String url:        urlList) {
            String appId = StringUtils.parseAppId( url);
            imgName = appId;
            execute(url);
        }

    }

    /**
     * 通用chromeDriver获取方法
     *
     * @param argument 获取浏览器宽高
     */
    public WebDriver getDriver(String argument) {
        String driverPath = Thread.currentThread().getContextClassLoader().getResource("static/chromedriver.exe").getPath();
        System.setProperty("webdriver.chrome.driver", driverPath);   //设置chrome驱动程序的路径
        System.out.println(System.getProperty("webdriver.chrome.driver"));

        //驱动地址(linux用)
        System.setProperty("webdriver.chrome.driver",
                driverPath);
        ChromeOptions chromeOptions = new ChromeOptions();
        // chrome安装路径(linux用)
//        chromeOptions.setBinary("/usr/bin/google-chrome");
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("start-maximized");
        chromeOptions.addArguments("disable-infobars");
        chromeOptions.addArguments("--disable-dev-shm-usage");
        chromeOptions.addArguments("--test-type");
        chromeOptions.addArguments("--disable-extensions");
        chromeOptions.addArguments("--headless");
        chromeOptions.setExperimentalOption("useAutomationExtension", false);
        chromeOptions.addArguments("--disable-dev-shm-usage");
        if (!argument.equals("")) chromeOptions.addArguments(argument);
        WebDriver webDriver = new ChromeDriver(chromeOptions);
        return webDriver;
    }

    /**
     * 无头浏览器获取页面生成的宽高
     * 完成后执行截图
     */
    public static void pdfImgGenerate(String url, String imgName, String type, String resultId) {
//        String url = "";// MdisConfig.getPdfUrl() + "?id=" + resultId + "&type=" + type;
        WebDriver driver = new SnapUtils().getDriver("");
        driver.get(url);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //获取JS执行器，可以执行js代码来控制页面
        JavascriptExecutor driver_js = ((JavascriptExecutor) driver);
        Long height = (Long) driver_js.executeScript("return document.body.scrollHeight");
        Long width = (Long) driver_js.executeScript("return document.body.scrollWidth");
        System.out.println("height" + height);
        Map<String, Long> map = new HashMap<>();
        map.put("height", height);
        map.put("width", width);
        driver.quit();
        new SnapUtils().frontEndCut(imgName, url, map);
    }

    /**
     * 无头浏览器截图
     */
    public void frontEndCut(String imgName, String url, Map<String, Long> map) {
        String argument = "--window-size=" + map.get("width") + "," + map.get("height");
        WebDriver driver = new SnapUtils().getDriver(argument);
        driver.get(url);
        // 页面等待渲染时长，如果你的页面需要动态渲染数据的话一定要留出页面渲染的时间，单位默认是秒
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 获取到截图的文件
        File screenshotFile = ((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.FILE);
        if ((screenshotFile != null) && screenshotFile.exists()) {
            //截取到的图片存到本地
            FileOutputStream out = null;
            FileInputStream in = null;
            try {
                in = new FileInputStream(screenshotFile);
                // 本地路径 MdisConfig.getUploadPath() +
//                out = new FileOutputStream("/images/" + imgName + ".png");
                out = new FileOutputStream("/" + imgName + ".png");
                byte[] b = new byte[1024];
                while (true) {
                    int temp = in.read(b, 0, b.length);
                    // 如果temp = -1的时候，说明读取完毕
                    if (temp == -1) {
                        break;
                    }
                    out.write(b, 0, temp);
                }
            } catch (Exception e) {
                //TODO异常处理
            }
        }
        driver.quit();
    }

    // , String device
    public static String execute(String uri) {
//        PerfCounter.count("miui_ad_schedule_capture.execute.total", 1L);
        long start = System.currentTimeMillis();
//        System.setProperty("webdriver.chrome.driver", "/home/rd/chromedriver");
        //windows: System.setProperty("webdriver.chrome.driver", "src/main/resources/driver/chromedriver.exe");
        WebDriver driver = new SnapUtils().getDriver("");// createWebDriver(device);
        String cdnUrl = "";
        try {
            driver.get(uri);
            String filePath = "/";
            cdnUrl = savePage(driver, filePath, "capture_" + System.currentTimeMillis() + ".png");
        } catch (Exception e) {
//            logger.error("capture fail: [{},{}],{} !", uri ,device,e);
        } finally {
            driver.close();
            driver.quit();
        }
//        PerfCounter.count("miui_ad_schedule_capture.execute.success", 1L, System.currentTimeMillis() - start);
        return cdnUrl;
    }

    private static String savePage(WebDriver driver, String filePath, String fileName) throws IOException, InterruptedException, NoSuchAlgorithmException {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        jsExecutor.executeScript("window.scrollTo(0,0)");
        BufferedImage imageOriginal = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);// 创建全屏截图
        int lastScroll = -1;
        int currentScroll = 0;
        Thread.sleep(1000);
        while (lastScroll != currentScroll) {
            byte[] bytesScroll = takesScreenshot.getScreenshotAs(OutputType.BYTES);
            BufferedImage imageScroll = ImageIO.read(new ByteArrayInputStream(bytesScroll));
            int screenHeight = imageScroll.getHeight();
            int screenWidth = imageScroll.getWidth();
            System.out.println("scree:" + screenHeight + ":" + screenWidth);

            BufferedImage combined = new BufferedImage(screenWidth, currentScroll + screenHeight, BufferedImage.TYPE_INT_RGB);
            Graphics g = combined.getGraphics();
            g.drawImage(imageOriginal, 0, 0, null);
            g.drawImage(imageScroll, 0, currentScroll, null);
            imageOriginal = combined;

//            logger.info("lastScroll:" + lastScroll + "    currentScroll:" + currentScroll + "    screenHeight:" + screenHeight);
            int scrollTo = currentScroll + screenHeight;
            lastScroll = currentScroll;
            jsExecutor.executeScript("window.scrollTo(0," + scrollTo + ")");
            currentScroll = Double.valueOf(jsExecutor.executeScript("return document.documentElement.scrollTop").toString()).intValue();
            System.out.println(screenHeight + ":" + scrollTo);
            if (lastScroll > 5000) {
                break;
            }
            Thread.sleep(1000);
        }

        File path = new File(filePath);
        if (!path.exists() || !path.isDirectory()) {
            path.mkdirs();
        }
        File file = new File(path.getAbsolutePath() + File.separatorChar + fileName);
        ImageIO.write(imageOriginal, "png", file);
        String uploadPath = path.getAbsolutePath() + File.separatorChar + fileName; //FileStorageUtils.upload(file.getAbsolutePath(), CHANNEL_ID);
//        System.out.println("uploadPath:" + uploadPath);
//        if(file.exists()){
//            file.delete();
//        }
        return uploadPath;
    }
}
