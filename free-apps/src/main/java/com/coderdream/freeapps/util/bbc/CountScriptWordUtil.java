package com.coderdream.freeapps.util.bbc;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import com.coderdream.freeapps.enums.VocLevelEnum;
import com.coderdream.freeapps.model.WordEntity;
import com.coderdream.freeapps.model.WordInfo;
import com.coderdream.freeapps.util.nlp.CoreNlpUtils;
import com.coderdream.freeapps.util.other.CdExcelUtil;
import com.coderdream.freeapps.util.other.CdFileUtils;
import com.coderdream.freeapps.util.other.TxtUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.beam.vendor.grpc.v1p43p2.com.google.gson.internal.LinkedHashTreeMap;
import org.springframework.beans.BeanUtils;

/**
 * @author CoderDream
 */
public class CountScriptWordUtil {

    public static void main(String[] args) {
        String folderName = "E:\\02_\\电影\\Oppenheimer.2023.IMAX.2160p.BluRay.x265.10bit.DTS-HD.MA.5.1-WiKi\\";
        String fileName = "test.txt";
        CountScriptWordUtil.writeToScriptFile(folderName, fileName);
    }

    public static void writeToScriptFile(String folderName, String fileName) {
        List<List<String>> srtScript = CdFileUtils.getSrtScript(folderName, fileName);
        if (CollectionUtil.isNotEmpty(srtScript)) {
            if (srtScript.size() == 2) {
                List<String> stringBuilderCn = srtScript.get(0);
                List<String> stringBuilderEn = srtScript.get(1);

                String srcFileNameCn = folderName + "cn_" + fileName;
                // 中文文本
                CdFileUtils.writeToFile(srcFileNameCn, stringBuilderCn);

                String srcFileNameEn = folderName + "en_" + fileName;
                // 英文文本
                CdFileUtils.writeToFile(srcFileNameEn, stringBuilderEn);
            }
        }
    }
}
