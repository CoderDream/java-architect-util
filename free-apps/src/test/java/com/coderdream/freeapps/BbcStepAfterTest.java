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
import com.coderdream.freeapps.util.other.CdFileUtils;
import java.io.File;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;

//@SpringBootTest
//@J
@Slf4j
public class BbcStepAfterTest {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(BbcStepAfterTest.class);

    private List<String> NUMBER_LIST;

    @BeforeEach
    void init() {
        String folderPath =
            CdFileUtils.getResourceRealPath() + File.separatorChar + "data" + File.separatorChar + "bbc"
                + File.separatorChar;

        NUMBER_LIST = FileUtil.readLines(folderPath + "todo.txt", "UTF-8");
//        list = new ArrayList<>(Arrays.asList("test1", "test2"));
    }

    /**
     * <pre>
     *     你支持妇女参政么？
     * </pre>
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