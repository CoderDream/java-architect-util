package com.coderdream.freeapps.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 *
 * @TableName t_act_result_log
 */
@TableName(value ="t_act_result_log")
@Data
public class ActResultLogEntity implements Serializable {
    /**
     *
     */
    @TableId
    private Long id;

    /**
     *
     */
    private String onlineSeqid;

    /**
     *
     */
    private String businessId;

    /**
     *
     */
    private String becifno;

    /**
     *
     */
    private String ivisResult;

    /**
     *
     */
    private String createdBy;

    /**
     *
     */
    private Date createdDate;

    /**
     *
     */
    private String updateBy;

    /**
     *
     */
    private Date updatedDate;

    /**
     *
     */
    private String riskLevel;

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
        ActResultLogEntity other = (ActResultLogEntity) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getOnlineSeqid() == null ? other.getOnlineSeqid() == null : this.getOnlineSeqid().equals(other.getOnlineSeqid()))
            && (this.getBusinessId() == null ? other.getBusinessId() == null : this.getBusinessId().equals(other.getBusinessId()))
            && (this.getBecifno() == null ? other.getBecifno() == null : this.getBecifno().equals(other.getBecifno()))
            && (this.getIvisResult() == null ? other.getIvisResult() == null : this.getIvisResult().equals(other.getIvisResult()))
            && (this.getCreatedBy() == null ? other.getCreatedBy() == null : this.getCreatedBy().equals(other.getCreatedBy()))
            && (this.getCreatedDate() == null ? other.getCreatedDate() == null : this.getCreatedDate().equals(other.getCreatedDate()))
            && (this.getUpdateBy() == null ? other.getUpdateBy() == null : this.getUpdateBy().equals(other.getUpdateBy()))
            && (this.getUpdatedDate() == null ? other.getUpdatedDate() == null : this.getUpdatedDate().equals(other.getUpdatedDate()))
            && (this.getRiskLevel() == null ? other.getRiskLevel() == null : this.getRiskLevel().equals(other.getRiskLevel()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getOnlineSeqid() == null) ? 0 : getOnlineSeqid().hashCode());
        result = prime * result + ((getBusinessId() == null) ? 0 : getBusinessId().hashCode());
        result = prime * result + ((getBecifno() == null) ? 0 : getBecifno().hashCode());
        result = prime * result + ((getIvisResult() == null) ? 0 : getIvisResult().hashCode());
        result = prime * result + ((getCreatedBy() == null) ? 0 : getCreatedBy().hashCode());
        result = prime * result + ((getCreatedDate() == null) ? 0 : getCreatedDate().hashCode());
        result = prime * result + ((getUpdateBy() == null) ? 0 : getUpdateBy().hashCode());
        result = prime * result + ((getUpdatedDate() == null) ? 0 : getUpdatedDate().hashCode());
        result = prime * result + ((getRiskLevel() == null) ? 0 : getRiskLevel().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", onlineSeqid=").append(onlineSeqid);
        sb.append(", businessId=").append(businessId);
        sb.append(", becifno=").append(becifno);
        sb.append(", ivisResult=").append(ivisResult);
        sb.append(", createdBy=").append(createdBy);
        sb.append(", createdDate=").append(createdDate);
        sb.append(", updateBy=").append(updateBy);
        sb.append(", updatedDate=").append(updatedDate);
        sb.append(", riskLevel=").append(riskLevel);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
