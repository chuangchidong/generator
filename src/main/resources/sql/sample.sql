# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: (MySQL 5.6.25)
# Database: sample
# Generation Time: 2018-09-18 05:30:42 +0000
# ************************************************************


# Dump of table t_api
# ------------------------------------------------------------

DROP TABLE IF EXISTS `t_api`;

CREATE TABLE `t_api` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `project_id` int(11) NOT NULL COMMENT '项目ID',
  `module_id` int(11) NOT NULL COMMENT '项目模块ID',
  `name` varchar(64) NOT NULL COMMENT '名称',
  `url` varchar(64) NOT NULL COMMENT 'url路径',
  `method` varchar(8) NOT NULL COMMENT '访问方式',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='模块信息';

LOCK TABLES `t_api` WRITE;
/*!40000 ALTER TABLE `t_api` DISABLE KEYS */;

INSERT INTO `t_api` (`id`, `project_id`, `module_id`, `name`, `url`, `method`)
VALUES
	(1,1,1,'测试添加','/pos/get','GET'),
	(2,1,1,'测试post','/pos/post','POST');

/*!40000 ALTER TABLE `t_api` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table t_module
# ------------------------------------------------------------

DROP TABLE IF EXISTS `t_module`;

CREATE TABLE `t_module` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `project_id` int(11) NOT NULL COMMENT '项目ID',
  `name` varchar(64) NOT NULL COMMENT '名称',
  `code` varchar(64) NOT NULL DEFAULT '' COMMENT '名称英文',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='模块信息';

LOCK TABLES `t_module` WRITE;
/*!40000 ALTER TABLE `t_module` DISABLE KEYS */;

INSERT INTO `t_module` (`id`, `project_id`, `name`, `code`)
VALUES
	(1,1,'测试模块','test'),
	(2,1,'商品','goods'),
	(3,1,'店铺','store'),
	(4,1,'会员','user'),
	(5,1,'订单','order');

/*!40000 ALTER TABLE `t_module` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table t_project
# ------------------------------------------------------------

DROP TABLE IF EXISTS `t_project`;

CREATE TABLE `t_project` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(64) NOT NULL COMMENT '名称',
  `version` varchar(12) NOT NULL COMMENT '版本',
  `desc` varchar(128) DEFAULT '' COMMENT '描述',
  `remark` varchar(128) DEFAULT '' COMMENT '说明',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='项目信息';

LOCK TABLES `t_project` WRITE;
/*!40000 ALTER TABLE `t_project` DISABLE KEYS */;

INSERT INTO `t_project` (`id`, `name`, `version`, `desc`, `remark`)
VALUES
	(1,'测试','1.0.0','这是第一个例子','就是试试'),
	(2,'测试项目','1.0.1','第二行','编辑测试');

/*!40000 ALTER TABLE `t_project` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table t_request
# ------------------------------------------------------------

DROP TABLE IF EXISTS `t_request`;

CREATE TABLE `t_request` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `project_id` int(11) NOT NULL COMMENT '项目ID',
  `module_id` int(11) NOT NULL COMMENT '项目模块ID',
  `api_id` int(11) NOT NULL COMMENT 'API接口ID',
  `parent_id` int(11) DEFAULT '0' COMMENT '上一级字段ID',
  `field` varchar(16) NOT NULL COMMENT '字段',
  `type` varchar(8) NOT NULL COMMENT '类型',
  `is_nullable` int(4) NOT NULL DEFAULT '1' COMMENT '是否能为空(1.能，0 不能)',
  `desc` varchar(64) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='请求参数信息';

LOCK TABLES `t_request` WRITE;
/*!40000 ALTER TABLE `t_request` DISABLE KEYS */;

INSERT INTO `t_request` (`id`, `project_id`, `module_id`, `api_id`, `parent_id`, `field`, `type`, `is_nullable`, `desc`)
VALUES
	(1,1,1,1,1,'userId','Integer',0,'用户名称'),
	(21,1,1,2,NULL,'storeId','Integer',0,'店铺ID');

/*!40000 ALTER TABLE `t_request` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table t_response
# ------------------------------------------------------------

DROP TABLE IF EXISTS `t_response`;

CREATE TABLE `t_response` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `project_id` int(11) NOT NULL COMMENT '项目ID',
  `module_id` int(11) NOT NULL COMMENT '项目模块ID',
  `api_id` int(11) NOT NULL COMMENT 'API接口ID',
  `parent_id` int(11) DEFAULT '0' COMMENT '上一级字段ID',
  `field` varchar(16) NOT NULL COMMENT '字段',
  `type` varchar(8) NOT NULL COMMENT '类型',
  `desc` varchar(64) DEFAULT NULL COMMENT '描述',
  `mock` varchar(20) DEFAULT NULL COMMENT 'mock值',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='返回结果字段信息';

LOCK TABLES `t_response` WRITE;
/*!40000 ALTER TABLE `t_response` DISABLE KEYS */;

INSERT INTO `t_response` (`id`, `project_id`, `module_id`, `api_id`, `parent_id`, `field`, `type`, `desc`, `mock`)
VALUES
	(1,1,1,1,0,'userId','Integer','用户ID','102'),
	(2,1,1,1,0,'userName','String','用户名称','周芷若'),
	(3,1,1,1,0,'age','Integer','年龄','19'),
	(64,1,1,2,NULL,'userId','Integer','用户ID','101'),
	(65,1,1,2,NULL,'userName','String','用户名称','张无忌'),
	(66,1,1,2,NULL,'age','Integer','年龄','20'),
	(67,1,1,2,NULL,'address','String','地址','北京市朝阳区');

/*!40000 ALTER TABLE `t_response` ENABLE KEYS */;
UNLOCK TABLES;


