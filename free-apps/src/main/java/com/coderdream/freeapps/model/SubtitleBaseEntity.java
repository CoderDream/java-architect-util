package com.coderdream.freeapps.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @author CoderDream
 * @TableName t_subtitle
 */
@Data
public class SubtitleBaseEntity implements Serializable {

    /**
     * 字幕序号
     */
    private Integer subIndex;

    /**
     * 时间字符串 00:00:50,280 --> 00:00:52,800
     */
    private String timeStr;

    /**
     * 第一字幕内容
     */
    private String subtitle;

    /**
     * 第二字幕内容
     */
    private String subtitleSecond;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(subIndex + "\n");
        sb.append(timeStr + "\n");
        sb.append(subtitle + "\n");
        sb.append(subtitleSecond + "\n");
        return sb.toString();
    }
}
