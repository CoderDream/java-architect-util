package com.coderdream.autogenvedio.selenium;

import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
public class HelloSelenium {
    public static void main(String[] args) throws MalformedURLException {
//        useRemoteWebDriver();
//        useLocalWebDriver();


//        String url = "https://apps.apple.com/us/app/translator-plus/id1435494722?l=zh";
        String url = "https://apps.apple.com/us/app/id1061289155";
        useRemoteWebDriver(url);
//        useLocalWebDriver(url);
    }

    private static void useRemoteWebDriver() throws MalformedURLException {
        long startTime = System.currentTimeMillis();
        ChromeOptions chromeOptions = new ChromeOptions();
        String cloudUrl = "http://59.172.75.156:34444/wd/hub";
        // DesiredCapabilities.class
        WebDriver driver = new RemoteWebDriver(new URL(cloudUrl), chromeOptions);


//        ChromeOptions browserOptions = new ChromeOptions();
//        browserOptions.setPlatformName("Windows 10");
//        browserOptions.setBrowserVersion("92");
//        Map<String, Object> cloudOptions = new HashMap<>();
////        cloudOptions.put("build", myTestBuild);
////        cloudOptions.put("name", myTestName);
//        browserOptions.setCapability("cloud:options", cloudOptions);
//        WebDriver driver = new RemoteWebDriver(new URL(cloudUrl), browserOptions);

//        WebDriver driver = new ChromeDriver();

        Map uaMap = new HashMap(){{
            put("userAgent", "customUserAgent");
        }};
        ((CdpRemoteWebDriver) driver).executeCdpCommand("Network.setUserAgentOverride",
                uaMap
        );
        driver.get("http://www.baidu.com");
        System.out.println(driver.getTitle());
        driver.quit();
        long endTime = System.currentTimeMillis();
        log.info("useRemoteWebDriver 方法运行时间：" + (endTime - startTime) + "ms");
    }

    public static void useRemoteWebDriver(String url) throws MalformedURLException {
        long startTime = System.currentTimeMillis();
        ChromeOptions chromeOptions = new ChromeOptions();

        // DesiredCapabilities.class
        WebDriver driver = new RemoteWebDriver(new URL(url), chromeOptions);

//        Map uaMap = new HashMap(){{
//            put("userAgent", "customUserAgent");
//        }};
//        ((CdpRemoteWebDriver) driver).executeCdpCommand("Network.setUserAgentOverride",
//                uaMap
//        );
//        driver.get(url);


        String xPath = "/html/body/div[5]/main/div[2]/section[1]/div/div[2]/header/h1";
        WebElement webElement = driver.findElement(By.xpath(xPath));
        System.out.println("应用名称" + webElement.getText());

//        "body > div.ember-view > main > div.animation-wrapper.is-visible > section.l-content-width.section.section--hero.product-hero > div > div.l-column.small-7.medium-8.large-9.small-valign-top > header > ul:nth-child(3) > li > ul > li > figure"

        xPath = "/html/body/div[5]/main/div[2]/section[1]/div/div[2]/header/ul[1]/li/ul/li/figure";
        webElement = driver.findElement(By.xpath(xPath));
        System.out.println("评分：" + webElement.getText());
        System.out.println(driver.getTitle());

        driver.quit();
        long endTime = System.currentTimeMillis();
        log.info("useRemoteWebDriver 方法运行时间：" + (endTime - startTime) + "ms");
    }




    private static void useLocalWebDriver() throws MalformedURLException {
        long startTime = System.currentTimeMillis();
        String driverPath = Thread.currentThread().getContextClassLoader().getResource("static/chromedriver.exe").getPath();
        System.setProperty("webdriver.chrome.driver", driverPath);   //设置chrome驱动程序的路径
        System.out.println(System.getProperty("webdriver.chrome.driver"));
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setHeadless(true);
        WebDriver driver = new ChromeDriver(chromeOptions);   //初始化一个chrome驱动实例，保存到driver中
        driver.get("http://www.baidu.com");
        System.out.println(driver.getTitle());
        driver.quit();
        long endTime = System.currentTimeMillis();
        log.info("useLocalWebDriver 方法运行时间：" + (endTime - startTime) + "ms");
    }

    // https://apps.apple.com/us/app/translator-plus/id1435494722?l=zh

    public static void useLocalWebDriver(String url) throws MalformedURLException {
        String driverPath = Thread.currentThread().getContextClassLoader().getResource("static/chromedriver.exe").getPath();
        System.setProperty("webdriver.chrome.driver", driverPath);   //设置chrome驱动程序的路径
        System.out.println(System.getProperty("webdriver.chrome.driver"));
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setHeadless(true);
        WebDriver driver = new ChromeDriver(chromeOptions);   //初始化一个chrome驱动实例，保存到driver中
        driver.get(url);

//        driver.findElement(By.className("className"));
//        driver.findElement(By.cssSelector(".className"));
//        driver.findElement(By.id("elementId"));
//        driver.findElement(By.linkText("linkText"));
//        driver.findElement(By.name("elementName"));
//        driver.findElement(By.partialLinkText("partialText"));
//        driver.findElement(By.tagName("elementTagName"));
//        driver.findElement(By.xpath("xPath"));

        String xPath = "/html/body/div[5]/main/div[2]/section[1]/div/div[2]/header/h1";
        WebElement webElement = driver.findElement(By.xpath(xPath));
        System.out.println("应用名称" + webElement.getText());

//        "body > div.ember-view > main > div.animation-wrapper.is-visible > section.l-content-width.section.section--hero.product-hero > div > div.l-column.small-7.medium-8.large-9.small-valign-top > header > ul:nth-child(3) > li > ul > li > figure"

        xPath = "/html/body/div[5]/main/div[2]/section[1]/div/div[2]/header/ul[1]/li/ul/li/figure";
        webElement = driver.findElement(By.xpath(xPath));
        System.out.println("评分：" + webElement.getText());
        System.out.println(driver.getTitle());
        driver.quit();
    }
}
