/*
 Navicat MySQL Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : localhost:3306
 Source Schema         : yidian

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 24/05/2022 10:47:28
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for form
-- ----------------------------
DROP TABLE IF EXISTS `form`;
CREATE TABLE `form`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人Id',
  `is_end` int(1) NULL DEFAULT 0 COMMENT '是否结束，0是没结束 1是结束',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '表单标题',
  `label_combination` json NULL COMMENT '交文本标签组合方式',
  `label_combination_img` json NULL COMMENT '交图片的标签组合',
  `healthy_code` int(1) NULL DEFAULT NULL COMMENT '是否要健康码',
  `travel_card` int(1) NULL DEFAULT NULL COMMENT '是否要行程卡',
  `organization_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '选择群组id',
  `begin_time` datetime NULL DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime NULL DEFAULT NULL COMMENT '结束时间',
  `attention_mode` int(11) NULL DEFAULT NULL COMMENT '提醒方式，枚举',
  `acid_requirement` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '核酸要求',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '表单发布' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of form
-- ----------------------------
INSERT INTO `form` VALUES ('1527273297393188866', '2022-05-19 21:01:44', '2022-05-19 21:01:44', '1', 0, '你必绿码', '{\"title1\": \"姓名\", \"title2\": \"身份证号\"}', '{\"title3\": \"死亡证明\"}', 1, 1, '6', '2022-05-19 20:35:34', '2022-05-19 20:35:34', 0, NULL);
INSERT INTO `form` VALUES ('1527274078825578498', '2022-05-19 21:04:50', '2022-05-19 21:04:50', '1', 0, '你没事吧', '{\"title1\": \"姓名\", \"title2\": \"身份证号\"}', '{}', 1, 1, '6', '2022-05-19 20:35:34', '2022-05-19 20:35:34', 0, NULL);

-- ----------------------------
-- Table structure for form_count
-- ----------------------------
DROP TABLE IF EXISTS `form_count`;
CREATE TABLE `form_count`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `form_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '表单id',
  `undone` int(11) NULL DEFAULT 0 COMMENT '未完成的数量',
  `done` int(11) NULL DEFAULT 0 COMMENT '完成的数量',
  `abnormal` int(11) NULL DEFAULT 0 COMMENT '异常的数量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '表单计数' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of form_count
-- ----------------------------
INSERT INTO `form_count` VALUES ('1527273298542428162', '2022-05-19 21:01:44', '2022-05-19 21:01:44', '1527273297393188866', 3, 0, 0);
INSERT INTO `form_count` VALUES ('1527274078888493058', '2022-05-19 21:04:50', '2022-05-19 21:04:50', '1527274078825578498', 3, 0, 0);

-- ----------------------------
-- Table structure for form_count_tuple
-- ----------------------------
DROP TABLE IF EXISTS `form_count_tuple`;
CREATE TABLE `form_count_tuple`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `form_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '表单Id',
  `tuple_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '元组id',
  `undone` int(11) NULL DEFAULT 0 COMMENT '没做的',
  `done` int(11) NULL DEFAULT 0 COMMENT '做了正常的',
  `abnormal` int(11) NULL DEFAULT 0 COMMENT '做了不正常的',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '表单元组统计' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of form_count_tuple
-- ----------------------------
INSERT INTO `form_count_tuple` VALUES ('1527273298676645891', '2022-05-19 21:01:44', '2022-05-19 21:01:44', '1527273297393188866', '11', 2, 0, 0);
INSERT INTO `form_count_tuple` VALUES ('1527273298676645892', '2022-05-19 21:01:44', '2022-05-19 21:01:44', '1527273297393188866', '12', 1, 0, 0);
INSERT INTO `form_count_tuple` VALUES ('1527274078951407620', '2022-05-19 21:04:50', '2022-05-19 21:04:50', '1527274078825578498', '11', 2, 0, 0);
INSERT INTO `form_count_tuple` VALUES ('1527274078951407621', '2022-05-19 21:04:50', '2022-05-19 21:04:50', '1527274078825578498', '12', 1, 0, 0);

-- ----------------------------
-- Table structure for form_img
-- ----------------------------
DROP TABLE IF EXISTS `form_img`;
CREATE TABLE `form_img`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `healthy_code` int(1) NULL DEFAULT NULL COMMENT '是否要健康码',
  `tarvel_card` int(1) NULL DEFAULT NULL COMMENT '是否要行程卡',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '表单图片选择' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of form_img
-- ----------------------------

-- ----------------------------
-- Table structure for form_label
-- ----------------------------
DROP TABLE IF EXISTS `form_label`;
CREATE TABLE `form_label`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标签名称不能重名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '表单标签' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of form_label
-- ----------------------------
INSERT INTO `form_label` VALUES ('41', '2022-05-19 20:29:10', '2022-05-19 20:29:10', '姓名');
INSERT INTO `form_label` VALUES ('42', '2022-05-19 20:29:18', '2022-05-19 20:29:18', '身份证号');
INSERT INTO `form_label` VALUES ('43', '2022-05-19 20:29:30', '2022-05-19 20:29:30', '手机号');

-- ----------------------------
-- Table structure for form_label_img
-- ----------------------------
DROP TABLE IF EXISTS `form_label_img`;
CREATE TABLE `form_label_img`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标签名称不能重名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '图片标签' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of form_label_img
-- ----------------------------

-- ----------------------------
-- Table structure for form_records
-- ----------------------------
DROP TABLE IF EXISTS `form_records`;
CREATE TABLE `form_records`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `form_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '表单Id',
  `organization_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '组织Id',
  `tuple_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '元组id',
  `user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户id',
  `status` int(11) NULL DEFAULT 0 COMMENT '完成情况 0未完成 1已完成 2有异常',
  `label_combination` json NULL COMMENT '交文本标签组合方式',
  `label_combination_img` json NULL COMMENT '交图片的标签组合',
  `healthy_code_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '健康码url',
  `travel_card_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '行程卡url',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '表单记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of form_records
-- ----------------------------
INSERT INTO `form_records` VALUES ('1527273298609537025', '2022-05-19 21:01:44', '2022-05-19 21:01:44', '1527273297393188866', '6', '11', '1', 0, NULL, NULL, NULL, NULL);
INSERT INTO `form_records` VALUES ('1527273298609537026', '2022-05-19 21:01:44', '2022-05-19 21:01:44', '1527273297393188866', '6', '11', '2', 0, NULL, NULL, NULL, NULL);
INSERT INTO `form_records` VALUES ('1527273298676645890', '2022-05-19 21:01:44', '2022-05-19 21:01:44', '1527273297393188866', '6', '12', '4', 0, NULL, NULL, NULL, NULL);
INSERT INTO `form_records` VALUES ('1527274078888493059', '2022-05-19 21:04:50', '2022-05-19 21:04:50', '1527274078825578498', '6', '11', '1', 0, NULL, NULL, NULL, NULL);
INSERT INTO `form_records` VALUES ('1527274078951407618', '2022-05-19 21:04:50', '2022-05-19 21:04:50', '1527274078825578498', '6', '11', '2', 0, NULL, NULL, NULL, NULL);
INSERT INTO `form_records` VALUES ('1527274078951407619', '2022-05-19 21:04:50', '2022-05-19 21:04:50', '1527274078825578498', '6', '12', '4', 0, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for img_health_code
-- ----------------------------
DROP TABLE IF EXISTS `img_health_code`;
CREATE TABLE `img_health_code`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `form_records_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '表单记录Id',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片url',
  `color` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '颜色',
  `acid_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '核酸类型',
  `acid_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '核酸时间',
  `vaccines_count` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '疫苗针次',
  `status` int(1) NULL DEFAULT 1 COMMENT '是否异常 1或者2',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '健康码图片' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of img_health_code
-- ----------------------------

-- ----------------------------
-- Table structure for img_label
-- ----------------------------
DROP TABLE IF EXISTS `img_label`;
CREATE TABLE `img_label`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `img_titile` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标签标题',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '标签要求上传的图片' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of img_label
-- ----------------------------
INSERT INTO `img_label` VALUES ('51', '2022-05-19 20:30:15', '2022-05-19 20:30:15', '死亡证明');

-- ----------------------------
-- Table structure for img_travel_card
-- ----------------------------
DROP TABLE IF EXISTS `img_travel_card`;
CREATE TABLE `img_travel_card`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `form_records_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '表单记录id',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片url',
  `color` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '颜色',
  `is_star` int(1) NULL DEFAULT 1 COMMENT '是否带星  1或者2',
  `travel_records` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '行程记录',
  `risk_area` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '风险地区',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '行程卡照片' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of img_travel_card
-- ----------------------------

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `relation_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '关联id',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '内容',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '消息类型',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '消息模板' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of message
-- ----------------------------

-- ----------------------------
-- Table structure for message_user
-- ----------------------------
DROP TABLE IF EXISTS `message_user`;
CREATE TABLE `message_user`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户id',
  `message_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '消息id',
  `status` int(1) NULL DEFAULT 0 COMMENT '未读已读',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '通知用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of message_user
-- ----------------------------

-- ----------------------------
-- Table structure for organization
-- ----------------------------
DROP TABLE IF EXISTS `organization`;
CREATE TABLE `organization`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `title` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '群组命名',
  `create_user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人id',
  `type` int(11) NULL DEFAULT NULL COMMENT '群组类型，枚举',
  `notes` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `head` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '群头像',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '群组' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of organization
-- ----------------------------
INSERT INTO `organization` VALUES ('6', '2022-05-19 21:01:10', '2022-05-19 21:01:10', '还行', '3', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for organization_special
-- ----------------------------
DROP TABLE IF EXISTS `organization_special`;
CREATE TABLE `organization_special`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '群组名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '特殊群组' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of organization_special
-- ----------------------------

-- ----------------------------
-- Table structure for tuple
-- ----------------------------
DROP TABLE IF EXISTS `tuple`;
CREATE TABLE `tuple`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人Id',
  `organization_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '群组Id',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '元组名称',
  `num` int(11) NULL DEFAULT 1 COMMENT '人数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '元组' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tuple
-- ----------------------------
INSERT INTO `tuple` VALUES ('11', '2022-05-19 20:24:27', '2022-05-19 20:24:27', '3', '6', '试试', 2);
INSERT INTO `tuple` VALUES ('12', '2022-05-19 20:23:55', '2022-05-19 20:23:55', '4', '6', '还好啊', 1);

-- ----------------------------
-- Table structure for tuple_member
-- ----------------------------
DROP TABLE IF EXISTS `tuple_member`;
CREATE TABLE `tuple_member`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `organization_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '群组Id',
  `tuple_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '元组Id',
  `user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '元组成员id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '元组成员名字',
  `is_fake` int(1) NULL DEFAULT 0 COMMENT '是否是虚账户',
  `head` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '元组成员信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tuple_member
-- ----------------------------
INSERT INTO `tuple_member` VALUES ('16', '2022-05-19 20:25:58', '2022-05-19 20:25:58', '6', '11', '1', '张三', 0, 'xxx');
INSERT INTO `tuple_member` VALUES ('17', '2022-05-19 20:25:39', '2022-05-19 20:25:39', '6', '11', '2', '李四', 0, 'xxx');
INSERT INTO `tuple_member` VALUES ('18', '2022-05-19 20:25:13', '2022-05-19 20:25:13', '6', '12', '4', '王五', 0, 'xxx');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `union_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '平台union_id',
  `open_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '平台OpenId',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `head` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `download_num` int(11) NULL DEFAULT NULL COMMENT '剩余可下载次数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '2022-05-19 20:26:24', '2022-05-19 20:26:24', NULL, NULL, '张三', NULL, NULL);
INSERT INTO `user` VALUES ('2', '2022-05-19 20:26:39', '2022-05-19 20:26:39', NULL, NULL, '李四', NULL, NULL);
INSERT INTO `user` VALUES ('3', '2022-05-19 20:26:48', '2022-05-19 20:26:48', NULL, NULL, '老大', NULL, NULL);
INSERT INTO `user` VALUES ('4', '2022-05-19 20:26:56', '2022-05-19 20:26:56', NULL, NULL, '王五', NULL, NULL);

-- ----------------------------
-- Table structure for vip
-- ----------------------------
DROP TABLE IF EXISTS `vip`;
CREATE TABLE `vip`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `status` int(1) NULL DEFAULT NULL COMMENT '是否是会员',
  `download_add` int(11) NULL DEFAULT NULL COMMENT '每月增加的下载次数',
  `expire_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '过期时间',
  `user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户Id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '会员权益表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of vip
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
