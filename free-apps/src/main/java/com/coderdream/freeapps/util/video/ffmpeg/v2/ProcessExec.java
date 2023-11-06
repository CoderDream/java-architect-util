package com.coderdream.freeapps.util.video.ffmpeg.v2;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author CoderDream
 */
public class ProcessExec {

    private Process process;

    public void execute(Map<String, String> dto) {
        StringBuffer waterlogo = new StringBuffer();
        waterlogo.append("-i ");
        if (null != dto.get("input_path") && StringUtils.isNotEmpty(dto.get("input_path"))) {
            waterlogo.append(dto.get("input_path"));
        }
        waterlogo.append("  -vf \"movie=");
        if (null != dto.get("logo") && StringUtils.isNotEmpty(dto.get("logo"))) {
            waterlogo.append(dto.get("logo"));
        }
        waterlogo.append(",scale= 60: 30");
        waterlogo.append(" [watermark]; [in][watermark] overlay=main_w-overlay_w-10:main_h-overlay_h-10 [out]\" ");
        if (null != dto.get("video_converted_path") && StringUtils.isNotEmpty(dto.get("video_converted_path"))) {
            waterlogo.append(dto.get("video_converted_path"));
        }
        System.out.println(waterlogo);
        Runtime run = Runtime.getRuntime();
        String ffmegPath = null;
        if (StringUtils.isNotEmpty(dto.get("ffmpeg_path"))) {
            ffmegPath = dto.get("ffmpeg_path");
        }
// 执行命
        try {
            ffmegPath = "D:\\01_Software\\01_Video_Maker\\ffmpeg-2023-06-15\\bin\\ffmpeg.exe";
            String commandStr = ffmegPath + " -i D:\\202307082232.mp4 -ss 00:00:00 -codec copy -t 15 D:\\202307082232_15.mp4";
//            Process process = run.exec(ffmegPath + waterlogo);
            Process process = run.exec(commandStr);
// 异步读取输出
            InputStream inputStream = process.getInputStream();
            InputStream errorStream = process.getErrorStream();
                    /* BufferedReader br=new BufferedReader(new InputStreamReader(inputStream,"gbk"));
                     String str1="";
                     while((str=br.readLine())!=null){
                         System.out.println(str1);
                     }*/

            ExecutorService service = Executors.newFixedThreadPool(2);

            ResultStreamHandler inputStreamHandler = new ResultStreamHandler(inputStream);
            ResultStreamHandler errorStreamHandler = new ResultStreamHandler(errorStream);

            service.execute(inputStreamHandler);
            service.execute(errorStreamHandler);

            process.waitFor();
            service.shutdownNow();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
