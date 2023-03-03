package com.coderdream.freeapps.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @TableName t_crawler_task
 */
@TableName(value ="t_crawler_task")
@Data
public class CrawlerTask implements Serializable {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 应用ID列表
     */
    private String appIdList;

    /**
     * 任务状态
     */
    private String taskStatus;

    /**
     * 任务开始时间
     */
    private Date startTime;

    /**
     * 任务结束时间
     */
    private Date endTime;

    /**
     * 成功列表
     */
    private String succesList;

    /**
     * 失败列表
     */
    private String failList;

    /**
     * 任务信息
     */
    private String taskMessage;

    /**
     * 是否删除 0未删除（默认），1已删除
     */
    private Integer delFlag;

    /**
     * 创建人代码
     */
    private String createUserCode;

    /**
     * 创建人名称
     */
    private String createUserName;

    /**
     * 创建时间
     */
    private Date createdDate;

    /**
     * 修改人代码
     */
    private String lastModifiedCode;

    /**
     * 修改人名称
     */
    private String lastModifiedName;

    /**
     * 修改时间
     */
    private Date lastModifiedDate;

    /**
     * 版本号
     */
    private String version;

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
        CrawlerTask other = (CrawlerTask) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getAppIdList() == null ? other.getAppIdList() == null : this.getAppIdList().equals(other.getAppIdList()))
            && (this.getTaskStatus() == null ? other.getTaskStatus() == null : this.getTaskStatus().equals(other.getTaskStatus()))
            && (this.getStartTime() == null ? other.getStartTime() == null : this.getStartTime().equals(other.getStartTime()))
            && (this.getEndTime() == null ? other.getEndTime() == null : this.getEndTime().equals(other.getEndTime()))
            && (this.getSuccesList() == null ? other.getSuccesList() == null : this.getSuccesList().equals(other.getSuccesList()))
            && (this.getFailList() == null ? other.getFailList() == null : this.getFailList().equals(other.getFailList()))
            && (this.getTaskMessage() == null ? other.getTaskMessage() == null : this.getTaskMessage().equals(other.getTaskMessage()))
            && (this.getDelFlag() == null ? other.getDelFlag() == null : this.getDelFlag().equals(other.getDelFlag()))
            && (this.getCreateUserCode() == null ? other.getCreateUserCode() == null : this.getCreateUserCode().equals(other.getCreateUserCode()))
            && (this.getCreateUserName() == null ? other.getCreateUserName() == null : this.getCreateUserName().equals(other.getCreateUserName()))
            && (this.getCreatedDate() == null ? other.getCreatedDate() == null : this.getCreatedDate().equals(other.getCreatedDate()))
            && (this.getLastModifiedCode() == null ? other.getLastModifiedCode() == null : this.getLastModifiedCode().equals(other.getLastModifiedCode()))
            && (this.getLastModifiedName() == null ? other.getLastModifiedName() == null : this.getLastModifiedName().equals(other.getLastModifiedName()))
            && (this.getLastModifiedDate() == null ? other.getLastModifiedDate() == null : this.getLastModifiedDate().equals(other.getLastModifiedDate()))
            && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getAppIdList() == null) ? 0 : getAppIdList().hashCode());
        result = prime * result + ((getTaskStatus() == null) ? 0 : getTaskStatus().hashCode());
        result = prime * result + ((getStartTime() == null) ? 0 : getStartTime().hashCode());
        result = prime * result + ((getEndTime() == null) ? 0 : getEndTime().hashCode());
        result = prime * result + ((getSuccesList() == null) ? 0 : getSuccesList().hashCode());
        result = prime * result + ((getFailList() == null) ? 0 : getFailList().hashCode());
        result = prime * result + ((getTaskMessage() == null) ? 0 : getTaskMessage().hashCode());
        result = prime * result + ((getDelFlag() == null) ? 0 : getDelFlag().hashCode());
        result = prime * result + ((getCreateUserCode() == null) ? 0 : getCreateUserCode().hashCode());
        result = prime * result + ((getCreateUserName() == null) ? 0 : getCreateUserName().hashCode());
        result = prime * result + ((getCreatedDate() == null) ? 0 : getCreatedDate().hashCode());
        result = prime * result + ((getLastModifiedCode() == null) ? 0 : getLastModifiedCode().hashCode());
        result = prime * result + ((getLastModifiedName() == null) ? 0 : getLastModifiedName().hashCode());
        result = prime * result + ((getLastModifiedDate() == null) ? 0 : getLastModifiedDate().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", appIdList=").append(appIdList);
        sb.append(", taskStatus=").append(taskStatus);
        sb.append(", startTime=").append(startTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", succesList=").append(succesList);
        sb.append(", failList=").append(failList);
        sb.append(", taskMessage=").append(taskMessage);
        sb.append(", delFlag=").append(delFlag);
        sb.append(", createUserCode=").append(createUserCode);
        sb.append(", createUserName=").append(createUserName);
        sb.append(", createdDate=").append(createdDate);
        sb.append(", lastModifiedCode=").append(lastModifiedCode);
        sb.append(", lastModifiedName=").append(lastModifiedName);
        sb.append(", lastModifiedDate=").append(lastModifiedDate);
        sb.append(", version=").append(version);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
