/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : PostgreSQL
 Source Server Version : 110005
 Source Host           : localhost:5432
 Source Catalog        : postgres
 Source Schema         : public

 Target Server Type    : PostgreSQL
 Target Server Version : 110005
 File Encoding         : 65001

 Date: 17/07/2020 15:44:24
*/


-- ----------------------------
-- Sequence structure for t_current_job_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."t_current_job_id_seq";
CREATE SEQUENCE "public"."t_current_job_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for t_history_job_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."t_history_job_id_seq";
CREATE SEQUENCE "public"."t_history_job_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

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
INSERT INTO "public"."t_sys_dictionary" VALUES ('d23d70aa99b311ea9afe02fcdc4e7412', 'sys_resources_classify', '0', '服务类型', 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO "public"."t_sys_dictionary" VALUES ('d23d721299b311ea9afe02fcdc4e7412', 'sys_resources_classify', '1', '文件类型', 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO "public"."t_sys_dictionary" VALUES ('7712beb299b611ea828802fcdc4e7412', 'sys_user_landed', '0', '已登录', 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO "public"."t_sys_dictionary" VALUES ('7712c02499b611ea828802fcdc4e7412', 'sys_user_landed', '1', '未登录', 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO "public"."t_sys_dictionary" VALUES ('8aa1822a99b511ea828802fcdc4e7412', 'sys_node_state', '0', '启用', 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO "public"."t_sys_dictionary" VALUES ('202cc27a99b411ea828802fcdc4e7412', 'sys_resources_status', '0', '注册', 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO "public"."t_sys_dictionary" VALUES ('202cc3ce99b411ea828802fcdc4e7412', 'sys_resources_status', '1', '发布', 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO "public"."t_sys_dictionary" VALUES ('202cc48299b411ea828802fcdc4e7412', 'sys_resources_status', '2', '删除', 2, NULL, NULL, NULL, NULL, NULL);
INSERT INTO "public"."t_sys_dictionary" VALUES ('a7ed441299b611eab29402fcdc4e7412', 'sys_user_status', '0', '启用', 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO "public"."t_sys_dictionary" VALUES ('f4ebfeda99b011eab29402fcdc4e7412', 'sys_resources_category', '0', '要素服务', 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO "public"."t_sys_dictionary" VALUES ('f4ec004299b011eab29402fcdc4e7412', 'sys_resources_category', '1', '影像服务', 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO "public"."t_sys_dictionary" VALUES ('f4ec010099b011eab29402fcdc4e7412', 'sys_resources_category', '2', 'KML 服务', 2, NULL, NULL, NULL, NULL, NULL);
INSERT INTO "public"."t_sys_dictionary" VALUES ('f4ec01a099b011eab29402fcdc4e7412', 'sys_resources_category', '3', '地图服务', 3, NULL, NULL, NULL, NULL, NULL);
INSERT INTO "public"."t_sys_dictionary" VALUES ('f4ec024099b011eab29402fcdc4e7412', 'sys_resources_category', '4', '场景服务', 4, NULL, NULL, NULL, NULL, NULL);
INSERT INTO "public"."t_sys_dictionary" VALUES ('f4ec02cc99b011eab29402fcdc4e7412', 'sys_resources_category', '5', 'OGC 服务', 5, NULL, NULL, NULL, NULL, NULL);
INSERT INTO "public"."t_sys_dictionary" VALUES ('f4ec036299b011eab29402fcdc4e7412', 'sys_resources_category', '6', '切片服务', 6, NULL, NULL, NULL, NULL, NULL);
INSERT INTO "public"."t_sys_dictionary" VALUES ('a7ed455299b611eab29402fcdc4e7412', 'sys_user_status', '1', '禁用', 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO "public"."t_sys_dictionary" VALUES ('8aa1839299b511ea828802fcdc4e7412', 'sys_node_state', '1', '停用', 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO "public"."t_sys_dictionary" VALUES ('1a2404ba9a5e11eaa57502fcdc4e7412', 'sys_role_status', '0', '可编辑', 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO "public"."t_sys_dictionary" VALUES ('1a2406229a5e11eaa57502fcdc4e7412', 'sys_role_status', '1', '不可编辑', 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO "public"."t_sys_dictionary" VALUES ('0cfa95c299b611fabbd302fcdc4e7412', 'sys_user_gender', '0', '男', 0, NULL, NULL, '2020-06-16 11:54:57', NULL, NULL);
INSERT INTO "public"."t_sys_dictionary" VALUES ('0cfa95c299b611fabgd302fcdc4e7412', 'sys_user_gender', '1', '女', 1, NULL, NULL, '2020-06-16 11:55:05', NULL, NULL);
INSERT INTO "public"."t_sys_dictionary" VALUES ('0cfa97ac99b611eabbd302fcdc4e7412', 'sys_log_type', 'WARN', '警告日志', 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO "public"."t_sys_dictionary" VALUES ('0cfa98f699b611eabbd302fcdc4e7412', 'sys_log_type', 'ERROR', '错误日志', 2, NULL, NULL, NULL, NULL, NULL);
INSERT INTO "public"."t_sys_dictionary" VALUES ('0cfa95c299b611eabbd302fcdc4e7412', 'sys_log_type', 'INFO', '信息日志', 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO "public"."t_sys_dictionary" VALUES ('4a039d5c9a5f11ea9afe02fcdc4e7412', 'sys_permission_type', '0', '是', 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO "public"."t_sys_dictionary" VALUES ('4a039eba9a5f11ea9afe02fcdc4e7412', 'sys_permission_type', '1', '否', 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO "public"."t_sys_dictionary" VALUES ('3a039eba3a5f11ea9afe02fcdc4e7412', 'sys_permission_status', '0', '启用', 0, NULL, NULL, '2020-06-17 17:05:14', NULL, NULL);
INSERT INTO "public"."t_sys_dictionary" VALUES ('3a039eba3a5f11ba9afe02fcdc4e7412', 'sys_permission_status', '1', '禁用', 1, NULL, NULL, '2020-06-17 17:05:42', NULL, NULL);

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
INSERT INTO "public"."t_sys_dictionary_content" VALUES ('df10a9da99b011ea9afe02fcdc4e7412', 'sys_menu_type', '菜单类型', NULL, '0', NULL, '2020-06-02 15:33:46', NULL, '2020-06-02 15:34:23');
INSERT INTO "public"."t_sys_dictionary_content" VALUES ('df109e3699b011ea9afe02fcdc4e7412', 'sys_resources_category', '资源服务类型', NULL, '0', NULL, '2020-06-02 15:33:20', NULL, '2020-06-02 15:33:59');
INSERT INTO "public"."t_sys_dictionary_content" VALUES ('df109f9499b011ea9afe02fcdc4e7412', 'sys_resources_classify', '资源数据类型', NULL, '0', NULL, '2020-06-02 15:33:23', NULL, '2020-06-02 15:34:02');
INSERT INTO "public"."t_sys_dictionary_content" VALUES ('df10a04899b011ea9afe02fcdc4e7412', 'sys_resources_status', '资源数据状态', NULL, '0', NULL, '2020-06-02 15:33:26', NULL, '2020-06-02 15:34:04');
INSERT INTO "public"."t_sys_dictionary_content" VALUES ('df10a0e899b011ea9afe02fcdc4e7412', 'sys_node_type', '节点目录类型', NULL, '0', NULL, '2020-06-02 15:33:30', NULL, '2020-06-02 15:34:07');
INSERT INTO "public"."t_sys_dictionary_content" VALUES ('df10a17499b011ea9afe02fcdc4e7412', 'sys_node_state', '节点目录状态', NULL, '0', NULL, '2020-06-02 15:33:33', NULL, '2020-06-02 15:34:09');
INSERT INTO "public"."t_sys_dictionary_content" VALUES ('df10a20a99b011ea9afe02fcdc4e7412', 'sys_log_type', '系统日志类型', NULL, '0', NULL, '2020-06-02 15:33:35', NULL, '2020-06-02 15:34:11');
INSERT INTO "public"."t_sys_dictionary_content" VALUES ('df10a29699b011ea9afe02fcdc4e7412', 'sys_user_landed', '用户登录状态', NULL, '0', NULL, '2020-06-02 15:33:39', NULL, '2020-06-02 15:34:13');
INSERT INTO "public"."t_sys_dictionary_content" VALUES ('df10a32299b011ea9afe02fcdc4e7412', 'sys_user_status', '用户状态', NULL, '0', NULL, '2020-06-02 15:33:41', NULL, '2020-06-02 15:34:17');
INSERT INTO "public"."t_sys_dictionary_content" VALUES ('df10a3ae99b011ea9afe02fcdc4e7412', 'sys_role_status', '角色状态', NULL, '0', NULL, '2020-06-02 15:33:44', NULL, '2020-06-02 15:34:20');

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
  "log_type" text COLLATE "pg_catalog"."default",
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
COMMENT ON COLUMN "public"."t_sys_log"."log_type" IS '日志类型';
COMMENT ON COLUMN "public"."t_sys_log"."error" IS '错误描述';
COMMENT ON COLUMN "public"."t_sys_log"."browser" IS '客户端浏览器名称';
COMMENT ON COLUMN "public"."t_sys_log"."version" IS '客户端浏览器版本';
COMMENT ON COLUMN "public"."t_sys_log"."os" IS '客户端操作系统';
COMMENT ON TABLE "public"."t_sys_log" IS '系统日志表';

-- ----------------------------
-- Records of t_sys_log
-- ----------------------------
INSERT INTO "public"."t_sys_log" VALUES ('ba15d50b5020443ba4566ece5d17bedb', '', '', 'http://localhost:8081/os/api/example/arguments/get/chain/validate/1', 'GET', '0:0:0:0:0:0:0:1', 'com.esrichina.geoservices.controller.ExampleController.argumentsChainValidateGetController', '["1",{"keyword":"21","username":"21"},{"messageCodesResolver":{"prefix":""},"suppressedFields":[],"target":{"keyword":"21","username":"21"},"nestedPath":"","objectName":"argumentsChainValidateParameter"}]', '路径对象带参GET请求示例', '35 ms', '2020-07-17 14:13:01', 'ERROR', 'com.esrichina.geoservices.exception.ParameterException: [{"msg":"生日不能为空","filed":"birthday"}]', 'Chrome 8', '83.0.4103.116', 'Windows 10');
INSERT INTO "public"."t_sys_log" VALUES ('1b6fd31b95bb466493817d238bd883a7', '', '', 'http://localhost:8081/os/api/example/arguments/post/validate/111111', 'POST', '0:0:0:0:0:0:0:1', 'com.esrichina.geoservices.controller.ExampleController.argumentsValidatePostController', '["111111",{"birthday":"string","keyword":"string","email":"string","username":"string"},{"messageCodesResolver":{"prefix":""},"suppressedFields":[],"target":{"birthday":"string","keyword":"string","email":"string","username":"string"},"nestedPath":"","objectName":"argumentsValidateParameter"}]', '有参带校验POST请求示例', '33 ms', '2020-07-17 14:34:05', 'ERROR', 'com.esrichina.geoservices.exception.ParameterException: [{"msg":"电子邮件格式不正确","filed":"email"}]', 'Chrome 8', '83.0.4103.116', 'Windows 10');
INSERT INTO "public"."t_sys_log" VALUES ('77868762033c46f6a382c9e8155b4f52', '', '', 'http://localhost:8081/os/api/example/arguments/post/validate/111111', 'POST', '0:0:0:0:0:0:0:1', 'com.esrichina.geoservices.controller.ExampleController.argumentsValidatePostController', '["111111",{"birthday":"","keyword":"string","email":"string","username":"string"},{"messageCodesResolver":{"prefix":""},"suppressedFields":[],"target":{"birthday":"","keyword":"string","email":"string","username":"string"},"nestedPath":"","objectName":"argumentsValidateParameter"}]', '有参带校验POST请求示例', '3 ms', '2020-07-17 14:34:21', 'ERROR', 'com.esrichina.geoservices.exception.ParameterException: [{"msg":"电子邮件格式不正确","filed":"email"},{"msg":"生日不能为空","filed":"birthday"}]', 'Chrome 8', '83.0.4103.116', 'Windows 10');
INSERT INTO "public"."t_sys_log" VALUES ('513415f5a8df4197b7aa9c5186c49171', '', '', 'http://localhost:8081/os/api/example/arguments/post/validate/dictionary/1', 'POST', '0:0:0:0:0:0:0:1', 'com.esrichina.geoservices.controller.ExampleController.argumentsValidateDictionaryPostController', '["1",{"keyword":"1","username":"string","status":"string"},{"messageCodesResolver":{"prefix":""},"suppressedFields":[],"target":{"keyword":"1","username":"string","status":"string"},"nestedPath":"","objectName":"argumentsDictionaryParameter"}]', '非法字典POST请求示例', '23 ms', '2020-07-17 15:14:30', 'INFO', NULL, 'Chrome 8', '83.0.4103.116', 'Windows 10');
INSERT INTO "public"."t_sys_log" VALUES ('d0833d8e20e34906b6867379a027af2a', '', '', 'http://localhost:8081/os/api/example/arguments/post/validate/dictionary/1', 'POST', '0:0:0:0:0:0:0:1', 'com.esrichina.geoservices.controller.ExampleController.argumentsValidateDictionaryPostController', '["1",{"keyword":"1","username":"string","status":"string"},{"messageCodesResolver":{"prefix":""},"suppressedFields":[],"target":{"keyword":"1","username":"string","status":"string"},"nestedPath":"","objectName":"argumentsDictionaryParameter"}]', '非法字典POST请求示例', '24 ms', '2020-07-17 15:21:39', 'INFO', NULL, 'Chrome 8', '83.0.4103.116', 'Windows 10');
INSERT INTO "public"."t_sys_log" VALUES ('a9fb10e3def84b72a9a6d4ebbc664039', '', '', 'http://localhost:8081/os/api/example/arguments/post/validate/dictionary/1', 'POST', '0:0:0:0:0:0:0:1', 'com.esrichina.geoservices.controller.ExampleController.argumentsValidateDictionaryPostController', '["1",{"keyword":"ERROR","username":"string","status":"string"},{"messageCodesResolver":{"prefix":""},"suppressedFields":[],"target":{"keyword":"ERROR","username":"string","status":"string"},"nestedPath":"","objectName":"argumentsDictionaryParameter"}]', '非法字典POST请求示例', '0 ms', '2020-07-17 15:21:48', 'INFO', NULL, 'Chrome 8', '83.0.4103.116', 'Windows 10');
INSERT INTO "public"."t_sys_log" VALUES ('5c0fd9c0de3b4f4aa346bab9993d4a85', '', '', 'http://localhost:8081/os/api/example/arguments/post/validate/dictionary/1', 'POST', '0:0:0:0:0:0:0:1', 'com.esrichina.geoservices.controller.ExampleController.argumentsValidateDictionaryPostController', '["1",{"keyword":"ERROR","username":"string","status":"string"},{"messageCodesResolver":{"prefix":""},"suppressedFields":[],"target":{"keyword":"ERROR","username":"string","status":"string"},"nestedPath":"","objectName":"argumentsDictionaryParameter"}]', '非法字典POST请求示例', '23 ms', '2020-07-17 15:23:01', 'INFO', NULL, 'Chrome 8', '83.0.4103.116', 'Windows 10');
INSERT INTO "public"."t_sys_log" VALUES ('d8ef3b9c26d4410aaab066b5f2cfcf1c', '', '', 'http://localhost:8081/os/api/example/arguments/post/validate/dictionary/1', 'POST', '0:0:0:0:0:0:0:1', 'com.esrichina.geoservices.controller.ExampleController.argumentsValidateDictionaryPostController', '["1",{"keyword":"ERROR","username":"string","status":"string"},{"messageCodesResolver":{"prefix":""},"suppressedFields":[],"target":{"keyword":"ERROR","username":"string","status":"string"},"nestedPath":"","objectName":"argumentsDictionaryParameter"}]', '非法字典POST请求示例', '24 ms', '2020-07-17 15:24:43', 'INFO', NULL, 'Chrome 8', '83.0.4103.116', 'Windows 10');
INSERT INTO "public"."t_sys_log" VALUES ('418a30bf02d940a8b34ea39a51fe918b', '', '', 'http://localhost:8081/os/api/example/arguments/post/validate/dictionary/1', 'POST', '0:0:0:0:0:0:0:1', 'com.esrichina.geoservices.controller.ExampleController.argumentsValidateDictionaryPostController', '["1",{"keyword":"ERROR","username":"string","status":"ERROR"},{"messageCodesResolver":{"prefix":""},"suppressedFields":[],"target":{"keyword":"ERROR","username":"string","status":"ERROR"},"nestedPath":"","objectName":"argumentsDictionaryParameter"}]', '非法字典POST请求示例', '24 ms', '2020-07-17 15:26:25', 'INFO', NULL, 'Chrome 8', '83.0.4103.116', 'Windows 10');
INSERT INTO "public"."t_sys_log" VALUES ('5262ebeb678f4df6ba624dfe31f68edd', '', '', 'http://localhost:8081/os/api/example/arguments/post/validate/dictionary/1', 'POST', '0:0:0:0:0:0:0:1', 'com.esrichina.geoservices.controller.ExampleController.argumentsValidateDictionaryPostController', '["1",{"keyword":"ERROR","username":"string","status":"1"},{"messageCodesResolver":{"prefix":""},"suppressedFields":[],"target":{"keyword":"ERROR","username":"string","status":"1"},"nestedPath":"","objectName":"argumentsDictionaryParameter"}]', '非法字典POST请求示例', '0 ms', '2020-07-17 15:26:37', 'INFO', NULL, 'Chrome 8', '83.0.4103.116', 'Windows 10');
INSERT INTO "public"."t_sys_log" VALUES ('8628fd46529943d99051117c21de6f9b', '', '', 'http://localhost:8081/os/api/example/arguments/post/validate/dictionary/1', 'POST', '0:0:0:0:0:0:0:1', 'com.esrichina.geoservices.controller.ExampleController.argumentsValidateDictionaryPostController', '["1",{"keyword":"ERROR","username":"string","status":"1"},{"messageCodesResolver":{"prefix":""},"suppressedFields":[],"target":{"keyword":"ERROR","username":"string","status":"1"},"nestedPath":"","objectName":"argumentsDictionaryParameter"}]', '非法字典POST请求示例', '20 ms', '2020-07-17 15:27:54', 'ERROR', 'com.esrichina.geoservices.exception.ParameterException: [{"msg":"非法参数","filed":"status"}]', 'Chrome 8', '83.0.4103.116', 'Windows 10');
INSERT INTO "public"."t_sys_log" VALUES ('b071b4f2c125477d82429e6b3b1dc225', '', '', 'http://localhost:8081/os/api/example/samples/logs/page', 'GET', '0:0:0:0:0:0:0:1', 'com.esrichina.geoservices.controller.ExampleController.samplesLogPageController', '[{"current":"1","pages":"10"}]', '日志分页示例', '164 ms', '2020-07-17 15:35:55', 'INFO', NULL, 'Chrome 8', '83.0.4103.116', 'Windows 10');
INSERT INTO "public"."t_sys_log" VALUES ('cfb743088d1747c8b1b1a8107b4d286d', '', '', 'http://localhost:8081/os/api/example/samples/logs/page', 'GET', '0:0:0:0:0:0:0:1', 'com.esrichina.geoservices.controller.ExampleController.samplesLogPageController', '[{"current":"1","pages":"10"}]', '日志分页示例', '172 ms', '2020-07-17 15:43:23', 'INFO', NULL, 'Chrome 8', '83.0.4103.116', 'Windows 10');

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
SELECT setval('"public"."t_current_job_id_seq"', 19, true);
SELECT setval('"public"."t_history_job_id_seq"', 19, true);

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
