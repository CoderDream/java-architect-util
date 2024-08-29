/*
 Navicat Premium Data Transfer

 Source Server         : Windows11-docker
 Source Server Type    : MySQL
 Source Server Version : 80038
 Source Host           : localhost:3306
 Source Schema         : ry-vue

 Target Server Type    : MySQL
 Target Server Version : 80038
 File Encoding         : 65001

 Date: 26/08/2024 09:20:42
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for biz_communication_health_stats
-- ----------------------------
DROP TABLE IF EXISTS `biz_communication_health_stats`;
CREATE TABLE `biz_communication_health_stats`  (
  `stats_id` bigint NOT NULL COMMENT '统计主键',
  `stats_date` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '统计时间 yyyy-MM-dd',
  `excellent_amount` int NULL DEFAULT NULL COMMENT '健康度优次数',
  `good_amount` int NULL DEFAULT NULL COMMENT '健康度良次数',
  `poor_amount` int NULL DEFAULT NULL COMMENT '健康度差次数',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`stats_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '通信健康度统计' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
