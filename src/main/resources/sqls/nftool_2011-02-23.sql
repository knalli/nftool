# Sequel Pro dump
# Version 2492
# http://code.google.com/p/sequel-pro
#
# Host: localhost (MySQL 5.5.8)
# Database: nftool
# Generation Time: 2011-02-23 09:08:35 +0100
# ************************************************************

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table exercises
# ------------------------------------------------------------

DROP TABLE IF EXISTS `exercises`;

CREATE TABLE `exercises` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

LOCK TABLES `exercises` WRITE;
/*!40000 ALTER TABLE `exercises` DISABLE KEYS */;
INSERT INTO `exercises` (`id`,`version`)
VALUES
	(1,0);

/*!40000 ALTER TABLE `exercises` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table localized_label
# ------------------------------------------------------------

DROP TABLE IF EXISTS `localized_label`;

CREATE TABLE `localized_label` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `attribute_name` varchar(30) NOT NULL,
  `content` varchar(255) NOT NULL,
  `entity_uri` varchar(255) DEFAULT NULL,
  `entity_uri3` varchar(255) NOT NULL,
  `locale` varchar(255) NOT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `indexEntityUri` (`entity_uri3`)
) ENGINE=InnoDB AUTO_INCREMENT=271 DEFAULT CHARSET=utf8;

LOCK TABLES `localized_label` WRITE;
/*!40000 ALTER TABLE `localized_label` DISABLE KEYS */;
INSERT INTO `localized_label` (`id`,`attribute_name`,`content`,`entity_uri`,`entity_uri3`,`locale`,`version`)
VALUES
	(1,'title','CD-Datenbank','Exercise:1','Exercise:1','de',NULL),
	(2,'title','CD-Database','Exercise:1','Exercise:1','en',NULL),
	(3,'description','Bitte sehen Sie sich folgende Aufgabentabelle an. Sie gilt es in die dritte Normalform zu bringen.','Task:1','Exercise:1/Task:1','de',NULL),
	(4,'description','Look at the exercise table. You have to build the third normal form of it.','Task:1','Exercise:1/Task:1','en',NULL),
	(5,'description','Aufgabentabelle','TaskTable:1','Exercise:1/Task:1/TaskTable:1','de',NULL),
	(6,'description','Exercise table','TaskTable:1','Exercise:1/Task:1/TaskTable:1','en',NULL),
	(7,'name','CD_ID','TableColumn:1','Exercise:1/Task:1/TaskTable:1/TableColumn:1','de',NULL),
	(8,'name','CD_ID','TableColumn:1','Exercise:1/Task:1/TaskTable:1/TableColumn:1','en',NULL),
	(9,'name','Album','TableColumn:2','Exercise:1/Task:1/TaskTable:1/TableColumn:2','de',NULL),
	(10,'name','Album','TableColumn:2','Exercise:1/Task:1/TaskTable:1/TableColumn:2','en',NULL),
	(11,'name','Gründungsjahr','TableColumn:3','Exercise:1/Task:1/TaskTable:1/TableColumn:3','de',NULL),
	(12,'name','Founding year','TableColumn:3','Exercise:1/Task:1/TaskTable:1/TableColumn:3','en',NULL),
	(13,'name','Titelliste','TableColumn:4','Exercise:1/Task:1/TaskTable:1/TableColumn:4','de',NULL),
	(14,'name','Track list','TableColumn:4','Exercise:1/Task:1/TaskTable:1/TableColumn:4','en',NULL),
	(16,'content','100','TableRow:1','Exercise:1/Task:1/TaskTable:1/TableRow:1','en',NULL),
	(17,'content','Helge Schneider - Da Humm','TableRow:2','Exercise:1/Task:1/TaskTable:1/TableRow:2','en',NULL),
	(18,'content','1998','TableRow:3','Exercise:1/Task:1/TaskTable:1/TableRow:3','en',NULL),
	(19,'content','F-Dur, Da Humm (Solo), Bonbon aus Wurst','TableRow:4','Exercise:1/Task:1/TaskTable:1/TableRow:4','en',NULL),
	(20,'content','101','TableRow:5','Exercise:1/Task:1/TaskTable:1/TableRow:5','en',NULL),
	(21,'content','Helge Schneider - Es gibt Reis Baby','TableRow:6','Exercise:1/Task:1/TaskTable:1/TableRow:6','en',NULL),
	(22,'content','2000','TableRow:7','Exercise:1/Task:1/TaskTable:1/TableRow:7','en',NULL),
	(23,'content','Dubiduppdubi, Das prangere ich an, Der goldene Käfig','TableRow:8','Exercise:1/Task:1/TaskTable:1/TableRow:8','en',NULL),
	(24,'content','102','TableRow:9','Exercise:1/Task:1/TaskTable:1/TableRow:9','de',NULL),
	(25,'content','Helge Schneider - Es rappelt im Karton!','TableRow:10','Exercise:1/Task:1/TaskTable:1/TableRow:10','de',NULL),
	(26,'content','2001','TableRow:11','Exercise:1/Task:1/TaskTable:1/TableRow:11','de',NULL),
	(27,'content','Musik Musik Musik, Der Sensemann (Eierlikoer), Hoffnung','TableRow:12','Exercise:1/Task:1/TaskTable:1/TableRow:12','de',NULL),
	(28,'description','Markieren Sie die Spalten, welche eine Zeile eindeutig identifizieren.','Task:6','Exercise:1/Task:6','de',NULL),
	(29,'description','Mark the columns which identify uniquely one column.','Task:6','Exercise:1/Task:6','en',NULL),
	(30,'description','Tabelle ohne Schlüssel','TaskTable:9','Exercise:1/Task:6/TaskTable:2','de',NULL),
	(31,'description','Table without keys','TaskTable:9','Exercise:1/Task:6/TaskTable:2','en',NULL),
	(32,'name','CD_ID','TableColumn:31','Exercise:1/Task:9/TableColumn:31','en',NULL),
	(33,'name','Album','TableColumn:32','Exercise:1/Task:9/TableColumn:32','en',NULL),
	(34,'name','Gründungsjahr','TableColumn:33','Exercise:1/Task:9/TableColumn:33','de',NULL),
	(35,'name','Founding year','TableColumn:33','Exercise:1/Task:9/TableColumn:33','en',NULL),
	(36,'name','Titelliste','TableColumn:34','Exercise:1/Task:9/TableColumn:34','de',NULL),
	(37,'name','Track list','TableColumn:34','Exercise:1/Task:9/TableColumn:34','en',NULL),
	(38,'content','100','TableRow:13','Exercise:1/Task:6/TaskTable:9/TableRow:13','de',NULL),
	(39,'content','Helge Schneider - Da Humm','TableRow:14','Exercise:1/Task:6/TaskTable:9/TableRow:14','de',NULL),
	(40,'content','1998','TableRow:15','Exercise:1/Task:6/TaskTable:9/TableRow:15','de',NULL),
	(41,'content','F-Dur, Da Humm (Solo), Bonbon aus Wurst','TableRow:16','Exercise:1/Task:6/TaskTable:9/TableRow:16','de',NULL),
	(42,'content','101','TableRow:17','Exercise:1/Task:6/TaskTable:9/TableRow:17','de',NULL),
	(43,'content','Helge Schneider - Es gibt Reis Baby','TableRow:18','Exercise:1/Task:6/TaskTable:9/TableRow:18','en',NULL),
	(44,'content','2000','TableRow:19','Exercise:1/Task:6/TaskTable:9/TableRow:19','de',NULL),
	(45,'content','Dubiduppdubi, Das prangere ich an, Der goldene Käfig','TableRow:20','Exercise:1/Task:6/TaskTable:9/TableRow:20','de',NULL),
	(46,'content','102','TableRow:21','Exercise:1/Task:6/TaskTable:9/TableRow:21','de',NULL),
	(47,'content','Helge Schneider - Es rappelt im Karton!','TableRow:22','Exercise:1/Task:6/TaskTable:9/TableRow:22','de',NULL),
	(48,'content','2001','TableRow:23','Exercise:1/Task:6/TaskTable:9/TableRow:23','de',NULL),
	(49,'content','Musik Musik Musik, Der Sensemann (Eierlikoer), Hoffnung','TableRow:24','Exercise:1/Task:6/TaskTable:9/TableRow:24','de',NULL),
	(50,'description','Bitte stellen Sie die erste Normalform her. Markieren Sie die Spalten, die gegen diese verstossen.','Task:2','Exercise:1/Task:2','de',NULL),
	(51,'description','Please create the first normalized form. Mark the columns which conflict.','Task:2','Exercise:1/Task:2','en',NULL),
	(52,'description','Aufgabentabelle mit eindeutigem Schluessel','TaskTable:2','Exercise:1/Task:2/TaskTable:2','de',NULL),
	(53,'description','Table with unique key','TaskTable:2','Exercise:1/Task:2/TaskTable:2','en',NULL),
	(54,'content','100','TableRow:25','Exercise:1/Task:2/TaskTable:2/TableRow:25','de',NULL),
	(55,'content','Helge Schneider - Da Humm','TableRow:26','Exercise:1/Task:2/TaskTable:2/TableRow:26','de',NULL),
	(56,'content','1998','TableRow:27','Exercise:1/Task:2/TaskTable:2/TableRow:27','de',NULL),
	(57,'content','F-Dur, Da Humm (Solo), Bonbon aus Wurst','TableRow:28','Exercise:1/Task:2/TaskTable:2/TableRow:28','de',NULL),
	(58,'content','101','TableRow:29','Exercise:1/Task:2/TaskTable:2/TableRow:29','de',NULL),
	(59,'content','Helge Schneider  - Es gibt Reis Baby','TableRow:30','Exercise:1/Task:2/TaskTable:2/TableRow:30','de',NULL),
	(60,'content','2000','TableRow:31','Exercise:1/Task:2/TaskTable:2/TableRow:31','de',NULL),
	(61,'content','Dubiduppdubi, Das prangere ich an, Der goldene Käfig','TableRow:32','Exercise:1/Task:2/TaskTable:2/TableRow:32','de',NULL),
	(62,'content','102','TableRow:33','Exercise:1/Task:2/TaskTable:2/TableRow:33','de',NULL),
	(63,'content','Helge Schneider - Es rappelt im Karton!','TableRow:34','Exercise:1/Task:2/TaskTable:2/TableRow:34','de',NULL),
	(64,'content','2001','TableRow:35','Exercise:1/Task:2/TaskTable:2/TableRow:35','de',NULL),
	(65,'content','Musik Musik Musik, Der Sensemann (Eierlikoer), Hoffnung','TableRow:36','Exercise:1/Task:2/TaskTable:2/TableRow:36','de',NULL),
	(67,'description','Bitte stellen Sie die zweite Normalform her.','Task:3','Exercise:1/Task:3','de',NULL),
	(68,'description','Please build the second normal form.','Task:3','Exercise:1/Task:3','en',NULL),
	(69,'name','CD_ID','TableColumn:5','Exercise:1/Task:2/TaskTable:2/TableColumn:5','en',NULL),
	(70,'name','Album','TableColumn:6','Exercise:1/Task:2/TaskTable:2/TableColumn:6','en',NULL),
	(71,'name','Gründungsjahr','TableColumn:7','Exercise:1/Task:2/TaskTable:2/TableColumn:7','de',NULL),
	(72,'name','Founding year','TableColumn:7','Exercise:1/Task:2/TaskTable:2/TableColumn:7','en',NULL),
	(73,'name','Titelliste','TableColumn:8','Exercise:1/Task:2/TaskTable:2/TableColumn:8','de',NULL),
	(74,'name','Track list','TableColumn:8','Exercise:1/Task:2/TaskTable:2/TableColumn:8','en',NULL),
	(75,'name','CD_ID','TableColumn:9','Exercise:1/Task:3/TaskTable:3/TableColumn:9','en',NULL),
	(76,'name','Albumtitel','TableColumn:10','Exercise:1/Task:3/TaskTable:3/TableColumn:10','de',NULL),
	(77,'name','Albumtitle','TableColumn:10','Exercise:1/Task:3/TaskTable:3/TableColumn:10','en',NULL),
	(78,'name','Interpret','TableColumn:11','Exercise:1/Task:3/TaskTable:3/TableColumn:11','de',NULL),
	(79,'name','Artist','TableColumn:11','Exercise:1/Task:3/TaskTable:3/TableColumn:11','en',NULL),
	(80,'name','Gründungsjahr','TableColumn:12','Exercise:1/Task:3/TaskTable:3/TableColumn:12','de',NULL),
	(81,'name','Founding year','TableColumn:12','Exercise:1/Task:3/TaskTable:3/TableColumn:12','en',NULL),
	(82,'name','Track','TableColumn:13','Exercise:1/Task:3/TaskTable:3/TableColumn:13','en',NULL),
	(83,'name','Titel','TableColumn:14','Exercise:1/Task:3/TaskTable:3/TableColumn:14','de',NULL),
	(84,'name','Title','TableColumn:14','Exercise:1/Task:3/TaskTable:3/TableColumn:14','en',NULL),
	(85,'content','100','TableRow:37','Exercise:1/Task:3/TaskTable:3/TableRow:37','de',NULL),
	(86,'content','Da Humm','TableRow:38','Exercise:1/Task:3/TaskTable:3/TableRow:38','de',NULL),
	(87,'content','Helge Schneider','TableRow:39','Exercise:1/Task:3/TaskTable:3/TableRow:39','de',NULL),
	(88,'content','1998','TableRow:40','Exercise:1/Task:3/TaskTable:3/TableRow:40','de',NULL),
	(89,'content','1','TableRow:41','Exercise:1/Task:3/TaskTable:3/TableRow:41','de',NULL),
	(90,'content','F-Dur','TableRow:42','Exercise:1/Task:3/TaskTable:3/TableRow:42','de',NULL),
	(91,'content','100','TableRow:43','Exercise:1/Task:3/TaskTable:3/TableRow:43','de',NULL),
	(92,'content','Da Humm','TableRow:44','Exercise:1/Task:3/TaskTable:3/TableRow:44','de',NULL),
	(93,'content','Helge Schneider','TableRow:45','Exercise:1/Task:3/TaskTable:3/TableRow:45','de',NULL),
	(94,'content','1998','TableRow:46','Exercise:1/Task:3/TaskTable:3/TableRow:46','de',NULL),
	(95,'content','1','TableRow:47','Exercise:1/Task:3/TaskTable:3/TableRow:47','de',NULL),
	(96,'content','Da Humm (Solo)','TableRow:48','Exercise:1/Task:3/TaskTable:3/TableRow:48','de',NULL),
	(97,'content','100','TableRow:49','Exercise:1/Task:3/TaskTable:3/TableRow:49','de',NULL),
	(98,'content','Da Humm','TableRow:50','Exercise:1/Task:3/TaskTable:3/TableRow:50','de',NULL),
	(99,'content','Helge Schneider','TableRow:51','Exercise:1/Task:3/TaskTable:3/TableRow:51','de',NULL),
	(100,'content','1998','TableRow:52','Exercise:1/Task:3/TaskTable:3/TableRow:52','de',NULL),
	(101,'content','1','TableRow:53','Exercise:1/Task:3/TaskTable:3/TableRow:53','de',NULL),
	(102,'content','Bonbon aus Wurst','TableRow:54','Exercise:1/Task:3/TaskTable:3/TableRow:54','de',NULL),
	(103,'content','101','TableRow:55','Exercise:1/Task:3/TaskTable:3/TableRow:55','de',NULL),
	(104,'content','Es gibt Reis Baby','TableRow:56','Exercise:1/Task:3/TaskTable:3/TableRow:56','de',NULL),
	(105,'content','Helge Schneider','TableRow:57','Exercise:1/Task:3/TaskTable:3/TableRow:57','de',NULL),
	(106,'content','2000','TableRow:58','Exercise:1/Task:3/TaskTable:3/TableRow:58','de',NULL),
	(107,'content','1','TableRow:59','Exercise:1/Task:3/TaskTable:3/TableRow:59','de',NULL),
	(108,'content','Dubiduppdubi','TableRow:60','Exercise:1/Task:3/TaskTable:3/TableRow:60','de',NULL),
	(109,'content','101','TableRow:61','Exercise:1/Task:3/TaskTable:3/TableRow:61','de',NULL),
	(110,'content','Es gibt Reis Baby','TableRow:62','Exercise:1/Task:3/TaskTable:3/TableRow:62','de',NULL),
	(111,'content','Helge Schneider','TableRow:63','Exercise:1/Task:3/TaskTable:3/TableRow:63','de',NULL),
	(112,'content','2000','TableRow:64','Exercise:1/Task:3/TaskTable:3/TableRow:64','de',NULL),
	(113,'content','1','TableRow:65','Exercise:1/Task:3/TaskTable:3/TableRow:65','de',NULL),
	(114,'content','Das prangere ich an','TableRow:66','Exercise:1/Task:3/TaskTable:3/TableRow:66','de',NULL),
	(115,'content','101','TableRow:67','Exercise:1/Task:3/TaskTable:3/TableRow:67','de',NULL),
	(116,'content','Es gibt Reis Baby','TableRow:68','Exercise:1/Task:3/TaskTable:3/TableRow:68','de',NULL),
	(117,'content','Helge Schneider','TableRow:69','Exercise:1/Task:3/TaskTable:3/TableRow:69','de',NULL),
	(118,'content','2000','TableRow:70','Exercise:1/Task:3/TaskTable:3/TableRow:70','de',NULL),
	(119,'content','1','TableRow:71','Exercise:1/Task:3/TaskTable:3/TableRow:71','de',NULL),
	(120,'content','Der goldene Käfig','TableRow:72','Exercise:1/Task:3/TaskTable:3/TableRow:72','de',NULL),
	(121,'content','102','TableRow:73','Exercise:1/Task:3/TaskTable:3/TableRow:73','de',NULL),
	(122,'content','Es rappelt im Karton!','TableRow:74','Exercise:1/Task:3/TaskTable:3/TableRow:74','de',NULL),
	(123,'content','Helge Schneider','TableRow:75','Exercise:1/Task:3/TaskTable:3/TableRow:75','de',NULL),
	(124,'content','2001','TableRow:76','Exercise:1/Task:3/TaskTable:3/TableRow:76','de',NULL),
	(125,'content','1','TableRow:77','Exercise:1/Task:3/TaskTable:3/TableRow:77','de',NULL),
	(126,'content','Musik Musik Musik','TableRow:78','Exercise:1/Task:3/TaskTable:3/TableRow:78','de',NULL),
	(127,'content','102','TableRow:79','Exercise:1/Task:3/TaskTable:3/TableRow:79','de',NULL),
	(128,'content','Es rappelt im Karton!','TableRow:80','Exercise:1/Task:3/TaskTable:3/TableRow:80','de',NULL),
	(129,'content','Helge Schneider','TableRow:81','Exercise:1/Task:3/TaskTable:3/TableRow:81','de',NULL),
	(130,'content','2001','TableRow:82','Exercise:1/Task:3/TaskTable:3/TableRow:82','de',NULL),
	(131,'content','1','TableRow:83','Exercise:1/Task:3/TaskTable:3/TableRow:83','de',NULL),
	(132,'content','Der Sensenmann (Eierlikoer)','TableRow:84','Exercise:1/Task:3/TaskTable:3/TableRow:84','de',NULL),
	(133,'content','102','TableRow:85','Exercise:1/Task:3/TaskTable:3/TableRow:85','de',NULL),
	(134,'content','Es rappelt im Karton!','TableRow:86','Exercise:1/Task:3/TaskTable:3/TableRow:86','de',NULL),
	(135,'content','Helge Schneider','TableRow:87','Exercise:1/Task:3/TaskTable:3/TableRow:87','de',NULL),
	(136,'content','2001','TableRow:88','Exercise:1/Task:3/TaskTable:3/TableRow:88','de',NULL),
	(137,'content','1','TableRow:89','Exercise:1/Task:3/TaskTable:3/TableRow:89','de',NULL),
	(138,'content','Hoffnung','TableRow:90','Exercise:1/Task:3/TaskTable:3/TableRow:90','de',NULL),
	(139,'description','Bitte stellen Sie nun noch die dritte Normalform her. Erstellen Sie dazu alle Lösungstabellen, auch wenn diese der 2. Normalform gleichen.','Task:4','Exercise:1/Task:4','de',NULL),
	(140,'description','Finally, please ensure the third normal form. Your solution has to contain all solution Tables, even when they were part of your previous answer.','Task:4','Exercise:1/Task:4','en',NULL),
	(141,'description','Aufgabentabelle CD','TaskTable:4','Exercise:1/Task:4/TaskTable:4','de',NULL),
	(142,'description','Exercise table CD','TaskTable:4','Exercise:1/Task:4/TaskTable:4','en',NULL),
	(143,'description','Aufgabentabelle Lied','TaskTable:5','Exercise:1/Task:4/TaskTable:5','de',NULL),
	(144,'description','Exercise table Track','TaskTable:5','Exercise:1/Task:4/TaskTable:5','en',NULL),
	(145,'name','CD_ID','TableColumn:15','Exercise:1/Task:4/TaskTable:4/TableColumn:15','en',NULL),
	(146,'name','Albumtitel','TableColumn:16','Exercise:1/Task:4/TaskTable:4/TableColumn:16','de',NULL),
	(147,'name','Albumtitle','TableColumn:16','Exercise:1/Task:4/TaskTable:4/TableColumn:16','en',NULL),
	(148,'name','Interpret','TableColumn:17','Exercise:1/Task:4/TaskTable:4/TableColumn:17','de',NULL),
	(150,'name','Artist','TableColumn:17','Exercise:1/Task:4/TaskTable:4/TableColumn:17','en',NULL),
	(151,'name','Gründungsjahr','TableColumn:18','Exercise:1/Task:4/TaskTable:4/TableColumn:18','de',NULL),
	(152,'name','Founding year','TableColumn:18','Exercise:1/Task:4/TaskTable:4/TableColumn:18','en',NULL),
	(153,'name','CD_ID','TableColumn:19','Exercise:1/Task:4/TaskTable:5/TableColumn:19','en',NULL),
	(154,'name','Track','TableColumn:20','Exercise:1/Task:4/TaskTable:5/TableColumn:20','en',NULL),
	(155,'name','Titel','TableColumn:21','Exercise:1/Task:4/TaskTable:5/TableColumn:21','de',NULL),
	(156,'name','Title','TableColumn:21','Exercise:1/Task:4/TaskTable:5/TableColumn:21','en',NULL),
	(157,'content','100','TableRow:91','Exercise:1/Task:4/TaskTable:4/TableRow:91','de',NULL),
	(158,'content','Da Humm','TableRow:92','Exercise:1/Task:4/TaskTable:4/TableRow:92','de',NULL),
	(159,'content','Helge Schneider','TableRow:93','Exercise:1/Task:4/TaskTable:4/TableRow:93','de',NULL),
	(160,'content','1998','TableRow:94','Exercise:1/Task:4/TaskTable:4/TableRow:94','de',NULL),
	(161,'content','101','TableRow:95','Exercise:1/Task:4/TaskTable:4/TableRow:95','de',NULL),
	(162,'content','Es gibt Reis Baby','TableRow:96','Exercise:1/Task:4/TaskTable:4/TableRow:96','de',NULL),
	(163,'content','Helge Schneider','TableRow:97','Exercise:1/Task:4/TaskTable:4/TableRow:97','de',NULL),
	(164,'content','2000','TableRow:98','Exercise:1/Task:4/TaskTable:4/TableRow:98','de',NULL),
	(165,'content','102','TableRow:99','Exercise:1/Task:4/TaskTable:4/TableRow:99','de',NULL),
	(166,'content','Es rappelt im Karton!','TableRow:100','Exercise:1/Task:4/TaskTable:4/TableRow:100','de',NULL),
	(167,'content','Helge Schneider','TableRow:101','Exercise:1/Task:4/TaskTable:4/TableRow:101','de',NULL),
	(168,'content','2001','TableRow:102','Exercise:1/Task:4/TaskTable:4/TableRow:102','de',NULL),
	(169,'content','100','TableRow:103','Exercise:1/Task:4/TaskTable:4/TableRow:103','de',NULL),
	(170,'content','1','TableRow:104','Exercise:1/Task:4/TaskTable:4/TableRow:104','de',NULL),
	(171,'content','F-Dur','TableRow:105','Exercise:1/Task:4/TaskTable:4/TableRow:105','de',NULL),
	(172,'content','100','TableRow:106','Exercise:1/Task:4/TaskTable:4/TableRow:106','de',NULL),
	(173,'content','2','TableRow:107','Exercise:1/Task:4/TaskTable:4/TableRow:107','de',NULL),
	(174,'content','Da Humm (Solo)','TableRow:108','Exercise:1/Task:4/TaskTable:4/TableRow:108','de',NULL),
	(175,'content','100','TableRow:109','Exercise:1/Task:4/TaskTable:4/TableRow:109','de',NULL),
	(176,'content','3','TableRow:110','Exercise:1/Task:4/TaskTable:4/TableRow:110','de',NULL),
	(177,'content','Bonbon aus Wurst','TableRow:111','Exercise:1/Task:4/TaskTable:4/TableRow:111','de',NULL),
	(178,'content','101','TableRow:112','Exercise:1/Task:4/TaskTable:4/TableRow:112','de',NULL),
	(179,'content','1','TableRow:113','Exercise:1/Task:4/TaskTable:4/TableRow:113','de',NULL),
	(180,'content','Dubiduppdubi','TableRow:114','Exercise:1/Task:4/TaskTable:4/TableRow:114','de',NULL),
	(181,'content','101','TableRow:115','Exercise:1/Task:4/TaskTable:4/TableRow:115','de',NULL),
	(182,'content','2','TableRow:116','Exercise:1/Task:4/TaskTable:4/TableRow:116','de',NULL),
	(183,'content','Das prangere ich an','TableRow:117','Exercise:1/Task:4/TaskTable:4/TableRow:117','de',NULL),
	(184,'content','101','TableRow:118','Exercise:1/Task:4/TaskTable:4/TableRow:118','de',NULL),
	(185,'content','3','TableRow:119','Exercise:1/Task:4/TaskTable:4/TableRow:119','de',NULL),
	(186,'content','Der goldene Käfig','TableRow:120','Exercise:1/Task:4/TaskTable:4/TableRow:120','de',NULL),
	(187,'content','102','TableRow:121','Exercise:1/Task:4/TaskTable:4/TableRow:121','de',NULL),
	(188,'content','1','TableRow:122','Exercise:1/Task:4/TaskTable:4/TableRow:122','de',NULL),
	(189,'content','Musik Musik Musik','TableRow:123','Exercise:1/Task:4/TaskTable:4/TableRow:123','de',NULL),
	(190,'content','102','TableRow:124','Exercise:1/Task:4/TaskTable:4/TableRow:124','de',NULL),
	(191,'content','2','TableRow:125','Exercise:1/Task:4/TaskTable:4/TableRow:125','de',NULL),
	(192,'content','Der Sensemann (Eierlikoer)','TableRow:126','Exercise:1/Task:4/TaskTable:4/TableRow:126','de',NULL),
	(193,'content','102','TableRow:127','Exercise:1/Task:4/TaskTable:4/TableRow:127','de',NULL),
	(194,'content','3','TableRow:128','Exercise:1/Task:4/TaskTable:4/TableRow:128','de',NULL),
	(195,'content','Hoffnung','TableRow:129','Exercise:1/Task:4/TaskTable:4/TableRow:129','de',NULL),
	(196,'description','Lösung','Task:5','Exercise:1/Task:5','de',NULL),
	(197,'description','Solution','Task:5','Exercise:1/Task:5','en',NULL),
	(198,'description','Aufgabentabelle CDs','TaskTable:6','Exercise:1/Task:5/TaskTable:6','de',NULL),
	(199,'description','Exercise table CDs','TaskTable:6','Exercise:1/Task:5/TaskTable:6','en',NULL),
	(200,'description','Aufgabentabelle Interpreten','TaskTable:7','Exercise:1/Task:5/TaskTable:7','de',NULL),
	(201,'description','Exercise table Artists','TaskTable:7','Exercise:1/Task:5/TaskTable:7','en',NULL),
	(202,'description','Aufgabentabelle Lieder','TaskTable:8','Exercise:1/Task:5/TaskTable:8','de',NULL),
	(203,'description','Exercise table Songs','TaskTable:8','Exercise:1/Task:5/TaskTable:8','en',NULL),
	(204,'content','100','TableRow:130','Exercise:1/Task:5/TaskTable:6/TableRow:130','de',NULL),
	(205,'content','Helge Schneider','TableRow:131','Exercise:1/Task:5/TaskTable:6/TableRow:131','de',NULL),
	(206,'content','Da Humm','TableRow:132','Exercise:1/Task:5/TaskTable:6/TableRow:132','de',NULL),
	(207,'content','101','TableRow:133','Exercise:1/Task:5/TaskTable:6/TableRow:133','de',NULL),
	(208,'content','Helge Schneider','TableRow:134','Exercise:1/Task:5/TaskTable:6/TableRow:134','de',NULL),
	(209,'content','Es gibt Reis Baby','TableRow:135','Exercise:1/Task:5/TaskTable:6/TableRow:135','de',NULL),
	(210,'content','102','TableRow:136','Exercise:1/Task:5/TaskTable:6/TableRow:136','de',NULL),
	(211,'content','Helge Schneider','TableRow:137','Exercise:1/Task:5/TaskTable:6/TableRow:137','de',NULL),
	(212,'content','Es rappelt im Karton!','TableRow:138','Exercise:1/Task:5/TaskTable:6/TableRow:138','de',NULL),
	(213,'content','Helge Schneider','TableRow:139','Exercise:1/Task:5/TaskTable:6/TableRow:139','de',NULL),
	(214,'content','1980','TableRow:140','Exercise:1/Task:5/TaskTable:6/TableRow:140','de',NULL),
	(215,'content','100','TableRow:141','Exercise:1/Task:5/TaskTable:6/TableRow:141','de',NULL),
	(216,'content','100','TableRow:141','Exercise:1/Task:5/TaskTable:6/TableRow:141','en',NULL),
	(217,'content','1','TableRow:142','Exercise:1/Task:5/TaskTable:6/TableRow:142','de',NULL),
	(218,'content','1','TableRow:142','Exercise:1/Task:5/TaskTable:6/TableRow:142','en',NULL),
	(219,'content','F-Dur','TableRow:143','Exercise:1/Task:5/TaskTable:6/TableRow:143','de',NULL),
	(220,'content','F-Dur','TableRow:143','Exercise:1/Task:5/TaskTable:6/TableRow:143','en',NULL),
	(221,'content','100','TableRow:144','Exercise:1/Task:5/TaskTable:6/TableRow:144','de',NULL),
	(222,'content','100','TableRow:144','Exercise:1/Task:5/TaskTable:6/TableRow:144','en',NULL),
	(223,'content','2','TableRow:145','Exercise:1/Task:5/TaskTable:6/TableRow:145','de',NULL),
	(224,'content','2','TableRow:145','Exercise:1/Task:5/TaskTable:6/TableRow:145','en',NULL),
	(225,'content','Da Humm (Solo)','TableRow:146','Exercise:1/Task:5/TaskTable:6/TableRow:146','de',NULL),
	(226,'content','Da Humm (Solo)','TableRow:146','Exercise:1/Task:5/TaskTable:6/TableRow:146','en',NULL),
	(227,'content','100','TableRow:147','Exercise:1/Task:5/TaskTable:6/TableRow:147','de',NULL),
	(228,'content','100','TableRow:147','Exercise:1/Task:5/TaskTable:6/TableRow:147','en',NULL),
	(229,'content','3','TableRow:148','Exercise:1/Task:5/TaskTable:6/TableRow:148','de',NULL),
	(230,'content','3','TableRow:148','Exercise:1/Task:5/TaskTable:6/TableRow:148','en',NULL),
	(231,'content','Bonbon aus Wurst','TableRow:149','Exercise:1/Task:5/TaskTable:6/TableRow:149','de',NULL),
	(232,'content','Bonbon aus Wurst','TableRow:149','Exercise:1/Task:5/TaskTable:6/TableRow:149','en',NULL),
	(233,'content','101','TableRow:150','Exercise:1/Task:5/TaskTable:6/TableRow:150','de',NULL),
	(234,'content','101','TableRow:150','Exercise:1/Task:5/TaskTable:6/TableRow:150','en',NULL),
	(235,'content','1','TableRow:151','Exercise:1/Task:5/TaskTable:6/TableRow:151','de',NULL),
	(236,'content','1','TableRow:151','Exercise:1/Task:5/TaskTable:6/TableRow:151','en',NULL),
	(237,'content','Dubiduppdubi','TableRow:152','Exercise:1/Task:5/TaskTable:6/TableRow:152','de',NULL),
	(238,'content','Dubiduppdubi','TableRow:152','Exercise:1/Task:5/TaskTable:6/TableRow:152','en',NULL),
	(239,'content','101','TableRow:153','Exercise:1/Task:5/TaskTable:6/TableRow:153','de',NULL),
	(240,'content','101','TableRow:153','Exercise:1/Task:5/TaskTable:6/TableRow:153','en',NULL),
	(241,'content','2','TableRow:154','Exercise:1/Task:5/TaskTable:6/TableRow:154','de',NULL),
	(242,'content','2','TableRow:154','Exercise:1/Task:5/TaskTable:6/TableRow:154','en',NULL),
	(243,'content','Das prangere ich an','TableRow:155','Exercise:1/Task:5/TaskTable:6/TableRow:155','de',NULL),
	(244,'content','Das prangere ich an','TableRow:155','Exercise:1/Task:5/TaskTable:6/TableRow:155','en',NULL),
	(245,'content','101','TableRow:156','Exercise:1/Task:5/TaskTable:6/TableRow:156','de',NULL),
	(246,'content','101','TableRow:156','Exercise:1/Task:5/TaskTable:6/TableRow:156','en',NULL),
	(247,'content','3','TableRow:157','Exercise:1/Task:5/TaskTable:6/TableRow:157','de',NULL),
	(248,'content','3','TableRow:157','Exercise:1/Task:5/TaskTable:6/TableRow:157','en',NULL),
	(249,'content','Der goldene Käfig','TableRow:158','Exercise:1/Task:5/TaskTable:6/TableRow:158','de',NULL),
	(250,'content','Der goldene Käfig','TableRow:158','Exercise:1/Task:5/TaskTable:6/TableRow:158','en',NULL),
	(251,'content','102','TableRow:159','Exercise:1/Task:5/TaskTable:6/TableRow:159','de',NULL),
	(252,'content','102','TableRow:159','Exercise:1/Task:5/TaskTable:6/TableRow:159','en',NULL),
	(253,'content','1','TableRow:160','Exercise:1/Task:5/TaskTable:6/TableRow:160','de',NULL),
	(254,'content','1','TableRow:160','Exercise:1/Task:5/TaskTable:6/TableRow:160','en',NULL),
	(255,'content','Musik Musik Musik','TableRow:161','Exercise:1/Task:5/TaskTable:6/TableRow:161','de',NULL),
	(256,'content','Musik Musik Musik','TableRow:161','Exercise:1/Task:5/TaskTable:6/TableRow:161','en',NULL),
	(257,'content','102','TableRow:162','Exercise:1/Task:5/TaskTable:6/TableRow:162','de',NULL),
	(258,'content','102','TableRow:162','Exercise:1/Task:5/TaskTable:6/TableRow:162','en',NULL),
	(259,'content','2','TableRow:163','Exercise:1/Task:5/TaskTable:6/TableRow:163','de',NULL),
	(260,'content','2','TableRow:163','Exercise:1/Task:5/TaskTable:6/TableRow:163','en',NULL),
	(261,'content','Der Sensemann (Eierlikoer)','TableRow:164','Exercise:1/Task:5/TaskTable:6/TableRow:164','de',NULL),
	(262,'content','Der Sensemann (Eierlikoer)','TableRow:164','Exercise:1/Task:5/TaskTable:6/TableRow:164','en',NULL),
	(263,'content','102','TableRow:165','Exercise:1/Task:5/TaskTable:6/TableRow:165','de',NULL),
	(264,'content','102','TableRow:165','Exercise:1/Task:5/TaskTable:6/TableRow:165','en',NULL),
	(265,'content','3','TableRow:166','Exercise:1/Task:5/TaskTable:6/TableRow:166','de',NULL),
	(266,'content','3','TableRow:166','Exercise:1/Task:5/TaskTable:6/TableRow:166','en',NULL),
	(267,'content','Hoffnung','TableRow:167','Exercise:1/Task:5/TaskTable:6/TableRow:167','de',NULL),
	(268,'content','Hoffnung','TableRow:167','Exercise:1/Task:5/TaskTable:6/TableRow:167','en',NULL),
	(269,'description','Aufgabentabelle in erster Normalform','TaskTable:3','','de',NULL),
	(270,'description','Exercise table in second normal form','TaskTable:3','','en',NULL);

/*!40000 ALTER TABLE `localized_label` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table points
# ------------------------------------------------------------

DROP TABLE IF EXISTS `points`;

CREATE TABLE `points` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `app_key` varchar(10) NOT NULL,
  `game_date` datetime DEFAULT NULL,
  `points` bigint(20) NOT NULL,
  `username` varchar(255) NOT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table table_columns
# ------------------------------------------------------------

DROP TABLE IF EXISTS `table_columns`;

CREATE TABLE `table_columns` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `key_column` bit(1) NOT NULL,
  `version` int(11) DEFAULT NULL,
  `table_fk` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5061CC6C6A969059` (`table_fk`),
  CONSTRAINT `FK5061CC6C6A969059` FOREIGN KEY (`table_fk`) REFERENCES `task_tables` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

LOCK TABLES `table_columns` WRITE;
/*!40000 ALTER TABLE `table_columns` DISABLE KEYS */;
INSERT INTO `table_columns` (`id`,`key_column`,`version`,`table_fk`)
VALUES
	(1,0,NULL,1),
	(2,0,NULL,1),
	(3,0,NULL,1),
	(4,0,NULL,1),
	(5,1,NULL,2),
	(6,1,NULL,2),
	(7,0,NULL,2),
	(8,0,NULL,2),
	(9,1,NULL,3),
	(10,1,NULL,3),
	(11,0,NULL,3),
	(12,0,NULL,3),
	(13,0,NULL,3),
	(14,0,NULL,3),
	(15,1,NULL,4),
	(16,0,NULL,4),
	(17,0,NULL,4),
	(18,0,NULL,4),
	(19,1,NULL,5),
	(20,1,NULL,5),
	(21,0,NULL,5),
	(23,1,NULL,6),
	(24,1,NULL,6),
	(25,0,NULL,6),
	(26,1,NULL,8),
	(27,1,NULL,8),
	(28,0,NULL,8),
	(29,1,NULL,7),
	(30,0,NULL,7),
	(31,0,NULL,9),
	(32,0,NULL,9),
	(33,0,NULL,9),
	(34,0,NULL,9);

/*!40000 ALTER TABLE `table_columns` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table table_rows
# ------------------------------------------------------------

DROP TABLE IF EXISTS `table_rows`;

CREATE TABLE `table_rows` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `row_number` int(11) NOT NULL,
  `version` int(11) DEFAULT NULL,
  `task_column_fk` bigint(20) DEFAULT NULL,
  `task_table_fk` bigint(20) DEFAULT NULL,
  `table_column` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKA6532FAAE8EABA92` (`task_column_fk`),
  KEY `FKA6532FAA6B18F1D3` (`task_table_fk`),
  CONSTRAINT `FKA6532FAA6B18F1D3` FOREIGN KEY (`task_table_fk`) REFERENCES `task_tables` (`id`),
  CONSTRAINT `FKA6532FAAE8EABA92` FOREIGN KEY (`task_column_fk`) REFERENCES `table_columns` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=168 DEFAULT CHARSET=utf8;

LOCK TABLES `table_rows` WRITE;
/*!40000 ALTER TABLE `table_rows` DISABLE KEYS */;
INSERT INTO `table_rows` (`id`,`row_number`,`version`,`task_column_fk`,`task_table_fk`,`table_column`)
VALUES
	(1,1,NULL,1,1,0),
	(2,1,NULL,2,1,0),
	(3,1,NULL,3,1,0),
	(4,1,NULL,4,1,0),
	(5,2,NULL,1,1,0),
	(6,2,NULL,2,1,0),
	(7,2,NULL,3,1,0),
	(8,2,NULL,4,1,0),
	(9,3,NULL,1,1,0),
	(10,3,NULL,2,1,0),
	(11,3,NULL,3,1,0),
	(12,3,NULL,4,1,0),
	(13,1,NULL,31,9,0),
	(14,1,NULL,32,9,0),
	(15,1,NULL,33,9,0),
	(16,1,NULL,34,9,0),
	(17,2,NULL,31,9,0),
	(18,2,NULL,32,9,0),
	(19,2,NULL,33,9,0),
	(20,2,NULL,34,9,0),
	(21,3,NULL,31,9,0),
	(22,3,NULL,32,9,0),
	(23,3,NULL,33,9,0),
	(24,3,NULL,34,9,0),
	(25,1,NULL,1,2,0),
	(26,1,NULL,2,2,0),
	(27,1,NULL,3,2,0),
	(28,1,NULL,4,2,0),
	(29,2,NULL,1,2,0),
	(30,2,NULL,2,2,0),
	(31,2,NULL,3,2,0),
	(32,2,NULL,4,2,0),
	(33,3,NULL,1,2,0),
	(34,3,NULL,2,2,0),
	(35,3,NULL,3,2,0),
	(36,3,NULL,4,2,0),
	(37,1,NULL,9,3,0),
	(38,1,NULL,10,3,0),
	(39,1,NULL,11,3,0),
	(40,1,NULL,12,3,0),
	(41,1,NULL,13,3,0),
	(42,1,NULL,14,3,0),
	(43,2,NULL,9,3,0),
	(44,2,NULL,10,3,0),
	(45,2,NULL,11,3,0),
	(46,2,NULL,12,3,0),
	(47,2,NULL,13,3,0),
	(48,2,NULL,14,3,0),
	(49,3,NULL,9,3,0),
	(50,3,NULL,10,3,0),
	(51,3,NULL,11,3,0),
	(52,3,NULL,12,3,0),
	(53,3,NULL,13,3,0),
	(54,3,NULL,14,3,0),
	(55,4,NULL,9,3,0),
	(56,4,NULL,10,3,0),
	(57,4,NULL,11,3,0),
	(58,4,NULL,12,3,0),
	(59,4,NULL,13,3,0),
	(60,4,NULL,14,3,0),
	(61,5,NULL,9,3,0),
	(62,5,NULL,10,3,0),
	(63,5,NULL,11,3,0),
	(64,5,NULL,12,3,0),
	(65,5,NULL,13,3,0),
	(66,5,NULL,14,3,0),
	(67,6,NULL,9,3,0),
	(68,6,NULL,10,3,0),
	(69,6,NULL,11,3,0),
	(70,6,NULL,12,3,0),
	(71,6,NULL,13,3,0),
	(72,6,NULL,14,3,0),
	(73,7,NULL,9,3,0),
	(74,7,NULL,10,3,0),
	(75,7,NULL,11,3,0),
	(76,7,NULL,12,3,0),
	(77,7,NULL,13,3,0),
	(78,7,NULL,14,3,0),
	(79,8,NULL,9,3,0),
	(80,8,NULL,10,3,0),
	(81,8,NULL,11,3,0),
	(82,8,NULL,12,3,0),
	(83,8,NULL,13,3,0),
	(84,8,NULL,14,3,0),
	(85,9,NULL,9,3,0),
	(86,9,NULL,10,3,0),
	(87,9,NULL,11,3,0),
	(88,9,NULL,12,3,0),
	(89,9,NULL,13,3,0),
	(90,9,NULL,14,3,0),
	(91,1,NULL,15,4,0),
	(92,1,NULL,16,4,0),
	(93,1,NULL,17,4,0),
	(94,1,NULL,18,4,0),
	(95,2,NULL,15,4,0),
	(96,2,NULL,16,4,0),
	(97,2,NULL,17,4,0),
	(98,2,NULL,18,4,0),
	(99,3,NULL,15,4,0),
	(100,3,NULL,16,4,0),
	(101,3,NULL,17,4,0),
	(102,3,NULL,18,4,0),
	(103,1,NULL,19,5,0),
	(104,1,NULL,20,5,0),
	(105,1,NULL,21,5,0),
	(106,2,NULL,19,5,0),
	(107,2,NULL,20,5,0),
	(108,2,NULL,21,5,0),
	(109,3,NULL,19,5,0),
	(110,3,NULL,20,5,0),
	(111,3,NULL,21,5,0),
	(112,4,NULL,19,5,0),
	(113,4,NULL,20,5,0),
	(114,4,NULL,21,5,0),
	(115,5,NULL,19,5,0),
	(116,5,NULL,20,5,0),
	(117,5,NULL,21,5,0),
	(118,6,NULL,19,5,0),
	(119,6,NULL,20,5,0),
	(120,6,NULL,21,5,0),
	(121,7,NULL,19,5,0),
	(122,7,NULL,20,5,0),
	(123,7,NULL,21,5,0),
	(124,8,NULL,19,5,0),
	(125,8,NULL,20,5,0),
	(126,8,NULL,21,5,0),
	(127,9,NULL,19,5,0),
	(128,9,NULL,20,5,0),
	(129,9,NULL,21,5,0),
	(130,1,NULL,23,6,0),
	(131,1,NULL,24,6,0),
	(132,1,NULL,25,6,0),
	(133,2,NULL,23,6,0),
	(134,2,NULL,24,6,0),
	(135,2,NULL,25,6,0),
	(136,3,NULL,23,6,0),
	(137,3,NULL,24,6,0),
	(138,3,NULL,25,6,0),
	(139,1,NULL,29,7,0),
	(140,1,NULL,30,7,0),
	(141,1,NULL,26,8,0),
	(142,1,NULL,27,8,0),
	(143,1,NULL,28,8,0),
	(144,2,NULL,26,8,0),
	(145,2,NULL,27,8,0),
	(146,2,NULL,28,8,0),
	(147,3,NULL,26,8,0),
	(148,3,NULL,27,8,0),
	(149,3,NULL,28,8,0),
	(150,4,NULL,26,8,0),
	(151,4,NULL,27,8,0),
	(152,4,NULL,28,8,0),
	(153,5,NULL,26,8,0),
	(154,5,NULL,27,8,0),
	(155,5,NULL,28,8,0),
	(156,6,NULL,26,8,0),
	(157,6,NULL,27,8,0),
	(158,6,NULL,28,8,0),
	(159,7,NULL,26,8,0),
	(160,7,NULL,27,8,0),
	(161,7,NULL,28,8,0),
	(162,8,NULL,26,8,0),
	(163,8,NULL,27,8,0),
	(164,8,NULL,28,8,0),
	(165,9,NULL,26,8,0),
	(166,9,NULL,27,8,0),
	(167,9,NULL,28,8,0);

/*!40000 ALTER TABLE `table_rows` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table task_tables
# ------------------------------------------------------------

DROP TABLE IF EXISTS `task_tables`;

CREATE TABLE `task_tables` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `normalform` smallint(6) NOT NULL,
  `ordering` smallint(6) NOT NULL,
  `version` int(11) DEFAULT NULL,
  `task_fk` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7A37B65F62CBB6CA` (`task_fk`),
  CONSTRAINT `FK7A37B65F62CBB6CA` FOREIGN KEY (`task_fk`) REFERENCES `tasks` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

LOCK TABLES `task_tables` WRITE;
/*!40000 ALTER TABLE `task_tables` DISABLE KEYS */;
INSERT INTO `task_tables` (`id`,`normalform`,`ordering`,`version`,`task_fk`)
VALUES
	(1,0,0,0,1),
	(2,0,0,0,2),
	(3,1,0,0,3),
	(4,2,0,NULL,4),
	(5,2,1,NULL,4),
	(6,3,0,NULL,5),
	(7,3,1,NULL,5),
	(8,3,2,NULL,5),
	(9,0,0,0,6);

/*!40000 ALTER TABLE `task_tables` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table tasks
# ------------------------------------------------------------

DROP TABLE IF EXISTS `tasks`;

CREATE TABLE `tasks` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ordering` smallint(6) NOT NULL,
  `state` int(11) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `exercise_fk` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6907B8E763EBEEA` (`exercise_fk`),
  CONSTRAINT `FK6907B8E763EBEEA` FOREIGN KEY (`exercise_fk`) REFERENCES `exercises` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

LOCK TABLES `tasks` WRITE;
/*!40000 ALTER TABLE `tasks` DISABLE KEYS */;
INSERT INTO `tasks` (`id`,`ordering`,`state`,`version`,`exercise_fk`)
VALUES
	(1,1,0,0,1),
	(2,1,2,0,1),
	(3,2,3,0,1),
	(4,3,4,0,1),
	(5,4,5,NULL,1),
	(6,5,1,0,1);

/*!40000 ALTER TABLE `tasks` ENABLE KEYS */;
UNLOCK TABLES;





/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
