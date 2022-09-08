package com.coderdream;

import lombok.Data;

/**
 * 字幕项
 */
@Data
public class Item {
    public Integer id;

    /**
     * 时间段
     */
    public String timeRange;

    /**
     * 开始时间
     */
    public String startTime;

    /**
     * 结束时间
     */
    public String endTime;

    /**
     * 第一语言
     */
    public String content;

    /**
     * 第二语言
     */
    public String secondContent;

    public Item prevItem;
    public Item nextItem;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTimeRange() {
        return timeRange;
    }

    public void setTimeRange(String timeRange) {
        this.timeRange = timeRange;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSecondContent() {
        return secondContent;
    }

    public void setSecondContent(String secondContent) {
        this.secondContent = secondContent;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", timeRange='" + timeRange + '\'' +
                ", content='" + content + '\'' +
                ", secondContent='" + secondContent + '\'' +
                '}';
    }

    public String toMultiLine() {
        if (null == secondContent || "".equals(secondContent.trim())) {
            // 去掉中文无用空格及末尾标点符号
            content = SubtitleUtil.trimChineseBlank(content);
            content = SubtitleUtil.removeLastPunctuation(content.trim());

            return id + "\r\n" + timeRange + "\r\n" + content + "\r\n";
        } else {
            // 去掉中文无用空格及末尾标点符号
            content = SubtitleUtil.trimChineseBlank(content);
            content = SubtitleUtil.removeLastPunctuation(content.trim());
            secondContent = SubtitleUtil.trimChineseBlank(secondContent);
            secondContent = SubtitleUtil.removeLastPunctuation(secondContent.trim());

            return id + "\r\n" + timeRange + "\r\n" + content+ "\r\n" + secondContent + "\r\n";
        }
    }
}
