package com.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.domain.ChapterInfo;
import com.domain.NoteInfo;

public class Utils {

    //获取html的doc   @1
    public static Document GetDocument(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        return doc;
    }
    /*
     * 获取每本小说的信息
     */

    //获取小说名传入排行榜页面的url     @2		调用@1
    public static List<NoteInfo> getNoteName(Document doc) {
        List<NoteInfo> noteList = new ArrayList<>(); // novelslist2
//        Element element = doc.select("ul.item-con").first();

//        Elements elements = element.select("a[href*=index.html]");
//        Element element =

        Elements elementsA = doc.getElementsByClass("novelslist2");

        if (elementsA.size() > 0) {
            Element elementA = elementsA.get(0);
            if (elementA.childNodeSize() == 7) {
                Element elementB = (Element) elementA.childNode(3);
                int size = elementB.childNodeSize();
                int elementSize = size /2;
                if (size > 0) {
                    Element elementC;
                    for (int i = 1; i < elementSize; i++) {
                        if (i < elementSize) {
                            System.out.println("i: " + i + "; elementSize: " + elementSize);
                            elementC = elementB.child(i);
                            if (elementC != null) {
                                System.out.println(elementC.text());
                            }
//                            i++;
                        }
                    }
                }
            }

        }

        Elements elementsB = doc.getElementsByClass("novelslist2");

        NoteInfo eachNoteInfo;
//        for (Element e : elements) {
//            eachNoteInfo = new NoteInfo();
//            eachNoteInfo.setBaseUrl(e.attr("href"));
//            eachNoteInfo.setNoteName(e.text());
//            eachNoteInfo.setNoteAuthor(e.parent().parent().select("span.s3").text());
//            noteList.add(eachNoteInfo);
//        }
        return noteList;
    }

    //单本小说每个章节信息.     这个直接用在了WriteToTxt中了。
    public static List<ChapterInfo> getChapterInfo(List<NoteInfo> notelist) throws IOException {
        List<ChapterInfo> chapList = new ArrayList<ChapterInfo>();
        for (NoteInfo note : notelist) {

            //获取index页面根节点
            Document document = GetDocument(note.getBaseUrl());

            //获取章节信息的父节点后获取章节节点。
            Elements element = document.select("dl.chapterlist").first().select("a[href$=.html]");

            //拼接chapterUrl字符串。
            String url1 = note.getBaseUrl().replace("index.html", "");
            String url2 = url1;

            for (Element e : element) {
                ChapterInfo cInfo = new ChapterInfo();
                url2 = url1;
                url2 += e.attr("href");

                //给chapterUrl和chapterName赋值
                cInfo.setChapterName(e.text());
                cInfo.setChapterUrl(url2);

                //获取chapterText
                Document document2 = GetDocument(url2);

                //控制txt文本的格式。
                String list = "  ";
                Element element3 = document2.getElementById("BookText");
                String[] s = element3.text().split(" ");
                for (String l : s) {
                    list += l + "\r\n  ";
                    cInfo.setChapterText(list);
                }
                chapList.add(cInfo);
            }
            System.out.println("finish");
        }

        return chapList;
    }
}
