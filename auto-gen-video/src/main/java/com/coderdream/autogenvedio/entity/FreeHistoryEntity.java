package com.coderdream.autogenvedio.entity;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class FreeHistoryEntity {

    /**
     * 应用名称
     */
    private String appId;

    /**
     * 应用名称
     */
    private String name;

    /**
     * 应用价格
     */
    private String priceStr;

    /**
     * 应用价格（中文字符串）
     */
    private String priceStrCn;

    /**
     * 应用价格（英文字符串）
     */
    private String priceStrEn;

    /**
     * 应用简介
     */
    private String description;

    /**
     * 美版URL
     */
    private String urlUs;

    /**
     * 限免日期
     */
    private Date freeDate;

    /**
     * 创建时间
     */
    private Date createTime;

}
