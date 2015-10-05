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
  `nombre` varchar(10) NOT NULL COMMENT 'Grado de dificultad de la via de escalada, por ejemplo: ',
  `descripcion` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla eskalada.grado: ~9 rows (aproximadamente)
DELETE FROM `grado`;
/*!40000 ALTER TABLE `grado` DISABLE KEYS */;
INSERT INTO `grado` (`id`, `nombre`, `descripcion`) VALUES
	(1, '6a', NULL),
	(2, '6b', NULL),
	(3, 'V', NULL),
	(4, 'V+', NULL),
	(5, '6b+\r\n', NULL),
	(6, '7b+\r\n', NULL),
	(7, '9a\r\n', NULL),
	(8, '7c+\r\n', NULL),
	(12, '6a+', 'VIA COMPLICADA');
/*!40000 ALTER TABLE `grado` ENABLE KEYS */;


-- Volcando estructura para tabla eskalada.rol
CREATE TABLE IF NOT EXISTS `rol` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL DEFAULT '0',
  `descripcion` varchar(250) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla eskalada.rol: ~2 rows (aproximadamente)
DELETE FROM `rol`;
/*!40000 ALTER TABLE `rol` DISABLE KEYS */;
INSERT INTO `rol` (`id`, `nombre`, `descripcion`) VALUES
	(1, 'administrador', 'Administrador del sistema'),
	(2, 'usuario', 'Usuario estandar');
/*!40000 ALTER TABLE `rol` ENABLE KEYS */;


-- Volcando estructura para tabla eskalada.sector
CREATE TABLE IF NOT EXISTS `sector` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `zona` int(11) NOT NULL,
  `imagen` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`,`zona`),
  KEY `fk_sector_zona1_idx` (`zona`),
  CONSTRAINT `fk_sector_zona1` FOREIGN KEY (`zona`) REFERENCES `zona` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla eskalada.sector: ~10 rows (aproximadamente)
DELETE FROM `sector`;
/*!40000 ALTER TABLE `sector` DISABLE KEYS */;
INSERT INTO `sector` (`id`, `nombre`, `zona`, `imagen`) VALUES
	(2, 'Primer espolón', 1, NULL),
	(3, 'Tercer espolón\r\n', 1, NULL),
	(5, 'Alluitz', 1, NULL),
	(6, 'Labargorri\r\n', 1, NULL),
	(7, 'Urrestei\r\n', 1, NULL),
	(8, 'Elosuko Harrobia\r\n', 2, NULL),
	(9, 'Lauretzape\r\n', 2, NULL),
	(10, 'Ogoño\r\n', 4, NULL),
	(11, 'Cara Sur\r\n', 3, NULL),
	(12, 'Cara Oeste\r\n', 3, NULL);
/*!40000 ALTER TABLE `sector` ENABLE KEYS */;


-- Volcando estructura para tabla eskalada.tipo_escalada
CREATE TABLE IF NOT EXISTS `tipo_escalada` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `descripcion` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla eskalada.tipo_escalada: ~4 rows (aproximadamente)
DELETE FROM `tipo_escalada`;
/*!40000 ALTER TABLE `tipo_escalada` DISABLE KEYS */;
INSERT INTO `tipo_escalada` (`id`, `nombre`, `descripcion`) VALUES
	(1, 'Deportiva', NULL),
	(2, 'Clásica', NULL),
	(3, 'Alpina\r\n', NULL),
	(4, 'Artificial', NULL);
/*!40000 ALTER TABLE `tipo_escalada` ENABLE KEYS */;


-- Volcando estructura para tabla eskalada.usuario
CREATE TABLE IF NOT EXISTS `usuario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(100) NOT NULL DEFAULT '0',
  `nombre` varchar(100) NOT NULL DEFAULT '0',
  `password` varchar(30) NOT NULL DEFAULT '0',
  `id_rol` int(11) NOT NULL DEFAULT '0',
  `validado` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0 no validado, 1 validado',
  `token` varchar(250) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`,`nombre`),
  KEY `fk_usuario_rol` (`id_rol`),
  CONSTRAINT `fk_usuario_rol` FOREIGN KEY (`id_rol`) REFERENCES `rol` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla eskalada.usuario: ~7 rows (aproximadamente)
DELETE FROM `usuario`;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` (`id`, `email`, `nombre`, `password`, `id_rol`, `validado`, `token`) VALUES
	(1, 'aaa@aaa.com', 'AAA', '1234', 1, 0, ''),
	(2, 'lolo@lolo.com', 'lol', '12', 2, 0, ''),
	(3, 'lili@lili.com', 'troll', '124578', 2, 0, ''),
	(4, 'lili@hotmail.com', 'lulu', '123456', 2, 1, ''),
	(5, 'roller@roller.com', 'roller', '123478', 2, 0, ''),
	(6, 'derby@gmail.com', 'derby', '456789', 2, 0, ''),
	(7, 'laragonzalez.bm@gmail.com', 'lara', '123456', 2, 1, 'BK60W6GSPA8U43QFYA7TLHWU5AN0DFYD18FG0J3DL9VCWRA0DXPU6J67A0A1UPJ4NAJITHD45YQN1WL7CRQWL8NARF0KSGK4Y7LPAUEUCA9YXC5G5MHGETUG2Y9AZV5CGP25FPJK7TL72THXUCYVZX5LCLN4YY0CLFLW32IC9FKSTXB0PRST0GO1DPFJI5GQY4A4QX3U46Z8BS11NHULU0LUN2J3MSGHT0FQLT8E5DBLL0AIFXAG7O5OIK');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;


-- Volcando estructura para tabla eskalada.via
CREATE TABLE IF NOT EXISTS `via` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `longitud` int(11) NOT NULL DEFAULT '0' COMMENT 'Longitud de la via de escalada en metros',
  `descripcion` text,
  `id_grado` int(11) NOT NULL,
  `id_tipo_escalada` int(11) NOT NULL,
  `id_sector` int(11) NOT NULL,
  PRIMARY KEY (`id`,`id_grado`,`id_tipo_escalada`,`id_sector`),
  KEY `fk_via_grado_idx` (`id_grado`),
  KEY `fk_via_tipo_escalada1_idx` (`id_tipo_escalada`),
  KEY `fk_via_sector1_idx` (`id_sector`),
  CONSTRAINT `fk_via_grado` FOREIGN KEY (`id_grado`) REFERENCES `grado` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_via_sector1` FOREIGN KEY (`id_sector`) REFERENCES `sector` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_via_tipo_escalada1` FOREIGN KEY (`id_tipo_escalada`) REFERENCES `tipo_escalada` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla eskalada.via: ~14 rows (aproximadamente)
DELETE FROM `via`;
/*!40000 ALTER TABLE `via` DISABLE KEYS */;
INSERT INTO `via` (`id`, `nombre`, `longitud`, `descripcion`, `id_grado`, `id_tipo_escalada`, `id_sector`) VALUES
	(1, 'Irentxo', 30, 'Mantenida', 1, 1, 2),
	(2, 'Bosque de los Inurios\r\n', 18, 'Vía con 5 movimientos muy duros', 2, 1, 2),
	(3, 'Normal', 120, 'Larga y bonita vía con pasos de todo tipo. Reequipada con químicos en su totalidad', 3, 1, 3),
	(4, 'La Cabra de Judas\r\n', 400, 'A1 de artificial. Roca buena en general. Material: Fisureros, cordinos, estribos', 1, 4, 5),
	(5, 'María chimenea', 140, 'Último largo bastante lavado, técnica y de coco', 4, 1, 6),
	(6, 'Arista de Urrestei', 300, 'Grado asequible para utilizar tu chatarra', 4, 2, 7),
	(7, 'Oroimen\r\n', 45, 'Equipada por Carmen Urkiola, Ricardo Garaigordobil y Manu Marcos\r\n', 1, 1, 8),
	(8, 'Amaituezina', 50, 'Reunión común con la 7', 1, 1, 8),
	(9, 'Abiadura handiko trena KK\r\n', 30, 'Excelente roca\r\n', 2, 1, 8),
	(10, 'Ezinezkoa\r\n', 30, 'Buena roca caliza\r\n', 6, 1, 9),
	(11, 'Gaviotas', 1300, 'Escalar a partir de Septiembre por nidificación de aves\r\n', 4, 1, 10),
	(12, 'Directa de los Martínez\r\n', 160, 'Material necesario: 8 cintas exprés, juego completo de friends desde el 0,3 de los camalot hasta el n2 (nº3 y empotradores opcionales) casco imprescindible\r\n', 3, 2, 11),
	(13, 'Orbayu\r\n', 500, 'La vía clásica más dificil del mundo\r\n', 7, 3, 12),
	(14, 'Murciana 78\r\n', 600, 'Dominar el 6b+ a vista. Pasos difíciles: A1\r\n', 8, 1, 12);
/*!40000 ALTER TABLE `via` ENABLE KEYS */;


-- Volcando estructura para tabla eskalada.zona
CREATE TABLE IF NOT EXISTS `zona` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nombre_UNIQUE` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla eskalada.zona: ~4 rows (aproximadamente)
DELETE FROM `zona`;
/*!40000 ALTER TABLE `zona` DISABLE KEYS */;
INSERT INTO `zona` (`id`, `nombre`) VALUES
	(1, 'Atxarte'),
	(4, 'Cabo Ogoño\r\n'),
	(3, 'Naranjo de Bulnes\r\n'),
	(2, 'Untzillaitz Sur');
/*!40000 ALTER TABLE `zona` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
