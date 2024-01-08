package com.coderdream.freeapps.util.bbc;

import com.coderdream.freeapps.model.CoreWordInfo;
import com.coderdream.freeapps.model.WordEntity;
import com.coderdream.freeapps.model.WordInfo;
import com.coderdream.freeapps.util.other.CdExcelUtil;
import com.coderdream.freeapps.util.other.CdFileUtils;
import com.coderdream.freeapps.util.other.TxtUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.beam.vendor.grpc.v1p43p2.com.google.gson.internal.LinkedHashTreeMap;

/**
 * @author CoderDream
 */
public class AdvancedWordUtil {

    public static void main(String[] args) {
        String folderName = "230223";
        AdvancedWordUtil.genCoreWordTable(folderName);
    }

    public static void genCoreWordTable(String folderName) {
        List<WordInfo> wordInfoList = getAdvancedWordList(folderName);
        String folderPath =
            CdFileUtils.getResourceRealPath() + File.separatorChar + "data" + File.separatorChar + "dict";
        String templateFileName = folderPath + File.separator + "高级词汇表.xlsx";

        // WordInfo

        // 方案1 一下子全部放到内存里面 并填充
        String excelFileName = CommonUtil.getFullPathFileName(folderName, folderName, "_高级词汇表.xlsx");
        String sheetName = "四六级及以上";

        MakeExcel.fillWordEntityList(templateFileName, excelFileName, sheetName, wordInfoList);
    }

    /**
     *
     * @return
     */
    public static List<WordInfo> getAdvancedWordList(String folderName) {
        String fileName = folderName + "_完整词汇表";
        String filePath = CommonUtil.getFullPathFileName(folderName, fileName, ".xlsx");
        List<WordInfo> wordEntityList = CdExcelUtil.genWordInfoList(filePath, "四六级及以上");

        return wordEntityList;
    }

}
