package com.coderdream.freeapps.mstts;


import java.util.Base64;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner sco = new Scanner(System.in);
                MSTTS mstts = new MSTTS();) {
            System.out.println("your text:");
            String text;
            text = "大家好，今天是2023年03月19日，给您推荐9款正在限免的游戏或应用，为您节省96元！";
            text = "大家好，今天是2023年03月19日，给您推荐9款正在限免的游戏或应用，为您节省96元！第1款是莴苣姑娘，动画有声故事书。第2款是Incus Training，一个耳朵训练应用程序。旨在改善音乐家、音乐制作人和音响工程师的耳朵感知。。第3款是灰姑娘，精细设计的故事书和绝佳的声音图像体验。。第4款是Bug Frenzy，本应用原价6元，一款简单的益智游戏。第5款是Lightning Control，一款简单的益智游戏。控制闪电并到达冰门。。第6款是检讨书·无广告秒开，一款提供多种参考检讨书模板的app。第7款是NotifiNote:NotificationNotes，本应用原价12元，一款笔记记事本应用。第8款是AnimalKIDS：3、4、5岁儿童拼图，在这里您的孩子可以学习如何比较不同的形状！第9款是不断变化的风景，录制实时音频和视频。并使用应用程序的随机算法将它们混合在一起。。感谢大家的关注与点赞，公众号有历史限免图文，可能有你想要的应用仍在限免哦。";
            byte[] mp3btye = mstts.gen(text);
            System.out.println(new String(Base64.getEncoder().encode(mp3btye)));
            FileUtils.bytes2FileSpring( mp3btye,"test.mp3");
//            while (sco.hasNextLine()) {
//                text = sco.nextLine();
//                if ("quit".equals(text) || "exit".equals(text)) {
//                    break;
//                }
//                byte[] mp3btye = mstts.gen(text);
//                System.out.println(new String(Base64.getEncoder().encode(mp3btye)));
//                FileUtils.bytes2FileSpring( mp3btye,"test.mp3");
//            }
        }
    }
}
