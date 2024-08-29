package com.coderdream;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.downloader.Downloader;
import us.codecraft.webmagic.selector.PlainText;

/**
 * @author CoderDream
 */
public class ChromeDownloader implements Downloader {

    //声明驱动
    private RemoteWebDriver driver;

    public ChromeDownloader() {
        //第一个参数是使用哪种浏览器驱动
        //第二个参数是浏览器驱动的地址
        System.setProperty("webdriver.chrome.driver", Constants.DRIVER_PATH);

        //创建浏览器参数对象
        ChromeOptions chromeOptions = new ChromeOptions();

        // chromeOptions.addArguments("--headless");
        // 设置浏览器窗口打开大小
        chromeOptions.addArguments("--window-size=1280,700");
//        chromeOptions.setExperimentalOption("debuggerAddress", "127.0.0.1:7890"); // 117.28.219.226
//        chromeOptions.setExperimentalOption("debuggerAddress", "106.4.31.132:5424"); // 117.28.219.226 106.4.31.132	5424
        chromeOptions.addArguments("--remote-allow-origins=*");
        // 配置代理IP
//        chromeOptions.addArguments("--proxy-server=http://proxy-ip:port");

        //创建驱动
        this.driver = new ChromeDriver(chromeOptions);
    }

    @Override
    public Page download(Request request, Task task) {
        try {
            driver.get(request.getUrl());
            Thread.sleep(3500);

            //无论是搜索页还是详情页,都滚动到页面底部,所有该加载的资源都加载
            driver.executeScript("window.scrollTo(0, document.body.scrollHeight - 1000)");
            Thread.sleep(3500L);

            //获取页面对象
            Page page = createPage(request.getUrl(), driver.getPageSource());

            //关闭浏览器
            //driver.close();

            return page;

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void setThread(int i) {

    }

    //构建page返回对象
    private Page createPage(String url, String content) {
        Page page = new Page();
        page.setRawText(content);
        page.setUrl(new PlainText(url));
        page.setRequest(new Request(url));
        page.setDownloadSuccess(true);

/*        System.out.println("==============page内容===============");
        System.out.println("1."+content);
        System.out.println("2."+new PlainText(url));
        System.out.println("3."+new Request(url));
        System.out.println("=====================================");*/
        return page;
    }
}
