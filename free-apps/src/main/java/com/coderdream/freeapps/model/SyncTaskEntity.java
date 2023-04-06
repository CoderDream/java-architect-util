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
 * @TableName t_sync_task
 */
@TableName(value ="t_sync_task")
@Data
public class SyncTaskEntity implements Serializable {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     *
     */
    private Integer taskId;

    /**
     * 任务名称
     */
    private String taskName;

    /**
     * 每次执行的天数
     */
    private Integer processPeriod;

    /**
     * 最后一次完成任务的日期
     */
    private Date lastProcessDate;

    /**
     * 更新的记录条数
     */
    private Long updateRecord;

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
        SyncTaskEntity other = (SyncTaskEntity) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTaskId() == null ? other.getTaskId() == null : this.getTaskId().equals(other.getTaskId()))
            && (this.getTaskName() == null ? other.getTaskName() == null : this.getTaskName().equals(other.getTaskName()))
            && (this.getProcessPeriod() == null ? other.getProcessPeriod() == null : this.getProcessPeriod().equals(other.getProcessPeriod()))
            && (this.getLastProcessDate() == null ? other.getLastProcessDate() == null : this.getLastProcessDate().equals(other.getLastProcessDate()))
            && (this.getUpdateRecord() == null ? other.getUpdateRecord() == null : this.getUpdateRecord().equals(other.getUpdateRecord()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTaskId() == null) ? 0 : getTaskId().hashCode());
        result = prime * result + ((getTaskName() == null) ? 0 : getTaskName().hashCode());
        result = prime * result + ((getProcessPeriod() == null) ? 0 : getProcessPeriod().hashCode());
        result = prime * result + ((getLastProcessDate() == null) ? 0 : getLastProcessDate().hashCode());
        result = prime * result + ((getUpdateRecord() == null) ? 0 : getUpdateRecord().hashCode());
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
        sb.append(", taskId=").append(taskId);
        sb.append(", taskName=").append(taskName);
        sb.append(", processPeriod=").append(processPeriod);
        sb.append(", lastTime=").append(lastProcessDate);
        sb.append(", updateRecord=").append(updateRecord);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
