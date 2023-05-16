package com.coderdream.freeapps.util.spiderdistrict;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import java.util.Map;
import org.jsoup.nodes.Document;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.LinkedBlockingQueue;

public class WormCore {

    //Document获取层对象
    private volatile CatchPage catchPage = new CatchPage();
    //Document解析层对象
    private volatile AnalysisPage analysisPage = new AnalysisPage();
    //数据处理层对象
    public volatile AccessPage accessPage = new AccessPage();

    public void wormCore(LinkedBlockingQueue<String> urlQueue, Map<String, String> districtMap)
        throws IOException, InterruptedException {
        synchronized (this) {
            if (!urlQueue.isEmpty()) {
                String url = urlQueue.take();
                //通过Url队列中的Url抓取Document，进行Url和文本信息的抓取
                Document document = catchPage.catchDocument(url);
                //数据解析模块返回的数据（含有文本信息以及URL）
                HashMap<String, ArrayList<String>> dataMap = analysisPage.analysisDocument(document, url, districtMap);
                //数据处理模块分离出的、只含有URL的集合
                ArrayList<String> urlList = accessPage.dataAccess(dataMap);
                //定义迭代器，把抓取到的Url添加到Url队列中
                Iterator<String> iterator = urlList.iterator();

                while (iterator.hasNext()) {
                    urlQueue.put(iterator.next());
                }
                //打印URL队列中的URL条数以及队列是否为空
                System.out.println(urlQueue.size());
                System.out.println(urlQueue.isEmpty());
                //为空说明爬取完毕，由于个人技术问题，在抓取完毕之后只能强制退出
                if (urlQueue.isEmpty()) {
                    System.out.println("抓取完毕！");
                    if(CollectionUtils.isNotEmpty(districtMap)) {
                        System.out.println(districtMap.size());
                        for (Map.Entry<String, String> entry : districtMap.entrySet()) {
                            String mapKey = entry.getKey();
                            String mapValue = entry.getValue();
                            System.out.println(mapKey + "\t\t\t\t" + mapValue);
                        }
                    }
                    System.exit(1);
                }
            }
        }
    }
}
