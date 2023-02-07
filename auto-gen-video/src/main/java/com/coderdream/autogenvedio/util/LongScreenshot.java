package com.coderdream.autogenvedio.util;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;
import org.openqa.selenium.Platform;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
public class LongScreenshot {



    /**
     * 截图
     *
     * @param domain          地址  https://www.baidu.com/
     * @param correlationId   图片附件保留关联id
     * @param rollingDistance 滚动距离 300
     * @param waitTime        滚动等待时间(毫秒) 500
     * @param implicitlyWait  浏览器等待时间
     * @return void
     * @date 2020/9/28 10:48
     * @author Dora
     **/
    public void screenshot(String domain, Long correlationId , int rollingDistance, long waitTime, long implicitlyWait, String fileName) {
//        if (WebAssert.isAnyoneEmpty(domain, correlationId)) {
//            throw new BusinessException("[域名|关联id]不能为空");
//        }
        long o = System.currentTimeMillis();
        log.info("网站:{},截图-上传开始时间:{}", domain, o);
        WebDriver driver = this.createWebDriver();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            driver.manage().timeouts().implicitlyWait(implicitlyWait, TimeUnit.SECONDS);
            driver.manage().window().maximize();
            driver.get(domain);
            //等待页面加载完成
            new WebDriverWait(driver, Duration.ofMillis(3000)).until(drive -> ((JavascriptExecutor) drive)
                    .executeScript("return document.readyState").equals("complete"));
            // 设置小的分辨率
            driver.manage().window().setSize(new Dimension(1920, 1080));
            JavascriptExecutor je = (JavascriptExecutor) driver;
            //    int width = Integer.parseInt(je.executeScript("return document.body.scrollWidth") + "");
            int height = Integer.parseInt(je.executeScript("return document.body.scrollHeight") + "");
            log.info("{},当前页面高度:{}", domain, height);
            // 滚动次数
            int frequency = height % rollingDistance == 0 ? height / rollingDistance : height / rollingDistance + 1;
            for (int i = 0; i < frequency; i++) {

                int length = i * rollingDistance;
                Thread.sleep(waitTime);
                ((JavascriptExecutor) driver).executeScript("window.scrollTo(0," + length + ")");
            }

            //设置浏览窗口大小
            driver.manage().window().setSize(new Dimension(1440, height));
            // 重新拉到页面顶端
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(document.body.scrollHeight,0)");

            // 截图
            Screenshot screenshot = new AShot().coordsProvider(new WebDriverCoordsProvider()).takeScreenshot(driver);
            BufferedImage image = screenshot.getImage();

            log.info("网站:{},截图-上传结束时间:{}", domain, System.currentTimeMillis() - o);
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {

        }
        driver.quit();
    }

    public static WebDriver createWebDriver() {
        List<String> snapshotFileNameList = new ArrayList<>();
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
        chromeOptions.addArguments("--window-size=3840,1916"); // 3840 1916 1920,1050
        WebDriver driver = new ChromeDriver(chromeOptions);

        ChromeOptions options = new ChromeOptions();
        options.addArguments("test-type");
        options.addArguments("--disable-extensions--");
        options.addArguments("proxy=null");
        options.addArguments("--headless");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--no-sandbox");
        options.addArguments("--incognito");
        DesiredCapabilities capability = new DesiredCapabilities();
        capability.setBrowserName("chrome");
        capability.setPlatform(Platform.LINUX);
        try {
            driver = new RemoteWebDriver(new URL(driverPath), capability);
        } catch (MalformedURLException e) {
//            logger.error("driver异常：{}", e);
        }
        return driver;

    }
}
