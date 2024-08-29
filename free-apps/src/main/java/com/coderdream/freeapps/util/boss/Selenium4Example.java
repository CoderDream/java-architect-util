package com.coderdream.freeapps.util.boss;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Selenium4Example {
    public static void main(String[] args) {
        // 初始化WebDriver实例，使用Chrome浏览器

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
        chromeOptions.setBinary("D:\\00_Green\\Chrome122_AllNew_2024.2.23\\App\\chrome.exe");
//        chromeOptions.addArguments("--window-size=3840,1916"); // 3840 1916 1920,
//        chromeOptions.addArguments("--window-size=3840,1916"); // 3840 1916 1920,1050

        // 创建代理对象
        Proxy proxy = new Proxy();
        // 设置代理IP和端口
        proxy.setHttpProxy("114.231.82.123:8888");
        // 将代理设置添加到 Chrome 选项中
        chromeOptions.setCapability("proxy", proxy);
        WebDriver driver = new ChromeDriver(chromeOptions);   //初始化一个chrome驱动实例，保存到driver中

        // 访问 Google首页
        String url = "https://www.zhipin.com/job_detail/62a806a7a0bffe9c1nJ40t68FFM~.html";
        driver.get(url);
        // 等待3秒以查看搜索结果页面
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 获取登录后的所有 Cookie
        Set<Cookie> cookies = driver.manage().getCookies();
        System.out.println("BOSS 直聘登录后的所有 Cookies: ");
        for (Cookie cookie : cookies) {
            System.out.println(cookie.toString());
        }
        // 等待3秒以查看搜索结果页面
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 找到搜索框并输入“Selenium 4” detail-section-item company-info-box
//        WebElement name =  driver.findElement(By.className("name"));
//        driver.findElement(By.cssSelector(".className"));
//        driver.findElement(By.id("elementId"));
//        driver.findElement(By.linkText("linkText"));
//        driver.findElement(By.name("elementName"));
//        driver.findElement(By.partialLinkText("partialText"));
//        driver.findElement(By.tagName("elementTagName"));
//        driver.findElement(By.xpath("xPath"));

        // Get element with tag name 'div'
        WebElement element = driver.findElement(By.tagName("div"));

        // Get all the elements available with tag name 'p'
        List<WebElement> elements = element.findElements(By.tagName("p"));
        for (WebElement e : elements) {
            System.out.println(e.getText());
        }


//        WebElement name = driver.findElement(By.className("name"));//通过class name定位到input标签上面。
//        System.out.println("####name: " + name);
//        WebElement webElement = driver.findElement(By.className("detail-section-item company-info-box"));
//        System.out.println("webElement: " + webElement);
//        List<WebElement> webElementList = driver.findElements(By.cssSelector(".job-body-wrapper .job-sec-text"));
//        if(CollectionUtils.isNotEmpty(webElementList)) {
//            System.out.println(webElementList.size());
//        } else {
//            System.out.println("#####");
//        }
//        searchBox.sendKeys("Selenium 4");
//
//        // 提交搜索
//        searchBox.submit();

        // 等待3秒以查看搜索结果页面
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        // 关闭驱动器并结束进程
        driver.quit();
    }
}
