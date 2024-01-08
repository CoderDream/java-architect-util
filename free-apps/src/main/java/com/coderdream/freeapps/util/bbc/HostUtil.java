package com.coderdream.freeapps.util.bbc;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.excel.EasyExcel;
import com.coderdream.freeapps.model.ScriptEntity;
import com.coderdream.freeapps.util.easyexcel.TestFileUtil;
import com.coderdream.freeapps.util.other.CdExcelUtil;
import com.coderdream.freeapps.util.other.CdFileUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;

/**
 * @author CoderDream
 */
public class HostUtil {

    public static void main(String[] args) {

//        ep230907();
        String folderName = "220106";

        HostUtil.updateHostTextFile(folderName);

        List<String> stringList = getHost();
        System.out.println("##########################");
        for (int i = 0; i < stringList.size(); i++) {
            System.out.println("#" + i + "\t:length: " + stringList.get(i).length() + "\t\t: " + stringList.get(i));
//            System.out.println(s);
        }
    }

    /**
     * 获取主持人和嘉宾名字列表
     *
     * @return
     */
    public static List<String> getHost() {
        String folderPath =
            CdFileUtils.getResourceRealPath() + File.separatorChar + "data" + File.separatorChar + "dict";
        String filePath = folderPath + File.separator + "host.txt";
        List<String> hostList = FileUtil.readLines(filePath, "UTF-8");
        return hostList;
    }

    public static void writeHost(String folderName, List<String> talkerList) {

    }

    /**
     * 获取主持人和嘉宾名字集合
     *
     * @return
     */
    public static Set<String> getHostSet() {
        Set<String> hostSet = new TreeSet<>();
        List<String> hostList = getHost();
        for (String s : hostList) {
            hostSet.add(s.toLowerCase());
        }
        return hostSet;
    }


    public static List<String> updateHostTextFile(String folderName) {
        String newFileName = CommonUtil.getFullPathFileName(folderName, "script_dialog", ".txt");
        List<String> scriptList = CdFileUtils.readFileAddEndEmptyList(newFileName);

        List<ScriptEntity> scriptEntityList = new ArrayList<>();
        ScriptEntity scriptEntity;
        // 脚本列表是3的倍数，否则有问题
        if (scriptList.size() % 3 != 0) {
            System.out.println("###");
            return null;
        }
        for (int i = 0; i < scriptList.size(); i += 3) {
            if (StrUtil.isEmpty(scriptList.get(i + 2))) {
                scriptEntity = new com.coderdream.freeapps.model.ScriptEntity();
                scriptEntity.setTalker(scriptList.get(i));
                scriptEntity.setContent(scriptList.get(i + 1));

                scriptEntityList.add(scriptEntity);
            }
        }

        Set<String> talkerSet = scriptEntityList.stream().map(ScriptEntity::getTalker).collect(Collectors.toSet());
        Set<String> oldTalkerSet = getHostSet();
        talkerSet.addAll(oldTalkerSet);
        List<String> talkerList = new ArrayList<>(talkerSet);

        String folderPath =
            CdFileUtils.getResourceRealPath() + File.separatorChar + "data" + File.separatorChar + "dict";
//        String filePath = folderPath + File.separator + "host.xlsx";
        String filePath = folderPath + File.separator + "host.txt";
        String filePathNew = folderPath + File.separator + folderName + "_host.txt";

        FileUtil.writeLines(talkerList, filePath, "UTF-8");
        FileUtil.writeLines(talkerList, filePathNew, "UTF-8");

        return null;
    }


}
