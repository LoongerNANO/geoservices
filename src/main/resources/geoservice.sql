/*
 Navicat Premium Data Transfer

 Source Server         : 124.205.245.99（工程管理）
 Source Server Type    : PostgreSQL
 Source Server Version : 100013
 Source Host           : 124.205.245.99:5432
 Source Catalog        : LYGCGLDB
 Source Schema         : public

 Target Server Type    : PostgreSQL
 Target Server Version : 100013
 File Encoding         : 65001

 Date: 18/08/2020 10:26:48
*/


-- ----------------------------
-- Table structure for t_sys_dictionary
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_sys_dictionary";
CREATE TABLE "public"."t_sys_dictionary" (
  "id" text COLLATE "pg_catalog"."default" NOT NULL,
  "identify" text COLLATE "pg_catalog"."default",
  "code" text COLLATE "pg_catalog"."default",
  "name" text COLLATE "pg_catalog"."default",
  "seq" int2,
  "remarks" text COLLATE "pg_catalog"."default",
  "creater" text COLLATE "pg_catalog"."default",
  "created" timestamp(0) DEFAULT CURRENT_TIMESTAMP,
  "updater" text COLLATE "pg_catalog"."default",
  "updated" timestamp(0)
)
;
COMMENT ON COLUMN "public"."t_sys_dictionary"."id" IS '字典唯一编码';
COMMENT ON COLUMN "public"."t_sys_dictionary"."identify" IS '字典类型编码（字典类型表）';
COMMENT ON COLUMN "public"."t_sys_dictionary"."code" IS '字典编码（同一类型是唯一）';
COMMENT ON COLUMN "public"."t_sys_dictionary"."name" IS '字典名称';
COMMENT ON COLUMN "public"."t_sys_dictionary"."seq" IS '字典排序（同一类型排序）';
COMMENT ON COLUMN "public"."t_sys_dictionary"."remarks" IS '字典备注';
COMMENT ON COLUMN "public"."t_sys_dictionary"."creater" IS '创建人';
COMMENT ON COLUMN "public"."t_sys_dictionary"."created" IS '创建日期';
COMMENT ON COLUMN "public"."t_sys_dictionary"."updater" IS '修改人';
COMMENT ON COLUMN "public"."t_sys_dictionary"."updated" IS '修改日期';
COMMENT ON TABLE "public"."t_sys_dictionary" IS '系统字典表';

-- ----------------------------
-- Records of t_sys_dictionary
-- ----------------------------
INSERT INTO "public"."t_sys_dictionary" VALUES ('0cfa97ac99b611eabbd302fcdc4e7412', 'sys_dic_log_type', 'WARN', '警告日志', 1, NULL, NULL, '2020-08-17 16:39:56', NULL, '2020-08-17 16:40:06');
INSERT INTO "public"."t_sys_dictionary" VALUES ('0cfa98f699b611eabbd302fcdc4e7412', 'sys_dic_log_type', 'ERROR', '错误日志', 2, NULL, NULL, '2020-08-17 16:39:59', NULL, '2020-08-17 16:40:08');
INSERT INTO "public"."t_sys_dictionary" VALUES ('0cfa95c299b611eabbd302fcdc4e7412', 'sys_dic_log_type', 'INFO', '信息日志', 0, NULL, NULL, '2020-08-17 16:40:02', NULL, '2020-08-17 16:40:11');

-- ----------------------------
-- Table structure for t_sys_dictionary_content
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_sys_dictionary_content";
CREATE TABLE "public"."t_sys_dictionary_content" (
  "id" text COLLATE "pg_catalog"."default" NOT NULL,
  "identify" text COLLATE "pg_catalog"."default",
  "name" text COLLATE "pg_catalog"."default",
  "remarks" text COLLATE "pg_catalog"."default",
  "status" text COLLATE "pg_catalog"."default",
  "creater" text COLLATE "pg_catalog"."default",
  "created" timestamp(0) DEFAULT CURRENT_TIMESTAMP,
  "updater" text COLLATE "pg_catalog"."default",
  "updated" timestamp(0)
)
;
COMMENT ON COLUMN "public"."t_sys_dictionary_content"."id" IS '字典类型唯一编码';
COMMENT ON COLUMN "public"."t_sys_dictionary_content"."identify" IS '字典类型（字典类型表）';
COMMENT ON COLUMN "public"."t_sys_dictionary_content"."name" IS '字典名称';
COMMENT ON COLUMN "public"."t_sys_dictionary_content"."remarks" IS '字典备注';
COMMENT ON COLUMN "public"."t_sys_dictionary_content"."status" IS '字典类型状态 OPEN OR LOCK';
COMMENT ON COLUMN "public"."t_sys_dictionary_content"."creater" IS '创建人';
COMMENT ON COLUMN "public"."t_sys_dictionary_content"."created" IS '创建日期';
COMMENT ON COLUMN "public"."t_sys_dictionary_content"."updater" IS '修改人';
COMMENT ON COLUMN "public"."t_sys_dictionary_content"."updated" IS '修改日期';
COMMENT ON TABLE "public"."t_sys_dictionary_content" IS '系统字典类型表';

-- ----------------------------
-- Records of t_sys_dictionary_content
-- ----------------------------
INSERT INTO "public"."t_sys_dictionary_content" VALUES ('df10a20a99b011ea9afe02fcdc4e7412', 'sys_dic_log_type', '系统日志类型', NULL, '0', NULL, '2020-06-02 15:33:35', NULL, '2020-06-02 15:34:11');

-- ----------------------------
-- Table structure for t_sys_log
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_sys_log";
CREATE TABLE "public"."t_sys_log" (
  "id" text COLLATE "pg_catalog"."default" NOT NULL,
  "username" text COLLATE "pg_catalog"."default",
  "fullname" text COLLATE "pg_catalog"."default",
  "url" text COLLATE "pg_catalog"."default",
  "method" text COLLATE "pg_catalog"."default",
  "ip" text COLLATE "pg_catalog"."default",
  "functions" text COLLATE "pg_catalog"."default",
  "parameter" text COLLATE "pg_catalog"."default",
  "operation" text COLLATE "pg_catalog"."default",
  "time" text COLLATE "pg_catalog"."default",
  "ask" timestamp(0),
  "sys_dic_log_type" text COLLATE "pg_catalog"."default",
  "error" text COLLATE "pg_catalog"."default",
  "browser" text COLLATE "pg_catalog"."default",
  "version" text COLLATE "pg_catalog"."default",
  "os" text COLLATE "pg_catalog"."default"
)
;
COMMENT ON COLUMN "public"."t_sys_log"."id" IS '日志唯一标识';
COMMENT ON COLUMN "public"."t_sys_log"."username" IS '请求人账号';
COMMENT ON COLUMN "public"."t_sys_log"."fullname" IS '请求人姓名';
COMMENT ON COLUMN "public"."t_sys_log"."url" IS '请求地址';
COMMENT ON COLUMN "public"."t_sys_log"."method" IS '请求方式';
COMMENT ON COLUMN "public"."t_sys_log"."ip" IS '客户端IP';
COMMENT ON COLUMN "public"."t_sys_log"."functions" IS '响应类函数';
COMMENT ON COLUMN "public"."t_sys_log"."parameter" IS '请求参数';
COMMENT ON COLUMN "public"."t_sys_log"."operation" IS '操作内容描述';
COMMENT ON COLUMN "public"."t_sys_log"."time" IS '响应时间';
COMMENT ON COLUMN "public"."t_sys_log"."ask" IS '请求时间';
COMMENT ON COLUMN "public"."t_sys_log"."sys_dic_log_type" IS '日志类型';
COMMENT ON COLUMN "public"."t_sys_log"."error" IS '错误描述';
COMMENT ON COLUMN "public"."t_sys_log"."browser" IS '客户端浏览器名称';
COMMENT ON COLUMN "public"."t_sys_log"."version" IS '客户端浏览器版本';
COMMENT ON COLUMN "public"."t_sys_log"."os" IS '客户端操作系统';
COMMENT ON TABLE "public"."t_sys_log" IS '系统日志表';

-- ----------------------------
-- Records of t_sys_log
-- ----------------------------
INSERT INTO "public"."t_sys_log" VALUES ('70a72e5c3f8949c08a51b08d46f278a4', '', '', 'http://localhost:8081/os/api/example/arguments/post/1', 'POST', '0:0:0:0:0:0:0:1', 'com.esrichina.geoservices.controller.ExampleController.argumentsPostController', '["1",{"keyword":"string","username":"string"}]', '有参POST请求示例', '39 ms', '2020-08-17 16:35:39', 'INFO', NULL, 'Chrome 8', '83.0.4103.116', 'Windows 10');
INSERT INTO "public"."t_sys_log" VALUES ('09b53874552a422da751a21317c98336', '', '', 'http://localhost:8081/os/api/example/samples/logs/page', 'GET', '0:0:0:0:0:0:0:1', 'com.esrichina.geoservices.controller.ExampleController.samplesLogPageController', '[{"current":"1","pages":"10"}]', '日志分页示例', '95 ms', '2020-08-17 16:41:22', 'INFO', NULL, 'Chrome 8', '83.0.4103.116', 'Windows 10');
INSERT INTO "public"."t_sys_log" VALUES ('9f10fe1a93ae4ca88a18c97561b8c268', '', '', 'http://localhost:8081/os/api/example/samples/logs/page', 'GET', '0:0:0:0:0:0:0:1', 'com.esrichina.geoservices.controller.ExampleController.samplesLogPageController', '[{"current":"1","pages":"10"}]', '日志分页示例', '25 ms', '2020-08-17 16:42:29', 'INFO', NULL, 'Chrome 8', '83.0.4103.116', 'Windows 10');
INSERT INTO "public"."t_sys_log" VALUES ('86222e6bdb9142289d668927b97f81c6', '', '', 'http://localhost:8081/os/api/example/samples/logs/page', 'GET', '0:0:0:0:0:0:0:1', 'com.esrichina.geoservices.controller.ExampleController.samplesLogPageController', '[{"current":"1","pages":"10"}]', '日志分页示例', '167 ms', '2020-08-17 16:44:07', 'INFO', NULL, 'Chrome 8', '83.0.4103.116', 'Windows 10');

-- ----------------------------
-- Primary Key structure for table t_sys_dictionary
-- ----------------------------
ALTER TABLE "public"."t_sys_dictionary" ADD CONSTRAINT "pk_t_sys_dictionary" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table t_sys_dictionary_content
-- ----------------------------
ALTER TABLE "public"."t_sys_dictionary_content" ADD CONSTRAINT "pk_t_sys_dictionary_type" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table t_sys_log
-- ----------------------------
ALTER TABLE "public"."t_sys_log" ADD CONSTRAINT "pk_t_sys_log" PRIMARY KEY ("id");
