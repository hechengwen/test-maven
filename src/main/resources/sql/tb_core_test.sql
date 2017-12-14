/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50624
 Source Host           : localhost
 Source Database       : bbt

 Target Server Type    : MySQL
 Target Server Version : 50624
 File Encoding         : utf-8

 Date: 09/24/2015 21:01:24 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `tb_core_test`
-- ----------------------------
DROP TABLE IF EXISTS `tb_core_test`;
CREATE TABLE `tb_core_test` (
  `id` varchar(255) NOT NULL COMMENT '主键',
  `name` varchar(255) DEFAULT '' COMMENT '姓名',
  `age` decimal(11,0) DEFAULT NULL COMMENT '年龄',
  `bol` tinyint(1) DEFAULT NULL COMMENT '布尔',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `tb_core_test`
-- ----------------------------
BEGIN;
INSERT INTO `tb_core_test` VALUES ('1', 'test1', '11', null), ('2', 'test2', '12', null), ('3', 'mask5', '55', null), ('4', 'aaa', '44', null);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
