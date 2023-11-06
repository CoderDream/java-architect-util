package com.coderdream.freeapps.util.bbc;

import com.coderdream.freeapps.model.SubtitleBaseEntity;
import com.coderdream.freeapps.util.other.CdFileUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author CoderDream
 */
public class ProcessCnSrtUtil {


    public final static String FOLDER_NAME = "230921";

    public static void main(String[] args) {
        String folderName = "230518";
        String fileName = "chn_raw";
        processCnSrt(folderName, fileName);//

//        replace();
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
    public static void processCnSrt(String folderName, String fileName) {
        String srcFileName = CommonUtil.getFullPathFileName(folderName, fileName, ".srt");
        List<SubtitleBaseEntity> subtitleBaseEntityList = CdFileUtils.readSrcFileContent(srcFileName);

        List<String> newScriptList = new ArrayList<>();

        String timeStr;
        String subtitle;
        for (SubtitleBaseEntity subtitleBaseEntity : subtitleBaseEntityList) {
            newScriptList.add(subtitleBaseEntity.getSubIndex().toString());
            timeStr = subtitleBaseEntity.getTimeStr();
            timeStr = timeStr.replaceAll("：", ":"); // 时间字符串的【中文冒号】替换为【英文冒号】
            newScriptList.add(timeStr);
            subtitle = subtitleBaseEntity.getSubtitle();
            subtitle = subtitle.replaceAll(" ", ""); // 去掉空格
            subtitle = subtitle.replaceAll("-", "- "); // 还原减号后面的空格
            subtitle = subtitle.replaceAll("6分钟英语", "六分钟英语");
            subtitle = subtitle.replaceAll("BBClearningenglish", "BBC learningenglish"); //BBC Radio 4
            subtitle = subtitle.replaceAll("BBCRadio4", "BBC Radio 4"); // 13249189450
            newScriptList.add(subtitle);
            newScriptList.add("");// 空行
        }

        String newFileName = CommonUtil.getFullPathFileName(folderName, "chn", ".srt");
        CdFileUtils.writeToFile(newFileName, newScriptList);
    }

}


