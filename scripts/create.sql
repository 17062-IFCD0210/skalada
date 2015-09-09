-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         5.6.17 - MySQL Community Server (GPL)
-- SO del servidor:              Win64
-- HeidiSQL Versión:             9.1.0.4867
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Volcando estructura de base de datos para eskalada
CREATE DATABASE IF NOT EXISTS `eskalada` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `eskalada`;


-- Volcando estructura para tabla eskalada.grado
CREATE TABLE IF NOT EXISTS `grado` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(10) NOT NULL COMMENT 'Grado de dificultad de la via de escalada, p.e. (A, B y C) -> 4A, 5B',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla eskalada.grado: ~17 rows (aproximadamente)
/*!40000 ALTER TABLE `grado` DISABLE KEYS */;
INSERT INTO `grado` (`id`, `nombre`) VALUES
	(1, 'Ab'),
	(2, 'Ae'),
	(3, 'A0'),
	(4, 'A1 o C1'),
	(5, 'A2 o C2'),
	(6, 'A3 o C3'),
	(7, 'A4 o C4'),
	(8, 'A5 o C5'),
	(9, 'A6'),
	(10, '6a'),
	(11, '6b'),
	(12, 'V'),
	(13, 'V+'),
	(14, '6b+'),
	(15, '7b+'),
	(16, '9a'),
	(17, '7c+');
/*!40000 ALTER TABLE `grado` ENABLE KEYS */;


-- Volcando estructura para tabla eskalada.sector
CREATE TABLE IF NOT EXISTS `sector` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `zona_id1` int(11) NOT NULL,
  PRIMARY KEY (`id`,`zona_id1`),
  KEY `fk_sector_zona1_idx` (`zona_id1`),
  CONSTRAINT `fk_sector_zona1` FOREIGN KEY (`zona_id1`) REFERENCES `zona` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla eskalada.sector: ~10 rows (aproximadamente)
/*!40000 ALTER TABLE `sector` DISABLE KEYS */;
INSERT INTO `sector` (`id`, `nombre`, `zona_id1`) VALUES
	(1, 'Cara Oeste', 1),
	(2, 'Cara Sur', 1),
	(3, 'Ogoño', 2),
	(4, 'Lauretzape', 3),
	(5, 'Elosuko Harrobia', 3),
	(6, 'Urrestei', 4),
	(7, 'Labargorri', 4),
	(8, 'Alluitz', 4),
	(9, 'Tercer espolón', 4),
	(10, 'Primer espolón', 4);
/*!40000 ALTER TABLE `sector` ENABLE KEYS */;


-- Volcando estructura para tabla eskalada.tipo_escalada
CREATE TABLE IF NOT EXISTS `tipo_escalada` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla eskalada.tipo_escalada: ~11 rows (aproximadamente)
/*!40000 ALTER TABLE `tipo_escalada` DISABLE KEYS */;
INSERT INTO `tipo_escalada` (`id`, `nombre`) VALUES
	(1, 'Libre'),
	(2, 'Clásica'),
	(3, 'Alpina'),
	(4, 'Artificial'),
	(5, 'Dry-tooling'),
	(6, 'Big Wall'),
	(7, 'Solitaria'),
	(8, 'Deportiva'),
	(9, 'Bloque'),
	(10, 'Psicobloc'),
	(11, 'Urbana');
/*!40000 ALTER TABLE `tipo_escalada` ENABLE KEYS */;


-- Volcando estructura para tabla eskalada.via
CREATE TABLE IF NOT EXISTS `via` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(200) NOT NULL,
  `longitud` int(11) NOT NULL DEFAULT '0' COMMENT 'Longitud de la via de escalada en metros.',
  `descripcion` text,
  `grado_id` int(11) NOT NULL,
  `tipo_escalada_id` int(11) NOT NULL,
  `sector_id` int(11) NOT NULL,
  PRIMARY KEY (`id`,`grado_id`,`tipo_escalada_id`,`sector_id`),
  KEY `fk_via_grado1_idx` (`grado_id`),
  KEY `fk_via_tipo_escalada1_idx` (`tipo_escalada_id`),
  KEY `fk_via_sector1_idx` (`sector_id`),
  CONSTRAINT `fk_via_grado1` FOREIGN KEY (`grado_id`) REFERENCES `grado` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_via_tipo_escalada1` FOREIGN KEY (`tipo_escalada_id`) REFERENCES `tipo_escalada` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_via_sector1` FOREIGN KEY (`sector_id`) REFERENCES `sector` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla eskalada.via: ~14 rows (aproximadamente)
/*!40000 ALTER TABLE `via` DISABLE KEYS */;
INSERT INTO `via` (`id`, `nombre`, `longitud`, `descripcion`, `grado_id`, `tipo_escalada_id`, `sector_id`) VALUES
	(3, 'Irentxo', 30, 'Mantenida', 10, 8, 10),
	(4, 'Bosque de los Inurios', 18, 'Vía con 5 movimientos muy duros', 11, 8, 10),
	(6, 'Normal', 120, 'Larga y bonita vía con pasos de todo tipo. Reequipada con químicos en su totalidad', 12, 8, 9),
	(7, 'La Cabra de Judas', 400, 'A1 de artificial. Roca buena en general. Material: Fisureros, cordinos, estribos', 10, 4, 8),
	(9, 'María chimenea', 140, 'Último largo bastante lavado, técnica y de coco', 13, 8, 7),
	(10, 'Arista de Urrestei', 300, 'Grado asequible para utilizar tu chatarra', 13, 2, 6),
	(11, 'Oroimen', 45, 'Equipada por Carmen Urkiola, Ricardo Garaigordobil y Manu Marcos', 10, 8, 5),
	(12, 'Amaituezina', 50, 'Reunión común con la 7', 10, 8, 5),
	(13, 'Abiadura handiko trena KK', 30, 'Excelente roca', 14, 8, 5),
	(16, 'Ezinezkoa', 30, 'Buena roca caliza', 15, 8, 4),
	(18, 'Gaviotas', 130, 'Escalar a partir de Septiembre por nidificación de aves', 13, 8, 3),
	(19, 'Directa de los Martínez', 160, 'Material necesario: 8 cintas exprés, juego completo de friends desde el 0,3 de los camalot hasta el n2 (nº3 y empotradores opcionales) casco imprescindible', 12, 2, 2),
	(20, 'Orbayu', 500, 'La vía clásica más dificil del mundo', 16, 3, 1),
	(21, 'Murciana 78', 600, 'Dominar el 6b+ a vista. Pasos difíciles: A1', 17, 8, 1);
/*!40000 ALTER TABLE `via` ENABLE KEYS */;


-- Volcando estructura para tabla eskalada.zona
CREATE TABLE IF NOT EXISTS `zona` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nombre_UNIQUE` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla eskalada.zona: ~4 rows (aproximadamente)
/*!40000 ALTER TABLE `zona` DISABLE KEYS */;
INSERT INTO `zona` (`id`, `nombre`) VALUES
	(4, 'Atxarte'),
	(2, 'Cabo Ogoño'),
	(1, 'Naranjo de Bulnes'),
	(3, 'Untzillaitz Sur');
/*!40000 ALTER TABLE `zona` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
