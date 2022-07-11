package com.coderdream;

import lombok.Data;

@Data
public class Item {
    public Integer id;
    public String timeRange;
    public String startTime;
    public String endTime;
    public String content;
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
