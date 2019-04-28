/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50716
Source Host           : localhost:3306
Source Database       : xiao_shou

Target Server Type    : MYSQL
Target Server Version : 50716
File Encoding         : 65001

Date: 2019-02-18 19:53:02
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for gongyingshangxingxi
-- ----------------------------
DROP TABLE IF EXISTS `gongyingshangxingxi`;
CREATE TABLE `gongyingshangxingxi` (
  `id` varchar(11) NOT NULL,
  `name` varchar(32) DEFAULT NULL,
  `Gysdw` varchar(32) DEFAULT NULL,
  `address` varchar(128) DEFAULT NULL,
  `youxiang` varchar(128) DEFAULT NULL,
  `lname1` varchar(128) DEFAULT NULL,
  `lname2` varchar(128) DEFAULT NULL,
  `ltel1` varchar(128) DEFAULT NULL,
  `ltel2` varchar(128) DEFAULT NULL,
  `yh` varchar(128) DEFAULT NULL,
  `hao` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gongyingshangxingxi
-- ----------------------------
INSERT INTO `gongyingshangxingxi` VALUES ('gys1002', '泸州XX公司', '公司', '泸州XX路', '123@qq.com', '阿王', '阿八', '123', '456', '555', '工商银行');
INSERT INTO `gongyingshangxingxi` VALUES ('gys1003', '成都XX公司', '公司', '成都XX路', '123@qq.com', '王', '八', '555', '666', '987564', '工商银行');

-- ----------------------------
-- Table structure for kehuxingxi
-- ----------------------------
DROP TABLE IF EXISTS `kehuxingxi`;
CREATE TABLE `kehuxingxi` (
  `id` varchar(11) NOT NULL,
  `khname` varchar(32) DEFAULT NULL,
  `khdw` varchar(32) DEFAULT NULL,
  `address` varchar(128) DEFAULT NULL,
  `youxiang` varchar(128) DEFAULT NULL,
  `lname1` varchar(128) DEFAULT NULL,
  `lname2` varchar(128) DEFAULT NULL,
  `ltel1` varchar(128) DEFAULT NULL,
  `ltel2` varchar(128) DEFAULT NULL,
  `khbeizhu` varchar(128) DEFAULT NULL,
  `yinhang` varchar(128) DEFAULT NULL,
  `hao` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of kehuxingxi
-- ----------------------------
INSERT INTO `kehuxingxi` VALUES ('kh1001', '某某公司', '公司', '成都市XX路', '200120@126.com', '阿王', '阿八', '123456', '123456', '还可以', 'XX银行', '123456');
INSERT INTO `kehuxingxi` VALUES ('kh1002', '某某学校', '学校', '成都市XX路', '123@126.com', '小王', '小八', '135555555555', '13666666666', '还行', '中国银行', '654321');

-- ----------------------------
-- Table structure for kucun
-- ----------------------------
DROP TABLE IF EXISTS `kucun`;
CREATE TABLE `kucun` (
  `id` varchar(32) NOT NULL,
  `spname` varchar(64) DEFAULT NULL,
  `Gysdw` varchar(64) DEFAULT NULL,
  `pinpai` varchar(128) DEFAULT NULL,
  `gg` varchar(128) DEFAULT NULL,
  `bz` varchar(128) DEFAULT NULL,
  `scqy` varchar(128) DEFAULT NULL,
  `dj` double DEFAULT NULL,
  `kcsl` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of kucun
-- ----------------------------
INSERT INTO `kucun` VALUES ('sp1001', 'XX鼠标', 'XX供应商', '雷蛇', 'G12 RGB', '全新', '雷蛇', '10', '9');
INSERT INTO `kucun` VALUES ('sp1002', 'YY键盘', 'YY供应商', '罗技', 'P123 RGB', '二手', '罗技', '20', '15');

-- ----------------------------
-- Table structure for rukutuihuo_detail
-- ----------------------------
DROP TABLE IF EXISTS `rukutuihuo_detail`;
CREATE TABLE `rukutuihuo_detail` (
  `id` varchar(32) NOT NULL,
  `spid` varchar(12) DEFAULT NULL,
  `dj` double DEFAULT NULL,
  `sl` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rukutuihuo_detail
-- ----------------------------
INSERT INTO `rukutuihuo_detail` VALUES ('RT20190218001', 'sp1001', '10', '1');
INSERT INTO `rukutuihuo_detail` VALUES ('RT20190218002', 'sp1002', '20', '2');

-- ----------------------------
-- Table structure for rukutuihuo_main
-- ----------------------------
DROP TABLE IF EXISTS `rukutuihuo_main`;
CREATE TABLE `rukutuihuo_main` (
  `rkthId` varchar(32) NOT NULL,
  `pzs` varchar(64) DEFAULT NULL,
  `je` varchar(64) DEFAULT NULL,
  `bz` varchar(128) DEFAULT NULL,
  `gysname` varchar(128) DEFAULT NULL,
  `rtdate` varchar(128) DEFAULT NULL,
  `czy` varchar(128) DEFAULT NULL,
  `jsr` varchar(18) DEFAULT NULL,
  `jsfs` varchar(18) DEFAULT NULL,
  PRIMARY KEY (`rkthId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rukutuihuo_main
-- ----------------------------
INSERT INTO `rukutuihuo_main` VALUES ('RT20190218001', '1', '10.0', '次品', '泸州XX公司', '2019-2-18 1:41:41', '林', '林', '现金');
INSERT INTO `rukutuihuo_main` VALUES ('RT20190218002', '1', '40.0', '假货', '成都XX公司', '2019-2-18 1:42:02', '林', '林', '现金');

-- ----------------------------
-- Table structure for rukutuihuo_view
-- ----------------------------
DROP TABLE IF EXISTS `rukutuihuo_view`;
CREATE TABLE `rukutuihuo_view` (
  `sellID` varchar(32) NOT NULL,
  `sp_id` varchar(32) NOT NULL,
  `spname` varchar(32) NOT NULL,
  `gg` varchar(64) DEFAULT NULL,
  `dj` varchar(128) DEFAULT NULL,
  `sl` varchar(128) DEFAULT NULL,
  `je` varchar(64) DEFAULT NULL,
  `gysname` varchar(128) DEFAULT NULL,
  `rtdate` datetime DEFAULT NULL,
  `czy` varchar(32) NOT NULL,
  `jsr` varchar(18) DEFAULT NULL,
  `jsfs` varchar(18) DEFAULT NULL,
  `bz` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rukutuihuo_view
-- ----------------------------
INSERT INTO `rukutuihuo_view` VALUES ('RT20190218001', 'sp1001', 'XX鼠标', 'G12 RGB', '10.0', '1', '10.0', '泸州XX公司', '2019-02-18 01:41:41', '林', '林', '现金', '次品');
INSERT INTO `rukutuihuo_view` VALUES ('RT20190218002', 'sp1002', 'YY键盘', 'P123 RGB', '20.0', '2', '40.0', '成都XX公司', '2019-02-18 01:42:02', '林', '林', '现金', '假货');

-- ----------------------------
-- Table structure for ruku_detail
-- ----------------------------
DROP TABLE IF EXISTS `ruku_detail`;
CREATE TABLE `ruku_detail` (
  `id` varchar(32) NOT NULL,
  `id_sp` varchar(64) DEFAULT NULL,
  `dj` double DEFAULT NULL,
  `sl` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ruku_detail
-- ----------------------------
INSERT INTO `ruku_detail` VALUES ('RK20190218001', 'sp1002', '50', '50');
INSERT INTO `ruku_detail` VALUES ('RK20190218001', 'sp1001', '20', '20');
INSERT INTO `ruku_detail` VALUES ('RK20190218002', 'sp1001', '10', '10');
INSERT INTO `ruku_detail` VALUES ('RK20190218003', 'sp1002', '20', '20');

-- ----------------------------
-- Table structure for ruku_main
-- ----------------------------
DROP TABLE IF EXISTS `ruku_main`;
CREATE TABLE `ruku_main` (
  `rkid` varchar(32) NOT NULL,
  `pzs` varchar(64) DEFAULT NULL,
  `je` varchar(64) DEFAULT NULL,
  `bz` varchar(128) DEFAULT NULL,
  `gysname` varchar(128) DEFAULT NULL,
  `rkdate` varchar(128) DEFAULT NULL,
  `czy` varchar(128) DEFAULT NULL,
  `jsr` varchar(18) DEFAULT NULL,
  `jsfs` varchar(18) DEFAULT NULL,
  PRIMARY KEY (`rkid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ruku_main
-- ----------------------------
INSERT INTO `ruku_main` VALUES ('RK20190218001', '2', '2900.0', '非常好', '泸州XX公司', '2019-2-18 1:19:32', '林', '海', '现金');
INSERT INTO `ruku_main` VALUES ('RK20190218002', '1', '100.0', 'OK', '泸州XX公司', '2019-2-18 1:40:09', '林', '林', '现金');
INSERT INTO `ruku_main` VALUES ('RK20190218003', '1', '400.0', '好', '成都XX公司', '2019-2-18 1:41:18', '林', '海', '现金');

-- ----------------------------
-- Table structure for ruku_view
-- ----------------------------
DROP TABLE IF EXISTS `ruku_view`;
CREATE TABLE `ruku_view` (
  `sellID` varchar(32) NOT NULL,
  `sp_id` varchar(32) NOT NULL,
  `spname` varchar(32) NOT NULL,
  `gg` varchar(64) DEFAULT NULL,
  `dj` varchar(128) DEFAULT NULL,
  `sl` varchar(128) DEFAULT NULL,
  `je` varchar(64) DEFAULT NULL,
  `gysname` varchar(128) DEFAULT NULL,
  `rkdate` datetime DEFAULT NULL,
  `czy` varchar(32) NOT NULL,
  `jsr` varchar(18) DEFAULT NULL,
  `jsfs` varchar(18) DEFAULT NULL,
  `bz` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ruku_view
-- ----------------------------
INSERT INTO `ruku_view` VALUES ('RK20190218002', 'sp1001', 'XX鼠标', 'G12 RGB', '10.0', '10', '100.0', '泸州XX公司', '2019-02-18 01:40:09', '林', '林', '现金', 'OK');
INSERT INTO `ruku_view` VALUES ('RK20190218003', 'sp1002', 'YY键盘', 'P123 RGB', '20.0', '20', '400.0', '成都XX公司', '2019-02-18 01:41:18', '林', '海', '现金', '好');

-- ----------------------------
-- Table structure for sell_detail
-- ----------------------------
DROP TABLE IF EXISTS `sell_detail`;
CREATE TABLE `sell_detail` (
  `id` varchar(32) NOT NULL,
  `spid` varchar(64) DEFAULT NULL,
  `dj` double DEFAULT NULL,
  `sl` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sell_detail
-- ----------------------------
INSERT INTO `sell_detail` VALUES ('XS20190218001', 'sp1001', '10', '2');
INSERT INTO `sell_detail` VALUES ('XS20190218002', 'sp1002', '20', '5');

-- ----------------------------
-- Table structure for sell_main
-- ----------------------------
DROP TABLE IF EXISTS `sell_main`;
CREATE TABLE `sell_main` (
  `sellID` varchar(32) NOT NULL,
  `pzs` varchar(64) DEFAULT NULL,
  `je` varchar(64) DEFAULT NULL,
  `bz` varchar(128) DEFAULT NULL,
  `khname` varchar(128) DEFAULT NULL,
  `xsdate` varchar(128) DEFAULT NULL,
  `czy` varchar(128) DEFAULT NULL,
  `jsr` varchar(18) DEFAULT NULL,
  `jsfs` varchar(18) DEFAULT NULL,
  PRIMARY KEY (`sellID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sell_main
-- ----------------------------
INSERT INTO `sell_main` VALUES ('XS20190218001', '1', '20.0', 'OK', '某某公司', '2019-2-18 1:42:21', '林', '林', '现金');
INSERT INTO `sell_main` VALUES ('XS20190218002', '1', '100.0', 'OK', '某某学校', '2019-2-18 1:42:34', '林', '林', '现金');

-- ----------------------------
-- Table structure for sell_view
-- ----------------------------
DROP TABLE IF EXISTS `sell_view`;
CREATE TABLE `sell_view` (
  `sellId` varchar(32) NOT NULL,
  `sp_id` varchar(32) NOT NULL,
  `spname` varchar(32) NOT NULL,
  `gg` varchar(64) DEFAULT NULL,
  `dj` varchar(128) DEFAULT NULL,
  `sl` varchar(128) DEFAULT NULL,
  `je` varchar(64) DEFAULT NULL,
  `khname` varchar(128) DEFAULT NULL,
  `xsdate` datetime DEFAULT NULL,
  `czy` varchar(32) NOT NULL,
  `jsr` varchar(18) DEFAULT NULL,
  `jsfs` varchar(18) DEFAULT NULL,
  `bz` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sell_view
-- ----------------------------
INSERT INTO `sell_view` VALUES ('XS20190218001', 'sp1001', 'XX鼠标', 'G12 RGB', '10.0', '2', '20.0', '某某公司', '2019-02-18 01:42:21', '林', '林', '现金', 'OK');
INSERT INTO `sell_view` VALUES ('XS20190218002', 'sp1002', 'YY键盘', 'P123 RGB', '20.0', '5', '100.0', '某某学校', '2019-02-18 01:42:34', '林', '林', '现金', 'OK');

-- ----------------------------
-- Table structure for shangpingxingxi
-- ----------------------------
DROP TABLE IF EXISTS `shangpingxingxi`;
CREATE TABLE `shangpingxingxi` (
  `id` varchar(11) NOT NULL,
  `spname` varchar(64) DEFAULT NULL,
  `Gysdw` varchar(64) DEFAULT NULL,
  `pinpai` varchar(128) DEFAULT NULL,
  `scqy` varchar(128) DEFAULT NULL,
  `gg` varchar(128) DEFAULT NULL,
  `bz` varchar(128) DEFAULT NULL,
  `zhibao` varchar(128) DEFAULT NULL,
  `scjk` varchar(128) DEFAULT NULL,
  `zhuyi` varchar(128) DEFAULT NULL,
  `gysName` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shangpingxingxi
-- ----------------------------
INSERT INTO `shangpingxingxi` VALUES ('sp1001', 'XX鼠标', 'XX供应商', '雷蛇', '雷蛇', 'G12 RGB', '全新', '2年', 'USB', '好好好', '泸州XX公司');
INSERT INTO `shangpingxingxi` VALUES ('sp1002', 'YY键盘', 'YY供应商', '罗技', '罗技', 'P123 RGB', '二手', '2年', 'USB', 'OK', '成都XX公司');

-- ----------------------------
-- Table structure for xiaoshoutuihuo_detail
-- ----------------------------
DROP TABLE IF EXISTS `xiaoshoutuihuo_detail`;
CREATE TABLE `xiaoshoutuihuo_detail` (
  `id` varchar(32) NOT NULL,
  `spid` varchar(64) DEFAULT NULL,
  `dj` double DEFAULT NULL,
  `sl` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xiaoshoutuihuo_detail
-- ----------------------------
INSERT INTO `xiaoshoutuihuo_detail` VALUES ('XT20190218001', 'sp1001', '10', '2');
INSERT INTO `xiaoshoutuihuo_detail` VALUES ('XT20190218002', 'sp1002', '20', '2');

-- ----------------------------
-- Table structure for xiaoshoutuihuo_main
-- ----------------------------
DROP TABLE IF EXISTS `xiaoshoutuihuo_main`;
CREATE TABLE `xiaoshoutuihuo_main` (
  `xsthID` varchar(32) NOT NULL,
  `pzs` varchar(64) DEFAULT NULL,
  `je` varchar(64) DEFAULT NULL,
  `bz` varchar(128) DEFAULT NULL,
  `khname` varchar(128) DEFAULT NULL,
  `thdate` varchar(128) DEFAULT NULL,
  `czy` varchar(128) DEFAULT NULL,
  `jsr` varchar(18) DEFAULT NULL,
  `jsfs` varchar(18) DEFAULT NULL,
  PRIMARY KEY (`xsthID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xiaoshoutuihuo_main
-- ----------------------------
INSERT INTO `xiaoshoutuihuo_main` VALUES ('XT20190216001', '1', '1.0', '有次品', '某某公司', '2019-2-16 20:20:26', '明礼馨德', 'a', '现金');
INSERT INTO `xiaoshoutuihuo_main` VALUES ('XT20190216002', '1', '11.0', '烂货', '某某公司', '2019-2-16 20:40:33', '明礼馨德', '4', '现金');
INSERT INTO `xiaoshoutuihuo_main` VALUES ('XT20190218001', '1', '20.0', '不行', '某某公司', '2019-2-18 1:43:56', '林', '海', '现金');
INSERT INTO `xiaoshoutuihuo_main` VALUES ('XT20190218002', '1', '40.0', '不行', '某某学校', '2019-2-18 1:44:07', '林', '海', '现金');

-- ----------------------------
-- Table structure for xiaoshoutuihuo_view
-- ----------------------------
DROP TABLE IF EXISTS `xiaoshoutuihuo_view`;
CREATE TABLE `xiaoshoutuihuo_view` (
  `sellId` varchar(32) NOT NULL,
  `sp_id` varchar(32) NOT NULL,
  `spname` varchar(32) NOT NULL,
  `gg` varchar(64) DEFAULT NULL,
  `dj` varchar(128) DEFAULT NULL,
  `sl` varchar(128) DEFAULT NULL,
  `je` varchar(64) DEFAULT NULL,
  `khname` varchar(128) DEFAULT NULL,
  `thdate` datetime DEFAULT NULL,
  `czy` varchar(32) NOT NULL,
  `jsr` varchar(18) DEFAULT NULL,
  `jsfs` varchar(18) DEFAULT NULL,
  `bz` varchar(128) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xiaoshoutuihuo_view
-- ----------------------------
INSERT INTO `xiaoshoutuihuo_view` VALUES ('XT20190218001', 'sp1001', 'XX鼠标', 'G12 RGB', '10.0', '2', '20.0', '某某公司', '2019-02-18 01:43:56', '林', '海', '现金', '不行');
INSERT INTO `xiaoshoutuihuo_view` VALUES ('XT20190218002', 'sp1002', 'YY键盘', 'P123 RGB', '20.0', '2', '40.0', '某某学校', '2019-02-18 01:44:07', '林', '海', '现金', '不行');

-- ----------------------------
-- Table structure for yonghubiao
-- ----------------------------
DROP TABLE IF EXISTS `yonghubiao`;
CREATE TABLE `yonghubiao` (
  `name` varchar(64) NOT NULL,
  `username` varchar(64) NOT NULL,
  `pass` varchar(32) DEFAULT NULL,
  `quan` char(1) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of yonghubiao
-- ----------------------------
INSERT INTO `yonghubiao` VALUES ('海', 'ABC', '321', 'c');
INSERT INTO `yonghubiao` VALUES ('林', 'admin', '123', 'a');
