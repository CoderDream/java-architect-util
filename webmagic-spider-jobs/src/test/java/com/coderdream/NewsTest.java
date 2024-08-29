package com.coderdream;

import com.coderdream.demos.web.NewsRepoPageProcessor;
import com.coderdream.demos.web.pojo.News;
import com.coderdream.demos.web.service.NewsService;
import com.coderdream.demos.web.service.impl.NewsServiceImpl;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NewsTest {

    @Autowired
    private NewsService newsService;

    @Test
    public void test01() {

        System.out.println("###");

        Spider.create(new NewsRepoPageProcessor())
            .addUrl("https://news.163.com/world/")//添加解析的网址
            .addPipeline(new Pipeline() {
                @Override
                public void process(ResultItems resultItems, Task task) {
                    List<String> titles = resultItems.get("titles");
                    for (String title: titles) {
                        System.err.println(title);
                        // 持久化到数据库
                        News news = new News();
                        news.setTitle(title);
                        newsService.save(news);
                    }

                }
            })
            .thread(5)//开启5个线程去访问
            .run();//运行
    }
}
