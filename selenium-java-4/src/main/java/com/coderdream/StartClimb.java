package com.coderdream;

import us.codecraft.webmagic.Spider;

/**
 * https://blog.csdn.net/weixin_54129622/article/details/131271998
 * @author CoderDream
 */
public class StartClimb {

    public static void main(String[] args) {
//        com.google.common.base.Preconditions();
        ChromeDownloader downloader = new ChromeDownloader();
        BossPipeline bossPipeline = new BossPipeline();
        //声明搜索页的初始地址
        String url = "https://www.zhipin.com/web/geek/job?query=java&city=101200100";
        url = Constants.BASE_URL;
        Spider.create(new BossProcessor())
            .addUrl(url)
            //设置下载器
            .setDownloader(downloader)
            //设置输出
            .addPipeline(bossPipeline)
            .run();
    }
}
