-- 新建数据库：格式
CREATE DATABASE database_name 
SET type;
-- 新建数据库book
CREATE DATABASE book CHARACTER 
SET utf8 COLLATE utf8_general_ci;
-- 新建数据表user_information
DROP TABLE IF EXISTS `user_information`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_information` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(50) DEFAULT NULL COMMENT '用户姓名',
  `sex` varchar(10) DEFAULT NULL COMMENT '性别',
  `address` varchar(255) DEFAULT NULL COMMENT '用户住址',
  `phone_number` varchar(11) DEFAULT NULL COMMENT '手机号',
  `password` varchar(255) DEFAULT NULL COMMENT '用户密码，加密',
  `is_delete` tinyint(1) DEFAULT '0' COMMENT '删除标志位',
  `created_time` datetime DEFAULT NULL COMMENT '创建数据时间',
  `updated_time` datetime DEFAULT NULL COMMENT '更新数据时间',
  `user_id` varchar(255) DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 添加数据
INSERT INTO book.user_information VALUES (1,'admin','男','黑龙江','1234567890','$2a$10$UM92AGdARDFp/5bQ2X31Geeksy9W8doqvVGhkStkqILtvKVy9Ya6m',0,'2021-02-18 23:40:46',NULL,'123456');
-- 新建普通索引
-- CREATE INDEX index_username ON user_information ( username ) COMMENT '用户名普通索引';
-- ALTER TABLE user_information ADD INDEX index_username ( username ) COMMENT '用户名普通索引';
-- 删除普通索引
-- DROP INDEX index_username ON user_information;
-- 查询索引
-- SHOW INDEX 
-- FROM
-- 	user_information;
-- 新建唯一索引
-- CREATE UNIQUE INDEX index_phone_number ON user_information ( phone_number ) COMMENT '手机号唯一索引';
-- ALTER TABLE user_information ADD UNIQUE INDEX index_phone_number ( phone_number ) COMMENT '手机号唯一索引';
-- 新建单列索引
-- CREATE INDEX index_username ON user_information ( username ) COMMENT '用户名单列索引';
-- ALTER TABLE user_information ADD INDEX index_username ( username ) COMMENT '用户名单列索引';
-- 多列索引alter
-- CREATE INDEX index_name_phone_number ON user_information ( username, phone_number ) COMMENT '多列索引：用户名和手机号';
-- ALTER TABLE user_information ADD INDEX index_name_phone_number ( username, phone_number ) COMMENT '多列索引：用户名和手机号';