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
-- Table structure for test_a
-- ----------------------------
DROP TABLE IF EXISTS `test_a`;
CREATE TABLE `test_a`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `last_update_don` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of test_a
-- ----------------------------
INSERT INTO `test_a` VALUES (1, 'zhao', '2018-11-06 18:00:45');
INSERT INTO `test_a` VALUES (2, 'qian', '2018-11-06 18:00:58');
INSERT INTO `test_a` VALUES (3, 'sun', '2018-11-06 18:01:16');
INSERT INTO `test_a` VALUES (4, 'li', '2018-11-06 18:02:24');

SET FOREIGN_KEY_CHECKS = 1;
