package com.coderdream.freeapps.util.mytts.audio;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * https://blog.csdn.net/zj850324/article/details/122613974
 * Java获取MP3音频播放时长（总结）
 */
public class FfmpegCmdDurationUtils {

    private final static String DURATION_START = "Duration:";

    private final static String KEY_FOR_HOUR = "hour";
    private final static String KEY_FOR_MINUTE = "minute";
    private final static String KEY_FOR_SECOND = "second";
    private final static String KEY_FOR_MILLISECOND = "millisecond";

    //小时 * 60 = 分
    //分 * 60 = 秒
    private final static BigDecimal GAP_60 = new BigDecimal("60");
    //秒* 1000 = 毫秒
    private final static BigDecimal GAP_1000 = new BigDecimal("1000");

    /**
     * TYPE_0:小时 TYPE_1:分钟 TYPE_2:秒钟 TYPE_3:毫秒
     */
    public final static int TYPE_0 = 0;
    public final static int TYPE_1 = 1;
    public final static int TYPE_2 = 2;
    public final static int TYPE_3 = 3;

    //ffmpeg执行命令
//    private final static String cmd_4_info = "-i D:\\MP3\\22.mp3";

    public static void main(String[] args) {

        String fileName = "D:\\12_iOS_Android\\202306\\20230604\\20230604_bi\\20230604_bi.mp3";
        try {
//            System.out.println(String.format("获取时长：%s 小时", duration(cmd_4_info,TYPE_0).doubleValue()));
//            System.out.println(String.format("获取时长：%s 分钟", duration(cmd_4_info,TYPE_1).doubleValue()));
//            System.out.println(String.format("获取时长：%s 秒钟", duration(cmd_4_info,TYPE_2).doubleValue()));
//            System.out.println(String.format("获取时长：%s 毫秒", duration(cmd_4_info,TYPE_3).doubleValue()));
//            System.out.println("");
            System.out.println(String.format("获取时长（格式化）：%s", FfmpegCmdDurationUtils.durationFormat(fileName)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @throws
     * @Description: (获取格式化的时间duration ： such as : 00 : 01 : 03.32)
     * @param: @param fileName
     * @param: @return
     * @return: BigDecimal
     */
    public static String durationFormat(final String fileName) {
        String cmdStr = "-i ";
        cmdStr += fileName;
        String duration = null;
        try {
            String finalCmdStr = cmdStr;
            CompletableFuture<String> completableFutureTask = CompletableFuture.supplyAsync(() -> {
                String durationStr = null;
                //执行ffmpeg命令
                StringBuffer sText = getErrorStreamText(finalCmdStr);
                if (sText.indexOf(DURATION_START) > -1) {
                    String textOriginal = sText.toString();
                    //正则解析时间
                    Matcher matcher = patternDuration().matcher(textOriginal);
                    //正则提取字符串
                    while (matcher.find()) {
                        //such as:00:01:03.32
                        durationStr = matcher.group(1);
                        break;
                    }
                }
                return durationStr;
            }, ThreadPoolExecutorUtils.pool);

            duration = completableFutureTask.get();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return duration;
    }

    /**
     * @throws Exception
     * @throws
     * @Description: (获取时长)
     * @param: @param cmdStr ffmpeg命令，如：-i I:\\荣耀视频测试.mp4
     * @param: @param type 类型： TYPE_0:小时 TYPE_1:分钟 TYPE_2:秒钟 TYPE_3:毫秒
     * @param: @return
     * @return: BigDecimal
     */
    public static BigDecimal duration(final String cmdStr, int type) throws Exception {
        BigDecimal duration = new BigDecimal("00");
        Map<String, String> map;
        try {
            CompletableFuture<Map<String, String>> completableFutureTask = CompletableFuture.supplyAsync(() -> {
                Map<String, String> mapTmp = null;
                //执行ffmpeg命令
                StringBuffer sText = getErrorStreamText(cmdStr);
                if (sText.indexOf(DURATION_START) > -1) {
                    String textOriginal = sText.toString();
                    //正则解析时间
                    Matcher matcher = patternDuration().matcher(textOriginal);
                    //正则提取字符串
                    while (matcher.find()) {
                        //such as:00:01:03.32
                        String durationStr = matcher.group(1);
                        mapTmp = getHourMinuteSecondMillisecond(durationStr);
                        break;
                    }
                }
                return mapTmp;
            }, ThreadPoolExecutorUtils.pool);

            map = completableFutureTask.get();
            if (null != map && map.size() > 0) {
                switch (type) {
                    case TYPE_0:
                        //小时
                        duration = duration.add(new BigDecimal(map.get(KEY_FOR_HOUR)));
                        break;
                    case TYPE_1:
                        //分钟
                        duration = duration.add(new BigDecimal(map.get(KEY_FOR_HOUR)).multiply(GAP_60))
                            .add(new BigDecimal(map.get(KEY_FOR_MINUTE)));
                        break;
                    case TYPE_2:
                        //秒
                        duration = duration.add(new BigDecimal(map.get(KEY_FOR_HOUR)).multiply(GAP_60).multiply(GAP_60))
                            .add(new BigDecimal(map.get(KEY_FOR_MINUTE)).multiply(GAP_60))
                            .add(new BigDecimal(map.get(KEY_FOR_SECOND)));
                        break;
                    case TYPE_3:
                        //毫秒
                        duration = duration.add(
                                new BigDecimal(map.get(KEY_FOR_HOUR)).multiply(GAP_60).multiply(GAP_60).multiply(GAP_1000))
                            .add(new BigDecimal(map.get(KEY_FOR_MINUTE)).multiply(GAP_60).multiply(GAP_1000))
                            .add(new BigDecimal(map.get(KEY_FOR_SECOND)).multiply(GAP_1000))
                            .add(new BigDecimal(map.get(KEY_FOR_MILLISECOND)));
                        break;
                    default:
                        throw new Exception("未知的类型！");
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return duration;
    }

    //模式
    public static Pattern patternDuration() {
        //"(?i)duration:\\s*([0-9\\:\\.]+)"-->匹配到时分秒即可，毫秒不需要
        return Pattern.compile("(?i)duration:\\s*([0-9\\:\\.]+)");
    }

    /**
     * @throws
     * @Description: (获取错误流)
     * @param: @param cmd_4_info
     * @param: @return
     * @return: StringBuffer
     */
    private static StringBuffer getErrorStreamText(String cmdStr) {
        //返回的text
        StringBuffer sText = new StringBuffer();
        FfmpegCmd ffmpegCmd = new FfmpegCmd();
        try {
            //错误流
            InputStream errorStream = null;
            //destroyOnRuntimeShutdown表示是否立即关闭Runtime
            //如果ffmpeg命令需要长时间执行，destroyOnRuntimeShutdown = false

            //openIOStreams表示是不是需要打开输入输出流:
            //	       inputStream = processWrapper.getInputStream();
            //	       outputStream = processWrapper.getOutputStream();
            //	       errorStream = processWrapper.getErrorStream();
            ffmpegCmd.execute(false, true, cmdStr);
            errorStream = ffmpegCmd.getErrorStream();

            //打印过程
            int len;
            while ((len = errorStream.read()) != -1) {
                char t = (char) len;
                sText.append(t);
            }

            //code=0表示正常
            ffmpegCmd.getProcessExitCode();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            ffmpegCmd.close();
        }
        //返回
        return sText;
    }

    /**
     * @throws
     * @Description: (获取duration的时分秒毫秒)
     * @param: durationStr  such as:00:01:03.32
     * @return: Map
     */
    private static Map<String, String> getHourMinuteSecondMillisecond(String durationStr) {
        HashMap<String, String> map = new HashMap<>(4);
        if (null != durationStr && durationStr.length() > 0) {
            String[] durationStrArr = durationStr.split("\\:");
            String hour = durationStrArr[0];
            String minute = durationStrArr[1];
            //特殊
            String second;
            String millisecond = "00";
            String secondTmp = durationStrArr[2];
            if (secondTmp.contains("\\.")) {
                String[] secondTmpArr = secondTmp.split("\\.");
                second = secondTmpArr[0];
                millisecond = secondTmpArr[1];
            } else {
                second = secondTmp;
            }
            map.put(KEY_FOR_HOUR, hour);
            map.put(KEY_FOR_MINUTE, minute);
            map.put(KEY_FOR_SECOND, second);
            map.put(KEY_FOR_MILLISECOND, millisecond);
        }
        return map;
    }

}
