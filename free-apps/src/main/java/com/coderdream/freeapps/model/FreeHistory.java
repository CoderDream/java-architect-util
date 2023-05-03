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
 * @TableName t_free_history
 */
@TableName(value ="t_free_history")
@Data
public class FreeHistory implements Serializable {
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
     * 限免日期
     */
    private Date freeDate;

    /**
     * 是否推荐 0未推荐，1已推荐（默认）
     */
    private Integer recommendFlag;

    /**
     * 名称
     */
    private String title;

    /**
     * 副标题
     */
    private String subTitle;

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
     * 是否美区限免（默认为0）
     */
    private Boolean usFlag;

    /**
     * 国区URL
     */
    private String urlCn;

    /**
     * 美区URL
     */
    private String urlUs;

    /**
     * 应用简介
     */
    private String description;

    /**
     * 数据来源（cl、wechat、goapps、geek）
     */
    private String dataSource;

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

    /**
     * 昨日URL
     */
    private String yesterdayUrl;

    /**
     * app在今日限免列表中的位置，从01开始
     */
    private String appIndex;

    /**
     * 截图url
     */
    private String snapshot;

    /**
     * QR url
     */
    private String qrUrl;

    /**
     * App url
     */
    private String appUrl;

    /**
     * App url
     */
    private String capabilityStr;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
