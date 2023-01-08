/*
 Navicat Premium Data Transfer

 Source Server         : 59.172.75.156-NAS公网IP
 Source Server Type    : MySQL
 Source Server Version : 80030
 Source Host           : 59.172.75.156:33016
 Source Schema         : appstore

 Target Server Type    : MySQL
 Target Server Version : 80030
 File Encoding         : 65001

 Date: 29/12/2022 11:25:40
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for apps
-- ----------------------------
DROP TABLE IF EXISTS `apps`;
CREATE TABLE `apps`  (
  `ID` char(9) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `name` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL,
  `seller` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL,
  `price` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `category` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `updated` date NULL DEFAULT NULL,
  `version` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `size` decimal(5, 1) NULL DEFAULT NULL,
  `language` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL,
  `seller_spec` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL,
  `copy_right` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL,
  `limit_grade` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `compatibility` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL,
  `rating_all` decimal(2, 1) NULL DEFAULT NULL,
  `ra_amount` int NULL DEFAULT NULL,
  `rating_current` decimal(2, 1) NULL DEFAULT NULL,
  `rc_amount` int NULL DEFAULT NULL,
  `in_app_purchase` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL,
  `more_app` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL,
  `comment_1` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL,
  `rate_1` decimal(2, 1) NULL DEFAULT NULL,
  `comment_2` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL,
  `rate_2` decimal(2, 1) NULL DEFAULT NULL,
  `comment_3` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL,
  `rate_3` decimal(2, 1) NULL DEFAULT NULL,
  `purchase_also_1` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL,
  `purchase_also_2` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL,
  `purchase_also_3` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL,
  `purchase_also_4` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL,
  `purchase_also_5` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL,
  `content` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
