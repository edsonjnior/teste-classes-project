/**
 * Author:  junior
 * Created: 25/06/2019
 */

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

CREATE DATABASE IF NOT EXISTS `classes_db` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `classes_db`;

CREATE TABLE IF NOT EXISTS `aluno` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cpf` varchar(255) NOT NULL,
  `data_nascimento` datetime NOT NULL,
  `email` varchar(255) NOT NULL,
  `nome` varchar(255) NOT NULL,
  `senha` varchar(30) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_crrvmtky7d9tfarahi4jahewg` (`cpf`),
  UNIQUE KEY `UK_3wpes15e0anbfaa4do0pey97k` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `aluno_teste` (
  `teste_id` bigint(20) NOT NULL,
  `aluno_id` bigint(20) NOT NULL,
  KEY `FK_8c5fwyq79qkg2d9noslvoctl6` (`aluno_id`),
  KEY `FK_kltgwrnt5nw097ix1aaymspfr` (`teste_id`),
  CONSTRAINT `FK_kltgwrnt5nw097ix1aaymspfr` FOREIGN KEY (`teste_id`) REFERENCES `teste` (`id`),
  CONSTRAINT `FK_8c5fwyq79qkg2d9noslvoctl6` FOREIGN KEY (`aluno_id`) REFERENCES `aluno` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `arquivo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ano` varchar(255) NOT NULL,
  `caminho` varchar(255) DEFAULT NULL,
  `etapa` varchar(255) NOT NULL,
  `tipo` varchar(255) DEFAULT NULL,
  `teste_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_689muffs7q2ih6y7lbbiimi4p` (`teste_id`),
  CONSTRAINT `FK_689muffs7q2ih6y7lbbiimi4p` FOREIGN KEY (`teste_id`) REFERENCES `teste` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `curso` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(255) NOT NULL,
  `serie` varchar(255) NOT NULL,
  `unidade` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `sala` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `lotacao_maxima` int(11) NOT NULL,
  `nome` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `teste` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `data_evento` date NOT NULL,
  `data_inicio_evento` time NOT NULL,
  `data_inicio_inscricao` datetime NOT NULL,
  `data_termino_evento` time NOT NULL,
  `data_termino_inscricao` datetime NOT NULL,
  `info_evento` varchar(255) NOT NULL,
  `segmento` varchar(255) NOT NULL,
  `status` varchar(255) NOT NULL,
  `titulo` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `teste_texto` (
  `teste_id` bigint(20) NOT NULL,
  `texto` varchar(255) DEFAULT NULL,
  KEY `FK_48f6uten3afvj4l5t4b0px7hx` (`teste_id`),
  CONSTRAINT `FK_48f6uten3afvj4l5t4b0px7hx` FOREIGN KEY (`teste_id`) REFERENCES `teste` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `texto` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `texto` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
