package com.coderdream.freeapps.util.spiderdistrict;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import java.util.Map;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.ArrayList;
import java.util.HashMap;

public class AnalysisPage {

    //根据Document解析网页
    public HashMap<String, ArrayList<String>> analysisDocument(Document document, String url, Map<String, String> districtMap) {
        //因为网页上的URL为相对地址，所以在这里进行URL的拼接，这是前半部分
        String beforeUrl = url.substring(0, url.lastIndexOf("/") + 1);

        //储存文本信息的List
        ArrayList<String> stringArrayList = new ArrayList<>();
        //储存Url的List
        ArrayList<String> urls = new ArrayList<>();

        HashMap<String, ArrayList<String>> arrayList = new HashMap<>();
        //最后一个页面的前三个文本不是我们想要的
        int flag = 1;
        Elements elements = document.select("tr[class]").select("a[href]");
        //最后一个页面的处理
        if (elements.isEmpty()) {
            elements = document.select("tr[class]").select("td");
            for (Element element : elements) {
                if (!NumberUtil.isNumber(element.text()) && flag > 3) {
                    System.out.println("###1" + element.text());
                }
                flag++;
            }
            //普通页面的处理
        } else {
            String key = "";
            for (Element element : elements) {

                if (NumberUtil.isNumber(element.text())) {
                    key = element.text();
                }

                if (!NumberUtil.isNumber(element.text())) {
                    stringArrayList.add(element.text());
                    if("东城区".equals(element.text())) {
//                        System.out.println("##########");
                    }
//                    System.out.println("###2" + element.text());
                    urls.add(beforeUrl + element.attr("href"));
                    if(StrUtil.isNotEmpty(key)) {
                        districtMap.put(key, element.text());
//                        System.out.println(key + " ########## " + element.text());
                    }
                }
            }
        }
        //把文本集合和URL集合装到Map中返回
        arrayList.put("text", stringArrayList);
        arrayList.put("url", urls);
        return arrayList;
    }
}
