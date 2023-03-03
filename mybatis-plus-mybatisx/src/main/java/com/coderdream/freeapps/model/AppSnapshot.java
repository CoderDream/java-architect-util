package com.coderdream.freeapps.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * APP截图信息表
 * @TableName t_app_snapshot
 */
@TableName(value ="t_app_snapshot")
@Data
public class AppSnapshot implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 应用ID
     */
    private String appId;

    /**
     * 名称
     */
    private String name;

    /**
     * 是否美区限免
     */
    private Integer usFlag;

    /**
     * 国区截图1URL
     */
    private String urlCn1;

    /**
     * 国区截图2URL
     */
    private String urlCn2;

    /**
     * 国区截图3URL
     */
    private String urlCn3;

    /**
     * 国区截图4URL
     */
    private String urlCn4;

    /**
     * 美区URL
     */
    private String urlUs;

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
        AppSnapshot other = (AppSnapshot) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getAppId() == null ? other.getAppId() == null : this.getAppId().equals(other.getAppId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getUsFlag() == null ? other.getUsFlag() == null : this.getUsFlag().equals(other.getUsFlag()))
            && (this.getUrlCn1() == null ? other.getUrlCn1() == null : this.getUrlCn1().equals(other.getUrlCn1()))
            && (this.getUrlCn2() == null ? other.getUrlCn2() == null : this.getUrlCn2().equals(other.getUrlCn2()))
            && (this.getUrlCn3() == null ? other.getUrlCn3() == null : this.getUrlCn3().equals(other.getUrlCn3()))
            && (this.getUrlCn4() == null ? other.getUrlCn4() == null : this.getUrlCn4().equals(other.getUrlCn4()))
            && (this.getUrlUs() == null ? other.getUrlUs() == null : this.getUrlUs().equals(other.getUrlUs()))
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
        result = prime * result + ((getAppId() == null) ? 0 : getAppId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getUsFlag() == null) ? 0 : getUsFlag().hashCode());
        result = prime * result + ((getUrlCn1() == null) ? 0 : getUrlCn1().hashCode());
        result = prime * result + ((getUrlCn2() == null) ? 0 : getUrlCn2().hashCode());
        result = prime * result + ((getUrlCn3() == null) ? 0 : getUrlCn3().hashCode());
        result = prime * result + ((getUrlCn4() == null) ? 0 : getUrlCn4().hashCode());
        result = prime * result + ((getUrlUs() == null) ? 0 : getUrlUs().hashCode());
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
        sb.append(", appId=").append(appId);
        sb.append(", name=").append(name);
        sb.append(", usFlag=").append(usFlag);
        sb.append(", urlCn1=").append(urlCn1);
        sb.append(", urlCn2=").append(urlCn2);
        sb.append(", urlCn3=").append(urlCn3);
        sb.append(", urlCn4=").append(urlCn4);
        sb.append(", urlUs=").append(urlUs);
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