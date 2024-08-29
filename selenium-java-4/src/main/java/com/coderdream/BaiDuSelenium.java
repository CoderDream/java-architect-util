package com.coderdream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * @author CoderDream
 */
public class BaiDuSelenium {

    public static void main(String[] args) {
        //指定驱动路径
        System.setProperty("webdriver.chrome.driver", Constants.DRIVER_PATH);
//        System.setProperty("webdriver.chrome.whitelistedIps", "183.2.172.42");
        // 谷歌驱动
        ChromeOptions options = new ChromeOptions();
        // 允许所有请求
        options.addArguments("--remote-allow-origins=*");
//        options.addArguments("--remote-allow-origins=*");

        WebDriver webDriver = new ChromeDriver(options);
        // 2.打开百度首页
        webDriver.get("https://www.baidu.com");
        // 3.获取输入框，输入selenium
        webDriver.findElement(By.id("kw")).sendKeys("selenium");
        // 4.获取“百度一下”按钮，进行搜索
        webDriver.findElement(By.id("su")).click();
    }
}
