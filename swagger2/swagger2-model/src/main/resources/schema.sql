-- ----------------------------
-- Table structure for UserInfo
-- ----------------------------

DROP TABLE IF EXISTS `UserInfo`;
-- 创建一个表，指定了4个属性：id、年龄、身高、体重。最后指定了id是唯一不能重复的键值
CREATE TABLE IF NOT EXISTS `UserInfo` (  `id` varchar(20) NOT NULL,  `age` int DEFAULT NULL,  `height` int DEFAULT NULL,  `weight` int DEFAULT NULL,  PRIMARY KEY (`id`) );

