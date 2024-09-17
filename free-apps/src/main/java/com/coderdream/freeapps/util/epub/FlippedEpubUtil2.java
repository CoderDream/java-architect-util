package com.coderdream.freeapps.util.epub;

import com.coderdream.freeapps.util.other.CdFileUtils;
import com.github.mertakdut.BookSection;
import com.github.mertakdut.Reader;
import com.github.mertakdut.exception.OutOfPagesException;
import com.github.mertakdut.exception.ReadingException;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.domain.MediaType;
import nl.siegmann.epublib.domain.Metadata;
import nl.siegmann.epublib.domain.Resource;
import nl.siegmann.epublib.domain.Resources;
import nl.siegmann.epublib.domain.Spine;
import nl.siegmann.epublib.domain.SpineReference;
import nl.siegmann.epublib.domain.TOCReference;
import nl.siegmann.epublib.domain.TableOfContents;
import nl.siegmann.epublib.epub.EpubReader;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

/**
 * @author CoderDream
 */
public class FlippedEpubUtil2 {

    public static void main(String[] args) throws ReadingException, OutOfPagesException {

        Map<String, List<String>> contentMap = new LinkedHashMap<>();
        String epubFilePath = "D:\\Download\\FLIPPED 怦然心动（中英双语典藏版） [书云openelib.org]-85d7.epub";
        File file = new File(
            "");

        Reader reader = new Reader();
        reader.setMaxContentPerSection(100000); // Max string length for the current page.
        reader.setIsIncludingTextContent(true); // Optional, to return the tags-excluded version.
        reader.setFullContent(epubFilePath); // Must call before readSection.

        int pageIndex = 1;
        int pageSize = 1;// 30;
        for (int i = 0; i < pageSize; i++) {
//        reader
            pageIndex = i;
            BookSection bookSection = reader.readSection(pageIndex);
            String sectionContent = bookSection.getSectionContent(); // Returns content as html.
            String sectionTextContent = bookSection.getSectionTextContent(); // Excludes html tags.
//            System.out.println("###############  " + i +  sectionTextContent);
            System.out.println("###############  " + i);
            Document doc = Jsoup.parse(sectionContent);
            Elements allElements = doc.getAllElements();
            for (Element element : allElements) {
                if(element instanceof Element) {
                    System.out.println("$$$  " + element.text());
                }
            }
        }
        return;
    }

}
