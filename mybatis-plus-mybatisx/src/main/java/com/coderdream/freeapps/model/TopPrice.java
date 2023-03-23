package com.coderdream.freeapps.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName t_top_price
 */
@TableName(value ="t_top_price")
@Data
public class TopPrice implements Serializable {
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
     * 最高价格
     */
    private BigDecimal topPrice;

    /**
     * 国区价格
     */
    private Integer priceCn;

    /**
     * 美区价格
     */
    private BigDecimal priceUs;

    /**
     * 价格信息
     */
    private String priceStr;

    /**
     * 国区价格信息
     */
    private String priceStrCn;

    /**
     * 美区价格信息
     */
    private String priceStrUs;

    /**
     * 是否美区限免
     */
    private Integer usFlag;

    /**
     * 是否有内购，默认为0，无内购
     */
    private Integer inPurchaseFlag;

    /**
     * 内购是否限免
     */
    private Integer inPurchaseFreeFlag;

    /**
     * 国区URL
     */
    private String urlCn;

    /**
     * 美区URL
     */
    private String urlUs;

    /**
     * 应用内购买项目
     */
    private Object appInPurchase;

    /**
     * 版本历史
     */
    private Object versionHistory;

    /**
     * 备注
     */
    private String remark;

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
        TopPrice other = (TopPrice) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getAppId() == null ? other.getAppId() == null : this.getAppId().equals(other.getAppId()))
            && (this.getTopPrice() == null ? other.getTopPrice() == null : this.getTopPrice().equals(other.getTopPrice()))
            && (this.getPriceCn() == null ? other.getPriceCn() == null : this.getPriceCn().equals(other.getPriceCn()))
            && (this.getPriceUs() == null ? other.getPriceUs() == null : this.getPriceUs().equals(other.getPriceUs()))
            && (this.getPriceStr() == null ? other.getPriceStr() == null : this.getPriceStr().equals(other.getPriceStr()))
            && (this.getPriceStrCn() == null ? other.getPriceStrCn() == null : this.getPriceStrCn().equals(other.getPriceStrCn()))
            && (this.getPriceStrUs() == null ? other.getPriceStrUs() == null : this.getPriceStrUs().equals(other.getPriceStrUs()))
            && (this.getUsFlag() == null ? other.getUsFlag() == null : this.getUsFlag().equals(other.getUsFlag()))
            && (this.getInPurchaseFlag() == null ? other.getInPurchaseFlag() == null : this.getInPurchaseFlag().equals(other.getInPurchaseFlag()))
            && (this.getInPurchaseFreeFlag() == null ? other.getInPurchaseFreeFlag() == null : this.getInPurchaseFreeFlag().equals(other.getInPurchaseFreeFlag()))
            && (this.getUrlCn() == null ? other.getUrlCn() == null : this.getUrlCn().equals(other.getUrlCn()))
            && (this.getUrlUs() == null ? other.getUrlUs() == null : this.getUrlUs().equals(other.getUrlUs()))
            && (this.getAppInPurchase() == null ? other.getAppInPurchase() == null : this.getAppInPurchase().equals(other.getAppInPurchase()))
            && (this.getVersionHistory() == null ? other.getVersionHistory() == null : this.getVersionHistory().equals(other.getVersionHistory()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
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
        result = prime * result + ((getTopPrice() == null) ? 0 : getTopPrice().hashCode());
        result = prime * result + ((getPriceCn() == null) ? 0 : getPriceCn().hashCode());
        result = prime * result + ((getPriceUs() == null) ? 0 : getPriceUs().hashCode());
        result = prime * result + ((getPriceStr() == null) ? 0 : getPriceStr().hashCode());
        result = prime * result + ((getPriceStrCn() == null) ? 0 : getPriceStrCn().hashCode());
        result = prime * result + ((getPriceStrUs() == null) ? 0 : getPriceStrUs().hashCode());
        result = prime * result + ((getUsFlag() == null) ? 0 : getUsFlag().hashCode());
        result = prime * result + ((getInPurchaseFlag() == null) ? 0 : getInPurchaseFlag().hashCode());
        result = prime * result + ((getInPurchaseFreeFlag() == null) ? 0 : getInPurchaseFreeFlag().hashCode());
        result = prime * result + ((getUrlCn() == null) ? 0 : getUrlCn().hashCode());
        result = prime * result + ((getUrlUs() == null) ? 0 : getUrlUs().hashCode());
        result = prime * result + ((getAppInPurchase() == null) ? 0 : getAppInPurchase().hashCode());
        result = prime * result + ((getVersionHistory() == null) ? 0 : getVersionHistory().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
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
        sb.append(", topPrice=").append(topPrice);
        sb.append(", priceCn=").append(priceCn);
        sb.append(", priceUs=").append(priceUs);
        sb.append(", priceStr=").append(priceStr);
        sb.append(", priceStrCn=").append(priceStrCn);
        sb.append(", priceStrUs=").append(priceStrUs);
        sb.append(", usFlag=").append(usFlag);
        sb.append(", inPurchaseFlag=").append(inPurchaseFlag);
        sb.append(", inPurchaseFreeFlag=").append(inPurchaseFreeFlag);
        sb.append(", urlCn=").append(urlCn);
        sb.append(", urlUs=").append(urlUs);
        sb.append(", appInPurchase=").append(appInPurchase);
        sb.append(", versionHistory=").append(versionHistory);
        sb.append(", remark=").append(remark);
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