package com.main;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.jsoup.nodes.Document;

import com.domain.NoteInfo;
import com.utils.Utils;

/**
 * 基于Jsoup的多线程java小说爬虫
 * https://blog.csdn.net/a2331739756/article/details/79405091
 */
public class Spider {

    public static void main(String[] args) throws IOException {
        List<NoteInfo> noteList = new ArrayList<NoteInfo>();
        String url = "https://www.kanshugee.com/hot/allvisit.html";
        // "https://www.kanshugee.com/2128/18181287.html"
        Document doc = Utils.GetDocument(url);
        noteList = Utils.getNoteName(doc);
        System.out.println(noteList.size());
        ExecutorService pool = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 100; i++) {
            pool.execute(new Thread(new WriteToTxt(noteList.get(i))));
        }
        pool.shutdown();
    }
}
