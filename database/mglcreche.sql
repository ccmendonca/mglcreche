CREATE DATABASE  IF NOT EXISTS `mglcreche` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `mglcreche`;
-- MySQL dump 10.13  Distrib 8.0.36, for Linux (x86_64)
--
-- Host: localhost    Database: mglcreche
-- ------------------------------------------------------
-- Server version	8.0.46-0ubuntu0.24.04.3

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
-- Table structure for table `alocacao_professor`
--

DROP TABLE IF EXISTS `alocacao_professor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `alocacao_professor` (
  `id_alocacao_professor` int NOT NULL AUTO_INCREMENT,
  ` carga_horaria_semanal` int DEFAULT NULL,
  `id_profissional` int DEFAULT NULL,
  `id_disciplina` int DEFAULT NULL,
  `id_turma` int DEFAULT NULL,
  `id_ano_lectivo` int DEFAULT NULL,
  `id_trimestre` int DEFAULT NULL,
  `data_alocacao` date DEFAULT NULL,
  `data_registo` datetime DEFAULT NULL,
  PRIMARY KEY (`id_alocacao_professor`),
  KEY `fk_alocacao_professor_profissional_idx` (`id_profissional`),
  KEY `fk_alocacao_professor_disciplina_idx` (`id_disciplina`),
  KEY `fk_alocacao_professor_turma_idx` (`id_turma`),
  KEY `fk_alocacao_professor_ano_lectivo_idx` (`id_ano_lectivo`),
  KEY `fk_alocacao_professor_trimestre_idx` (`id_trimestre`),
  CONSTRAINT `fk_alocacao_professor_ano_lectivo` FOREIGN KEY (`id_ano_lectivo`) REFERENCES `ano_lectivo` (`id_ano_lectivo`),
  CONSTRAINT `fk_alocacao_professor_disciplina` FOREIGN KEY (`id_disciplina`) REFERENCES `disciplina` (`id_disciplina`),
  CONSTRAINT `fk_alocacao_professor_profissional` FOREIGN KEY (`id_profissional`) REFERENCES `profissional` (`id_profissional`),
  CONSTRAINT `fk_alocacao_professor_trimestre` FOREIGN KEY (`id_trimestre`) REFERENCES `trimestre` (`id_trimestre`),
  CONSTRAINT `fk_alocacao_professor_turma` FOREIGN KEY (`id_turma`) REFERENCES `turma` (`id_turma`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alocacao_professor`
--

LOCK TABLES `alocacao_professor` WRITE;
/*!40000 ALTER TABLE `alocacao_professor` DISABLE KEYS */;
/*!40000 ALTER TABLE `alocacao_professor` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aluno`
--

LOCK TABLES `aluno` WRITE;
/*!40000 ALTER TABLE `aluno` DISABLE KEYS */;
INSERT INTO `aluno` VALUES (1,'Ana','Carlos','2026-09-03','a','as','as','as','as','as','as','as','as','as','2026-03-20 18:20:24','',1,1),(2,'Pedro','Maria',NULL,'',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2026-03-20 18:20:36',NULL,2,1),(3,'as','as','2026-09-03','as','as','as','as','as','as','as','as','as','as','2026-03-27 01:24:01',NULL,1,2);
/*!40000 ALTER TABLE `aluno` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `aluno_status_aluno`
--

DROP TABLE IF EXISTS `aluno_status_aluno`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `aluno_status_aluno` (
  `id_aluno` int NOT NULL,
  `id_status_aluno` int NOT NULL,
  `data_definicao_status_aluno` date DEFAULT NULL,
  `data_registo_status_aluno` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_aluno`,`id_status_aluno`),
  KEY `fk_aluno_status_aluno_status_idx` (`id_status_aluno`),
  CONSTRAINT `fk_aluno_status_aluno_aluno` FOREIGN KEY (`id_aluno`) REFERENCES `aluno` (`id_aluno`),
  CONSTRAINT `fk_aluno_status_aluno_status` FOREIGN KEY (`id_status_aluno`) REFERENCES `status_aluno` (`id_status_aluno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aluno_status_aluno`
--

LOCK TABLES `aluno_status_aluno` WRITE;
/*!40000 ALTER TABLE `aluno_status_aluno` DISABLE KEYS */;
/*!40000 ALTER TABLE `aluno_status_aluno` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ano_lectivo`
--

LOCK TABLES `ano_lectivo` WRITE;
/*!40000 ALTER TABLE `ano_lectivo` DISABLE KEYS */;
INSERT INTO `ano_lectivo` VALUES (1,'2026-2027'),(2,'2025-2026'),(3,'2024-2025');
/*!40000 ALTER TABLE `ano_lectivo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `avaliacao`
--

DROP TABLE IF EXISTS `avaliacao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `avaliacao` (
  `id_avaliacao` int NOT NULL AUTO_INCREMENT,
  `descricao_avaliacao` varchar(45) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `data_aplicacao` date DEFAULT NULL,
  `data_liminte_lancamento` date DEFAULT NULL,
  `peso_avaliacao` double DEFAULT NULL,
  `pontuacao_maxima_avaliacao` double DEFAULT NULL,
  `id_disciplina` int NOT NULL,
  `id_ano_lectivo` int NOT NULL,
  `id_trimestre` int NOT NULL,
  `id_profissional` int NOT NULL,
  `id_tipo_avaliacao` int NOT NULL,
  `id_turma` int NOT NULL,
  PRIMARY KEY (`id_avaliacao`),
  KEY `fk_avaliacao_disciplina1_idx` (`id_disciplina`),
  KEY `fk_avaliacao_ano_lectivo1_idx` (`id_ano_lectivo`),
  KEY `fk_avaliacao_trimestre1_idx` (`id_trimestre`),
  KEY `fk_avaliacao_profissional1_idx` (`id_profissional`),
  KEY `fk_avaliacao_tipo_avaliacao1_idx` (`id_tipo_avaliacao`),
  KEY `fk_avaliacao_turma1_idx` (`id_turma`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `avaliacao`
--

LOCK TABLES `avaliacao` WRITE;
/*!40000 ALTER TABLE `avaliacao` DISABLE KEYS */;
/*!40000 ALTER TABLE `avaliacao` ENABLE KEYS */;
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
  `descricao_categoriaprofissional` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id_categoriaprofissional`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoria_profissional`
--

LOCK TABLES `categoria_profissional` WRITE;
/*!40000 ALTER TABLE `categoria_profissional` DISABLE KEYS */;
INSERT INTO `categoria_profissional` VALUES (1,'Professor'),(2,'Director/a'),(3,'SubDirector/a'),(4,'Secretário/a'),(5,'Segurança'),(6,'Administrador/a'),(7,'Contabilista'),(8,'Conselho'),(9,'Técnico Informático'),(10,'Vigilante'),(11,'Cozinheira');
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
  `id_nivel` int DEFAULT NULL,
  PRIMARY KEY (`id_classe`),
  KEY `fk_classe_nivel_idx` (`id_nivel`),
  CONSTRAINT `fk_classe_nivel` FOREIGN KEY (`id_nivel`) REFERENCES `nivel_classe` (`id_nivel_classe`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `classe`
--

LOCK TABLES `classe` WRITE;
/*!40000 ALTER TABLE `classe` DISABLE KEYS */;
INSERT INTO `classe` VALUES (1,'1.ª Classe',NULL),(2,'2:ª Classe',NULL);
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
  `abreviatura` varchar(45) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `carga_horaria_semanal` int DEFAULT NULL,
  `id_eixo_curricular` int DEFAULT NULL,
  PRIMARY KEY (`id_disciplina`),
  KEY `fk_disciplina_eixo_curricular_idx` (`id_eixo_curricular`),
  CONSTRAINT `fk_disciplina_eixo_curricular` FOREIGN KEY (`id_eixo_curricular`) REFERENCES `eixo_curricular_disciplina` (`id_eixo_curricular`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `disciplina`
--

LOCK TABLES `disciplina` WRITE;
/*!40000 ALTER TABLE `disciplina` DISABLE KEYS */;
INSERT INTO `disciplina` VALUES (1,'Matemática','MAT',5,NULL),(2,'Português','PORT',4,NULL),(3,'Ciências','CIÊN',3,NULL),(4,'História','HIST',2,NULL),(5,'Geografia','GEO',2,NULL);
/*!40000 ALTER TABLE `disciplina` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `eixo_curricular_disciplina`
--

DROP TABLE IF EXISTS `eixo_curricular_disciplina`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `eixo_curricular_disciplina` (
  `id_eixo_curricular` int NOT NULL AUTO_INCREMENT,
  `descricao_eixo_curricular` varchar(45) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id_eixo_curricular`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `eixo_curricular_disciplina`
--

LOCK TABLES `eixo_curricular_disciplina` WRITE;
/*!40000 ALTER TABLE `eixo_curricular_disciplina` DISABLE KEYS */;
/*!40000 ALTER TABLE `eixo_curricular_disciplina` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `forma_pagamento`
--

LOCK TABLES `forma_pagamento` WRITE;
/*!40000 ALTER TABLE `forma_pagamento` DISABLE KEYS */;
INSERT INTO `forma_pagamento` VALUES (1,'Cash'),(2,'TPA'),(3,'Banco'),(4,'Bolsa');
/*!40000 ALTER TABLE `forma_pagamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `frequencia`
--

DROP TABLE IF EXISTS `frequencia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `frequencia` (
  `id_frequencia` int NOT NULL AUTO_INCREMENT,
  `id_matricula` int NOT NULL,
  `data_aula` date NOT NULL,
  `id_disciplina` int NOT NULL,
  `presenca` tinyint(1) DEFAULT '1',
  `justificativa_falta` text COLLATE utf8mb4_general_ci,
  PRIMARY KEY (`id_frequencia`),
  UNIQUE KEY `uk_frequencia_diaria` (`id_matricula`,`data_aula`,`id_disciplina`),
  KEY `id_disciplina` (`id_disciplina`),
  CONSTRAINT `frequencia_ibfk_1` FOREIGN KEY (`id_matricula`) REFERENCES `matricula` (`id_matricula`),
  CONSTRAINT `frequencia_ibfk_2` FOREIGN KEY (`id_disciplina`) REFERENCES `disciplina` (`id_disciplina`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `frequencia`
--

LOCK TABLES `frequencia` WRITE;
/*!40000 ALTER TABLE `frequencia` DISABLE KEYS */;
/*!40000 ALTER TABLE `frequencia` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grau_parentesco`
--

LOCK TABLES `grau_parentesco` WRITE;
/*!40000 ALTER TABLE `grau_parentesco` DISABLE KEYS */;
INSERT INTO `grau_parentesco` VALUES (1,'Pai'),(2,'Mãe'),(3,'Pai'),(4,'Mae');
/*!40000 ALTER TABLE `grau_parentesco` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `historico_escolar`
--

DROP TABLE IF EXISTS `historico_escolar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `historico_escolar` (
  `id_historico` int NOT NULL AUTO_INCREMENT,
  `id_aluno` int NOT NULL,
  `id_ano_letivo` int NOT NULL,
  `id_trimestre` int NOT NULL,
  `id_disciplina` int NOT NULL,
  `nota_final` decimal(5,2) DEFAULT NULL,
  `carga_horaria_cursada` int DEFAULT NULL,
  `frequencia_percentual` decimal(5,2) DEFAULT NULL,
  `resultado` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `data_emissao` date DEFAULT (curdate()),
  PRIMARY KEY (`id_historico`),
  KEY `id_aluno` (`id_aluno`),
  KEY `id_disciplina` (`id_disciplina`),
  CONSTRAINT `historico_escolar_ibfk_1` FOREIGN KEY (`id_aluno`) REFERENCES `aluno` (`id_aluno`),
  CONSTRAINT `historico_escolar_ibfk_2` FOREIGN KEY (`id_disciplina`) REFERENCES `disciplina` (`id_disciplina`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `historico_escolar`
--

LOCK TABLES `historico_escolar` WRITE;
/*!40000 ALTER TABLE `historico_escolar` DISABLE KEYS */;
/*!40000 ALTER TABLE `historico_escolar` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lancamento_nota`
--

DROP TABLE IF EXISTS `lancamento_nota`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lancamento_nota` (
  `id_lancamento` int NOT NULL AUTO_INCREMENT,
  `nota_obtida` double DEFAULT NULL,
  `falta_no_dia` tinyint DEFAULT NULL,
  `data_lancamento` datetime DEFAULT CURRENT_TIMESTAMP,
  `observacoes` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `publicado` tinyint DEFAULT NULL,
  `id_aluno` int NOT NULL,
  `id_avaliacao` int NOT NULL,
  `id_matricula` int NOT NULL,
  PRIMARY KEY (`id_lancamento`),
  KEY `fk_lancamentos_nota_aluno1_idx` (`id_aluno`),
  KEY `fk_lancamentos_nota_avaliacao1_idx` (`id_avaliacao`),
  KEY `fk_lancamentos_nota_matricula1_idx` (`id_matricula`),
  CONSTRAINT `fk_lancamentos_nota_aluno1` FOREIGN KEY (`id_aluno`) REFERENCES `aluno` (`id_aluno`),
  CONSTRAINT `fk_lancamentos_nota_avaliacao1` FOREIGN KEY (`id_avaliacao`) REFERENCES `avaliacao` (`id_avaliacao`),
  CONSTRAINT `fk_lancamentos_nota_matricula1` FOREIGN KEY (`id_matricula`) REFERENCES `matricula` (`id_matricula`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lancamento_nota`
--

LOCK TABLES `lancamento_nota` WRITE;
/*!40000 ALTER TABLE `lancamento_nota` DISABLE KEYS */;
/*!40000 ALTER TABLE `lancamento_nota` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `log_nota_auditoria`
--

DROP TABLE IF EXISTS `log_nota_auditoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `log_nota_auditoria` (
  `id_log` int NOT NULL AUTO_INCREMENT,
  `id_lancamento` int DEFAULT NULL,
  `id_usuario` int DEFAULT NULL COMMENT 'quem alterou',
  `nota_anterior` decimal(5,2) DEFAULT NULL,
  `nota_nova` decimal(5,2) DEFAULT NULL,
  `data_alteracao` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `motivo_alteracao` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id_log`),
  KEY `id_lancamento` (`id_lancamento`),
  CONSTRAINT `log_nota_auditoria_ibfk_1` FOREIGN KEY (`id_lancamento`) REFERENCES `lancamento_nota` (`id_lancamento`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `log_nota_auditoria`
--

LOCK TABLES `log_nota_auditoria` WRITE;
/*!40000 ALTER TABLE `log_nota_auditoria` DISABLE KEYS */;
/*!40000 ALTER TABLE `log_nota_auditoria` ENABLE KEYS */;
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
  `id_forma_pagamento` int DEFAULT NULL,
  `id_status_matricula` int DEFAULT NULL,
  PRIMARY KEY (`id_matricula`),
  KEY `fk_matricula_aluno1_idx` (`id_aluno`),
  KEY `fk_matricula_turma1_idx` (`id_turma`),
  KEY `fk_matricula_status_matricula_idx` (`id_status_matricula`),
  KEY `fk_matricula_forma_pagamento_idx` (`id_forma_pagamento`),
  CONSTRAINT `fk_matricula_aluno1` FOREIGN KEY (`id_aluno`) REFERENCES `aluno` (`id_aluno`),
  CONSTRAINT `fk_matricula_forma_pagamento` FOREIGN KEY (`id_forma_pagamento`) REFERENCES `forma_pagamento` (`id_forma_pagamento`),
  CONSTRAINT `fk_matricula_turma1` FOREIGN KEY (`id_turma`) REFERENCES `turma` (`id_turma`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `matricula`
--

LOCK TABLES `matricula` WRITE;
/*!40000 ALTER TABLE `matricula` DISABLE KEYS */;
INSERT INTO `matricula` VALUES (1,'2026-05-25 21:24:07',1,1,1,NULL),(2,'2026-05-25 21:33:14',1,1,2,NULL),(3,'2026-05-25 21:36:12',1,1,2,NULL),(4,'2026-05-25 21:39:13',1,1,1,NULL),(5,'2026-05-25 21:46:15',1,1,1,NULL);
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
-- Table structure for table `nivel_classe`
--

DROP TABLE IF EXISTS `nivel_classe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nivel_classe` (
  `id_nivel_classe` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id_nivel_classe`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nivel_classe`
--

LOCK TABLES `nivel_classe` WRITE;
/*!40000 ALTER TABLE `nivel_classe` DISABLE KEYS */;
/*!40000 ALTER TABLE `nivel_classe` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pauta_trimestral`
--

DROP TABLE IF EXISTS `pauta_trimestral`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pauta_trimestral` (
  `id_pauta` int NOT NULL AUTO_INCREMENT,
  `id_matricula` int NOT NULL,
  `id_disciplina` int NOT NULL,
  `id_trimestre` int NOT NULL,
  `id_ano_letivo` year NOT NULL,
  `media_parcial` decimal(5,2) DEFAULT NULL COMMENT 'Média antes do exame final',
  `nota_exame_final` decimal(5,2) DEFAULT NULL,
  `media_final` decimal(5,2) GENERATED ALWAYS AS ((case when (`nota_exame_final` is not null) then ((`media_parcial` + `nota_exame_final`) / 2) else `media_parcial` end)) STORED,
  `resultado_final` enum('aprovado','reprovado','exame','aprovado_com_exame') COLLATE utf8mb4_general_ci GENERATED ALWAYS AS ((case when ((`media_final` >= 6.0) and (`nota_exame_final` is null)) then _utf8mb4'aprovado' when ((`media_final` >= 5.0) and (`nota_exame_final` is not null)) then _utf8mb4'aprovado_com_exame' when ((`media_final` < 5.0) and (`nota_exame_final` is not null)) then _utf8mb4'reprovado' when ((`media_parcial` < 6.0) and (`nota_exame_final` is null)) then _utf8mb4'exame' when ((`media_parcial` < 6.0) and (`nota_exame_final` < 5.0)) then _utf8mb4'reprovado' else _utf8mb4'reprovado' end)) VIRTUAL,
  `data_fecho_ano_lectivo` date DEFAULT NULL,
  PRIMARY KEY (`id_pauta`),
  UNIQUE KEY `uk_pauta` (`id_matricula`,`id_disciplina`,`id_trimestre`,`id_ano_letivo`),
  KEY `id_disciplina` (`id_disciplina`),
  CONSTRAINT `pauta_trimestral_ibfk_1` FOREIGN KEY (`id_matricula`) REFERENCES `matricula` (`id_matricula`),
  CONSTRAINT `pauta_trimestral_ibfk_2` FOREIGN KEY (`id_disciplina`) REFERENCES `disciplina` (`id_disciplina`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pauta_trimestral`
--

LOCK TABLES `pauta_trimestral` WRITE;
/*!40000 ALTER TABLE `pauta_trimestral` DISABLE KEYS */;
/*!40000 ALTER TABLE `pauta_trimestral` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `periodo`
--

DROP TABLE IF EXISTS `periodo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `periodo` (
  `id_periodo` int NOT NULL AUTO_INCREMENT,
  `descricao_periodo` varchar(45) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id_periodo`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `periodo`
--

LOCK TABLES `periodo` WRITE;
/*!40000 ALTER TABLE `periodo` DISABLE KEYS */;
INSERT INTO `periodo` VALUES (1,'Manhã'),(2,'Tarde');
/*!40000 ALTER TABLE `periodo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `profissional`
--

DROP TABLE IF EXISTS `profissional`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `profissional` (
  `id_profissional` int NOT NULL AUTO_INCREMENT,
  `bi_profissional` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `nome_profissional` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `sobrenome_profissional` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `dataNascimento_profissional` date NOT NULL,
  `numeroCasa_profissional` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `rua_profissional` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `bairro_profissional` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `telefoneUnitel_profissional` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `email_profissional` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `habilitacoesLiterarias_profissional` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `observacoes_profissional` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
  `id_sexo` int NOT NULL,
  `id_municipio` int NOT NULL,
  `id_categoriaprofissional` int NOT NULL,
  PRIMARY KEY (`id_profissional`),
  KEY `fk_profissional_sexo` (`id_sexo`),
  KEY `fk_profissional_municipio` (`id_municipio`),
  KEY `fk_profissional_categoria` (`id_categoriaprofissional`),
  CONSTRAINT `fk_profissional_categoria` FOREIGN KEY (`id_categoriaprofissional`) REFERENCES `categoria_profissional` (`id_categoriaprofissional`),
  CONSTRAINT `fk_profissional_municipio` FOREIGN KEY (`id_municipio`) REFERENCES `municipio` (`id_municipio`),
  CONSTRAINT `fk_profissional_sexo` FOREIGN KEY (`id_sexo`) REFERENCES `sexo` (`id_sexo`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `profissional`
--

LOCK TABLES `profissional` WRITE;
/*!40000 ALTER TABLE `profissional` DISABLE KEYS */;
INSERT INTO `profissional` VALUES (1,'00654444','António','Mendonça','1986-01-17','a','a','a','(24) 922 222 222','antoniomendonca','Ensino Superior','Sem descrições',2,73,6),(2,'47281948','Nádia','Maderes','2002-09-20','a','a','a','(24) 911 111 111','nadiamaderes','Ensino Superior','sem',1,9,7),(3,'0052555','Hélder','Joaquim','1990-07-05','20','02','Marçal','936554455','helderjoaquim','Ensino Superior',NULL,2,20,6),(4,'000124587UE0','Mário','Pascoal','1994-05-05','1','2','Plutão','945326355','mariopascol@gmail.com','Ensino Superior','Sem descrições',2,135,10),(5,'29742947','José','Gourgel','2026-05-12','1','1','1','(24) 966 666 666','josegourgel','Ensino Superior','Sem descrições',2,1,8),(6,'297447','Jorge','Gourgel','2024-05-12','1','1','1','(24) 966 666 666','josegourgel','Ensino Superior','s',2,11,1),(7,'3088329389EU271','Cristiano','Alves','2000-03-23','11','2','Scongolences','(24) 927 447 942','cristianoalves@gmail.com','Ensino Superior','Sem descrições',2,95,6);
/*!40000 ALTER TABLE `profissional` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `propina`
--

DROP TABLE IF EXISTS `propina`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `propina` (
  `id_propina` int NOT NULL AUTO_INCREMENT,
  `data_pagamento` datetime DEFAULT CURRENT_TIMESTAMP,
  `id_aluno` int DEFAULT NULL,
  `id_ano_lectivo` int DEFAULT NULL,
  `id_forma_pagamento` int DEFAULT NULL,
  `observacoes` varchar(250) DEFAULT NULL,
  `data_hora_registo` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_propina`),
  KEY `fk_proprina_aluno_idx` (`id_aluno`),
  KEY `fk_proprina_ano_lectivo_idx` (`id_ano_lectivo`),
  KEY `fk_proprina_forma_pagamento_idx` (`id_forma_pagamento`),
  CONSTRAINT `fk_proprina_aluno` FOREIGN KEY (`id_aluno`) REFERENCES `aluno` (`id_aluno`),
  CONSTRAINT `fk_proprina_ano_lectivo` FOREIGN KEY (`id_ano_lectivo`) REFERENCES `ano_lectivo` (`id_ano_lectivo`),
  CONSTRAINT `fk_proprina_forma_pagamento` FOREIGN KEY (`id_forma_pagamento`) REFERENCES `forma_pagamento` (`id_forma_pagamento`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `propina`
--

LOCK TABLES `propina` WRITE;
/*!40000 ALTER TABLE `propina` DISABLE KEYS */;
INSERT INTO `propina` VALUES (1,'2026-05-17 12:11:01',1,2,1,NULL,'2026-05-17 12:11:01'),(2,'2026-05-17 12:15:01',1,2,4,NULL,'2026-05-17 12:15:01'),(3,'2026-05-17 12:29:55',1,2,2,NULL,'2026-05-17 12:29:55'),(4,'2026-05-17 12:34:50',1,1,2,NULL,'2026-05-17 12:34:50'),(5,'2026-05-17 12:37:04',1,2,1,NULL,'2026-05-17 12:37:04'),(6,'2026-05-17 12:40:24',1,1,2,NULL,'2026-05-17 12:40:24'),(7,'2026-05-17 12:45:03',1,2,1,NULL,'2026-05-17 12:45:03'),(8,'2026-05-17 12:48:42',1,2,2,NULL,'2026-05-17 12:48:42'),(9,'2026-05-17 12:52:07',1,2,1,NULL,'2026-05-17 12:52:07'),(10,'2026-05-17 12:54:09',1,2,1,NULL,'2026-05-17 12:54:09'),(11,'2026-05-17 12:59:29',1,2,2,NULL,'2026-05-17 12:59:29'),(12,'2026-05-17 16:57:20',2,3,3,NULL,'2026-05-17 16:57:20'),(13,'2026-05-17 17:00:09',3,2,1,NULL,'2026-05-17 17:00:09'),(14,'2026-05-17 17:11:00',3,2,4,NULL,'2026-05-17 17:11:00'),(15,'2026-05-17 17:16:29',2,1,1,'','2026-05-17 17:16:29'),(16,'2026-05-17 17:21:40',2,2,4,'','2026-05-17 17:21:40'),(17,'2026-05-17 17:32:49',1,1,4,'','2026-05-17 17:32:49'),(18,'2026-05-17 17:45:28',2,1,2,'','2026-05-17 17:45:28');
/*!40000 ALTER TABLE `propina` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proprina_detalhes`
--

DROP TABLE IF EXISTS `proprina_detalhes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `proprina_detalhes` (
  `id_propina` int NOT NULL,
  `id_mes_propina` int NOT NULL,
  `valor_mes_proprina` double DEFAULT NULL,
  PRIMARY KEY (`id_propina`,`id_mes_propina`),
  KEY `fk_proprina_detalhes_me_idx` (`id_mes_propina`),
  CONSTRAINT `fk_proprina_detalhes_me` FOREIGN KEY (`id_mes_propina`) REFERENCES `mes_propina` (`id_mes_propina`),
  CONSTRAINT `fk_proprina_detalhes_propina` FOREIGN KEY (`id_propina`) REFERENCES `propina` (`id_propina`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proprina_detalhes`
--

LOCK TABLES `proprina_detalhes` WRITE;
/*!40000 ALTER TABLE `proprina_detalhes` DISABLE KEYS */;
INSERT INTO `proprina_detalhes` VALUES (16,1,0),(16,2,0),(17,2,0),(17,3,0),(18,1,2000),(18,2,2000),(18,3,2000),(18,4,2000);
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
-- Table structure for table `recuperacao_especial`
--

DROP TABLE IF EXISTS `recuperacao_especial`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recuperacao_especial` (
  `id_recuperacao` int NOT NULL AUTO_INCREMENT,
  `id_aluno` int NOT NULL,
  `id_disciplina` int NOT NULL,
  `id_turma` int NOT NULL,
  `tipo_recuperacao` enum('final','paralela','segunda_chamada','dependencia') COLLATE utf8mb4_general_ci NOT NULL,
  `data_realizacao` date DEFAULT NULL,
  `nota_obtida` decimal(5,2) DEFAULT NULL,
  `substitui_media` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id_recuperacao`),
  KEY `id_aluno` (`id_aluno`),
  KEY `id_disciplina` (`id_disciplina`),
  KEY `id_turma` (`id_turma`),
  CONSTRAINT `recuperacao_especial_ibfk_1` FOREIGN KEY (`id_aluno`) REFERENCES `aluno` (`id_aluno`),
  CONSTRAINT `recuperacao_especial_ibfk_2` FOREIGN KEY (`id_disciplina`) REFERENCES `disciplina` (`id_disciplina`),
  CONSTRAINT `recuperacao_especial_ibfk_3` FOREIGN KEY (`id_turma`) REFERENCES `turma` (`id_turma`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recuperacao_especial`
--

LOCK TABLES `recuperacao_especial` WRITE;
/*!40000 ALTER TABLE `recuperacao_especial` DISABLE KEYS */;
/*!40000 ALTER TABLE `recuperacao_especial` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sala`
--

LOCK TABLES `sala` WRITE;
/*!40000 ALTER TABLE `sala` DISABLE KEYS */;
INSERT INTO `sala` VALUES (1,'A'),(2,'B');
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
INSERT INTO `servico` VALUES (1,'Serviço 01',50),(2,'Serviço 02',10),(3,'Serviço 03',100);
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
INSERT INTO `sexo` VALUES (1,'Masculino'),(2,'Femenino');
/*!40000 ALTER TABLE `sexo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `status_aluno`
--

DROP TABLE IF EXISTS `status_aluno`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `status_aluno` (
  `id_status_aluno` int NOT NULL AUTO_INCREMENT,
  `descricao_status_aluno` varchar(45) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id_status_aluno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status_aluno`
--

LOCK TABLES `status_aluno` WRITE;
/*!40000 ALTER TABLE `status_aluno` DISABLE KEYS */;
/*!40000 ALTER TABLE `status_aluno` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `status_matricula`
--

DROP TABLE IF EXISTS `status_matricula`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `status_matricula` (
  `id_status_matricula` int NOT NULL AUTO_INCREMENT,
  `descricao_status_matricula` varchar(45) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id_status_matricula`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status_matricula`
--

LOCK TABLES `status_matricula` WRITE;
/*!40000 ALTER TABLE `status_matricula` DISABLE KEYS */;
/*!40000 ALTER TABLE `status_matricula` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_avaliacao`
--

DROP TABLE IF EXISTS `tipo_avaliacao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipo_avaliacao` (
  `id_tipo_avaliacao` int NOT NULL AUTO_INCREMENT,
  `descricao_tipo_avaliacao` varchar(45) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `peso_tipo_avaliacao` varchar(45) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id_tipo_avaliacao`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_avaliacao`
--

LOCK TABLES `tipo_avaliacao` WRITE;
/*!40000 ALTER TABLE `tipo_avaliacao` DISABLE KEYS */;
INSERT INTO `tipo_avaliacao` VALUES (1,'Prova Bimestral','2.0'),(2,'Trabalho Prático','1.5'),(3,'Exame Final','3.0'),(4,'Atividade Online','0.8'),(5,'Recuperação','1.0');
/*!40000 ALTER TABLE `tipo_avaliacao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trimestre`
--

DROP TABLE IF EXISTS `trimestre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trimestre` (
  `id_trimestre` int NOT NULL AUTO_INCREMENT,
  `descricao_trimestre` varchar(45) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id_trimestre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trimestre`
--

LOCK TABLES `trimestre` WRITE;
/*!40000 ALTER TABLE `trimestre` DISABLE KEYS */;
/*!40000 ALTER TABLE `trimestre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `turma`
--

DROP TABLE IF EXISTS `turma`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `turma` (
  `id_turma` int NOT NULL AUTO_INCREMENT,
  `codigo_turma` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `id_ano_lectivo` int NOT NULL,
  `id_classe` int NOT NULL,
  `numero_maximo` int DEFAULT NULL,
  `activa` tinyint DEFAULT NULL,
  `data_registo` datetime DEFAULT CURRENT_TIMESTAMP,
  `id_sala` int NOT NULL,
  `id_periodo` int DEFAULT NULL,
  `observacoes_turma` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`id_turma`),
  UNIQUE KEY `codigo_turma_UNIQUE` (`codigo_turma`),
  KEY `fk_turma_ano_lectivo1_idx` (`id_ano_lectivo`),
  KEY `fk_turma_classe1_idx` (`id_classe`),
  KEY `fk_turma_sala1_idx` (`id_sala`),
  KEY `fk_turma_periodo_idx` (`id_periodo`),
  CONSTRAINT `fk_turma_ano_lectivo1` FOREIGN KEY (`id_ano_lectivo`) REFERENCES `ano_lectivo` (`id_ano_lectivo`),
  CONSTRAINT `fk_turma_classe1` FOREIGN KEY (`id_classe`) REFERENCES `classe` (`id_classe`),
  CONSTRAINT `fk_turma_sala1` FOREIGN KEY (`id_sala`) REFERENCES `sala` (`id_sala`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `turma`
--

LOCK TABLES `turma` WRITE;
/*!40000 ALTER TABLE `turma` DISABLE KEYS */;
INSERT INTO `turma` VALUES (1,NULL,1,1,22,1,'2026-01-01 00:00:00',1,1,'1');
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

-- Dump completed on 2026-07-03 11:55:50
