-- MySQL dump 10.13  Distrib 5.7.16, for Linux (x86_64)
--
-- Host: localhost    Database: hostel_world
-- ------------------------------------------------------
-- Server version	5.7.16-0ubuntu0.16.04.1

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
-- Table structure for table `application_modify`
--

DROP TABLE IF EXISTS `application_modify`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `application_modify` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `hostelID` varchar(7) NOT NULL,
  `name_before` varchar(50) NOT NULL,
  `address_before` varchar(100) NOT NULL,
  `name_after` varchar(50) NOT NULL,
  `address_after` varchar(100) NOT NULL,
  `pass` enum('pass','reject','waiting') NOT NULL DEFAULT 'waiting',
  `notes` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `application_modify`
--

LOCK TABLES `application_modify` WRITE;
/*!40000 ALTER TABLE `application_modify` DISABLE KEYS */;
INSERT INTO `application_modify` VALUES (1,'h000003','南京金陵饭店','南京 鼓楼区 汉中路2号','南京金陵饭店','南京 鼓楼区 汉中路2号','pass',NULL),(2,'h000009','酒店4','123','南京酒店','南京','pass',NULL),(3,'h000009','酒店4','123','南京','123','pass',NULL);
/*!40000 ALTER TABLE `application_modify` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `application_open`
--

DROP TABLE IF EXISTS `application_open`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `application_open` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `hostelID` varchar(7) NOT NULL,
  `pass` enum('pass','reject','waiting') NOT NULL DEFAULT 'waiting',
  `notes` varchar(100) DEFAULT NULL,
  `name` varchar(50) NOT NULL DEFAULT '',
  `address` varchar(100) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `application_open`
--

LOCK TABLES `application_open` WRITE;
/*!40000 ALTER TABLE `application_open` DISABLE KEYS */;
INSERT INTO `application_open` VALUES (5,'h000002','pass',NULL,'',''),(6,'h000003','pass',NULL,'',''),(7,'h000004','pass',NULL,'',''),(8,'h000005','pass',NULL,'',''),(9,'h000001','pass',NULL,'',''),(11,'h000006','waiting',NULL,'酒店','123'),(12,'h000007','waiting',NULL,'酒店1','123'),(13,'h000008','waiting',NULL,'酒店2','123'),(14,'h000009','waiting',NULL,'酒店4','123');
/*!40000 ALTER TABLE `application_open` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `check_in`
--

DROP TABLE IF EXISTS `check_in`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `check_in` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cardNum` varchar(7) DEFAULT NULL,
  `hostelID` varchar(7) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `roomID` varchar(7) NOT NULL,
  `startDate` date NOT NULL,
  `endDate` date DEFAULT NULL,
  `type` enum('single','normal') DEFAULT NULL,
  `orderID` int(11) DEFAULT NULL,
  `payType` enum('card','crash') DEFAULT NULL,
  `price` double NOT NULL,
  `discount` double DEFAULT NULL,
  `money` double DEFAULT NULL,
  `finish` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `check_in`
--

LOCK TABLES `check_in` WRITE;
/*!40000 ALTER TABLE `check_in` DISABLE KEYS */;
INSERT INTO `check_in` VALUES (1,NULL,'h000003','test','1','2017-03-22','2017-03-22','single',0,'crash',100,0,200,0),(2,NULL,'h000003','test，123','12','2017-03-22','2017-03-22','single',0,'crash',100,0,300,0),(3,'0000001','h000003',NULL,'123','2017-03-22','2017-03-22','single',0,'card',100,1,800,1),(4,'0000004','h000009',NULL,'1','2017-03-24','2017-03-24','normal',6,'card',200,0.9,720,1),(5,NULL,'h000009','1, 2','2','2017-03-24','2017-03-28','single',0,NULL,100,0,400,0);
/*!40000 ALTER TABLE `check_in` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hostel`
--

DROP TABLE IF EXISTS `hostel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hostel` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `hostelID` varchar(7) NOT NULL,
  `name` varchar(50) NOT NULL,
  `address` varchar(100) NOT NULL,
  `password` varchar(64) NOT NULL,
  `active` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hostel`
--

LOCK TABLES `hostel` WRITE;
/*!40000 ALTER TABLE `hostel` DISABLE KEYS */;
INSERT INTO `hostel` VALUES (1,'h000001','南京中心大酒店','南京 鼓楼区 中山路75号','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3',1),(2,'h000002','南京新纪元大酒店','南京 鼓楼区 中山路251号-1','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3',1),(3,'h000003','南京金陵饭店','南京 鼓楼区 汉中路2号','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3',1),(4,'h000004','南京绿地洲际酒店','江苏省南京市鼓楼区中央路1号','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3',1),(5,'h000005','金鹰珠江壹号国际酒店','江苏省南京市玄武区珠江路1号','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3',1),(6,'h000006','酒店','123','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3',1),(7,'h000007','酒店1','123','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3',1),(8,'h000008','酒店2','123','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3',1),(9,'h000009','酒店4','123','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3',1);
/*!40000 ALTER TABLE `hostel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `manager`
--

DROP TABLE IF EXISTS `manager`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `manager` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `password` varchar(64) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `manager`
--

LOCK TABLES `manager` WRITE;
/*!40000 ALTER TABLE `manager` DISABLE KEYS */;
INSERT INTO `manager` VALUES (1,'manager','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3');
/*!40000 ALTER TABLE `manager` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `payment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `hostelID` varchar(7) NOT NULL,
  `amount` double NOT NULL DEFAULT '0',
  `date` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
INSERT INTO `payment` VALUES (1,'h000003',100,'2017-03-22'),(2,'h000009',980,'2017-03-24'),(3,'h000009',1230,'2017-03-24'),(4,'h000009',235,'2017-03-24'),(5,'h000003',300,'2017-03-26'),(6,'h000001',340,'2017-03-27'),(7,'h000002',5320,'2017-03-26');
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `plan`
--

DROP TABLE IF EXISTS `plan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `plan` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `hostelID` varchar(7) NOT NULL,
  `startDate` date NOT NULL,
  `endDate` date NOT NULL,
  `single` int(11) NOT NULL,
  `normal` int(11) NOT NULL,
  `singlePrice` double NOT NULL,
  `normalPrice` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `plan`
--

LOCK TABLES `plan` WRITE;
/*!40000 ALTER TABLE `plan` DISABLE KEYS */;
INSERT INTO `plan` VALUES (1,'h000003','2017-04-21','2017-04-28',7,9,100,210),(2,'h000003','2017-05-01','2017-05-07',10,10,100,220),(3,'h000003','2017-03-10','2017-04-20',19,20,100,210),(4,'h000003','2018-03-01','2018-03-30',10,20,100,500),(5,'h000009','2017-03-10','2017-04-20',9,9,100,200);
/*!40000 ALTER TABLE `plan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room_order`
--

DROP TABLE IF EXISTS `room_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `room_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cardNum` varchar(7) NOT NULL,
  `hostelID` varchar(7) NOT NULL,
  `startDate` date NOT NULL,
  `endDate` date NOT NULL,
  `type` enum('single','normal') DEFAULT NULL,
  `finish` tinyint(1) NOT NULL DEFAULT '0',
  `amount` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room_order`
--

LOCK TABLES `room_order` WRITE;
/*!40000 ALTER TABLE `room_order` DISABLE KEYS */;
INSERT INTO `room_order` VALUES (4,'0000001','h000003','2017-04-21','2017-04-28','single',0,700),(6,'0000004','h000009','2017-03-24','2017-03-28','normal',0,1000),(7,'0000001','h000003','2017-03-29','2017-03-31','single',0,300);
/*!40000 ALTER TABLE `room_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cardNum` varchar(7) NOT NULL,
  `password` varchar(64) NOT NULL,
  `bankCard` varchar(30) NOT NULL,
  `balance` double NOT NULL DEFAULT '0',
  `sum` double NOT NULL DEFAULT '0',
  `rate` enum('白金卡','金卡','银卡','象牙卡') NOT NULL DEFAULT '象牙卡',
  `name` varchar(50) NOT NULL,
  `phoneNum` varchar(20) NOT NULL,
  `active` tinyint(1) NOT NULL DEFAULT '0',
  `validity` date NOT NULL,
  `stopDate` date DEFAULT NULL,
  `discount` double NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'0000001','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','12345678',8400,1800,'银卡','bedisdover','13276631257',1,'2018-02-19',NULL,0.9),(3,'0000000','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','123',0,0,'象牙卡','manager','12',1,'2222-02-22',NULL,1),(8,'0000002','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','1',0,0,'象牙卡','test','123',0,'2018-03-15',NULL,1),(9,'0000003','6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b','1',0,0,'象牙卡','1','1',0,'2018-03-15',NULL,1),(10,'0000004','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','123',8500,1500,'银卡','会员123','12382349',0,'2018-03-24',NULL,0.9);
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

-- Dump completed on 2017-06-12 14:49:52
