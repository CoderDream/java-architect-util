package com.coderdream.freeapps.dto;

import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

@Data
public class AppDTO {

    /**
     * ID
     */
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
     * 图标地址
     */
    private String appIconUrl;

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

}
