package com.coderdream.freeapps.mstts;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 说话人物
 */
@AllArgsConstructor
@Getter
public enum VoiceEnum {
    Zh_CN_XiaoxiaoNeural("zh-CN-XiaoxiaoNeural","Xiaoxiao (Neural) - 晓晓"),
    Zh_CN_YunyangNeural("zh-CN-YunyangNeural","Yunyang (Neural) - 云扬"),
    Zh_CN_XiaochenNeural("zh-CN-XiaochenNeural","Xiaochen (Neural) - 晓辰"),
    Zh_CN_XiaohanNeural("zh-CN-XiaohanNeural","Xiaohan (Neural) - 晓涵"),
    Zh_CN_XiaomengNeural("zh-CN-XiaomengNeural","Xiaomeng (Neural) - 晓梦"),
    Zh_CN_XiaomoNeural("zh-CN-XiaomoNeural","Xiaomo (Neural) - 晓墨"),
    Zh_CN_XiaoqiuNeural("zh-CN-XiaoqiuNeural","Xiaoqiu (Neural) - 晓秋"),
    Zh_CN_XiaoruiNeural("zh-CN-XiaoruiNeural","Xiaorui (Neural) - 晓睿"),
    Zh_CN_XiaoshuangNeural("zh-CN-XiaoshuangNeural","Xiaoshuang (Neural) - 晓双"),
    Zh_CN_XiaoxuanNeural("zh-CN-XiaoxuanNeural","Xiaoxuan (Neural) - 晓萱"),
    Zh_CN_XiaoyanNeural("zh-CN-XiaoyanNeural","Xiaoyan (Neural) - 晓颜"),
    Zh_CN_XiaoyiNeural("zh-CN-XiaoyiNeural","Xiaoyi (Neural) - 晓伊"),
    Zh_CN_XiaoyouNeural("zh-CN-XiaoyouNeural","Xiaoyou (Neural) - 晓悠"),
    Zh_CN_XiaozhenNeural("zh-CN-XiaozhenNeural","Xiaozhen (Neural) - 晓甄"),
    Zh_CN_YunfengNeural("zh-CN-YunfengNeural","Yunfeng (Neural) - 云枫"),
    Zh_CN_YunhaoNeural("zh-CN-YunhaoNeural","Yunhao (Neural) - 云皓"),
    Zh_CN_YunjianNeural("zh-CN-YunjianNeural","Yunjian (Neural) - 云健"),
    Zh_CN_YunxiaNeural("zh-CN-YunxiaNeural","Yunxia (Neural) - 云夏"),
    Zh_CN_YunxiNeural("zh-CN-YunxiNeural","Yunxi (Neural) - 云希"),
    Zh_CN_YunyeNeural("zh-CN-YunyeNeural","Yunye (Neural) - 云野"),
    Zh_CN_YunzeNeural("zh-CN-YunzeNeural","Yunze (Neural) - 云泽"),
    ;
    private String code;
    private String desc;
}
