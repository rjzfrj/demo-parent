/*
Navicat MySQL Data Transfer

Source Server         : p2p测试服务器
Source Server Version : 50516
Source Host           : 192.168.15.109:3306
Source Database       : sdk

Target Server Type    : MYSQL
Target Server Version : 50516
File Encoding         : 65001

Date: 2015-07-03 11:32:30
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sdk_pay_order
-- ----------------------------
DROP TABLE IF EXISTS `sdk_pay_order`;
CREATE TABLE `sdk_pay_order` (
  `ORDER_ID` varchar(50) NOT NULL,
  `OUTORDER_ID` varchar(50) NOT NULL,
  `OUTACCT_ID` varchar(50) DEFAULT NULL,
  `TRAN_AMT` varchar(50) NOT NULL DEFAULT '0',
  `NOTIFY_URL` varchar(100) DEFAULT NULL,
  `ORDER_INFO` varchar(100) DEFAULT NULL,
  `MERCHANT_ID` varchar(30) NOT NULL,
  `MERCHANT_NAME` varchar(100) DEFAULT NULL,
  `TRAN_TIME` varchar(50) DEFAULT NULL,
  `CARD_NO` varchar(30) DEFAULT NULL,
  `ORG_CODE` varchar(30) DEFAULT NULL,
  `ACCT_MOBILE` varchar(30) DEFAULT '0',
  `NOTIFY_STATUS` varchar(3) DEFAULT '0',
  `NOTIFY_CNT` int(3) DEFAULT '0',
  `TRAN_STATUS` varchar(3) DEFAULT NULL,
  PRIMARY KEY (`ORDER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sdk_pay_order
-- ----------------------------
