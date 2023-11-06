package com.coderdream.freeapps.mstts;

import cn.hutool.core.io.file.FileReader;
import com.coderdream.freeapps.util.other.BaseUtils;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MsttsUtils {

    public static void genMp3(String content, String fileName) {
        long startTime = System.currentTimeMillis();
        MSTTS mstts = new MSTTS();
        byte[] mp3btye = mstts.gen(content);
//        System.out.println(new String(Base64.getEncoder().encode(mp3btye)));
        FileUtils.bytes2FileSpring(mp3btye, fileName);
        //
        log.info("耗时: " + (System.currentTimeMillis() - startTime) / 1000.0 + "s");
    }

    public static void main(String[] args) {
        String content = "大家好，今天是2023年03月19日，给您推荐9款正在限免的游戏或应用，为您节省96元！第1款是莴苣姑娘，动画有声故事书。第2款是Incus Training，一个耳朵训练应用程序。旨在改善音乐家、音乐制作人和音响工程师的耳朵感知。。第3款是灰姑娘，精细设计的故事书和绝佳的声音图像体验。。第4款是Bug Frenzy，本应用原价6元，一款简单的益智游戏。第5款是Lightning Control，一款简单的益智游戏。控制闪电并到达冰门。。第6款是检讨书·无广告秒开，一款提供多种参考检讨书模板的app。第7款是NotifiNote:NotificationNotes，本应用原价12元，一款笔记记事本应用。第8款是AnimalKIDS：3、4、5岁儿童拼图，在这里您的孩子可以学习如何比较不同的形状！第9款是不断变化的风景，录制实时音频和视频。并使用应用程序的随机算法将它们混合在一起。。感谢大家的关注与点赞，公众号有历史限免图文，可能有你想要的应用仍在限免哦。";
        String fileName = "20230327_wx1.mp3";
        String contentFilename = "D:\\12_iOS_Android\\202303\\20230327\\20230327_wx (1).txt";
        FileReader fileReader = new FileReader(contentFilename);
        content = fileReader.readString();
//        MsttsUtils.genMp3(content, fileName);
        // 20230328_wx (1)
        String dateStr = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String path = BaseUtils.getPath();
        String txtFileName1 = dateStr + "_bi.txt";
        String txtFileName2 = dateStr + "_wx.txt";
        String mp3FileName1 = dateStr + "_bi.mp3";
        String mp3FileName2 = dateStr + "_wx.mp3";
        fileReader = new FileReader(path + File.separator + txtFileName1);
        content = fileReader.readString();
        MsttsUtils.genMp3(content, path + File.separator + mp3FileName1);
        fileReader = new FileReader(path + File.separator + txtFileName2);
        content = fileReader.readString();
        MsttsUtils.genMp3(content,path + File.separator +  mp3FileName2);
    }
}
