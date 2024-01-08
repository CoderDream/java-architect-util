package com.coderdream.freeapps.util.other;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileWriter;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.coderdream.freeapps.model.DualSubtitleEntity;
import com.coderdream.freeapps.model.FreeHistory;
import com.coderdream.freeapps.model.SubtitleBaseEntity;
import com.coderdream.freeapps.model.SubtitleEntity;
import com.coderdream.freeapps.model.TopicEntity;
import com.coderdream.freeapps.mstts.FileUtils;
import com.coderdream.freeapps.util.bbc.ScriptEntity;
import com.spreada.utils.chinese.ZHConverter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;

/**
 * Java按一行一行进行文件的读取或写入 https://blog.csdn.net/yuanhaiwn/article/details/83090540
 *
 * @author CoderDream
 */
public class DualSubtitleUtils {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(DualSubtitleUtils.class);

    public static void main(String[] args) {
//        String fileName = "D:\\12_iOS_Android\\1024_data\\2022-06-29.txt";

        // List<SubtitleEntity>;
        String folderName = "E:\\02_\\电影\\Oppenheimer.2023.IMAX.2160p.BluRay.x265.10bit.DTS-HD.MA.5.1-WiKi\\";
        String fileName = "E:\\02_\\电影\\Oppenheimer.2023.IMAX.2160p.BluRay.x265.10bit.DTS-HD.MA.5.1-WiKi\\Oppenheimer.srt";
        fileName = "E:\\02_\\电影\\Oppenheimer.2023.IMAX.2160p.BluRay.x265.10bit.DTS-HD.MA.5.1-WiKi\\test.txt";
//        List<SubtitleEntity> subtitleEntityList = readTwinsSrcFileContent(fileName);
//        for (SubtitleEntity subtitleEntity : subtitleEntityList) {
//            System.out.println(subtitleEntity);
//        }

//        CdFileUtils.getSrtScript(folderName, fileName);

        folderName = "F:\\BT\\Planet.Earth.III.S01.2160p.iP.WEB-DL.AAC2.0.HLG.H.265-playWEB";
        fileName = "Day 1 话题1 name.srt";
        writeToFile(folderName, fileName);
    }


    public static void writeToFile(String folderName, String fileName) {
        List<String> allFileNames = CdFileUtils.getAllFileNames(folderName);
        for (String srcFileName : allFileNames) {
            if (srcFileName.endsWith("chs&eng.srt")) {
                System.out.println(srcFileName);
                List<DualSubtitleEntity> dualSubtitleEntityList = CdFileUtils.getDualSubtitleEntityList(srcFileName);
                StringBuffer stringBufferCn =new StringBuffer();
                for (DualSubtitleEntity dualSubtitleEntity : dualSubtitleEntityList) {
                    System.out.println(dualSubtitleEntity);
                    stringBufferCn.append(dualSubtitleEntity.getSubtitleCn());
                }

                List<String> subtitleCnList = dualSubtitleEntityList.stream().map(DualSubtitleEntity::getSubtitleCn)
                    .collect(Collectors.toList());
                String textCn = subtitleCnList.stream().map(String::valueOf).collect(Collectors.joining("\r\n"));

                List<String> subtitleEnList = dualSubtitleEntityList.stream().map(DualSubtitleEntity::getSubtitleEn)
                    .collect(Collectors.toList());
                String textEn = subtitleCnList.stream().map(String::valueOf).collect(Collectors.joining("\r\n"));

                CdFileUtils.writeToFile(srcFileName.replace("chs&eng.srt", "chs.txt"), subtitleCnList);
                CdFileUtils.writeToFile(srcFileName.replace("chs&eng.srt", "eng.txt"), subtitleEnList);
            }
        }
    }
}
