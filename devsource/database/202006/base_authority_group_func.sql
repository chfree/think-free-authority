/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50718
 Source Host           : localhost
 Source Database       : think_free

 Target Server Type    : MySQL
 Target Server Version : 50718
 File Encoding         : utf-8

 Date: 06/03/2020 12:18:45 PM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `base_authority_group_func`
-- ----------------------------
DROP TABLE IF EXISTS `base_authority_group_func`;
CREATE TABLE `base_authority_group_func` (
  `id` varchar(50) NOT NULL COMMENT 'id',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user_id` varchar(50) DEFAULT NULL COMMENT '创建者id',
  `create_user_name` varchar(50) DEFAULT NULL COMMENT '创建者名称',
  `func_id` varchar(50) NOT NULL COMMENT '菜单id',
  `group_id` varchar(50) NOT NULL COMMENT '分组Id',
  `func_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

SET FOREIGN_KEY_CHECKS = 1;
