/*
SQLyog Ultimate v10.00 Beta1
MySQL - 5.6.20-log : Database - msm_720_systemmanager_db
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`msm_720_systemmanager_db` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `msm_720_systemmanager_db`;

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `id` bigint(15) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(150) COMMENT '用户名',
  `account` varchar(50) DEFAULT NULL COMMENT '登录账号',
  `pwd` varchar(50) DEFAULT NULL COMMENT '密码',
  `token` varchar(150) DEFAULT NULL COMMENT '会话令牌',
  `enckey` varchar(50) DEFAULT NULL COMMENT '加密密钥1',
  `enciv` varchar(50) DEFAULT NULL COMMENT '加密密钥2',
  `effective_date` timestamp NULL DEFAULT NULL COMMENT '生效日期',
  `expiration_date` timestamp NULL DEFAULT NULL COMMENT '失效日期',
  `email` varchar(150) DEFAULT NULL COMMENT '邮件',
  `mobile_no` varchar(30) DEFAULT NULL COMMENT '手机号',
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `create_by` bigint(15) NOT NULL COMMENT '创建人',
  `last_upd_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新日期',
  `last_upd_by` bigint(15) DEFAULT NULL COMMENT '最后更新人',
  `modification_num` bigint(15) DEFAULT NULL COMMENT '版本号',
  `enable_flag` varchar(1) NOT NULL DEFAULT 'Y' COMMENT '有效标识',
  `org_id` bigint(15) NOT NULL COMMENT '组织id组织表主键',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100023 DEFAULT CHARSET=utf8;

/*Data for the table `sys_user` */

insert  into `sys_user`(`id`,`name`,`account`,`pwd`,`token`,`enckey`,`enciv`,`effective_date`,`expiration_date`,`email`,`mobile_no`,`create_date`,`create_by`,`last_upd_date`,`last_upd_by`,`modification_num`,`enable_flag`,`org_id`) values (1,'田翼彪','6418000406',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2017-02-22 17:26:25',1,'2017-03-01 19:11:49',NULL,NULL,'N',1),(100000,'朱云生','10207653',NULL,NULL,NULL,NULL,NULL,NULL,'aa@1','12','2017-03-01 16:33:34',1,'2017-03-01 16:33:34',NULL,NULL,'Y',1),(100001,'黄福强','10209744',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2017-03-02 19:03:49',1,NULL,NULL,NULL,'Y',1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
