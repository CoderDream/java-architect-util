package com.coderdream.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Vincente
 * @date 2020/08/09-21:48
 * @desc
 **/
public class MergeFilesToOne {
    public static void main(String[] args) throws IOException {
        String in = "E:\\02_\\S01";
        in = "F:\\BT\\Kids.Baking.Championship.S04.1080p.AMZN.WEB-DL.DD+2.0.H.264-SiGMA\\S04";
        String out = "D:\\S04.srt";
        // 合并
        mergeFileToOne(in, out);
    }

    /**
     * 递归获取文件夹以及子文件夹下的文件
     *
     * @param path
     * @return
     */
    public static List getFiles(String path) {
        List list = new ArrayList();
        File[] files = new File(path).listFiles();
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) {
                List node = getFiles(files[i].getPath());
                list.addAll(node);
            } else {
                list.add(files[i].getPath());
            }
        }
        return list;
    }


    /**
     * 合并文件
     *
     * @param inPath
     * @param outPath
     * @throws IOException
     */
    public static void mergeFileToOne(String inPath, String outPath) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(outPath));
        System.out.println("--->合并目标文件夹路径：" + inPath);
        System.out.println("--->正在读取文件...");
        List<String> files = getFiles(inPath);
        System.out.println("--->合并文件数量：" + files.size());
        for (int i = 0; i < files.size(); i++) {
            File file = new File(files.get(i));
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                bw.write(line);
                bw.newLine();
            }
            bufferedReader.close();
        }
        System.out.println("--->合并完成！");
        System.out.println("--->合并文件输出路径：" + outPath);
        bw.close();
    }
}
