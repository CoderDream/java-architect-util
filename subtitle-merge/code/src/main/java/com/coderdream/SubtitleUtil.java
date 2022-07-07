package com.coderdream;


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
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
     * 日志
     */
    static Logger logger = Logger.getLogger(SubtitleUtil.class.getName());
    private static final Integer FIRST_END = 9;
    private static final Integer SECOND_END = 24;

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
        try (Stream<String> stream = Files.lines(Paths.get(fileName), StandardCharsets.UTF_8)) {
            List<String> list = new ArrayList<String>();
            stream.forEach(str -> {
                list.add(str);
            });

            String[] lines = new String[list.size()];
            for (int i = 0; i < list.size(); i++) {
                lines[i] = list.get(i);
            }

            for (int i = 0; i < lines.length; i++) {
                String str = lines[i];
                if ("".equals(str.trim())) {
                    item = new Item();
                    String prewLine3 = lines[i - 3];
                    // 不是数字，再往上找
                    if (!isNumeric(prewLine3)) {
                        String prewLine4 = lines[i - 4];
                        // 00:21:41,55 --> 00:21:45,30
                        // 00:00:06,960 --> 00:00:09,050
                        if (!isNumeric(prewLine4)) {
                            System.out.println("isNumeric ERROR: " + prewLine4);
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
                    item.setContent(content);
                    if (!timeRangeSet.contains(timeRange)) {
                        // 加入返回列表
                        results.add(item);
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
            if (null != secondItem && null != thirdItem && secondItem.getTimeRange().equals(thirdItem.getTimeRange())) {
                // 存在第二语言，合并
                firstContent = item.getContent();
                secondContent = secondItem.getContent();
                item.setContent(firstContent);
                item.setSecondContent(secondContent);
                subtitleMergeItems.add(item);
            } else {
                // 存在第二语言，合并
                if (null != secondItem) {
                    firstContent = item.getContent();
                    secondContent = secondItem.getContent();
                    item.setContent(firstContent);
                    item.setSecondContent(secondContent);
                    subtitleMergeItems.add(item);
                } else if (null != thirdItem) {
                    firstContent = item.getContent();
                    secondContent = thirdItem.getContent();
                    item.setContent(firstContent);
                    item.setSecondContent(secondContent);
                    subtitleMergeItems.add(item);
                }
                // 不存在第二语言
                else {
//                    firstContent = item.getContent();
//                    item.setContent(firstContent);
//                    // item.setSecondContent(secondContent);
//                    subtitleMergeItems.add(item);
                }
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
        Map<String, Item> secondMap = new HashMap<>();
        for (Item item : secondItems) {
            secondMap.put(item.getTimeRange().substring(0, FIRST_END), item);
            secondMap.put(item.getTimeRange().substring(16, SECOND_END), item);
        }

        // 开始时间为key
        Item secondItem = null;
        // 结束时间为key
        Item thirdItem = null;
        Item leftItem = null;
        String firstContent = "";
        String secondContent = "";
        for (Item item : firstItems) {
            secondItem = secondMap.get(item.getTimeRange().substring(0, FIRST_END));
            thirdItem = secondMap.get(item.getTimeRange().substring(16, SECOND_END));
            // 开始时间和完成时间完全匹配
            if (null != secondItem && null != thirdItem && secondItem.getTimeRange().equals(thirdItem.getTimeRange())) {
                // 存在第二语言，合并
                firstContent = item.getContent();
                secondContent = secondItem.getContent();
                item.setContent(firstContent);
                item.setSecondContent(secondContent);
                // 把 Map 中处理过的对象删除
                secondMap.remove(secondItem.getTimeRange().substring(0, FIRST_END));
                secondMap.remove(secondItem.getTimeRange().substring(16, SECOND_END));
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
                    secondMap.remove(secondItem.getTimeRange().substring(16, SECOND_END));
                    subtitleMergeItems.add(item);
                } else if (null != thirdItem) {
                    firstContent = item.getContent();
                    secondContent = thirdItem.getContent();
                    item.setContent(firstContent);
                    item.setSecondContent(secondContent);
                    // 把 Map 中处理过的对象删除
                    secondMap.remove(thirdItem.getTimeRange().substring(0, FIRST_END));
                    secondMap.remove(thirdItem.getTimeRange().substring(16, SECOND_END));
                    subtitleMergeItems.add(item);
                }
                // 不存在第二语言
                else {
//                    firstContent = item.getContent();
//                    item.setContent(firstContent);
//                    // item.setSecondContent(secondContent);
//                    subtitleMergeItems.add(item);
                }
            }
        }

        for (Item item2 : secondItems) {
            leftItem = secondMap.get(item2.getTimeRange().substring(0, FIRST_END));
            // 找到未处理过的第二语言
            if (null != leftItem) {
                subtitleMergeItems.add(item2);
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
