package com.coderdream.freeapps.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @TableName t_snapshot
 */
@TableName(value = "t_snapshot")
@Data
public class Snapshot implements Serializable {
    /**
     * ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 应用ID
     */
    private String appId;

    /**
     * 文件名
     */
    private String filename;

    /**
     * 截图地址
     */
    private String snapshotUrl;

    /**
     * 是否下载成功
     */
    private Integer downloadFlag;

    /**
     * 备注
     */
    private String remark;

    /**
     * 是否删除 0未删除（默认），1已删除
     */
    private Integer deleteFlag;

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
        Snapshot other = (Snapshot) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getAppId() == null ? other.getAppId() == null : this.getAppId().equals(other.getAppId()))
                && (this.getFilename() == null ? other.getFilename() == null : this.getFilename().equals(other.getFilename()))
                && (this.getSnapshotUrl() == null ? other.getSnapshotUrl() == null : this.getSnapshotUrl().equals(other.getSnapshotUrl()))
                && (this.getDownloadFlag() == null ? other.getDownloadFlag() == null : this.getDownloadFlag().equals(other.getDownloadFlag()))
                && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
                && (this.getDeleteFlag() == null ? other.getDeleteFlag() == null : this.getDeleteFlag().equals(other.getDeleteFlag()))
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
        result = prime * result + ((getAppId() == null) ? 0 : getAppId().hashCode());
        result = prime * result + ((getFilename() == null) ? 0 : getFilename().hashCode());
        result = prime * result + ((getSnapshotUrl() == null) ? 0 : getSnapshotUrl().hashCode());
        result = prime * result + ((getDownloadFlag() == null) ? 0 : getDownloadFlag().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getDeleteFlag() == null) ? 0 : getDeleteFlag().hashCode());
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
        sb.append(", appId=").append(appId);
        sb.append(", filename=").append(filename);
        sb.append(", snapshotUrl=").append(snapshotUrl);
        sb.append(", downloadFlag=").append(downloadFlag);
        sb.append(", remark=").append(remark);
        sb.append(", deleteFlag=").append(deleteFlag);
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
