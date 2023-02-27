/*
 Navicat Premium Data Transfer

 Source Server         : 27.19.125.63-NAS公网IP
 Source Server Type    : MySQL
 Source Server Version : 80030
 Source Host           : 27.19.125.63:33016
 Source Schema         : mybatis_plus

 Target Server Type    : MySQL
 Target Server Version : 80030
 File Encoding         : 65001

 Date: 12/09/2022 21:47:21
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_demo
-- ----------------------------
DROP TABLE IF EXISTS `t_demo`;
CREATE TABLE `t_demo`  (
  `id` bigint NOT NULL COMMENT '主键ID',
  `user_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '姓名',
  `age` int NULL DEFAULT NULL COMMENT '年龄',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '邮箱',
  `is_deleted` int NULL DEFAULT 0 COMMENT '逻辑删除',
  `sex` int NULL DEFAULT NULL COMMENT '性别',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_demo
-- ----------------------------
INSERT INTO `t_demo` VALUES (1, 'Jone', 18, 'test1@baomidou.com', 0, NULL);
INSERT INTO `t_demo` VALUES (2, 'LambdaUpdateWrapper修改', 20, 'abc改@163.com', 0, NULL);
INSERT INTO `t_demo` VALUES (3, 'Tom', 28, 'test3@baomidou.com', 0, NULL);
INSERT INTO `t_demo` VALUES (4, '李四', 21, 'lisi@162.com', 0, NULL);
INSERT INTO `t_demo` VALUES (5, 'Billie', 24, 'test5@baomidou.com', 0, NULL);
INSERT INTO `t_demo` VALUES (6, 'test', NULL, NULL, 1, NULL);
INSERT INTO `t_demo` VALUES (7, 'admin', 33, NULL, 0, 1);
INSERT INTO `t_demo` VALUES (8, 'admin', 33, NULL, 0, 1);
INSERT INTO `t_demo` VALUES (1568432808453029890, 'user1', 21, 'user1@163.com', 0, NULL);
INSERT INTO `t_demo` VALUES (1568432808566276098, 'user2', 22, 'user2@163.com', 0, NULL);
INSERT INTO `t_demo` VALUES (1568432808566276099, 'user3', 23, 'user3@163.com', 0, NULL);
INSERT INTO `t_demo` VALUES (1568432808566276100, 'user4', 24, 'user4@163.com', 0, NULL);
INSERT INTO `t_demo` VALUES (1568432808566276101, 'user5', 25, 'user5@163.com', 0, NULL);
INSERT INTO `t_demo` VALUES (1568432808566276102, 'user6', 26, 'user6@163.com', 0, NULL);
INSERT INTO `t_demo` VALUES (1568432808566276103, 'user7', 27, 'user7@163.com', 0, NULL);
INSERT INTO `t_demo` VALUES (1568432808566276104, 'user8', 28, 'user8@163.com', 0, NULL);
INSERT INTO `t_demo` VALUES (1568432808566276105, 'user9', 29, 'user9@163.com', 0, NULL);
INSERT INTO `t_demo` VALUES (1568432808566276106, 'user10', 30, 'user10@163.com', 0, NULL);

-- ----------------------------
-- Table structure for t_product
-- ----------------------------
DROP TABLE IF EXISTS `t_product`;
CREATE TABLE `t_product`  (
  `id` bigint NOT NULL COMMENT '主键ID',
  `NAME` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '商品名称',
  `price` int NULL DEFAULT 0 COMMENT '价格',
  `VERSION` int NULL DEFAULT 0 COMMENT '乐观锁版本号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_product
-- ----------------------------
INSERT INTO `t_product` VALUES (1, '外星人笔记本', 140, 3);

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` bigint NOT NULL COMMENT '主键ID',
  `user_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '姓名',
  `age` int NULL DEFAULT NULL COMMENT '年龄',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '邮箱',
  `is_deleted` int NULL DEFAULT 0 COMMENT '逻辑删除',
  `sex` int NULL DEFAULT NULL COMMENT '性别',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, 'Jone', 18, 'test1@baomidou.com', 0, NULL);
INSERT INTO `t_user` VALUES (2, 'LambdaUpdateWrapper修改', 20, 'abc改@163.com', 0, NULL);
INSERT INTO `t_user` VALUES (3, 'Tom', 28, 'test3@baomidou.com', 0, NULL);
INSERT INTO `t_user` VALUES (4, '李四', 21, 'lisi@162.com', 0, NULL);
INSERT INTO `t_user` VALUES (5, 'Billie', 24, 'test5@baomidou.com', 0, NULL);
INSERT INTO `t_user` VALUES (6, 'test', NULL, NULL, 1, NULL);
INSERT INTO `t_user` VALUES (7, 'admin', 33, NULL, 0, 1);
INSERT INTO `t_user` VALUES (8, 'admin', 33, NULL, 0, 1);
INSERT INTO `t_user` VALUES (1568432808453029890, 'user1', 21, 'user1@163.com', 0, NULL);
INSERT INTO `t_user` VALUES (1568432808566276098, 'user2', 22, 'user2@163.com', 0, NULL);
INSERT INTO `t_user` VALUES (1568432808566276099, 'user3', 23, 'user3@163.com', 0, NULL);
INSERT INTO `t_user` VALUES (1568432808566276100, 'user4', 24, 'user4@163.com', 0, NULL);
INSERT INTO `t_user` VALUES (1568432808566276101, 'user5', 25, 'user5@163.com', 0, NULL);
INSERT INTO `t_user` VALUES (1568432808566276102, 'user6', 26, 'user6@163.com', 0, NULL);
INSERT INTO `t_user` VALUES (1568432808566276103, 'user7', 27, 'user7@163.com', 0, NULL);
INSERT INTO `t_user` VALUES (1568432808566276104, 'user8', 28, 'user8@163.com', 0, NULL);
INSERT INTO `t_user` VALUES (1568432808566276105, 'user9', 29, 'user9@163.com', 0, NULL);
INSERT INTO `t_user` VALUES (1568432808566276106, 'user10', 30, 'user10@163.com', 0, NULL);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint NOT NULL COMMENT '主键ID',
  `user_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '姓名',
  `age` int NULL DEFAULT NULL COMMENT '年龄',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '邮箱',
  `is_deleted` int NULL DEFAULT 0 COMMENT '逻辑删除',
  `sex` int NULL DEFAULT NULL COMMENT '性别',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'Jone', 18, 'test1@baomidou.com', 0, NULL);
INSERT INTO `user` VALUES (2, 'LambdaUpdateWrapper修改', 20, 'abc改@163.com', 0, NULL);
INSERT INTO `user` VALUES (3, 'Tom', 28, 'test3@baomidou.com', 0, NULL);
INSERT INTO `user` VALUES (4, '李四', 21, 'lisi@162.com', 0, NULL);
INSERT INTO `user` VALUES (5, 'Billie', 24, 'test5@baomidou.com', 0, NULL);
INSERT INTO `user` VALUES (6, 'test', NULL, NULL, 1, NULL);
INSERT INTO `user` VALUES (7, 'admin', 33, NULL, 0, 1);
INSERT INTO `user` VALUES (8, 'admin', 33, NULL, 0, 1);
INSERT INTO `user` VALUES (1568432808453029890, 'user1', 21, 'user1@163.com', 0, NULL);
INSERT INTO `user` VALUES (1568432808566276098, 'user2', 22, 'user2@163.com', 0, NULL);
INSERT INTO `user` VALUES (1568432808566276099, 'user3', 23, 'user3@163.com', 0, NULL);
INSERT INTO `user` VALUES (1568432808566276100, 'user4', 24, 'user4@163.com', 0, NULL);
INSERT INTO `user` VALUES (1568432808566276101, 'user5', 25, 'user5@163.com', 0, NULL);
INSERT INTO `user` VALUES (1568432808566276102, 'user6', 26, 'user6@163.com', 0, NULL);
INSERT INTO `user` VALUES (1568432808566276103, 'user7', 27, 'user7@163.com', 0, NULL);
INSERT INTO `user` VALUES (1568432808566276104, 'user8', 28, 'user8@163.com', 0, NULL);
INSERT INTO `user` VALUES (1568432808566276105, 'user9', 29, 'user9@163.com', 0, NULL);
INSERT INTO `user` VALUES (1568432808566276106, 'user10', 30, 'user10@163.com', 0, NULL);

SET FOREIGN_KEY_CHECKS = 1;
