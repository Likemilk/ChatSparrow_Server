-- MySQL dump 10.13  Distrib 5.6.25, for Win64 (x86_64)
--
-- Host: localhost    Database: chatsparrow
-- ------------------------------------------------------
-- Server version	5.6.25-log

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
-- Table structure for table `pirate`
--

DROP TABLE IF EXISTS `pirate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pirate` (
  `group` char(200) NOT NULL,
  `money` int(11) NOT NULL,
  `member` int(11) NOT NULL,
  `date` datetime NOT NULL,
  PRIMARY KEY (`group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pirate`
--

LOCK TABLES `pirate` WRITE;
/*!40000 ALTER TABLE `pirate` DISABLE KEYS */;
INSERT INTO `pirate` VALUES ('111',100,1,'2015-11-15 18:01:22'),('11111111',100,1,'2015-11-14 23:25:01'),('12',100,1,'2015-11-15 17:53:19'),('1231',100,1,'2015-11-14 23:27:46'),('123123',100,1,'2015-11-14 23:26:43'),('123212',100,1,'2015-11-14 23:49:38'),('aaaaaaaa',100,1,'2015-11-15 18:08:39'),('Bastard',100,1,'2015-11-15 22:51:16'),('bbbbbbbb',100,1,'2015-11-15 18:11:15'),('cccccc',100,1,'2015-11-15 18:12:28'),('ChatSparrow',60100,3,'2015-11-08 00:00:00'),('eeeeeee',100,1,'2015-11-15 18:17:38'),('hello',100,1,'2015-11-15 17:50:33'),('JackSparrow!!',100,1,'2015-11-26 00:44:35'),('likemilk',100,1,'2015-11-17 15:43:37');
/*!40000 ALTER TABLE `pirate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` char(100) NOT NULL,
  `password` char(100) NOT NULL,
  `group` char(200) NOT NULL,
  `money` int(11) NOT NULL,
  `login` tinyint(4) NOT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('11111111','11111111','11111111',100,0,'2015-11-14'),('12312311','12312311','1231',100,0,'2015-11-14'),('123123123','123123123','123123',-4925,0,'2015-11-14'),('12341234','12341234','hello',-615,0,'2015-11-15'),('123412341','12341234','12',100,0,'2015-11-15'),('321321321','321321321','111',100,0,'2015-11-15'),('345345345','345345345','likemilk',-115,0,'2015-11-17'),('aaaaaaaa','aaaaaaaa','aaaaaaaa',100,0,'2015-11-15'),('bbbbbbbb','bbbbbbbb','bbbbbbbb',100,0,'2015-11-15'),('cccccccc','cccccccc','cccccc',-230,0,'2015-11-15'),('dydwls121200','dydrkf45','ChatSparrow',27120,0,'2015-11-08'),('eeeeeeee','eeeeeeee','eeeeeee',100,0,'2015-11-15'),('JackJack','123123123','JackSparrow!!',-800,0,'2015-11-26'),('likemilk','choco2323','ChatSparrow',30000,0,'2015-11-08'),('Pink Floyd','[C@47125a42','ChatSparrow',-735,0,'2015-11-17');
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

-- Dump completed on 2015-11-26  1:59:50
