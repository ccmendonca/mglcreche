CREATE DATABASE  IF NOT EXISTS `mglcreche` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `mglcreche`;
-- MySQL dump 10.13  Distrib 8.0.36, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: mglcreche
-- ------------------------------------------------------
-- Server version	8.0.45-0ubuntu0.24.04.1

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
-- Table structure for table `aluno`
--

DROP TABLE IF EXISTS `aluno`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `aluno` (
  `id_aluno` int NOT NULL AUTO_INCREMENT,
  `nome_aluno` varchar(100) DEFAULT NULL,
  `sobrenome_aluno` varchar(100) DEFAULT NULL,
  `data_nascimento_aluno` date DEFAULT NULL,
  `grupo_sanguineo_aluno` char(5) DEFAULT NULL,
  `casa_aluno` varchar(45) DEFAULT NULL,
  `rua_aluno` varchar(100) DEFAULT NULL,
  `bairro_aluno` varchar(200) DEFAULT NULL,
  `nome_mae_aluno` varchar(45) DEFAULT NULL,
  `sobrenome_mae_aluno` varchar(45) DEFAULT NULL,
  `telefone_mae_aluno` varchar(45) DEFAULT NULL,
  `nome_pai_aluno` varchar(45) DEFAULT NULL,
  `sobrenome_pai_aluno` varchar(45) DEFAULT NULL,
  `telefone_pai_aluno` varchar(45) DEFAULT NULL,
  `data_registo_aluno` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_aluno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aluno`
--

LOCK TABLES `aluno` WRITE;
/*!40000 ALTER TABLE `aluno` DISABLE KEYS */;
/*!40000 ALTER TABLE `aluno` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cadastro_responsavel_busca`
--

DROP TABLE IF EXISTS `cadastro_responsavel_busca`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cadastro_responsavel_busca` (
  `id_registo_responsavel_busca` int NOT NULL AUTO_INCREMENT,
  `data_registo` datetime DEFAULT CURRENT_TIMESTAMP,
  `id_aluno` int NOT NULL,
  `id_grau_parentesco` int NOT NULL,
  `id_responsavel` int NOT NULL,
  PRIMARY KEY (`id_registo_responsavel_busca`),
  KEY `fk_cadastro_responsavel_busca_aluno_idx` (`id_aluno`),
  KEY `fk_cadastro_responsavel_busca_grau_parentesco1_idx` (`id_grau_parentesco`),
  KEY `fk_cadastro_responsavel_busca_responsavel_busca_aluno1_idx` (`id_responsavel`),
  CONSTRAINT `fk_cadastro_responsavel_busca_aluno` FOREIGN KEY (`id_aluno`) REFERENCES `aluno` (`id_aluno`),
  CONSTRAINT `fk_cadastro_responsavel_busca_grau_parentesco1` FOREIGN KEY (`id_grau_parentesco`) REFERENCES `grau_parentesco` (`id_grau_parentesco`),
  CONSTRAINT `fk_cadastro_responsavel_busca_responsavel_busca_aluno1` FOREIGN KEY (`id_responsavel`) REFERENCES `responsavel_busca_aluno` (`id_responsavel`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cadastro_responsavel_busca`
--

LOCK TABLES `cadastro_responsavel_busca` WRITE;
/*!40000 ALTER TABLE `cadastro_responsavel_busca` DISABLE KEYS */;
/*!40000 ALTER TABLE `cadastro_responsavel_busca` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grau_parentesco`
--

DROP TABLE IF EXISTS `grau_parentesco`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `grau_parentesco` (
  `id_grau_parentesco` int NOT NULL AUTO_INCREMENT,
  `descricao_grau_parentesco` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id_grau_parentesco`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grau_parentesco`
--

LOCK TABLES `grau_parentesco` WRITE;
/*!40000 ALTER TABLE `grau_parentesco` DISABLE KEYS */;
INSERT INTO `grau_parentesco` VALUES (1,'Pai'),(2,'Mãe');
/*!40000 ALTER TABLE `grau_parentesco` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mes_propina`
--

DROP TABLE IF EXISTS `mes_propina`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mes_propina` (
  `id_mes_propina` int NOT NULL AUTO_INCREMENT,
  `descricao_mes_propina` varchar(45) DEFAULT NULL,
  `valor_mes_propina` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_mes_propina`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mes_propina`
--

LOCK TABLES `mes_propina` WRITE;
/*!40000 ALTER TABLE `mes_propina` DISABLE KEYS */;
INSERT INTO `mes_propina` VALUES (1,'Janeiro','2000.0'),(2,'Fevereiro','2000.0'),(3,'Março','2000.0'),(4,'Abril','2000.0'),(5,'Maio','2000.0'),(6,'Junho','2000.0'),(7,'Julho','2000.0'),(8,'Agosto','2000.0'),(9,'Setembro','2000.0'),(10,'Outubro','2000.0'),(11,'Novembro','2000.0'),(12,'Dezembro','2000.0');
/*!40000 ALTER TABLE `mes_propina` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proprina`
--

DROP TABLE IF EXISTS `proprina`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `proprina` (
  `id_proprina` int NOT NULL AUTO_INCREMENT,
  `data_pagamento` datetime DEFAULT CURRENT_TIMESTAMP,
  `id_aluno` int DEFAULT NULL,
  PRIMARY KEY (`id_proprina`),
  KEY `fk_proprina_aluno_idx` (`id_aluno`),
  CONSTRAINT `fk_proprina_aluno` FOREIGN KEY (`id_aluno`) REFERENCES `aluno` (`id_aluno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proprina`
--

LOCK TABLES `proprina` WRITE;
/*!40000 ALTER TABLE `proprina` DISABLE KEYS */;
/*!40000 ALTER TABLE `proprina` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proprina_detalhes`
--

DROP TABLE IF EXISTS `proprina_detalhes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `proprina_detalhes` (
  `id_proprina` int NOT NULL,
  `id_mes_propina` int NOT NULL,
  `valor_mes_proprina` double DEFAULT NULL,
  PRIMARY KEY (`id_proprina`,`id_mes_propina`),
  KEY `fk_proprina_detalhes_me_idx` (`id_mes_propina`),
  CONSTRAINT `fk_proprina_detalhes_me` FOREIGN KEY (`id_mes_propina`) REFERENCES `mes_propina` (`id_mes_propina`),
  CONSTRAINT `fk_proprina_detalhes_propina` FOREIGN KEY (`id_proprina`) REFERENCES `proprina` (`id_proprina`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proprina_detalhes`
--

LOCK TABLES `proprina_detalhes` WRITE;
/*!40000 ALTER TABLE `proprina_detalhes` DISABLE KEYS */;
/*!40000 ALTER TABLE `proprina_detalhes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `responsavel_busca_aluno`
--

DROP TABLE IF EXISTS `responsavel_busca_aluno`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `responsavel_busca_aluno` (
  `id_responsavel` int NOT NULL AUTO_INCREMENT,
  `nome_responsavel` varchar(100) DEFAULT NULL,
  `sobrenome_responsavel` varchar(100) DEFAULT NULL,
  `data_nascimento_responsavel` date DEFAULT NULL,
  `casa_responsavel` varchar(45) DEFAULT NULL,
  `rua_responsavel` varchar(100) DEFAULT NULL,
  `bairro_responsavel` varchar(100) DEFAULT NULL,
  `telefone_responsavel` varchar(45) DEFAULT NULL,
  `data_registo_responsavel` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_responsavel`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `responsavel_busca_aluno`
--

LOCK TABLES `responsavel_busca_aluno` WRITE;
/*!40000 ALTER TABLE `responsavel_busca_aluno` DISABLE KEYS */;
/*!40000 ALTER TABLE `responsavel_busca_aluno` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-03-20  0:11:42
