package com.coderdream.freeapps.model;

import lombok.Data;

import java.util.Map;

@Data
public class AppExtraInfo {

    /**
     * 供应商
     */
    private String supplier;

    /**
     * 大小
     */
    private String size;


    /**
     * 类别
     */
    private String category;

    /**
     * 兼容性
     */
    private Map<String, String> compatibility;

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
     * 价格
     */
    private String price;

    /**
     * App 应用内购买项目
     */
    private Map<String, String> appInPurchase;

}
