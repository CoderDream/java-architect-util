package com.coderdream.demo01;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileWriter;
import com.coderdream.Constants;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * @author CoderDream
 */
@Slf4j
public class Demo {

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        System.setProperty("webdriver.chrome.driver", Constants.DRIVER_PATH);
        WebDriver driver = null; //new ChromeDriver();
        String URL = "http://www.baidu.com";
//        URL = "https://www.zhipin.com/job_detail/fc1e039762e97c941XR53t-4FlZY.html";
//        driver.get("http://www.baidu.com");
//        driver.close();
        ChromeOptions chromeOptions = new ChromeOptions();
//        options.addArguments("--headless");
//        options.addArguments("--disable-gpu");
//        options.addArguments("--remote-debugging-port=9222");
        chromeOptions.addArguments("--remote-allow-origins=*");
        // 配置代理IP
        String proxy = "8.223.31.16\t443";
        String[] ips = proxy.split("\t");
        chromeOptions.addArguments("--proxy-server=http://" + ips[0] + ":" + ips[1] + "");
//        chromeOptions.addArguments("--proxy-server=http://116.63.129.202:6000");
//        require 'selenium-webdriver'

//        options = Selenium::WebDriver::Chrome::Options.new
//            options.add_argument('--headless')
//        options.add_argument('--disable-gpu')
//        options.add_argument('--remote-debugging-port=9222')
//        driver = Selenium::WebDriver.for :chrome, options: options

//        options.addArguments("--headless");
//        options.addArguments("--disable-gpu");

        // 启动浏览器
        driver = new ChromeDriver(chromeOptions);
        // 设置最长等待时间
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(URL);

        Thread.sleep(3000L);
        if (driver == null) {
            return;
        }
        System.out.println("#####");
        log.info("走到这里了");
        WebDriver.Options manage = driver.manage();
        Set<Cookie> cookies = manage.getCookies();
        // 检查缓存文件是否存在，如果存在则先删除再创建
        if (FileUtil.exist(Constants.TMP_COOKIE_PATH)) {
            FileUtil.del(Constants.TMP_COOKIE_PATH);
        }
        FileWriter writer = new FileWriter(Constants.TMP_COOKIE_PATH);
        for (Cookie c : cookies) {
            StringBuilder sb = new StringBuilder();
//            sb.append(c.getName() + ";");
//            sb.append(c.getValue() + ";");
//            sb.append(c.getDomain() + ";");
//            sb.append(c.getPath() + ";\n");
            log.info("获取数据=> " + sb.toString());

            if (c.getName().equals(Constants.COOKIE_KEY)) {
                System.out.println(c.getValue());
                sb.append(c.getValue() + ";");
                System.out.println("获取数据=> " + sb.toString());
                writer.append(sb.toString());
            }
        }

        driver.close();
        long period = System.currentTimeMillis() - start;
        System.out.println("#### 耗时(毫秒) ##### " + period);
    }
}
