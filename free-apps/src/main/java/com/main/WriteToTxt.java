package com.main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.domain.ChapterInfo;
import com.domain.NoteInfo;
import com.utils.Utils;

public class WriteToTxt implements Runnable {

    private NoteInfo note;
    List<ChapterInfo> chapList = new ArrayList<ChapterInfo>();

    public WriteToTxt(NoteInfo note) throws IOException {
        this.note = note;
    }

    @Override
    public void run() {
        try {
            // 创建文件
            File file = new File("e:/aaa/" + note.getNoteName() + ".txt");
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                file.delete();
                System.out.println("aaa");
            }
            FileWriter fW = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fW);
            bw.write("   " + note.getNoteName() + "\r\n");
            bw.write("   " + note.getNoteAuthor() + "\r\n");
            bw.write("***copy@by nice***\r\n");
            Document document = Utils.GetDocument(note.getBaseUrl());
            // 获取章节信息的父节点后获取章节节点。
            Elements element = document.select("dl.chapterlist").first().select("a[href$=.html]");

            // 拼接chapterUrl字符串。
            String url1 = note.getBaseUrl().replace("index.html", "");
            String url2 = url1;
            for (Element e : element) {
                ChapterInfo cInfo = new ChapterInfo();
                url2 = url1;
                url2 += e.attr("href");

                // 给chapterUrl和chapterName赋值
                cInfo.setChapterName(e.text());
                cInfo.setChapterUrl(url2);

                // 获取chapterText
                Document document2 = Utils.GetDocument(url2);

                // 控制txt文本的格式。
                String list = "  ";
                Element element3 = document2.getElementById("BookText");
                String[] s = element3.text().split(" ");
                for (String l : s) {
                    list += l + "\r\n  ";
                    cInfo.setChapterText(list);
                }
                chapList.add(cInfo);
            }

            for (ChapterInfo c : chapList) {
                bw.write(c.getChapterName() + "\r\n");
                bw.write(c.getChapterText() + "\r\n");
            }
            bw.close();
            fW.close();
        } catch (IOException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
    }
}
