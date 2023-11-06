package com.coderdream.freeapps.util.subtitle;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.coderdream.freeapps.model.SubtitleEntity;
import com.coderdream.freeapps.util.other.CdDateTimeUtils;
import com.coderdream.freeapps.util.other.CdFileUtils;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SubtitleUtils {

    public static void main(String[] args) {
        String videoName = "天津德云社成立二周年系列专场第一场.De.Yun.Society.20230612.2160p.WEB-DL.H265.AAC-OurTV-00.00.00.000-01.35.35.482-seg1";
        String subtitleFileName = "2023061201.srt";
        subtitleFileName = "2023061202.srt";
        List<SubtitleEntity> subtitleEntityList = SubtitleUtils.genSubtitleEntityList(videoName, subtitleFileName);
        for (SubtitleEntity subtitleEntity : subtitleEntityList) {
            System.out.println(subtitleEntity);
        }
    }


    public static List<SubtitleEntity> genSubtitleEntityList(String videoName, String subtitleFileName) {
        long startTime = System.currentTimeMillis();

        String folderPath =
            CdFileUtils.getResourceRealPath() + File.separatorChar + "subtitle" + File.separatorChar + "2023";
        String fileName = folderPath + File.separatorChar + subtitleFileName;

        List<SubtitleEntity> subtitleEntityList = null;
        List<String> biStringList = CdFileUtils.readFileContent(fileName); // TODO
        Map<Integer, SubtitleEntity> subtitleEntityMap = new LinkedHashMap<>();
        int index = 0;
        int count = 0;

        DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss,SSS");
        if (CollectionUtils.isNotEmpty(biStringList)) {
            subtitleEntityList = new ArrayList<>();
            SubtitleEntity subtitleEntity;
            for (String str : biStringList) {
                subtitleEntity = subtitleEntityMap.get(index);
                if (subtitleEntity == null) {
                    subtitleEntity = new SubtitleEntity();
                    subtitleEntity.setVideoName(videoName);
                    subtitleEntity.setCreateTime(new Date());
                    subtitleEntityMap.put(index, subtitleEntity);
                    subtitleEntityList.add(subtitleEntity);
                }
                count++;
                if (StrUtil.isNotEmpty(str)) {
                    log.error(str);
                    switch (count) {
                        case 1:
                            subtitleEntity.setSubtitleIndex(str + "_" + videoName);
                            subtitleEntity.setSubIndex(Integer.parseInt(str));
                            break;

                        case 2:
                            String[] arr = str.split("-->");
                            if (arr != null && arr.length == 2) {
                                subtitleEntity.setStartTimeStr(arr[0].trim());
                                subtitleEntity.setEndTimeStr(arr[1].trim());
                                try {
                                    subtitleEntity.setStartTime(timeFormat.parse(arr[0].trim()));
                                    subtitleEntity.setEndTime(timeFormat.parse(arr[1].trim()));
                                } catch (ParseException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                            break;
                        case 3:
                            subtitleEntity.setSubtitleCn(str);
                            break;

                        default:
                            break;
                    }

                } else {
                    count = 0;
                    index++;
                }
            }
        }

        long endTime = System.currentTimeMillis();
        long period = endTime - startTime;

        String operateTimeLengthStr = CdDateTimeUtils.genMessage(period);

        log.error("本次耗时" + operateTimeLengthStr + "。");

        return subtitleEntityList;
    }

    public static List<SubtitleEntity> genSubtitleEntityList(String folderPath, String videoName, String subtitleFileName) {
        long startTime = System.currentTimeMillis();


        String fileName = folderPath + File.separatorChar + subtitleFileName;

        List<SubtitleEntity> subtitleEntityList = null;
        List<String> biStringList = CdFileUtils.readFileContent(fileName); // TODO
        Map<Integer, SubtitleEntity> subtitleEntityMap = new LinkedHashMap<>();
        int index = 0;
        int count = 0;

        DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss,SSS");
        if (CollectionUtils.isNotEmpty(biStringList)) {
            subtitleEntityList = new ArrayList<>();
            SubtitleEntity subtitleEntity;
            for (String str : biStringList) {
                subtitleEntity = subtitleEntityMap.get(index);
                if (subtitleEntity == null) {
                    subtitleEntity = new SubtitleEntity();
                    subtitleEntity.setVideoName(videoName);
                    subtitleEntity.setCreateTime(new Date());
                    subtitleEntityMap.put(index, subtitleEntity);
                    subtitleEntityList.add(subtitleEntity);
                }
                count++;
                if (StrUtil.isNotEmpty(str)) {
                    log.error(str);
                    switch (count) {
                        case 1:
                            subtitleEntity.setSubtitleIndex(str + "_" + videoName);
                            subtitleEntity.setSubIndex(Integer.parseInt(str));
                            break;

                        case 2:
                            String[] arr = str.split("-->");
                            if (arr != null && arr.length == 2) {
                                subtitleEntity.setStartTimeStr(arr[0].trim());
                                subtitleEntity.setEndTimeStr(arr[1].trim());
                                try {
                                    subtitleEntity.setStartTime(timeFormat.parse(arr[0].trim()));
                                    subtitleEntity.setEndTime(timeFormat.parse(arr[1].trim()));
                                } catch (ParseException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                            break;
                        case 3:
                            subtitleEntity.setSubtitleCn(str);
                            break;

                        default:
                            break;
                    }

                } else {
                    count = 0;
                    index++;
                }
            }
        }

        long endTime = System.currentTimeMillis();
        long period = endTime - startTime;

        String operateTimeLengthStr = CdDateTimeUtils.genMessage(period);

        log.error("本次耗时" + operateTimeLengthStr + "。");

        return subtitleEntityList;
    }

    private static String genXmlString(List<String> stringList, Integer rateValue) {
        String voiceName = "zh-CN-XiaomengNeural";
        String text = "";

        int size = 0;
        for (String str : stringList) {
            size += str.length();
            text += "\r\n        " + str;
        }
        log.error("字数：" + size);
//        text = String.join("\r\n    ", stringList);
        String rate = "+" + rateValue + ".00";
        String lang = "zh-CN";
        lang = "en-US";
//        String xmlString =
//            "<speak xmlns=\"http://www.w3.org/2001/10/synthesis\" xmlns:mstts=\"http://www.w3.org/2001/mstts\" xmlns:emo=\"http://www.w3.org/2009/10/emotionml\" version=\"1.0\" xml:lang=\""
//                + lang + "\"><voice name=\"" + voiceName
//                + "\"><prosody rate=\"" + rate + "%\">"
//                + text + "</prosody></mstts:express-as></voice></speak>";

        String xmlString =
            "<speak version=\"1.0\" xmlns=\"http://www.w3.org/2001/10/synthesis\" xml:lang=\""
                + lang + "\">\r\n    <voice name=\"" + voiceName
                + "\">\r\n    <prosody rate=\"" + rate + "%\">"
                + text + "\r\n        </prosody>\r\n     </voice>\r\n</speak>";

        return xmlString;
    }


    private static String xmlToString(String filePath) {
        File file = new File(filePath);
        StringBuilder fileContents = new StringBuilder((int) file.length());

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                fileContents.append(scanner.nextLine() + System.lineSeparator());
            }
            return fileContents.toString().trim();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            return "File not found.";
        }
    }
}
