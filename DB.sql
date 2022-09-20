-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: localhost    Database: testing1
-- ------------------------------------------------------
-- Server version	8.0.28

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `bed`
--

DROP TABLE IF EXISTS `bed`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bed` (
  `id` int NOT NULL AUTO_INCREMENT,
  `bed_no` varchar(255) DEFAULT NULL,
  `room_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK115cuh725wpbt8yxq2lvgg1c9` (`room_id`),
  CONSTRAINT `FK115cuh725wpbt8yxq2lvgg1c9` FOREIGN KEY (`room_id`) REFERENCES `room` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=94 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bed`
--

LOCK TABLES `bed` WRITE;
/*!40000 ALTER TABLE `bed` DISABLE KEYS */;
INSERT INTO `bed` VALUES (1,'101-a',1),(2,'101-b',1),(3,'101-c',1),(4,'101-d',1),(5,'101-e',1),(6,'102-a',2),(7,'102-b',2),(8,'102-c',2),(9,'102-d',2),(10,'102-e',2),(11,'103-a',3),(12,'103-b',3),(13,'103-c',3),(14,'103-d',3),(15,'103-e',3),(16,'104-a',4),(17,'104-b',4),(18,'104-c',4),(19,'104-d',4),(20,'104-e',4),(21,'201-a',5),(22,'201-b',5),(23,'201-c',5),(24,'201-d',5),(25,'201-e',5),(26,'202-a',6),(27,'202-b',6),(28,'202-c',6),(29,'202-d',6),(30,'202-e',6),(31,'203-a',7),(32,'203-b',7),(33,'203-c',7),(34,'203-d',7),(35,'203-e',7),(36,'204-a',8),(37,'204-b',8),(38,'204-c',8),(39,'204-d',8),(40,'204-e',8),(41,'301-a',9),(42,'301-b',9),(43,'301-c',9),(44,'301-d',9),(45,'301-e',9),(46,'302-a',10),(47,'302-b',10),(48,'302-c',10),(49,'302-d',10),(50,'302-e',10),(51,'303-a',11),(52,'303-b',11),(53,'303-c',11),(54,'303-d',11),(55,'303-e',11),(56,'304-a',12),(57,'304-b',12),(58,'304-c',12),(59,'304-d',12),(60,'304-e',12),(61,'101-a',13),(62,'101-b',13),(63,'101-c',13),(64,'101-d',13),(65,'102-a',14),(66,'102-b',14),(67,'102-c',14),(68,'102-d',14),(69,'103-a',15),(70,'103-b',15),(80,'103-c',15),(81,'103-d',15),(82,'201-a',16),(83,'201-b',16),(84,'201-c',16),(85,'201-d',16),(86,'202-a',17),(87,'202-b',17),(88,'202-c',17),(89,'202-d',17),(90,'203-a',18),(91,'203-b',18),(92,'203-c',18),(93,'203-d',18);
/*!40000 ALTER TABLE `bed` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bed_assignment`
--

DROP TABLE IF EXISTS `bed_assignment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bed_assignment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `bed_id` int DEFAULT NULL,
  `patient_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK61xh93kvmy556jb6c5bppeeei` (`bed_id`),
  UNIQUE KEY `UK6vm197reidjp9yo1hojflrymj` (`patient_id`),
  CONSTRAINT `FKfyvycw08g6xwrx8b02v4om7v` FOREIGN KEY (`bed_id`) REFERENCES `bed` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FKkq334xdtqxedy0x800ed6906f` FOREIGN KEY (`patient_id`) REFERENCES `patients` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=290 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bed_assignment`
--

LOCK TABLES `bed_assignment` WRITE;
/*!40000 ALTER TABLE `bed_assignment` DISABLE KEYS */;
/*!40000 ALTER TABLE `bed_assignment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `floor`
--

DROP TABLE IF EXISTS `floor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `floor` (
  `id` int NOT NULL AUTO_INCREMENT,
  `floor_no` varchar(255) DEFAULT NULL,
  `hospital_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKa5b8lcu8owi14prg8v4jx7mpl` (`hospital_id`),
  CONSTRAINT `FKa5b8lcu8owi14prg8v4jx7mpl` FOREIGN KEY (`hospital_id`) REFERENCES `hospital` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `floor`
--

LOCK TABLES `floor` WRITE;
/*!40000 ALTER TABLE `floor` DISABLE KEYS */;
INSERT INTO `floor` VALUES (1,'First',1),(2,'Second',1),(3,'Third',1),(4,'First',2),(5,'Second',2);
/*!40000 ALTER TABLE `floor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hospital`
--

DROP TABLE IF EXISTS `hospital`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hospital` (
  `id` int NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hospital`
--

LOCK TABLES `hospital` WRITE;
/*!40000 ALTER TABLE `hospital` DISABLE KEYS */;
INSERT INTO `hospital` VALUES (1,'Yelahanka','Apollo','9876433467'),(2,'Hebbal','Fortis','8654321901');
/*!40000 ALTER TABLE `hospital` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patients`
--

DROP TABLE IF EXISTS `patients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `patients` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `age` varchar(100) DEFAULT NULL,
  `gender` varchar(100) DEFAULT NULL,
  `aadhaar` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `date_of_birth` datetime DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `hospital_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKf4uijrhsjjvlpe6kdks9qy9hy` (`hospital_id`),
  CONSTRAINT `FKf4uijrhsjjvlpe6kdks9qy9hy` FOREIGN KEY (`hospital_id`) REFERENCES `hospital` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patients`
--

LOCK TABLES `patients` WRITE;
/*!40000 ALTER TABLE `patients` DISABLE KEYS */;
INSERT INTO `patients` VALUES (1,'Ramesh','35','Male','654231643747','Bangalore','1977-10-05 00:00:00','9647326826',1),(2,'Ravi','40','Male','786090654321','Mumbai','1982-12-02 00:00:00','8767421953',1),(3,'Reena','19','Female','645442344324','Patna','1996-12-09 00:00:00','9823627647',1),(4,'Shobha','20','Female','689043216789','Mysore','2002-03-07 00:00:00','7864321975',1),(5,'Vinay','32','Male','987543209768','Mandya','1990-11-01 00:00:00','7654310976',1),(6,'Suraiya','16','Female','568905321678','Hubli','2006-01-15 00:00:00','8906432187',1),(7,'Stephan','45','Male','230997665127','Madurai','1977-10-05 00:00:00','6728399928',2),(8,'Vimala','60','Female','439900187651','Karwar','1963-01-25 00:00:00','8736764728',2),(9,'Venkat','5','Male','231443434356','Bangalore','2017-03-11 00:00:00','9787328644',2),(10,'Fathima','10','Female','487839393377','Bidar','2012-04-30 00:00:00','7653267178',1),(11,'Rekha','42','Female','232354657688','Kalburgi','1990-11-01 00:00:00','8764543343',2),(12,'Pranith','32','Male','453243424556','Srinagar','1996-12-09 00:00:00','8654434322',2);
/*!40000 ALTER TABLE `patients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room`
--

DROP TABLE IF EXISTS `room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room` (
  `id` int NOT NULL AUTO_INCREMENT,
  `room_no` varchar(255) DEFAULT NULL,
  `floor_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKstlo96g0nkwp4urd4e0ki5b3h` (`floor_id`),
  CONSTRAINT `FKstlo96g0nkwp4urd4e0ki5b3h` FOREIGN KEY (`floor_id`) REFERENCES `floor` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room`
--

LOCK TABLES `room` WRITE;
/*!40000 ALTER TABLE `room` DISABLE KEYS */;
INSERT INTO `room` VALUES (1,'101',1),(2,'102',1),(3,'103',1),(4,'104',1),(5,'201',2),(6,'202',2),(7,'203',2),(8,'204',2),(9,'301',3),(10,'302',3),(11,'303',3),(12,'304',3),(13,'101',4),(14,'102',4),(15,'103',4),(16,'201',5),(17,'202',5),(18,'203',5);
/*!40000 ALTER TABLE `room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `password` varchar(120) DEFAULT NULL,
  `username` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKr43af9ap4edm43mmtq01oddj6` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'$2a$10$1POyB/OCfjg/TSgc0bgpFeQ9zTKQVUGSRVX52u21dfaOVMQt0Ov/q','MN097789');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-06-02 17:17:15
