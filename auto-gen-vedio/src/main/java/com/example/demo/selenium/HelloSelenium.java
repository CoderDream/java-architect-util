package com.example.demo.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class HelloSelenium {
    public static void main(String[] args) throws MalformedURLException, InterruptedException {
        WebDriver driver  = new RemoteWebDriver(new URL("http://59.172.75.156:34444/wd/hub"), DesiredCapabilities.chrome());
//        WebDriver driver = new ChromeDriver();
        driver.get("http://www.baidu.com");
        System.out.println(driver.getTitle());
        driver.close();

//        WebDriver driver  = new RemoteWebDriver(new URL("http://59.172.75.156:34444/wd/hub"), DesiredCapabilities.chrome());
//        // 打开百度首页
//        driver.get("https://www.baidu.com");
//        // 等待 3 秒
//        Thread.sleep(3000);
//        // 关闭浏览器窗口
//        driver.close();

    }
}
