/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.1.73-community : Database - drugee1.0
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`drugee1.0` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;

USE `drugee1.0`;

/*Table structure for table `message` */

DROP TABLE IF EXISTS `message`;

CREATE TABLE `message` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `usercode` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '用户名',
  `imsi` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '电话号码的唯一识别号',
  `title` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '消息标题',
  `data` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '推送的数据',
  `modid` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '模块标识',
  `module` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '模块名称',
  `pushed` bit(1) DEFAULT NULL COMMENT '是否已推送过',
  `time` varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT '消息推送时间',
  `isDel` int(1) DEFAULT NULL COMMENT '是否被删0-正常，1-未读，9-已删',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `message` */


/*Table structure for table `sys_logs` */

DROP TABLE IF EXISTS `sys_logs`;

CREATE TABLE `sys_logs` (
  `id` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '日志主键',
  `TAG` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '日志标记',
  `INFO` varchar(2000) COLLATE utf8_bin DEFAULT NULL COMMENT '日志内容',
  `TIME` varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT '日志时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `sys_logs` */


/*Table structure for table `sys_menu` */

DROP TABLE IF EXISTS `sys_menu`;

CREATE TABLE `sys_menu` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '菜单主键',
  `name` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '菜单名称',
  `link` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '连接地址',
  `target` bit(1) DEFAULT NULL COMMENT '打开方式',
  `parent` int(11) DEFAULT NULL COMMENT '上级目录',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `sys_menu` */

insert  into `sys_menu`(`id`,`name`,`link`,`target`,`parent`) values (1,'系统管理',NULL,'\0',NULL),(2,'密码修改','index.do?mt=pwd','\0',1),(3,'用户管理',NULL,'\0',NULL),(4,'APP用户管理','user.do','\0',3),(5,'消息管理',NULL,'\0',NULL),(6,'推送消息管理','msg.do','\0',5),(7,'日志管理','logs.do','\0',1),(8,'APP版本更新','index.do?mt=upfile','',1);

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `usercode` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '账号',
  `password` varchar(41) COLLATE utf8_bin DEFAULT NULL COMMENT '密码',
  `imsi` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '手机IMSI码',
  `imei` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '手机IMEI码',
  `telphone` varchar(11) COLLATE utf8_bin DEFAULT NULL COMMENT '联系手机号',
  `corpid` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '公司编号',
  `corpname` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '公司名称',
  `reason` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '设备变更理由',
  `type` int(1) DEFAULT NULL COMMENT '账户类别,1-药材公司，2-耗材公司，9-管理员',
  `isDel` int(1) DEFAULT NULL COMMENT '0-正常，1-待审核,9-删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `sys_user` */


/*Table structure for table `sys_version` */

DROP TABLE IF EXISTS `sys_version`;

CREATE TABLE `sys_version` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '版本主键',
  `version` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `sys_version` */

insert  into `sys_version`(`id`,`version`) values (1,'1.8');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
