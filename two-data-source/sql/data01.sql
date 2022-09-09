/*
 Navicat Premium Data Transfer

 Source Server         : 172.16.104.131-山洪预警
 Source Server Type    : PostgreSQL
 Source Server Version : 140005
 Source Host           : 172.16.104.131:31302
 Source Catalog        : code_system
 Source Schema         : public

 Target Server Type    : PostgreSQL
 Target Server Version : 140005
 File Encoding         : 65001

 Date: 09/09/2022 10:25:41
*/


-- ----------------------------
-- Table structure for data01
-- ----------------------------
DROP TABLE IF EXISTS "public"."data01";
CREATE TABLE "public"."data01" (
  "id" int4 NOT NULL,
  "a1" varchar(255) COLLATE "pg_catalog"."default",
  "b1" varchar(255) COLLATE "pg_catalog"."default",
  "c1" varchar(255) COLLATE "pg_catalog"."default",
  "d1" varchar(255) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Records of data01
-- ----------------------------
INSERT INTO "public"."data01" VALUES (1, '2', '3', '4', '5');

-- ----------------------------
-- Primary Key structure for table data01
-- ----------------------------
ALTER TABLE "public"."data01" ADD CONSTRAINT "user_copy2_pkey" PRIMARY KEY ("id");
