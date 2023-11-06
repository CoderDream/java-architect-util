package com.coderdream.freeapps.util.video.ffmpeg;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.Lists;
import org.junit.Test;

@Slf4j
public class FfmpegUtils {

    //分割视频的大小
    private static long blockSize = 1 * 1024 * 1024;

    @Test
    public void Test1() throws Exception {
        String filePath = "C:\\Users\\CoderDream\\Videos\\DFTV.Kai.Gong.Xi.Ju.Zhi.Ye.20230615.HDTV.1080i.MPEG2-HDCTV.mp4";
        List<String> lists = cutVideo(filePath);
        System.out.println(lists);
    }

    public static void main(String[] args) throws Exception {
        String filePath = "C:\\Users\\CoderDream\\Videos\\DFTV.Kai.Gong.Xi.Ju.Zhi.Ye.20230615.HDTV.1080i.MPEG2-HDCTV.mp4";
        filePath = "C:\\Users\\CoderDream\\Downloads2\\抖包袱大会.Dou.Bao.Fu.S01.2023.1080p.H264.AAC-OurTV\\抖包袱大会.Dou.Bao.Fu.2023.S01E01.1080p.H264.AAC-OurTV.mp4";
        List<String> lists = cutVideo(filePath);
        System.out.println(lists);
    }

    /**
     * @param filePath 要处理的文件路径
     * @return 分割后的文件路径
     * @throws Exception 文件
     */
    static List<String> cutVideo(String filePath) throws Exception {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new FileNotFoundException(filePath + "文件不存在");
        }
        if (!filePath.endsWith(".mp4")) {
            throw new Exception("文件格式错误");
        }
        //从ffmpeg获得的时间长度00:00:00格式
        String videoTimeString = getVideoTime(file);
        log.info("从ffmpeg获得的时间长度00:00:00格式:{}", videoTimeString);
        //将时长转换为秒数
        int videoSecond = parseTimeToSecond(videoTimeString);
        log.info("将时长转换为秒数:{}", videoSecond);
        //视频文件的大小
        long fileLength = getVideoFileLength(file);
        log.info("视频文件的大小:{}", fileLength);
        List<String> cutedVideoPaths = new ArrayList<String>();
        if (fileLength <= blockSize) {
            log.info("如果视频文件大小不大于预设值，则直接返回原视频文件");
            cutedVideoPaths.add(filePath);
        } else {
            log.info("超过预设大小，需要切割");
            int partNum = (int) (fileLength / blockSize);
            log.info("文件大小除以分块大小的商:{}", partNum);
            long remainSize = fileLength % blockSize;
            log.info("余数:{}", remainSize);
            int cutNum;
            if (remainSize > 0) {
                cutNum = partNum + 1;
            } else {
                cutNum = partNum;
            }
            log.info("cutNum:{}", cutNum);
            int eachPartTime = videoSecond / cutNum;
            log.info("eachPartTime:{}", eachPartTime);
            String fileFolder = file.getParentFile().getAbsolutePath();
            log.info("fileFolder:{}", fileFolder);
            String fileName[] = file.getName().split("\\.");
            log.info("fileName[]:{}", fileName);

            for (int i = 0; i < cutNum; i++) {
                List<String> commands = Lists.newArrayList();
                commands.add("ffmpeg");
                commands.add("-ss");
                commands.add(parseTimeToString(eachPartTime * i));
                if (i != cutNum - 1) {
                    commands.add("-t");
                    commands.add(parseTimeToString(eachPartTime));
                }
                commands.add("-i");
                commands.add(filePath);
                commands.add("-codec");
                commands.add("copy");
                commands.add(fileFolder + File.separator + fileName[0] + "_part" + i + "." + fileName[1]);
                cutedVideoPaths.add(fileFolder + File.separator + fileName[0] + "_part" + i + "." + fileName[1]);
                newRunCommand(commands);
            }
        }
        return cutedVideoPaths;
    }

    private static Result newRunCommand(List<String> command) {
        log.info("相关命令 command:{}", command);
        Result result = new Result(false, "");
        ProcessBuilder builder = new ProcessBuilder(command);
        builder.redirectErrorStream(true);
        try {
            Process process = builder.start();
            final StringBuilder stringBuilder = new StringBuilder();
            final InputStream inputStream = process.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }

            while ((line = reader.readLine()) != null) {

            }
            if (reader != null) {
                reader.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        } catch (Exception e) {
            throw new RuntimeException("ffmpeg执行异常" + e.getMessage());
        }
        return result;
    }

    /**
     * 获取视频文件时长
     *
     * @param file 文件
     * @return 时长 格式hh:MM:ss
     * @throws FileNotFoundException 视频不存在抛出此异常
     */
    private static String getVideoTime(File file) throws FileNotFoundException {
        if (!file.exists()) {
            throw new FileNotFoundException(file.getAbsolutePath() + "不存在");
        }
        List<String> commands = new ArrayList<String>();
        commands.add("ffmpeg");
        commands.add("-i");
        commands.add(file.getAbsolutePath());
        Result result = newRunCommand(commands);
        String msg = result.getMsg();
        if (result.isSuccess()) {
            Pattern pattern = Pattern.compile("\\d{2}:\\d{2}:\\d{2}");
            Matcher matcher = pattern.matcher(msg);
            String time = "";
            while (matcher.find()) {
                time = matcher.group();
            }
            return time;
        } else {
            return "";
        }
    }

    /**
     * 获取文件大小
     *
     * @param file 去的文件长度，单位为字节b
     * @return 文件长度的字节数
     * @throws FileNotFoundException 文件未找到异常
     */
    private static long getVideoFileLength(File file) throws FileNotFoundException {
        if (!file.exists()) {
            throw new FileNotFoundException(file.getAbsolutePath() + "不存在");
        }
        return file.length();
    }

    /**
     * 将字符串时间格式转换为整型，以秒为单位
     *
     * @param timeString 字符串时间时长
     * @return 时间所对应的秒数
     */
    public  static int parseTimeToSecond(String timeString) {
        Pattern pattern = Pattern.compile("\\d{2}:\\d{2}:\\d{2}");
        Matcher matcher = pattern.matcher(timeString);
        if (!matcher.matches()) {
            try {
                throw new Exception("时间格式不正确");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        String[] time = timeString.split(":");
        return Integer.parseInt(time[0]) * 3600 + Integer.parseInt(time[1]) * 60 + Integer.parseInt(time[2]);
    }

    /**
     * 将秒表示时长转为00:00:00格式
     *
     * @param second 秒数时长
     * @return 字符串格式时长
     */
    public static String parseTimeToString(int second) {
        int end = second % 60;
        int mid = second / 60;
        if (mid < 60) {
            return mid + ":" + end;
        } else if (mid == 60) {
            return "1:00:" + end;
        } else {
            int first = mid / 60;
            mid = mid % 60;
            return first + ":" + mid + ":" + end;
        }
    }
}
