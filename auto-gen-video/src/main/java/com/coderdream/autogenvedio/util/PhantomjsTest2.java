package com.coderdream.autogenvedio.util;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class PhantomjsTest2 {
    public static void main(String[] args) throws InterruptedException, IOException {
        //设置必要参数
        DesiredCapabilities dcaps = new DesiredCapabilities();
        //ssl证书支持
        dcaps.setCapability("acceptSslCerts", true);
        //截屏支持
        dcaps.setCapability("takesScreenshot", true);
        //css搜索支持
        dcaps.setCapability("cssSelectorsEnabled", true);
        //js支持
        dcaps.setJavascriptEnabled(true);
        //驱动支持（第二参数表明的是你的phantomjs引擎所在的路径）
        com.google.common.collect.ImmutableList eeei;
        String driverPath = Thread.currentThread().getContextClassLoader().getResource("static/phantomjs/bin/phantomjs.exe").getPath();
        dcaps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
                driverPath);
        //创建无界面浏览器对象
        PhantomJSDriver driver = new PhantomJSDriver(dcaps);

        //设置隐性等待（作用于全局）
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        long start = System.currentTimeMillis();
        //打开页面
        driver.get("https://juejin.im/post/5bb24bafe51d450e4437fd96");
        Thread.sleep(30 * 1000);
        JavascriptExecutor js = driver;
        for (int i = 0; i < 33; i++) {
            js.executeScript("window.scrollBy(0,1000)");
            //睡眠10s等js加载完成
            Thread.sleep(5 * 1000);
        }
        //指定了OutputType.FILE做为参数传递给getScreenshotAs()方法，其含义是将截取的屏幕以文件形式返回。
        File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        Thread.sleep(3000);
        //利用FileUtils工具类的copyFile()方法保存getScreenshotAs()返回的文件对象
        FileUtils.copyFile(srcFile, new File("juejin-01.png"));
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " 毫秒");
    }
}
