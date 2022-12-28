package com.example.demo.selenium;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Test1 {

    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "D:/04_GitHub/java-architect-util/auto-gen-vedio/src/main/resources/static/chromedriver.exe");   //设置chrome驱动程序的路径

        System.out.println(System.getProperty("webdriver.chrome.driver"));

        WebDriver driver = new ChromeDriver();   //初始化一个chrome驱动实例，保存到driver中
        // WebDriver driver =  = new FirefoxDriver();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); //隐式等待10秒
        //最大化窗口
        driver.manage().window().maximize();  //最大化窗口
        //设置隐性等待时间
        com.google.common.util.concurrent.SimpleTimeLimiter abc;

//        org.openqa.selenium.internal.Require r;

        driver.get("https://www.runoob.com/html/html-tutorial.html");
        Thread.sleep(3000);  //等待页面加载完成，后续写代码注意，如果页面加载未完成，可能导致页面元素找不到
        System.out.println("当前打开页面的标题是： " + driver.getTitle());
        //关闭并退出浏览器
        driver.quit();
    }
}
