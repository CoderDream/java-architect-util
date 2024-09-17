package com.coderdream.freeapps;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.StrUtil;
import com.coderdream.freeapps.model.SubtitleBaseEntity;
import com.coderdream.freeapps.util.srt.SrtUtils;
import com.mpatric.mp3agic.*;
//import core.MP3Slicer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {

    public static final String FILENAME = "D:\\FFOutput\\Apple Event — September 9.mp3";

    public static final String SRT_FILENAME_CN = "C:\\Users\\CoderDream\\Music\\iTunes\\iTunes Media\\Podcasts\\Apple Events (video)\\Apple Event — September 9.chn.srt";

    public static final String SRT_FILENAME_EN = "C:\\Users\\CoderDream\\Music\\iTunes\\iTunes Media\\Podcasts\\Apple Events (video)\\Apple Event — September 9.eng.srt";


    public static void main(String[] args) {
//        splitMp3BySize();
//        splitMp3ByTime();
//        splitMp3ByMilliseconds(FILENAME);

        // TODO
        List<SubtitleBaseEntity> subtitleBaseEntityListCn = SrtUtils.genSubtitleBaseEntityList(SRT_FILENAME_CN);
        List<SubtitleBaseEntity> subtitleBaseEntityListEn = SrtUtils.genSubtitleBaseEntityList(SRT_FILENAME_EN);
        System.out.println("####");
        Map<String, String> map;
        Set<String> beginIndexTimeSet = new LinkedHashSet<>();
        Set<Integer> beginIndexSet = new LinkedHashSet<>();
        Map<Integer, Integer> beginEndIndexMap = new LinkedHashMap<>();
        beginIndexTimeSet.add("00:02:31.952 --> 00:02:35.422");
        beginIndexSet.add(16);
        Set<String> endIndexTimeSet = new LinkedHashSet<>();
        Set<Integer> endIndexSet = new LinkedHashSet<>();

        for (int i = 0; i < subtitleBaseEntityListCn.size(); i++) {
            SubtitleBaseEntity subtitleBaseEntity = subtitleBaseEntityListCn.get(i);
            if (subtitleBaseEntity.getSubtitle().contains("有请")) {
                endIndexTimeSet.add(subtitleBaseEntity.getTimeStr());
                if (subtitleBaseEntityListCn.get(i + 1).getSubtitle().contains("♪")) {
                    if (!subtitleBaseEntityListCn.get(i + 2).getSubtitle().contains("♪")) {
                        beginIndexTimeSet.add(subtitleBaseEntityListCn.get(i + 2).getTimeStr());
                        beginIndexSet.add(i+2);
                    }
                } else {
                    beginIndexTimeSet.add(subtitleBaseEntityListCn.get(i + 1).getTimeStr());
                    beginIndexSet.add(i+1);
                }
            }
            if (i == subtitleBaseEntityListCn.size() - 1) {
                endIndexTimeSet.add(subtitleBaseEntity.getTimeStr());
            }
        }
        endIndexSet.add(12); // TODO
        System.out.println("#########");
        String PATH = "D:/";
        if (beginIndexTimeSet.size() == endIndexTimeSet.size()) {
            List<String> startList = new ArrayList<>(beginIndexTimeSet);
            List<String> endList = new ArrayList<>(endIndexTimeSet);
            for (int i = 0; i < beginIndexTimeSet.size(); i++) {
                String startTime = startList.get(i).substring(0, 12);
                String endTime = endList.get(i).substring(17);
                System.out.println(startTime + " : " + endTime);

                String name = i < 10 ? "0" + i : i + "";

                String fileName = PATH + name + "_output.mp3";

                splitMp3ByMilliseconds(FILENAME, startTime.trim(), endTime.trim(), fileName);
            }
        }


    }

    private static void splitMp3BySize() {
        File file = new File("D:/input.mp3");
        try {
            List<byte[]> fileBytesList = MP3Slicer.split(file, 200 * 1024);
            int number = 0;
            for (byte[] fileBytes : fileBytesList) {
                String fileName = "D:/output" + ++number + ".mp3";
                File outFile = new File(fileName);
                try (FileOutputStream fos = new FileOutputStream(outFile)) {
                    fos.write(fileBytes);
                }
            }
        } catch (InvalidDataException e) {
            e.printStackTrace();
        } catch (UnsupportedTagException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void splitMp3ByTime() {
        File file = new File("D:/input.mp3");
        try {
            byte[] fileBytes = MP3Slicer.split(file, 10, 20);
            String fileName = "D:/output.mp3";
            File outFile = new File(fileName);
            try (FileOutputStream fos = new FileOutputStream(outFile)) {
                fos.write(fileBytes);
            }
        } catch (InvalidDataException e) {
            e.printStackTrace();
        } catch (UnsupportedTagException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void splitMp3ByMilliseconds(String srcFileName) {
//        File file = new File("D:/input.mp3");
        File file = new File(srcFileName);
        // 00:02:31,952 00:05:30,964
        try {

            String startMillisecondsStr = "00:02:31.952";
            String endMillisecondsStr = "00:05:30.964";
//
//            DateTime dateTimeStart = new DateTime(startMillisecondsStr, "HH:mm:ss:SSS");
//            DateTime dateTimeEnd = new DateTime(endMillisecondsStr, "HH:mm:ss:SSS");

            int startMilliseconds = strToMilliseconds(startMillisecondsStr);
            int endMilliseconds = strToMilliseconds(endMillisecondsStr);

            byte[] fileBytes = MP3Slicer.splitLengthInMilliseconds(file, startMilliseconds - 100,
                endMilliseconds + 100);
            String fileName = "D:/output.mp3";
            File outFile = new File(fileName);
            try (FileOutputStream fos = new FileOutputStream(outFile)) {
                fos.write(fileBytes);
            }
        } catch (InvalidDataException e) {
            e.printStackTrace();
        } catch (UnsupportedTagException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void splitMp3ByMilliseconds(String srcFileName, String startMillisecondsStr,
        String endMillisecondsStr, String destFileName) {
//        File file = new File("D:/input.mp3");
        File file = new File(srcFileName);
        // 00:02:31,952 00:05:30,964
        try {

//            String startMillisecondsStr = "00:02:31.952";
//            String endMillisecondsStr = "00:05:30.964";
////
//            DateTime dateTimeStart = new DateTime(startMillisecondsStr, "HH:mm:ss:SSS");
//            DateTime dateTimeEnd = new DateTime(endMillisecondsStr, "HH:mm:ss:SSS");

            int startMilliseconds = strToMilliseconds(startMillisecondsStr);
            int endMilliseconds = strToMilliseconds(endMillisecondsStr);

            byte[] fileBytes = MP3Slicer.splitLengthInMilliseconds(file, startMilliseconds - 100,
                endMilliseconds + 100);
//            String fileName = "D:/output.mp3";
            File outFile = new File(destFileName);
            try (FileOutputStream fos = new FileOutputStream(outFile)) {
                fos.write(fileBytes);
            }
        } catch (InvalidDataException e) {
            e.printStackTrace();
        } catch (UnsupportedTagException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int strToMilliseconds(String timeStr) {
        int result = 0;

        if (StrUtil.isEmpty(timeStr)) {
            return result;
        }

        timeStr = timeStr.replaceAll(",", ":");
        timeStr = timeStr.replaceAll("\\.", ":");
        String[] strings = timeStr.split(":");
        if (strings.length != 4) {
            return result;
        }

        result = Integer.parseInt(strings[0]) * 60 * 60 * 1000 + Integer.parseInt(strings[1]) * 60 * 1000
            + Integer.parseInt(strings[2]) * 1000 + Integer.parseInt(strings[3]);

        log.info("result: {}", result);
        return result;
    }
}
