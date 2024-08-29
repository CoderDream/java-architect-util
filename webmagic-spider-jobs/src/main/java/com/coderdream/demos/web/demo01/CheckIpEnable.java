package com.coderdream.demos.web.demo01;

import cn.hutool.core.util.StrUtil;
import com.coderdream.demos.web.Constants;
import com.coderdream.demos.web.util.FileUtils;
import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * @author CoderDream
 */
@Slf4j
public class CheckIpEnable {

    public static void main(String[] args) throws InterruptedException {
        String ipFileName = FileUtils.getResourceRealPath() + File.separatorChar + "raw_ip.txt";
        List<String> strings = FileUtils.readFileContent(ipFileName);
        for (String str : strings) {
            String[] ips = str.split("\t");
            check(ips[0], ips[1]);
        }

    }

    public static void check(String ip, String port) {
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
        if (StrUtil.isNotEmpty(ip) && StrUtil.isNotEmpty(port)) {
            chromeOptions.addArguments("--proxy-server=http://" + ip + ":" + port);
        }
//        chromeOptions.addArguments("--proxy-server=http://116.63.129.202:6000");


//        options.addArguments("--headless");
//        options.addArguments("--disable-gpu");

        // 启动浏览器
        driver = new ChromeDriver(chromeOptions);
        // 设置最长等待时间
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(URL);

        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if (driver == null) {
            return;
        }
        System.out.println("#####");
        log.info("走到这里了");
        String url = driver.getCurrentUrl();
        String title = driver.getTitle();
        System.out.println(url+":" + title);
        if (url.equals("https://www.baidu.com/") && title.equals("百度一下，你就知道")) {
            System.out.println("当前页面url：" + url + "，当前页面title：" + title);
            System.out.println("测试通过：\t" + ip + "\t" + port);
        }

        driver.close();
        long period = System.currentTimeMillis() - start;
        System.out.println("#### 耗时(毫秒) ##### " + period);
    }
}
