CREATE TABLE `merchants`
(
    `id`                   int(10) NOT NULL AUTO_INCREMENT,
    `name`                 varchar(64)  DEFAULT NULL COMMENT '商户名称',
    `logo_url`             varchar(256) DEFAULT NULL COMMENT '商户 logo',
    `business_license_url` varchar(256) DEFAULT NULL COMMENT '商户营业执照地址',
    `phone`                varchar(64)  DEFAULT NULL COMMENT '商户联系电话',
    `address`              varchar(64)  DEFAULT NULL COMMENT '商户地址',
    `is_audit`             tinyint(1)   DEFAULT NULL COMMENT '是否通过审核',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;