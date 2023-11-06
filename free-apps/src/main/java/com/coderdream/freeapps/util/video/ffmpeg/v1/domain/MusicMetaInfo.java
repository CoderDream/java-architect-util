package com.coderdream.freeapps.util.video.ffmpeg.v1.domain;

import lombok.Data;

/**
 * Author: dreamer-1
 * Date: 2018/5/11
 * Time: 11:28
 * Description: 音频数据的基本信息
 */
@Data
public class MusicMetaInfo extends MetaInfo {
    /**
     * 音频时长 ,单位：毫秒
     */
    private Long duration;
    /**
     * 比特率，单位：Kb/s
     * 指音频每秒传送（包含）的比特数
     */
    private Integer bitRate;

    /**
     * 采样频率，单位：Hz
     * 指一秒钟内对声音信号的采样次数
     */
    private Long sampleRate;
}
