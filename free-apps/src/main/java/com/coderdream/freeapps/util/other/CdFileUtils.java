package com.coderdream.freeapps.util.other;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.coderdream.freeapps.model.FreeHistory;
import com.coderdream.freeapps.model.SubtitleBaseEntity;
import com.coderdream.freeapps.model.TopicEntity;
import com.coderdream.freeapps.util.bbc.BbcConstants;
import com.coderdream.freeapps.util.bbc.CommonUtil;
import com.coderdream.freeapps.util.bbc.ScriptEntity;
import com.spreada.utils.chinese.ZHConverter;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;


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
//+

//        String temp = "EPISODE 231005 / 05 OCT 2023";
//        System.out.println(temp.substring(8, 14));
        String fileName = BbcConstants.ROOT_FOLDER_NAME + "all.txt";
        List<TopicEntity> topicEntityList = CdFileUtils.genTopicEntityList(fileName);
        String year;
        String shortYear;
        Integer yearValue;
        File file = null;

//        String newFolderName;
//        for (TopicEntity topicEntity : topicEntityList) {
//            System.out.println(topicEntity.getEpisode());
//            shortYear = topicEntity.getEpisode().substring(0, 2);
//            year = "20" + shortYear;
//            newFolderName =
//                BbcConstants.ROOT_FOLDER_NAME + File.separator + year + File.separator + topicEntity.getEpisode();
//            // 在D盘创建一个文件夹123
//            file = new File(newFolderName);// 或者 File file = new File("D:\\123");
//
//            if (file.mkdirs()) {
//                System.out.println(newFolderName + " 文件夹创建成功！");
//            } else {
//                System.out.println("文件夹创建失败！");
//            }
//        }

        String newFileName;
        for (TopicEntity topicEntity : topicEntityList) {
            System.out.println(topicEntity.getEpisode());
            shortYear = topicEntity.getEpisode().substring(0, 2);
            year = "20" + shortYear;
            newFileName = CommonUtil.getFullPathFileName(year,  topicEntity.getEpisode(), ".txt"); //
//                BbcConstants.ROOT_FOLDER_NAME + File.separator + year + File.separator + topicEntity.getEpisode() + File.separator + "script_raw.txt";
            // 在D盘创建一个文件夹123
            file = new File(newFileName);// 或者 File file = new File("D:\\123");

            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(newFileName + " 文件夹创建成功！");
            } else {
                System.out.println("文件夹创建失败！");
            }
        }
    }

    /**
     * 返回 D:\04_GitHub\java-architect-util\free-apps\src\main\resources //
     * https://blog.csdn.net/qq_38319289/article/details/115236819 // SpringBoot获取resources文件路径 // File directory = new
     * File("src/main/resources"); // String reportPath = directory.getCanonicalPath(); // String resource =reportPath +
     * "/static/template/resultTemplate.docx";
     *
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

                        freeHistory.setUrlCn(CdConstants.URL_CN_BASE + appId);
                        freeHistory.setUrlUs(CdConstants.URL_US_BASE + appId);
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

            int size = contentList.size();
            for (int i = 0; i < size; i++) {
                String str = contentList.get(i);
                // 如果不是最后一行，就加上回车换行
                if (i != size - 1) {
                    str = str.trim().replaceAll("  ", " ") + "\r\n";
                }

                bw.write(str);
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

    public static List<String> getAllFileNames(String folderPath) {
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
//            System.out.println("路径：" + file2.getPath());
//            System.out.println("文件夹/文件名：" + file2.getName());
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

    public static String parseFilename(int indexPrefixJpg, String jsonStr) {
        int endIndex = indexPrefixJpg + 4;
        String tempStr = jsonStr.substring(0, indexPrefixJpg + 4);
        String tempStr2 = tempStr.substring(0, tempStr.lastIndexOf("/"));
        int beginIndex = tempStr2.lastIndexOf("/");
        String tempStr3 = tempStr.substring(beginIndex + 1, endIndex);
        tempStr3 = tempStr3.replaceAll("/", "_");
        return tempStr3;
    }

    public static List<String> readFileContent(String fileName) {
        List<String> stringList = new ArrayList<>();
        File file = new File(fileName);//定义一个file对象，用来初始化FileReader
        FileReader reader;//定义一个fileReader对象，用来初始化BufferedReader
        try {
            reader = new FileReader(file);
            BufferedReader bReader = new BufferedReader(reader);//new一个BufferedReader对象，将文件内容读取到缓存
//            StringBuilder sb = new StringBuilder();//定义一个字符串缓存，将字符串存放缓存中
            String s = "";
            while ((s = bReader.readLine()) != null) {//逐行读取文件内容，不读取换行符和末尾的空格
//                sb.append(s + "\n");//将读取的字符串添加换行符后累加存放在缓存中
                stringList.add(s.trim());
//                System.out.println(s);
            }
            bReader.close();
//            String str = sb.toString();
//            System.out.println(str);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return stringList;
    }

    public static List<SubtitleBaseEntity> readSrcFileContent(String fileName) {
        List<String> stringList = new ArrayList<>();
        File file = new File(fileName);//定义一个file对象，用来初始化FileReader
        FileReader reader;//定义一个fileReader对象，用来初始化BufferedReader
        try {
            reader = new FileReader(file);
            BufferedReader bReader = new BufferedReader(reader);//new一个BufferedReader对象，将文件内容读取到缓存
//            StringBuilder sb = new StringBuilder();//定义一个字符串缓存，将字符串存放缓存中
            String s = "";
            while ((s = bReader.readLine()) != null) {//逐行读取文件内容，不读取换行符和末尾的空格
//                sb.append(s + "\n");//将读取的字符串添加换行符后累加存放在缓存中
                stringList.add(s.trim());
//                System.out.println(s);
            }
            stringList.add("");// 补最后一行的空格
            bReader.close();
//            String str = sb.toString();
//            System.out.println(str);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        List<SubtitleBaseEntity> result = new ArrayList<>();
        int firstSpaceIndex = 0;
        SubtitleBaseEntity subtitleBaseEntity;
        if (CollectionUtils.isNotEmpty(stringList)) {

            int size = stringList.size();
            if (size % 4 != 0 && StrUtil.isEmpty(stringList.get(size - 1)) && StrUtil.isEmpty(
                stringList.get(size - 2))) {
                stringList.remove(size - 1);
            }

            for (int i = 0; i < stringList.size(); i++) {
                if (StrUtil.isEmpty(stringList.get(i))) {
                    subtitleBaseEntity = new SubtitleBaseEntity();
                    if (firstSpaceIndex == 0) {
                        subtitleBaseEntity.setSubIndex(Integer.parseInt(stringList.get(0)));
                        subtitleBaseEntity.setTimeStr(stringList.get(1));
                        subtitleBaseEntity.setSubtitle(stringList.get(2));
                    } else {
                        if (StrUtil.isNotEmpty(stringList.get(firstSpaceIndex))) {
                            subtitleBaseEntity.setSubIndex(Integer.parseInt(stringList.get(firstSpaceIndex)));
                        }
                        subtitleBaseEntity.setTimeStr(stringList.get(firstSpaceIndex + 1));
                        subtitleBaseEntity.setSubtitle(stringList.get(firstSpaceIndex + 2));
                    }
                    firstSpaceIndex = i + 1;

                    result.add(subtitleBaseEntity);
                }
            }
        }

        return result;
    }

    /**
     * 获取对话实体列表
     *
     * @param fileName 脚本位置
     * @return 对话实体列表
     */

    public static List<ScriptEntity> genScriptEntityList(String fileName) {
        List<String> stringList = new ArrayList<>();
        File file = new File(fileName);//定义一个file对象，用来初始化FileReader
        FileReader reader;//定义一个fileReader对象，用来初始化BufferedReader
        try {
            reader = new FileReader(file);
            BufferedReader bReader = new BufferedReader(reader);//new一个BufferedReader对象，将文件内容读取到缓存
            String s = "";
            while ((s = bReader.readLine()) != null) {//逐行读取文件内容，不读取换行符和末尾的空格
                stringList.add(s.trim());
            }
            stringList.add("");// 补最后一行的空格
            bReader.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        List<ScriptEntity> result = new ArrayList<>();

        ScriptEntity scriptEntity;
        if (CollectionUtils.isNotEmpty(stringList)) {
            int size = stringList.size();
            if (size % 3 != 0) {
                System.out.println("文件格式有问题，行数应该是3的倍数，实际为：" + size + "; fileName: " + fileName);
                return null;
            }

            for (int i = 0; i < stringList.size(); i += 3) {
                scriptEntity = new ScriptEntity();
                scriptEntity.setTalker(stringList.get(i));
                scriptEntity.setContent(stringList.get(i + 1));

                result.add(scriptEntity);
            }
        }

        return result;
    }

    //

    /**
     * 获取对话实体列表
     *
     * @param fileName 脚本位置
     * @return 对话实体列表
     */

    public static List<TopicEntity> genTopicEntityList(String fileName) {
        List<String> stringList = new ArrayList<>();
        File file = new File(fileName);//定义一个file对象，用来初始化FileReader
        FileReader reader;//定义一个fileReader对象，用来初始化BufferedReader
        try {
            reader = new FileReader(file);
            BufferedReader bReader = new BufferedReader(reader);//new一个BufferedReader对象，将文件内容读取到缓存
            String s = "";
            while ((s = bReader.readLine()) != null) {//逐行读取文件内容，不读取换行符和末尾的空格
                if (StrUtil.isNotEmpty(s)) {
                    stringList.add(s.trim());
                }
            }
//            stringList.add("");// 补最后一行的空格
            bReader.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        List<TopicEntity> result = new ArrayList<>();

        TopicEntity topicEntity;
        if (CollectionUtils.isNotEmpty(stringList)) {
            int size = stringList.size();
            if (size % 3 != 0) {
                System.out.println("文件格式有问题，行数应该是3的倍数，实际为：" + size + "; fileName: " + fileName);
                return null;
            }

            for (int i = 0; i < stringList.size(); i += 3) {
                topicEntity = new TopicEntity();
                topicEntity.setTopic(stringList.get(i));
                topicEntity.setEpisode(stringList.get(i + 1).substring(8, 14)); // TODO
                topicEntity.setDescription(stringList.get(i + 2));

                result.add(topicEntity);
            }
        }

        return result;
    }
}
