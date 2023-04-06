package com.coderdream.freeapps.util;

import cn.hutool.core.util.StrUtil;
import com.coderdream.freeapps.handler.DailyPriceHandler;
import com.coderdream.freeapps.model.FreeHistory;
import com.spreada.utils.chinese.ZHConverter;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.util.CollectionUtils;

import java.io.*;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Java按一行一行进行文件的读取或写入 https://blog.csdn.net/yuanhaiwn/article/details/83090540
 */
public class CdFileUtils {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(CdFileUtils.class);

    public static void main(String[] args) {
//        String fileName = "D:\\12_iOS_Android\\1024_data\\2022-06-29.txt";
//        List<FreeHistory> freeHistoryList = FileUtils.readFile(fileName);
//        for (FreeHistory freeHistory : freeHistoryList) {
//            System.out.println(freeHistory);
////            document = JSoupCLUtil.getDocument(url);
////            Element firstHeading = document.getElementsByClass(PC_CONTENT_CLASS).first();
////            System.out.println(firstHeading.text());
//        }

//        String folderPath = "D:\\04_GitHub\\java-architect-util\\free-apps\\src\\main\\resources\\data\\1024\\2022\\202206";
//
//
//        List<String> stringList = FileUtils.getAllFilenames(folderPath);
////        for (String str :                stringList) {
////            System.out.println(str);
////        }
        List<String> stringList = new ArrayList<>();
        String folderPath = "D:\\04_GitHub\\java-architect-util\\free-apps\\src\\main\\resources\\data\\1024\\2022";
        CdFileUtils.getFiles(folderPath, stringList);
        for (String str : stringList) {
            System.out.println(str);
        }

    }

    /**
     * 返回
     * D:\04_GitHub\java-architect-util\free-apps\src\main\resources
     *                 // https://blog.csdn.net/qq_38319289/article/details/115236819
     *                 // SpringBoot获取resources文件路径
     *                 // File directory = new File("src/main/resources");
     *                 // String reportPath = directory.getCanonicalPath();
     *                 // String resource =reportPath + "/static/template/resultTemplate.docx";
     * @return 资源文件夹路径
     */
    public static String getResourceRealPath() {
        File directory = new File("src/main/resources");
        String reportPath = "";
        try {
            reportPath = directory.getCanonicalPath();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return reportPath;
    }


    public static List<FreeHistory> getFreeHistoryFromCL(String fileName) {
        List<FreeHistory> freeHistoryList = new ArrayList<>();
        FreeHistory freeHistory;
        FileInputStream fis;
        String realFileName;
        try {
            File file = new File(fileName);
            if (!file.exists()) {
                logger.error("文件不存在：" + fileName);
                return null;
            }
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
            String title;
            String description;

            int titleIndex; //【限免软件】位置

            List<String> titleList = new ArrayList<>();
            List<String> priceList = new ArrayList<>();
            List<String> descriptionList = new ArrayList<>();
            List<String> contentList = new ArrayList<>();
            String content;
            int size = 0;
            Boolean titleFlag = true;
            Boolean descriptionFlag = false;
            Boolean priceFlag = false; // 默认中文
            String url;
            String appId;
            while ((line = br.readLine()) != null) {
                j++;
                // 繁体转简体
                ZHConverter converter = ZHConverter.getInstance(ZHConverter.SIMPLIFIED);
                line = converter.convert(line);

                contentList.add(line.trim());
                index1 = j + 11;

                if (line.startsWith("安卓手机限免软件")
                    || line.startsWith("Android")
                    || line.startsWith("https://play.google.com/")) {
                    break;
                }

                System.out.println(line);
                // 一个FreeHistory信息
                if (line.startsWith("https://apps.apple.com")) {
                    if (!CollectionUtils.isEmpty(contentList)) {
                        freeHistory = new FreeHistory();
                        url = contentList.get(contentList.size() - 1);
                        appId = CdStringUtils.parseAppId(url);

                        freeHistory.setUrlCn(Constants.URL_CN_BASE + appId);
                        freeHistory.setUrlUs(Constants.URL_US_BASE + appId);
                        freeHistory.setAppId(appId);
                        int idx = 0;
                        size = contentList.size();
                        for (int i = 0; i < size; i++) {
                            content = contentList.get(i);
                            if (titleFlag && !content.startsWith("¥")
                                && !content.startsWith("￥")
                                && !content.startsWith("$")) {
                                titleList.add(content);
                            }
                            if (descriptionFlag && !content.startsWith("https://apps.apple.com")) {
                                descriptionList.add(content);
                            }
                            idx++;
                            if (content.startsWith("限免软件")) {
                                titleList.clear();
                            }
                            // 来到价格页
                            if (content.startsWith("¥") || content.startsWith("￥")) {
                                priceList.add(content);
//                                freeHistory.setName(org.apache.commons.lang3.StringUtils.join(nameList,""));
//                                freeHistory.setPriceStr(line);
//                                descriptionList.clear();
                                titleFlag = false;
                                descriptionFlag = true;
                                priceFlag = true;
                            }

                            // 来到价格页 美元
                            if (content.startsWith("$")) {
                                priceList.add(content);
//                                freeHistory.setName(org.apache.commons.lang3.StringUtils.join(nameList,""));
//                                freeHistory.setPriceStr(line);
//                                descriptionList.clear();
                                titleFlag = false;
                                descriptionFlag = true;
                                priceFlag = false;
                            }
//                            freeHistory.setDescription(org.apache.commons.lang3.StringUtils.join(nameList,","));
                        }
                        title = StrUtil.trim(org.apache.commons.lang3.StringUtils.join(titleList, " "));
                        freeHistory.setTitle(title);
                        priceStr = org.apache.commons.lang3.StringUtils.join(priceList, "");
                        freeHistory.setPriceStr(priceStr);
                        String realPriceStr = AppStringUtils.filterPriceStr(priceStr);
                        try {
                            if (StrUtil.isNotEmpty(realPriceStr)) {
                                if (priceFlag) {
                                    freeHistory.setPriceCn(Integer.parseInt(realPriceStr));
                                } else {
                                    freeHistory.setPriceUs(BigDecimal.valueOf(Double.valueOf(realPriceStr)));
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        description = org.apache.commons.lang3.StringUtils.join(descriptionList, "");
                        freeHistory.setDescription(StrUtil.trim(description));
                        if (description.indexOf("美区限免") != -1) {
                            freeHistory.setUsFlag(true);
                        }
                        try {
                            Date freeDate = new SimpleDateFormat("yyyy-MM-dd").parse(realFileName);
                            freeHistory.setFreeDate(freeDate);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        freeHistory.setDataSource("cl"); // 设置数据来源
                        if (title.lastIndexOf("https") != -1 && description.lastIndexOf("https") != 1) {
                            freeHistory.setRemark("ERROR: " + title + ":" + description);
                        }

                        freeHistoryList.add(freeHistory);
                    }
                    titleFlag = true;
                    descriptionFlag = false;
                    titleList = new ArrayList<>();
                    priceList = new ArrayList<>();
                    descriptionList = new ArrayList<>();
                    contentList = new ArrayList<>();
                }

                // 设置日期
//                index4 = j + 7;
//                if (index4 % 12 == 0) {
//                    System.out.println("index4: " + index4 + "; text: " + text);
//                    if (text.length() > 5) {
//                        freeHistory.setPostDate(text.substring(5));
//                        freeHistoryList.add(freeHistory);
//                    }
//                }
            }
            br.close();
            isr.close();
            fis.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return freeHistoryList;
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

    public static List<String> getAllFilenames(String folderPath) {
        List<String> stringList = new ArrayList<>();
        //获取文件路径文件夹下的全部文件列表
        System.out.println("文件有如下：");
        //表示一个文件路径
        File file = new File(folderPath);
        //用数组把文件夹下的文件存起来
        File[] files = file.listFiles();
        //foreach遍历数组
        for (File file2 : files) {
            //打印文件列表：只读取名称使用getTitle();
            System.out.println("路径：" + file2.getPath());
            System.out.println("文件夹/文件名：" + file2.getName());
            stringList.add(file2.getAbsolutePath());
        }

        return stringList;
    }

    /**
     * 递归获取某路径下的所有文件，文件夹，并输出
     */
    public static void getFiles(String clientBase, List<String> stringList) {
//        List<String> stringList = new ArrayList<>();
        File file = new File(clientBase);
        // 如果这个路径是文件夹
        if (file.isDirectory()) {
            // 获取路径下的所有文件
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                // 如果还是文件夹 递归获取里面的文件 文件夹
                if (files[i].isDirectory()) {
//                    System.out.println("目录：" + files[i].getPath());
                    //继续读取文件夹里面的所有文件
                    getFiles(files[i].getPath(), stringList);
                } else {
//                    System.out.println("文件：" + files[i].getPath());
                    stringList.add(files[i].getPath());
                }
            }
        } else {
//            System.out.println("文件：" + file.getPath());
            stringList.add(file.getPath());
        }

//        return stringList;
    }

    /**
     * 通过网络地址获取文件InputStream
     *
     * @param path 地址
     * @return
     */
    public static InputStream returnBitMap(String path) {
        if (StringUtils.isBlank(path)) {
            return null;
        }
        URL url = null;
        InputStream is = null;
        try {
            url = new URL(path);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            // 利用HttpURLConnection对象,我们可以从网络中获取网页数据.
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.connect();
            // 得到网络返回的输入流
            is = conn.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return is;
    }

    public void inputStreamToFile(InputStream ins, File file) {
        OutputStream os = null;
        try {
            os = new FileOutputStream(file);

            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
