/*
Navicat MySQL Data Transfer

Source Server         : 本地数据库
Source Server Version : 50640
Source Host           : localhost:3306
Source Database       : db_middleware

Target Server Type    : MYSQL
Target Server Version : 50640
File Encoding         : 65001

Date: 2019-10-18 23:09:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for book_rob
-- ----------------------------
DROP TABLE IF EXISTS `book_rob`;
CREATE TABLE `book_rob` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `book_no` varchar(255) NOT NULL COMMENT '书籍编号',
  `rob_time` datetime DEFAULT NULL COMMENT '抢购时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=153 DEFAULT CHARSET=utf8 COMMENT='书籍抢购记录';

-- ----------------------------
-- Records of book_rob
-- ----------------------------
INSERT INTO `book_rob` VALUES ('2', '10010', 'BS20190421001', '2019-04-22 22:49:05');
INSERT INTO `book_rob` VALUES ('147', '10040', 'BS20190421001', '2019-04-22 23:28:05');
INSERT INTO `book_rob` VALUES ('148', '10042', 'BS20190421001', '2019-04-22 23:28:05');
INSERT INTO `book_rob` VALUES ('149', '10041', 'BS20190421001', '2019-04-22 23:28:05');
INSERT INTO `book_rob` VALUES ('150', '10045', 'BS20190421001', '2019-04-22 23:28:05');
INSERT INTO `book_rob` VALUES ('151', '10043', 'BS20190421001', '2019-04-22 23:28:05');
INSERT INTO `book_rob` VALUES ('152', '10044', 'BS20190421001', '2019-04-22 23:28:05');

-- ----------------------------
-- Table structure for book_stock
-- ----------------------------
DROP TABLE IF EXISTS `book_stock`;
CREATE TABLE `book_stock` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `book_no` varchar(255) NOT NULL COMMENT '书籍编号',
  `stock` int(255) NOT NULL COMMENT '库存',
  `is_active` tinyint(255) DEFAULT '1' COMMENT '是否上架（1=是；0=否）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='书籍库存表';

-- ----------------------------
-- Records of book_stock
-- ----------------------------
INSERT INTO `book_stock` VALUES ('1', 'BS20190421001', '4', '1');

-- ----------------------------
-- Table structure for item
-- ----------------------------
DROP TABLE IF EXISTS `item`;
CREATE TABLE `item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL COMMENT '商品编号',
  `name` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '商品名称',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='商品信息表';

-- ----------------------------
-- Records of item
-- ----------------------------
INSERT INTO `item` VALUES ('1', 'book_10010', '分布式中间件实战', '2019-03-17 17:21:16');

-- ----------------------------
-- Table structure for mq_order
-- ----------------------------
DROP TABLE IF EXISTS `mq_order`;
CREATE TABLE `mq_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) NOT NULL COMMENT '下单记录id',
  `business_time` datetime DEFAULT NULL COMMENT '失效下单记录的时间',
  `memo` varchar(255) DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='RabbitMQ失效下单记录的历史记录表';

-- ----------------------------
-- Records of mq_order
-- ----------------------------
INSERT INTO `mq_order` VALUES ('3', '6', '2019-04-12 23:48:04', '更新失效当前用户下单记录Id,orderId=6');
INSERT INTO `mq_order` VALUES ('4', '7', '2019-04-13 10:28:43', '更新失效当前用户下单记录Id,orderId=7');

-- ----------------------------
-- Table structure for praise
-- ----------------------------
DROP TABLE IF EXISTS `praise`;
CREATE TABLE `praise` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `blog_id` int(11) NOT NULL COMMENT '博客id',
  `user_id` int(11) NOT NULL COMMENT '点赞人',
  `praise_time` datetime DEFAULT NULL COMMENT '点赞时间',
  `status` int(11) DEFAULT '1' COMMENT '状态(1=正常;0=取消点赞)',
  `is_active` int(11) DEFAULT '1' COMMENT '是否有效(1=是;0=否)',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8mb4 COMMENT='点赞表';

-- ----------------------------
-- Records of praise
-- ----------------------------
INSERT INTO `praise` VALUES ('35', '2', '101', '2019-05-04 15:18:04', '1', '1', '2019-05-04 15:18:03', null);
INSERT INTO `praise` VALUES ('37', '1', '103', '2019-05-04 19:27:22', '1', '1', '2019-05-04 19:27:22', null);
INSERT INTO `praise` VALUES ('38', '1', '104', '2019-05-04 19:27:24', '1', '1', '2019-05-04 19:27:24', null);
INSERT INTO `praise` VALUES ('40', '3', '101', '2019-05-04 19:29:13', '1', '1', '2019-05-04 19:29:12', null);
INSERT INTO `praise` VALUES ('41', '3', '102', '2019-05-04 19:29:15', '1', '1', '2019-05-04 19:29:15', null);
INSERT INTO `praise` VALUES ('42', '3', '103', '2019-05-04 19:29:17', '1', '1', '2019-05-04 19:29:17', null);
INSERT INTO `praise` VALUES ('43', '4', '101', '2019-05-04 19:30:19', '1', '1', '2019-05-04 19:30:18', null);
INSERT INTO `praise` VALUES ('44', '5', '101', '2019-05-04 19:30:39', '1', '1', '2019-05-04 19:30:39', null);
INSERT INTO `praise` VALUES ('45', '1', '101', '2019-05-04 19:32:26', '1', '1', '2019-05-04 19:32:26', null);
INSERT INTO `praise` VALUES ('46', '1', '102', '2019-05-04 19:33:17', '1', '1', '2019-05-04 19:33:16', null);
INSERT INTO `praise` VALUES ('47', '2', '102', '2019-05-04 19:33:27', '1', '1', '2019-05-04 19:33:27', null);
INSERT INTO `praise` VALUES ('48', '2', '103', '2019-05-04 19:33:31', '1', '1', '2019-05-04 19:33:30', null);
INSERT INTO `praise` VALUES ('49', '2', '104', '2019-05-04 19:33:38', '1', '1', '2019-05-04 19:33:37', null);
INSERT INTO `praise` VALUES ('50', '2', '105', '2019-05-04 19:33:40', '1', '1', '2019-05-04 19:33:39', null);
INSERT INTO `praise` VALUES ('51', '100', '101', '2019-05-08 22:14:52', '0', '1', null, null);
INSERT INTO `praise` VALUES ('52', '100', '103', '2019-05-08 22:27:02', '0', '1', null, null);
INSERT INTO `praise` VALUES ('53', '100', '104', '2019-05-08 22:27:04', '0', '1', null, null);
INSERT INTO `praise` VALUES ('54', '100', '105', '2019-05-08 22:27:06', '0', '1', null, null);
INSERT INTO `praise` VALUES ('55', '100', '106', '2019-05-08 22:27:08', '0', '1', null, null);
INSERT INTO `praise` VALUES ('56', '100', '107', '2019-05-08 22:27:10', '0', '1', null, null);
INSERT INTO `praise` VALUES ('57', '100', '108', '2019-05-08 22:27:13', '1', '1', null, null);
INSERT INTO `praise` VALUES ('58', '100', '109', '2019-05-08 22:27:16', '1', '1', null, null);
INSERT INTO `praise` VALUES ('59', '100', '102', '2019-05-08 22:27:23', '0', '1', null, null);
INSERT INTO `praise` VALUES ('60', '100', '201', '2019-05-09 21:58:22', '1', '1', null, null);
INSERT INTO `praise` VALUES ('61', '100', '202', '2019-05-09 21:58:25', '1', '1', null, null);
INSERT INTO `praise` VALUES ('62', '100', '203', '2019-05-09 21:58:29', '1', '1', null, null);
INSERT INTO `praise` VALUES ('63', '100', '204', '2019-05-09 21:58:35', '1', '1', null, null);

-- ----------------------------
-- Table structure for red_detail
-- ----------------------------
DROP TABLE IF EXISTS `red_detail`;
CREATE TABLE `red_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `record_id` int(11) NOT NULL COMMENT '红包记录id',
  `amount` decimal(8,2) DEFAULT NULL COMMENT '金额（单位为分）',
  `is_active` tinyint(4) DEFAULT '1',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=133 DEFAULT CHARSET=utf8 COMMENT='红包明细金额';

-- ----------------------------
-- Records of red_detail
-- ----------------------------
INSERT INTO `red_detail` VALUES ('93', '12', '34.00', '1', '2019-03-23 20:41:22');
INSERT INTO `red_detail` VALUES ('94', '12', '61.00', '1', '2019-03-23 20:41:22');
INSERT INTO `red_detail` VALUES ('95', '12', '106.00', '1', '2019-03-23 20:41:22');
INSERT INTO `red_detail` VALUES ('96', '12', '28.00', '1', '2019-03-23 20:41:22');
INSERT INTO `red_detail` VALUES ('97', '12', '15.00', '1', '2019-03-23 20:41:22');
INSERT INTO `red_detail` VALUES ('98', '12', '214.00', '1', '2019-03-23 20:41:22');
INSERT INTO `red_detail` VALUES ('99', '12', '34.00', '1', '2019-03-23 20:41:22');
INSERT INTO `red_detail` VALUES ('100', '12', '237.00', '1', '2019-03-23 20:41:22');
INSERT INTO `red_detail` VALUES ('101', '12', '91.00', '1', '2019-03-23 20:41:22');
INSERT INTO `red_detail` VALUES ('102', '12', '180.00', '1', '2019-03-23 20:41:22');
INSERT INTO `red_detail` VALUES ('103', '13', '95.00', '1', null);
INSERT INTO `red_detail` VALUES ('104', '13', '37.00', '1', null);
INSERT INTO `red_detail` VALUES ('105', '13', '25.00', '1', null);
INSERT INTO `red_detail` VALUES ('106', '13', '10.00', '1', null);
INSERT INTO `red_detail` VALUES ('107', '13', '43.00', '1', null);
INSERT INTO `red_detail` VALUES ('108', '13', '22.00', '1', null);
INSERT INTO `red_detail` VALUES ('109', '13', '12.00', '1', null);
INSERT INTO `red_detail` VALUES ('110', '13', '150.00', '1', null);
INSERT INTO `red_detail` VALUES ('111', '13', '38.00', '1', null);
INSERT INTO `red_detail` VALUES ('112', '13', '68.00', '1', null);
INSERT INTO `red_detail` VALUES ('113', '14', '161.00', '1', null);
INSERT INTO `red_detail` VALUES ('114', '14', '65.00', '1', null);
INSERT INTO `red_detail` VALUES ('115', '14', '54.00', '1', null);
INSERT INTO `red_detail` VALUES ('116', '14', '130.00', '1', null);
INSERT INTO `red_detail` VALUES ('117', '14', '16.00', '1', null);
INSERT INTO `red_detail` VALUES ('118', '14', '44.00', '1', null);
INSERT INTO `red_detail` VALUES ('119', '14', '16.00', '1', null);
INSERT INTO `red_detail` VALUES ('120', '14', '220.00', '1', null);
INSERT INTO `red_detail` VALUES ('121', '14', '48.00', '1', null);
INSERT INTO `red_detail` VALUES ('122', '14', '246.00', '1', null);
INSERT INTO `red_detail` VALUES ('123', '15', '14.00', '1', null);
INSERT INTO `red_detail` VALUES ('124', '15', '149.00', '1', null);
INSERT INTO `red_detail` VALUES ('125', '15', '204.00', '1', null);
INSERT INTO `red_detail` VALUES ('126', '15', '139.00', '1', null);
INSERT INTO `red_detail` VALUES ('127', '15', '79.00', '1', null);
INSERT INTO `red_detail` VALUES ('128', '15', '38.00', '1', null);
INSERT INTO `red_detail` VALUES ('129', '15', '133.00', '1', null);
INSERT INTO `red_detail` VALUES ('130', '15', '85.00', '1', null);
INSERT INTO `red_detail` VALUES ('131', '15', '53.00', '1', null);
INSERT INTO `red_detail` VALUES ('132', '15', '106.00', '1', null);

-- ----------------------------
-- Table structure for red_record
-- ----------------------------
DROP TABLE IF EXISTS `red_record`;
CREATE TABLE `red_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `red_packet` varchar(255) CHARACTER SET utf8mb4 NOT NULL COMMENT '红包全局唯一标识串',
  `total` int(11) NOT NULL COMMENT '人数',
  `amount` decimal(10,2) DEFAULT NULL COMMENT '总金额（单位为分）',
  `is_active` tinyint(4) DEFAULT '1',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='发红包记录';

-- ----------------------------
-- Records of red_record
-- ----------------------------
INSERT INTO `red_record` VALUES ('12', '10010', 'redis:red:packet:10010:177565921763957', '10', '1000.00', '1', '2019-03-23 20:41:16');
INSERT INTO `red_record` VALUES ('13', '10030', 'redis:red:packet:10030:272246147091856', '10', '500.00', '1', null);
INSERT INTO `red_record` VALUES ('14', '10050', 'redis:red:packet:10050:356031000034967', '10', '1000.00', '1', null);
INSERT INTO `red_record` VALUES ('15', '10050', 'redis:red:packet:10050:356507939164216', '10', '1000.00', '1', null);

-- ----------------------------
-- Table structure for red_rob_record
-- ----------------------------
DROP TABLE IF EXISTS `red_rob_record`;
CREATE TABLE `red_rob_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '用户账号',
  `red_packet` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '红包标识串',
  `amount` decimal(8,2) DEFAULT NULL COMMENT '红包金额（单位为分）',
  `rob_time` datetime DEFAULT NULL COMMENT '时间',
  `is_active` tinyint(4) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=118 DEFAULT CHARSET=utf8 COMMENT='抢红包记录';

-- ----------------------------
-- Records of red_rob_record
-- ----------------------------
INSERT INTO `red_rob_record` VALUES ('82', '10010', 'redis:red:packet:10010:177565921763957', '34.00', '2019-03-24 17:03:25', '1');
INSERT INTO `red_rob_record` VALUES ('83', '10011', 'redis:red:packet:10010:177565921763957', '61.00', '2019-03-24 17:22:03', '1');
INSERT INTO `red_rob_record` VALUES ('84', '10012', 'redis:red:packet:10010:177565921763957', '106.00', '2019-03-24 17:33:12', '1');
INSERT INTO `red_rob_record` VALUES ('85', '10013', 'redis:red:packet:10010:177565921763957', '28.00', '2019-03-24 17:33:15', '1');
INSERT INTO `red_rob_record` VALUES ('86', '10014', 'redis:red:packet:10010:177565921763957', '15.00', '2019-03-24 17:33:18', '1');
INSERT INTO `red_rob_record` VALUES ('87', '10015', 'redis:red:packet:10010:177565921763957', '214.00', '2019-03-24 17:33:20', '1');
INSERT INTO `red_rob_record` VALUES ('88', '10016', 'redis:red:packet:10010:177565921763957', '34.00', '2019-03-24 17:33:22', '1');
INSERT INTO `red_rob_record` VALUES ('89', '10017', 'redis:red:packet:10010:177565921763957', '237.00', '2019-03-24 17:33:26', '1');
INSERT INTO `red_rob_record` VALUES ('90', '10018', 'redis:red:packet:10010:177565921763957', '91.00', '2019-03-24 17:33:29', '1');
INSERT INTO `red_rob_record` VALUES ('91', '10019', 'redis:red:packet:10010:177565921763957', '180.00', '2019-03-24 17:33:32', '1');
INSERT INTO `red_rob_record` VALUES ('92', '10035', 'redis:red:packet:10030:272246147091856', '38.00', '2019-03-24 22:57:40', '1');
INSERT INTO `red_rob_record` VALUES ('93', '10031', 'redis:red:packet:10030:272246147091856', '68.00', '2019-03-24 22:57:40', '1');
INSERT INTO `red_rob_record` VALUES ('94', '10033', 'redis:red:packet:10030:272246147091856', '22.00', '2019-03-24 22:57:40', '1');
INSERT INTO `red_rob_record` VALUES ('95', '10031', 'redis:red:packet:10030:272246147091856', '25.00', '2019-03-24 22:57:40', '1');
INSERT INTO `red_rob_record` VALUES ('96', '10034', 'redis:red:packet:10030:272246147091856', '37.00', '2019-03-24 22:57:40', '1');
INSERT INTO `red_rob_record` VALUES ('97', '10030', 'redis:red:packet:10030:272246147091856', '10.00', '2019-03-24 22:57:40', '1');
INSERT INTO `red_rob_record` VALUES ('98', '10035', 'redis:red:packet:10030:272246147091856', '150.00', '2019-03-24 22:57:40', '1');
INSERT INTO `red_rob_record` VALUES ('99', '10031', 'redis:red:packet:10030:272246147091856', '43.00', '2019-03-24 22:57:40', '1');
INSERT INTO `red_rob_record` VALUES ('100', '10030', 'redis:red:packet:10030:272246147091856', '12.00', '2019-03-24 22:57:40', '1');
INSERT INTO `red_rob_record` VALUES ('101', '10032', 'redis:red:packet:10030:272246147091856', '95.00', '2019-03-24 22:57:40', '1');
INSERT INTO `red_rob_record` VALUES ('102', '10035', 'redis:red:packet:10050:356031000034967', '130.00', '2019-03-25 22:05:28', '1');
INSERT INTO `red_rob_record` VALUES ('103', '10032', 'redis:red:packet:10050:356031000034967', '44.00', '2019-03-25 22:05:28', '1');
INSERT INTO `red_rob_record` VALUES ('104', '10033', 'redis:red:packet:10050:356031000034967', '161.00', '2019-03-25 22:05:28', '1');
INSERT INTO `red_rob_record` VALUES ('105', '10034', 'redis:red:packet:10050:356031000034967', '65.00', '2019-03-25 22:05:28', '1');
INSERT INTO `red_rob_record` VALUES ('106', '10031', 'redis:red:packet:10050:356031000034967', '246.00', '2019-03-25 22:05:28', '1');
INSERT INTO `red_rob_record` VALUES ('107', '10033', 'redis:red:packet:10050:356031000034967', '54.00', '2019-03-25 22:05:28', '1');
INSERT INTO `red_rob_record` VALUES ('108', '10030', 'redis:red:packet:10050:356031000034967', '16.00', '2019-03-25 22:05:28', '1');
INSERT INTO `red_rob_record` VALUES ('109', '10031', 'redis:red:packet:10050:356031000034967', '220.00', '2019-03-25 22:05:28', '1');
INSERT INTO `red_rob_record` VALUES ('110', '10031', 'redis:red:packet:10050:356031000034967', '48.00', '2019-03-25 22:05:28', '1');
INSERT INTO `red_rob_record` VALUES ('111', '10030', 'redis:red:packet:10050:356031000034967', '16.00', '2019-03-25 22:05:28', '1');
INSERT INTO `red_rob_record` VALUES ('112', '10031', 'redis:red:packet:10050:356507939164216', '149.00', '2019-03-25 22:07:00', '1');
INSERT INTO `red_rob_record` VALUES ('113', '10035', 'redis:red:packet:10050:356507939164216', '204.00', '2019-03-25 22:07:00', '1');
INSERT INTO `red_rob_record` VALUES ('114', '10033', 'redis:red:packet:10050:356507939164216', '139.00', '2019-03-25 22:07:00', '1');
INSERT INTO `red_rob_record` VALUES ('115', '10034', 'redis:red:packet:10050:356507939164216', '38.00', '2019-03-25 22:07:00', '1');
INSERT INTO `red_rob_record` VALUES ('116', '10030', 'redis:red:packet:10050:356507939164216', '79.00', '2019-03-25 22:07:00', '1');
INSERT INTO `red_rob_record` VALUES ('117', '10032', 'redis:red:packet:10050:356507939164216', '14.00', '2019-03-25 22:07:00', '1');

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT '0' COMMENT '用户id',
  `module` varchar(255) DEFAULT NULL COMMENT '所属操作模块',
  `data` varchar(5000) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '操作数据',
  `memo` varchar(500) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='日志记录表';

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES ('3', '2', '用户登录模块', '{\"userName\":\"jack\",\"password\":\"123456\",\"userId\":2}', '用户登录成功记录相关登录信息', '2019-04-07 22:59:20');
INSERT INTO `sys_log` VALUES ('4', '80001', '用户登录模块', '{\"userName\":\"jack-debug\",\"password\":\"123456\",\"userId\":80001}', '用户登录成功记录相关登录信息', '2019-04-28 22:35:25');
INSERT INTO `sys_log` VALUES ('5', '90001', '用户登录模块', '{\"userName\":\"a-xiu-luo\",\"password\":\"123456\",\"userId\":90001}', '用户登录成功记录相关登录信息', '2019-04-28 23:00:32');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `user_name` varchar(255) NOT NULL COMMENT '用户名',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_user_name` (`user_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='用户信息表';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'debug', '123456', '2019-04-07 19:03:41');
INSERT INTO `user` VALUES ('2', 'jack', '123456', '2019-04-07 19:03:48');
INSERT INTO `user` VALUES ('3', 'heart', '123456', '2019-04-07 19:03:56');

-- ----------------------------
-- Table structure for user_account
-- ----------------------------
DROP TABLE IF EXISTS `user_account`;
CREATE TABLE `user_account` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) NOT NULL COMMENT '用户账户id',
  `amount` decimal(10,4) NOT NULL COMMENT '账户余额',
  `version` int(11) DEFAULT '1' COMMENT '版本号字段',
  `is_active` tinyint(11) DEFAULT '1' COMMENT '是否有效(1=是;0=否)',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='用户账户表';

-- ----------------------------
-- Records of user_account
-- ----------------------------
INSERT INTO `user_account` VALUES ('1', '10010', '20.0000', '1', '1');
INSERT INTO `user_account` VALUES ('4', '10011', '-1860.0000', '1', '1');
INSERT INTO `user_account` VALUES ('5', '10012', '20.0000', '3', '1');
INSERT INTO `user_account` VALUES ('6', '10013', '20.0000', '1', '1');

-- ----------------------------
-- Table structure for user_account_record
-- ----------------------------
DROP TABLE IF EXISTS `user_account_record`;
CREATE TABLE `user_account_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `account_id` int(11) NOT NULL COMMENT '账户表主键id',
  `money` decimal(10,4) DEFAULT NULL COMMENT '提现成功时记录的金额',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=422 DEFAULT CHARSET=utf8 COMMENT='用户每次提现时的金额记录表';

-- ----------------------------
-- Records of user_account_record
-- ----------------------------
INSERT INTO `user_account_record` VALUES ('360', '1', '80.0000', '2019-04-17 23:30:06');
INSERT INTO `user_account_record` VALUES ('361', '4', '40.0000', '2019-04-18 20:43:42');
INSERT INTO `user_account_record` VALUES ('362', '4', '40.0000', '2019-04-18 20:43:42');
INSERT INTO `user_account_record` VALUES ('363', '4', '40.0000', '2019-04-18 20:43:42');
INSERT INTO `user_account_record` VALUES ('364', '4', '40.0000', '2019-04-18 20:43:42');
INSERT INTO `user_account_record` VALUES ('365', '4', '40.0000', '2019-04-18 20:43:42');
INSERT INTO `user_account_record` VALUES ('366', '4', '40.0000', '2019-04-18 20:43:42');
INSERT INTO `user_account_record` VALUES ('367', '4', '40.0000', '2019-04-18 20:43:42');
INSERT INTO `user_account_record` VALUES ('368', '4', '40.0000', '2019-04-18 20:43:42');
INSERT INTO `user_account_record` VALUES ('369', '4', '40.0000', '2019-04-18 20:43:42');
INSERT INTO `user_account_record` VALUES ('370', '4', '40.0000', '2019-04-18 20:43:42');
INSERT INTO `user_account_record` VALUES ('371', '4', '40.0000', '2019-04-18 20:43:42');
INSERT INTO `user_account_record` VALUES ('372', '4', '40.0000', '2019-04-18 20:43:42');
INSERT INTO `user_account_record` VALUES ('373', '4', '40.0000', '2019-04-18 20:43:42');
INSERT INTO `user_account_record` VALUES ('374', '4', '40.0000', '2019-04-18 20:43:42');
INSERT INTO `user_account_record` VALUES ('375', '4', '40.0000', '2019-04-18 20:43:42');
INSERT INTO `user_account_record` VALUES ('376', '4', '40.0000', '2019-04-18 20:43:42');
INSERT INTO `user_account_record` VALUES ('377', '4', '40.0000', '2019-04-18 20:43:42');
INSERT INTO `user_account_record` VALUES ('378', '4', '40.0000', '2019-04-18 20:43:42');
INSERT INTO `user_account_record` VALUES ('379', '4', '40.0000', '2019-04-18 20:43:42');
INSERT INTO `user_account_record` VALUES ('380', '4', '40.0000', '2019-04-18 20:43:42');
INSERT INTO `user_account_record` VALUES ('381', '4', '40.0000', '2019-04-18 20:43:42');
INSERT INTO `user_account_record` VALUES ('382', '4', '40.0000', '2019-04-18 20:43:42');
INSERT INTO `user_account_record` VALUES ('383', '4', '40.0000', '2019-04-18 20:43:42');
INSERT INTO `user_account_record` VALUES ('384', '4', '40.0000', '2019-04-18 20:43:42');
INSERT INTO `user_account_record` VALUES ('385', '4', '40.0000', '2019-04-18 20:43:42');
INSERT INTO `user_account_record` VALUES ('386', '4', '40.0000', '2019-04-18 20:43:42');
INSERT INTO `user_account_record` VALUES ('387', '4', '40.0000', '2019-04-18 20:43:42');
INSERT INTO `user_account_record` VALUES ('388', '4', '40.0000', '2019-04-18 20:43:42');
INSERT INTO `user_account_record` VALUES ('389', '4', '40.0000', '2019-04-18 20:43:42');
INSERT INTO `user_account_record` VALUES ('390', '4', '40.0000', '2019-04-18 20:43:42');
INSERT INTO `user_account_record` VALUES ('391', '4', '40.0000', '2019-04-18 20:43:42');
INSERT INTO `user_account_record` VALUES ('392', '4', '40.0000', '2019-04-18 20:43:42');
INSERT INTO `user_account_record` VALUES ('393', '4', '40.0000', '2019-04-18 20:43:42');
INSERT INTO `user_account_record` VALUES ('394', '4', '40.0000', '2019-04-18 20:43:42');
INSERT INTO `user_account_record` VALUES ('395', '4', '40.0000', '2019-04-18 20:43:42');
INSERT INTO `user_account_record` VALUES ('396', '4', '40.0000', '2019-04-18 20:43:42');
INSERT INTO `user_account_record` VALUES ('397', '4', '40.0000', '2019-04-18 20:43:42');
INSERT INTO `user_account_record` VALUES ('398', '4', '40.0000', '2019-04-18 20:43:42');
INSERT INTO `user_account_record` VALUES ('399', '4', '40.0000', '2019-04-18 20:43:42');
INSERT INTO `user_account_record` VALUES ('400', '4', '40.0000', '2019-04-18 20:43:42');
INSERT INTO `user_account_record` VALUES ('401', '4', '40.0000', '2019-04-18 20:43:42');
INSERT INTO `user_account_record` VALUES ('402', '4', '40.0000', '2019-04-18 20:43:42');
INSERT INTO `user_account_record` VALUES ('403', '4', '40.0000', '2019-04-18 20:43:42');
INSERT INTO `user_account_record` VALUES ('404', '4', '40.0000', '2019-04-18 20:43:42');
INSERT INTO `user_account_record` VALUES ('405', '4', '40.0000', '2019-04-18 20:43:42');
INSERT INTO `user_account_record` VALUES ('406', '4', '40.0000', '2019-04-18 20:43:42');
INSERT INTO `user_account_record` VALUES ('407', '4', '40.0000', '2019-04-18 20:43:42');
INSERT INTO `user_account_record` VALUES ('408', '4', '40.0000', '2019-04-18 20:43:42');
INSERT INTO `user_account_record` VALUES ('409', '4', '40.0000', '2019-04-18 20:43:42');
INSERT INTO `user_account_record` VALUES ('410', '5', '40.0000', '2019-04-18 21:13:00');
INSERT INTO `user_account_record` VALUES ('411', '5', '40.0000', '2019-04-18 21:13:00');
INSERT INTO `user_account_record` VALUES ('420', '6', '40.0000', '2019-04-19 21:55:56');
INSERT INTO `user_account_record` VALUES ('421', '6', '40.0000', '2019-04-19 21:55:56');

-- ----------------------------
-- Table structure for user_order
-- ----------------------------
DROP TABLE IF EXISTS `user_order`;
CREATE TABLE `user_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_no` varchar(255) NOT NULL COMMENT '订单编号',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `status` int(11) DEFAULT NULL COMMENT '支付状态(1=已保存；2=已付款；3=已取消)',
  `is_active` int(255) DEFAULT '1' COMMENT '是否有效（1=有效；0=失效）',
  `create_time` datetime DEFAULT NULL COMMENT '下单时间',
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='用户下单记录表';

-- ----------------------------
-- Records of user_order
-- ----------------------------
INSERT INTO `user_order` VALUES ('5', '20190411001', '10010', '1', '1', '2019-04-12 22:48:43', null);
INSERT INTO `user_order` VALUES ('6', '20190412001', '10011', '1', '0', '2019-04-12 23:47:54', '2019-04-12 23:48:04');
INSERT INTO `user_order` VALUES ('7', '20190412002', '10012', '1', '0', '2019-04-13 10:28:33', '2019-04-13 10:28:43');

-- ----------------------------
-- Table structure for user_reg
-- ----------------------------
DROP TABLE IF EXISTS `user_reg`;
CREATE TABLE `user_reg` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) NOT NULL COMMENT '用户名',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=78 DEFAULT CHARSET=utf8 COMMENT='用户注册信息表';

-- ----------------------------
-- Records of user_reg
-- ----------------------------
INSERT INTO `user_reg` VALUES ('53', 'linsen', '123456', '2019-04-20 23:01:08');
INSERT INTO `user_reg` VALUES ('54', 'debug', '123456', '2019-04-20 23:36:42');
INSERT INTO `user_reg` VALUES ('55', 'debug', '123456', '2019-04-20 23:36:42');
INSERT INTO `user_reg` VALUES ('56', 'jack', '123456', '2019-04-20 23:36:42');
INSERT INTO `user_reg` VALUES ('57', 'sam', '123456', '2019-04-20 23:36:42');
INSERT INTO `user_reg` VALUES ('58', 'jack', '123456', '2019-04-20 23:36:42');
INSERT INTO `user_reg` VALUES ('59', 'jack', '123456', '2019-04-20 23:36:42');
INSERT INTO `user_reg` VALUES ('60', 'sam', '123456', '2019-04-20 23:36:42');
INSERT INTO `user_reg` VALUES ('61', 'sam', '123456', '2019-04-20 23:36:42');
INSERT INTO `user_reg` VALUES ('62', 'database', '123456', '2019-04-20 23:59:41');
INSERT INTO `user_reg` VALUES ('63', 'rabbitmq', '123456', '2019-04-20 23:59:41');
INSERT INTO `user_reg` VALUES ('64', 'lock', '123456', '2019-04-20 23:59:41');
INSERT INTO `user_reg` VALUES ('65', 'java', '123456', '2019-04-20 23:59:41');
INSERT INTO `user_reg` VALUES ('66', 'redis', '123456', '2019-04-20 23:59:41');
INSERT INTO `user_reg` VALUES ('71', 'luohou', '123456', '2019-04-21 21:51:05');
INSERT INTO `user_reg` VALUES ('72', 'lixiaolong', '123456', '2019-04-21 21:51:05');
INSERT INTO `user_reg` VALUES ('73', 'zhongwenjie', '123456', '2019-04-21 21:51:05');
INSERT INTO `user_reg` VALUES ('74', 'userC', '123456', '2019-05-03 15:37:37');
INSERT INTO `user_reg` VALUES ('75', 'userD', '123456', '2019-05-03 15:37:37');
INSERT INTO `user_reg` VALUES ('76', 'userA', '123456', '2019-05-03 15:37:37');
INSERT INTO `user_reg` VALUES ('77', 'userB', '123456', '2019-05-03 15:37:37');
