package com.coderdream.freeapps.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 *
 * @author CoderDream
 * @TableName t_subtitle
 */
@TableName(value ="t_subtitle")
@Data
public class SubtitleEntity implements Serializable {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 字幕序号字符串
     */
    private String subtitleIndex;

    /**
     * 字幕序号
     */
    private Integer subIndex;

    /**
     * 视频ID
     */
    private String videoId;

    /**
     * 视频名称
     */
    private String videoName;

    /**
     * 中文字幕内容
     */
    private String subtitleCn;

    /**
     * 英文字幕内容
     */
    private String subtitleEn;

    /**
     * 开始时间字符串
     */
    private String startTimeStr;

    /**
     * 结束时间字符串
     */
    private String endTimeStr;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        SubtitleEntity other = (SubtitleEntity) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getSubtitleIndex() == null ? other.getSubtitleIndex() == null : this.getSubtitleIndex().equals(other.getSubtitleIndex()))
            && (this.getVideoId() == null ? other.getVideoId() == null : this.getVideoId().equals(other.getVideoId()))
            && (this.getVideoName() == null ? other.getVideoName() == null : this.getVideoName().equals(other.getVideoName()))
            && (this.getSubtitleCn() == null ? other.getSubtitleCn() == null : this.getSubtitleCn().equals(other.getSubtitleCn()))
            && (this.getSubtitleEn() == null ? other.getSubtitleEn() == null : this.getSubtitleEn().equals(other.getSubtitleEn()))
            && (this.getStartTimeStr() == null ? other.getStartTimeStr() == null : this.getStartTimeStr().equals(other.getStartTimeStr()))
            && (this.getEndTimeStr() == null ? other.getEndTimeStr() == null : this.getEndTimeStr().equals(other.getEndTimeStr()))
            && (this.getStartTime() == null ? other.getStartTime() == null : this.getStartTime().equals(other.getStartTime()))
            && (this.getEndTime() == null ? other.getEndTime() == null : this.getEndTime().equals(other.getEndTime()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getSubtitleIndex() == null) ? 0 : getSubtitleIndex().hashCode());
        result = prime * result + ((getVideoId() == null) ? 0 : getVideoId().hashCode());
        result = prime * result + ((getVideoName() == null) ? 0 : getVideoName().hashCode());
        result = prime * result + ((getSubtitleCn() == null) ? 0 : getSubtitleCn().hashCode());
        result = prime * result + ((getSubtitleEn() == null) ? 0 : getSubtitleEn().hashCode());
        result = prime * result + ((getStartTimeStr() == null) ? 0 : getStartTimeStr().hashCode());
        result = prime * result + ((getEndTimeStr() == null) ? 0 : getEndTimeStr().hashCode());
        result = prime * result + ((getStartTime() == null) ? 0 : getStartTime().hashCode());
        result = prime * result + ((getEndTime() == null) ? 0 : getEndTime().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", subtitleIndex=").append(subtitleIndex);
        sb.append(", videoId=").append(videoId);
        sb.append(", videoName=").append(videoName);
        sb.append(", subtitleCn=").append(subtitleCn);
        sb.append(", subtitleEn=").append(subtitleEn);
        sb.append(", startTimeStr=").append(startTimeStr);
        sb.append(", endTimeStr=").append(endTimeStr);
        sb.append(", startTime=").append(startTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
