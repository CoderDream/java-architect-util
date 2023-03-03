package com.coderdream.freeapps.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName t_extra_info
 */
@TableName(value ="t_extra_info")
@Data
public class ExtraInfo implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 
     */
    private Object extraObject;

    /**
     * 
     */
    private Object extraJson;

    /**
     * 
     */
    private Object extraList;

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
        ExtraInfo other = (ExtraInfo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getExtraObject() == null ? other.getExtraObject() == null : this.getExtraObject().equals(other.getExtraObject()))
            && (this.getExtraJson() == null ? other.getExtraJson() == null : this.getExtraJson().equals(other.getExtraJson()))
            && (this.getExtraList() == null ? other.getExtraList() == null : this.getExtraList().equals(other.getExtraList()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getExtraObject() == null) ? 0 : getExtraObject().hashCode());
        result = prime * result + ((getExtraJson() == null) ? 0 : getExtraJson().hashCode());
        result = prime * result + ((getExtraList() == null) ? 0 : getExtraList().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", extraObject=").append(extraObject);
        sb.append(", extraJson=").append(extraJson);
        sb.append(", extraList=").append(extraList);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}