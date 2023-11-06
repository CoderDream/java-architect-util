package com.coderdream.freeapps;


import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FirstScriptTest {

    @Test
    public void eightComponents() {
        String driverPath = Thread.currentThread().getContextClassLoader().getResource("static/chromedriver.exe")
            .getPath();
        System.setProperty("webdriver.chrome.driver", driverPath);   //设置chrome驱动程序的路径
        System.out.println(System.getProperty("webdriver.chrome.driver"));
//        WebDriver driver = new ChromeDriver();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");//解决 403 出错问题
        chromeOptions.addArguments("--start-maximized");
        chromeOptions.addArguments("--no-sandbox"); // TODO
//        chromeOptions.setExperimentalOption("prefs", chromePreferences);
        String chromeExeFile = "D:\\00_Green\\Chrome100_AllNew_2022.4.4\\App\\chrome.exe";
        if (chromeExeFile != null) {
            chromeOptions.setBinary(chromeExeFile);
        }
        ChromeDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://www.selenium.dev/selenium/web/web-form.html");

        String title = driver.getTitle();
        System.out.println(title);
        assertEquals("Web form", title);

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        WebElement textBox = driver.findElement(By.name("my-text"));
        WebElement submitButton = driver.findElement(By.cssSelector("button"));

        textBox.sendKeys("Selenium");
        submitButton.click();

        WebElement message = driver.findElement(By.id("message"));
        String value = message.getText();
        System.out.println(value);
        assertEquals("Received!", value);

        driver.quit();
    }

    @Test
    public void oneComponents() {
        String driverPath = Thread.currentThread().getContextClassLoader().getResource("static/chromedriver.exe")
            .getPath();
        System.setProperty("webdriver.chrome.driver", driverPath);   //设置chrome驱动程序的路径
        System.out.println(System.getProperty("webdriver.chrome.driver"));
//        WebDriver driver = new ChromeDriver();
        ChromeOptions chromeOptions = new ChromeOptions();
//        182.39.6.245:38634',
//        '115.210.181.31:34301',
//            '123.161.152.38:23201',
//            '222.85.5.187:26675',
//            '123.161.152.31:23127',
//            '93.170.6.26:8080',

        String proxyServer = "123.161.152.31:23127";
//       proxy
//        Proxy proxy = new Proxy().setHttpProxy(proxyServer).setSslProxy(proxyServer);
//        chromeOptions.setProxy(proxy);
//        原文链接：https://blog.csdn.net/bohu83/article/details/108397071
//        chromeOptions.addArguments("--headless"); // TODO
        chromeOptions.addArguments("--remote-allow-origins=*");//解决 403 出错问题
        chromeOptions.addArguments("--start-maximized");
        chromeOptions.addArguments("--no-sandbox"); // TODO
        String httpUrl = "118.31.1.154:80";  // 代理IP示例 36.111.146.161:9000 118.31.1.154:80
        chromeOptions.addArguments("--proxy-server=http://" + httpUrl);
        ChromeOptions options = new ChromeOptions();
        // 开启开发者模式
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
//        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
//————————————————
//        版权声明：本文为CSDN博主「黑鱼鱼鱼鱼呀」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
//        原文链接：https://blog.csdn.net/qq_42588357/article/details/100152723
//        chromeOptions.setExperimentalOption("prefs", chromePreferences);
        String chromeExeFile = "D:\\00_Green\\Chrome100_AllNew_2022.4.4\\App\\chrome.exe";
        if (chromeExeFile != null) {
            chromeOptions.setBinary(chromeExeFile);
        }
        ChromeDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://www.zhipin.com/web/geek/job?query=Java&city=101200100&areaBusiness=420106");

        try {
            Thread.sleep(5000);  //等待页面加载完成，后续写代码注意，如果页面加载未完成，可能导致页面元素找不到
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        String title = driver.getTitle();
        System.out.println(title);
        assertEquals("「武汉武昌区Java招聘」-2023年武汉Java人才招聘信息 - BOSS直聘", title);

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(1500));

//        WebElement textBox = driver.findElement(By.name("my-text"));
       List<WebElement> submitButton = driver.findElements(By.cssSelector(".job-card-wrapper"));
        System.out.println(submitButton.size());
//
//        textBox.sendKeys("Selenium");
//        submitButton.click();
//
//        WebElement message = driver.findElement(By.id("message"));
//        String value = message.getText();
//        System.out.println(value);
//        assertEquals("Received!", value);

        driver.quit();
    }

}
