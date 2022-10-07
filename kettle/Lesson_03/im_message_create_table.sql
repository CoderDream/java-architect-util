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

 Date: 30/09/2022 15:28:44
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for im_message2
-- ----------------------------
DROP TABLE IF EXISTS `im_message2`;
CREATE TABLE `im_message2`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `sender` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '消息发送者：SYSTEM',
  `send_time` datetime NOT NULL,
  `receiver` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '消息接受者',
  `content` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '消息内容',
  `is_read` tinyint NOT NULL COMMENT '消息是否被读取：0-未读；非0-已读',
  `read_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `id_UNIQUE`(`id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 100000 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin COMMENT = '消息表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
