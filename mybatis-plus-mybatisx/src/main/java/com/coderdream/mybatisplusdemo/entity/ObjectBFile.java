package com.coderdream.mybatisplusdemo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Arrays;
import lombok.Data;

/**
 * 文件
 * @TableName object_b_file
 */
@TableName(value ="object_b_file")
@Data
public class ObjectBFile implements Serializable {
    /**
     * 属性条目全码
     */
    @TableId
    private String attrItemFullCode;

    /**
     * 值
     */
    private byte[] value;

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
        ObjectBFile other = (ObjectBFile) that;
        return (this.getAttrItemFullCode() == null ? other.getAttrItemFullCode() == null : this.getAttrItemFullCode().equals(other.getAttrItemFullCode()))
            && (Arrays.equals(this.getValue(), other.getValue()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getAttrItemFullCode() == null) ? 0 : getAttrItemFullCode().hashCode());
        result = prime * result + (Arrays.hashCode(getValue()));
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", attrItemFullCode=").append(attrItemFullCode);
        sb.append(", value=").append(value);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}