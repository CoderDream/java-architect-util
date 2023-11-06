package com.coderdream.freeapps.util.video.ffmpeg;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FfmpegUtils2 {

    private static String ffmpegPath = "D:\\01_Software\\01_Video_Maker\\ffmpeg-2023-06-15\\bin\\ffmpeg.exe";  //ffmepg的绝对路径


    /**
     * 压缩视频
     *
     * @param convertFile 待转换的文件
     * @param targetFile  转换后的目标文件
     */
    public static void toCompressFile(String convertFile, String targetFile) throws IOException {
        List<String> command = new ArrayList<String>();
        /**将视频压缩为 每秒15帧 平均码率600k 画面的宽与高 为1280*720*/
        command.add(ffmpegPath);
        command.add("-i");
        command.add(convertFile);
        command.add("-r");
        command.add("15");
        command.add("-b:v");
        command.add("600k");
        command.add("-s");
        command.add("1280x720");
        command.add(targetFile);
        ProcessBuilder builder = new ProcessBuilder(command);
        Process process = null;
        try {
            process = builder.start();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // 使用这种方式会在瞬间大量消耗CPU和内存等系统资源，所以这里我们需要对流进行处理

        InputStream errorStream = process.getErrorStream();
        InputStreamReader inputStreamReader = new InputStreamReader(errorStream);
        BufferedReader br = new BufferedReader(inputStreamReader);
        String line = "";
        while ((line = br.readLine()) != null) {
        }
        if (br != null) {
            br.close();
        }
        if (inputStreamReader != null) {
            inputStreamReader.close();
        }
        if (errorStream != null) {
            errorStream.close();
        }
        log.info("-------------------压缩完成---转存文件--" + targetFile + "-------------");
    }

    /**
     * ffmpeg合并多个视频文件
     *
     * @param list       需要合并的多视频url地址以List存放
     * @param outputDir  此处是ffmpeg 配置地址，可写死如“E:/ffmpeg/bin/ffmpeg.exe”
     * @param outputFile 合并后的视频存放地址，如：E:/mergevideo.mp4
     * @date: 2021/4/17 9:31
     * @return: void
     */
    public static String mergeVideo(List<String> list, String outputDir, String outputFile) {

        try {
            String format1 = "%s -i %s -c copy -bsf:v h264_mp4toannexb -f mpegts %s";
            List<String> commandList = new ArrayList<>(6);
            List<String> inputList = new ArrayList<>(6);
            for (int i = 0; i < list.size(); i++) {
                String input = String.format("input%d.ts", i + 1);
                String command = String.format(format1, ffmpegPath, list.get(i), outputDir + input);
                commandList.add(command);
                inputList.add(input);
            }
            String command = getCommand(outputDir, outputFile, inputList);
            commandList.add(command);
            Boolean falg = Boolean.FALSE;
            for (int i = 0; i < commandList.size(); i++) {
                if (execCommand(commandList.get(i)) > 0) {
                    falg = true;
                }
            }
            if (falg) {
                for (int i = 0; i < inputList.size(); i++) {
                    if (i != commandList.size() - 1) {
                        File file = new File(outputDir + inputList.get(i));
                        file.delete();
                    }
                }
//                //删除压缩的文件
//                for (String s:list
//                     ) {
//                    new File(s).delete();
//                }
                return outputFile;
            } else {
                return "fail";
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("-----合并失败!!!!!!" + outputFile);
            return "fail";
        }

    }

    private static Integer execCommand(String command) {

        log.info("execCommand.exec command={}", command);
        try {
            Process process = Runtime.getRuntime().exec(command);
            //获取进程的标准输入流
            final InputStream is1 = process.getInputStream();
            //获取进城的错误流
            final InputStream is2 = process.getErrorStream();
            //启动两个线程，一个线程负责读标准输出流，另一个负责读标准错误流
            readInputStream(is1);
            readInputStream(is2);
            process.waitFor();
            process.destroy();

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
            log.info("-----操作成功" + command + " " + format.format(new Date()));
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("-----操作失败" + command);
            return -1;
        }
    }

    private static void readInputStream(InputStream inputStream) {
        new Thread(() -> {
            BufferedReader br1 = new BufferedReader(new InputStreamReader(inputStream));
            try {
                String line1;
                while ((line1 = br1.readLine()) != null) {
                    if (line1 != null) {
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 将视频分割为小段
     *
     * @param fileName   源文件名字(带路径)
     * @param outputPath 输出文件路径，会在该路径下根据系统时间创建目录，并在此目录下输出段视频
     * @param videoTime  总时间，单位 分钟
     * @param periodTime 小段视频时长 单位 分钟
     * @param merge      true合并，false单独分割 说明：是否将整个视频结尾部分不足一次分割时间的部分，合并到最后一次分割的视频中，即false会比true多生成一段视频
     */
    public static List<Map<String, Object>> splitVideoFile(String fileName, String outputPath, float videoTime,
        int periodTime, boolean merge) {
        final String TAG = "----------------------------";

        // 在outputPath路径下根据系统时间创建目录
        File file = createFileBySysTime(outputPath);
        if (file == null) {
            System.out.println("分割视频失败，创建目录失败");
            return null;
        }
        outputPath = file.getPath() + File.separator; // 更新视频输出目录

        // 计算视频分割的个数
        int count;// 分割为几段
        float remain = 0; // 不足一次剪辑的剩余时间
        if (merge) {
            count = (int) (videoTime / periodTime);
            remain = videoTime % periodTime; // 不足一次剪辑的剩余时间
        } else {
            count = (int) (videoTime / periodTime) + 1;
        }
        System.out.println("将视频分割为" + count + "段，每段约" + periodTime + "分钟");

        String indexName; // 第 i 个视频，打印日志用
        final String FFMPEG = ffmpegPath;
        String startTime; // 每段视频的开始时间
        String periodVideoName; // 每段视频的名字，名字规则：视频i_时间段xx_yy
        float duration; // 每次分割的时长
        String command;// 执行的命令
        // 得到视频后缀 如.mp4
        String videoSuffix = fileName.substring(fileName.lastIndexOf("."));//得到点后面的后缀，包括点
        Runtime runtime = Runtime.getRuntime(); // 执行命令者

        List<Map<String, Object>> list = new ArrayList<>();

        // 将视频分割为count段
        for (int i = 0; i < count; i++) {
            Map<String, Object> map = new HashMap<>();
            indexName = "第" + (i + 1) + "个视频";

            // 决定是否将整个视频结尾部分不足一次的时间，合并到最后一次分割的视频中
            if (merge) {
                if (i == count - 1) {
                    duration = periodTime * 60 + remain * 60;// 将整个视频不足一次剪辑的时间，拼接在最后一次剪裁中
                    if (periodTime * i / 60 >= 1) {
                        startTime = "0" + periodTime * i / 60 + ":00:00";
                    } else {
                        startTime = periodTime * i + ":00";
                    }
                    periodVideoName = "video" + (i + 1) + "_" + periodTime * i + "_end" + videoSuffix;
                } else {
                    duration = periodTime * 60;
                    if (periodTime * i / 60 >= 1) {
                        startTime = "0" + periodTime * i / 60 + ":00:00";
                    } else {
                        startTime = periodTime * i + ":00";
                    }
                    periodVideoName =
                        "video" + (i + 1) + "_" + periodTime * i + "_" + periodTime * (i + 1) + videoSuffix;
                }
            } else {
                duration = periodTime * 60;
                if (periodTime * i / 60 >= 1) {
                    startTime = "0" + periodTime * i / 60 + ":00:00";
                } else {
                    startTime = periodTime * i + ":00";
                }
                periodVideoName = "video" + (i + 1) + "_" + periodTime * i + "_" + periodTime * (i + 1) + videoSuffix;
            }

            // 执行分割命令
            try {
                // 创建命令
                command =
                    FFMPEG + " -ss " + startTime + " -accurate_seek " + " -i " + fileName + " -c copy -t " + duration
                        + " " + outputPath + periodVideoName;

                System.out.println(TAG);
                System.out.println(indexName);
                System.out.println("执行命令：" + command);
                runtime.exec(command);
                System.out.println(indexName + "分割成功");

                map.put("videoPath", (outputPath + periodVideoName).replace("\\", "/"));
                map.put("count", i);
                list.add(map);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(indexName + "分割失败!!!!!!");
            }
        }
        //删除原来的大视频
        new File(fileName).delete();

        return list;
    }

    /**
     * 在指定目录下根据系统时间创建文件夹 文件名字eg：2019-07-02-23-56-31
     *
     * @param path 路径：eg： "/Users/amarao/业余/剪辑/output/"; 结果：创建成功/Users/amarao/业余/剪辑/output/2019-07-03-10-28-05
     *             <p>
     *             步骤： 1. 读取系统时间 2. 格式化系统时间 3. 创建文件夹
     *             <p>
     *             参考：http://www.bubuko.com/infodetail-1685972.html
     */
    public static File createFileBySysTime(String path) {

        // 1. 读取系统时间
        Calendar calendar = Calendar.getInstance();
        Date time = calendar.getTime();

        // 2. 格式化系统时间
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        String fileName = format.format(time); //获取系统当前时间并将其转换为string类型,fileName即文件名

        // 3. 创建文件夹
        String newPath = path + fileName;
        File file = new File(newPath);
        //如果文件目录不存在则创建目录
        if (!file.exists()) {
            if (!file.mkdir()) {
                System.out.println("当前路径不存在，创建失败");
                return null;
            }
        }
        System.out.println("创建成功" + newPath);
        return file;
    }

//    /**
//     * 获取视频时长 单位/秒
//     *
//     * @param video
//     * @return
//     */
//    public static long getVideoDuration(File video) {
//        long duration = 0L;
//        FFmpegFrameGrabber ff = new FFmpegFrameGrabber(video);
//        try {
//            ff.start();
//            duration = ff.getLengthInTime() / (1000 * 1000 * 60);
//            ff.stop();
//        } catch (FrameGrabber.Exception e) {
//            e.printStackTrace();
//        }
//        return duration;
//    }

    /**
     * 剪切视频 videoInputPath 需要处理的视频路径 startTime： 截取的开始时间 格式为 00:00:00（时分秒） endTime: 截取的结束时间 格式为00:03:00（时分秒） devIp： 通道号
     * 业务存在 ，可自行删除
     */
    public static String videoClip(String videoInputPath, String startTime, String endTime, String devIp)
        throws IOException {

        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dtf = new SimpleDateFormat("yyyyMMddHHmmss");

        //判断转码文件是否存在
        if (!new File(videoInputPath).exists()) {
            System.out.println("需要处理的视频不存在");
            return null;
        }
        StringBuffer videoOutPath = new StringBuffer();
        videoOutPath.append("C:/video/playBack/" + devIp + "/" + sdf1.format(new Date()) + "/clip/");
        File file = new File(videoOutPath.toString());
        if (!file.exists()) {
            file.mkdirs();
        }
        videoOutPath.append(dtf.format(new Date()) + ".mp4");
        List<String> command = new ArrayList<String>();
        command.add(ffmpegPath);
        command.add("-ss");
        command.add(startTime);
        command.add("-t");
        command.add(endTime);
        command.add("-i");
        command.add(videoInputPath);
        command.add("-c");
        command.add("copy");
        command.add(videoOutPath.toString());
        command.add("-y");
        ProcessBuilder builder = new ProcessBuilder(command);
        Process process = null;
        try {
            process = builder.start();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        InputStream errorStream = process.getErrorStream();
        InputStreamReader inputStreamReader = new InputStreamReader(errorStream);
        BufferedReader br = new BufferedReader(inputStreamReader);
        String line = "";
        while ((line = br.readLine()) != null) {
        }
        if (br != null) {
            br.close();
        }
        if (inputStreamReader != null) {
            inputStreamReader.close();
        }
        if (errorStream != null) {
            errorStream.close();
        }

        return videoOutPath.toString();
    }

    /*
     * 视频转gif
     *inputVideoPath   需要转换的视频
     * createGif  gif保存的位置
     * */
    public static String mp4ToGif(String inputVideoPath, String createGif) {
        String name = UUID.randomUUID().toString().replaceAll("-", "");
        String paletteFile = createGif + name + ".png";
        String gifFile = createGif + name + ".gif";
        boolean isComplete = false;
        //操作ffmpeg生成gif图片
        for (int i = 0; i < 5; i++) {
            try {
                //生成调色板
                Process p = new ProcessBuilder()
                    .command(ffmpegPath,
                        "-v", "warning",
                        "-ss", "2",
                        "-t", "10",
                        "-i", inputVideoPath,
                        "-vf", "fps=5,scale=400:-1:flags=lanczos,palettegen",
                        "-y", paletteFile, "-vn")
                    .redirectError(new File("stderr.txt"))
                    .start();
                isComplete = p.waitFor(10, TimeUnit.SECONDS);

                if (!isComplete) {
                    System.out.println("生成调色板出错");
                } else {
                    List<String> command = new ArrayList<String>();
                    /**将视频压缩为 每秒15帧 平均码率600k 画面的宽与高 为1280*720*/
                    command.add(ffmpegPath);
                    command.add("-v");
                    command.add("warning");
                    command.add("-ss");
                    command.add("2");
                    command.add("-t");
                    command.add("10");
                    command.add("-i");
                    command.add(inputVideoPath);
                    command.add("-i");
                    command.add(paletteFile);
                    command.add("-lavfi");
                    command.add("fps=5,scale=400:-1:flags=lanczos [x]; [x][1:v] paletteuse");
                    command.add("-y");
                    command.add(gifFile);
                    command.add("-vn");
                    ProcessBuilder builder = new ProcessBuilder(command);
                    Process process = null;
                    try {
                        process = builder.start();
                        isComplete = process.waitFor(10, TimeUnit.SECONDS);
                        if (isComplete) {
                            new File(paletteFile).delete();
                            System.out.println("生成gif成功");
                            break;
                        } else {
                            System.out.println("生成gif出错");
                        }
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
//                process = new ProcessBuilder()
//                            .command(ffmpegPath,
//                                    "-v", "warning",
//                                    "-ss", "2",
//                                    "-t", "10",
//                                    "-i", "E:\\Video_2021-05-14_113013.mp4",
//                                    "-i", paletteFile,
//                                    "-lavfi", "fps=5,scale=400:-1:flags=lanczos [x]; [x][1:v] paletteuse",
//                                    "-y", gifFile, "-vn")
//                            .redirectError(new File("stderr.txt"))
//                            .start();
//                    isComplete = process.waitFor(10, TimeUnit.SECONDS);
//                    if (isComplete) {
//                        System.out.println("生成gif成功");
//                        break;
//                    } else {
//                        System.out.println("生成gif出错");
//                    }
                }
            } catch (Exception e) {
                System.out.println("生成gif出错");
            }
        }

        return gifFile;
    }

    /**
     * 拼接执行新的videoUrl command命令
     *
     * @param outputFile
     * @param newfilePath
     * @param inputList
     * @date: 2021/4/17 11:29
     * @return: java.lang.StringBuffer
     */
    private static String getCommand(String outputFile, String newfilePath, List<String> inputList) {

        StringBuffer tsPath = new StringBuffer();
        tsPath.append(ffmpegPath);
        tsPath.append(" -i ");
        tsPath.append("\"");
        tsPath.append("concat:");
        for (int t = 0; t < inputList.size(); t++) {
            tsPath.append(outputFile);
            if (t != inputList.size() - 1) {
                tsPath.append(inputList.get(t) + "|");
            } else {
                tsPath.append(inputList.get(t));
            }
        }
        tsPath.append("\"");
        tsPath.append(" -c copy -bsf:a aac_adtstoasc -movflags +faststart ");
        tsPath.append(newfilePath);
        return tsPath.toString();
    }
}
