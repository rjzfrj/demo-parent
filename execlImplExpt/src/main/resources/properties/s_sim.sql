/*
Navicat MySQL Data Transfer


Source Server Version : 50626
Source Host           : localhost:3306

Target Server Type    : MYSQL
Target Server Version : 50626
File Encoding         : 65001

*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for s_sim
-- ----------------------------
DROP TABLE IF EXISTS `s_sim`;
CREATE TABLE `s_sim` (
  `CCID` varchar(30) NOT NULL,
  `SIM` varchar(50) DEFAULT NULL,
  `IMSI` varchar(20) DEFAULT NULL,
  `STATUS` char(2) DEFAULT NULL,
  `COMBO` varchar(50) DEFAULT NULL,
  `NAME` varchar(50) DEFAULT NULL,
  `TYPE` varchar(50) DEFAULT NULL,
  `E_ID` varchar(50) DEFAULT NULL,
  `U_ID` bigint(20) DEFAULT NULL,
  `REMARK` text,
  PRIMARY KEY (`CCID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='sim卡';
