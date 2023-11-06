package com.coderdream.freeapps.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 生成音频日志表
 * @TableName t_ms_tts
 */
@TableName(value ="t_ms_tts")
@Data
public class MsTtsEntity implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * B站字幕文字长度
     */
    private Integer biContentLength;

    /**
     * B站生成音频时长
     */
    private String biTimeLength;

    /**
     * 公众号字幕文字长度
     */
    private Integer wxContentLength;

    /**
     * 公众号生成音频时长
     */
    private String wxTimeLength;

    /**
     * 操作时长（毫秒）
     */
    private Long operateTimeLength;

    /**
     * 操作时长（字符串）
     */
    private String operateTimeLengthStr;

    /**
     * 操作时间
     */
    private Date createTime;

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
        MsTtsEntity other = (MsTtsEntity) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getBiContentLength() == null ? other.getBiContentLength() == null : this.getBiContentLength().equals(other.getBiContentLength()))
            && (this.getBiTimeLength() == null ? other.getBiTimeLength() == null : this.getBiTimeLength().equals(other.getBiTimeLength()))
            && (this.getWxContentLength() == null ? other.getWxContentLength() == null : this.getWxContentLength().equals(other.getWxContentLength()))
            && (this.getWxTimeLength() == null ? other.getWxTimeLength() == null : this.getWxTimeLength().equals(other.getWxTimeLength()))
            && (this.getOperateTimeLength() == null ? other.getOperateTimeLength() == null : this.getOperateTimeLength().equals(other.getOperateTimeLength()))
            && (this.getOperateTimeLengthStr() == null ? other.getOperateTimeLengthStr() == null : this.getOperateTimeLengthStr().equals(other.getOperateTimeLengthStr()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getBiContentLength() == null) ? 0 : getBiContentLength().hashCode());
        result = prime * result + ((getBiTimeLength() == null) ? 0 : getBiTimeLength().hashCode());
        result = prime * result + ((getWxContentLength() == null) ? 0 : getWxContentLength().hashCode());
        result = prime * result + ((getWxTimeLength() == null) ? 0 : getWxTimeLength().hashCode());
        result = prime * result + ((getOperateTimeLength() == null) ? 0 : getOperateTimeLength().hashCode());
        result = prime * result + ((getOperateTimeLengthStr() == null) ? 0 : getOperateTimeLengthStr().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", biContentLength=").append(biContentLength);
        sb.append(", biTimeLength=").append(biTimeLength);
        sb.append(", wxContentLength=").append(wxContentLength);
        sb.append(", wxTimeLength=").append(wxTimeLength);
        sb.append(", operateTimeLength=").append(operateTimeLength);
        sb.append(", operateTimeLengthStr=").append(operateTimeLengthStr);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}