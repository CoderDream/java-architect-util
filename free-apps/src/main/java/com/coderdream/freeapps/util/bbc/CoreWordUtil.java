package com.coderdream.freeapps.util.bbc;

import com.coderdream.freeapps.model.CoreWordInfo;
import com.coderdream.freeapps.model.WordInfo;
import com.coderdream.freeapps.util.other.CdFileUtils;
import com.coderdream.freeapps.util.other.TxtUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author CoderDream
 */
public class CoreWordUtil {

    public static void main(String[] args) {
        String folderName = "230202";
        CoreWordUtil.genCoreWordTable(folderName);
    }

    public static void genCoreWordTable(String folderName) {
        String fileName = "voc_cn";
        List<CoreWordInfo> wordInfoList = process(folderName, fileName);
        String folderPath =
            CdFileUtils.getResourceRealPath() + File.separatorChar + "data" + File.separatorChar + "dict";
        String templateFileName = folderPath + File.separator + "核心词汇表.xlsx";

        // 方案1 一下子全部放到内存里面 并填充
        String excelFileName = CommonUtil.getFullPathFileName(folderName, folderName, "_核心词汇表.xlsx");
        String sheetName = "核心词汇表";

        MakeExcel.listFill(templateFileName, excelFileName, sheetName, wordInfoList);
    }

    public static List<CoreWordInfo> process(String folderName, String fileName) {
        List<CoreWordInfo> wordInfoList = new ArrayList<>();

        String filePath = CommonUtil.getFullPathFileName(folderName, fileName, ".txt");

        List<String> stringList = TxtUtil.readTxtFileToListWithEmpty(filePath);
        CoreWordInfo coreWordInfo;
        for (String string : stringList) {
            coreWordInfo = new CoreWordInfo();
            coreWordInfo.setContent(string);
            wordInfoList.add(coreWordInfo);
        }

        return wordInfoList;
    }


}
