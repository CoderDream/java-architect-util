package com.coderdream.freeapps;

import cn.hutool.core.io.FileUtil;
import com.coderdream.freeapps.util.bbc.AdvancedWordUtil;
import com.coderdream.freeapps.util.bbc.CoreWordUtil;
import com.coderdream.freeapps.util.bbc.DictUtils;
import com.coderdream.freeapps.util.bbc.GenSixMinutePptx;
import com.coderdream.freeapps.util.bbc.GenSrtUtil;
import com.coderdream.freeapps.util.bbc.HostUtil;
import com.coderdream.freeapps.util.bbc.ProcessScriptUtil;
import com.coderdream.freeapps.util.bbc.TranslateUtil;
import com.coderdream.freeapps.util.bbc.WordCountUtil;
import com.coderdream.freeapps.util.other.CdFileUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.springframework.boot.test.context.SpringBootTest;

//@SpringBootTest
//@J
@Slf4j
public class BbcStepTest {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(BbcStepTest.class);

    private List<String> NUMBER_LIST;

    @BeforeEach
    void init() {
        String folderPath =
            CdFileUtils.getResourceRealPath() + File.separatorChar + "data" + File.separatorChar + "bbc"
                + File.separatorChar;

        NUMBER_LIST = FileUtil.readLines(folderPath + "todo.txt", "UTF-8");
//        list = new ArrayList<>(Arrays.asList("test1", "test2"));
    }


    //    @Before
//    public void setup() {
//
//
//        String folderPath =
//            CdFileUtils.getResourceRealPath() + File.separatorChar + "data" + File.separatorChar + "bbc"
//                + File.separatorChar;
//
//    }
//    @Test
//    public void testStep000() {
//        for (String num : NUMBER_LIST) {
//            String folderName = "" + num;
//            System.out.println(folderName);
////            log.error("error "+ folderName);
//////            ProcessRawTxtUtil.processRawTxtSrt(folderName);
////        ProcessScriptUtil.process(folderName);
////        TranslateUtil.process(folderName);
////        TranslateUtil.mergeScriptContent(folderName);
//        }
//    }


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
     * 智能技术能影响气候变化吗？ 我们为什么会打哈欠？ 第五步：生成ppt和待填充《核心词汇表》的文件
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
            WordCountUtil wordCountUtil;
            String folderName = "" + num;
//            WordCountUtil.genVocTable(folderName);
        }
    }

    /**
     * 第三步：生成核心词汇表
     */
//    @Test
//    public void testStep09() {
//        for (String num : NUMBER_LIST) {
//            String folderName = "" + num;
//            CoreWordUtil.genCoreWordTable(folderName);
//        }
//    }

//    /**
//     * 第三步：生成高级词汇表
//     */
//    @Test
//    public void testStep10() {
//        for (String num : NUMBER_LIST) {
//            String folderName = "" + num;
//            AdvancedWordUtil.genCoreWordTable(folderName);
//        }
//    }

}
