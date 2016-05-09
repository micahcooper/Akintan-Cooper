-- MySQL dump 10.13  Distrib 5.7.11, for Win64 (x86_64)
--
-- Host: localhost    Database: silverpotato
-- ------------------------------------------------------
-- Server version	5.7.11

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
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer` (
  `idnumber` int(11) NOT NULL AUTO_INCREMENT,
  `firstname` varchar(45) NOT NULL,
  `lastname` varchar(45) NOT NULL,
  `dob` varchar(10) DEFAULT NULL,
  `password` varchar(100) NOT NULL,
  `username` varchar(20) NOT NULL,
  `sessionid` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idnumber`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'Bob','Smith','1991-01-01','1234','bob',NULL),(2,'Sue','Kelly','1991-01-01','1235','sue',NULL),(3,'micah','cooper','1990-01-01','A6xnQhbz4Vx2HuGl4lXwZ5U2I8iziLRFnhP5eNfIRvQ=','mrc','AF9DE35933B9C782CA255AC8BD66FEB8'),(4,'a','a','1990-01-01','8OTC92xYkW7CWPJGhRvqCR0U1CR6L8PhhpRGGxgW4Ts=','a',NULL),(6,'b','b','b','PiPoFgA5WUoziU9lZOGxNIu9egCI1CxKy3PurtWcAJ0=','b','135B0E7CF05E1F3BC6568A8285263732'),(8,'c','c','c','Ln0sA6lQeuJl7PW1NWiFpTOTogKdJBOUmXJloaJa78Y=','c','F9F2052FE6426760E3807E0BBB40E69E'),(9,'d','d','d','GKw+c0PwFokMUQ6T+TUmEWnZ4/VlQ2Qpgw+vCTT0+OQ=','d',NULL),(10,'e','e','e','P3m7e0NbBTIWUdrv03TNxoHcBvqmXjdOODN7iMoEbeo=','e',NULL);
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product` (
  `recnum` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `category` varchar(45) DEFAULT NULL,
  `imageURL` varchar(100) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `cost` double DEFAULT NULL,
  `price` double DEFAULT NULL,
  PRIMARY KEY (`recnum`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'Car','dodge challenger','auto','1.jpg',36,1000,2000),(2,'Pepper','hot pepper','food','2.jpg',45,1,2),(3,'Red Pomegranate','pomegranate seeds','food','3.jpg',43,0.5,1),(4,'Muffin','blueberry muffin','food','4.jpg',45,50,51);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchase`
--

DROP TABLE IF EXISTS `purchase`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `purchase` (
  `recnum` int(11) NOT NULL AUTO_INCREMENT,
  `product` int(11) NOT NULL,
  `customer` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `date_added` varchar(30) NOT NULL,
  `date_purchased` varchar(10) DEFAULT NULL,
  `status` varchar(10) NOT NULL,
  PRIMARY KEY (`recnum`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchase`
--

LOCK TABLES `purchase` WRITE;
/*!40000 ALTER TABLE `purchase` DISABLE KEYS */;
INSERT INTO `purchase` VALUES (1,1,1,0,'2016-04-30T23:53:43.546',NULL,'draft'),(14,1,3,10,'Wed May 04 22:57:59 EDT 2016',NULL,'draft'),(15,3,3,4,'Thu May 05 21:55:05 EDT 2016',NULL,'draft'),(16,1,6,2,'Mon May 09 14:48:04 EDT 2016',NULL,'draft'),(17,2,6,1,'Mon May 09 16:17:00 EDT 2016',NULL,'draft'),(19,3,8,2,'Mon May 09 16:17:30 EDT 2016',NULL,'draft'),(20,1,8,1,'Mon May 09 16:22:32 EDT 2016',NULL,'draft'),(21,4,6,1,'Mon May 09 16:33:02 EDT 2016',NULL,'draft'),(22,4,8,1,'Mon May 09 16:33:15 EDT 2016',NULL,'draft');
/*!40000 ALTER TABLE `purchase` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-05-09 16:38:44
