package com.coderdream.freeapps.util.bbc;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.bytedeco.opencv.presets.opencv_core.Str;

/**
 * @author CoderDream
 */
public class CallCmdUtil {

    public static void main(String[] args) {
        String folderName = "230413";
        CallCmdUtil.genWxSrt(folderName);
    }


    public static void genWxSrt(String folderName) {
        try {
            String audioFileName = CommonUtil.getFullPathFileName(folderName, "audio5", ".mp3");
            String scriptFileName = CommonUtil.getFullPathFileName(folderName, "script_dialog_wx2", ".txt");
            String srtFileName = CommonUtil.getFullPathFileName(folderName, "wx_eng", ".srt");

            List<String> arrayList = new ArrayList();
            String cmd = "python -m aeneas.tools.execute_task ";
            cmd += "\"" + audioFileName + "\" ";
//            cmd += scriptFileName + " ";
            cmd += "\"" + scriptFileName + "\" ";
            cmd += "\"task_language=eng|os_task_file_format=srt|is_text_type=plain\" ";
//            cmd += srtFileName;
            cmd += "\"" + srtFileName + "\" ";
            cmd = cmd.replaceAll( "\\\\",   "\\\\\\\\");
            arrayList.add(cmd);
//            arrayList.add("--json");
//            arrayList.add("https://www.iqiyi.com/v_1hz54gdreig.html");

            // 执行命令, 返回一个子进程对象（命令在子进程中执行）
            ProcessBuilder processBuilder = new ProcessBuilder();
            Process process = processBuilder.command(arrayList).start();
            InputStream inputStream = process.getInputStream();
            byte[] bytes = new byte[1024];
            String line;
            System.out.println("-----------获得数据-------------");
            while (inputStream.read(bytes) != -1) {
                line = new String(bytes, "utf-8");
                System.out.println("获得数据：" + line);
            }
            System.out.println("-------------------------------");
            inputStream.close();

            InputStream errorStream = process.getErrorStream();
            bytes = new byte[1024];
            while (errorStream.read(bytes) != -1) {
                line = new String(bytes, "utf-8");
                System.out.println("获得errorStream数据：" + line);
            }
            errorStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void genWxSrt2(String folderName) {
        try {
            String audioFileName = CommonUtil.getFullPathFileName(folderName, "audio5", ".mp3");
            String scriptFileName = CommonUtil.getFullPathFileName(folderName, "script_dialog_wx2", ".txt");
            String srtFileName = CommonUtil.getFullPathFileName(folderName, "wx_eng", ".srt");

            List<String> arrayList = new ArrayList();
            String cmd = "python -m aeneas.tools.execute_task ";
            cmd += audioFileName + " ";
            cmd += scriptFileName + " ";
            cmd += "\"task_language=eng|os_task_file_format=srt|is_text_type=plain\" ";
            cmd += srtFileName;
            cmd = cmd.replaceAll( "\\\\",   "\\\\\\\\");
            arrayList.add(cmd);
//            arrayList.add("--json");
//            arrayList.add("https://www.iqiyi.com/v_1hz54gdreig.html");

            // 执行命令, 返回一个子进程对象（命令在子进程中执行）
            ProcessBuilder processBuilder = new ProcessBuilder();
            Process process = processBuilder.command(arrayList).start();
            InputStream inputStream = process.getInputStream();
            byte[] bytes = new byte[1024];
            String line;
            System.out.println("-----------获得数据-------------");
            while (inputStream.read(bytes) != -1) {
                line = new String(bytes, "utf-8");
                System.out.println("获得数据：" + line);
            }
            System.out.println("-------------------------------");
            inputStream.close();

            InputStream errorStream = process.getErrorStream();
            bytes = new byte[1024];
            while (errorStream.read(bytes) != -1) {
                line = new String(bytes, "utf-8");
                System.out.println("获得errorStream数据：" + line);
            }
            errorStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
