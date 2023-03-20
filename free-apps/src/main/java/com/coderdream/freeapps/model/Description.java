package com.coderdream.freeapps.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @TableName t_description
 */
@TableName(value ="t_description")
@Data
public class Description implements Serializable {
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
     * 名称
     */
    private String title;

    /**
     * 副标题
     */
    private String subTitle;

    /**
     * 专为设计
     */
    private String designedFor;

    /**
     * 排名
     */
    private String ranking;

    /**
     * 评分字符串
     */
    private String ratingStr;

    /**
     * 评分
     */
    private BigDecimal rating;

    /**
     * 评分人数
     */
    private Integer rateAmount;

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
     * 美区应用简介
     */
    private String descriptionUs;

    /**
     * 国区应用简介
     */
    private String descriptionCn;

    /**
     * cl应用简介
     */
    private String descriptionCl;

    /**
     * iOS口袋应用简介
     */
    private String descriptionWc;

    /**
     * goapps应用简介
     */
    private String descriptionGo;

    /**
     * geek应用简介
     */
    private String descriptionGeek;

    /**
     * 个人修饰应用简介
     */
    private String descriptionMy;

    /**
     * 一句话应用简介，用于字幕
     */
    private String descriptionZm;

    /**
     * 供应商
     */
    private String supplier;

    /**
     * 应用大小信息
     */
    private String appSizeStr;

    /**
     * 应用大小实际值
     */
    private BigDecimal appSize;

    /**
     * 应用分类ID
     */
    private Integer categoryId;

    /**
     * 应用分类
     */
    private String category;

    /**
     * 兼容性
     */
    private Object compatibility;

    /**
     * 语言
     */
    private String language;

    /**
     * 年龄分级
     */
    private String age;

    /**
     * 版权
     */
    private String copyright;

    /**
     * 应用内购买项目
     */
    private Object appInPurchase;

    /**
     * 截图地址
     */
    private Object snapshotUrl;

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
        Description other = (Description) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getAppId() == null ? other.getAppId() == null : this.getAppId().equals(other.getAppId()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getSubTitle() == null ? other.getSubTitle() == null : this.getSubTitle().equals(other.getSubTitle()))
            && (this.getDesignedFor() == null ? other.getDesignedFor() == null : this.getDesignedFor().equals(other.getDesignedFor()))
            && (this.getRanking() == null ? other.getRanking() == null : this.getRanking().equals(other.getRanking()))
            && (this.getRatingStr() == null ? other.getRatingStr() == null : this.getRatingStr().equals(other.getRatingStr()))
            && (this.getRating() == null ? other.getRating() == null : this.getRating().equals(other.getRating()))
            && (this.getRateAmount() == null ? other.getRateAmount() == null : this.getRateAmount().equals(other.getRateAmount()))
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
            && (this.getDescriptionUs() == null ? other.getDescriptionUs() == null : this.getDescriptionUs().equals(other.getDescriptionUs()))
            && (this.getDescriptionCn() == null ? other.getDescriptionCn() == null : this.getDescriptionCn().equals(other.getDescriptionCn()))
            && (this.getDescriptionCl() == null ? other.getDescriptionCl() == null : this.getDescriptionCl().equals(other.getDescriptionCl()))
            && (this.getDescriptionWc() == null ? other.getDescriptionWc() == null : this.getDescriptionWc().equals(other.getDescriptionWc()))
            && (this.getDescriptionGo() == null ? other.getDescriptionGo() == null : this.getDescriptionGo().equals(other.getDescriptionGo()))
            && (this.getDescriptionGeek() == null ? other.getDescriptionGeek() == null : this.getDescriptionGeek().equals(other.getDescriptionGeek()))
            && (this.getDescriptionMy() == null ? other.getDescriptionMy() == null : this.getDescriptionMy().equals(other.getDescriptionMy()))
            && (this.getDescriptionZm() == null ? other.getDescriptionZm() == null : this.getDescriptionZm().equals(other.getDescriptionZm()))
            && (this.getSupplier() == null ? other.getSupplier() == null : this.getSupplier().equals(other.getSupplier()))
            && (this.getAppSizeStr() == null ? other.getAppSizeStr() == null : this.getAppSizeStr().equals(other.getAppSizeStr()))
            && (this.getAppSize() == null ? other.getAppSize() == null : this.getAppSize().equals(other.getAppSize()))
            && (this.getCategoryId() == null ? other.getCategoryId() == null : this.getCategoryId().equals(other.getCategoryId()))
            && (this.getCategory() == null ? other.getCategory() == null : this.getCategory().equals(other.getCategory()))
            && (this.getCompatibility() == null ? other.getCompatibility() == null : this.getCompatibility().equals(other.getCompatibility()))
            && (this.getLanguage() == null ? other.getLanguage() == null : this.getLanguage().equals(other.getLanguage()))
            && (this.getAge() == null ? other.getAge() == null : this.getAge().equals(other.getAge()))
            && (this.getCopyright() == null ? other.getCopyright() == null : this.getCopyright().equals(other.getCopyright()))
            && (this.getAppInPurchase() == null ? other.getAppInPurchase() == null : this.getAppInPurchase().equals(other.getAppInPurchase()))
            && (this.getSnapshotUrl() == null ? other.getSnapshotUrl() == null : this.getSnapshotUrl().equals(other.getSnapshotUrl()))
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
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getSubTitle() == null) ? 0 : getSubTitle().hashCode());
        result = prime * result + ((getDesignedFor() == null) ? 0 : getDesignedFor().hashCode());
        result = prime * result + ((getRanking() == null) ? 0 : getRanking().hashCode());
        result = prime * result + ((getRatingStr() == null) ? 0 : getRatingStr().hashCode());
        result = prime * result + ((getRating() == null) ? 0 : getRating().hashCode());
        result = prime * result + ((getRateAmount() == null) ? 0 : getRateAmount().hashCode());
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
        result = prime * result + ((getDescriptionUs() == null) ? 0 : getDescriptionUs().hashCode());
        result = prime * result + ((getDescriptionCn() == null) ? 0 : getDescriptionCn().hashCode());
        result = prime * result + ((getDescriptionCl() == null) ? 0 : getDescriptionCl().hashCode());
        result = prime * result + ((getDescriptionWc() == null) ? 0 : getDescriptionWc().hashCode());
        result = prime * result + ((getDescriptionGo() == null) ? 0 : getDescriptionGo().hashCode());
        result = prime * result + ((getDescriptionGeek() == null) ? 0 : getDescriptionGeek().hashCode());
        result = prime * result + ((getDescriptionMy() == null) ? 0 : getDescriptionMy().hashCode());
        result = prime * result + ((getDescriptionZm() == null) ? 0 : getDescriptionZm().hashCode());
        result = prime * result + ((getSupplier() == null) ? 0 : getSupplier().hashCode());
        result = prime * result + ((getAppSizeStr() == null) ? 0 : getAppSizeStr().hashCode());
        result = prime * result + ((getAppSize() == null) ? 0 : getAppSize().hashCode());
        result = prime * result + ((getCategoryId() == null) ? 0 : getCategoryId().hashCode());
        result = prime * result + ((getCategory() == null) ? 0 : getCategory().hashCode());
        result = prime * result + ((getCompatibility() == null) ? 0 : getCompatibility().hashCode());
        result = prime * result + ((getLanguage() == null) ? 0 : getLanguage().hashCode());
        result = prime * result + ((getAge() == null) ? 0 : getAge().hashCode());
        result = prime * result + ((getCopyright() == null) ? 0 : getCopyright().hashCode());
        result = prime * result + ((getAppInPurchase() == null) ? 0 : getAppInPurchase().hashCode());
        result = prime * result + ((getSnapshotUrl() == null) ? 0 : getSnapshotUrl().hashCode());
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
        sb.append(", title=").append(title);
        sb.append(", subTitle=").append(subTitle);
        sb.append(", designedFor=").append(designedFor);
        sb.append(", ranking=").append(ranking);
        sb.append(", ratingStr=").append(ratingStr);
        sb.append(", rating=").append(rating);
        sb.append(", rateAmount=").append(rateAmount);
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
        sb.append(", descriptionUs=").append(descriptionUs);
        sb.append(", descriptionCn=").append(descriptionCn);
        sb.append(", descriptionCl=").append(descriptionCl);
        sb.append(", descriptionWc=").append(descriptionWc);
        sb.append(", descriptionGo=").append(descriptionGo);
        sb.append(", descriptionGeek=").append(descriptionGeek);
        sb.append(", descriptionMy=").append(descriptionMy);
        sb.append(", descriptionZm=").append(descriptionZm);
        sb.append(", supplier=").append(supplier);
        sb.append(", appSizeStr=").append(appSizeStr);
        sb.append(", appSize=").append(appSize);
        sb.append(", categoryId=").append(categoryId);
        sb.append(", category=").append(category);
        sb.append(", compatibility=").append(compatibility);
        sb.append(", language=").append(language);
        sb.append(", age=").append(age);
        sb.append(", copyright=").append(copyright);
        sb.append(", appInPurchase=").append(appInPurchase);
        sb.append(", snapshotUrl=").append(snapshotUrl);
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
