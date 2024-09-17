package com.coderdream.freeapps.util.epub;

import cn.hutool.core.io.FileUtil;
import com.github.mertakdut.BookSection;
import com.github.mertakdut.Reader;
import com.github.mertakdut.exception.OutOfPagesException;
import com.github.mertakdut.exception.ReadingException;
import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author CoderDream
 */
public class FlippedEpubUtil3 {

    public static void main(String[] args) throws ReadingException, OutOfPagesException {

        String cnFileName = "D:\\Download\\怦然心动.cn.txt";
        String enFileName = "D:\\Download\\怦然心动.en.txt";

        List<String> stringsCn = FileUtil.readUtf8Lines(cnFileName);
        List<String> stringsEn = FileUtil.readUtf8Lines(enFileName);

        int size = Math.min(stringsCn.size(), stringsEn.size());
        int start = 536;
        for (int i = start; i < size; i++) {
            System.out.println(i);
            System.out.println(stringsEn.get(i));
            System.out.println(stringsCn.get(i));
            System.out.println();
        }

        return;
    }

}
