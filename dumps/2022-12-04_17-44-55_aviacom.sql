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
  `country_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5m12861556g03qkkwptwo8nco` (`country_id`),
  CONSTRAINT `FK5m12861556g03qkkwptwo8nco` FOREIGN KEY (`country_id`) REFERENCES `city` (`id`)
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
INSERT INTO `city` VALUES (17,'-','Москва',14),(18,'-','Ставрополь',14);
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
INSERT INTO `client` VALUES (153,'1@inbox.ru','1','123456','1234','$2a$10$xujGSs67jK5cusisn6gwXuu3liZcsFKCws6sr1gMJDE1YutypXHdm','1','+7(999)999-99-94','1');
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
INSERT INTO `employee` VALUES (13,'Админ','Админ','$2a$10$nFEA2TXcs9DHj8dS5Nepeef1RQ37AbBUFPxOHdbKzhkHfoOmzyJ0G','Админ','+7(999)999-99-99','Админ',7);
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
INSERT INTO `hibernate_sequence` VALUES (417);
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
INSERT INTO `ticket` VALUES (291,184.5,'1A',6,26),(292,184.5,'1B',6,26),(293,184.5,'1C',6,26),(294,184.5,'1D',6,26),(295,184.5,'1E',6,26),(296,184.5,'1F',6,26),(297,184.5,'2A',6,26),(298,184.5,'2B',6,26),(299,184.5,'2C',6,26),(300,184.5,'2D',6,26),(301,153.75,'2E',5,26),(302,153.75,'2F',5,26),(303,153.75,'3A',5,26),(304,153.75,'3B',5,26),(305,153.75,'3C',5,26),(306,153.75,'3D',5,26),(307,153.75,'3E',5,26),(308,153.75,'3F',5,26),(309,153.75,'4A',5,26),(310,153.75,'4B',5,26),(311,153.75,'4C',5,26),(312,153.75,'4D',5,26),(313,153.75,'4E',5,26),(314,153.75,'4F',5,26),(315,153.75,'5A',5,26),(316,153.75,'5B',5,26),(317,153.75,'5C',5,26),(318,153.75,'5D',5,26),(319,153.75,'5E',5,26),(320,153.75,'5F',5,26),(321,123,'6A',4,26),(322,123,'6B',4,26),(323,123,'6C',4,26),(324,123,'6D',4,26),(325,123,'6E',4,26),(326,123,'6F',4,26),(327,123,'7A',4,26),(328,123,'7B',4,26),(329,123,'7C',4,26),(330,123,'7D',4,26),(331,123,'7E',4,26),(332,123,'7F',4,26),(333,123,'8A',4,26),(334,123,'8B',4,26),(335,123,'8C',4,26),(336,123,'8D',4,26),(337,123,'8E',4,26),(338,123,'8F',4,26),(339,123,'9A',4,26),(340,123,'9B',4,26),(341,123,'9C',4,26),(342,123,'9D',4,26),(343,123,'9E',4,26),(344,123,'9F',4,26),(345,123,'10A',4,26),(346,123,'10B',4,26),(347,123,'10C',4,26),(348,123,'10D',4,26),(349,123,'10E',4,26),(350,123,'10F',4,26),(351,123,'11A',4,26),(352,123,'11B',4,26),(353,123,'11C',4,26),(354,123,'11D',4,26),(355,123,'11E',4,26),(356,123,'11F',4,26),(357,123,'12A',4,26),(358,123,'12B',4,26),(359,123,'12C',4,26),(360,123,'12D',4,26),(361,123,'12E',4,26),(362,123,'12F',4,26),(363,123,'13A',4,26),(364,123,'13B',4,26),(365,123,'13C',4,26),(366,123,'13D',4,26),(367,123,'13E',4,26),(368,123,'13F',4,26),(369,123,'14A',4,26),(370,123,'14B',4,26),(371,123,'14C',4,26),(372,123,'14D',4,26),(373,123,'14E',4,26),(374,123,'14F',4,26),(375,123,'15A',4,26),(376,123,'15B',4,26),(377,123,'15C',4,26),(378,123,'15D',4,26),(379,123,'15E',4,26),(380,123,'15F',4,26),(381,123,'16A',4,26),(382,123,'16B',4,26),(383,123,'16C',4,26),(384,123,'16D',4,26),(385,123,'16E',4,26),(386,123,'16F',4,26),(387,123,'17A',4,26),(388,123,'17B',4,26),(389,123,'17C',4,26),(390,123,'17D',4,26),(391,123,'17E',4,26),(392,123,'17F',4,26),(393,123,'18A',4,26),(394,123,'18B',4,26),(395,123,'18C',4,26),(396,123,'18D',4,26),(397,123,'18E',4,26),(398,123,'18F',4,26),(399,123,'19A',4,26),(400,123,'19B',4,26),(401,123,'19C',4,26),(402,123,'19D',4,26),(403,123,'19E',4,26),(404,123,'19F',4,26),(405,123,'20A',4,26),(406,123,'20B',4,26),(407,123,'20C',4,26),(408,123,'20D',4,26),(409,123,'20E',4,26),(410,123,'20F',4,26),(411,123,'21A',4,26),(412,123,'21B',4,26),(413,123,'21C',4,26),(414,123,'21D',4,26),(415,123,'21E',4,26),(416,123,'21F',4,26);
/*!40000 ALTER TABLE `ticket` ENABLE KEYS */;
UNLOCK TABLES;

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
INSERT INTO `voyage` VALUES (26,'2022-11-13 21:32','2022-11-27 21:32',24,20,23,17,18),(154,'2022-11-22 13:32','2022-11-23 12:32',24,23,20,18,17);
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

-- Dump completed on 2022-12-04 17:44:55
