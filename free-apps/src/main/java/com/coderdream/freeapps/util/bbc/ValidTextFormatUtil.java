package com.coderdream.freeapps.util.bbc;

import cn.hutool.core.io.FileUtil;
import com.coderdream.freeapps.model.DownloadInfoEntity;
import com.coderdream.freeapps.model.SubtitleBaseEntity;
import com.coderdream.freeapps.util.other.CdFileUtils;
import com.coderdream.freeapps.util.proxy.HtmlUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author CoderDream
 */
public class ValidTextFormatUtil {


    public final static String FOLDER_NAME = "230921";

    public static void main(String[] args) {
//        String folderName = "230518";
//        String fileName = "chn_raw";
//        processCnSrt(folderName, fileName);//

//        replace();
//        process("pdf", "2022", "01");
        process("pdf", "2022");
    }

    /**
     * <pre>
     *     整理中文字幕：
     *     1、替换中文冒号为英文冒号；
     *     2、去掉中文字幕中多余的空格，先【 -> 】替换为【#->#】，然后去掉空格，最后再换回来，（【#->#】替换为【 -> 】）
     *
     * </pre>
     *
     * @param folderName
     * @param fileName
     */
    public static void process(String extFileName, String... args) {

        List<DownloadInfoEntity> downloadInfoEntityListTemp = HtmlUtil.getDownloadHtmlInfo(extFileName, args);

        // script_dialog.txt
        String scriptDialogFileName = "script_dialog.txt";
        String vocFileName = "voc.txt";
        Map<String, String> dialogMap = new LinkedHashMap<>();
        Map<String, String> vocMap = new LinkedHashMap<>();

        List<String> list = new ArrayList<>();
        for (DownloadInfoEntity downloadInfoEntity : downloadInfoEntityListTemp) {
            String key = downloadInfoEntity.getFileName().substring(0, 6);
            int dialogSize = getContentLineSize(downloadInfoEntity.getPath() + scriptDialogFileName);
            int vocSize = getContentLineSize(downloadInfoEntity.getPath() + vocFileName);
            String dialogResult = dialogSize % 3 == 0 ? "true":"false";
            String vocResult = vocSize % 3 == 0 ? "true":"false";

            list.add(key + "\t" + dialogSize + "\t" + dialogResult+ "\t" + vocSize + "\t" + vocResult);
        }

        String newFileName = "valid.txt";
        CdFileUtils.writeToFile(newFileName, list);
    }



    /**
     * <pre>
     *     整理中文字幕：
     *     1、替换中文冒号为英文冒号；
     *     2、去掉中文字幕中多余的空格，先【 -> 】替换为【#->#】，然后去掉空格，最后再换回来，（【#->#】替换为【 -> 】）
     *
     * </pre>
     *
     * @param folderName
     * @param fileName
     */
    public static int getContentLineSize(String srcFileName) {
        File file = new File(srcFileName);
        if(!file.exists()) {
            return -1;
        }

        List<String> strings1 = FileUtil.readLines(srcFileName, "UTF-8");
//        System.out.println(strings1.size());

//        List<String> strings = CdFileUtils.readFileContent(srcFileName);
        return strings1.size() + 1;
    }

}


