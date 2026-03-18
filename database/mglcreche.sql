
/**
 * Author:  «claudiomendonca»
 * Created: 18/03/2026
 */

// Base de Dados ainda em implementação

CREATE DATABASE  IF NOT EXISTS mglcreche;
USE mglcreche;

DROP TABLE IF EXISTS aluno;

CREATE TABLE aluno(
  id_aluno int NOT NULL AUTO_INCREMENT,
  nome_aluno varchar(100) DEFAULT NULL,
  sobrenome_aluno varchar(100) DEFAULT NULL,
  data_nascimento_aluno date DEFAULT NULL,
  grupo_sanguineo_aluno char(5) DEFAULT NULL,
  casa_aluno varchar(45) DEFAULT NULL,
  rua_aluno varchar(100) DEFAULT NULL,
  bairro_aluno varchar(200) DEFAULT NULL,
  nome_mae_aluno varchar(45) DEFAULT NULL,
  sobrenome_mae_aluno varchar(45) DEFAULT NULL,
  telefone_mae_aluno varchar(45) DEFAULT NULL,
  nome_pai_aluno varchar(45) DEFAULT NULL,
  sobrenome_pai_aluno varchar(45) DEFAULT NULL,
  telefone_pai_aluno varchar(45) DEFAULT NULL,
  data_registo_aluno datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id_aluno)
);

DROP TABLE IF EXISTS grau_parentesco;

CREATE TABLE grau_parentesco (
  id_grau_parentesco int NOT NULL AUTO_INCREMENT,
  descricao_grau_parentesco varchar(100) DEFAULT NULL,
  PRIMARY KEY (id_grau_parentesco)
);


DROP TABLE IF EXISTS responsavel_busca_aluno;

CREATE TABLE responsavel_busca_aluno(
  id_responsavel int NOT NULL AUTO_INCREMENT,
  nome_responsavel varchar(100) DEFAULT NULL,
  sobrenome_responsavel varchar(100) DEFAULT NULL,
  data_nascimento_responsavel date DEFAULT NULL,
  casa_responsavel varchar(45) DEFAULT NULL,
  rua_responsavel varchar(100) DEFAULT NULL,
  bairro_responsavel varchar(100) DEFAULT NULL,
  telefone_responsavel varchar(45) DEFAULT NULL,
  data_registo_responsavel datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id_responsavel)
);


DROP TABLE IF EXISTS cadastro_responsavel_busca;

CREATE TABLE cadastro_responsavel_busca (
  id_registo_responsavel_busca int NOT NULL AUTO_INCREMENT,
  data_registo datetime DEFAULT CURRENT_TIMESTAMP,
  id_aluno int NOT NULL,
  id_grau_parentesco int NOT NULL,
  id_responsavel int NOT NULL,
  PRIMARY KEY (id_registo_responsavel_busca),
  KEY fk_cadastro_responsavel_busca_aluno_idx (id_aluno),
  KEY fk_cadastro_responsavel_busca_grau_parentesco1_idx (id_grau_parentesco),
  KEY fk_cadastro_responsavel_busca_responsavel_busca_aluno1_idx (id_responsavel),
  CONSTRAINT fk_cadastro_responsavel_busca_aluno FOREIGN KEY (id_aluno) REFERENCES aluno (id_aluno),
  CONSTRAINT fk_cadastro_responsavel_busca_grau_parentesco1 FOREIGN KEY (id_grau_parentesco) REFERENCES grau_parentesco(id_grau_parentesco),
  CONSTRAINT fk_cadastro_responsavel_busca_responsavel_busca_aluno1 FOREIGN KEY (id_responsavel) REFERENCES responsavel_busca_aluno (id_responsavel)
);

