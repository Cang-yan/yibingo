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

 Date: 18/05/2022 15:26:12
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
  `label_combination` json NULL COMMENT '标签组合方式',
  `healthy_code` int(1) NULL DEFAULT NULL COMMENT '是否要健康码',
  `travel_card` int(1) NULL DEFAULT NULL COMMENT '是否要行程卡',
  `organization_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '选择群组id',
  `begin_time` datetime NULL DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime NULL DEFAULT NULL COMMENT '结束时间',
  `attention_mode` int(11) NULL DEFAULT NULL COMMENT '提醒方式，枚举',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '表单发布' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of form
-- ----------------------------
INSERT INTO `form` VALUES ('1526100079022129154', '2022-05-16 15:19:47', '2022-05-16 15:19:47', '1', 0, '你必绿码', '{\"titile1\": \"姓名\", \"titile2\": \"身份证号\"}', 1, 1, '6', '2022-05-16 15:02:25', '2022-05-16 15:02:25', 0);
INSERT INTO `form` VALUES ('1526454411684999170', '2022-05-17 14:47:47', '2022-05-17 14:47:47', '1', 1, '你没事吧', '{\"titile1\": \"姓名\", \"titile2\": \"身份证号\"}', 0, 0, '6', '2022-05-17 14:46:49', '2022-05-17 14:46:49', 0);

-- ----------------------------
-- Table structure for form_count
-- ----------------------------
DROP TABLE IF EXISTS `form_count`;
CREATE TABLE `form_count`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `form_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '表单id',
  `undone` int(11) NULL DEFAULT NULL COMMENT '未完成的数量',
  `done` int(11) NULL DEFAULT NULL COMMENT '完成的数量',
  `abnormal` int(11) NULL DEFAULT NULL COMMENT '异常的数量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '表单计数' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of form_count
-- ----------------------------
INSERT INTO `form_count` VALUES ('1526100081014423554', '2022-05-16 15:19:48', '2022-05-16 15:19:48', '1526100079022129154', 1, 1, 1);
INSERT INTO `form_count` VALUES ('1526454413580824577', '2022-05-17 14:47:47', '2022-05-17 14:47:47', '1526454411684999170', 0, 2, 1);

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
INSERT INTO `form_label` VALUES ('21', '2022-05-16 11:46:05', '2022-05-16 11:46:05', '姓名');
INSERT INTO `form_label` VALUES ('22', '2022-05-16 11:46:27', '2022-05-16 11:46:27', '手机号');
INSERT INTO `form_label` VALUES ('23', '2022-05-16 11:46:40', '2022-05-16 11:46:40', '身份证号');

-- ----------------------------
-- Table structure for form_records
-- ----------------------------
DROP TABLE IF EXISTS `form_records`;
CREATE TABLE `form_records`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `form_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '表单Id',
  `user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户id',
  `status` int(11) NULL DEFAULT 0 COMMENT '完成情况 0未完成 1已完成 2有异常',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '表单记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of form_records
-- ----------------------------
INSERT INTO `form_records` VALUES ('1526100081148641282', '2022-05-16 15:19:48', '2022-05-16 15:19:48', '1526100079022129154', '4', 0);
INSERT INTO `form_records` VALUES ('1526100081215750146', '2022-05-16 15:19:48', '2022-05-16 15:19:48', '1526100079022129154', '2', 1);
INSERT INTO `form_records` VALUES ('1526100081215750147', '2022-05-16 15:19:48', '2022-05-16 15:19:48', '1526100079022129154', '1', 2);
INSERT INTO `form_records` VALUES ('1526454413715042305', '2022-05-17 14:47:47', '2022-05-17 14:47:47', '1526454411684999170', '4', 1);
INSERT INTO `form_records` VALUES ('1526454413715042306', '2022-05-17 14:47:47', '2022-05-17 14:47:47', '1526454411684999170', '2', 1);
INSERT INTO `form_records` VALUES ('1526454413782151169', '2022-05-17 14:47:47', '2022-05-17 14:47:47', '1526454411684999170', '1', 2);

-- ----------------------------
-- Table structure for form_template
-- ----------------------------
DROP TABLE IF EXISTS `form_template`;
CREATE TABLE `form_template`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `info` json NULL COMMENT '填写的信息',
  `is_healthy_code` int(1) NOT NULL DEFAULT 1 COMMENT '是否要健康码',
  `is_travel_card` int(1) NOT NULL DEFAULT 1 COMMENT '是否要行程卡',
  `label_combination` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标签组合方式',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '表单模板' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of form_template
-- ----------------------------

-- ----------------------------
-- Table structure for img_health_code
-- ----------------------------
DROP TABLE IF EXISTS `img_health_code`;
CREATE TABLE `img_health_code`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `form_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '表单记录Id',
  `user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户Id',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片url',
  `color` int(11) NULL DEFAULT NULL COMMENT '颜色枚举',
  `acid_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '核酸类型',
  `acid_time` datetime NULL DEFAULT NULL COMMENT '核酸时间',
  `vaccines_count` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '疫苗针次',
  `status` int(1) NULL DEFAULT 0 COMMENT '是否异常',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '健康码图片' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of img_health_code
-- ----------------------------

-- ----------------------------
-- Table structure for img_travel_card
-- ----------------------------
DROP TABLE IF EXISTS `img_travel_card`;
CREATE TABLE `img_travel_card`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `form_records_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '表单记录id',
  `user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户id',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片url',
  `color` int(11) NULL DEFAULT NULL COMMENT '颜色枚举',
  `is_star` int(1) NULL DEFAULT 0 COMMENT '是否带星',
  `travel_records` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '行程记录',
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
INSERT INTO `organization` VALUES ('6', '2022-05-15 22:28:07', '2022-05-15 22:28:07', NULL, '3', NULL, NULL, NULL);
INSERT INTO `organization` VALUES ('7', '2022-05-15 23:13:27', '2022-05-15 23:13:27', NULL, '1', NULL, NULL, NULL);

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
-- Table structure for schedule
-- ----------------------------
DROP TABLE IF EXISTS `schedule`;
CREATE TABLE `schedule`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `last_schedule_time` datetime NULL DEFAULT NULL COMMENT '上次定时时间',
  `relation_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '相关联id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '定时任务表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of schedule
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
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '元组' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tuple
-- ----------------------------
INSERT INTO `tuple` VALUES ('11', '2022-05-15 22:28:48', '2022-05-15 22:28:48', '1', '6', '试试');
INSERT INTO `tuple` VALUES ('12', '2022-05-15 22:29:17', '2022-05-15 22:29:17', '4', '6', '还好啊');

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
INSERT INTO `tuple_member` VALUES ('16', '2022-05-15 22:29:51', '2022-05-15 22:29:51', '6', '11', '1', '张三', 0, NULL);
INSERT INTO `tuple_member` VALUES ('17', '2022-05-15 22:30:12', '2022-05-15 22:30:12', '6', '11', '2', '李四', 0, NULL);
INSERT INTO `tuple_member` VALUES ('18', '2022-05-15 22:30:27', '2022-05-15 22:30:27', '6', '12', '4', '王五', 0, NULL);

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
INSERT INTO `user` VALUES ('1', '2022-05-15 22:27:04', '2022-05-15 22:27:04', NULL, NULL, '张三', NULL, NULL);
INSERT INTO `user` VALUES ('2', '2022-05-15 22:27:15', '2022-05-15 22:27:15', NULL, NULL, '李四', NULL, NULL);
INSERT INTO `user` VALUES ('3', '2022-05-15 22:27:29', '2022-05-15 22:27:29', NULL, NULL, '老大', NULL, NULL);
INSERT INTO `user` VALUES ('4', '2022-05-15 22:27:41', '2022-05-15 22:27:41', NULL, NULL, '王五', NULL, NULL);

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
