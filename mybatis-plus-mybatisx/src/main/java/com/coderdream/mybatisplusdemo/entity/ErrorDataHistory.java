package com.coderdream.mybatisplusdemo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName error_data_history
 */
@TableName(value ="error_data_history")
@Data
public class ErrorDataHistory implements Serializable {
    /**
     * 错误id
     */
    private String erroId;

    /**
     * 条目编码
     */
    private String attrItemFullCode;

    /**
     * 条目名称
     */
    private String attrItemName;

    /**
     * 数据编码
     */
    private String sendId;

    /**
     * 系统来源
     */
    private String dataType;

    /**
     * 错误描述
     */
    private String remark;

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
        ErrorDataHistory other = (ErrorDataHistory) that;
        return (this.getErroId() == null ? other.getErroId() == null : this.getErroId().equals(other.getErroId()))
            && (this.getAttrItemFullCode() == null ? other.getAttrItemFullCode() == null : this.getAttrItemFullCode().equals(other.getAttrItemFullCode()))
            && (this.getAttrItemName() == null ? other.getAttrItemName() == null : this.getAttrItemName().equals(other.getAttrItemName()))
            && (this.getSendId() == null ? other.getSendId() == null : this.getSendId().equals(other.getSendId()))
            && (this.getDataType() == null ? other.getDataType() == null : this.getDataType().equals(other.getDataType()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getErroId() == null) ? 0 : getErroId().hashCode());
        result = prime * result + ((getAttrItemFullCode() == null) ? 0 : getAttrItemFullCode().hashCode());
        result = prime * result + ((getAttrItemName() == null) ? 0 : getAttrItemName().hashCode());
        result = prime * result + ((getSendId() == null) ? 0 : getSendId().hashCode());
        result = prime * result + ((getDataType() == null) ? 0 : getDataType().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", erroId=").append(erroId);
        sb.append(", attrItemFullCode=").append(attrItemFullCode);
        sb.append(", attrItemName=").append(attrItemName);
        sb.append(", sendId=").append(sendId);
        sb.append(", dataType=").append(dataType);
        sb.append(", remark=").append(remark);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}