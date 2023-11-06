package com.coderdream.freeapps.util.video.ffmpeg.v1.domain;

import lombok.Data;

/**
 * Author: dreamer-1
 * Date: 2018/5/7
 * Time: 16:32
 * Description: 视频数据基本信息类
 */
@Data
public class VideoMetaInfo extends MetaInfo {
    /**
     * 视频（帧）宽度 ，单位为px
     */
    private Integer width;
    /**
     * 视频（帧）高度 ，单位为px
     */
    private Integer height;
    /**
     * 视频时长 ,单位：毫秒
     */
    private Long duration;
    /**
     * 比特率，单位：Kb/s
     * 指视频每秒传送（包含）的比特数
     */
    private Integer bitRate;
    /**
     * 编码器
     */
    private String encoder;
    /**
     * 帧率，单位：FPS（Frame Per Second）
     * 指视频每秒包含的帧数
     */
    private Float frameRate;
    /**
     * 视频中包含的音频信息
     */
    private MusicMetaInfo musicMetaInfo;
}
