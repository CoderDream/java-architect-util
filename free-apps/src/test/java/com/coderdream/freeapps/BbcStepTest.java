package com.coderdream.freeapps;

import com.coderdream.freeapps.util.bbc.DictUtils;
import com.coderdream.freeapps.util.bbc.GenSixMinutePptx;
import com.coderdream.freeapps.util.bbc.GenSrtUtil;
import com.coderdream.freeapps.util.bbc.ProcessRawTxtUtil;
import com.coderdream.freeapps.util.bbc.TranslateUtil;
import com.coderdream.freeapps.util.bbc.WordCountUtil;
import java.util.Arrays;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class BbcStepTest {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(BbcStepTest.class);

//    private final String NUMBER = "230706";

//    private final ArrayList NUMBER_LIST = new ArrayList<String >(){"230706"};

//    private final List<String> NUMBER_LIST = Arrays.asList("230420", "230427");
//    private final List<String> NUMBER_LIST = Arrays.asList("230406", "230413", "230420", "230427");
//    private final List<String> TITLE_LIST = Arrays.asList("冰和地球上生命的起源", "沉迷于战争", "食物短缺", "食物短缺");


    private final List<String> NUMBER_LIST = Arrays.asList("230309");
//    private final List<String> TITLE_LIST = Arrays.asList("怎样活到一百岁");

    /**
     * 第一步：字幕初剪，生成voc（英文版词汇）和对话脚本
     */
    @Test
    public void testStep00() {
        for (String num : NUMBER_LIST) {
            String folderName = "" + num;
            ProcessRawTxtUtil.processRawTxtSrt(folderName);
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
     * 生成中文字幕
     */
    @Test
    public void testStep06() {
        for (String num : NUMBER_LIST) {
            String folderName = "" + num;
            TranslateUtil.translateEngSrc(folderName);
        }
    }

    /**
     * 第三步：生成完整词汇表
     */
    @Test
    public void testStep08() {
        for (String num : NUMBER_LIST) {
            String folderName = "" + num;
            WordCountUtil.genVocTable(folderName);
        }
    }

}
