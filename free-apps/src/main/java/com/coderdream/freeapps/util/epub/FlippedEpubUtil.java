package com.coderdream.freeapps.util.epub;

import com.coderdream.freeapps.util.other.CdFileUtils;
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
public class FlippedEpubUtil {

    public static void main(String[] args) {

        Map<String, List<String>> contentMap = new LinkedHashMap<>();

        File file = new File(
            "D:\\Download\\FLIPPED 怦然心动（中英双语典藏版） [书云openelib.org]-85d7.epub");
        InputStream in = null;
        try {
            //从输入流当中读取epub格式文件
            EpubReader reader = new EpubReader();
            in = new FileInputStream(file);
            Book book = reader.readEpub(in);
            //获取到书本的头部信息
            Metadata metadata = book.getMetadata();
            System.out.println("FirstTitle为：" + metadata.getFirstTitle());
            //获取到书本的全部资源
            Resources resources = book.getResources();
            System.out.println("所有资源数量为：" + resources.size());
            //获取所有的资源数据
            Collection<String> allHrefs = resources.getAllHrefs();
            for (String href : allHrefs) {
                Resource resource = resources.getByHref(href);
                //data就是资源的内容数据，可能是css,html,图片等等
                byte[] data = resource.getData();
                // 获取到内容的类型  css,html,还是图片
                MediaType mediaType = resource.getMediaType();
            }
            //获取到书本的内容资源
            List<Resource> contents = book.getContents();
            System.out.println("内容资源数量为：" + contents.size());
            for (Resource resource : contents) {
                System.out.println("resource: " + resource);
                Document doc = Jsoup.parse(Arrays.toString(resource.getData()));
                Elements allElements = doc.getAllElements();
                for (Element element : allElements) {
                    System.out.println(element.text().toString());
                }
                System.out.println("###");
            }

            //获取到书本的spine资源 线性排序
            Spine spine = book.getSpine();
            System.out.println("spine资源数量为：" + spine.size());
            //通过spine获取所有的数据
            List<SpineReference> spineReferences = spine.getSpineReferences();
            for (SpineReference spineReference : spineReferences) {
                Resource resource = spineReference.getResource();
                //data就是资源的内容数据，可能是css,html,图片等等
                byte[] data = resource.getData();
                // 获取到内容的类型  css,html,还是图片
                MediaType mediaType = resource.getMediaType();
//                System.out.println("mediaType: " + mediaType.toString());
                Document doc = Jsoup.parse(resource.getData().toString());
//                System.out.println("doc: " + doc.text());
            }
            //获取到书本的目录资源
            TableOfContents tableOfContents = book.getTableOfContents();
            System.out.println("目录资源数量为：" + tableOfContents.size());
            //获取到目录对应的资源数据
            List<TOCReference> tocReferences = tableOfContents.getTocReferences();
            List<String> contentList = new ArrayList<>();
            String chapterParentTitle = "";
            String chapterTitle = "";
            for (TOCReference tocReference : tocReferences) {
                Resource resource = tocReference.getResource();
                //data就是资源的内容数据，可能是css,html,图片等等
                byte[] data = resource.getData();

//                byte[] bytes="123456789".getBytes();
                ByteArrayInputStream bais = new ByteArrayInputStream(data);
                DataInputStream dis = new DataInputStream(bais);
                System.out.println(dis.readByte()); //一次读1个字节,读4个字节请循环
                System.out.println(dis.readShort());//一次读2个字节，并转化成Short
                System.out.println(dis.readInt());//一次读4个字节，并转化成Int

                // 获取到内容的类型  css,html,还是图片
                MediaType mediaType = resource.getMediaType();
                Document doc = Jsoup.parse(Arrays.toString(resource.getData()));
                Elements allElements = doc.getAllElements();
                System.out.println("doc: " + doc.text());
                chapterParentTitle = tocReference.getTitle();
                System.out.println("chapterParentTitle: " + chapterParentTitle);
//                System.out.println(doc);
                int tocSize = tocReference.getChildren().size();
                System.out.println("### tocReference: \t" + tocSize);
                if (tocReference.getChildren().size() > 0) {
                    //获取子目录的内容
                    // TODO
                    List<TOCReference> tocReferenceChildren = tocReference.getChildren();
                    for (TOCReference tocReferenceLevel1 : tocReferenceChildren) {
                        System.out.println("title:\t" + tocReferenceLevel1.getTitle());

                        Resource resourceLevel1 = tocReferenceLevel1.getResource();
                        System.out.println("Level1 href:\t" + resourceLevel1.getHref());
                        //data就是资源的内容数据，可能是css,html,图片等等
                        byte[] dataLevel1 = resourceLevel1.getData();

                        String dataLevel1Str = new String(dataLevel1, "utf-8");
                        // 获取到内容的类型  css,html,还是图片
                        MediaType mediaTypeLevel1 = resourceLevel1.getMediaType();
                        Document docLevel1 = Jsoup.parse(dataLevel1Str);
//                        System.out.println("docLevel1: \t" + docLevel1.text());
                        if (docLevel1.childNodeSize() == 2) {
                            Node nodeL1 = docLevel1.childNode(1); // size =2
                            Element elementL1 = (Element) nodeL1;
                            if (elementL1.childNodeSize() == 3) {
                                Node nodeL2 = elementL1.childNode(2); // size = 3
                                Element elementL2 = (Element) nodeL2;

                                // size = 4
                                if (elementL2.childNodeSize() == 4) {
                                    Node nodeL3 = elementL2.childNode(1); // size = 3
                                    Element elementL3 = (Element) nodeL3;
                                    int size = elementL3.childNodeSize();
                                    contentList = new ArrayList<>();
                                    for (int i = 0; i < size; i++) {
                                        Node nodeL4 = elementL3.childNode(i);
                                        if (nodeL4 instanceof Element) {
                                            Element elementL4 = (Element) nodeL4;
                                            String content = elementL4.text();
                                            content = content.replaceAll("`", "'");
                                            content = content.replaceAll("\\* ", "** ");
                                            contentList.add(content);
                                            System.out.println("elementL4:\t" + content);
                                        }
                                    }
                                    String href = resourceLevel1.getHref();
                                    int beginIndex = href.lastIndexOf("/");
                                    int endIndex = href.lastIndexOf(".");
                                    if (beginIndex != -1 && endIndex != -1 && beginIndex < endIndex) {
                                        href = href.substring(beginIndex + 1, endIndex);
                                    }
                                    contentMap.put(href, contentList);
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //一定要关闭资源
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println(contentMap.size());
        Set<Entry<String, List<String>>> entries = contentMap.entrySet();

        // TODO
        for (String str : contentMap.keySet()) {
            List<String> contentList = contentMap.get(str);

            String folderPath =
                CdFileUtils.getResourceRealPath() + File.separatorChar + "output" + File.separatorChar;
            String srcFileNameCn = folderPath + str + ".md";
            // 写中文翻译文本
            CdFileUtils.writeToFile(srcFileNameCn, contentList);
        }

        return;
    }

}
