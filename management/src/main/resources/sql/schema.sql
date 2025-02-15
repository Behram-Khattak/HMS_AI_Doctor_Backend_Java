-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: hospitalmanagementsystem
-- ------------------------------------------------------
-- Server version	8.0.34

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */
;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */
;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */
;
/*!50503 SET NAMES utf8 */
;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */
;
/*!40103 SET TIME_ZONE='+00:00' */
;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */
;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */
;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */
;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */
;

--
-- Table structure for table `prescription`
--
SET FOREIGN_KEY_CHECKS = 0;
-- Disable foreign key constraints

DROP TABLE IF EXISTS `prescription`;

SET FOREIGN_KEY_CHECKS = 1;
-- Re-enable foreign key constraints
/*!40101 SET @saved_cs_client     = @@character_set_client */
;
/*!50503 SET character_set_client = utf8mb4 */
;
CREATE TABLE `prescription` (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `amount` double NOT NULL,
    `date` date DEFAULT NULL,
    `status` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 4 DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */
;

--
-- Dumping data for table `prescription`
--

LOCK TABLES `prescription` WRITE;
/*!40000 ALTER TABLE `prescription` DISABLE KEYS */
;
INSERT INTO
    `prescription`
VALUES (
        1,
        200,
        '2024-01-27',
        'scheduled'
    ),
    (
        2,
        5000,
        '2024-03-11',
        'completed'
    ),
    (
        3,
        5000,
        '2024-03-11',
        'completed'
    );
/*!40000 ALTER TABLE `prescription` ENABLE KEYS */
;
UNLOCK TABLES;

--
-- Table structure for table `patient`
--
SET FOREIGN_KEY_CHECKS = 0;
-- Disable foreign key constraints

DROP TABLE IF EXISTS `patient`;

SET FOREIGN_KEY_CHECKS = 1;
-- Re-enable foreign key constraints
/*!40101 SET @saved_cs_client     = @@character_set_client */
;
/*!50503 SET character_set_client = utf8mb4 */
;
CREATE TABLE `patient` (
    `id` varchar(255) NOT NULL,
    `address` varchar(255) DEFAULT NULL,
    `dob` date DEFAULT NULL,
    `email_address` varchar(255) DEFAULT NULL,
    `full_name` varchar(255) DEFAULT NULL,
    `gender` varchar(255) DEFAULT NULL,
    `phone_number` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */
;

--
-- Dumping data for table `patient`
--

LOCK TABLES `patient` WRITE;
/*!40000 ALTER TABLE `patient` DISABLE KEYS */
;
INSERT INTO
    `patient`
VALUES (
        '1',
        'China',
        '2024-03-10',
        'ggdds@cbb.hh',
        'khan',
        'male',
        '1122333'
    ),
    (
        '2',
        'china',
        '2024-03-11',
        'aaa@com',
        'aaaaaa',
        'male',
        '112504478'
    ),
    (
        '3',
        'china',
        '2024-03-11',
        'aamir@gmail.com',
        'amir',
        'male',
        '15524700'
    );
/*!40000 ALTER TABLE `patient` ENABLE KEYS */
;
UNLOCK TABLES;

--
-- Table structure for table `users`
--
SET FOREIGN_KEY_CHECKS = 0;
-- Disable foreign key constraints

DROP TABLE IF EXISTS `users`;

SET FOREIGN_KEY_CHECKS = 1;
-- Re-enable foreign key constraints
/*!40101 SET @saved_cs_client     = @@character_set_client */
;
/*!50503 SET character_set_client = utf8mb4 */
;
CREATE TABLE `users` (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `department` varchar(255) DEFAULT NULL,
    `email` varchar(255) DEFAULT NULL,
    `name` varchar(255) DEFAULT NULL,
    `password` varchar(255) DEFAULT NULL,
    `role` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UK_6dotkott2kjsp8vw4d0m25fb7` (`email`)
) ENGINE = InnoDB AUTO_INCREMENT = 9 DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */
;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */
;
INSERT INTO
    `users`
VALUES (
        1,
        'CARDIOLOGY',
        'doctor1@test.com',
        'Hammad',
        'test',
        'doctor'
    ),
    (
        2,
        'NEUROLOGY',
        'doctor2@test.com',
        'Sarah',
        'test',
        'doctor'
    ),
    (
        3,
        'N/A',
        'receptionist1@test.com',
        'Emily',
        'test',
        'receptionist'
    ),
    (
        4,
        'N/A',
        'receptionist2@test.com',
        'James',
        'test',
        'receptionist'
    ),
    (
        5,
        'N/A',
        'pharmacist1@test.com',
        'Alex',
        'test',
        'pharmacist'
    ),
    (
        6,
        'N/A',
        'pharmacist2@test.com',
        'Olivia',
        'test',
        'pharmacist'
    ),
    (
        7,
        'INSPECTION',
        'labtech1@test.com',
        'Ethan',
        'test',
        'lab_technician'
    ),
    (
        8,
        'DISPOSAL',
        'labtech2@test.com',
        'Ava',
        'test',
        'lab_technician'
    );
/*!40000 ALTER TABLE `users` ENABLE KEYS */
;
UNLOCK TABLES;

--
-- Table structure for table `medical_condition`
--

SET FOREIGN_KEY_CHECKS = 0;
-- Disable foreign key constraints

DROP TABLE IF EXISTS `medical_condition`;

SET FOREIGN_KEY_CHECKS = 1;
-- Re-enable foreign key constraints

/*!40101 SET @saved_cs_client     = @@character_set_client */
;
/*!50503 SET character_set_client = utf8mb4 */
;
CREATE TABLE `medical_condition` (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `description` varchar(255) DEFAULT NULL,
    `name` varchar(255) DEFAULT NULL,
    `severity` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 4 DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */
;

--
-- Dumping data for table `medical_condition`
--

LOCK TABLES `medical_condition` WRITE;
/*!40000 ALTER TABLE `medical_condition` DISABLE KEYS */
;
INSERT INTO
    `medical_condition`
VALUES (
        1,
        'head ache',
        'fever',
        'hhhh'
    ),
    (
        2,
        'head ache',
        'fever',
        'Normal'
    ),
    (
        3,
        'head ache',
        'fever',
        'Normal'
    );
/*!40000 ALTER TABLE `medical_condition` ENABLE KEYS */
;
UNLOCK TABLES;

-- Table structure for table `appointment`
SET FOREIGN_KEY_CHECKS = 0;
-- Disable foreign key constraints

DROP TABLE IF EXISTS `appointment`;

SET FOREIGN_KEY_CHECKS = 1;
-- Re-enable foreign key constraints

/*!40101 SET @saved_cs_client     = @@character_set_client */
;
/*!50503 SET character_set_client = utf8mb4 */
;
CREATE TABLE `appointment` (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `charges` double NOT NULL,
    `date` date DEFAULT NULL,
    `department` varchar(255) DEFAULT NULL,
    `diagnosis` bit(1) NOT NULL,
    `status` varchar(255) DEFAULT NULL,
    `token` bigint DEFAULT NULL,
    `doctor_id` bigint DEFAULT NULL,
    `medical_condition_id` bigint DEFAULT NULL,
    `patient_id` varchar(255) DEFAULT NULL,
    `prescription_id` bigint DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `FK2qxfq7calhysrwb3lu7xlry2p` (`prescription_id`),
    KEY `FK4apif2ewfyf14077ichee8g06` (`patient_id`),
    KEY `FKgwhur9oxtxd00p1ardeum1xao` (`doctor_id`),
    KEY `FKiq9d54pj943a7h3oyfpeosi5p` (`medical_condition_id`),
    CONSTRAINT `FK2qxfq7calhysrwb3lu7xlry2p` FOREIGN KEY (`prescription_id`) REFERENCES `prescription` (`id`),
    CONSTRAINT `FK4apif2ewfyf14077ichee8g06` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`id`),
    CONSTRAINT `FKgwhur9oxtxd00p1ardeum1xao` FOREIGN KEY (`doctor_id`) REFERENCES `users` (`id`),
    CONSTRAINT `FKiq9d54pj943a7h3oyfpeosi5p` FOREIGN KEY (`medical_condition_id`) REFERENCES `medical_condition` (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 5 DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */
;

--
-- Dumping data for table `appointment`
--

LOCK TABLES `appointment` WRITE;
/*!40000 ALTER TABLE `appointment` DISABLE KEYS */
;
INSERT INTO
    `appointment`
VALUES (
        1,
        1000,
        '2024-01-27',
        'CARDIOLOGY',
        _binary '',
        'completed',
        1,
        1,
        1,
        '1',
        1
    ),
    (
        2,
        1200,
        '2024-03-11',
        'CARDIOLOGY',
        _binary '\0',
        'completed',
        1,
        1,
        2,
        '1',
        2
    ),
    (
        3,
        1200,
        '2024-03-11',
        'CARDIOLOGY',
        _binary '\0',
        'in-progress',
        2,
        1,
        NULL,
        '2',
        NULL
    ),
    (
        4,
        1200,
        '2024-03-11',
        'CARDIOLOGY',
        _binary '\0',
        'completed',
        3,
        1,
        3,
        '3',
        3
    );
/*!40000 ALTER TABLE `appointment` ENABLE KEYS */
;
UNLOCK TABLES;

--
-- Table structure for table `test`
--
SET FOREIGN_KEY_CHECKS = 0;
-- Disable foreign key constraints

DROP TABLE IF EXISTS `test`;

SET FOREIGN_KEY_CHECKS = 1;
-- Re-enable foreign key constraints
/*!40101 SET @saved_cs_client     = @@character_set_client */
;
/*!50503 SET character_set_client = utf8mb4 */
;
CREATE TABLE `test` (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `department` varchar(255) DEFAULT NULL,
    `description` varchar(255) DEFAULT NULL,
    `name` varchar(255) DEFAULT NULL,
    `price` double NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 3 DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */
;

--
-- Dumping data for table `test`
--

LOCK TABLES `test` WRITE;
/*!40000 ALTER TABLE `test` DISABLE KEYS */
;
INSERT INTO
    `test`
VALUES (
        1,
        'Inspection',
        'take blood sample and look for white cells',
        'nerves test',
        100
    ),
    (
        2,
        'Disposal',
        'take blood sample and look for white cells',
        'x-ray test',
        100
    );
/*!40000 ALTER TABLE `test` ENABLE KEYS */
;
UNLOCK TABLES;
--
-- Table structure for table `appointment_tests`
--
SET FOREIGN_KEY_CHECKS = 0;
-- Disable foreign key constraints

DROP TABLE IF EXISTS `appointment_tests`;

SET FOREIGN_KEY_CHECKS = 1;
-- Re-enable foreign key constraints

/*!40101 SET @saved_cs_client     = @@character_set_client */
;
/*!50503 SET character_set_client = utf8mb4 */
;
CREATE TABLE `appointment_tests` (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `date` date DEFAULT NULL,
    `status` varchar(255) DEFAULT NULL,
    `appointment_id` bigint DEFAULT NULL,
    `test_id` bigint DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `FKi0r6cgd76wv8y71c6t9on9r0` (`appointment_id`),
    KEY `FK5ckrc2tj9xks7kceo88pv5mun` (`test_id`),
    CONSTRAINT `FKi0r6cgd76wv8y71c6t9on9r0` FOREIGN KEY (`appointment_id`) REFERENCES `appointment` (`id`),
    CONSTRAINT `FK5ckrc2tj9xks7kceo88pv5mun` FOREIGN KEY (`test_id`) REFERENCES `test` (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 5 DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */
;

--
-- Dumping data for table `appointment_tests`
--

LOCK TABLES `appointment_tests` WRITE;
/*!40000 ALTER TABLE `appointment_tests` DISABLE KEYS */
;
INSERT INTO
    `appointment_tests`
VALUES (
        1,
        '2024-01-27',
        'completed',
        1,
        2
    ),
    (
        2,
        '2024-03-11',
        'completed',
        2,
        1
    ),
    (
        3,
        '2024-03-11',
        'completed',
        2,
        1
    ),
    (
        4,
        '2024-03-11',
        'completed',
        4,
        1
    );
/*!40000 ALTER TABLE `appointment_tests` ENABLE KEYS */
;
UNLOCK TABLES;

--
-- Table structure for table `medicine`
--
SET FOREIGN_KEY_CHECKS = 0;
-- Disable foreign key constraints

DROP TABLE IF EXISTS `medicine`;

SET FOREIGN_KEY_CHECKS = 1;
-- Re-enable foreign key constraints
/*!40101 SET @saved_cs_client     = @@character_set_client */
;
/*!50503 SET character_set_client = utf8mb4 */
;
CREATE TABLE `medicine` (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `name` varchar(255) DEFAULT NULL,
    `power` varchar(255) DEFAULT NULL,
    `price_per_unit` double NOT NULL,
    `stock` double NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 4 DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */
;

--
-- Dumping data for table `medicine`
--

LOCK TABLES `medicine` WRITE;
/*!40000 ALTER TABLE `medicine` DISABLE KEYS */
;
INSERT INTO
    `medicine`
VALUES (
        1,
        'panadol',
        '500 mg',
        500,
        30
    ),
    (
        2,
        'paracetamol',
        '1000mg',
        500,
        50
    ),
    (
        3,
        'dispirin',
        '100 mg',
        100,
        10
    );
/*!40000 ALTER TABLE `medicine` ENABLE KEYS */
;
UNLOCK TABLES;

--
-- Table structure for table `prescription_medicines`
--
SET FOREIGN_KEY_CHECKS = 0;
-- Disable foreign key constraints

DROP TABLE IF EXISTS `prescription_medicines`;

SET FOREIGN_KEY_CHECKS = 1;
-- Re-enable foreign key constraints
/*!40101 SET @saved_cs_client     = @@character_set_client */
;
/*!50503 SET character_set_client = utf8mb4 */
;
CREATE TABLE `prescription_medicines` (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `price` double NOT NULL,
    `quantity` double NOT NULL,
    `medicine_id` bigint DEFAULT NULL,
    `prescription_id` bigint DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `FKbxj8x2jo8qih334yi63jwmr51` (`medicine_id`),
    KEY `FKbwtcoglp0aw51n8ax8jcovi64` (`prescription_id`),
    CONSTRAINT `FKbxj8x2jo8qih334yi63jwmr51` FOREIGN KEY (`medicine_id`) REFERENCES `medicine` (`id`),
    CONSTRAINT `FKbwtcoglp0aw51n8ax8jcovi64` FOREIGN KEY (`prescription_id`) REFERENCES `prescription` (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 4 DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */
;

--
-- Dumping data for table `prescription_medicines`
--

LOCK TABLES `prescription_medicines` WRITE;
/*!40000 ALTER TABLE `prescription_medicines` DISABLE KEYS */
;
INSERT INTO
    `prescription_medicines`
VALUES (1, 200, 2, 3, 1),
    (2, 5000, 10, 1, 2),
    (3, 5000, 10, 1, 3);
/*!40000 ALTER TABLE `prescription_medicines` ENABLE KEYS */
;
UNLOCK TABLES;

--
-- Table structure for table `result`
--
SET FOREIGN_KEY_CHECKS = 0;
-- Disable foreign key constraints

DROP TABLE IF EXISTS `result`;

SET FOREIGN_KEY_CHECKS = 1;
-- Re-enable foreign key constraints
/*!40101 SET @saved_cs_client     = @@character_set_client */
;
/*!50503 SET character_set_client = utf8mb4 */
;
CREATE TABLE `result` (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `description` varchar(255) DEFAULT NULL,
    `appointment_tests_id` bigint DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `FKi33u64jic2l78nwoformb4qr8` (`appointment_tests_id`),
    CONSTRAINT `FKi33u64jic2l78nwoformb4qr8` FOREIGN KEY (`appointment_tests_id`) REFERENCES `appointment_tests` (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 5 DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */
;

--
-- Dumping data for table `result`
--

LOCK TABLES `result` WRITE;
/*!40000 ALTER TABLE `result` DISABLE KEYS */
;
INSERT INTO
    `result`
VALUES (1, 'CLEAR', 1),
    (2, 'CLEAR', 2),
    (3, 'CLEAR', 3),
    (4, 'CLEAR', 4);
/*!40000 ALTER TABLE `result` ENABLE KEYS */
;
UNLOCK TABLES;

--
-- Table structure for table `transaction`
--
SET FOREIGN_KEY_CHECKS = 0;
-- Disable foreign key constraints

DROP TABLE IF EXISTS `transaction`;

SET FOREIGN_KEY_CHECKS = 1;
-- Re-enable foreign key constraints
/*!40101 SET @saved_cs_client     = @@character_set_client */
;
/*!50503 SET character_set_client = utf8mb4 */
;
CREATE TABLE `transaction` (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `amount` double NOT NULL,
    `created_at` date DEFAULT NULL,
    `record_id` bigint DEFAULT NULL,
    `status` varchar(255) DEFAULT NULL,
    `transaction_of` varchar(255) DEFAULT NULL,
    `patient_id` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `FKmq5s6xpmjtn7m9knvt3h7o4ct` (`patient_id`),
    CONSTRAINT `FKmq5s6xpmjtn7m9knvt3h7o4ct` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 12 DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */
;

--
-- Dumping data for table `transaction`
--

LOCK TABLES `transaction` WRITE;
/*!40000 ALTER TABLE `transaction` DISABLE KEYS */
;
INSERT INTO
    `transaction`
VALUES (
        1,
        1000,
        '2024-01-27',
        1,
        'paid',
        'appointment',
        '1'
    ),
    (
        2,
        100,
        '2024-01-27',
        1,
        'paid',
        'test',
        '1'
    ),
    (
        3,
        200,
        '2024-01-27',
        1,
        'valid',
        'medicine',
        '1'
    ),
    (
        4,
        1200,
        '2024-03-11',
        2,
        'paid',
        'appointment',
        '1'
    ),
    (
        5,
        100,
        '2024-03-11',
        2,
        'paid',
        'test',
        '1'
    ),
    (
        6,
        5000,
        '2024-03-11',
        2,
        'paid',
        'medicine',
        '1'
    ),
    (
        7,
        100,
        '2024-03-11',
        3,
        'paid',
        'test',
        '1'
    ),
    (
        8,
        1200,
        '2024-03-11',
        3,
        'paid',
        'appointment',
        '2'
    ),
    (
        9,
        1200,
        '2024-03-11',
        4,
        'paid',
        'appointment',
        '3'
    ),
    (
        10,
        100,
        '2024-03-11',
        4,
        'paid',
        'test',
        '3'
    ),
    (
        11,
        5000,
        '2024-03-11',
        3,
        'paid',
        'medicine',
        '3'
    );
/*!40000 ALTER TABLE `transaction` ENABLE KEYS */
;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */
;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */
;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */
;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */
;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */
;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */
;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */
;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */
;

-- Dump completed on 2024-03-11 15:09:10