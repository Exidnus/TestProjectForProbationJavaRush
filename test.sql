-- MySQL dump 10.13  Distrib 5.5.47, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: test
-- ------------------------------------------------------
-- Server version	5.5.47-0ubuntu0.14.04.1

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
-- Current Database: `test`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `test` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `test`;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `name` varchar(25) NOT NULL,
  `age` int(11) DEFAULT NULL,
  `isAdmin` tinyint(4) NOT NULL DEFAULT '0',
  `createdDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (20,'Мишаня',15,1,'2016-01-09 12:22:15'),(25,'Василий',21,1,'2016-01-09 17:12:01'),(27,'Геральт',56,0,'2016-01-10 15:25:10'),(28,'Максим',45,0,'2016-01-11 05:19:23'),(35,'Маргарита',45,1,'2016-01-11 16:09:24'),(36,'Сима',26,0,'2016-01-12 11:05:22'),(40,'Владимир',46,1,'2016-01-12 11:41:33'),(41,'Жора',22,0,'2016-01-12 11:43:31'),(42,'Михаил',22,0,'2016-01-12 11:57:15'),(44,'Василиса',74,0,'2016-01-13 12:09:48'),(45,'Симеон',43,1,'2016-01-13 12:13:03'),(48,'Коля',22,1,'2016-01-13 12:16:35'),(51,'Валера',11,0,'2016-01-20 09:25:43'),(52,'Жора',13,0,'2016-01-20 09:32:27'),(53,'Полинушка',23,0,'2016-01-21 05:13:41'),(55,'Колян',12,0,'2016-01-21 05:26:59'),(56,'Петр',133,1,'2016-01-21 06:00:37'),(57,'Карл',48,1,'2016-01-21 13:58:02'),(58,'Маруся',45,0,'2016-01-22 07:37:18'),(59,'Людмила',45,1,'2016-01-22 07:51:43'),(60,'Антуан',24,0,'2016-01-24 08:46:08'),(61,'Владислав',44,0,'2016-01-24 10:28:39'),(62,'Андрей',27,0,'2016-01-24 12:04:21');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-02-03 19:17:48
