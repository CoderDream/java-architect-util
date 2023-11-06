package com.coderdream.freeapps.util.demo01;

/*
 * author:合肥工业大学 管院学院 钱洋
 *1563178220@qq.com
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.nodes.Document;
import com.alibaba.fastjson.JSON;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MtimeParse {

    public static List<MtimeModel> getData(Document document) {
        List<MtimeModel> mtimeData = new ArrayList<MtimeModel>();
        //获取待解析的html文件
        String className = "information-list__item l-column small-12 medium-6 large-4 small-valign-top";

        List<String> contentListByClass = getContentListByClass(document, className);

//        String html = doc.html();
//        System.out.println(html);
//        //fastJson测试
//        //just contain the preview
//        List<JsonModel> mtimeJsonData = new ArrayList<JsonModel>();
//        Pattern data1 = Pattern.compile("预告片\":(.*?)\\,(\"拍摄花絮|\"精彩片段)");
//        Matcher dataMatcher1 = data1.matcher(html);
//        String da1 = "";
//        while (dataMatcher1.find()) {
//            //待解析的json字符串
//            da1 = dataMatcher1.group(1);
//        }
//        if (da1.length() != 0) {
//            List<JsonModel> jsonmodel1 = JSON.parseArray(da1, JsonModel.class);
//            for (JsonModel jso : jsonmodel1) {
//                JsonModel mtimeModel = new JsonModel();
//                String VideoID = "mtime" + jso.getVideoID();
//                String MovieID = "mtime" + jso.getMovieID();
//                String ShortTitle = jso.getShortTitle();
//                String url = "http://video.mtime.com/" + jso.getVideoID() + "/?mid" + jso.getMovieID();
//                mtimeModel.setPrmovieId(VideoID);
//                mtimeModel.setUrl(url);
//                mtimeModel.setMovieID(MovieID);
//                mtimeModel.setShortTitle(ShortTitle);
//                mtimeJsonData.add(mtimeModel);
//            }
//        }

        return mtimeData;
    }

    public static List<String> getContentListByClass(Document document, String className) {
//        Elements elementList = document.getElementsByClass(className);
//        Element element = document.getElementsByClass(className).first();
//        System.out.println("first: " +  element.text());
//        Element element;
//        int index = 0;
//        do {
//            index++;
////            Elements elements = elementList.first();
//            element = elementList.first();
//            System.out.println(element.toString());
//            System.out.println("index: " + index + ": " + element.text());
//        } while (elementList.next() != null);

        List<String> stringList = new ArrayList<>();
        Elements elements = document.getElementsByClass(className);

        //获取迭代器
        int childNodeSize = 0;
        String tempText="";
        Iterator it = elements.iterator();
        while (it.hasNext()) {
            Element element = (Element) it.next();
//            Node titleNode = element.child(0).childNode(0).childNode(0);
//            Node titleNode1 = element.child(1).childNode(0);
//            stringList.add(titleNode.toString());
//            stringList.add(titleNode1.toString());
//            System.out.println(element.toString());
            //if(element.text())
            childNodeSize = element.childNodeSize();
            if (childNodeSize > 0) {
                for (Element element1 : element.children()) {
                    if(tempText.equals("Price") ||tempText.equals("价格")) {
                        System.out.println(element1.text());
                    }
                    tempText = element1.text();
                }

            }
            stringList.add(element.toString());
            //遍历中。。。。。。
        }
        return stringList;

//        System.out.println(element.text());
//        return new ArrayList<>();
    }

}
