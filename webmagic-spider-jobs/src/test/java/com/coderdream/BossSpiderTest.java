package com.coderdream;

import com.coderdream.demos.web.BossProcessor;
import com.coderdream.demos.web.ChromeDownloader;
import com.coderdream.demos.web.Constants;
import com.coderdream.demos.web.pojo.BossJobDetail;
import com.coderdream.demos.web.service.BossJobDetailService;
import java.util.Arrays;
import java.util.Date;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import us.codecraft.webmagic.Spider;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BossSpiderTest {

    @Autowired
    private BossJobDetailService bossJobDetailService;

    @Test
    public void test01() {

        System.out.println("###");

//        Spider.create(new NewsRepoPageProcessor())
//            .addUrl("https://news.163.com/world/")//添加解析的网址
//            .addPipeline(new Pipeline() {
//                @Override
//                public void process(ResultItems resultItems, Task task) {
//                    List<String> titles = resultItems.get("titles");
//                    for (String title: titles) {
//                        System.err.println(title);
//                        // 持久化到数据库
//                        News news = new News();
//                        news.setTitle(title);
//                        newsService.save(news);
//                    }
//
//                }
//            })
//            .thread(5)//开启5个线程去访问
//            .run();//运行

        ChromeDownloader downloader = new ChromeDownloader();
//        BossDatabasePipeline bossPipeline = new BossDatabasePipeline();
        //声明搜索页的初始地址
        String url = "https://www.zhipin.com/web/geek/job?query=java&city=101200100";
        url = Constants.BASE_URL;
        Spider.create(new BossProcessor())
            .addUrl(url)
            //设置下载器
            .setDownloader(downloader)
            //设置输出
//            .addPipeline(bossPipeline)
            .addPipeline((resultItems, task) -> {
//                    List<String> titles = resultItems.get("bossJobDetailList");
//                    for (String title : titles) {
//                        System.err.println(title);
//                        // 持久化到数据库
//                        News news = new News();
//                        news.setTitle(title);
//                        newsService.save(news);
//                    }
                BossJobDetail bossJobDetail = resultItems.get("bossJobDetail");
                if (bossJobDetail != null) {
                    bossJobDetail.setCreateTime(new Date());
                    bossJobDetailService.insertOrUpdateBatch(Arrays.asList(bossJobDetail));
                } else {
                    System.err.println("#### bossJobDetail is null " + resultItems.toString());
                }
            })
            .run();
    }
}
