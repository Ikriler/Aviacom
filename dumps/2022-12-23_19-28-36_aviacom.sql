-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: aviacom
-- ------------------------------------------------------
-- Server version	8.0.30

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `aviacom`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `aviacom` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `aviacom`;

--
-- Table structure for table `airplane`
--

DROP TABLE IF EXISTS `airplane`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `airplane` (
  `id` bigint NOT NULL,
  `business_class_clients_count` int NOT NULL,
  `economic_class_clients_count` int NOT NULL,
  `first_class_clients_count` int NOT NULL,
  `model` varchar(200) DEFAULT NULL,
  `layout_type_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_d1nl7nqrnd25j6t3h94j4x4by` (`model`),
  KEY `FK2cxiac7q4m6ucyrpo26rsrpdo` (`layout_type_id`),
  CONSTRAINT `FK2cxiac7q4m6ucyrpo26rsrpdo` FOREIGN KEY (`layout_type_id`) REFERENCES `layout_type` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `airplane`
--

LOCK TABLES `airplane` WRITE;
/*!40000 ALTER TABLE `airplane` DISABLE KEYS */;
INSERT INTO `airplane` VALUES (24,20,100,10,' Ту-204',3);
/*!40000 ALTER TABLE `airplane` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `airport`
--

DROP TABLE IF EXISTS `airport`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `airport` (
  `id` bigint NOT NULL,
  `address` varchar(200) DEFAULT NULL,
  `name` varchar(200) DEFAULT NULL,
  `city_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5m12861556g03qkkwptwo8nco` (`city_id`),
  CONSTRAINT `FK5m12861556g03qkkwptwo8nco` FOREIGN KEY (`city_id`) REFERENCES `city` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `airport`
--

LOCK TABLES `airport` WRITE;
/*!40000 ALTER TABLE `airport` DISABLE KEYS */;
INSERT INTO `airport` VALUES (20,'Россия, 141400, Московская обл., г. Химки, а/п Шереметьево','Шереметьево',17),(23,'Россия, Ставропольский край, г.Ставрополь, аэропорт, 10','Международный аэропорт Ставрополь имени А.В. Суворова',18);
/*!40000 ALTER TABLE `airport` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `booking`
--

DROP TABLE IF EXISTS `booking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `booking` (
  `id` bigint NOT NULL,
  `date_end` varchar(255) DEFAULT NULL,
  `client_id` bigint DEFAULT NULL,
  `employee_id` bigint DEFAULT NULL,
  `ticket_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKhs7eej4m2orrmr5cfbcrqs8yw` (`client_id`),
  KEY `FK1dnnhqt4wl3v6a72hxiarf7lg` (`employee_id`),
  KEY `FKte7386dwsq1v3bgg8bbfe9nuq` (`ticket_id`),
  CONSTRAINT `FK1dnnhqt4wl3v6a72hxiarf7lg` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`),
  CONSTRAINT `FKhs7eej4m2orrmr5cfbcrqs8yw` FOREIGN KEY (`client_id`) REFERENCES `client` (`id`),
  CONSTRAINT `FKte7386dwsq1v3bgg8bbfe9nuq` FOREIGN KEY (`ticket_id`) REFERENCES `ticket` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booking`
--

LOCK TABLES `booking` WRITE;
/*!40000 ALTER TABLE `booking` DISABLE KEYS */;
/*!40000 ALTER TABLE `booking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `city`
--

DROP TABLE IF EXISTS `city`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `city` (
  `id` bigint NOT NULL,
  `description` longtext,
  `name` varchar(200) DEFAULT NULL,
  `country_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_qsstlki7ni5ovaariyy9u8y79` (`name`),
  KEY `FKrpd7j1p7yxr784adkx4pyepba` (`country_id`),
  CONSTRAINT `FKrpd7j1p7yxr784adkx4pyepba` FOREIGN KEY (`country_id`) REFERENCES `country` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `city`
--

LOCK TABLES `city` WRITE;
/*!40000 ALTER TABLE `city` DISABLE KEYS */;
INSERT INTO `city` VALUES (17,'-','Москва',14),(18,'-','Ставрополь',14),(682,'-','Севастополь',14);
/*!40000 ALTER TABLE `city` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `client`
--

DROP TABLE IF EXISTS `client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `client` (
  `id` bigint NOT NULL,
  `email` varchar(200) DEFAULT NULL,
  `name` varchar(200) DEFAULT NULL,
  `passport_number` varchar(6) DEFAULT NULL,
  `passport_series` varchar(4) DEFAULT NULL,
  `password` longtext,
  `patronymic` varchar(200) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `surname` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_bfgjs3fem0hmjhvih80158x29` (`email`),
  UNIQUE KEY `UK_qe9dtj732yl9u3oqhhsee4lps` (`phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client`
--

LOCK TABLES `client` WRITE;
/*!40000 ALTER TABLE `client` DISABLE KEYS */;
INSERT INTO `client` VALUES (153,'1@inbox.ru','1123','123456','1234','$2a$10$xujGSs67jK5cusisn6gwXuu3liZcsFKCws6sr1gMJDE1YutypXHdm','1','+7(999)999-99-94','1'),(548,'test1@mail.ru','TEST1','234561','1234','$2a$10$Q/r7O0RIw27dNSbQaMJ/ge5Pi6vvvawiC.g4fBRU0RZKJVEbt/32S','TEST1','+7(999)999-19-99','TEST1'),(549,'test2@mail.ru','TEST2','412314','1234','$2a$10$LXHwGaqpJLvVV0CQQLX83OhswfEfTCIDTZzmQTLLbztBCkL5KypIO','TEST2','+7(999)999-29-99','TEST2');
/*!40000 ALTER TABLE `client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `country`
--

DROP TABLE IF EXISTS `country`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `country` (
  `id` bigint NOT NULL,
  `name` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_llidyp77h6xkeokpbmoy710d4` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `country`
--

LOCK TABLES `country` WRITE;
/*!40000 ALTER TABLE `country` DISABLE KEYS */;
INSERT INTO `country` VALUES (14,'Россия'),(15,'США'),(16,'Япония');
/*!40000 ALTER TABLE `country` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `id` bigint NOT NULL,
  `login` varchar(200) DEFAULT NULL,
  `name` varchar(200) DEFAULT NULL,
  `password` longtext,
  `patronymic` varchar(200) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `surname` varchar(200) DEFAULT NULL,
  `post_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_buf2qp04xpwfp5qq355706h4a` (`phone`),
  KEY `FKcm3b9d5fiw8s6co7lkw8c0lbs` (`post_id`),
  CONSTRAINT `FKcm3b9d5fiw8s6co7lkw8c0lbs` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (13,'Админ','Админ','$2a$10$nFEA2TXcs9DHj8dS5Nepeef1RQ37AbBUFPxOHdbKzhkHfoOmzyJ0G','Админ','+7(999)999-99-99','Админ',7),(809,'Кассир','Кассир','$2a$10$l79RuW.27G593I05Czg8BuFyD21rAkZu42Q3wBq.ksFMQavPgpeYu','Кассир','+7(999)239-99-99','Кассир',8),(810,'Диспетчер','Диспетчер','$2a$10$c9Dtd83m/W.VEhEXHH0jXeHs5V.uWhwVCUgMhXG1st4omMLfuuw0K','Диспетчер','+7(923)999-99-99','Диспетчер',9),(811,'Агент','Агент','$2a$10$DZMoHcnQbqvR796nRqL3aupAO76rgqnSsWbiynp.ynZo.MTzsBhri','Агент','+7(999)234-99-99','Агент',10),(812,'Кадровик','Кадровик','$2a$10$V2ZJnz1WcmhnNPevMtouyeIHu5JxkYapYfrlM.1ny8yGeDy57fuf.','Кадровик','+7(999)999-99-23','Кадровик',11);
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (817);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `layout_type`
--

DROP TABLE IF EXISTS `layout_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `layout_type` (
  `id` bigint NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_h884kghfjdurqek6s02lla6w1` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `layout_type`
--

LOCK TABLES `layout_type` WRITE;
/*!40000 ALTER TABLE `layout_type` DISABLE KEYS */;
INSERT INTO `layout_type` VALUES (3,'Большая'),(1,'Малая'),(2,'Средняя');
/*!40000 ALTER TABLE `layout_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post`
--

DROP TABLE IF EXISTS `post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `post` (
  `id` bigint NOT NULL,
  `alias` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post`
--

LOCK TABLES `post` WRITE;
/*!40000 ALTER TABLE `post` DISABLE KEYS */;
INSERT INTO `post` VALUES (7,'Админ','ADMIN'),(8,'Кассир','CASHIER'),(9,'Диспетчер','AIRDROME'),(10,'Агент бронирования','BOOKING'),(11,'Кадровик','PERSONNEL');
/*!40000 ALTER TABLE `post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sale`
--

DROP TABLE IF EXISTS `sale`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sale` (
  `id` bigint NOT NULL,
  `sale_date` varchar(255) DEFAULT NULL,
  `client_id` bigint DEFAULT NULL,
  `employee_id` bigint DEFAULT NULL,
  `ticket_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKon0o9ba5ajsnwivekhl1tfjiy` (`client_id`),
  KEY `FKmct0j4p9ggcme3rlx7006bya1` (`employee_id`),
  KEY `FKbimqd12gjex7dniuhauseoeo2` (`ticket_id`),
  CONSTRAINT `FKbimqd12gjex7dniuhauseoeo2` FOREIGN KEY (`ticket_id`) REFERENCES `ticket` (`id`),
  CONSTRAINT `FKmct0j4p9ggcme3rlx7006bya1` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`),
  CONSTRAINT `FKon0o9ba5ajsnwivekhl1tfjiy` FOREIGN KEY (`client_id`) REFERENCES `client` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sale`
--

LOCK TABLES `sale` WRITE;
/*!40000 ALTER TABLE `sale` DISABLE KEYS */;
INSERT INTO `sale` VALUES (547,'2022-12-10',153,13,291),(813,'2022-12-23',549,13,292),(814,'2022-12-23',548,13,295),(815,'2022-12-23',153,13,683),(816,'2022-12-23',548,13,293);
/*!40000 ALTER TABLE `sale` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seat_class`
--

DROP TABLE IF EXISTS `seat_class`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `seat_class` (
  `id` bigint NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seat_class`
--

LOCK TABLES `seat_class` WRITE;
/*!40000 ALTER TABLE `seat_class` DISABLE KEYS */;
INSERT INTO `seat_class` VALUES (4,'Эконом',0),(5,'Бизнес',25),(6,'Первый',50);
/*!40000 ALTER TABLE `seat_class` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket`
--

DROP TABLE IF EXISTS `ticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ticket` (
  `id` bigint NOT NULL,
  `price` double DEFAULT NULL,
  `seat` varchar(255) DEFAULT NULL,
  `seat_class_id` bigint DEFAULT NULL,
  `voyage_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKf3j5pc5wwxy1tt60owit4r9qq` (`seat_class_id`),
  KEY `FKdc16jbdeel6rd71vyoe8anvfg` (`voyage_id`),
  CONSTRAINT `FKdc16jbdeel6rd71vyoe8anvfg` FOREIGN KEY (`voyage_id`) REFERENCES `voyage` (`id`),
  CONSTRAINT `FKf3j5pc5wwxy1tt60owit4r9qq` FOREIGN KEY (`seat_class_id`) REFERENCES `seat_class` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket`
--

LOCK TABLES `ticket` WRITE;
/*!40000 ALTER TABLE `ticket` DISABLE KEYS */;
INSERT INTO `ticket` VALUES (291,184.5,'1A',6,26),(292,184.5,'1B',6,26),(293,184.5,'1C',6,26),(294,184.5,'1D',6,26),(295,184.5,'1E',6,26),(296,184.5,'1F',6,26),(297,184.5,'2A',6,26),(298,184.5,'2B',6,26),(299,184.5,'2C',6,26),(300,184.5,'2D',6,26),(301,153.75,'2E',5,26),(302,153.75,'2F',5,26),(303,153.75,'3A',5,26),(304,153.75,'3B',5,26),(305,153.75,'3C',5,26),(306,153.75,'3D',5,26),(307,153.75,'3E',5,26),(308,153.75,'3F',5,26),(309,153.75,'4A',5,26),(310,153.75,'4B',5,26),(311,153.75,'4C',5,26),(312,153.75,'4D',5,26),(313,153.75,'4E',5,26),(314,153.75,'4F',5,26),(315,153.75,'5A',5,26),(316,153.75,'5B',5,26),(317,153.75,'5C',5,26),(318,153.75,'5D',5,26),(319,153.75,'5E',5,26),(320,153.75,'5F',5,26),(321,123,'6A',4,26),(322,123,'6B',4,26),(323,123,'6C',4,26),(324,123,'6D',4,26),(325,123,'6E',4,26),(326,123,'6F',4,26),(327,123,'7A',4,26),(328,123,'7B',4,26),(329,123,'7C',4,26),(330,123,'7D',4,26),(331,123,'7E',4,26),(332,123,'7F',4,26),(333,123,'8A',4,26),(334,123,'8B',4,26),(335,123,'8C',4,26),(336,123,'8D',4,26),(337,123,'8E',4,26),(338,123,'8F',4,26),(339,123,'9A',4,26),(340,123,'9B',4,26),(341,123,'9C',4,26),(342,123,'9D',4,26),(343,123,'9E',4,26),(344,123,'9F',4,26),(345,123,'10A',4,26),(346,123,'10B',4,26),(347,123,'10C',4,26),(348,123,'10D',4,26),(349,123,'10E',4,26),(350,123,'10F',4,26),(351,123,'11A',4,26),(352,123,'11B',4,26),(353,123,'11C',4,26),(354,123,'11D',4,26),(355,123,'11E',4,26),(356,123,'11F',4,26),(357,123,'12A',4,26),(358,123,'12B',4,26),(359,123,'12C',4,26),(360,123,'12D',4,26),(361,123,'12E',4,26),(362,123,'12F',4,26),(363,123,'13A',4,26),(364,123,'13B',4,26),(365,123,'13C',4,26),(366,123,'13D',4,26),(367,123,'13E',4,26),(368,123,'13F',4,26),(369,123,'14A',4,26),(370,123,'14B',4,26),(371,123,'14C',4,26),(372,123,'14D',4,26),(373,123,'14E',4,26),(374,123,'14F',4,26),(375,123,'15A',4,26),(376,123,'15B',4,26),(377,123,'15C',4,26),(378,123,'15D',4,26),(379,123,'15E',4,26),(380,123,'15F',4,26),(381,123,'16A',4,26),(382,123,'16B',4,26),(383,123,'16C',4,26),(384,123,'16D',4,26),(385,123,'16E',4,26),(386,123,'16F',4,26),(387,123,'17A',4,26),(388,123,'17B',4,26),(389,123,'17C',4,26),(390,123,'17D',4,26),(391,123,'17E',4,26),(392,123,'17F',4,26),(393,123,'18A',4,26),(394,123,'18B',4,26),(395,123,'18C',4,26),(396,123,'18D',4,26),(397,123,'18E',4,26),(398,123,'18F',4,26),(399,123,'19A',4,26),(400,123,'19B',4,26),(401,123,'19C',4,26),(402,123,'19D',4,26),(403,123,'19E',4,26),(404,123,'19F',4,26),(405,123,'20A',4,26),(406,123,'20B',4,26),(407,123,'20C',4,26),(408,123,'20D',4,26),(409,123,'20E',4,26),(410,123,'20F',4,26),(411,123,'21A',4,26),(412,123,'21B',4,26),(413,123,'21C',4,26),(414,123,'21D',4,26),(415,123,'21E',4,26),(416,123,'21F',4,26),(420,666,'1A',6,154),(421,666,'1B',6,154),(422,666,'1C',6,154),(423,666,'1D',6,154),(424,666,'1E',6,154),(425,666,'1F',6,154),(426,666,'2A',6,154),(427,666,'2B',6,154),(428,666,'2C',6,154),(429,666,'2D',6,154),(430,666,'2E',5,154),(431,666,'2F',5,154),(432,666,'3A',5,154),(433,666,'3B',5,154),(434,666,'3C',5,154),(435,666,'3D',5,154),(436,666,'3E',5,154),(437,666,'3F',5,154),(438,666,'4A',5,154),(439,666,'4B',5,154),(440,666,'4C',5,154),(441,666,'4D',5,154),(442,666,'4E',5,154),(443,666,'4F',5,154),(444,666,'5A',5,154),(445,666,'5B',5,154),(446,666,'5C',5,154),(447,666,'5D',5,154),(448,666,'5E',5,154),(449,666,'5F',5,154),(450,666,'6A',4,154),(451,666,'6B',4,154),(452,666,'6C',4,154),(453,666,'6D',4,154),(454,666,'6E',4,154),(455,666,'6F',4,154),(456,666,'7A',4,154),(457,666,'7B',4,154),(458,666,'7C',4,154),(459,666,'7D',4,154),(460,666,'7E',4,154),(461,666,'7F',4,154),(462,666,'8A',4,154),(463,666,'8B',4,154),(464,666,'8C',4,154),(465,666,'8D',4,154),(466,666,'8E',4,154),(467,666,'8F',4,154),(468,666,'9A',4,154),(469,666,'9B',4,154),(470,666,'9C',4,154),(471,666,'9D',4,154),(472,666,'9E',4,154),(473,666,'9F',4,154),(474,666,'10A',4,154),(475,666,'10B',4,154),(476,666,'10C',4,154),(477,666,'10D',4,154),(478,666,'10E',4,154),(479,666,'10F',4,154),(480,666,'11A',4,154),(481,666,'11B',4,154),(482,666,'11C',4,154),(483,666,'11D',4,154),(484,666,'11E',4,154),(485,666,'11F',4,154),(486,666,'12A',4,154),(487,666,'12B',4,154),(488,666,'12C',4,154),(489,666,'12D',4,154),(490,666,'12E',4,154),(491,666,'12F',4,154),(492,666,'13A',4,154),(493,666,'13B',4,154),(494,666,'13C',4,154),(495,666,'13D',4,154),(496,666,'13E',4,154),(497,666,'13F',4,154),(498,666,'14A',4,154),(499,666,'14B',4,154),(500,666,'14C',4,154),(501,666,'14D',4,154),(502,666,'14E',4,154),(503,666,'14F',4,154),(504,666,'15A',4,154),(505,666,'15B',4,154),(506,666,'15C',4,154),(507,666,'15D',4,154),(508,666,'15E',4,154),(509,666,'15F',4,154),(510,666,'16A',4,154),(511,666,'16B',4,154),(512,666,'16C',4,154),(513,666,'16D',4,154),(514,666,'16E',4,154),(515,666,'16F',4,154),(516,666,'17A',4,154),(517,666,'17B',4,154),(518,666,'17C',4,154),(519,666,'17D',4,154),(520,666,'17E',4,154),(521,666,'17F',4,154),(522,666,'18A',4,154),(523,666,'18B',4,154),(524,666,'18C',4,154),(525,666,'18D',4,154),(526,666,'18E',4,154),(527,666,'18F',4,154),(528,666,'19A',4,154),(529,666,'19B',4,154),(530,666,'19C',4,154),(531,666,'19D',4,154),(532,666,'19E',4,154),(533,666,'19F',4,154),(534,666,'20A',4,154),(535,666,'20B',4,154),(536,666,'20C',4,154),(537,666,'20D',4,154),(538,666,'20E',4,154),(539,666,'20F',4,154),(540,666,'21A',4,154),(541,666,'21B',4,154),(542,666,'21C',4,154),(543,666,'21D',4,154),(544,666,'21E',4,154),(545,666,'21F',4,154),(683,30000,'1A',6,550),(684,30000,'1B',6,550),(685,30000,'1C',6,550),(686,30000,'1D',6,550),(687,30000,'1E',6,550),(688,30000,'1F',6,550),(689,30000,'2A',6,550),(690,30000,'2B',6,550),(691,30000,'2C',6,550),(692,30000,'2D',6,550),(693,25000,'2E',5,550),(694,25000,'2F',5,550),(695,25000,'3A',5,550),(696,25000,'3B',5,550),(697,25000,'3C',5,550),(698,25000,'3D',5,550),(699,25000,'3E',5,550),(700,25000,'3F',5,550),(701,25000,'4A',5,550),(702,25000,'4B',5,550),(703,25000,'4C',5,550),(704,25000,'4D',5,550),(705,25000,'4E',5,550),(706,25000,'4F',5,550),(707,25000,'5A',5,550),(708,25000,'5B',5,550),(709,25000,'5C',5,550),(710,25000,'5D',5,550),(711,25000,'5E',5,550),(712,25000,'5F',5,550),(713,20000,'6A',4,550),(714,20000,'6B',4,550),(715,20000,'6C',4,550),(716,20000,'6D',4,550),(717,20000,'6E',4,550),(718,20000,'6F',4,550),(719,20000,'7A',4,550),(720,20000,'7B',4,550),(721,20000,'7C',4,550),(722,20000,'7D',4,550),(723,20000,'7E',4,550),(724,20000,'7F',4,550),(725,20000,'8A',4,550),(726,20000,'8B',4,550),(727,20000,'8C',4,550),(728,20000,'8D',4,550),(729,20000,'8E',4,550),(730,20000,'8F',4,550),(731,20000,'9A',4,550),(732,20000,'9B',4,550),(733,20000,'9C',4,550),(734,20000,'9D',4,550),(735,20000,'9E',4,550),(736,20000,'9F',4,550),(737,20000,'10A',4,550),(738,20000,'10B',4,550),(739,20000,'10C',4,550),(740,20000,'10D',4,550),(741,20000,'10E',4,550),(742,20000,'10F',4,550),(743,20000,'11A',4,550),(744,20000,'11B',4,550),(745,20000,'11C',4,550),(746,20000,'11D',4,550),(747,20000,'11E',4,550),(748,20000,'11F',4,550),(749,20000,'12A',4,550),(750,20000,'12B',4,550),(751,20000,'12C',4,550),(752,20000,'12D',4,550),(753,20000,'12E',4,550),(754,20000,'12F',4,550),(755,20000,'13A',4,550),(756,20000,'13B',4,550),(757,20000,'13C',4,550),(758,20000,'13D',4,550),(759,20000,'13E',4,550),(760,20000,'13F',4,550),(761,20000,'14A',4,550),(762,20000,'14B',4,550),(763,20000,'14C',4,550),(764,20000,'14D',4,550),(765,20000,'14E',4,550),(766,20000,'14F',4,550),(767,20000,'15A',4,550),(768,20000,'15B',4,550),(769,20000,'15C',4,550),(770,20000,'15D',4,550),(771,20000,'15E',4,550),(772,20000,'15F',4,550),(773,20000,'16A',4,550),(774,20000,'16B',4,550),(775,20000,'16C',4,550),(776,20000,'16D',4,550),(777,20000,'16E',4,550),(778,20000,'16F',4,550),(779,20000,'17A',4,550),(780,20000,'17B',4,550),(781,20000,'17C',4,550),(782,20000,'17D',4,550),(783,20000,'17E',4,550),(784,20000,'17F',4,550),(785,20000,'18A',4,550),(786,20000,'18B',4,550),(787,20000,'18C',4,550),(788,20000,'18D',4,550),(789,20000,'18E',4,550),(790,20000,'18F',4,550),(791,20000,'19A',4,550),(792,20000,'19B',4,550),(793,20000,'19C',4,550),(794,20000,'19D',4,550),(795,20000,'19E',4,550),(796,20000,'19F',4,550),(797,20000,'20A',4,550),(798,20000,'20B',4,550),(799,20000,'20C',4,550),(800,20000,'20D',4,550),(801,20000,'20E',4,550),(802,20000,'20F',4,550),(803,20000,'21A',4,550),(804,20000,'21B',4,550),(805,20000,'21C',4,550),(806,20000,'21D',4,550),(807,20000,'21E',4,550),(808,20000,'21F',4,550);
/*!40000 ALTER TABLE `ticket` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_unicode_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER `Ticket_Price_Up` BEFORE INSERT ON `ticket` FOR EACH ROW SET NEW.price = NEW.price + NEW.price * 

 (SELECT seat_class.price FROM seat_class WHERE seat_class.id = NEW.seat_class_id) / 100 */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `voyage`
--

DROP TABLE IF EXISTS `voyage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `voyage` (
  `id` bigint NOT NULL,
  `date_time_inc` varchar(255) DEFAULT NULL,
  `date_time_out` varchar(255) DEFAULT NULL,
  `airplane_id` bigint DEFAULT NULL,
  `airport_inc_id` bigint DEFAULT NULL,
  `airport_out_id` bigint DEFAULT NULL,
  `city_inc_id` bigint DEFAULT NULL,
  `city_out_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKrwe0270tenm6ckb80g8i75nu9` (`airplane_id`),
  KEY `FK34h5yb25h5cfa85uiix7arxux` (`airport_inc_id`),
  KEY `FKy2gv589508cq615ub1cf43d1` (`airport_out_id`),
  KEY `FKir6a4lqv3qne5fg2hqm6ofius` (`city_inc_id`),
  KEY `FKd2y8b1m46lw1dsaj9dgqff403` (`city_out_id`),
  CONSTRAINT `FK34h5yb25h5cfa85uiix7arxux` FOREIGN KEY (`airport_inc_id`) REFERENCES `airport` (`id`),
  CONSTRAINT `FKd2y8b1m46lw1dsaj9dgqff403` FOREIGN KEY (`city_out_id`) REFERENCES `city` (`id`),
  CONSTRAINT `FKir6a4lqv3qne5fg2hqm6ofius` FOREIGN KEY (`city_inc_id`) REFERENCES `city` (`id`),
  CONSTRAINT `FKrwe0270tenm6ckb80g8i75nu9` FOREIGN KEY (`airplane_id`) REFERENCES `airplane` (`id`),
  CONSTRAINT `FKy2gv589508cq615ub1cf43d1` FOREIGN KEY (`airport_out_id`) REFERENCES `airport` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `voyage`
--

LOCK TABLES `voyage` WRITE;
/*!40000 ALTER TABLE `voyage` DISABLE KEYS */;
INSERT INTO `voyage` VALUES (26,'2022-11-13 21:32','2022-11-27 21:32',24,20,23,17,18),(154,'2022-11-22 13:32','2022-11-23 12:32',24,23,20,18,17),(550,'2022-12-23 21:35','2022-12-24 21:35',24,20,23,17,18);
/*!40000 ALTER TABLE `voyage` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-12-23 19:28:36
