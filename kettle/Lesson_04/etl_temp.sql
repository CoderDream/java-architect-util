/*
 Navicat Premium Data Transfer

 Source Server         : 171.113.161.31-NAS公网IP
 Source Server Type    : MySQL
 Source Server Version : 80030
 Source Host           : 171.113.161.31:33016
 Source Schema         : kettle_demo

 Target Server Type    : MySQL
 Target Server Version : 80030
 File Encoding         : 65001

 Date: 02/10/2022 10:48:33
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for etl_temp
-- ----------------------------
DROP TABLE IF EXISTS `etl_temp`;
CREATE TABLE `etl_temp`  (
  `id` int NOT NULL,
  `time_stamp` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of etl_temp
-- ----------------------------
INSERT INTO `etl_temp` VALUES (1, '2018-05-22 00:00:00');

SET FOREIGN_KEY_CHECKS = 1;
