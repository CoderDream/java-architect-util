/*
 Navicat Premium Data Transfer

 Source Server         : 171.113.161.31-NAS公网IP
 Source Server Type    : MySQL
 Source Server Version : 80030
 Source Host           : 171.113.161.31:33016
 Source Schema         : testa

 Target Server Type    : MySQL
 Target Server Version : 80030
 File Encoding         : 65001

 Date: 30/09/2022 11:33:05
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for test_b
-- ----------------------------
DROP TABLE IF EXISTS `test_b`;
CREATE TABLE `test_b`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `last_update_don` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of test_b
-- ----------------------------
INSERT INTO `test_b` VALUES (1, 'zhao', '2018-11-06 18:00:45');
INSERT INTO `test_b` VALUES (2, 'qian', '2018-11-06 18:00:58');
INSERT INTO `test_b` VALUES (3, 'sun', '2018-11-06 18:01:06');

SET FOREIGN_KEY_CHECKS = 1;
