-- MySQL dump 10.13  Distrib 5.6.17, for Win64 (x86_64)
--
-- Host: localhost    Database: vqdatabase
-- ------------------------------------------------------
-- Server version	5.6.23-log

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
-- Table structure for table `activity`
--

DROP TABLE IF EXISTS `activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `activity` (
  `activity_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name_act` varchar(50) NOT NULL,
  `about` blob,
  `latitude` int(11) NOT NULL,
  `longitude` int(11) NOT NULL,
  `location_in_venue` varchar(30) DEFAULT NULL,
  `time_per_event` int(11) NOT NULL,
  `entry_time` int(11) NOT NULL,
  `exit_time` int(11) NOT NULL,
  `max_guest_per_event` int(11) NOT NULL,
  `max_concurrent_event` int(11) NOT NULL,
  `min_age` int(11) DEFAULT NULL,
  `min_height` int(11) DEFAULT NULL,
  `max_height` int(11) DEFAULT NULL,
  `min_weight` int(11) DEFAULT NULL,
  `max_weight` int(11) DEFAULT NULL,
  `other_restriction` varchar(50) DEFAULT NULL,
  `disclaimer` blob,
  `waiver` blob,
  `restriction_group_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`activity_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity`
--

LOCK TABLES `activity` WRITE;
/*!40000 ALTER TABLE `activity` DISABLE KEYS */;
INSERT INTO `activity` VALUES (1,'Splash Mountain','Blah Blah Blah',56,34,'Orlando',2,2,2,8,1,NULL,110,220,80,300,NULL,NULL,NULL,NULL),(2,'Thunder Mountain','Blah Blah Blah',60,40,'Orlando',5,2,2,8,1,NULL,110,220,80,300,NULL,NULL,NULL,NULL),(3,'Space Mountain','Blah Blah Blah',70,80,'Orlando',5,5,5,9,2,NULL,110,220,80,300,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `activity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patron`
--

DROP TABLE IF EXISTS `patron`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patron` (
  `patron_id` int(10) unsigned NOT NULL,
  `venue_id` int(10) unsigned NOT NULL,
  `name_first` varchar(20) NOT NULL,
  `name_last` varchar(20) NOT NULL,
  `marketing_contact_info_tbd` varchar(30) DEFAULT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `code_number` varchar(45) DEFAULT NULL,
  `role_type` varchar(45) DEFAULT NULL,
  `role_id` varchar(45) DEFAULT NULL,
  `role_desc` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`patron_id`,`venue_id`),
  KEY `venue_id_fk_idx` (`venue_id`),
  CONSTRAINT `venue_id_fk` FOREIGN KEY (`venue_id`) REFERENCES `venue` (`venue_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patron`
--

LOCK TABLES `patron` WRITE;
/*!40000 ALTER TABLE `patron` DISABLE KEYS */;
INSERT INTO `patron` VALUES (1,1,'Kenneth','Kon',NULL,'kkon001@fiu.edu','123','1','User','1','1'),(2,1,'Michael','Lazo',NULL,'mlazo007@fiu.edu','321','1','Admin','1','1'),(3,1,'firstname3','lastname3',NULL,'random@fiu.edu','random3','1','User','1','1'),(4,1,'firstname4','lastname4',NULL,'random@fiu.edu','random4','1','User','1','1'),(5,1,'firstname5','lastname5',NULL,'random@fiu.edu','random5','1','User','1','1'),(6,1,'firstname6','lastname6',NULL,'random@fiu.edu','random6','1','User','1','1'),(7,1,'firstname7','lastname7',NULL,'random@fiu.edu','random7','1','User','1','1'),(8,1,'firstname8','lastname8',NULL,'random@fiu.edu','random8','1','User','1','1'),(9,1,'firstname9','lastname9',NULL,'random@fiu.edu','random9','1','User','1','1'),(10,1,'firstname10','lastname10',NULL,'random@fiu.edu','random10','1','User','1','1'),(11,1,'firstname11','lastname11',NULL,'random@fiu.edu','random11','1','User','1','1'),(12,1,'firstname12','lastname12',NULL,'random@fiu.edu','random12','1','User','1','1'),(13,1,'firstname13','lastname13',NULL,'random@fiu.edu','random13','1','User','1','1'),(14,1,'firstname14','lastname14',NULL,'random@fiu.edu','random14','1','User','1','1'),(15,1,'firstname15','lastname15',NULL,'random@fiu.edu','random15','1','User','1','1'),(16,1,'firstname16','lastname16',NULL,'random@fiu.edu','random16','1','User','1','1'),(17,1,'firstname17','lastname17',NULL,'random@fiu.edu','random17','1','User','1','1'),(18,1,'firstname18','lastname18',NULL,'random@fiu.edu','random18','1','User','1','1'),(19,1,'firstname19','lastname19',NULL,'random@fiu.edu','random19','1','User','1','1'),(20,1,'firstname20','lastname20',NULL,'random@fiu.edu','random20','1','User','1','1'),(21,1,'firstname21','lastname21',NULL,'random@fiu.edu','random21','1','User','1','1'),(22,1,'firstname22','lastname22',NULL,'random@fiu.edu','random22','1','User','1','1'),(23,1,'firstname23','lastname23',NULL,'random@fiu.edu','random23','1','User','1','1'),(24,1,'firstname24','lastname24',NULL,'random@fiu.edu','random24','1','User','1','1'),(25,1,'firstname25','lastname25',NULL,'random@fiu.edu','random25','1','User','1','1'),(26,1,'firstname26','lastname26',NULL,'random@fiu.edu','random26','1','User','1','1'),(27,1,'firstname27','lastname27',NULL,'random@fiu.edu','random27','1','User','1','1'),(28,1,'firstname28','lastname28',NULL,'random@fiu.edu','random28','1','User','1','1'),(29,1,'firstname29','lastname29',NULL,'random@fiu.edu','random29','1','User','1','1'),(30,1,'firstname30','lastname30',NULL,'random@fiu.edu','random30','1','User','1','1'),(31,1,'firstname31','lastname31',NULL,'random@fiu.edu','random31','1','User','1','1'),(32,1,'firstname32','lastname32',NULL,'random@fiu.edu','random32','1','User','1','1'),(33,1,'firstname33','lastname33',NULL,'random@fiu.edu','random33','1','User','1','1'),(34,1,'firstname34','lastname34',NULL,'random@fiu.edu','random34','1','User','1','1'),(35,1,'firstname35','lastname35',NULL,'random@fiu.edu','random35','1','User','1','1'),(36,1,'firstname36','lastname36',NULL,'random@fiu.edu','random36','1','User','1','1'),(37,1,'firstname37','lastname37',NULL,'random@fiu.edu','random37','1','User','1','1'),(38,1,'firstname38','lastname38',NULL,'random@fiu.edu','random38','1','User','1','1'),(39,1,'firstname39','lastname39',NULL,'random@fiu.edu','random39','1','User','1','1'),(40,1,'firstname40','lastname40',NULL,'random@fiu.edu','random40','1','User','1','1'),(41,1,'firstname41','lastname41',NULL,'random@fiu.edu','random41','1','User','1','1'),(42,1,'firstname42','lastname42',NULL,'random@fiu.edu','random42','1','User','1','1'),(43,1,'firstname43','lastname43',NULL,'random@fiu.edu','random43','1','User','1','1'),(44,1,'firstname44','lastname44',NULL,'random@fiu.edu','random44','1','User','1','1'),(45,1,'firstname45','lastname45',NULL,'random@fiu.edu','random45','1','User','1','1'),(46,1,'firstname46','lastname46',NULL,'random@fiu.edu','random46','1','User','1','1'),(47,1,'firstname47','lastname47',NULL,'random@fiu.edu','random47','1','User','1','1'),(48,1,'firstname48','lastname48',NULL,'random@fiu.edu','random48','1','User','1','1'),(49,1,'firstname49','lastname49',NULL,'random@fiu.edu','random49','1','User','1','1'),(50,1,'firstname50','lastname50',NULL,'random@fiu.edu','random50','1','User','1','1'),(51,1,'firstname51','lastname51',NULL,'random@fiu.edu','random51','1','User','1','1'),(52,1,'firstname52','lastname52',NULL,'random@fiu.edu','random52','1','User','1','1'),(53,1,'firstname53','lastname53',NULL,'random@fiu.edu','random53','1','User','1','1'),(54,1,'firstname54','lastname54',NULL,'random@fiu.edu','random54','1','User','1','1'),(55,1,'firstname55','lastname55',NULL,'random@fiu.edu','random55','1','User','1','1'),(56,1,'firstname56','lastname56',NULL,'random@fiu.edu','random56','1','User','1','1'),(57,1,'firstname57','lastname57',NULL,'random@fiu.edu','random57','1','User','1','1'),(58,1,'firstname58','lastname58',NULL,'random@fiu.edu','random58','1','User','1','1'),(59,1,'firstname59','lastname59',NULL,'random@fiu.edu','random59','1','User','1','1'),(60,1,'firstname60','lastname60',NULL,'random@fiu.edu','random60','1','User','1','1'),(61,1,'firstname61','lastname61',NULL,'random@fiu.edu','random61','1','User','1','1'),(62,1,'firstname62','lastname62',NULL,'random@fiu.edu','random62','1','User','1','1'),(63,1,'firstname63','lastname63',NULL,'random@fiu.edu','random63','1','User','1','1'),(64,1,'firstname64','lastname64',NULL,'random@fiu.edu','random64','1','User','1','1'),(65,1,'firstname65','lastname65',NULL,'random@fiu.edu','random65','1','User','1','1'),(66,1,'firstname66','lastname66',NULL,'random@fiu.edu','random66','1','User','1','1'),(67,1,'firstname67','lastname67',NULL,'random@fiu.edu','random67','1','User','1','1'),(68,1,'firstname68','lastname68',NULL,'random@fiu.edu','random68','1','User','1','1'),(69,1,'firstname69','lastname69',NULL,'random@fiu.edu','random69','1','User','1','1'),(70,1,'firstname70','lastname70',NULL,'random@fiu.edu','random70','1','User','1','1'),(71,1,'firstname71','lastname71',NULL,'random@fiu.edu','random71','1','User','1','1'),(72,1,'firstname72','lastname72',NULL,'random@fiu.edu','random72','1','User','1','1'),(73,1,'firstname73','lastname73',NULL,'random@fiu.edu','random73','1','User','1','1'),(74,1,'firstname74','lastname74',NULL,'random@fiu.edu','random74','1','User','1','1'),(75,1,'firstname75','lastname75',NULL,'random@fiu.edu','random75','1','User','1','1'),(76,1,'firstname76','lastname76',NULL,'random@fiu.edu','random76','1','User','1','1'),(77,1,'firstname77','lastname77',NULL,'random@fiu.edu','random77','1','User','1','1'),(78,1,'firstname78','lastname78',NULL,'random@fiu.edu','random78','1','User','1','1'),(79,1,'firstname79','lastname79',NULL,'random@fiu.edu','random79','1','User','1','1'),(80,1,'firstname80','lastname80',NULL,'random@fiu.edu','random80','1','User','1','1'),(81,1,'firstname81','lastname81',NULL,'random@fiu.edu','random81','1','User','1','1'),(82,1,'firstname82','lastname82',NULL,'random@fiu.edu','random82','1','User','1','1'),(83,1,'firstname83','lastname83',NULL,'random@fiu.edu','random83','1','User','1','1'),(84,1,'firstname84','lastname84',NULL,'random@fiu.edu','random84','1','User','1','1'),(85,1,'firstname85','lastname85',NULL,'random@fiu.edu','random85','1','User','1','1'),(86,1,'firstname86','lastname86',NULL,'random@fiu.edu','random86','1','User','1','1'),(87,1,'firstname87','lastname87',NULL,'random@fiu.edu','random87','1','User','1','1'),(88,1,'firstname88','lastname88',NULL,'random@fiu.edu','random88','1','User','1','1'),(89,1,'firstname89','lastname89',NULL,'random@fiu.edu','random89','1','User','1','1'),(90,1,'firstname90','lastname90',NULL,'random@fiu.edu','random90','1','User','1','1'),(91,1,'firstname91','lastname91',NULL,'random@fiu.edu','random91','1','User','1','1'),(92,1,'firstname92','lastname92',NULL,'random@fiu.edu','random92','1','User','1','1'),(93,1,'firstname93','lastname93',NULL,'random@fiu.edu','random93','1','User','1','1'),(94,1,'firstname94','lastname94',NULL,'random@fiu.edu','random94','1','User','1','1'),(95,1,'firstname95','lastname95',NULL,'random@fiu.edu','random95','1','User','1','1'),(96,1,'firstname96','lastname96',NULL,'random@fiu.edu','random96','1','User','1','1'),(97,1,'firstname97','lastname97',NULL,'random@fiu.edu','random97','1','User','1','1'),(98,1,'firstname98','lastname98',NULL,'random@fiu.edu','random98','1','User','1','1'),(99,1,'firstname99','lastname99',NULL,'random@fiu.edu','random99','1','User','1','1'),(100,1,'firstname100','lastname100',NULL,'random@fiu.edu','random100','1','User','1','1'),(101,1,'firstname101','lastname101',NULL,'random@fiu.edu','random101','1','User','1','1'),(102,1,'firstname102','lastname102',NULL,'random@fiu.edu','random102','1','User','1','1'),(103,1,'firstname103','lastname103',NULL,'random@fiu.edu','random103','1','User','1','1');
/*!40000 ALTER TABLE `patron` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `queue`
--

DROP TABLE IF EXISTS `queue`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `queue` (
  `activity_id` int(11) DEFAULT NULL,
  `visitor_id` int(11) DEFAULT NULL,
  `visitor_in_party` int(11) DEFAULT NULL,
  `time_reservation_made` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `reservation_time` time NOT NULL,
  `estimated_time` int(11) DEFAULT NULL,
  `actual_time` time DEFAULT NULL,
  `cancelled_time` time DEFAULT NULL,
  `group_visitor_id` int(11) DEFAULT NULL,
  `last_update` time DEFAULT NULL,
  `last_notification` time DEFAULT NULL,
  `queue_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`queue_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1032 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `queue`
--

LOCK TABLES `queue` WRITE;
/*!40000 ALTER TABLE `queue` DISABLE KEYS */;
INSERT INTO `queue` VALUES (3,4,NULL,'2015-03-22 17:14:26','13:14:26',27,NULL,NULL,NULL,NULL,NULL,289),(3,5,NULL,'2015-03-22 17:14:26','13:14:26',28,NULL,NULL,NULL,NULL,NULL,290),(3,5,NULL,'2015-03-22 17:14:26','13:14:26',28,NULL,NULL,NULL,NULL,NULL,291),(3,6,NULL,'2015-03-22 17:14:26','13:14:26',29,NULL,NULL,NULL,NULL,NULL,292),(3,6,NULL,'2015-03-22 17:14:26','13:14:26',29,NULL,NULL,NULL,NULL,NULL,293),(3,7,NULL,'2015-03-22 17:14:26','13:14:26',30,NULL,NULL,NULL,NULL,NULL,294),(3,7,NULL,'2015-03-22 17:14:26','13:14:26',30,NULL,NULL,NULL,NULL,NULL,295),(3,8,NULL,'2015-03-22 17:14:26','13:14:26',32,NULL,NULL,NULL,NULL,NULL,296),(3,8,NULL,'2015-03-22 17:14:26','13:14:26',32,NULL,NULL,NULL,NULL,NULL,297),(3,9,NULL,'2015-03-22 17:14:26','13:14:26',33,NULL,NULL,NULL,NULL,NULL,298),(3,9,NULL,'2015-03-22 17:14:26','13:14:26',33,NULL,NULL,NULL,NULL,NULL,299),(3,10,NULL,'2015-03-22 17:14:26','13:14:26',34,NULL,NULL,NULL,NULL,NULL,300),(3,10,NULL,'2015-03-22 17:14:26','13:14:26',34,NULL,NULL,NULL,NULL,NULL,301),(3,11,NULL,'2015-03-22 17:14:26','13:14:26',35,NULL,NULL,NULL,NULL,NULL,302),(3,11,NULL,'2015-03-22 17:14:26','13:14:26',36,NULL,NULL,NULL,NULL,NULL,303),(3,12,NULL,'2015-03-22 17:14:26','13:14:26',36,NULL,NULL,NULL,NULL,NULL,304),(3,12,NULL,'2015-03-22 17:14:26','13:14:26',37,NULL,NULL,NULL,NULL,NULL,305),(3,3,NULL,'2015-03-22 17:14:42','13:14:42',37,NULL,NULL,NULL,NULL,NULL,306),(3,4,NULL,'2015-03-22 17:14:42','13:14:42',38,NULL,NULL,NULL,NULL,NULL,307),(3,5,NULL,'2015-03-22 17:14:42','13:14:42',38,NULL,NULL,NULL,NULL,NULL,308),(3,6,NULL,'2015-03-22 17:14:42','13:14:42',39,NULL,NULL,NULL,NULL,NULL,309),(3,7,NULL,'2015-03-22 17:14:42','13:14:42',40,NULL,NULL,NULL,NULL,NULL,310),(3,8,NULL,'2015-03-22 17:14:42','13:14:42',40,NULL,NULL,NULL,NULL,NULL,311),(3,9,NULL,'2015-03-22 17:14:42','13:14:42',41,NULL,NULL,NULL,NULL,NULL,312),(3,10,NULL,'2015-03-22 17:14:42','13:14:42',41,NULL,NULL,NULL,NULL,NULL,313),(3,11,NULL,'2015-03-22 17:14:42','13:14:42',42,NULL,NULL,NULL,NULL,NULL,314),(3,12,NULL,'2015-03-22 17:14:42','13:14:42',42,NULL,NULL,NULL,NULL,NULL,315),(3,3,NULL,'2015-03-22 19:14:05','15:14:05',43,NULL,NULL,NULL,NULL,NULL,320),(3,3,NULL,'2015-03-22 19:14:05','15:14:05',43,NULL,NULL,NULL,NULL,NULL,321),(3,4,NULL,'2015-03-22 19:14:05','15:14:05',44,NULL,NULL,NULL,NULL,NULL,322),(3,4,NULL,'2015-03-22 19:14:05','15:14:05',44,NULL,NULL,NULL,NULL,NULL,323),(3,3,NULL,'2015-03-22 19:14:11','15:14:11',45,NULL,NULL,NULL,NULL,NULL,324),(3,4,NULL,'2015-03-22 19:14:11','15:14:11',46,NULL,NULL,NULL,NULL,NULL,325),(3,3,NULL,'2015-03-22 19:21:59','15:21:59',46,NULL,NULL,NULL,NULL,NULL,326),(3,3,NULL,'2015-03-22 19:21:59','15:21:59',46,NULL,NULL,NULL,NULL,NULL,327),(3,3,NULL,'2015-04-05 17:40:13','13:40:13',25,NULL,NULL,NULL,NULL,NULL,369),(3,3,NULL,'2015-04-05 17:40:13','13:40:13',25,NULL,NULL,NULL,NULL,NULL,370),(3,4,NULL,'2015-04-05 17:40:13','13:40:13',26,NULL,NULL,NULL,NULL,NULL,371),(3,4,NULL,'2015-04-05 17:40:13','13:40:13',26,NULL,NULL,NULL,NULL,NULL,372),(3,5,NULL,'2015-04-05 17:40:13','13:40:13',27,NULL,NULL,NULL,NULL,NULL,373),(3,5,NULL,'2015-04-05 17:40:13','13:40:13',27,NULL,NULL,NULL,NULL,NULL,374),(3,6,NULL,'2015-04-05 17:40:13','13:40:13',28,NULL,NULL,NULL,NULL,NULL,375),(3,6,NULL,'2015-04-05 17:40:13','13:40:13',28,NULL,NULL,NULL,NULL,NULL,376),(3,7,NULL,'2015-04-05 17:40:13','13:40:13',29,NULL,NULL,NULL,NULL,NULL,377),(3,7,NULL,'2015-04-05 17:40:13','13:40:13',29,NULL,NULL,NULL,NULL,NULL,378),(3,8,NULL,'2015-04-05 17:40:13','13:40:13',30,NULL,NULL,NULL,NULL,NULL,379),(3,8,NULL,'2015-04-05 17:40:13','13:40:13',30,NULL,NULL,NULL,NULL,NULL,380),(3,9,NULL,'2015-04-05 17:40:13','13:40:13',32,NULL,NULL,NULL,NULL,NULL,381),(3,9,NULL,'2015-04-05 17:40:13','13:40:13',32,NULL,NULL,NULL,NULL,NULL,382),(3,10,NULL,'2015-04-05 17:40:13','13:40:13',33,NULL,NULL,NULL,NULL,NULL,383),(3,10,NULL,'2015-04-05 17:40:13','13:40:13',33,NULL,NULL,NULL,NULL,NULL,384),(3,11,NULL,'2015-04-05 17:40:13','13:40:13',34,NULL,NULL,NULL,NULL,NULL,385),(3,11,NULL,'2015-04-05 17:40:13','13:40:13',34,NULL,NULL,NULL,NULL,NULL,386),(3,12,NULL,'2015-04-05 17:40:13','13:40:13',35,NULL,NULL,NULL,NULL,NULL,387),(3,12,NULL,'2015-04-05 17:40:13','13:40:13',35,NULL,NULL,NULL,NULL,NULL,388),(2,11,NULL,'2015-04-19 22:33:20','18:33:20',18,NULL,NULL,NULL,NULL,NULL,925),(2,11,NULL,'2015-04-19 22:33:20','18:33:20',18,NULL,NULL,NULL,NULL,NULL,926),(2,12,NULL,'2015-04-19 22:33:20','18:33:20',21,NULL,NULL,NULL,NULL,NULL,927),(2,12,NULL,'2015-04-19 22:33:20','18:33:20',21,NULL,NULL,NULL,NULL,NULL,928),(2,3,NULL,'2015-04-19 22:36:10','18:36:10',5,NULL,NULL,NULL,NULL,NULL,930),(2,4,NULL,'2015-04-19 22:36:10','18:36:10',6,NULL,NULL,NULL,NULL,NULL,931),(2,5,NULL,'2015-04-19 22:36:10','18:36:10',7,NULL,NULL,NULL,NULL,NULL,932),(2,6,NULL,'2015-04-19 22:36:10','18:36:10',8,NULL,NULL,NULL,NULL,NULL,933),(2,7,NULL,'2015-04-19 22:36:10','18:36:10',9,NULL,NULL,NULL,NULL,NULL,934),(2,8,NULL,'2015-04-19 22:36:10','18:36:10',11,NULL,NULL,NULL,NULL,NULL,935),(2,9,NULL,'2015-04-19 22:36:10','18:36:10',12,NULL,NULL,NULL,NULL,NULL,936),(2,10,NULL,'2015-04-19 22:36:10','18:36:10',13,NULL,NULL,NULL,NULL,NULL,937),(2,11,NULL,'2015-04-19 22:36:10','18:36:10',14,NULL,NULL,NULL,NULL,NULL,938),(2,12,NULL,'2015-04-19 22:36:10','18:36:10',15,NULL,NULL,NULL,NULL,NULL,939),(2,13,NULL,'2015-04-19 22:36:10','18:36:10',16,NULL,NULL,NULL,NULL,NULL,940),(2,14,NULL,'2015-04-19 22:36:10','18:36:10',17,NULL,NULL,NULL,NULL,NULL,941),(2,15,NULL,'2015-04-19 22:36:10','18:36:10',18,NULL,NULL,NULL,NULL,NULL,942),(2,16,NULL,'2015-04-19 22:36:10','18:36:10',20,NULL,NULL,NULL,NULL,NULL,943),(2,17,NULL,'2015-04-19 22:36:10','18:36:10',21,NULL,NULL,NULL,NULL,NULL,944),(2,18,NULL,'2015-04-19 22:36:10','18:36:10',22,NULL,NULL,NULL,NULL,NULL,945),(1,6,NULL,'2015-04-26 16:19:57','12:19:57',19,NULL,NULL,NULL,NULL,NULL,1011),(1,7,NULL,'2015-04-26 16:19:57','12:19:57',20,NULL,NULL,NULL,NULL,NULL,1012),(1,3,NULL,'2015-04-26 21:24:50','17:24:50',15,NULL,NULL,NULL,NULL,NULL,1020),(1,3,NULL,'2015-04-26 21:24:50','17:24:50',15,NULL,NULL,NULL,NULL,NULL,1021),(1,4,NULL,'2015-04-26 21:24:50','17:24:50',16,NULL,NULL,NULL,NULL,NULL,1022),(1,4,NULL,'2015-04-26 21:24:50','17:24:50',16,NULL,NULL,NULL,NULL,NULL,1023),(1,3,NULL,'2015-04-26 21:28:32','17:28:32',18,NULL,NULL,NULL,NULL,NULL,1024),(1,4,NULL,'2015-04-26 21:28:32','17:28:32',18,NULL,NULL,NULL,NULL,NULL,1025),(1,3,NULL,'2015-04-26 21:29:20','17:29:20',19,NULL,NULL,NULL,NULL,NULL,1026),(1,4,NULL,'2015-04-26 21:29:20','17:29:20',20,NULL,NULL,NULL,NULL,NULL,1027),(1,3,NULL,'2015-04-26 21:31:42','17:31:42',15,NULL,NULL,NULL,NULL,NULL,1028),(1,4,NULL,'2015-04-26 21:31:42','17:31:42',15,NULL,NULL,NULL,NULL,NULL,1029),(1,2,NULL,'2015-04-27 02:52:10','22:52:10',10,NULL,NULL,NULL,NULL,NULL,1030),(2,2,NULL,'2015-04-27 02:52:19','22:52:19',24,NULL,NULL,NULL,NULL,NULL,1031);
/*!40000 ALTER TABLE `queue` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `restrictiongroup`
--

DROP TABLE IF EXISTS `restrictiongroup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `restrictiongroup` (
  `restriction_group_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `venue_id` int(11) DEFAULT NULL,
  `name_rest` varchar(50) NOT NULL,
  `min_age` int(11) DEFAULT NULL,
  `max_age` int(11) DEFAULT NULL,
  `min_height` int(11) DEFAULT NULL,
  `max_height` int(11) DEFAULT NULL,
  `min_weight` int(11) DEFAULT NULL,
  `max_weight` int(11) DEFAULT NULL,
  `other_restriction` varchar(50) DEFAULT NULL,
  `disclaimer` blob,
  `waiver` blob,
  PRIMARY KEY (`restriction_group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `restrictiongroup`
--

LOCK TABLES `restrictiongroup` WRITE;
/*!40000 ALTER TABLE `restrictiongroup` DISABLE KEYS */;
/*!40000 ALTER TABLE `restrictiongroup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `scheduledate`
--

DROP TABLE IF EXISTS `scheduledate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `scheduledate` (
  `type_schedule_day` enum('1','2','3') DEFAULT NULL,
  `schedule_date_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `calendar_date` date DEFAULT NULL,
  `first_event_start` date DEFAULT NULL,
  `last_even_start` date DEFAULT NULL,
  `closed` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`schedule_date_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `scheduledate`
--

LOCK TABLES `scheduledate` WRITE;
/*!40000 ALTER TABLE `scheduledate` DISABLE KEYS */;
/*!40000 ALTER TABLE `scheduledate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `scheduleday`
--

DROP TABLE IF EXISTS `scheduleday`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `scheduleday` (
  `type_schedule_day` enum('1','2','3') DEFAULT NULL,
  `schedule_day_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `day_of_the_week` varchar(30) DEFAULT NULL,
  `first_event_start` date DEFAULT NULL,
  `last_even_start` date DEFAULT NULL,
  `closed` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`schedule_day_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `scheduleday`
--

LOCK TABLES `scheduleday` WRITE;
/*!40000 ALTER TABLE `scheduleday` DISABLE KEYS */;
/*!40000 ALTER TABLE `scheduleday` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `schedulegroup`
--

DROP TABLE IF EXISTS `schedulegroup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `schedulegroup` (
  `schedule_group_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `venue_id` int(11) DEFAULT NULL,
  `name_schedulegroup` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`schedule_group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `schedulegroup`
--

LOCK TABLES `schedulegroup` WRITE;
/*!40000 ALTER TABLE `schedulegroup` DISABLE KEYS */;
/*!40000 ALTER TABLE `schedulegroup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `venue`
--

DROP TABLE IF EXISTS `venue`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `venue` (
  `venue_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(35) NOT NULL,
  `max_queue_per_guest` int(11) DEFAULT '3',
  `restrictionsackrequired` enum('Y','N') DEFAULT NULL,
  `disclaimer_ack_required` enum('Y','N') DEFAULT NULL,
  `waiver_ack_required` enum('Y','N') DEFAULT NULL,
  `max_wait_per_acivity` int(11) NOT NULL,
  `metrics` enum('Y','N') DEFAULT NULL,
  PRIMARY KEY (`venue_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `venue`
--

LOCK TABLES `venue` WRITE;
/*!40000 ALTER TABLE `venue` DISABLE KEYS */;
INSERT INTO `venue` VALUES (1,'Disney World',100,'Y','N','N',60,'Y');
/*!40000 ALTER TABLE `venue` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `visitor`
--

DROP TABLE IF EXISTS `visitor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `visitor` (
  `visitor_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `patron_id` int(11) DEFAULT NULL,
  `contact_phone` int(11) NOT NULL,
  `contact_email` varchar(45) NOT NULL,
  `contact_other` varchar(45) DEFAULT NULL,
  `contact_method` enum('E','P') DEFAULT NULL,
  `time_in` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `time_out` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `group_leader` varchar(20) NOT NULL,
  `visitors_in_party` int(11) DEFAULT NULL,
  PRIMARY KEY (`visitor_id`)
) ENGINE=InnoDB AUTO_INCREMENT=104 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `visitor`
--

LOCK TABLES `visitor` WRITE;
/*!40000 ALTER TABLE `visitor` DISABLE KEYS */;
INSERT INTO `visitor` VALUES (1,1,911,'kkon001@fiu.edu',NULL,NULL,'2015-02-24 17:33:36','2015-02-24 17:33:36','Kenny',1),(2,2,911,'kkon001@fiu.edu',NULL,NULL,'2015-02-24 16:34:32','2015-02-24 18:34:32','Lazo',1),(3,3,911,'random@gmail.com',NULL,NULL,'2015-03-07 16:34:32','2015-03-07 18:34:32','Random Person',1),(4,4,911,'random@gmail.com',NULL,NULL,'2015-03-07 16:34:32','2015-03-07 18:34:32','Random Person',1),(5,5,911,'random@gmail.com',NULL,NULL,'2015-03-07 16:34:32','2015-03-07 18:34:32','Random Person',1),(6,6,911,'random@gmail.com',NULL,NULL,'2015-03-07 16:34:32','2015-03-07 18:34:32','Random Person',1),(7,7,911,'random@gmail.com',NULL,NULL,'2015-03-07 16:34:32','2015-03-07 18:34:32','Random Person',1),(8,8,911,'random@gmail.com',NULL,NULL,'2015-03-07 16:34:32','2015-03-07 18:34:32','Random Person',1),(9,9,911,'random@gmail.com',NULL,NULL,'2015-03-07 16:34:32','2015-03-07 18:34:32','Random Person',1),(10,10,911,'random@gmail.com',NULL,NULL,'2015-03-07 16:34:32','2015-03-07 18:34:32','Random Person',1),(11,11,911,'random@gmail.com',NULL,NULL,'2015-03-07 16:34:32','2015-03-07 18:34:32','Random Person',1),(12,12,911,'random@gmail.com',NULL,NULL,'2015-03-07 16:34:32','2015-03-07 18:34:32','Random Person',1),(13,13,911,'random@gmail.com',NULL,NULL,'2015-03-07 16:34:32','2015-03-07 18:34:32','Random Person',1),(14,14,911,'random@gmail.com',NULL,NULL,'2015-03-07 16:34:32','2015-03-07 18:34:32','Random Person',1),(15,15,911,'random@gmail.com',NULL,NULL,'2015-03-07 16:34:32','2015-03-07 18:34:32','Random Person',1),(16,16,911,'random@gmail.com',NULL,NULL,'2015-03-07 16:34:32','2015-03-07 18:34:32','Random Person',1),(17,17,911,'random@gmail.com',NULL,NULL,'2015-03-07 16:34:32','2015-03-07 18:34:32','Random Person',1),(18,18,911,'random@gmail.com',NULL,NULL,'2015-03-07 16:34:32','2015-03-07 18:34:32','Random Person',1),(19,19,911,'random@gmail.com',NULL,NULL,'2015-03-07 16:34:32','2015-03-07 18:34:32','Random Person',1),(20,20,911,'random@gmail.com',NULL,NULL,'2015-03-07 16:34:32','2015-03-07 18:34:32','Random Person',1),(21,21,911,'random@gmail.com',NULL,NULL,'2015-03-07 16:34:32','2015-03-07 18:34:32','Random Person',1),(22,22,911,'random@gmail.com',NULL,NULL,'2015-03-07 16:34:32','2015-03-07 18:34:32','Random Person',1),(23,23,911,'random@gmail.com',NULL,NULL,'2015-03-07 16:34:32','2015-03-07 18:34:32','Random Person',1),(24,24,911,'random@gmail.com',NULL,NULL,'2015-03-07 16:34:32','2015-03-07 18:34:32','Random Person',1),(25,25,911,'random@gmail.com',NULL,NULL,'2015-03-07 16:34:32','2015-03-07 18:34:32','Random Person',1),(26,26,911,'random@gmail.com',NULL,NULL,'2015-03-07 16:34:32','2015-03-07 18:34:32','Random Person',1),(27,27,911,'random@gmail.com',NULL,NULL,'2015-03-07 16:34:32','2015-03-07 18:34:32','Random Person',1),(28,28,911,'random@gmail.com',NULL,NULL,'2015-03-07 16:34:32','2015-03-07 18:34:32','Random Person',1),(29,29,911,'random@gmail.com',NULL,NULL,'2015-03-07 16:34:32','2015-03-07 18:34:32','Random Person',1),(30,30,911,'random@gmail.com',NULL,NULL,'2015-03-07 16:34:32','2015-03-07 18:34:32','Random Person',1),(31,31,911,'random@gmail.com',NULL,NULL,'2015-03-07 16:34:32','2015-03-07 18:34:32','Random Person',1),(32,32,911,'random@gmail.com',NULL,NULL,'2015-03-07 16:34:32','2015-03-07 18:34:32','Random Person',1),(33,33,911,'random@gmail.com',NULL,NULL,'2015-03-07 16:34:32','2015-03-07 18:34:32','Random Person',1),(34,34,911,'random@gmail.com',NULL,NULL,'2015-03-07 16:34:32','2015-03-07 18:34:32','Random Person',1),(35,35,911,'random@gmail.com',NULL,NULL,'2015-03-07 16:34:32','2015-03-07 18:34:32','Random Person',1),(36,36,911,'random@gmail.com',NULL,NULL,'2015-03-07 16:34:32','2015-03-07 18:34:32','Random Person',1),(37,37,911,'random@gmail.com',NULL,NULL,'2015-03-07 16:34:32','2015-03-07 18:34:32','Random Person',1),(38,38,911,'random@gmail.com',NULL,NULL,'2015-03-07 16:34:32','2015-03-07 18:34:32','Random Person',1),(39,39,911,'random@gmail.com',NULL,NULL,'2015-03-07 16:34:32','2015-03-07 18:34:32','Random Person',1),(40,40,911,'random@gmail.com',NULL,NULL,'2015-03-07 16:34:32','2015-03-07 18:34:32','Random Person',1),(41,41,911,'random@gmail.com',NULL,NULL,'2015-03-07 16:34:32','2015-03-07 18:34:32','Random Person',1),(42,42,911,'random@gmail.com',NULL,NULL,'2015-03-07 16:34:32','2015-03-07 18:34:32','Random Person',1),(43,43,911,'random@gmail.com',NULL,NULL,'2015-03-07 16:34:32','2015-03-07 18:34:32','Random Person',1),(44,44,911,'random@gmail.com',NULL,NULL,'2015-03-07 16:34:32','2015-03-07 18:34:32','Random Person',1),(45,45,911,'random@gmail.com',NULL,NULL,'2015-03-07 16:34:32','2015-03-07 18:34:32','Random Person',1),(46,46,911,'random@gmail.com',NULL,NULL,'2015-03-07 16:34:32','2015-03-07 18:34:32','Random Person',1),(47,47,911,'random@gmail.com',NULL,NULL,'2015-03-07 16:34:32','2015-03-07 18:34:32','Random Person',1),(48,48,911,'random@gmail.com',NULL,NULL,'2015-03-07 16:34:32','2015-03-07 18:34:32','Random Person',1),(49,49,911,'random@gmail.com',NULL,NULL,'2015-03-07 16:34:32','2015-03-07 18:34:32','Random Person',1),(50,50,911,'random@gmail.com',NULL,NULL,'2015-03-07 16:34:32','2015-03-07 18:34:32','Random Person',1),(51,51,911,'random@gmail.com',NULL,NULL,'2015-03-07 16:34:32','2015-03-07 18:34:32','Random Person',1),(52,52,911,'random@gmail.com',NULL,NULL,'2015-03-07 16:34:32','2015-03-07 18:34:32','Random Person',1),(53,53,911,'random@gmail.com',NULL,NULL,'2015-03-07 16:34:32','2015-03-07 18:34:32','Random Person',1),(54,54,911,'random@gmail.com',NULL,NULL,'2015-03-07 16:34:32','2015-03-07 18:34:32','Random Person',1),(55,55,911,'random@gmail.com',NULL,NULL,'2015-03-07 16:34:32','2015-03-07 18:34:32','Random Person',1),(56,56,911,'random@gmail.com',NULL,NULL,'2015-03-07 16:34:32','2015-03-07 18:34:32','Random Person',1),(57,57,911,'random@gmail.com',NULL,NULL,'2015-03-07 16:34:32','2015-03-07 18:34:32','Random Person',1),(58,58,911,'random@gmail.com',NULL,NULL,'2015-03-07 16:34:32','2015-03-07 18:34:32','Random Person',1),(59,59,911,'random@gmail.com',NULL,NULL,'2015-03-07 16:34:32','2015-03-07 18:34:32','Random Person',1),(60,60,911,'random@gmail.com',NULL,NULL,'2015-03-07 16:34:32','2015-03-07 18:34:32','Random Person',1),(61,61,911,'random@gmail.com',NULL,NULL,'2015-03-07 16:34:32','2015-03-07 18:34:32','Random Person',1),(62,62,911,'random@gmail.com',NULL,NULL,'2015-03-07 16:34:32','2015-03-07 18:34:32','Random Person',1),(63,63,911,'random@gmail.com',NULL,NULL,'2015-03-07 16:34:32','2015-03-07 18:34:32','Random Person',1),(64,64,911,'random@gmail.com',NULL,NULL,'2015-03-07 16:34:32','2015-03-07 18:34:32','Random Person',1),(65,65,911,'random@gmail.com',NULL,NULL,'2015-03-07 16:34:32','2015-03-07 18:34:32','Random Person',1),(66,66,911,'random@gmail.com',NULL,NULL,'2015-03-07 16:34:32','2015-03-07 18:34:32','Random Person',1),(67,67,911,'random@gmail.com',NULL,NULL,'2015-03-07 16:34:32','2015-03-07 18:34:32','Random Person',1),(68,68,911,'random@gmail.com',NULL,NULL,'2015-03-07 16:34:32','2015-03-07 18:34:32','Random Person',1),(69,69,911,'random@gmail.com',NULL,NULL,'2015-03-07 16:34:32','2015-03-07 18:34:32','Random Person',1),(70,70,911,'random@gmail.com',NULL,NULL,'2015-03-07 16:34:32','2015-03-07 18:34:32','Random Person',1),(71,71,911,'random@gmail.com',NULL,NULL,'2015-03-07 16:34:32','2015-03-07 18:34:32','Random Person',1),(72,72,911,'random@gmail.com',NULL,NULL,'2015-03-07 16:34:32','2015-03-07 18:34:32','Random Person',1),(73,73,911,'random@gmail.com',NULL,NULL,'2015-03-07 16:34:32','2015-03-07 18:34:32','Random Person',1),(74,74,911,'random@gmail.com',NULL,NULL,'2015-03-07 16:34:32','2015-03-07 18:34:32','Random Person',1),(75,75,911,'random@gmail.com',NULL,NULL,'2015-03-07 16:34:32','2015-03-07 18:34:32','Random Person',1),(76,76,911,'random@gmail.com',NULL,NULL,'2015-03-07 16:34:32','2015-03-07 18:34:32','Random Person',1),(77,77,911,'random@gmail.com',NULL,NULL,'2015-03-07 16:34:32','2015-03-07 18:34:32','Random Person',1),(78,78,911,'random@gmail.com',NULL,NULL,'2015-03-07 16:34:32','2015-03-07 18:34:32','Random Person',1),(79,79,911,'random@gmail.com',NULL,NULL,'2015-03-07 16:34:32','2015-03-07 18:34:32','Random Person',1),(80,80,911,'random@gmail.com',NULL,NULL,'2015-03-07 16:34:32','2015-03-07 18:34:32','Random Person',1),(81,81,911,'random@gmail.com',NULL,NULL,'2015-03-07 16:34:32','2015-03-07 18:34:32','Random Person',1),(82,82,911,'random@gmail.com',NULL,NULL,'2015-03-07 16:34:32','2015-03-07 18:34:32','Random Person',1),(83,83,911,'random@gmail.com',NULL,NULL,'2015-03-07 16:34:32','2015-03-07 18:34:32','Random Person',1),(84,84,911,'random@gmail.com',NULL,NULL,'2015-03-07 16:34:32','2015-03-07 18:34:32','Random Person',1),(85,85,911,'random@gmail.com',NULL,NULL,'2015-03-07 16:34:32','2015-03-07 18:34:32','Random Person',1),(86,86,911,'random@gmail.com',NULL,NULL,'2015-03-07 16:34:32','2015-03-07 18:34:32','Random Person',1),(87,87,911,'random@gmail.com',NULL,NULL,'2015-03-07 16:34:32','2015-03-07 18:34:32','Random Person',1),(88,88,911,'random@gmail.com',NULL,NULL,'2015-03-07 16:34:32','2015-03-07 18:34:32','Random Person',1),(89,89,911,'random@gmail.com',NULL,NULL,'2015-03-07 16:34:32','2015-03-07 18:34:32','Random Person',1),(90,90,911,'random@gmail.com',NULL,NULL,'2015-03-07 16:34:32','2015-03-07 18:34:32','Random Person',1),(91,91,911,'random@gmail.com',NULL,NULL,'2015-03-07 16:34:32','2015-03-07 18:34:32','Random Person',1),(92,92,911,'random@gmail.com',NULL,NULL,'2015-03-07 16:34:32','2015-03-07 18:34:32','Random Person',1),(93,93,911,'random@gmail.com',NULL,NULL,'2015-03-07 16:34:32','2015-03-07 18:34:32','Random Person',1),(94,94,911,'random@gmail.com',NULL,NULL,'2015-03-07 16:34:32','2015-03-07 18:34:32','Random Person',1),(95,95,911,'random@gmail.com',NULL,NULL,'2015-03-07 16:34:32','2015-03-07 18:34:32','Random Person',1),(96,96,911,'random@gmail.com',NULL,NULL,'2015-03-07 16:34:32','2015-03-07 18:34:32','Random Person',1),(97,97,911,'random@gmail.com',NULL,NULL,'2015-03-07 16:34:32','2015-03-07 18:34:32','Random Person',1),(98,98,911,'random@gmail.com',NULL,NULL,'2015-03-07 16:34:32','2015-03-07 18:34:32','Random Person',1),(99,99,911,'random@gmail.com',NULL,NULL,'2015-03-07 16:34:32','2015-03-07 18:34:32','Random Person',1),(100,100,911,'random@gmail.com',NULL,NULL,'2015-03-07 16:34:32','2015-03-07 18:34:32','Random Person',1),(101,101,911,'random@gmail.com',NULL,NULL,'2015-03-07 16:34:32','2015-03-07 18:34:32','Random Person',1),(102,102,911,'random@gmail.com',NULL,NULL,'2015-03-07 16:34:32','2015-03-07 18:34:32','Random Person',1),(103,103,911,'random@gmail.com',NULL,NULL,'2015-03-07 16:34:32','2015-03-07 18:34:32','Random Person',1);
/*!40000 ALTER TABLE `visitor` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-04-29 15:47:14
