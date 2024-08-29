package com.coderdream.freeapps.util.boss.demo01;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileWriter;
import cn.hutool.core.util.CharsetUtil;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

@Slf4j
//@Service
public class EdgeTestService {

    //    private final String DRIVER_PATH = "src/main/resources/msedgedriver.exe";
    private final String DRIVER_PATH =  "D:\\Download\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe";// "src/main/resources/chromedriver-120.exe";
    private final String TMP_COOKIE_PATH = "src/main/resources/tmpcookie.txt";
    private final String URL = "https://www.zhipin.com/web/geek/job?query=Java&city=101200100"; // "https://www.baidu.com/";
    //    private final String URL = "https://www.jd.com/?cu=true";
    private WebDriver driver = null;


    public void start() {
        System.setProperty("webdriver.chrome.driver", DRIVER_PATH);
        System.setProperty("webdriver.chrome.whitelistedIps", "");
//        EdgeOptions options = new EdgeOptions();
        ChromeOptions options = new ChromeOptions();
        //options.setExperimentalOption("debuggerAddress", "127.0.0.1:9222");
        options.setExperimentalOption("debuggerAddress", "127.0.0.1:7890");
        options.addArguments("--remote-allow-origins=*");

        // 启动浏览器
        driver = new ChromeDriver(options);
        // 设置最长等待时间
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(URL);
    }

    public void save() {
        if (driver == null) {
            return;
        }
        log.info("走到这里了");
        WebDriver.Options manage = driver.manage();
        Set<Cookie> cookies = manage.getCookies();
        // 检查缓存文件是否存在，如果存在则先删除再创建
        if (FileUtil.exist(TMP_COOKIE_PATH)) {
            FileUtil.del(TMP_COOKIE_PATH);
        }
        FileWriter writer = new FileWriter(TMP_COOKIE_PATH);
        for (Cookie c : cookies) {
            StringBuilder sb = new StringBuilder();
            sb.append(c.getName() + ";");
            sb.append(c.getValue() + ";");
            sb.append(c.getDomain() + ";");
            sb.append(c.getPath() + ";\n");
            log.info("获取数据=> " + sb.toString());
            writer.append(sb.toString());
        }
    }

    public void reload() {
        if (!FileUtil.exist(TMP_COOKIE_PATH)) {
            log.error(TMP_COOKIE_PATH + "文件不存在");
            return;
        }
        System.setProperty("webdriver.chrome.driver", DRIVER_PATH);
        System.setProperty("webdriver.chrome.whitelistedIps", "");
//        EdgeOptions options = new EdgeOptions();
        ChromeOptions options = new ChromeOptions();
        //options.setExperimentalOption("debuggerAddress", "127.0.0.1:9222");
//        options.setExperimentalOption("debuggerAddress", "127.0.0.1:7890");
        options.addArguments("--remote-allow-origins=*");
        options.setBinary("D:\\00_Green\\Chrome122_AllNew_2024.2.23\\App\\chrome.exe");
        // 启动浏览器
        driver = new ChromeDriver(options);
        // 设置最长等待时间
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get(URL);
        // 加载cookies
        List<String> lines = FileUtil.readLines(TMP_COOKIE_PATH, CharsetUtil.CHARSET_UTF_8);
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            List<String> tmplist = Arrays.asList(line.split(";"));
            Date expire = new Date(System.currentTimeMillis() + 60 * 1000 * 15);
            Cookie cookie = new Cookie(tmplist.get(0), tmplist.get(1), tmplist.get(2), tmplist.get(3), expire);
            log.info("加载cookie=> [name=" + tmplist.get(0) + "] pvalue=" + tmplist.get(1) +
                "] [domain=" + tmplist.get(2) + "] [path=" + tmplist.get(3) + "]" + " [expire=" + expire.toString()
                + "]");
            driver.manage().addCookie(cookie);

        }
        driver.manage().window().maximize();
        driver.get(URL);
    }

    public void close() {
        if (driver != null) {
            driver.close();
        }
    }

    public static void main(String[] args) {
        EdgeTestService edgeTestService = new EdgeTestService();
        edgeTestService.start();
        edgeTestService.save();
        edgeTestService.close();
    }
}
