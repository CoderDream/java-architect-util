package com.coderdream.freeapps.model;

import java.util.List;
import lombok.Data;

@Data
public class AppBrief {

    //  `ID` char(9) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
    private String appId;

    /**
     * 文件名，样例为 01_id1234567890，逻辑为第几个应用+下划线+应用ID，
     * 后面所有的图片，包括屏幕截图，图标，应用详情图片、二维码都以这个文件名结尾
     */
    private String filename;

    /**
     * 文件名，样例为 1_id1234567890，逻辑为第几个应用+下划线+应用ID，
     */
    private String singleFilename;

    //  `name` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL,
    private String name;

//  `seller` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL,

    //  `price` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '价格',
    private Integer price;

    private String osType;

    //  `updated` date NULL DEFAULT NULL,
//    private Date updated;
//    private String freeDate;

    /**
     * 简介
     */
    private String brief;

    /**
     * 详情
     */
    private List<String> content;

    /**
     * 原始链接
     */
    private String url;

    /**
     * 国区链接
     */
    private String urlCn;

    /**
     * 截图地址
     */
    private String snapshotPath;

    /**
     * 二维码路径
     */
    private String qrUrl;

    /**
     * 图标路径
     */
    private String iconUrl;

    /**
     * App详情路径 detail 文件夹
     */
    private String detailPath;


    /**
     * 单张海报路径（上面详情，下面二维码）
     */
    private String singlePosterPath;


    /**
     * App列表路径
     */
    private String listAppsPath;

    /**
     * 整页海报路径
     */
    private String pagePosterPath;
    /**
     * 整页海报名称
     */
    private String pagePosterFilename;
    /**
     * 外区限免
     */
    private Boolean onlyUs = false;
}
