package com.coderdream.autogenvedio.util;

import cn.hutool.core.util.StrUtil;
import com.coderdream.autogenvedio.dto.AppReqDto;
import com.coderdream.autogenvedio.entity.PageInfoEntity;
import org.springframework.util.CollectionUtils;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Java按一行一行进行文件的读取或写入
 * https://blog.csdn.net/yuanhaiwn/article/details/83090540
 */
public class FileUtils {

    public static String URL_US_BASE = "https://apps.apple.com/us/app/";

    public static String URL_CN_BASE = "https://apps.apple.com/cn/app/";

    public static void main(String[] args) {
        String fileName = "D:\\12_iOS_Android\\1024_data\\2022-06-29.txt";
        List<AppReqDto> appReqDtoList = FileUtils.readFile(fileName);
        for (AppReqDto pageInfoEntity : appReqDtoList) {
            System.out.println(pageInfoEntity);
//            document = JSoupCLUtil.getDocument(url);
//            Element firstHeading = document.getElementsByClass(PC_CONTENT_CLASS).first();
//            System.out.println(firstHeading.text());
        }
    }


    public static List<AppReqDto> readFile(String fileName) {
        List<AppReqDto> appReqDtoList = new ArrayList<>();
        AppReqDto appReqDto = new AppReqDto();
        FileInputStream fis = null;
        String realFileName;
        try {
            File file = new File(fileName);
            realFileName = file.getName();
            if (StrUtil.isNotEmpty(realFileName)) {
                int indexDot = realFileName.indexOf(".");
                realFileName = realFileName.substring(0, indexDot);
            }
            System.out.println("###" + realFileName);
            fis = new FileInputStream(file);

            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            //简写如下
            //BufferedReader br = new BufferedReader(new InputStreamReader(
            //        new FileInputStream("E:/phsftp/evdokey/evdokey_201103221556.txt"), "UTF-8"));
            String line = "";
            String[] arrs = null;
            int j = 0;
            int index1; // 新建对象
            int index2; // 名称
            int index3; // URL
            int index4; // 日期
            String text = "";
            String priceStr;
            String name;
            String description;

            int titleIndex; //【限免软件】位置

            List<String> nameList = new ArrayList<>();
            List<String> priceList = new ArrayList<>();
            List<String> descriptionList = new ArrayList<>();
            List<String> contentList = new ArrayList<>();
            String content;
            int size = 0;
            Boolean nameFlag = true;
            Boolean descriptionFlag = false;
            Boolean priceFlag = false; // 默认中文
            String url;
            String appId;
            while ((line = br.readLine()) != null) {
                j++;
                System.out.println(line);

                contentList.add(line.trim());
                index1 = j + 11;
                // 一个App信息
                if (line.startsWith("https://apps.apple.com")) {
                    if (!CollectionUtils.isEmpty(contentList)) {
                        appReqDto = new AppReqDto();
                        url = contentList.get(contentList.size() - 1);
                        appId = StringUtils.parseAppId(url);

                        appReqDto.setUrlCn(URL_CN_BASE + appId);
                        appReqDto.setUrlUs(URL_US_BASE + appId);
                        appReqDto.setAppId(appId);
                        int idx = 0;
                        size = contentList.size();
                        for (int i = 0; i < size; i++) {
                            content = contentList.get(i);
                            if (nameFlag && !content.startsWith("¥") && !content.startsWith("￥")) {
                                nameList.add(content);
                            }
                            if (descriptionFlag && !content.startsWith("https://apps.apple.com")) {
                                descriptionList.add(content);
                            }
                            idx++;
                            if (content.startsWith("限免软件")) {
                                nameList.clear();
                            }
                            // 来到价格页
                            if (content.startsWith("¥") || content.startsWith("￥")) {
                                priceList.add(content);
//                                appReqDto.setName(org.apache.commons.lang3.StringUtils.join(nameList,""));
//                                appReqDto.setPriceStr(line);
//                                descriptionList.clear();
                                nameFlag = false;
                                descriptionFlag = true;
                                priceFlag = true;
                            }

                            // 来到价格页 美元
                            if (content.startsWith("$")) {
                                priceList.add(content);
//                                appReqDto.setName(org.apache.commons.lang3.StringUtils.join(nameList,""));
//                                appReqDto.setPriceStr(line);
//                                descriptionList.clear();
                                nameFlag = false;
                                descriptionFlag = true;
                                priceFlag = true;
                            }
//                            appReqDto.setDescription(org.apache.commons.lang3.StringUtils.join(nameList,","));
                        }

                        appReqDto.setName(org.apache.commons.lang3.StringUtils.join(nameList, " "));
                        priceStr = org.apache.commons.lang3.StringUtils.join(priceList, "");
                        appReqDto.setPriceStr(priceStr);
                        String realPriceStr = AppStringUtils.filterPriceStr(priceStr);
                        try {
                            if (priceFlag) {
                                appReqDto.setPriceCn(Integer.parseInt(realPriceStr));
                            } else {
                                appReqDto.setPriceUs(Double.parseDouble(realPriceStr));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        description = org.apache.commons.lang3.StringUtils.join(descriptionList, "");
                        appReqDto.setDescription(description);
                        if (description.indexOf("美区限免") != -1) {
                            appReqDto.setUsFlag(true);
                        }
                        try {
                            Date freeDate = new SimpleDateFormat("yyyy-MM-dd").parse(realFileName);
                            appReqDto.setFreeDate(freeDate);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        appReqDtoList.add(appReqDto);
                    }
                    nameFlag = true;
                    descriptionFlag = false;
                    nameList = new ArrayList<>();
                    priceList = new ArrayList<>();
                    descriptionList = new ArrayList<>();
                    contentList = new ArrayList<>();
                }

                // 设置日期
//                index4 = j + 7;
//                if (index4 % 12 == 0) {
//                    System.out.println("index4: " + index4 + "; text: " + text);
//                    if (text.length() > 5) {
//                        appReqDto.setPostDate(text.substring(5));
//                        appReqDtoList.add(appReqDto);
//                    }
//                }
            }
            br.close();
            isr.close();
            fis.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return appReqDtoList;
    }

    public static List<PageInfoEntity> readFileToPageInfoEntity() {
        List<PageInfoEntity> pageInfoEntityList = new ArrayList<>();
        PageInfoEntity pageInfoEntity = new PageInfoEntity();
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("D:\\04_GitHub\\java-architect-util\\auto-gen-video\\page1.txt");

            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            //简写如下
            //BufferedReader br = new BufferedReader(new InputStreamReader(
            //        new FileInputStream("E:/phsftp/evdokey/evdokey_201103221556.txt"), "UTF-8"));
            String line = "";
            String[] arrs = null;
            int j = 0;
            int index1; // 新建对象
            int index2; // 名称
            int index3; // URL
            int index4; // 日期
            String text = "";
            while ((line = br.readLine()) != null) {
                j++;
//                System.out.println(line);
                arrs = line.split("\t");
                if (arrs.length == 2) {
                    text = arrs[1];
//                    System.out.println(text);
                }


                index1 = j + 11;
                if (index1 % 12 == 0) {
                    pageInfoEntity = new PageInfoEntity();
                }

                // 设置标题
                index2 = j + 9;
                if (index2 % 12 == 0) {
                    System.out.println("index2: " + index2 + "; text: " + text);
                    pageInfoEntity.setPageTitle(text);
                }

                // 设置url url:
                index3 = j + 8;
                if (index3 % 12 == 0) {
                    System.out.println("index3: " + index3 + "; text: " + text);
                    if (text.length() > 5) {
                        pageInfoEntity.setPageUrl(text.substring(5));
                    }
                }

                // 设置日期
                index4 = j + 7;
                if (index4 % 12 == 0) {
                    System.out.println("index4: " + index4 + "; text: " + text);
                    if (text.length() > 5) {
                        pageInfoEntity.setPostDate(text.substring(5));
                        pageInfoEntityList.add(pageInfoEntity);
                    }
                }

//                if (StrUtil.isNotEmpty(pageInfoEntity.getPageTitle())
//                        && Character.isDigit(pageInfoEntity.getPageTitle().charAt(0))
//                        && pageInfoEntity.getPostDate() != null
//                        && index4 % 12 == 0) {
//                    pageInfoEntityList.add(pageInfoEntity);
//                }
            }
            br.close();
            isr.close();
            fis.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return pageInfoEntityList;
    }


    public static void writeToFile(String fileName, List<String> contentList) {
        try {
//            String[] arrs = {
//                    "zhangsan,23,福建",
//                    "lisi,30,上海",
//                    "wangwu,43,北京",
//                    "laolin,21,重庆",
//                    "ximenqing,67,贵州"
//            };
            //写入中文字符时解决中文乱码问题
            FileOutputStream fos = null;

//            fos = new FileOutputStream(new File("E:/phsftp/evdokey/evdokey_201103221556.txt"));

            fos = new FileOutputStream(fileName);

            OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
            BufferedWriter bw = new BufferedWriter(osw);
            //简写如下：
            //BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
            //        new FileOutputStream(new File("E:/phsftp/evdokey/evdokey_201103221556.txt")), "UTF-8"));

            for (String arr : contentList) {
                bw.write(arr + "\t\n");
            }

            //注意关闭的先后顺序，先打开的后关闭，后打开的先关闭
            bw.close();
            osw.close();
            fos.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static void writeToFile(String fileName, String content) {
        try {
//            String[] arrs = {
//                    "zhangsan,23,福建",
//                    "lisi,30,上海",
//                    "wangwu,43,北京",
//                    "laolin,21,重庆",
//                    "ximenqing,67,贵州"
//            };
            String[] contentList = content.split(" ");
            //写入中文字符时解决中文乱码问题
            FileOutputStream fos = null;

//            fos = new FileOutputStream(new File("E:/phsftp/evdokey/evdokey_201103221556.txt"));

            fos = new FileOutputStream(fileName);

            OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
            BufferedWriter bw = new BufferedWriter(osw);
            //简写如下：
            //BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
            //        new FileOutputStream(new File("E:/phsftp/evdokey/evdokey_201103221556.txt")), "UTF-8"));

            for (String arr : contentList) {
                bw.write(arr + "\t\n");
            }

            //注意关闭的先后顺序，先打开的后关闭，后打开的先关闭
            bw.close();
            osw.close();
            fos.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
