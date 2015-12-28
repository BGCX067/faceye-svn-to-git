-- MySQL dump 10.10
--
-- Host: localhost    Database: faceye
-- ------------------------------------------------------
-- Server version	5.0.18-nt

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `sys_category`
--

DROP TABLE IF EXISTS `sys_category`;
CREATE TABLE `sys_category` (
  `id` varchar(32) NOT NULL default '',
  `name` varchar(255) NOT NULL default '',
  `parent_id` varchar(32) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

--
-- Dumping data for table `sys_category`
--


/*!40000 ALTER TABLE `sys_category` DISABLE KEYS */;
LOCK TABLES `sys_category` WRITE;
INSERT INTO `sys_category` VALUES ('402881b31764776f0117649634060005','门户','402881ce17a971ca0117a9743df20001'),('402881b31764776f01176496842b0006','军事','402881ce17a971ca0117a9743df20001'),('402881ce17625a1b0117627ee6160004','游戏','402881ce17a971ca0117a9743df20001'),('402881ce179c528301179cd5c3850001','博客','402881ce17a971ca0117a9743df20001'),('402881ce179c528301179cd62eb80002','视频','402881ce17a971ca0117a9743df20001'),('402881ce179c528301179cd66b3e0003','音乐','402881ce17a971ca0117a9743df20001'),('402881ce179c528301179cd6b0e50004','交友','402881ce17a971ca0117a9743df20001'),('402881ce179c528301179cd6dc020005','IT技术','402881ce17a971ca0117a9743df20001'),('402881ce17a971ca0117a9743df20001','传统导航分类',NULL),('402881ce17a971ca0117a97476c30002','Feed导航分类',NULL),('402881ce17a971ca0117a9778d070003','门户','402881ce17a971ca0117a97476c30002'),('402881d4192e0b3501192ea1e3ff0003','创意','402881ce17a971ca0117a97476c30002'),('402881d4192e0b3501192ea207c90004','电影','402881ce17a971ca0117a97476c30002'),('402881d4192e0b3501192ea230850005','音乐','402881ce17a971ca0117a97476c30002'),('402881d4192e0b3501192ea285880006','美女','402881ce17a971ca0117a97476c30002'),('402881d4192e0b3501192ea296430007','生活','402881ce17a971ca0117a97476c30002'),('402881d4192e0b3501192ea2bc520008','娱乐','402881ce17a971ca0117a97476c30002'),('402881d4192e0b3501192ea2da1f0009','视频','402881ce17a971ca0117a97476c30002'),('402881d4192e0b3501192ea2f2e3000a','博客','402881ce17a971ca0117a97476c30002'),('402881d4192e0b3501192ea311e8000b','下载','402881ce17a971ca0117a97476c30002'),('402881d4192e0b3501192ea35a94000c','体育','402881ce17a971ca0117a97476c30002'),('402881d4192e0b3501192ea4036b000d','军事','402881ce17a971ca0117a97476c30002'),('402881de187e657b01187e6b0e400002','财经','402881ce17a971ca0117a97476c30002');
UNLOCK TABLES;
/*!40000 ALTER TABLE `sys_category` ENABLE KEYS */;

--
-- Table structure for table `sys_column`
--

DROP TABLE IF EXISTS `sys_column`;
CREATE TABLE `sys_column` (
  `id` varchar(32) NOT NULL default '',
  `name` varchar(255) NOT NULL default '',
  `description` varchar(255) default NULL,
  `url` varchar(255) default NULL,
  `parentid` varchar(32) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

--
-- Dumping data for table `sys_column`
--


/*!40000 ALTER TABLE `sys_column` DISABLE KEYS */;
LOCK TABLES `sys_column` WRITE;
INSERT INTO `sys_column` VALUES ('1','开源栏目','#','openSourceAction.do?method=forward&forward=list',NULL),('402881ce17384d820117396361010001','组件类库','#','openSourceAction.do?method=forward&forward=list','1'),('402881ce1752f824011752fd057b0001','导航','','',NULL),('402881ce1752f824011752fd96340002','传统导航','','traditionAction.do?method=forward&forward=list','402881ce1752f824011752fd057b0001'),('402881ce1752f82401175300d84f0003','Feed导航','','feedAction.do?method=forward&forward=list','402881ce1752f824011752fd057b0001'),('402881ce1753a480011753aeea0b0001','WEB开发','','openSourceAction.do?method=forward&forward=list','1'),('402881ce1753a480011753af48a40002','AJAX','','openSourceAction.do?method=forward&forward=list','402881ce1753a480011753aeea0b0001'),('402881ce1753a480011753af787a0003','JSP Tag','','openSourceAction.do?method=forward&forward=list','402881ce1753a480011753aeea0b0001'),('402881ce1753a480011753afc0740004','Web开发框架','','openSourceAction.do?method=forward&forward=list','402881ce1753a480011753aeea0b0001'),('402881ce1757a480011757a590e20001','应用系统','','openSourceAction.do?method=forward&forward=list','1'),('f4ccd38117699653011769b8e14f0001','持久化框架','','openSourceAction.do?method=forward&forward=list','402881ce17384d820117396361010001'),('f4ccd38117699653011769bcc8210002','Blog','','openSourceAction.do?method=forward&forward=list','402881ce1757a480011757a590e20001');
UNLOCK TABLES;
/*!40000 ALTER TABLE `sys_column` ENABLE KEYS */;

--
-- Table structure for table `sys_data_type`
--

DROP TABLE IF EXISTS `sys_data_type`;
CREATE TABLE `sys_data_type` (
  `id` varchar(32) NOT NULL,
  `name` varchar(50) default NULL,
  `typeName` varchar(255) default NULL,
  `typeNumInSql` int(11) default NULL,
  `typeOfClass` varchar(100) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

--
-- Dumping data for table `sys_data_type`
--


/*!40000 ALTER TABLE `sys_data_type` DISABLE KEYS */;
LOCK TABLES `sys_data_type` WRITE;
INSERT INTO `sys_data_type` VALUES ('1','String','String',5,'java.lang.String'),('402881ce16e8833d0116e8a8428f0001','Number','Number',0,'java.lang.Number'),('402881ce16e8833d0116e8a948710002','Double','Double',4,'java.lang.Double'),('f4cccc9416dcb85a0116dcee0f9f0004','Integer','Integer',4,'java.lang.Integer'),('f4cccc9416dcb85a0116dcfabff20006','Float','Float',6,'java.lang.Float');
UNLOCK TABLES;
/*!40000 ALTER TABLE `sys_data_type` ENABLE KEYS */;

--
-- Table structure for table `sys_domain`
--

DROP TABLE IF EXISTS `sys_domain`;
CREATE TABLE `sys_domain` (
  `id` varchar(32) NOT NULL,
  `name` varchar(50) default NULL,
  `domainName` varchar(255) default NULL,
  `tableName` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

--
-- Dumping data for table `sys_domain`
--


/*!40000 ALTER TABLE `sys_domain` DISABLE KEYS */;
LOCK TABLES `sys_domain` WRITE;
INSERT INTO `sys_domain` VALUES ('1','域模型','com.zaodian.core.componentsupport.dao.model.Domain','sys_domain');
UNLOCK TABLES;
/*!40000 ALTER TABLE `sys_domain` ENABLE KEYS */;

--
-- Table structure for table `sys_domain_query`
--

DROP TABLE IF EXISTS `sys_domain_query`;
CREATE TABLE `sys_domain_query` (
  `id` varchar(32) NOT NULL,
  `name` varchar(50) default NULL,
  `domain_id` varchar(32) default NULL,
  `queryType_id` varchar(32) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK9E6A51BFB5E28529` (`domain_id`),
  KEY `FK9E6A51BF90AADA4B` (`queryType_id`),
  CONSTRAINT `FK9E6A51BF90AADA4B` FOREIGN KEY (`queryType_id`) REFERENCES `sys_query_type` (`id`),
  CONSTRAINT `FK9E6A51BFB5E28529` FOREIGN KEY (`domain_id`) REFERENCES `sys_domain` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

--
-- Dumping data for table `sys_domain_query`
--


/*!40000 ALTER TABLE `sys_domain_query` DISABLE KEYS */;
LOCK TABLES `sys_domain_query` WRITE;
INSERT INTO `sys_domain_query` VALUES ('1','Domain Model Query List','1','1');
UNLOCK TABLES;
/*!40000 ALTER TABLE `sys_domain_query` ENABLE KEYS */;

--
-- Table structure for table `sys_domain_query_property`
--

DROP TABLE IF EXISTS `sys_domain_query_property`;
CREATE TABLE `sys_domain_query_property` (
  `Property_ID` varchar(32) NOT NULL,
  `DOMAIN_QUERY_ID` varchar(32) NOT NULL,
  KEY `FK71453455938ADFC9` (`Property_ID`),
  KEY `FK714534558D4CEE2` (`DOMAIN_QUERY_ID`),
  CONSTRAINT `FK714534558D4CEE2` FOREIGN KEY (`DOMAIN_QUERY_ID`) REFERENCES `sys_domain_query` (`id`),
  CONSTRAINT `FK71453455938ADFC9` FOREIGN KEY (`Property_ID`) REFERENCES `sys_property` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

--
-- Dumping data for table `sys_domain_query_property`
--


/*!40000 ALTER TABLE `sys_domain_query_property` DISABLE KEYS */;
LOCK TABLES `sys_domain_query_property` WRITE;
INSERT INTO `sys_domain_query_property` VALUES ('1','1'),('2','1'),('3','1');
UNLOCK TABLES;
/*!40000 ALTER TABLE `sys_domain_query_property` ENABLE KEYS */;

--
-- Table structure for table `sys_feed`
--

DROP TABLE IF EXISTS `sys_feed`;
CREATE TABLE `sys_feed` (
  `id` varchar(32) NOT NULL default '',
  `name` varchar(255) NOT NULL default '',
  `url` varchar(255) NOT NULL default '',
  `description` varchar(255) default NULL,
  `createDate` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `column_id` varchar(32) NOT NULL default '',
  `category_id` varchar(32) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

--
-- Dumping data for table `sys_feed`
--


/*!40000 ALTER TABLE `sys_feed` DISABLE KEYS */;
LOCK TABLES `sys_feed` WRITE;
INSERT INTO `sys_feed` VALUES ('40288139192bd28101192caecdbf0007','宋海鹏的博客','http://papalong.javaeye.com/rss','ext,ajax,struts,spring,hibernate','2008-04-07 16:00:00','402881ce1752f82401175300d84f0003','402881ce17a971ca0117a9778d070003'),('402881b31960e142011960f9a4e4000a','JavaEye','http://www.javaeye.com/rss','','2008-04-18 09:59:43','402881ce1752f82401175300d84f0003','402881ce17a971ca0117a9778d070003'),('402881b31960e142011960fb7289000b','sohu每日焦点图片','http://rss.news.sohu.com/rss/pfocus.xml','','2008-04-18 10:01:41','402881ce1752f82401175300d84f0003','402881ce17a971ca0117a9778d070003'),('402881b31960e142011960fc6fae000c','sohu每日焦点新闻','http://rss.news.sohu.com/rss/focus.xml','搜狐每日焦点新闻','2008-04-18 10:02:46','402881ce1752f82401175300d84f0003','402881ce17a971ca0117a9778d070003'),('402881b31960e142011960fd4d9c000d','搜狐国内新闻','http://rss.news.sohu.com/rss/guonei.xml','','2008-04-18 10:03:43','402881ce1752f82401175300d84f0003','402881ce17a971ca0117a9778d070003'),('402881b31960e142011960fe068a000e','搜狐国际新闻','http://rss.news.sohu.com/rss/guoji.xml','','2008-04-18 10:04:30','402881ce1752f82401175300d84f0003','402881ce17a971ca0117a9778d070003'),('402881b31960e142011960feb148000f','搜狐社会新闻','http://rss.news.sohu.com/rss/shehui.xml','','2008-04-18 10:05:14','402881ce1752f82401175300d84f0003','402881ce17a971ca0117a9778d070003'),('402881b31960e142011960ffb59c0010','搜狐军事新闻','http://mil.news.sohu.com/rss/junshi.xml','','2008-04-18 10:06:21','402881ce1752f82401175300d84f0003','402881ce17a971ca0117a9778d070003'),('402881b31960e142011961006bc40011','搜狐体育新闻','http://rss.news.sohu.com/rss/sports.xml','','2008-04-18 10:07:07','402881ce1752f82401175300d84f0003','402881ce17a971ca0117a9778d070003'),('402881b31960e1420119610112f30012','搜狐产经新闻','http://rss.news.sohu.com/rss/business.xml','','2008-04-18 10:07:50','402881ce1752f82401175300d84f0003','402881ce17a971ca0117a9778d070003'),('402881b31960e1420119610216de0013','搜狐IT新闻','http://rss.news.sohu.com/rss/it.xml','','2008-04-18 10:08:57','402881ce1752f82401175300d84f0003','402881ce17a971ca0117a9778d070003'),('402881b31960e14201196102d6270014','搜狐文教新闻','http://rss.news.sohu.com/rss/learning.xml','','2008-04-18 10:09:46','402881ce1752f82401175300d84f0003','402881ce17a971ca0117a9778d070003'),('402881b31960e14201196103db6b0015','搜狐娱乐新闻','http://rss.news.sohu.com/rss/yule.xml','','2008-04-18 10:10:52','402881ce1752f82401175300d84f0003','402881ce17a971ca0117a9778d070003'),('402881de187e657b01187e6a74500001','老沙的博客','http://shaminnong.blog.sohu.com/rss','','2008-03-04 16:00:00','402881ce1752f82401175300d84f0003','402881de187e657b01187e6b0e400002'),('f4ccd5ad1889a5d5011889ab26dd0001','章子怡','http://zhangziyi.blog.sohu.com/rss','','2008-03-07 14:35:38','402881ce1752f82401175300d84f0003','402881ce17a971ca0117a9778d070003');
UNLOCK TABLES;
/*!40000 ALTER TABLE `sys_feed` ENABLE KEYS */;

--
-- Table structure for table `sys_feed_scbscribe_count`
--

DROP TABLE IF EXISTS `sys_feed_scbscribe_count`;
CREATE TABLE `sys_feed_scbscribe_count` (
  `id` varchar(32) NOT NULL default '',
  `name` varchar(45) default NULL,
  `totalCount` int(10) unsigned NOT NULL default '1',
  `createDate` timestamp NOT NULL default '0000-00-00 00:00:00',
  `feed_id` varchar(32) NOT NULL default '',
  PRIMARY KEY  (`id`),
  KEY `FK_sys_feed_scbscribe_count_1` (`feed_id`),
  CONSTRAINT `FK_sys_feed_scbscribe_count_1` FOREIGN KEY (`feed_id`) REFERENCES `sys_feed` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

--
-- Dumping data for table `sys_feed_scbscribe_count`
--


/*!40000 ALTER TABLE `sys_feed_scbscribe_count` DISABLE KEYS */;
LOCK TABLES `sys_feed_scbscribe_count` WRITE;
INSERT INTO `sys_feed_scbscribe_count` VALUES ('402881e52172be4d012172c26cd90005',NULL,1,'2009-05-23 16:00:00','40288139192bd28101192caecdbf0007'),('402881e52172be4d012172c26dbd0007',NULL,0,'2009-05-23 16:00:00','402881b31960e142011960f9a4e4000a'),('402881e52172be4d012172c26e390009',NULL,1,'2009-05-23 16:00:00','402881b31960e142011960fb7289000b'),('402881e52172be4d012172c26ead000b',NULL,1,'2009-05-23 16:00:00','402881b31960e142011960fc6fae000c'),('402881e52172be4d012172c26f21000d',NULL,2,'2009-05-23 16:00:00','402881b31960e142011960fd4d9c000d'),('402881e52172be4d012172c26f7a000f',NULL,1,'2009-05-23 16:00:00','402881b31960e142011960fe068a000e'),('402881e52172be4d012172c270050011',NULL,1,'2009-05-23 16:00:00','402881b31960e142011960feb148000f'),('402881e52172be4d012172c2706d0013',NULL,2,'2009-05-23 16:00:00','402881b31960e142011960ffb59c0010'),('402881e52172be4d012172c270bd0015',NULL,1,'2009-05-23 16:00:00','402881b31960e142011961006bc40011'),('402881e52172be4d012172c271100017',NULL,2,'2009-05-23 16:00:00','402881b31960e1420119610112f30012'),('402881e52172be4d012172c271820019',NULL,2,'2009-05-23 16:00:00','402881b31960e1420119610216de0013'),('402881e52172be4d012172c271cd001b',NULL,1,'2009-05-23 16:00:00','402881b31960e14201196102d6270014'),('402881e52172be4d012172c27210001d',NULL,0,'2009-05-23 16:00:00','402881b31960e14201196103db6b0015'),('402881e52172be4d012172c27252001f',NULL,2,'2009-05-23 16:00:00','402881de187e657b01187e6a74500001'),('402881e52172be4d012172c272a90021',NULL,1,'2009-05-23 16:00:00','f4ccd5ad1889a5d5011889ab26dd0001');
UNLOCK TABLES;
/*!40000 ALTER TABLE `sys_feed_scbscribe_count` ENABLE KEYS */;

--
-- Table structure for table `sys_feed_subscribe`
--

DROP TABLE IF EXISTS `sys_feed_subscribe`;
CREATE TABLE `sys_feed_subscribe` (
  `id` varchar(32) NOT NULL default '',
  `name` varchar(100) default NULL,
  `subscribeTime` timestamp NOT NULL default '0000-00-00 00:00:00',
  `feed_id` varchar(32) NOT NULL default '',
  `user_resource_category_ID` varchar(32) NOT NULL default '',
  `feedOrder` int(10) unsigned NOT NULL default '0',
  PRIMARY KEY  (`id`),
  KEY `FK_sys_feed_subscribe_1` (`user_resource_category_ID`),
  CONSTRAINT `FK_sys_feed_subscribe_1` FOREIGN KEY (`user_resource_category_ID`) REFERENCES `user_resoruce_category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

--
-- Dumping data for table `sys_feed_subscribe`
--


/*!40000 ALTER TABLE `sys_feed_subscribe` DISABLE KEYS */;
LOCK TABLES `sys_feed_subscribe` WRITE;
INSERT INTO `sys_feed_subscribe` VALUES ('402881e52172be4d012172c26e45000a',NULL,'2009-05-23 16:00:00','402881b31960e142011960fb7289000b','402881e521734ffb01217350ee5b0001',0),('402881e52172be4d012172c26f2b000e',NULL,'2009-05-23 16:00:00','402881b31960e142011960fd4d9c000d','402881e52172be4d012172c26c210004',1),('402881e52172be4d012172c2700d0012',NULL,'2009-05-23 16:00:00','402881b31960e142011960feb148000f','402881e521734ffb01217350ee5b0001',1),('402881e52172be4d012172c270760014',NULL,'2009-05-23 16:00:00','402881b31960e142011960ffb59c0010','402881e521734ffb01217350ee5b0001',2),('402881e52172be4d012172c2718a001a',NULL,'2009-05-23 16:00:00','402881b31960e1420119610216de0013','402881e52172be4d012172c26c210004',3),('402881e52172be4d012172c271d3001c',NULL,'2009-05-23 16:00:00','402881b31960e14201196102d6270014','402881e52172be4d012172c26c210004',3),('402881e52172f1000121730108d90009',NULL,'2009-05-23 16:00:00','402881b31960e142011960fc6fae000c','402881e52172f10001217301052e0005',3),('402881e52172f100012173010944000a',NULL,'2009-05-23 16:00:00','402881b31960e142011960fd4d9c000d','402881e52172f10001217301052e0005',4),('402881e52172f1000121730109c5000b',NULL,'2009-05-23 16:00:00','402881b31960e142011960fe068a000e','f4ccc9ad218c872401218ca2422f0005',0),('402881e52172f100012173010acb000d',NULL,'2009-05-23 16:00:00','402881b31960e142011960ffb59c0010','402881e52172f10001217301052e0005',2),('402881e52172f100012173010b9d000f',NULL,'2009-05-23 16:00:00','402881b31960e1420119610112f30012','f4ccc9ad218ccde301218cdc6bfe0001',0),('402881e52172f100012173010d6b0014',NULL,'2009-05-23 16:00:00','f4ccd5ad1889a5d5011889ab26dd0001','402881e52172f10001217301052e0005',3),('402881e5217357f70121735f09d00004',NULL,'2009-05-24 16:00:00','40288139192bd28101192caecdbf0007','402881e52172be4d012172c26c210004',2),('f4ccc9ad218ccde301218ce1b0e30004',NULL,'2009-05-28 16:00:00','402881b31960e1420119610216de0013','f4ccc9ad218c872401218ca2422f0005',1),('f4ccc9ad218ccde301218ce2697d0005',NULL,'2009-05-29 14:59:56','402881b31960e142011961006bc40011','f4ccc9ad218c872401218ca2422f0005',2),('f4ccc9ad218ccde301218ce29daa0006',NULL,'2009-05-28 16:00:00','402881de187e657b01187e6a74500001','f4ccc9ad218ccde301218cdc6bfe0001',1),('f4ccc9ad218ccde301218ce511270007',NULL,'2009-05-29 15:02:50','402881b31960e1420119610112f30012','402881e5217357f70121735cedda0001',0),('f4ccc9ad218ccde301218ce52a130008',NULL,'2009-05-29 15:02:57','402881de187e657b01187e6a74500001','402881e5217357f70121735cedda0001',1);
UNLOCK TABLES;
/*!40000 ALTER TABLE `sys_feed_subscribe` ENABLE KEYS */;

--
-- Table structure for table `sys_html_type`
--

DROP TABLE IF EXISTS `sys_html_type`;
CREATE TABLE `sys_html_type` (
  `id` varchar(32) NOT NULL,
  `name` varchar(50) default NULL,
  `type` varchar(255) default NULL,
  `html` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

--
-- Dumping data for table `sys_html_type`
--


/*!40000 ALTER TABLE `sys_html_type` DISABLE KEYS */;
LOCK TABLES `sys_html_type` WRITE;
INSERT INTO `sys_html_type` VALUES ('1','text','text','<input type=text');
UNLOCK TABLES;
/*!40000 ALTER TABLE `sys_html_type` ENABLE KEYS */;

--
-- Table structure for table `sys_opensource`
--

DROP TABLE IF EXISTS `sys_opensource`;
CREATE TABLE `sys_opensource` (
  `id` varchar(32) NOT NULL default '',
  `name` varchar(255) NOT NULL default '',
  `url` varchar(255) default NULL,
  `content` text,
  `column_id` varchar(32) NOT NULL default '',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

--
-- Dumping data for table `sys_opensource`
--


/*!40000 ALTER TABLE `sys_opensource` DISABLE KEYS */;
LOCK TABLES `sys_opensource` WRITE;
INSERT INTO `sys_opensource` VALUES ('402881ce17538d2e0117539ede450003','Hibernate','http://www.hibernate.org持久化框架','null','f4ccd38117699653011769b8e14f0001'),('402881ce1757a91e011757ac77870001','Spring','http://www.springframework.org','&nbsp;Spring是一个解决了许多在J2EE开发中常见的问题的强大框架。\nSpring提供了管理业务对象的一致方法并且鼓励了注入对接口编程而不是对类编程的良好习惯。Spring的架构基础是基于使用JavaBean属性的\nInversion of\nControl容器。然而，这仅仅是完整图景中的一部分：Spring在使用IoC容器作为构建完关注所有架构层的完整解决方案方面是独一无二的。\nSpring提供了唯一的数据访问抽象，包括简单和有效率的JDBC框架，极大的改进了效率并且减少了可能的错误。Spring的数据访问架构还集成了\nHibernate和其他O/R\nmapping解决方案。Spring还提供了唯一的事务管理抽象，它能够在各种底层事务管理技术，例如JTA或者JDBC事务提供一个一致的编程模型。\nSpring提供了一个用标准Java语言编写的AOP框架，它给POJOs提供了声明式的事务管理和其他企业事务--如果你需要--还能实现你自己的\naspects。这个框架足够强大，使得应用程序能够抛开EJB的复杂性，同时享受着和传统EJB相关的关键服务。Spring还提供了可以和IoC容器\n集成的强大而灵活的MVC Web框架。','402881ce1757a480011757a590e20001'),('402881ce17585bb301175892ea930001','Struts','http://www.struts.apache.org','<b>&nbsp;dhdhdhZ(0)</b>','402881ce1753a480011753afc0740004'),('f4ccc76d1772969b011773aa3e6d0001','iBates','http://ibats.apache.org','&nbsp;一个不错的持久化框架<br>','f4ccd38117699653011769b8e14f0001');
UNLOCK TABLES;
/*!40000 ALTER TABLE `sys_opensource` ENABLE KEYS */;

--
-- Table structure for table `sys_permission`
--

DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` varchar(32) NOT NULL,
  `name` varchar(50) default NULL,
  `alaisName` varchar(100) NOT NULL default '',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

--
-- Dumping data for table `sys_permission`
--


/*!40000 ALTER TABLE `sys_permission` DISABLE KEYS */;
LOCK TABLES `sys_permission` WRITE;
INSERT INTO `sys_permission` VALUES ('402881ce182058e401182080363f0001','后台登陆','AUTH_402881ce182058e401182080363f0001'),('402881ce18214176011821a3d2230001','denglu','AUTH_402881ce18214176011821a3d2230001'),('ff8080811818894801181895cca70002','用户管理','AUTH_ff8080811818894801181895cca70002'),('ff8080811818894801181896d88e0003','角色管理',''),('ff808081181889480118189706720004','资源管理','');
UNLOCK TABLES;
/*!40000 ALTER TABLE `sys_permission` ENABLE KEYS */;

--
-- Table structure for table `sys_permission_resource`
--

DROP TABLE IF EXISTS `sys_permission_resource`;
CREATE TABLE `sys_permission_resource` (
  `permission_ID` varchar(32) NOT NULL default '',
  `resource_id` varchar(32) NOT NULL default '',
  PRIMARY KEY  (`permission_ID`,`resource_id`),
  KEY `FK_Sys_Permission_Resource_2` (`resource_id`),
  CONSTRAINT `FK_Sys_Permission_Resource_1` FOREIGN KEY (`permission_ID`) REFERENCES `sys_permission` (`id`),
  CONSTRAINT `FK_Sys_Permission_Resource_2` FOREIGN KEY (`resource_id`) REFERENCES `sys_resource` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

--
-- Dumping data for table `sys_permission_resource`
--


/*!40000 ALTER TABLE `sys_permission_resource` DISABLE KEYS */;
LOCK TABLES `sys_permission_resource` WRITE;
INSERT INTO `sys_permission_resource` VALUES ('402881ce182058e401182080363f0001','402881ce17d945a10117d9496d390002'),('402881ce18214176011821a3d2230001','402881ce17d945a10117d9496d390002');
UNLOCK TABLES;
/*!40000 ALTER TABLE `sys_permission_resource` ENABLE KEYS */;

--
-- Table structure for table `sys_portal`
--

DROP TABLE IF EXISTS `sys_portal`;
CREATE TABLE `sys_portal` (
  `id` varchar(32) NOT NULL default '',
  `name` varchar(50) NOT NULL default '',
  `portalContainer_id` varchar(32) NOT NULL default '',
  `portalColumnTemplate_id` varchar(32) NOT NULL default '',
  `createTime` timestamp NOT NULL default '0000-00-00 00:00:00',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

--
-- Dumping data for table `sys_portal`
--


/*!40000 ALTER TABLE `sys_portal` DISABLE KEYS */;
LOCK TABLES `sys_portal` WRITE;
INSERT INTO `sys_portal` VALUES ('40288139190938d40119093961b70002','我的首页','f4ccc5b718f936c40118fa0872580003','f4ccc5b718fa52e70118fa8128db0008','2008-04-01 01:02:45'),('4028815b1a33a27a011a33dd80a10009','我的首页','4028815b1a33a27a011a33dd80a2000a','f4ccc5b718fa52e70118fa7fda720005','2008-05-29 00:48:51'),('4028815b1a33a27a011a33efa390002c','首页','4028815b1a33a27a011a33efa390002d','4028810318f4e9f10118f4ec9b1b0002','2008-05-29 01:08:40'),('4028817121814c4a0121814eaaf00003','分类导航','f4ccc5b718f936c40118fa0872580003','f4ccc5b718fa52e70118fa8128db0008','2009-05-27 01:02:44'),('4028817121814c4a0121814ef8b10007','RSS订阅','f4ccc5b718f936c40118fa0872580003','f4ccc5b718fa52e70118fa8128db0008','2009-05-27 01:03:04'),('402881c3193c713801193c7364070002','首页','402881c3193c713801193c7364070003','f4ccc5b718fa52e70118fa8128db0008','2008-04-10 23:46:45'),('402881e51c16d3e0011c16d7506f000a','我的博客','402881c3193c713801193c7364070003','f4ccc5b718fa52e70118fa8128db0008','2008-08-30 19:38:41'),('402881e51c16d3e0011c16d76a8d000e','常用导航','402881c3193c713801193c7364070003','f4ccc5b718fa52e70118fa7fda720005','2008-08-30 19:38:48'),('402881e51c16d3e0011c16d783560012','RSS订阅','402881c3193c713801193c7364070003','f4ccc5b718fa52e70118fa7fda720005','2008-08-30 19:38:54');
UNLOCK TABLES;
/*!40000 ALTER TABLE `sys_portal` ENABLE KEYS */;

--
-- Table structure for table `sys_portal_column`
--

DROP TABLE IF EXISTS `sys_portal_column`;
CREATE TABLE `sys_portal_column` (
  `id` varchar(32) NOT NULL default '',
  `name` varchar(45) NOT NULL default '',
  `icon` varchar(255) default NULL,
  `iconCls` varchar(255) default NULL,
  `portal_id` varchar(32) NOT NULL default '',
  `singleColumnScale` varchar(50) NOT NULL default '',
  `createTime` timestamp NOT NULL default '0000-00-00 00:00:00',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

--
-- Dumping data for table `sys_portal_column`
--


/*!40000 ALTER TABLE `sys_portal_column` DISABLE KEYS */;
LOCK TABLES `sys_portal_column` WRITE;
INSERT INTO `sys_portal_column` VALUES ('40288139190938d40119093961b70001','首页_0',NULL,NULL,'40288139190938d40119093961b70002','67','2008-04-01 01:02:45'),('4028815b1a33a27a011a33dd80a10008','首页_0',NULL,NULL,'4028815b1a33a27a011a33dd80a10009','33','2008-05-29 00:48:51'),('4028815b1a33a27a011a33dd80ae000b','首页_1',NULL,NULL,'4028815b1a33a27a011a33dd80a10009','67','2008-05-29 00:48:51'),('4028815b1a33a27a011a33efa390002b','首页_0',NULL,NULL,'4028815b1a33a27a011a33efa390002c','33','2008-05-29 01:08:40'),('4028815b1a33a27a011a33efa3a4002e','首页_1',NULL,NULL,'4028815b1a33a27a011a33efa390002c','33','2008-05-29 01:08:40'),('4028815b1a33a27a011a33efa3b1002f','首页_2',NULL,NULL,'4028815b1a33a27a011a33efa390002c','33','2008-05-29 01:08:40'),('4028817121814c4a0121814eaaf00002','棣栭〉_0',NULL,NULL,'4028817121814c4a0121814eaaf00003','67','2009-05-27 01:02:44'),('4028817121814c4a0121814eab230004','棣栭〉_1',NULL,NULL,'4028817121814c4a0121814eaaf00003','33','2009-05-27 01:02:45'),('4028817121814c4a0121814ef8b10006','棣栭〉_0',NULL,NULL,'4028817121814c4a0121814ef8b10007','67','2009-05-27 01:03:04'),('4028817121814c4a0121814ef8dd0008','棣栭〉_1',NULL,NULL,'4028817121814c4a0121814ef8b10007','33','2009-05-27 01:03:04'),('402881c3193c713801193c7364070001','首页_0',NULL,NULL,'402881c3193c713801193c7364070002','67','2008-04-10 23:46:45'),('402881c3193c713801193c7364a30004','首页_1',NULL,NULL,'402881c3193c713801193c7364070002','33','2008-04-10 23:46:45'),('402881dd19d6a8730119d6b136ed0001','首页_1',NULL,NULL,'40288139190938d40119093961b70002','33','2008-05-11 06:35:48'),('402881e51c16d3e0011c16d7506f0009','首页_0',NULL,NULL,'402881e51c16d3e0011c16d7506f000a','67','2008-08-30 19:38:41'),('402881e51c16d3e0011c16d7507a000b','首页_1',NULL,NULL,'402881e51c16d3e0011c16d7506f000a','33','2008-08-30 19:38:41'),('402881e51c16d3e0011c16d76a8c000d','首页_0',NULL,NULL,'402881e51c16d3e0011c16d76a8d000e','33','2008-08-30 19:38:48'),('402881e51c16d3e0011c16d76ab0000f','首页_1',NULL,NULL,'402881e51c16d3e0011c16d76a8d000e','67','2008-08-30 19:38:48'),('402881e51c16d3e0011c16d783560011','首页_0',NULL,NULL,'402881e51c16d3e0011c16d783560012','33','2008-08-30 19:38:54'),('402881e51c16d3e0011c16d7838b0013','首页_1',NULL,NULL,'402881e51c16d3e0011c16d783560012','67','2008-08-30 19:38:54');
UNLOCK TABLES;
/*!40000 ALTER TABLE `sys_portal_column` ENABLE KEYS */;

--
-- Table structure for table `sys_portal_column_template`
--

DROP TABLE IF EXISTS `sys_portal_column_template`;
CREATE TABLE `sys_portal_column_template` (
  `id` varchar(32) NOT NULL default '',
  `name` varchar(45) NOT NULL default '',
  `imageSrc` varchar(255) NOT NULL default '',
  `columnScale` varchar(255) NOT NULL default '',
  `createTime` timestamp NOT NULL default '0000-00-00 00:00:00',
  `marker` varchar(45) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

--
-- Dumping data for table `sys_portal_column_template`
--


/*!40000 ALTER TABLE `sys_portal_column_template` DISABLE KEYS */;
LOCK TABLES `sys_portal_column_template` WRITE;
INSERT INTO `sys_portal_column_template` VALUES ('4028810318f4e9f10118f4ec9b1b0002','经典版式','images/portal/columnStyle/colStyle_33_33_33.gif','33:33:33','2008-03-28 02:26:29','default'),('f4ccc5b718fa52e70118fa7e0a2a0001','25:25:25:25','images/portal/columnStyle/colStyle_25_25_25_25.gif','25:25:25:25','2008-03-29 12:23:26',NULL),('f4ccc5b718fa52e70118fa7e9b980002','25:25:50','images/portal/columnStyle/colStyle_25_25_50.gif','25:25:50','2008-03-29 12:24:04',NULL),('f4ccc5b718fa52e70118fa7f17120003','25:50:25','images/portal/columnStyle/colStyle_25_50_25.gif','25:50:25','2008-03-29 12:24:35',NULL),('f4ccc5b718fa52e70118fa7f70890004','25:75','images/portal/columnStyle/colStyle_25_75.gif','25:75','2008-03-29 12:24:58',NULL),('f4ccc5b718fa52e70118fa7fda720005','33:67','images/portal/columnStyle/colStyle_33_67.gif','33:67','2008-03-29 12:25:25',NULL),('f4ccc5b718fa52e70118fa804cff0006','50:25:25','images/portal/columnStyle/colStyle_50_25_25.gif','50:25:25','2008-03-29 12:25:55',NULL),('f4ccc5b718fa52e70118fa80acd00007','50:50','images/portal/columnStyle/colStyle_50_50.gif','50:50','2008-03-29 12:26:19',NULL),('f4ccc5b718fa52e70118fa8128db0008','67:33','images/portal/columnStyle/colStyle_67_33.gif','67:33','2008-03-29 12:26:51',NULL),('f4ccc5b718fa52e70118fa818d1c0009','75:25','images/portal/columnStyle/colStyle_75_25.gif','75:25','2008-03-29 12:27:17',NULL),('f4ccc5b718fa52e70118fa821890000a','100','images/portal/columnStyle/colStyle_100.gif','100','2008-03-29 12:27:52',NULL);
UNLOCK TABLES;
/*!40000 ALTER TABLE `sys_portal_column_template` ENABLE KEYS */;

--
-- Table structure for table `sys_portal_container`
--

DROP TABLE IF EXISTS `sys_portal_container`;
CREATE TABLE `sys_portal_container` (
  `id` varchar(32) NOT NULL default '',
  `name` varchar(45) NOT NULL default '',
  `user_id` varchar(32) NOT NULL default '',
  `portalStyle_id` varchar(32) NOT NULL default '',
  `createTime` timestamp NOT NULL default '0000-00-00 00:00:00',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

--
-- Dumping data for table `sys_portal_container`
--


/*!40000 ALTER TABLE `sys_portal_container` DISABLE KEYS */;
LOCK TABLES `sys_portal_container` WRITE;
INSERT INTO `sys_portal_container` VALUES ('4028815b1a33a27a011a33dd80a2000a','null-首页','4028815b1a33a27a011a33dd4d5a0007','4028810318f4e9f10118f4ec10a40001','2008-05-29 00:48:51'),('4028815b1a33a27a011a33efa390002d','null-首页','4028815b1a33a27a011a33ee311b002a','4028810318f4e9f10118f4ec10a40001','2008-05-29 01:08:40'),('402881c3193c713801193c7364070003','null-首页','402881ce17d98c510117d9926a6b0001','4028810318f4e9f10118f4ec10a40001','2008-04-10 23:46:45'),('f4ccc5b718f936c40118fa0872580003','null-首页','999999999','4028810318f4e9f10118f4ec10a40001','2008-03-29 10:15:00');
UNLOCK TABLES;
/*!40000 ALTER TABLE `sys_portal_container` ENABLE KEYS */;

--
-- Table structure for table `sys_portal_style`
--

DROP TABLE IF EXISTS `sys_portal_style`;
CREATE TABLE `sys_portal_style` (
  `id` varchar(32) NOT NULL default '',
  `name` varchar(45) NOT NULL default '',
  `createTime` timestamp NOT NULL default '0000-00-00 00:00:00',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

--
-- Dumping data for table `sys_portal_style`
--


/*!40000 ALTER TABLE `sys_portal_style` DISABLE KEYS */;
LOCK TABLES `sys_portal_style` WRITE;
INSERT INTO `sys_portal_style` VALUES ('4028810318f4e9f10118f4ec10a40001','经典样式','2008-03-28 02:25:54');
UNLOCK TABLES;
/*!40000 ALTER TABLE `sys_portal_style` ENABLE KEYS */;

--
-- Table structure for table `sys_portlet`
--

DROP TABLE IF EXISTS `sys_portlet`;
CREATE TABLE `sys_portlet` (
  `id` varchar(32) NOT NULL default '',
  `name` varchar(45) NOT NULL default '',
  `source` mediumtext,
  `createTime` timestamp NOT NULL default '0000-00-00 00:00:00',
  `url` varchar(255) default NULL,
  `imageSrc` varchar(255) default NULL,
  `init` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

--
-- Dumping data for table `sys_portlet`
--


/*!40000 ALTER TABLE `sys_portlet` DISABLE KEYS */;
LOCK TABLES `sys_portlet` WRITE;
INSERT INTO `sys_portlet` VALUES ('402881391927e7e70119280146ce0001','RSS订阅','','2008-04-07 00:29:42','scripts/portlets/RssPortlet.js','/scripts/ext/resources/images/default/tabs/scroll-left.gif','com.faceye.portal.portlet.SinglePortlet'),('4028814a1a7aa744011a7aaa7fb00002','博客点击量月排行榜','','2008-06-11 18:46:11','scripts/portlets/BlogMonthClickOrderList.js','/scripts/ext/resources/images/default/tabs/scroll-left.gif','com.faceye.portal.portlet.SinglePortlet'),('4028814a1a7aa744011a7aab17b70003','博客点击量季排行榜','','2008-06-11 18:46:50','scripts/portlets/BlogQuarterClickOrderList.js','/scripts/ext/resources/images/default/tabs/scroll-left.gif','com.faceye.portal.portlet.SinglePortlet'),('4028814a1a7aa744011a7aabb16a0004','博客点击量年度排行榜','','2008-06-11 18:47:29','scripts/portlets/BlogYearClickOrderList.js','/scripts/ext/resources/images/default/tabs/scroll-left.gif','com.faceye.portal.portlet.SinglePortlet'),('4028814a1a7aa744011a7aac25390005','博客点击量总排行榜','','2008-06-11 18:47:59','scripts/portlets/BlogAllClickOrderList.js','/scripts/ext/resources/images/default/tabs/scroll-left.gif','com.faceye.portal.portlet.SinglePortlet'),('4028814a1a7aa744011a7ac0e0cc000b','最新加入博客排行榜','','2008-06-11 19:10:38','scripts/portlets/BlogNewerList.js','/scripts/ext/resources/images/default/tabs/scroll-left.gif','com.faceye.portal.portlet.SinglePortlet'),('4028814a1a7ac1b7011a7ad90ff90003','最新博客文章排行榜','','2008-06-11 19:37:03','scripts/portlets/BlogNewerArticlePortlets.js','/scripts/ext/resources/images/default/tabs/scroll-left.gif','com.faceye.portal.portlet.SinglePortlet'),('4028815b1a33a27a011a33bd28fe0001','用户注册','','2008-05-29 00:13:32','scripts/portlets/UserRegisterPortlet.js','/scripts/ext/resources/images/default/tabs/scroll-left.gif','com.faceye.portal.portlet.SinglePortlet.init'),('4028817121814c4a01218173c36e0017','Google提供的图片广告','','2009-05-27 01:43:16','scripts/portlets/GooglePictureAd.js','/scripts/ext/resources/images/default/tabs/scroll-left.gif','com.faceye.portal.portlet.SinglePortlet'),('402881b31959ff9701195afb60b3000f','分类导航','','2008-04-16 22:03:52','scripts/portlets/TraditionHomePortlet.js','/scripts/ext/resources/images/default/form/date-trigger.gif','com.faceye.portal.UserSubscribePortlet'),('402881c319324a910119329ae2eb0009','开源项目','','2008-04-09 01:53:41','scripts/portlets/OpenSourcePortlet.js','/scripts/ext/resources/images/default/tabs/scroll-left.gif','com.faceye.portal.portlet.SinglePortlet'),('402881c31954e07d011954e344360001','RSS大全','','2008-04-15 17:39:50','scripts/portlets/RssHomePortlet.js','/scripts/ext/resources/images/default/tabs/scroll-left.gif','com.faceye.portal.portlet.SinglePortlet'),('402881fa1a7556a2011a7558c1120001','博客点击量周排行榜','','2008-06-10 17:58:48','scripts/portlets/BlogWeekClickOrderList.js','/scripts/ext/resources/images/default/tabs/scroll-left.gif','com.faceye.portal.portlet.SinglePortlet'),('f4ccc5ff196b0a1a01196b20cab00004','我的博客','','2008-04-20 09:18:41','scripts/portlets/BlogPortlets.js','/scripts/ext/resources/images/default/tabs/scroll-left.gif','com.faceye.portal.portlet.SinglePortlet'),('f4ccc5ff196b0a1a01196b55f2c00007','博客分类','','2008-04-20 10:16:44','scripts/portlets/BlogArticleCategoryPortlets.js','/scripts/ext/resources/images/default/tabs/scroll-left.gif','com.faceye.portal.portlet.SinglePortlet'),('f4ccd0352182b017012182b5ba340015','Google添加的广告','','2009-05-27 07:34:56','scripts/portlets/GoogleWordsPictureAd.js','/scripts/ext/resources/images/default/tabs/scroll-left.gif','com.faceye.portal.portlet.SinglePortlet');
UNLOCK TABLES;
/*!40000 ALTER TABLE `sys_portlet` ENABLE KEYS */;

--
-- Table structure for table `sys_portletsubscribe`
--

DROP TABLE IF EXISTS `sys_portletsubscribe`;
CREATE TABLE `sys_portletsubscribe` (
  `id` varchar(32) NOT NULL default '',
  `name` varchar(45) default NULL,
  `y` varchar(45) default NULL,
  `x` varchar(45) default NULL,
  `portlet_id` varchar(32) NOT NULL default '',
  `portalColumn_id` varchar(32) NOT NULL default '',
  `createTime` timestamp NOT NULL default '0000-00-00 00:00:00',
  `orderindex` int(10) unsigned default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

--
-- Dumping data for table `sys_portletsubscribe`
--


/*!40000 ALTER TABLE `sys_portletsubscribe` DISABLE KEYS */;
LOCK TABLES `sys_portletsubscribe` WRITE;
INSERT INTO `sys_portletsubscribe` VALUES ('4028815b1a33a27a011a33ddc61d000e','博客分类',NULL,NULL,'f4ccc5ff196b0a1a01196b55f2c00007','4028815b1a33a27a011a33dd80a10008','2008-05-29 00:49:09',0),('4028815b1a33a27a011a33ddf1b00011','我的博客',NULL,NULL,'f4ccc5ff196b0a1a01196b20cab00004','4028815b1a33a27a011a33dd80ae000b','2008-05-29 00:49:20',0),('4028815b1a33a27a011a33ddfc200012','RSS大全',NULL,NULL,'402881c31954e07d011954e344360001','4028815b1a33a27a011a33dd80ae000b','2008-05-29 00:49:23',1),('4028817121814c4a0121814fbd65000c','博客分类',NULL,NULL,'f4ccc5ff196b0a1a01196b55f2c00007','402881dd19d6a8730119d6b136ed0001','2009-05-27 01:03:54',1),('4028817121814c4a01218150ac57000d','分类导航',NULL,NULL,'402881b31959ff9701195afb60b3000f','4028817121814c4a0121814eaaf00002','2009-05-27 01:04:56',0),('4028817121814c4a012181525ee10012','RSS大全',NULL,NULL,'402881c31954e07d011954e344360001','4028817121814c4a0121814ef8b10006','2009-05-27 01:06:47',0),('402881e51c16d3e0011c16d9fa570020','分类导航',NULL,NULL,'402881b31959ff9701195afb60b3000f','402881e51c16d3e0011c16d76ab0000f','2008-08-30 19:41:36',0),('402881e51c16d3e0011c16daad2d0022','RSS订阅',NULL,NULL,'402881391927e7e70119280146ce0001','402881e51c16d3e0011c16d7838b0013','2008-08-30 19:42:21',0),('402881e52172aabb012172abde510005','博客点击量总排行榜',NULL,NULL,'4028814a1a7aa744011a7aac25390005','402881dd19d6a8730119d6b136ed0001','2009-05-24 04:50:14',2),('f4ccc8f3218a3edd01218a448d780002','Google提供的图片广告',NULL,NULL,'4028817121814c4a01218173c36e0017','4028817121814c4a0121814ef8dd0008','2009-05-28 18:48:17',0),('f4ccc9ad218c872401218c8df0880002','最新博客文章排行榜',NULL,NULL,'4028814a1a7ac1b7011a7ad90ff90003','40288139190938d40119093961b70001','2009-05-29 05:27:40',0),('f4ccc9ad218c872401218c8e21700004','最新加入博客排行榜',NULL,NULL,'4028814a1a7aa744011a7ac0e0cc000b','402881dd19d6a8730119d6b136ed0001','2009-05-29 05:27:53',3),('f4ccc9ad218cff8401218d16c3230005','我的博客',NULL,NULL,'f4ccc5ff196b0a1a01196b20cab00004','402881c3193c713801193c7364070001','2009-05-29 07:57:07',0),('f4ccc9ad218cff8401218d16d48c0006','博客分类',NULL,NULL,'f4ccc5ff196b0a1a01196b55f2c00007','402881c3193c713801193c7364a30004','2009-05-29 07:57:12',0),('f4ccc9ad218cff8401218d17834b0007','我的博客',NULL,NULL,'f4ccc5ff196b0a1a01196b20cab00004','402881e51c16d3e0011c16d7506f0009','2009-05-29 07:57:56',0),('f4ccc9ad218cff8401218d1793700008','博客分类',NULL,NULL,'f4ccc5ff196b0a1a01196b55f2c00007','402881e51c16d3e0011c16d7507a000b','2009-05-29 07:58:01',1),('f4ccd03521827e0d0121828e5c970003','Google提供的图片广告',NULL,NULL,'4028817121814c4a01218173c36e0017','402881dd19d6a8730119d6b136ed0001','2009-05-27 06:51:56',0),('f4ccd03521827e0d0121828ecbc20005','Google提供的图片广告',NULL,NULL,'4028817121814c4a01218173c36e0017','4028817121814c4a0121814eab230004','2009-05-27 06:52:24',0),('f4ccd03521827e0d01218295abd40007','Google提供的图片广告',NULL,NULL,'4028817121814c4a01218173c36e0017','402881c3193c713801193c7364a30004','2009-05-27 06:59:55',1),('f4ccd0352182b017012182b61aec0016','Google添加的广告',NULL,NULL,'f4ccd0352182b017012182b5ba340015','402881e51c16d3e0011c16d76a8c000d','2009-05-27 07:35:21',0),('f4ccd0352182b7fe012182c2dcda0003','Google提供的图片广告',NULL,NULL,'4028817121814c4a01218173c36e0017','402881e51c16d3e0011c16d7507a000b','2009-05-27 07:49:17',0),('f4ccd0352182b7fe012182c3765a0004','Google添加的广告',NULL,NULL,'f4ccd0352182b017012182b5ba340015','402881e51c16d3e0011c16d783560011','2009-05-27 07:49:56',0);
UNLOCK TABLES;
/*!40000 ALTER TABLE `sys_portletsubscribe` ENABLE KEYS */;

--
-- Table structure for table `sys_property`
--

DROP TABLE IF EXISTS `sys_property`;
CREATE TABLE `sys_property` (
  `id` varchar(32) NOT NULL,
  `name` varchar(50) default NULL,
  `cloumnName` varchar(255) default NULL,
  `propertyName` varchar(255) default NULL,
  `isShow` bit(1) default NULL,
  `orderIndexOfInput` int(11) default NULL,
  `domain_id` varchar(32) default NULL,
  `htmlType_id` varchar(32) default NULL,
  `dataType_id` varchar(32) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK6EC36F27B5E28529` (`domain_id`),
  KEY `FK6EC36F27FA19A589` (`dataType_id`),
  KEY `FK6EC36F275931E9E9` (`htmlType_id`),
  CONSTRAINT `FK6EC36F275931E9E9` FOREIGN KEY (`htmlType_id`) REFERENCES `sys_html_type` (`id`),
  CONSTRAINT `FK6EC36F27B5E28529` FOREIGN KEY (`domain_id`) REFERENCES `sys_domain` (`id`),
  CONSTRAINT `FK6EC36F27FA19A589` FOREIGN KEY (`dataType_id`) REFERENCES `sys_data_type` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

--
-- Dumping data for table `sys_property`
--


/*!40000 ALTER TABLE `sys_property` DISABLE KEYS */;
LOCK TABLES `sys_property` WRITE;
INSERT INTO `sys_property` VALUES ('1','Name','name','name','',1,'1','1','1'),('2','Domain Name','domainName\n','domainName','',2,'1','1','1'),('3','Table Name','tableName','tableName','',3,'1','1','1');
UNLOCK TABLES;
/*!40000 ALTER TABLE `sys_property` ENABLE KEYS */;

--
-- Table structure for table `sys_property_validator`
--

DROP TABLE IF EXISTS `sys_property_validator`;
CREATE TABLE `sys_property_validator` (
  `Property_ID` varchar(32) NOT NULL,
  `validator_ID` varchar(32) NOT NULL,
  PRIMARY KEY  (`validator_ID`,`Property_ID`),
  KEY `FKEDB2DC3A938ADFC9` (`Property_ID`),
  KEY `FKEDB2DC3A53FB2E05` (`validator_ID`),
  CONSTRAINT `FKEDB2DC3A53FB2E05` FOREIGN KEY (`validator_ID`) REFERENCES `sys_validator_type` (`id`),
  CONSTRAINT `FKEDB2DC3A938ADFC9` FOREIGN KEY (`Property_ID`) REFERENCES `sys_property` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

--
-- Dumping data for table `sys_property_validator`
--


/*!40000 ALTER TABLE `sys_property_validator` DISABLE KEYS */;
LOCK TABLES `sys_property_validator` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `sys_property_validator` ENABLE KEYS */;

--
-- Table structure for table `sys_query_type`
--

DROP TABLE IF EXISTS `sys_query_type`;
CREATE TABLE `sys_query_type` (
  `id` varchar(32) NOT NULL,
  `name` varchar(100) default NULL,
  `typeName` varchar(100) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

--
-- Dumping data for table `sys_query_type`
--


/*!40000 ALTER TABLE `sys_query_type` DISABLE KEYS */;
LOCK TABLES `sys_query_type` WRITE;
INSERT INTO `sys_query_type` VALUES ('1','By HQL','byHQL');
UNLOCK TABLES;
/*!40000 ALTER TABLE `sys_query_type` ENABLE KEYS */;

--
-- Table structure for table `sys_resource`
--

DROP TABLE IF EXISTS `sys_resource`;
CREATE TABLE `sys_resource` (
  `id` varchar(32) NOT NULL,
  `name` varchar(50) default NULL,
  `resourceType` varchar(50) default NULL,
  `resourceStr` varchar(250) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

--
-- Dumping data for table `sys_resource`
--


/*!40000 ALTER TABLE `sys_resource` DISABLE KEYS */;
LOCK TABLES `sys_resource` WRITE;
INSERT INTO `sys_resource` VALUES ('4028810318ef16040118ef1ca2220001','样式管理_URL','URL','portalStyleAction.do?method=index'),('4028810318ef16040118ef1ca26b0002','样式管理_Action','URL','portalStyleAction.do?method=list&forward=list'),('4028810318ef3d1e0118ef40367d0001','版式管理_URL','URL','portalColumnTemplateAction.do?method=index'),('4028810318ef3d1e0118ef4036d20002','版式管理_Action','URL','portalColumnTemplateAction.do?method=list&forward=list'),('4028810318ef614a0118ef744eb60001','Portlet管理_URL','URL','portletAction.do?method=index'),('4028810318ef614a0118ef744f620002','Portlet管理_Action','URL','portletAction.do?method=list&forward=list'),('402881ce17d945a10117d9496d390002','后台首页','URL','/default.do?method=forward&forward=default'),('402881ce1836385e0118364240060001','资源管理_URL','URL','resourceAction.do?method=indext'),('402881ce1836385e011836429eba0002','资源管理_Action','URL','resourceAction.do?method=list&forward=list'),('402881ce1836385e011836432cd70003','栏目维护_URL','URL','columnAction.do?method=index'),('402881ce1836385e0118364337e00004','栏目维护_Action','URL','columnAction.do?method=list&forward=list'),('402881ce1836474a01183649baaa0002','权限管理_URL','URL','permissionAction.do?method=index'),('402881ce1836474a01183649baba0003','权限管理_Action','URL','permissionAction.do?method=list&forward=list'),('402881ce1839fddd011839feb6160001','网站分类_Action','URL','categoryAction.do?method=forward&forward=list'),('402881ce1839fddd011839feb6fb0002','角色管理_URL','URL','roleAction.do?method=index'),('402881ce1839fddd011839feb7010003','角色管理_Action','URL','roleAction.do?method=list&forward=list'),('402881ce1839fddd011839feb7590004','用户管理_URL','URL','userAction.do?method=index'),('402881ce1839fddd011839feb7610005','用户管理_Action','URL','userAction.do?method=list&forward=list'),('402881ce1839fddd011839feb7b50006','节点维护_URL','URL','treeAction.do?method=index'),('402881ce1839fddd011839feb7bf0007','节点维护_Action','URL','treeAction.do?method=list&forward=list');
UNLOCK TABLES;
/*!40000 ALTER TABLE `sys_resource` ENABLE KEYS */;

--
-- Table structure for table `sys_role`
--

DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` varchar(32) NOT NULL,
  `name` varchar(50) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

--
-- Dumping data for table `sys_role`
--


/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;
LOCK TABLES `sys_role` WRITE;
INSERT INTO `sys_role` VALUES ('1','管理员'),('2','注册用户'),('402881651810cf3a0118115d0eea0001','访客'),('402881651810cf3a0118115f266c0002','测试角色'),('402881651810cf3a0118115f4ca60003','测试前台'),('402881651810cf3a0118115f671e0004','测试后台'),('402881651810cf3a0118115f9e0a0005','SQL脚本测试'),('402881651810cf3a0118115fd3b80006','JavaScripts测试');
UNLOCK TABLES;
/*!40000 ALTER TABLE `sys_role` ENABLE KEYS */;

--
-- Table structure for table `sys_role_permission`
--

DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
  `PERMISSION_ID` varchar(32) NOT NULL,
  `ROLE_ID` varchar(32) NOT NULL,
  PRIMARY KEY  (`ROLE_ID`,`PERMISSION_ID`),
  KEY `FK7D9867A6CEA226A6` (`PERMISSION_ID`),
  KEY `FK7D9867A694ED3A46` (`ROLE_ID`),
  CONSTRAINT `FK7D9867A694ED3A46` FOREIGN KEY (`ROLE_ID`) REFERENCES `sys_role` (`id`),
  CONSTRAINT `FK7D9867A6CEA226A6` FOREIGN KEY (`PERMISSION_ID`) REFERENCES `sys_permission` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

--
-- Dumping data for table `sys_role_permission`
--


/*!40000 ALTER TABLE `sys_role_permission` DISABLE KEYS */;
LOCK TABLES `sys_role_permission` WRITE;
INSERT INTO `sys_role_permission` VALUES ('402881ce182058e401182080363f0001','1');
UNLOCK TABLES;
/*!40000 ALTER TABLE `sys_role_permission` ENABLE KEYS */;

--
-- Table structure for table `sys_role_tree`
--

DROP TABLE IF EXISTS `sys_role_tree`;
CREATE TABLE `sys_role_tree` (
  `roleId` varchar(32) NOT NULL default '',
  `treeId` varchar(32) NOT NULL default '',
  PRIMARY KEY  (`roleId`,`treeId`),
  KEY `FK_sys_role_tree_2` (`treeId`),
  CONSTRAINT `FK_sys_role_tree_1` FOREIGN KEY (`roleId`) REFERENCES `sys_role` (`id`),
  CONSTRAINT `FK_sys_role_tree_2` FOREIGN KEY (`treeId`) REFERENCES `sys_tree` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

--
-- Dumping data for table `sys_role_tree`
--


/*!40000 ALTER TABLE `sys_role_tree` DISABLE KEYS */;
LOCK TABLES `sys_role_tree` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `sys_role_tree` ENABLE KEYS */;

--
-- Table structure for table `sys_tradition`
--

DROP TABLE IF EXISTS `sys_tradition`;
CREATE TABLE `sys_tradition` (
  `id` varchar(32) NOT NULL default '',
  `name` varchar(255) NOT NULL default '',
  `url` varchar(255) NOT NULL default '',
  `description` varchar(255) default NULL,
  `createDate` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `column_id` varchar(32) NOT NULL default '',
  `category_id` varchar(32) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

--
-- Dumping data for table `sys_tradition`
--


/*!40000 ALTER TABLE `sys_tradition` DISABLE KEYS */;
LOCK TABLES `sys_tradition` WRITE;
INSERT INTO `sys_tradition` VALUES ('402881ce1766e4140117673407480001',' 搜狐','http://www.sohu.com','中国最大的','2008-01-10 16:00:00','402881ce1752f824011752fd96340002','402881b31764776f0117649634060005'),('402881ce1767434a0117674a3cb80001','网易','http://www.163.com','中国最大的新闻门户','2008-01-11 05:19:54','402881ce1752f824011752fd96340002','402881b31764776f0117649634060005'),('402881ce179a640701179aaa7c960001','百度','http://www.baidu.com','世界上最大的中文搜索引擎','2008-01-21 04:45:40','402881ce1752f824011752fd96340002','402881b31764776f0117649634060005'),('402881ce179a640701179aab3d200002','新浪','http://www.sina.com','','2008-01-21 04:46:29','402881ce1752f824011752fd96340002','402881b31764776f0117649634060005'),('402881ce179a640701179aac1f5b0003','腾讯QQ','http://www.qq.com','','2008-01-21 04:47:27','402881ce1752f824011752fd96340002','402881b31764776f0117649634060005'),('402881ce179a640701179acd2c9b0004','中央电视台','http://www.cctv.com','','2008-01-21 05:23:33','402881ce1752f824011752fd96340002','402881b31764776f0117649634060005'),('402881ce179a640701179acf2d880005','人民网','http://www.people.com.cn/','','2008-01-21 05:25:44','402881ce1752f824011752fd96340002','402881b31764776f0117649634060005'),('402881ce179a640701179ad05f980006','新华网','http://www.xinhuanet.com','','2008-01-21 05:27:02','402881ce1752f824011752fd96340002','402881b31764776f0117649634060005'),('402881ce179a640701179ad1ed4c0007','凤凰网','http://www.ifeng.com','','2008-01-21 05:28:44','402881ce1752f824011752fd96340002','402881b31764776f0117649634060005'),('402881ce179a640701179ad29d0f0008','MSN中文','http://cn.msn.com','','2008-01-21 05:29:29','402881ce1752f824011752fd96340002','402881b31764776f0117649634060005'),('402881ce179a640701179ad48e930009','雅虎中国','http://cn.yahoo.com/?id=40004&pid=419348_1006&f=B419348_5','','2008-01-21 05:31:36','402881ce1752f824011752fd96340002','402881b31764776f0117649634060005'),('402881ce179a640701179ad4fb84000a','Google','http://www.google.com','','2008-01-21 05:32:04','402881ce1752f824011752fd96340002','402881b31764776f0117649634060005'),('402881ce179a640701179ad58090000b','中华网','http://www.china.com','','2008-01-21 05:32:38','402881ce1752f824011752fd96340002','402881b31764776f0117649634060005'),('402881ce179a640701179ad5dbdc000c','中国人','http://www.chinaren.com','','2008-01-21 05:33:02','402881ce1752f824011752fd96340002','402881b31764776f0117649634060005'),('402881ce179a640701179ad67562000d','太平洋电脑网','http://www.pconline.com.cn','','2008-01-21 05:33:41','402881ce1752f824011752fd96340002','402881b31764776f0117649634060005'),('402881ce179a640701179ad6cc1d000e','中国万网','http://www.net.cn','','2008-01-21 05:34:03','402881ce1752f824011752fd96340002','402881b31764776f0117649634060005'),('402881ce179a640701179ad7816d000f','中华英才网','http://www.chinahr.com','','2008-01-21 05:34:50','402881ce1752f824011752fd96340002','402881b31764776f0117649634060005'),('402881ce179a640701179ad7fbd10010','中国人力资源热线','http://www.cjol.com','','2008-01-21 05:35:21','402881ce1752f824011752fd96340002','402881b31764776f0117649634060005'),('402881ce179a640701179ad870240011','中国政府网','http://www.gov.cn','','2008-01-21 05:35:51','402881ce1752f824011752fd96340002','402881b31764776f0117649634060005'),('402881ce179a640701179ad946230012','搜房网','http://www.soufun.com','','2008-01-21 05:36:46','402881ce1752f824011752fd96340002','402881b31764776f0117649634060005'),('402881ce179c528301179cdb93880006','搜索博客','http://blog.sohu.com','','2008-01-21 14:58:31','402881ce1752f824011752fd96340002','402881ce179c528301179cd5c3850001'),('402881ce179c528301179cdbfb8c0007','新浪博客','http://blog.sina.com','','2008-01-21 14:58:58','402881ce1752f824011752fd96340002','402881ce179c528301179cd5c3850001'),('402881ce179f0c2b01179f6e99910001','优酷','http://www.youku.com','','2008-01-22 02:58:21','402881ce1752f824011752fd96340002','402881ce179c528301179cd62eb80002'),('402881ce179f0c2b01179f6eea570002','偶偶','http://www.ouou.com','','2008-01-22 02:58:41','402881ce1752f824011752fd96340002','402881ce179c528301179cd62eb80002'),('402881ce17a01e1d0117a021d1a00001','百度Mp3','http://mp3.baidu.com','','2008-01-22 06:14:06','402881ce1752f824011752fd96340002','402881ce179c528301179cd66b3e0003'),('402881ce17a01e1d0117a02d488e0002','51交友','http://www.51.com','','2008-01-22 06:26:37','402881ce1752f824011752fd96340002','402881ce179c528301179cd6b0e50004'),('402881ce17a01e1d0117a02db33b0003','QQ交友','http://www.qq.com','','2008-01-22 06:27:05','402881ce1752f824011752fd96340002','402881ce179c528301179cd6b0e50004'),('402881ce17a01e1d0117a02e0f230004','JavaEye','http://www.javaeye.com','','2008-01-22 06:27:28','402881ce1752f824011752fd96340002','402881ce179c528301179cd6dc020005'),('402881ce17a01e1d0117a02e677a0005','CSDN','http://www.csdn.com','','2008-01-22 06:27:51','402881ce1752f824011752fd96340002','402881ce179c528301179cd6dc020005'),('402881ce17a01e1d0117a02ebca80006','IT168','http://www.it168.com','','2008-01-22 06:28:13','402881ce1752f824011752fd96340002','402881ce179c528301179cd6dc020005');
UNLOCK TABLES;
/*!40000 ALTER TABLE `sys_tradition` ENABLE KEYS */;

--
-- Table structure for table `sys_tree`
--

DROP TABLE IF EXISTS `sys_tree`;
CREATE TABLE `sys_tree` (
  `id` varchar(32) NOT NULL,
  `name` varchar(50) default NULL,
  `action` varchar(255) default NULL,
  `url` varchar(255) default NULL,
  `PARENTID` varchar(32) default NULL,
  `domain_id` varchar(32) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FKA51085F088E82B2F` (`PARENTID`),
  KEY `FKA51085F0B5E28529` (`domain_id`),
  CONSTRAINT `FKA51085F088E82B2F` FOREIGN KEY (`PARENTID`) REFERENCES `sys_tree` (`id`),
  CONSTRAINT `FKA51085F0B5E28529` FOREIGN KEY (`domain_id`) REFERENCES `sys_domain` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

--
-- Dumping data for table `sys_tree`
--


/*!40000 ALTER TABLE `sys_tree` DISABLE KEYS */;
LOCK TABLES `sys_tree` WRITE;
INSERT INTO `sys_tree` VALUES ('1','系统管理',NULL,NULL,NULL,NULL),('4028810318ea692b0118ea6df1740001','Portal管理','','','1',NULL),('4028810318ea81ce0118ea838b5b0001','样式管理','portalStyleAction.do?method=list&forward=list','portalStyleAction.do?method=index','4028810318ea692b0118ea6df1740001',NULL),('4028810318ef3d1e0118ef4036dc0003','版式管理','portalColumnTemplateAction.do?method=list&forward=list','portalColumnTemplateAction.do?method=index','4028810318ea692b0118ea6df1740001',NULL),('4028810318ef614a0118ef744f6e0003','Portlet管理','portletAction.do?method=list&forward=list','portletAction.do?method=index','4028810318ea692b0118ea6df1740001',NULL),('402881ce175cdd8b01175d70ea020001','网站分类','categoryAction.do?method=forward&forward=list','categoryAction.do?method=forward&forward=list','1',NULL),('402881ce17d2c6040117d2d51b8d0001','安全管理','','','1',NULL),('402881ce17d2c6040117d2d579910002','角色管理','roleAction.do?method=list&forward=list','roleAction.do?method=index','402881ce17d2c6040117d2d51b8d0001',NULL),('402881ce17d945a10117d946cccb0001','资源管理','resourceAction.do?method=list&forward=list','resourceAction.do?method=indext','402881ce17d2c6040117d2d51b8d0001',NULL),('402881ce17d945a10117d95facdd0003','用户管理','userAction.do?method=list&forward=list','userAction.do?method=index','402881ce17d2c6040117d2d51b8d0001',NULL),('f4ccca51173491fc011734b89e8c0001','栏目维护','columnAction.do?method=list&forward=list','columnAction.do?method=index','1',NULL),('ff808081153fe48e0115407d41d30001','节点维护','treeAction.do?method=list&forward=list','treeAction.do?method=index','1',NULL),('ff808081181889480118188c67020001','权限管理','permissionAction.do?method=list&forward=list','permissionAction.do?method=index','402881ce17d2c6040117d2d51b8d0001',NULL);
UNLOCK TABLES;
/*!40000 ALTER TABLE `sys_tree` ENABLE KEYS */;

--
-- Table structure for table `sys_user`
--

DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` varchar(32) NOT NULL,
  `name` varchar(50) default NULL,
  `password` varchar(64) default NULL,
  `email` varchar(50) default NULL,
  `enabled` char(1) default NULL,
  `accountExpired` char(1) default NULL,
  `accountLocked` char(1) default NULL,
  `credentialsExpired` char(1) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

--
-- Dumping data for table `sys_user`
--


/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
LOCK TABLES `sys_user` WRITE;
INSERT INTO `sys_user` VALUES ('4028815b1a33a27a011a33dd4d5a0007','ecsun','4607e782c4d86fd5364d7e4508bb10d9','ecsun@sohu.com','Y','N','N','N'),('4028815b1a33a27a011a33ee311b002a','test','4607e782c4d86fd5364d7e4508bb10d9','test@faceye.com','Y','N','N','N'),('402881ce17d98c510117d9926a6b0001','admin','21232f297a57a5a743894a0e4a801fc3','admin@sohu.com','Y','1','1','1'),('999999999','guest','21232f297a57a5a743894a0e4a801fc3','guest@faceye.com','Y','N','N','N');
UNLOCK TABLES;
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;

--
-- Table structure for table `sys_user_role`
--

DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `USER_ID` varchar(32) NOT NULL,
  `ROLE_ID` varchar(32) NOT NULL,
  PRIMARY KEY  (`USER_ID`,`ROLE_ID`),
  KEY `FKAABB7D583A17FE26` (`USER_ID`),
  KEY `FKAABB7D5894ED3A46` (`ROLE_ID`),
  CONSTRAINT `User_id_Permission_Role` FOREIGN KEY (`USER_ID`) REFERENCES `sys_user` (`id`),
  CONSTRAINT `user_role_ID` FOREIGN KEY (`ROLE_ID`) REFERENCES `sys_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

--
-- Dumping data for table `sys_user_role`
--


/*!40000 ALTER TABLE `sys_user_role` DISABLE KEYS */;
LOCK TABLES `sys_user_role` WRITE;
INSERT INTO `sys_user_role` VALUES ('4028815b1a33a27a011a33dd4d5a0007','1'),('4028815b1a33a27a011a33ee311b002a','1'),('402881ce17d98c510117d9926a6b0001','1'),('999999999','1');
UNLOCK TABLES;
/*!40000 ALTER TABLE `sys_user_role` ENABLE KEYS */;

--
-- Table structure for table `sys_validator_type`
--

DROP TABLE IF EXISTS `sys_validator_type`;
CREATE TABLE `sys_validator_type` (
  `id` varchar(32) NOT NULL,
  `name` varchar(50) default NULL,
  `functionName` varchar(255) default NULL,
  `validatorMessage` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

--
-- Dumping data for table `sys_validator_type`
--


/*!40000 ALTER TABLE `sys_validator_type` DISABLE KEYS */;
LOCK TABLES `sys_validator_type` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `sys_validator_type` ENABLE KEYS */;

--
-- Table structure for table `user_blog_article`
--

DROP TABLE IF EXISTS `user_blog_article`;
CREATE TABLE `user_blog_article` (
  `articleCategory_id` varchar(32) NOT NULL,
  `createTime` timestamp NULL default NULL on update CURRENT_TIMESTAMP,
  `content` longtext character set gbk,
  `name` varchar(255) character set gbk NOT NULL,
  `id` varchar(32) character set gbk NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user_blog_article`
--


/*!40000 ALTER TABLE `user_blog_article` DISABLE KEYS */;
LOCK TABLES `user_blog_article` WRITE;
INSERT INTO `user_blog_article` VALUES ('402881e52172f100012172f8aea50001','2009-05-24 06:14:59','&nbsp;新写一日志,放上来测上一测<BR>新写一日志,放上来测上一测<BR>新写一日志,放上来测上一测<BR>新写一日志,放上来测上一测<BR>新写一日志,放上来测上一测<BR>新写一日志,放上来测上一测<BR>新写一日志,放上来测上一测<BR>新写一日志,放上来测上一测<BR>新写一日志,放上来测上一测<BR>新写一日志,放上来测上一测','新写一日志,放上来测上一测','402881e52172f100012172f974630004'),('402881e52172f100012173020257001b','2009-05-29 07:47:53','&nbsp;很早就写了这个demo,只是一直没有找到合适的地方放<br>现在找了一台服务器<br>简单的放上来<br>供需要的朋友在线查看<br>速度慢了些，请见谅．<br><br>更多关于本demo的信息<br>请参看：<br>http://papalong.javaeye.com<br><br>或<br>faceye.com<br>','FaceYe Ext portal Online demo','f4ccc9ad218cff8401218d0e50160001'),('402881e52172f100012173020257001b','2009-05-29 07:51:29','&nbsp;下载地址：<br>http://faceye.googlecode.com<br>','FaceYe Ext portal demo 源码下载','f4ccc9ad218cff8401218d11984a0002'),('402881e52172f100012173020257001b','2009-05-29 07:54:22','&nbsp;参考更多关于FaceYe　Ext portal 讨论，请在<a href=\"http://faceye.com\">faceye.com</a>注册用户，参与更多讨论．<br>','FaceYe Ext portal讨论','f4ccc9ad218cff8401218d143bc00003'),('402881e52172f100012173020257001b','2009-05-30 02:25:31','本demo数据库每半小时还原一次．<BR>敬请留意<BR><BR>参与讨论，请在FaceYe官方注册用户<A href=\"http://www.faceye.com\">http://www.faceye.com</A>','FaceYe Ext portal online demo数据库恢复机制','f4ccc9ad218cff8401218d1676db0004');
UNLOCK TABLES;
/*!40000 ALTER TABLE `user_blog_article` ENABLE KEYS */;

--
-- Table structure for table `user_blog_article_category`
--

DROP TABLE IF EXISTS `user_blog_article_category`;
CREATE TABLE `user_blog_article_category` (
  `parent_id` varchar(32) character set utf8 default NULL,
  `portalContainer_id` varchar(32) NOT NULL,
  `nodeOrder` int(11) default NULL,
  `name` varchar(50) NOT NULL,
  `id` varchar(32) NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `portalC_id` (`portalContainer_id`),
  CONSTRAINT `portalC_id` FOREIGN KEY (`portalContainer_id`) REFERENCES `sys_portal_container` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

--
-- Dumping data for table `user_blog_article_category`
--


/*!40000 ALTER TABLE `user_blog_article_category` DISABLE KEYS */;
LOCK TABLES `user_blog_article_category` WRITE;
INSERT INTO `user_blog_article_category` VALUES (NULL,'f4ccc5b718f936c40118fa0872580003',0,'默认分类','402881e52172f100012172f8aea50001'),(NULL,'402881c3193c713801193c7364070003',0,'默认分类','402881e52172f100012173020257001b'),(NULL,'4028815b1a33a27a011a33dd80a2000a',0,'默认分类','402881e5217309f10121730d6b270002');
UNLOCK TABLES;
/*!40000 ALTER TABLE `user_blog_article_category` ENABLE KEYS */;

--
-- Table structure for table `user_blog_article_click_count`
--

DROP TABLE IF EXISTS `user_blog_article_click_count`;
CREATE TABLE `user_blog_article_click_count` (
  `article_id` varchar(32) NOT NULL,
  `user_id` varchar(32) NOT NULL,
  `ip` varchar(150) default NULL,
  `createTime` timestamp NULL default NULL on update CURRENT_TIMESTAMP,
  `name` varchar(100) default NULL,
  `id` varchar(32) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user_blog_article_click_count`
--


/*!40000 ALTER TABLE `user_blog_article_click_count` DISABLE KEYS */;
LOCK TABLES `user_blog_article_click_count` WRITE;
INSERT INTO `user_blog_article_click_count` VALUES ('402881e52172f100012172f974630004','999999999','0:0:0:0:0:0:0:1','2009-05-24 06:23:41',NULL,'402881e52172f100012173016df80015'),('402881e52172f100012172f974630004','999999999','0:0:0:0:0:0:0:1','2009-05-24 06:36:27',NULL,'402881e5217309f10121730d1c380001'),('f4ccc9ad218cff8401218d1676db0004','999999999','0:0:0:0:0:0:0:1','2009-05-29 17:56:12',NULL,'f4ccc67d218f397801218f3b3b640001'),('f4ccc9ad218cff8401218d143bc00003','999999999','0:0:0:0:0:0:0:1','2009-05-29 17:56:28',NULL,'f4ccc67d218f397801218f3b7b150002'),('f4ccc9ad218cff8401218d1676db0004','999999999','0:0:0:0:0:0:0:1','2009-05-29 18:24:07',NULL,'f4ccc67d218f52c301218f54ccae0001');
UNLOCK TABLES;
/*!40000 ALTER TABLE `user_blog_article_click_count` ENABLE KEYS */;

--
-- Table structure for table `user_blog_article_discus`
--

DROP TABLE IF EXISTS `user_blog_article_discus`;
CREATE TABLE `user_blog_article_discus` (
  `user_id` varchar(32) NOT NULL,
  `article_id` varchar(32) NOT NULL,
  `createTime` timestamp NULL default NULL on update CURRENT_TIMESTAMP,
  `content` longtext,
  `name` varchar(254) default NULL,
  `id` varchar(32) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user_blog_article_discus`
--


/*!40000 ALTER TABLE `user_blog_article_discus` DISABLE KEYS */;
LOCK TABLES `user_blog_article_discus` WRITE;
INSERT INTO `user_blog_article_discus` VALUES ('999999999','402881e52172f100012172f974630004','2009-05-24 06:23:47','&nbsp;43234324',NULL,'402881e52172f1000121730181d60016'),('999999999','402881e52172f100012172f974630004','2009-05-24 06:23:50','32434242',NULL,'402881e52172f10001217301901b0017'),('999999999','402881e52172f100012172f974630004','2009-05-24 06:23:53','2342432424',NULL,'402881e52172f100012173019c040018'),('999999999','402881e52172f100012172f974630004','2009-05-24 06:23:56','32432424',NULL,'402881e52172f10001217301a6500019'),('999999999','402881e52172f100012172f974630004','2009-05-24 06:23:58','2342424',NULL,'402881e52172f10001217301b004001a');
UNLOCK TABLES;
/*!40000 ALTER TABLE `user_blog_article_discus` ENABLE KEYS */;

--
-- Table structure for table `user_blog_click_history`
--

DROP TABLE IF EXISTS `user_blog_click_history`;
CREATE TABLE `user_blog_click_history` (
  `PORTALCONTAINER_ID` varchar(32) NOT NULL,
  `USER_ID` varchar(32) NOT NULL,
  `CREATETIME` timestamp NULL default NULL on update CURRENT_TIMESTAMP,
  `IP` varchar(100) default NULL,
  `NAME` varchar(100) default NULL,
  `ID` varchar(32) NOT NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user_blog_click_history`
--


/*!40000 ALTER TABLE `user_blog_click_history` DISABLE KEYS */;
LOCK TABLES `user_blog_click_history` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `user_blog_click_history` ENABLE KEYS */;

--
-- Table structure for table `user_resoruce_category`
--

DROP TABLE IF EXISTS `user_resoruce_category`;
CREATE TABLE `user_resoruce_category` (
  `id` varchar(32) NOT NULL default '',
  `name` varchar(50) NOT NULL default '',
  `description` varchar(250) default NULL,
  `parent_id` varchar(32) default NULL,
  `user_id` varchar(32) NOT NULL default '',
  `nodeOrder` int(10) unsigned NOT NULL default '0',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk COMMENT='InnoDB free: 9216 kB';

--
-- Dumping data for table `user_resoruce_category`
--


/*!40000 ALTER TABLE `user_resoruce_category` DISABLE KEYS */;
LOCK TABLES `user_resoruce_category` WRITE;
INSERT INTO `user_resoruce_category` VALUES ('402881e52172be4d012172c26c210004','Root','create By FaceYe.com',NULL,'402881ce17d98c510117d9926a6b0001',0),('402881e52172f10001217301052e0005','Root','create By FaceYe.com',NULL,'999999999',0),('402881e521734ffb01217350ee5b0001','新闻','','402881e52172be4d012172c26c210004','402881ce17d98c510117d9926a6b0001',1),('402881e5217357f70121735cedda0001','财经','','402881e52172be4d012172c26c210004','402881ce17d98c510117d9926a6b0001',2),('f4ccc9ad218c872401218ca2422f0005','新闻','','402881e52172f10001217301052e0005','999999999',0),('f4ccc9ad218ccde301218cdc6bfe0001','财经','','402881e52172f10001217301052e0005','999999999',0);
UNLOCK TABLES;
/*!40000 ALTER TABLE `user_resoruce_category` ENABLE KEYS */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

