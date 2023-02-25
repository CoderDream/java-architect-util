package com.coderdream.autogenvedio.entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class AppEntity {

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
    private Integer priceCn;


    /**
     * 应用价格
     */
    private Double priceUs;


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
     * 是否美区限免
     */
    private Boolean usFlag = false;

    /**
     * 国区URL
     */
    private String urlCn;

    /**
     * 美版URL
     */
    private String urlUs;
}
