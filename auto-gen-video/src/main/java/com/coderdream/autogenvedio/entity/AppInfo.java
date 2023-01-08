package com.coderdream.autogenvedio.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class AppInfo {

    //  `ID` char(9) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
    private String appId;

    //  `name` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL,
    private String name;

//  `seller` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL,

    //  `price` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '价格',
    private Integer price;

    //  `category` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '分类',
    private String category;
    private String osType;

    //  `updated` date NULL DEFAULT NULL,
//    private Date updated;
    private String freeDate;

    //  `version` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '版本',
//  `size` decimal(5, 1) NULL DEFAULT NULL,
//  `language` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT '语言',
//  `seller_spec` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL,
//  `copy_right` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL,
//  `limit_grade` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
//  `compatibility` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL,
//  `rating_all` decimal(2, 1) NULL DEFAULT NULL,
//  `ra_amount` int NULL DEFAULT NULL,
    private Integer rateAmount;
    //  `rating_current` decimal(2, 1) NULL DEFAULT NULL,
    private Double ratingCurrent;
    //  `rc_amount` int NULL DEFAULT NULL,
//  `in_app_purchase` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL,
//  `more_app` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL,
//  `comment_1` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL,
//  `rate_1` decimal(2, 1) NULL DEFAULT NULL,
//    private Double rate;
//  `comment_2` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL,
//  `rate_2` decimal(2, 1) NULL DEFAULT NULL,
//  `comment_3` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL,
//  `rate_3` decimal(2, 1) NULL DEFAULT NULL,
//  `purchase_also_1` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL,
//  `purchase_also_2` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL,
//  `purchase_also_3` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL,
//  `purchase_also_4` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL,
//  `purchase_also_5` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL,
//  `content` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL,
    private String brief;
    private List<String> content;

    private String url;
    private String urlCn;
}
