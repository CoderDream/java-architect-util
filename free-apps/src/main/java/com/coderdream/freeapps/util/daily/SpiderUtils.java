package com.coderdream.freeapps.util.daily;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

public class SpiderUtils implements PageProcessor {


        private Site site = Site.me()
            .setRetryTimes(3)
            .setRetrySleepTime(3000)
            .setSleepTime(1000)
            .setTimeOut(3000);

        public void process(Page page) {

            // 创建ChromeDriver实例对象
            ChromeDriver driver = new ChromeDriver();
            // 去模拟浏览器输入url后敲回车
            driver.get(page.getUrl().toString());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 获取body下的标签内容
            WebElement webElement = driver.findElement(By.tagName("body"));
            // 模拟点击事件，因为有的时候不通过一些外设操作有些html代码是不会出现的，就爬不了了
            WebElement element = webElement.findElement(By.cssSelector("span[event-type='15']"));
            element.click();

            // 不知道是不是多线程run的原因，这里点击完要等一会儿，不然后面获取点击后的代码没有
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            // 获取其body下的原始html字符串，只含指定webElement下的内容
            String str = webElement.getAttribute("outerHTML");

            // 将上面得出来的字符串转换成Html对象
            // 其构造生成的是通过 Jsoup 解析对Html对象内部属性document进行初始化的
            Html html = new Html(str);
            System.out.println(html.xpath("//tbody/tr").all());
            // 关闭驱动，退出驱动
            driver.close();
            driver.quit();

        }

        public Site getSite() {
            return site;
        }

        public static void main(String[] args) {
            Spider.create(new SpiderUtils())
                .addUrl("https://www.zhipin.com/web/geek/job?query=Java&city=101200100&areaBusiness=420106")
                .thread(5)
                .run();
        }
    }
