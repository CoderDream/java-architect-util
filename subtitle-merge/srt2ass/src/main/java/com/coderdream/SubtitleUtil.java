package com.coderdream;


import com.coderdream.ass.Event;
import com.coderdream.util.User;
import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.collections.ComparatorUtils;
import org.apache.commons.collections.comparators.ComparableComparator;
import org.apache.commons.collections.comparators.ComparatorChain;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import java.util.Comparator;

/**
 * @author coderdream
 * @version 1.0
 * @since 2020-07-04
 */
public class SubtitleUtil {

    /**
     * 00:02:10,280 --> 00:02:15,280
     * 取前8位 00:02:10
     */
    private static final Integer FIRST_END = 12;

    private static final String LAYER_CN = "0";

    private static final String LAYER_EN = "1";

    private static final String MARGIN_V_CN = "52";

    private static final String MARGIN_V_EN = "0";

    /**
     *
     */
    private static final Integer SECOND_BEGIN = 17;
    /**
     *
     */
    private static final Integer SECOND_END = 29;
    /**
     * 日志
     */
    static Logger logger = Logger.getLogger(SubtitleUtil.class.getName());

    /**
     * 主方法
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        if (args.length == 3) {
            merge(args[0], args[1], args[2]);
            logger.info(args[2] + " makes successful.");
        } else if (args.length == 2) {
            mergeToOneLine(args[0], args[1]);
            logger.info(args[1] + " makes successful.");
        } else {
            logger.info("args length is not 3, please check and run again");
//            String fileName1 = "Subtitle_1.srt";
//            String fileName2 = "Subtitle_5.srt";
//            String fileName = "Subtitle_merge_1.srt";

            String fileName1 = "Subtitle_27.srt";
            String fileName2 = "Subtitle_29.srt";
            String fileNameA = "Coronavirus.Explained.E0";
            String fileNameB = ".2020.Netflix.WEB-DL.1080p.x264.DDP-HDCTV.srt";
            for (int i = 1; i < 4; i++) {
                merge("0" + i + "/" + fileName1, "0" + i + "/" + fileName2, fileNameA + i + fileNameB);
            }
        }
    }

    /**
     * 整合字幕并写入新文件
     *
     * @param fileName1
     * @param fileName2
     * @param fileName
     * @throws IOException
     */
    public static void merge(String fileName1, String fileName2, String fileName) throws IOException {
        List<Item> firstItems = SubtitleUtil.readFile(fileName1);
        List<Item> secondItems = SubtitleUtil.readFile(fileName2);
        List<Item> subtitleMergeItems = subtitleMerge(firstItems, secondItems);
        writeBuffer(fileName, subtitleMergeItems);
    }

    /**
     * 整合字幕并写入新文件
     *
     * @param fileName1
     * @param fileName2
     * @param fileName
     * @throws IOException
     */
    public static void mergeToAss(String fileName1, String fileName2, String fileName) throws IOException {
        List<Item> firstItems = SubtitleUtil.readFile(fileName1);
        List<Item> secondItems = SubtitleUtil.readFile(fileName2);
        List<Item> subtitleMergeItems = subtitleMerge(firstItems, secondItems);

        List<Event> eventList = new ArrayList<>();
        Event event = null;
        Event eventEn = null;
        for (Item item : subtitleMergeItems) {
            // 中文
            event = new Event();
            String beginTime = item.getBeginTime();
            String endTime = item.getEndTime();
            String content = item.getContent();
            event.setFormat("Dialogue");
            event.setStart(formatTime(beginTime));
            event.setEnd(formatTime(endTime));
            event.setStyle("");
            event.setMarginL("0");
            event.setMarginR("0");
            event.setEffect("");

            String secondContent = item.getSecondContent();

            // 无第二语言
            if (secondContent == null || secondContent.trim().equals("")) {

                if (isChineseContent(content)) {
                    event.setName("中文对白");

                    event.setLayer(LAYER_CN);
                    event.setMarginV(MARGIN_V_CN);
                } else {
                    event.setName("英文对白");

                    event.setLayer(LAYER_EN);
                    event.setMarginV(MARGIN_V_EN);
                }
                event.setText(content);
                eventList.add(event);
            }
            // 既有中文，也有英文
            else {
                // 中文
                event.setName("中文对白");
                event.setText(content);
                event.setLayer(LAYER_CN);
                event.setMarginV(MARGIN_V_CN);
                eventList.add(event);

                eventEn = new Event();
                eventEn.setFormat("Dialogue");
                eventEn.setStart(formatTime(beginTime));
                eventEn.setEnd(formatTime(endTime));
                eventEn.setName("英文对白");
                eventEn.setStyle("");
                eventEn.setMarginL("0");
                eventEn.setMarginR("0");
                eventEn.setEffect("");
                eventEn.setLayer(LAYER_EN);
                eventEn.setMarginV(MARGIN_V_EN);
                eventEn.setText(secondContent);
                eventList.add(eventEn);
            }

        }

        String[] properties =   {"layer","start"};
        multiSort(eventList, properties);

//        Collections.sort(eventList, new Comparator<Event>() {
//                    /**
//                     *先按Layer逆序排序，再按开始时间升序排序
//                     * @param item1
//                     * @param item2
//                     * @return
//                     */
//                    @Override
//                    public int compare(Event item1, Event item2) {
//                        if (item2.getLayer().compareTo(item1.getLayer()) == 0) {
//                            return item1.getStart().compareTo(item2.getStart());
//                        } else {
//                            return item1.getStart().compareTo(item2.getStart());
//                        }
//                    }
//                }
//        );

        writeBufferToAss(fileName, eventList);
    }

    /**
     *
     * @param eventListvent
     * @param properties
     */
    private static void multiSort(List<Event> eventListvent, String[] properties) {
        Comparator mycmp1 = ComparableComparator.getInstance();
        mycmp1 = ComparatorUtils.reversedComparator(mycmp1); //逆序

        Comparator mycmp2 = ComparableComparator.getInstance();
//		mycmp2 = ComparatorUtils. reversedComparator(mycmp2); //允许null
        mycmp2 = ComparatorUtils.nullHighComparator(mycmp2); //允许null

        // 声明要排序的对象的属性，并指明所使用的排序规则，如果不指明，则用默认排序
        ArrayList<Object> sortFields = new ArrayList<Object>();
        sortFields.add(new BeanComparator(properties[0], mycmp1)); //主排序（第一排序）
        sortFields.add(new BeanComparator(properties[1], mycmp2)); //次排序（第二排序）

        // 创建一个排序链
        ComparatorChain multiSort = new ComparatorChain(sortFields);

        // 开始真正的排序，按照先主，后副的规则
        Collections.sort(eventListvent, multiSort);
    }

    public static String formatTime(String strDate) {
        // 从字符串提取出日期
        // String strDate = "01:48:29,610";
        String pat1 = "HH:mm:ss,SSS";
        String pat2 = "H:mm:ss.SS";
        SimpleDateFormat format1 = new SimpleDateFormat(pat1);
        SimpleDateFormat format2 = new SimpleDateFormat(pat2);
        Date tempDate = null;
        try {
            //生成时间对象
            tempDate = format1.parse(strDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String result = format2.format(tempDate);
        if (result.length() == 11) {
            result = result.substring(0, 10);
        }

        return result;
    }

    /**
     * 把两行字幕整合成一行
     *
     * @param fileName1
     * @param fileName
     * @throws IOException
     */
    public static void mergeToOneLine(String fileName1, String fileName) throws IOException {
        List<Item> firstItems = SubtitleUtil.readFile(fileName1);
        List<Item> subtitleMergeItems = mergeToOneLine(firstItems);
        writeBuffer(fileName, subtitleMergeItems);
    }

    /**
     * 写入文件
     *
     * @return
     * @throws IOException
     */
    private static File writeBufferToAss(String fileName, List<Event> eventList) throws IOException {
        File file = new File(fileName);
        // 如果文件存在，则删除文件
        if (file.exists()) {
            file.delete();
        }
        // 创建文件
        file.createNewFile();

        if (!Files.exists(Paths.get(fileName))) {
            Files.write(Paths.get(fileName), new byte[0]);
        }
        FileOutputStream fos = new FileOutputStream(file);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));

        // 写 头部信息
        Path resourceDirectory = Paths.get("src", "test", "resources");
        String absolutePath = resourceDirectory.toFile().getAbsolutePath();

        //
        String templateFileName = absolutePath + File.separatorChar + "template.ass";
        List<String> tempList = readNormalFile(templateFileName);
        for (String str : tempList) {
            writer.write(str + "\r\n");
        }

        for (Event event : eventList) {
            writer.write(event.toLine() + "\r\n");
        }

        writer.close();
        fos.close();
        return file;
    }

    /**
     * 写入文件
     *
     * @return
     * @throws IOException
     */
    private static File writeBuffer(String fileName, List<Item> subtitleMergeItems) throws IOException {
        File file = new File(fileName);
        // 如果文件存在，则删除文件
        if (file.exists()) {
            file.delete();
        }
        // 创建文件
        file.createNewFile();

        if (!Files.exists(Paths.get(fileName))) {
            Files.write(Paths.get(fileName), new byte[0]);
        }
        FileOutputStream fos = new FileOutputStream(file);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));
        for (Item item : subtitleMergeItems) {
            writer.write(item.toMultiLine() + "\r\n");
        }

        writer.close();
        fos.close();
        return file;
    }

    /**
     * 读取字幕文件
     *
     * @param fileName
     * @return
     */
    private static List<Item> readFile(String fileName) {
        //read file into stream, try-with-resources
        List<Item> results = new ArrayList<>();
        Item item = null;
        Integer id = 0;
        String timeRange = "";
        String content = "";
        String contentNext = "";
        Set<String> timeRangeSet = new HashSet<>();

        Item prevItem = null;
        try (Stream<String> stream = Files.lines(Paths.get(fileName), StandardCharsets.UTF_8)) {
            // 读取每一行
            List<String> list = new ArrayList<String>();
            stream.forEach(str -> {
                list.add(str);
            });
            // 末尾补空格
            list.add("");

            // 放入字符串数组
            String[] lines = new String[list.size()];
            for (int i = 0; i < list.size(); i++) {
                lines[i] = list.get(i);
            }

            for (int i = 0; i < lines.length; i++) {
                String str = lines[i];
                if ("".equals(str.trim())) {
                    item = new Item();
                    // 设置当前Item的前一个Item
                    item.prevItem = prevItem;
                    // 设置前一个Item的nextItem
                    if (item.prevItem != null) {
                        item.prevItem.nextItem = item;
                    }
                    String prevLine3 = lines[i - 3];
                    // 不是数字，再往上找
                    if (!isNumeric(prevLine3)) {
                        String prevLine4 = lines[i - 4];
                        // 00:21:41,55 --> 00:21:45,30
                        // 00:00:06,960 --> 00:00:09,050
                        if (!isNumeric(prevLine4)) {
                            System.out.println("isNumeric ERROR: " + prevLine4);
                        }
                        // 内容有两行，需要合并
                        else {
                            id = Integer.parseInt(lines[i - 4]);
                            timeRange = lines[i - 3];
                            content = lines[i - 2];
                            contentNext = lines[i - 1];
                            // 合并内容
                            content = content + " " + contentNext;
                        }
                    }
                    // 只有一行，不需合并
                    else {
                        id = Integer.parseInt(lines[i - 3]);
                        timeRange = lines[i - 2];
                        content = lines[i - 1];
                    }

                    item.setId(id);
                    item.setTimeRange(timeRange);
                    String beginTime = timeRange.substring(0, FIRST_END);
                    String endTime = timeRange.substring(17, SECOND_END);
                    item.setBeginTime(beginTime);
                    item.setEndTime(endTime);
                    item.setContent(content);
                    if (!timeRangeSet.contains(timeRange)) {
                        // 加入返回列表
                        results.add(item);
                        prevItem = item;
                    }
                    // 去掉重复
                    timeRangeSet.add(timeRange);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return results;
    }

    /**
     * 读取文件
     *
     * @param fileName
     * @return
     */
    private static List<String> readNormalFile(String fileName) {
        //read file into stream, try-with-resources
        List<String> results = new ArrayList<>();
        try (Stream<String> stream = Files.lines(Paths.get(fileName), StandardCharsets.UTF_8)) {
            // 读取每一行
            List<String> list = new ArrayList<String>();
            stream.forEach(str -> {
                list.add(str);
            });
            results.addAll(list);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return results;
    }

    /**
     * 移除中文字符中间的空格，如果是英文+空格+中文，或者中文+空格+英文，则不去除；（中文包括中文及标点符号）
     *
     * @param str
     * @return
     */
    public static String trimChineseBlank(String str) {
        // 如果不包含空格或者为null或者只有一个字符，则直接返回字符串
        if (!str.contains(" ") || str == null || str.length() == 1) {
            return str;
        }

        // 如果本身就是一个字符串，则返回本身（空格则返回空串）
        if (str != null && str.length() == 1) {
            return str.trim();
        }

        // 如果是两个字符，则直接去掉前后空格
        if (str.length() == 2) {
            return str.trim();
        }

        char[] charArray = str.toCharArray();
        StringBuffer stringBuffer = new StringBuffer();
        int size = charArray.length;
        boolean char1;
        boolean char2;
        boolean char3;

        // 每次取三个字符
        for (int i = 0; i < size - 2; i++) {
            char1 = isChinese(charArray[i]);
            char2 = Character.isWhitespace(charArray[i + 1]);
            char3 = isChinese(charArray[i + 2]);

            if (char1 == true && char2 == true && char3 == true) {
                stringBuffer.append(charArray[i]).append(charArray[i + 2]);
                i += 2;
            } else {
                stringBuffer.append(charArray[i]);
                if (i == size - 3) {
                    stringBuffer.append(charArray[i + 1]);
                    stringBuffer.append(charArray[i + 2]);
                }
            }
        }

        return stringBuffer.toString();
    }

    /**
     * 移除最好的英文标点符号
     *
     * @param content
     * @return
     */
    public static String removeLastPunctuation(String content) {
        String result = "";
        if (content != null && !content.trim().equals("")) {
            char[] charArray = content.toCharArray();
            StringBuffer stringBuffer = new StringBuffer(content);
            int size = charArray.length;
            char comma = ',';
            char dot = '.';
            if (charArray[size - 1] == comma) {
                result = stringBuffer.replace(size - 1, size, "").toString();
            } else if (charArray[size - 1] == dot) {
                result = stringBuffer.replace(size - 1, size, "").toString();
            } else {
                result = stringBuffer.toString();
            }
        }

        return result;
    }

    /**
     * 是否为中文字符
     *
     * @param c
     * @return
     */
    public static boolean isChinese(char c) {
        // || (c >= 0x0000 && c <= 0x00FF)//英文字符
        //中文字符
        return c >= 0x0391 && c <= 0xFFE5;
    }

    /**
     * 是否为中文字幕
     *
     * @param content
     * @return
     */
    public static boolean isChineseContent(String content) {
        char[] charArray = content.toCharArray();
        int size = charArray.length;
        for (int i = 0; i < size; i++) {
            if (isChinese(charArray[i])) {
                return true;
            }
        }
        return false;
    }

    /**
     * 合并字幕
     *
     * @param firstItems
     * @return
     */
    private static List<Item> mergeToOneLine(List<Item> firstItems) {
        List<Item> subtitleMergeItems = new ArrayList<>();

        // 开始时间为key
        Item secondItem = null;
        // 结束时间为key
        Item thirdItem = null;
        Item leftItem = null;
        String firstContent = "";
        String secondContent = "";
        for (Item item : firstItems) {
            // 开始时间和完成时间完全匹配
            if (null != secondContent && !secondContent.trim().equals("")) {
                // 存在第二语言，合并
                firstContent = item.getContent();
                secondContent = secondItem.getContent();
                item.setContent(firstContent);
                item.setSecondContent(secondContent);
                subtitleMergeItems.add(item);
            } else {
                firstContent = item.getContent();
                item.setContent(firstContent);
                // item.setSecondContent(secondContent);
                subtitleMergeItems.add(item);
            }
        }

        Collections.sort(subtitleMergeItems, new Comparator<Item>() {
                    @Override
                    public int compare(Item sweet1, Item sweet2) {
                        return sweet1.timeRange.compareTo(sweet2.timeRange);
                    }
                }
        );

        return subtitleMergeItems;
    }

    /**
     * 合并字幕
     *
     * @param firstItems
     * @param secondItems
     * @return
     */
    private static List<Item> subtitleMerge(List<Item> firstItems, List<Item> secondItems) {
        List<Item> subtitleMergeItems = new ArrayList<>();

        Map<String, Item> firstMap = new LinkedHashMap<>();
        for (Item item : firstItems) {
            firstMap.put(item.getTimeRange(), item);
        }
        Map<String, Item> secondMap = new LinkedHashMap<>();
        for (Item item : secondItems) {
            String beginTime = item.getTimeRange().trim().substring(0, FIRST_END);
            String endTime = item.getTimeRange().trim().substring(17, SECOND_END);
            secondMap.put(beginTime, item);
            secondMap.put(endTime, item);
        }

        // 开始时间为key
        Item secondItem = null;
        // 结束时间为key
        Item thirdItem = null;
        Item leftItem = null;
        String firstContent = "";
        String secondContent = "";
        // 遍历第一语言
        for (Item item : firstItems) {
            if ("00:01:07,434 --> 00:01:09,203".equals(item.getTimeRange())) {
                //  System.out.println("hold on");
            }
            secondItem = secondMap.get(item.getTimeRange().substring(0, FIRST_END));
            thirdItem = secondMap.get(item.getTimeRange().substring(SECOND_BEGIN, SECOND_END));
            // 开始时间和完成时间完全匹配
            if (null != secondItem && null != thirdItem && secondItem.getTimeRange().equals(thirdItem.getTimeRange())) {
                // 存在第二语言，合并
                firstContent = item.getContent();
                secondContent = secondItem.getContent();
                item.setContent(firstContent);
                item.setSecondContent(secondContent);
                // 把 Map 中处理过的对象删除
                secondMap.remove(secondItem.getTimeRange().substring(0, FIRST_END));
                secondMap.remove(secondItem.getTimeRange().substring(SECOND_BEGIN, SECOND_END));
                subtitleMergeItems.add(item);
            } else {
                // 存在第二语言，合并
                if (null != secondItem) {
                    firstContent = item.getContent();
                    secondContent = secondItem.getContent();
                    item.setContent(firstContent);
                    item.setSecondContent(secondContent);
                    // 把 Map 中处理过的对象删除
                    secondMap.remove(secondItem.getTimeRange().substring(0, FIRST_END));
                    secondMap.remove(secondItem.getTimeRange().substring(SECOND_BEGIN, SECOND_END));
                    subtitleMergeItems.add(item);
                } else if (null != thirdItem) {
                    firstContent = item.getContent();
                    secondContent = thirdItem.getContent();
                    item.setContent(firstContent);
                    item.setSecondContent(secondContent);
                    // 把 Map 中处理过的对象删除
                    secondMap.remove(thirdItem.getTimeRange().substring(0, FIRST_END));
                    secondMap.remove(thirdItem.getTimeRange().substring(SECOND_BEGIN, SECOND_END));
                    subtitleMergeItems.add(item);
                }
                // 不存在第二语言
                else {
                    firstContent = item.getContent();
                    item.setContent(firstContent);
                    // item.setSecondContent(secondContent);
                    //System.out.println("第一语言的项：" + item);
                    System.out.println(item.getTimeRange());
                    subtitleMergeItems.add(item);
                }
            }
            firstMap.remove(item.getTimeRange());
        }
        System.out.println("第二语言的项：");
        //
        for (Item item2 : secondItems) {
            leftItem = secondMap.get(item2.getTimeRange().substring(0, FIRST_END));
            // 找到未处理过的第二语言
            if (null != leftItem) {
                // System.out.println("第二语言的项：" + leftItem);
                System.out.println(leftItem.getTimeRange());
                subtitleMergeItems.add(item2);
            }
        }

        for (Map.Entry<String, Item> entry : firstMap.entrySet()) {
            String mapKey = entry.getKey();
            Item mapValue = entry.getValue();
            System.out.println("遗漏的项：" + mapKey + "：" + mapValue);
        }

        Collections.sort(subtitleMergeItems, new Comparator<Item>() {
                    @Override
                    public int compare(Item item1, Item item2) {
                        return item1.timeRange.compareTo(item2.timeRange);
                    }
                }
        );


        return subtitleMergeItems;
    }

    /**
     * 判断字符串是否为正整数
     *
     * @param string
     * @return
     */
    public static boolean isNumeric(String string) {
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(string).matches();
    }
}
