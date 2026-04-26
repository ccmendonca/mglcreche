CREATE DATABASE  IF NOT EXISTS `mglcreche` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `mglcreche`;
-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: localhost    Database: mglcreche
-- ------------------------------------------------------
-- Server version	8.0.39

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
  `sexo_aluno` char(10) DEFAULT NULL,
  `id_sexo` int NOT NULL,
  `id_municipio` int DEFAULT NULL,
  PRIMARY KEY (`id_aluno`),
  KEY `fk_aluno_sexo1_idx` (`id_sexo`),
  KEY `fk_aluno_municipio_idx` (`id_municipio`),
  CONSTRAINT `fk_aluno_municipio` FOREIGN KEY (`id_municipio`) REFERENCES `municipio` (`id_municipio`),
  CONSTRAINT `fk_aluno_sexo1` FOREIGN KEY (`id_sexo`) REFERENCES `sexo` (`id_sexo`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aluno`
--

LOCK TABLES `aluno` WRITE;
/*!40000 ALTER TABLE `aluno` DISABLE KEYS */;
INSERT INTO `aluno` VALUES (1,'Ana','Carlos','2022-07-11','A+','a','a','a','a','a','a','a','a','a','2026-03-20 18:20:24',NULL,1,17),(2,'Pedro','Maria','2024-11-05','B+','a','a','a','a','a','a','a','a','a','2026-03-20 18:20:36',NULL,2,20),(3,'as','as','2026-09-03','B+','as','as','as','as','as','as','as','as','as','2026-03-27 01:24:01',NULL,2,100),(4,'Tamara','Prata','2022-05-30','A+','222','2','Centralidade','Belmira','Sanches','932445566','Santos','Prata','912645427','2026-04-17 11:10:48',NULL,1,2);
/*!40000 ALTER TABLE `aluno` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ano_lectivo`
--

DROP TABLE IF EXISTS `ano_lectivo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ano_lectivo` (
  `id_ano_lectivo` int NOT NULL AUTO_INCREMENT,
  `descricao_ano_lectivo` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id_ano_lectivo`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ano_lectivo`
--

LOCK TABLES `ano_lectivo` WRITE;
/*!40000 ALTER TABLE `ano_lectivo` DISABLE KEYS */;
INSERT INTO `ano_lectivo` VALUES (1,'2026-2027');
/*!40000 ALTER TABLE `ano_lectivo` ENABLE KEYS */;
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
-- Table structure for table `categoria_profissional`
--

DROP TABLE IF EXISTS `categoria_profissional`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categoria_profissional` (
  `id_categoriaprofissional` int NOT NULL AUTO_INCREMENT,
  `descricao_categoriaprofissional` varchar(200) COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id_categoriaprofissional`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoria_profissional`
--

LOCK TABLES `categoria_profissional` WRITE;
/*!40000 ALTER TABLE `categoria_profissional` DISABLE KEYS */;
INSERT INTO `categoria_profissional` VALUES (1,'Professor'),(2,'Director/a'),(3,'SubDirector/a'),(4,'Secretário/a'),(5,'Segurança'),(6,'Administrador/a'),(7,'Contabilista'),(8,'Conselho'),(9,'Técnico Informático'),(10,'Vigilante');
/*!40000 ALTER TABLE `categoria_profissional` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `classe`
--

DROP TABLE IF EXISTS `classe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `classe` (
  `id_classe` int NOT NULL AUTO_INCREMENT,
  `descricao_classe` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id_classe`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `classe`
--

LOCK TABLES `classe` WRITE;
/*!40000 ALTER TABLE `classe` DISABLE KEYS */;
INSERT INTO `classe` VALUES (1,'1.ª Classe'),(2,'2:ª Classe');
/*!40000 ALTER TABLE `classe` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `disciplina`
--

DROP TABLE IF EXISTS `disciplina`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `disciplina` (
  `id_disciplina` int NOT NULL AUTO_INCREMENT,
  `descricao_disciplina` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id_disciplina`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `disciplina`
--

LOCK TABLES `disciplina` WRITE;
/*!40000 ALTER TABLE `disciplina` DISABLE KEYS */;
/*!40000 ALTER TABLE `disciplina` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `forma_pagamento`
--

DROP TABLE IF EXISTS `forma_pagamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `forma_pagamento` (
  `id_forma_pagamento` int NOT NULL AUTO_INCREMENT,
  `descricao_forma_pagamento` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id_forma_pagamento`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `forma_pagamento`
--

LOCK TABLES `forma_pagamento` WRITE;
/*!40000 ALTER TABLE `forma_pagamento` DISABLE KEYS */;
/*!40000 ALTER TABLE `forma_pagamento` ENABLE KEYS */;
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
-- Table structure for table `matricula`
--

DROP TABLE IF EXISTS `matricula`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `matricula` (
  `id_matricula` int NOT NULL AUTO_INCREMENT,
  `data_matricula` datetime DEFAULT CURRENT_TIMESTAMP,
  `id_aluno` int NOT NULL,
  `id_turma` int NOT NULL,
  PRIMARY KEY (`id_matricula`),
  KEY `fk_matricula_aluno1_idx` (`id_aluno`),
  KEY `fk_matricula_turma1_idx` (`id_turma`),
  CONSTRAINT `fk_matricula_aluno1` FOREIGN KEY (`id_aluno`) REFERENCES `aluno` (`id_aluno`),
  CONSTRAINT `fk_matricula_turma1` FOREIGN KEY (`id_turma`) REFERENCES `turma` (`id_turma`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `matricula`
--

LOCK TABLES `matricula` WRITE;
/*!40000 ALTER TABLE `matricula` DISABLE KEYS */;
/*!40000 ALTER TABLE `matricula` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `matricula_detalhes`
--

DROP TABLE IF EXISTS `matricula_detalhes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `matricula_detalhes` (
  `id_servico` int NOT NULL,
  `id_matricula` int NOT NULL,
  `preco_servico` double NOT NULL,
  `quantidade_servico` int DEFAULT NULL,
  PRIMARY KEY (`id_servico`,`id_matricula`),
  KEY `fk_matricula_detalhes_servico1_idx` (`id_servico`),
  KEY `fk_matricula_detalhes_matricula1_idx` (`id_matricula`),
  CONSTRAINT `fk_matricula_detalhes_matricula1` FOREIGN KEY (`id_matricula`) REFERENCES `matricula` (`id_matricula`),
  CONSTRAINT `fk_matricula_detalhes_servico1` FOREIGN KEY (`id_servico`) REFERENCES `servico` (`id_servico`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `matricula_detalhes`
--

LOCK TABLES `matricula_detalhes` WRITE;
/*!40000 ALTER TABLE `matricula_detalhes` DISABLE KEYS */;
/*!40000 ALTER TABLE `matricula_detalhes` ENABLE KEYS */;
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
-- Table structure for table `municipio`
--

DROP TABLE IF EXISTS `municipio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `municipio` (
  `id_municipio` int NOT NULL AUTO_INCREMENT,
  `nome_municipio` varchar(45) DEFAULT NULL,
  `id_provincia` int NOT NULL,
  PRIMARY KEY (`id_municipio`),
  KEY `fk_municipio_provincia_idx` (`id_provincia`),
  CONSTRAINT `fk_municipio_provincia` FOREIGN KEY (`id_provincia`) REFERENCES `provincia` (`id_provincia`)
) ENGINE=InnoDB AUTO_INCREMENT=161 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `municipio`
--

LOCK TABLES `municipio` WRITE;
/*!40000 ALTER TABLE `municipio` DISABLE KEYS */;
INSERT INTO `municipio` VALUES (1,'Ambriz',1),(2,'Bula Atumba',1),(3,'Dande',1),(4,'Dembos',1),(5,'Nambuangongo',1),(6,'Pango Aluquém',1),(7,'Balombo',2),(8,'Baia Farta',2),(9,'Benguela',2),(10,'Bocoio',2),(11,'Caimbambo',2),(12,'Catumbela',2),(13,'Chongoroi',2),(14,'Cubal',2),(15,'Ganda',2),(16,'Lobito',2),(17,'Andulo',3),(18,'Camacupa',3),(19,'Catabola',3),(20,'Chinguar',3),(21,'Chitembo',3),(22,'Cuemba',3),(23,'Cunhinga',3),(24,'Kuito',3),(25,'Nharea',3),(26,'Belize',4),(27,'Buco-Zau',4),(28,'Cabinda',4),(29,'Cacongo',4),(30,'Calai',5),(31,'Cuangar',5),(32,'Cuchi',5),(33,'Cuito Cuanavale',5),(34,'Dirico',5),(35,'Mavinga',5),(36,'Menongue',5),(37,'Nancova',5),(38,'Rivungo',5),(39,'Cahama',8),(40,'Cuanhama',8),(41,'Curoca',8),(42,'Cuvelai',8),(43,'Namacunde',8),(44,'Ombadja',8),(45,'Bailundo',9),(46,'Catchiungo',9),(47,'Caala',9),(48,'Ekuma',9),(49,'Huambo',9),(50,'Londuimbale',9),(51,'Longonjo',9),(52,'Mungo',9),(53,'Tchicala-Tchiloanga',9),(54,'Tchindjenje',9),(55,'Ucuma',9),(56,'Caconda',10),(57,'Cacula',10),(58,'Caluquembe',10),(59,'Gambos',10),(60,'Chibia',10),(61,'Chicomba',10),(62,'Chipindo',10),(63,'Cuvango',10),(64,'Humpata',10),(65,'Jamba',10),(66,'Lubango',10),(67,'Matala',10),(68,'Quilengues',10),(69,'Quipungo',10),(70,'Ambaca',6),(71,'Banga',6),(72,'Bolongongo',6),(73,'Cambambe',6),(74,'Cazengo',6),(75,'Golungo Alto',6),(76,'Gonguembo',6),(77,'Lucala',6),(78,'Quiculungo',6),(79,'Samba Caju',6),(80,'Cassongue',7),(81,'Conda',7),(82,'Ebo',7),(83,'Libolo',7),(84,'Mussende',7),(85,'Porto Amboin',7),(86,'Quibala',7),(87,'Quilenda',7),(88,'Seles',7),(89,'Sumbe',7),(90,'Waku Kungo',7),(91,'Belas',11),(92,'Cacuaco',11),(93,'Cazenga',11),(94,'Icolo e Bengo',11),(95,'Luanda',11),(96,'Quiçama',11),(97,'Viana',11),(98,'Cambulo',12),(99,'Capenda-Camulemba',12),(100,'Caungula',12),(101,'Chitato',12),(102,'Cuango',12),(103,'Cuilo',12),(104,'Lubalo',12),(105,'Lukapa',12),(106,'Xá-Muteba',12),(107,'Cacolo',13),(108,'Dala',13),(109,'Muconda',13),(110,'Saurimo',13),(111,'Cacuso',14),(112,'Calandula',14),(113,'Cambundi-Catembo',14),(114,'Cangandala',14),(115,'Caombo',14),(116,'Cuaba Nzogo',14),(117,'Cunda-Dia-Baze',14),(118,'Luquembo',14),(119,'Malange',14),(120,'Marimba',14),(121,'Massango',14),(122,'Mucari',14),(123,'Quela',14),(124,'Quirima',14),(125,'Alto Zambeze',15),(126,'Bundas',15),(127,'Camanongue',15),(128,'Léua',15),(129,'Luau',15),(130,'Luacano',15),(131,'Luchazes',15),(132,'Lumeje',15),(133,'Moxico',15),(134,'Bibala',16),(135,'Camucuio',16),(136,'Namibe',16),(137,'Tômbua',16),(138,'Virei',16),(139,'Alto Cauale',17),(140,'Ambuila',17),(141,'Bembe',17),(142,'Buengas',17),(143,'Bungo',17),(144,'Damba',17),(145,'Macocola',17),(146,'Mucaba',17),(147,'Negage',17),(148,'Puri',17),(149,'Quimbele',17),(150,'Quitexe',17),(151,'Sanza Pombo',17),(152,'Songo',17),(153,'Uige',17),(154,'Zombo',17),(155,'Cuimba',18),(156,'M\'Banza Kongo',18),(157,'Noqui',18),(158,'N\'Zeto',18),(159,'Soyo',18),(160,'Tomboco',18);
/*!40000 ALTER TABLE `municipio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nota`
--

DROP TABLE IF EXISTS `nota`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nota` (
  `id_nota` int NOT NULL AUTO_INCREMENT,
  `id_aluno` int NOT NULL,
  `id_turma` int NOT NULL,
  `id_disciplina` int NOT NULL,
  `nota` double DEFAULT NULL,
  PRIMARY KEY (`id_nota`),
  KEY `fk_nota_aluno1_idx` (`id_aluno`),
  KEY `fk_nota_turma1_idx` (`id_turma`),
  KEY `fk_nota_disciplina1_idx` (`id_disciplina`),
  CONSTRAINT `fk_nota_aluno1` FOREIGN KEY (`id_aluno`) REFERENCES `aluno` (`id_aluno`),
  CONSTRAINT `fk_nota_disciplina1` FOREIGN KEY (`id_disciplina`) REFERENCES `disciplina` (`id_disciplina`),
  CONSTRAINT `fk_nota_turma1` FOREIGN KEY (`id_turma`) REFERENCES `turma` (`id_turma`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nota`
--

LOCK TABLES `nota` WRITE;
/*!40000 ALTER TABLE `nota` DISABLE KEYS */;
/*!40000 ALTER TABLE `nota` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `profissional`
--

DROP TABLE IF EXISTS `profissional`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `profissional` (
  `id_profissional` int NOT NULL AUTO_INCREMENT,
  `bi_profissional` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `nome_profissional` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `sobrenome_profissional` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `dataNascimento_profissional` date NOT NULL,
  `numeroCasa_profissional` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `rua_profissional` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `bairro_profissional` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `distrito_profissional` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `foto_profissional` blob,
  `telefoneFixo_profissional` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `telefoneUnitel_profissional` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `telefoneMovicel_profissional` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `telefoneAfricell_profissional` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `email_profissional` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `habilitacoesLiterarias_profissional` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `observacoes_profissional` text COLLATE utf8mb4_general_ci,
  `id_sexo` int DEFAULT NULL,
  `id_municipio` int DEFAULT NULL,
  `id_categoriaprofissional` int DEFAULT NULL,
  PRIMARY KEY (`id_profissional`),
  KEY `fk_profissional_sexo` (`id_sexo`),
  KEY `fk_profissional_municipio` (`id_municipio`),
  KEY `fk_profissional_categoria` (`id_categoriaprofissional`),
  CONSTRAINT `fk_profissional_categoria` FOREIGN KEY (`id_categoriaprofissional`) REFERENCES `categoria_profissional` (`id_categoriaprofissional`),
  CONSTRAINT `fk_profissional_municipio` FOREIGN KEY (`id_municipio`) REFERENCES `municipio` (`id_municipio`),
  CONSTRAINT `fk_profissional_sexo` FOREIGN KEY (`id_sexo`) REFERENCES `sexo` (`id_sexo`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `profissional`
--

LOCK TABLES `profissional` WRITE;
/*!40000 ALTER TABLE `profissional` DISABLE KEYS */;
INSERT INTO `profissional` VALUES (1,'00654444','António','Mendonça','2000-01-17',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,2,2,1),(2,'472819484','Nádia','Maderes','2002-09-20',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,44,2);
/*!40000 ALTER TABLE `profissional` ENABLE KEYS */;
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
  `id_ano_lectivo` int DEFAULT NULL,
  `id_forma_pagamento` int DEFAULT NULL,
  `observacoes` varchar(250) DEFAULT NULL,
  `data_hora_registo` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_proprina`),
  KEY `fk_proprina_aluno_idx` (`id_aluno`),
  KEY `fk_proprina_ano_lectivo_idx` (`id_ano_lectivo`),
  KEY `fk_proprina_forma_pagamento_idx` (`id_forma_pagamento`),
  CONSTRAINT `fk_proprina_aluno` FOREIGN KEY (`id_aluno`) REFERENCES `aluno` (`id_aluno`),
  CONSTRAINT `fk_proprina_ano_lectivo` FOREIGN KEY (`id_ano_lectivo`) REFERENCES `ano_lectivo` (`id_ano_lectivo`),
  CONSTRAINT `fk_proprina_forma_pagamento` FOREIGN KEY (`id_forma_pagamento`) REFERENCES `forma_pagamento` (`id_forma_pagamento`)
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
-- Table structure for table `provincia`
--

DROP TABLE IF EXISTS `provincia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `provincia` (
  `id_provincia` int NOT NULL AUTO_INCREMENT,
  `nome_provincia` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_provincia`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `provincia`
--

LOCK TABLES `provincia` WRITE;
/*!40000 ALTER TABLE `provincia` DISABLE KEYS */;
INSERT INTO `provincia` VALUES (1,'Bengo'),(2,'Benguela'),(3,'Bié'),(4,'Cabinda'),(5,'Cuando Cubango'),(6,'Cuanza Norte'),(7,'Cuanza Sul'),(8,'Cunene'),(9,'Huambo'),(10,'Huila'),(11,'Luanda'),(12,'Lunda Norte'),(13,'Lunda Sul'),(14,'Malange'),(15,'Moxico'),(16,'Namibe'),(17,'Uige'),(18,'Zaire');
/*!40000 ALTER TABLE `provincia` ENABLE KEYS */;
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
  `sexo_responsavel_busca_aluno` char(10) DEFAULT NULL,
  `id_sexo` int NOT NULL,
  `id_municipio` int DEFAULT NULL,
  PRIMARY KEY (`id_responsavel`),
  KEY `fk_responsavel_busca_aluno_sexo1_idx` (`id_sexo`),
  KEY `fk_responsavel_busca_aluno_municipio_idx` (`id_municipio`),
  CONSTRAINT `fk_responsavel_busca_aluno_municipio` FOREIGN KEY (`id_municipio`) REFERENCES `municipio` (`id_municipio`),
  CONSTRAINT `fk_responsavel_busca_aluno_sexo1` FOREIGN KEY (`id_sexo`) REFERENCES `sexo` (`id_sexo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `responsavel_busca_aluno`
--

LOCK TABLES `responsavel_busca_aluno` WRITE;
/*!40000 ALTER TABLE `responsavel_busca_aluno` DISABLE KEYS */;
/*!40000 ALTER TABLE `responsavel_busca_aluno` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sala`
--

DROP TABLE IF EXISTS `sala`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sala` (
  `id_sala` int NOT NULL AUTO_INCREMENT,
  `descricao_sala` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`id_sala`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sala`
--

LOCK TABLES `sala` WRITE;
/*!40000 ALTER TABLE `sala` DISABLE KEYS */;
/*!40000 ALTER TABLE `sala` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `servico`
--

DROP TABLE IF EXISTS `servico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `servico` (
  `id_servico` int NOT NULL AUTO_INCREMENT,
  `descricao_servico` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `preco_servico` double DEFAULT NULL,
  PRIMARY KEY (`id_servico`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `servico`
--

LOCK TABLES `servico` WRITE;
/*!40000 ALTER TABLE `servico` DISABLE KEYS */;
INSERT INTO `servico` VALUES (1,'Serviço 1',10000),(2,'Serviço 2',30000),(3,'Serviço 3',50000);
/*!40000 ALTER TABLE `servico` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sexo`
--

DROP TABLE IF EXISTS `sexo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sexo` (
  `id_sexo` int NOT NULL AUTO_INCREMENT,
  `descricao_sexo` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id_sexo`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sexo`
--

LOCK TABLES `sexo` WRITE;
/*!40000 ALTER TABLE `sexo` DISABLE KEYS */;
INSERT INTO `sexo` VALUES (1,'Feminino'),(2,'Masculino');
/*!40000 ALTER TABLE `sexo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `turma`
--

DROP TABLE IF EXISTS `turma`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `turma` (
  `id_turma` int NOT NULL AUTO_INCREMENT,
  `descricao_turma` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `id_ano_lectivo` int NOT NULL,
  `id_classe` int NOT NULL,
  `numero_maximo` int DEFAULT NULL,
  `activa` tinyint DEFAULT NULL,
  `data_registo` datetime DEFAULT CURRENT_TIMESTAMP,
  `id_sala` int NOT NULL,
  PRIMARY KEY (`id_turma`),
  KEY `fk_turma_ano_lectivo1_idx` (`id_ano_lectivo`),
  KEY `fk_turma_classe1_idx` (`id_classe`),
  KEY `fk_turma_sala1_idx` (`id_sala`),
  CONSTRAINT `fk_turma_ano_lectivo1` FOREIGN KEY (`id_ano_lectivo`) REFERENCES `ano_lectivo` (`id_ano_lectivo`),
  CONSTRAINT `fk_turma_classe1` FOREIGN KEY (`id_classe`) REFERENCES `classe` (`id_classe`),
  CONSTRAINT `fk_turma_sala1` FOREIGN KEY (`id_sala`) REFERENCES `sala` (`id_sala`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `turma`
--

LOCK TABLES `turma` WRITE;
/*!40000 ALTER TABLE `turma` DISABLE KEYS */;
/*!40000 ALTER TABLE `turma` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-04-23  6:47:43
