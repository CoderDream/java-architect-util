package com.coderdream.autogenvedio.util;

import com.coderdream.autogenvedio.entity.AppBrief;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SnapshotWechatUtils {
    public static void main(String[] args) {

        String url = "https://mp.weixin.qq.com/s/tSMK-ZYig6r3kpnZQju5vw";
        Integer appAmount = 6;
        snapshot(url, appAmount);
    }

    public static boolean snapshot(String url, Integer appAmount) {
        boolean res = true;
        long startTime = System.currentTimeMillis();
        String driverPath = Thread.currentThread().getContextClassLoader().getResource("static/chromedriver.exe").getPath();
        System.setProperty("webdriver.chrome.driver", driverPath);   //设置chrome驱动程序的路径
        System.out.println(System.getProperty("webdriver.chrome.driver"));
        // ChromeOptions9hy
        ChromeOptions chromeOptions = new ChromeOptions();
        // 设置后台静默模式启动浏览器
        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("--start-maximized");
        chromeOptions.addArguments("--no-sandbox");
//        chromeOptions.addArguments("--window-size=3840,1916"); // 3840 1916 1920,
        int height = appAmount * 1000 + 916;
        chromeOptions.addArguments("--window-size=3840," + height); // 3840 1916 1920,1050
        WebDriver driver = new ChromeDriver(chromeOptions);   //初始化一个chrome驱动实例，保存到driver中

        // 截图文件夹
        String snapshotPath = BaseUtils.getPath() + File.separator + "wechat_snapshot";
        File snapshotDir = new File(snapshotPath);
        if (!snapshotDir.exists()) {
            snapshotDir.mkdirs();
        }

        File file;
        try {

            System.out.println("####url:\t" + url);
            driver.get(url);
            Thread.sleep(5000);  //等待页面加载完成，后续写代码注意，如果页面加载未完成，可能导致页面元素找不到
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //使用getScreenshotAs进行截取屏幕
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            String dateStr = new SimpleDateFormat("yyyyMMdd").format(new Date());
            String fileName = snapshotPath + File.separator + dateStr + ".png";
            file = new File(fileName);
            FileUtils.copyFile(srcFile, file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //关闭并退出浏览器
        driver.quit();
        System.out.println("生成成功！");
        System.out.println("耗时: " + (System.currentTimeMillis() - startTime) / 1000.0 + "s");
        return res;
    }
}
