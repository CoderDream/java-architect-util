package com.coderdream.freeapps.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.ToString;

/**
 *
 * @author CoderDream
 * @TableName t_subtitle
 */
@ToString
@Data
public class DualSubtitleEntity implements Serializable {

    /**
     * 字幕序号
     */
    private String subtitleIndex;

    /**
     * 时间字符串
     */
    private String timeStr;

    /**
     * 中文字幕内容
     */
    private String subtitleCn;

    /**
     * 英文字幕内容
     */
    private String subtitleEn;

}
