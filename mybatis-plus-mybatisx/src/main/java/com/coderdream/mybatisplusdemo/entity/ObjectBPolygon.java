package com.coderdream.mybatisplusdemo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 地理信息-多边形数据
 * @TableName object_b_polygon
 */
@TableName(value ="object_b_polygon")
@Data
public class ObjectBPolygon implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 
     */
    private String attrItemFullCode;

    /**
     * 
     */
    private Object geometry;

    /**
     * 
     */
    private Object coordinates;

    /**
     * 
     */
    private Object properties;

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
        ObjectBPolygon other = (ObjectBPolygon) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getAttrItemFullCode() == null ? other.getAttrItemFullCode() == null : this.getAttrItemFullCode().equals(other.getAttrItemFullCode()))
            && (this.getGeometry() == null ? other.getGeometry() == null : this.getGeometry().equals(other.getGeometry()))
            && (this.getCoordinates() == null ? other.getCoordinates() == null : this.getCoordinates().equals(other.getCoordinates()))
            && (this.getProperties() == null ? other.getProperties() == null : this.getProperties().equals(other.getProperties()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getAttrItemFullCode() == null) ? 0 : getAttrItemFullCode().hashCode());
        result = prime * result + ((getGeometry() == null) ? 0 : getGeometry().hashCode());
        result = prime * result + ((getCoordinates() == null) ? 0 : getCoordinates().hashCode());
        result = prime * result + ((getProperties() == null) ? 0 : getProperties().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", attrItemFullCode=").append(attrItemFullCode);
        sb.append(", geometry=").append(geometry);
        sb.append(", coordinates=").append(coordinates);
        sb.append(", properties=").append(properties);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}