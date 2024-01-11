package com.coderdream.freeapps;

import com.coderdream.freeapps.model.DownloadInfoEntity;
import com.coderdream.freeapps.model.TopicEntity;
import com.coderdream.freeapps.util.bbc.AdvancedWordUtil;
import com.coderdream.freeapps.util.bbc.CoreWordUtil;
import com.coderdream.freeapps.util.bbc.DictUtils;
import com.coderdream.freeapps.util.bbc.GenSixMinutePptx;
import com.coderdream.freeapps.util.bbc.GenSrtUtil;
import com.coderdream.freeapps.util.bbc.HostUtil;
import com.coderdream.freeapps.util.bbc.ProcessRawTxtUtil;
import com.coderdream.freeapps.util.bbc.ProcessScriptUtil;
import com.coderdream.freeapps.util.bbc.TranslateUtil;
import com.coderdream.freeapps.util.bbc.WordCountUtil;
import com.coderdream.freeapps.util.proxy.HtmlUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class BbcStepTest {


    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(BbcStepTest.class);

//    private final String NUMBER = "230216";

//    private final ArrayList NUMBER_LIST = new ArrayList<String >(){"230706"};

//    private final List<String> NUMBER_LIST = Arrays.asList("230420", "230427");

    private final List<String> NUMBER_LIST = Arrays.asList(
        "220901",
        "220908"        ,
        "220915",
        "220922",
        "220929"
//        "240104"
    );
//
//    private final List<String> TITLE_LIST = Arrays.asList("冰和地球上生命的起源", "沉迷于战争", "食物短缺", "食物短缺");

//    private static List<String> NUMBER_LIST = Arrays.asList("231228");
//    private final List<String> TITLE_LIST = Arrays.asList("怎样活到一百岁");

//    @BeforeAll
//    public static void before(){
//
//        boolean test = false;
//
//        List<DownloadInfoEntity> downloadInfoEntityListTemp = new ArrayList<>();
//        if (test) {
//            DownloadInfoEntity infoEntity = new DownloadInfoEntity();
//            String ep = "231207";
//            String year = ep.substring(0, 2);
//            infoEntity.setFileUrl(
//                "https://www.bbc.co.uk/learningenglish/english/features/6-minute-english_20" + year + "/ep-" + ep + "");
//            infoEntity.setPath("D:/14_LearnEnglish/6MinuteEnglish/20" + year + "/" + ep + "/");
//            infoEntity.setFileName(ep + ".html");
//            downloadInfoEntityListTemp = Arrays.asList(infoEntity);
//        } else {
////            downloadInfoEntityListTemp = HtmlUtil.getDownloadHtmlInfo("pdf", "2022", "03", "04", "05", "06", "07", "08", "09");
//            downloadInfoEntityListTemp = HtmlUtil.getDownloadHtmlInfo("pdf", "2022", "03");
////            String folderName = downloadInfoEntity.getFileName().substring(0, 6); // "220901";
//            List<String> collect = downloadInfoEntityListTemp.stream().map(DownloadInfoEntity::getFileName)
//                .collect(Collectors.toList());
////            NUMBER_LIST = collect;
//
//            List<String> folderNameList = new ArrayList<>();
//            for (String fileName : collect) {
//                folderNameList.add(fileName.substring(0, 6));
//            }
//
//            NUMBER_LIST = folderNameList;
//        }
//    }

    @Before
    public void setup() {

    }

    /**
     * 第一步：生成voc（英文版词汇）和对话脚本
     */
    @Test
    public void testStep00() {
        for (String num : NUMBER_LIST) {
            String folderName = "" + num;
//            ProcessRawTxtUtil.processRawTxtSrt(folderName);
            ProcessScriptUtil.process(folderName);
            TranslateUtil.process(folderName);
            TranslateUtil.mergeScriptContent(folderName);
        }
    }

    /**
     * 第一步：字幕初剪，生成对话脚本、对话中文脚本和中英文脚本
     */
    @Test
    public void testStep01() {
        for (String num : NUMBER_LIST) {
            String folderName = "" + num;
            HostUtil.updateHostTextFile(folderName);
            GenSrtUtil.processScriptDialog(folderName);
        }
    }

    /**
     * 第四步：翻译核心词汇表，生成 voc_cn.txt 文件
     */
    @Test
    public void testStep04() {
        for (String num : NUMBER_LIST) {
            String folderName = "" + num;
            DictUtils.processVoc(folderName);
        }
    }

    /**
     * 第五步：生成ppt和待填充《核心词汇表》的文件
     */
    @Test
    public void testStep05() {
        int i = 0;
        String fileName = "script_raw";
        List<String> stringList = TranslateUtil.translateTitle(NUMBER_LIST, fileName);
        for (String num : NUMBER_LIST) {
            String folderName = "" + num;
            GenSixMinutePptx.genPpt(folderName, stringList.get(i));
            i++;
        }
    }


    /**
     * 第三步：生成完整词汇表
     */
    @Test
    public void testStep08() {
        for (String num : NUMBER_LIST) {
            String folderName = "" + num;
//            WordCountUtil.genVocTable(folderName);
        }
    }

    /**
     * 第三步：生成核心词汇表
     */
    @Test
    public void testStep09() {
        for (String num : NUMBER_LIST) {
            String folderName = "" + num;
            CoreWordUtil.genCoreWordTable(folderName);
        }
    }

    /**
     * 第三步：生成高级词汇表
     */
    @Test
    public void testStep10() {
        for (String num : NUMBER_LIST) {
            String folderName = "" + num;
            AdvancedWordUtil.genCoreWordTable(folderName);
        }
    }

    /**
     * 生成中文字幕
     */
    @Test
    public void testStep16() {
        for (String num : NUMBER_LIST) {
            String folderName = "" + num;
            TranslateUtil.translateEngSrc(folderName);
        }
    }

}
