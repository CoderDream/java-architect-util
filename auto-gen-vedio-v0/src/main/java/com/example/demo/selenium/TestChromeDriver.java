package com.example.demo.selenium;

import com.google.common.collect.ImmutableList;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * @author : MasterWei
 * @date : 2020-12-08 15:48
 * @description :
 * @params :
 * @return :
 **/
public class TestChromeDriver {

    public static void main(String[] args) throws InterruptedException {
        testHelloWord();
    }

    private static void testHelloWord() throws InterruptedException {

        // 1、通过Class的getResource方法
        String a1 = TestChromeDriver.class.getResource("/static/chromedriver.exe").getPath();

        // 2、通过本类的ClassLoader的getResource方法
        String b1 = TestChromeDriver.class.getClassLoader().getResource("static/chromedriver.exe").getPath();

        // 3、通过ClassLoader的getSystemResource方法
        String c1 = ClassLoader.getSystemClassLoader().getResource("static/chromedriver.exe").getPath();

        // 4、通过ClassLoader的getSystemResource方法
        String d1 = ClassLoader.getSystemResource("static/chromedriver.exe").getPath();

        // 5、通过Thread方式
        String e1 = Thread.currentThread().getContextClassLoader().getResource("static/chromedriver.exe").getPath();
        System.out.println(a1 + ":" + b1 + ":" + c1 + ":" + d1 + ":" + e1);

        com.google.common.util.concurrent.SimpleTimeLimiter abc;
//        com.google.common.collect.ImmutableList efg
        // "D:\\Program Files\\ChromeDriver\\chromedriver.exe"
        String driverPath = "D:\\04_GitHub\\java-architect-util\\auto-gen-vedio\\src\\main\\resources\\static\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", driverPath);
        //创建Chrome driver的实例
        WebDriver driver = new ChromeDriver();
        // 最大化浏览器
        driver.manage().window().maximize();

        //打开百度首页
//        driver.navigate().to ("https://www.baidu.com/");
        driver.get("http://www.baidu.com");

        //通过name属性找到搜索输入框
        WebElement search_input = driver.findElement(By.name("wd"));

        //在搜索输入框中输入搜索关键字"耗子尾汁"
        search_input.sendKeys("耗子尾汁");

        //递交搜索请求
        search_input.submit();

        //等待5秒后自动关闭浏览器
        Thread.sleep(5000);

        //验证搜索结果页面的标题，若匹配则关闭浏览器 );("耗子尾汁_百度搜索",
        System.out.println(driver.getTitle());

        //关闭浏览器窗口
        driver.quit();
    }
}
