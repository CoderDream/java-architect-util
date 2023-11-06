package com.coderdream;

import java.util.List;
import lombok.Data;

/**
 * 字幕项单语言
 * @author CoderDream
 */
@Data
public class ItemSingle {

    /**
     * 序号
     */
    public Integer id;

    /**
     * 时间段
     */
    public String timeRange;

    /**
     * 第一语言
     */
    public String content;


    /**
     * 第一语言列表
     */
    public List<String> contentList;


    public String toMultiLine() {
        return id + "\r\n" + timeRange + "\r\n" + content+ "\r\n";
    }
}
