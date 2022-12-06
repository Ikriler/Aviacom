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
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-12-06 14:11:33
