-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versi�n del servidor:         5.6.12-log - MySQL Community Server (GPL)
-- SO del servidor:              Win64
-- HeidiSQL Versi�n:             9.1.0.4867
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Volcando estructura de base de datos para eskalada
DROP DATABASE IF EXISTS `eskalada`;
CREATE DATABASE IF NOT EXISTS `eskalada` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `eskalada`;


-- Volcando estructura para tabla eskalada.grado
DROP TABLE IF EXISTS `grado`;
CREATE TABLE IF NOT EXISTS `grado` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(10) NOT NULL COMMENT 'Grado de dificultad de la via de escalada, por ejemplo: ',
  `descripcion` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla eskalada.grado: ~40 rows (aproximadamente)
/*!40000 ALTER TABLE `grado` DISABLE KEYS */;
INSERT INTO `grado` (`id`, `nombre`, `descripcion`) VALUES
	(1, 'I', 'Trepada sencilla'),
	(2, 'II', 'Trepada f�cil'),
	(3, 'II+', 'Trepada'),
	(4, 'III', 'Trepada comprometida'),
	(5, 'III+', 'Trepada complicada'),
	(6, 'IV', 'Escalada sencilla'),
	(7, 'IV+', 'Escalada sencilla'),
	(8, 'V', 'Escalada f�cil'),
	(9, 'V+', 'Escalada f�cil'),
	(10, '6a', 'Escalada complicada'),
	(11, '6a+', 'Escalada complicada'),
	(12, '6b', 'Escalada dif�cil'),
	(13, '6b+', 'Escalada dif�cil'),
	(14, '6c', 'Escalada dif�cil'),
	(15, '6c+', 'Escalada dif�cil'),
	(16, '7a', 'Escalada muy dif�cil'),
	(17, '7a+', 'Escalada muy dif�cil'),
	(18, '7b', 'Escalada muy dif�cil'),
	(19, '7b+', 'Escalada muy dif�cil'),
	(20, '7c', 'Escalada muy dif�cil'),
	(21, '7c+', 'Escalada muy dif�cil'),
	(22, '8a', 'Escalada extrema'),
	(23, '8a+', 'Escalada extrema'),
	(24, '8b', 'Escalada extrema'),
	(25, '8b+', 'Escalada extrema'),
	(26, '8c', 'Escalada extrema'),
	(27, '8c+', 'Escalada extrema'),
	(28, '9a', 'Escalada extrema'),
	(29, '9a+', 'Escalada extrema'),
	(30, '9b', 'Escalada extrema'),
	(31, '9b+', 'Escalada extrema'),
	(32, 'Ab', 'Artificial con buriles. El largo de artificial est� equipado con buriles, rivets o chinchetas.'),
	(33, 'Ae', 'Artificial equipado. El largo est� equipado con clavos, spits, parabolts o material abandonado (fisureros, plomos, etc.)'),
	(34, 'A0', 'Nos agarramos al seguro y nos superamos para poder progresar. No utilzamos estribos.'),
	(35, 'A1 o C1', 'Artificial f�cil. Los emplazamientos suelen ser sencillos y s�lidos. Si el escalador se cae el seguro anterior debiera de pararle sin problemas. Imagin�monos el mayor techo del mundo. Si est� asegurado mediante parabolts, clavos, fisureros o sistemas de expansi�n (friends) a prueba de bombas, lo graduaremos con �sta dificultad. Nuestros ri�ones se quejaran por estar colgados en el vacio, pero las posibilidades de hacerse da�o si nos caemos son minimas. El largo nos puede llevar una o dos horas el finalizarlo.'),
	(36, 'A2 o C2', 'Artificial moderado. Los emplazamientos suelen ser s�lidos, pero pueden resultar delicados y arduos de colocar. Normalmente hay uno o dos emplazamientos que solo aguantan el peso de nuestro cuerpo entre otros que si aguantarian una buena caida. De 5 a 10 metros de caida potencial pero sin peligro. El largo no puede llevar entre una y tres horas el finalizarlo.'),
	(37, 'A3 o C3', 'Artificial duro. Necesitamos comprobar metodicamente los seguros mediante la utilizaci�n del probador. Normalmente se tratar� de un largo compuesto de varios seguros d�biles, cinco o seis aproximadamente, y los cuales solo aguantan el peso de nuestro cuerpo y no una caida. No obstante en el largo contaremos con buenos seguros que si que aguantarian una posible caida. Unos 20 metros de caida potencial pero sin peligro. El largo nos puede llevar de dos a tres horas el finalizarlo.'),
	(38, 'A4 o C4', 'Artificial serio. Muchos emplazamientos seguidos que solo aguantan el peso de nuestro cuerpo (de seis a ocho). De 20 a 30 metros de caida potencial con peligro de chocar contra repisas y salientes. Finalizar el largo nos puede llevar mas de tres horas'),
	(39, 'A5 o C5', 'Artificial extremo. Mas de diez emplazamientos seguidos que solo aguantan el peso de nuestro cuerpo y no una caida. Practicamente podriamos descoser todo el largo en caso de caida. Es preciso comprobar cada seguro con mucha precisi�n. Finalizar un largo nos puede llevar mas de cuatro horas.'),
	(40, 'A6', 'Artificial extremo. Igual que el de A5 pero con la posibilidad de que la reuni�n no aguante el impacto de una caida. El riesgo de caida mortal es real.');
/*!40000 ALTER TABLE `grado` ENABLE KEYS */;


-- Volcando estructura para tabla eskalada.sector
DROP TABLE IF EXISTS `sector`;
CREATE TABLE IF NOT EXISTS `sector` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `id_zona` int(11) NOT NULL,
  PRIMARY KEY (`id`,`id_zona`),
  KEY `fk_sector_zona1_idx` (`id_zona`),
  CONSTRAINT `fk_sector_zona1` FOREIGN KEY (`id_zona`) REFERENCES `zona` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla eskalada.sector: ~8 rows (aproximadamente)
/*!40000 ALTER TABLE `sector` DISABLE KEYS */;
INSERT INTO `sector` (`id`, `nombre`, `id_zona`) VALUES
	(1, 'Primer espol�n', 1),
	(2, 'Tercer espol�n', 1),
	(3, 'Alluitz', 1),
	(4, 'Labargorri', 1),
	(5, 'Urrestei', 1),
	(6, 'Elosuko Harrobia', 2),
	(7, 'Lauretazpe', 2),
	(8, 'Ogo�o', 3),
	(9, 'Cara Sur', 4),
	(10, 'Cara Oeste', 4);
/*!40000 ALTER TABLE `sector` ENABLE KEYS */;


-- Volcando estructura para tabla eskalada.tipo_escalada
DROP TABLE IF EXISTS `tipo_escalada`;
CREATE TABLE IF NOT EXISTS `tipo_escalada` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `descripcion` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla eskalada.tipo_escalada: ~12 rows (aproximadamente)
/*!40000 ALTER TABLE `tipo_escalada` DISABLE KEYS */;
INSERT INTO `tipo_escalada` (`id`, `nombre`, `descripcion`) VALUES
	(1, 'Libre, solo integral o natural', 'En este tipo de escalada s�lo se utilizan los pies y las manos para progresar, sin fijar ning�n medio de seguridad para evitar accidentes. Se suelen usar pies de gato para una mayor fijaci�n.'),
	(2, 'Cl�sica', 'Aqu� el escalador va metiendo sus propios seguros en la pared para hacer una escalada m�s segura. Se pueden introducir los seguro en anclajes naturales (�rboles, puentes de roca, puntas de roca, etc.) o en anclajes artificiales recuperables (fisureros, friends, clavos, nudos empotrados, etc.).'),
	(3, 'Alpina', 'Cuando la escalada cl�sica se realiza en una monta�a de gran altura, conllevando riesgos de nevadas, avalanchas, falta de ox�geno, etc, y con el fin de alcanzar la cima.'),
	(4, 'Artificial', 'Se trata de una variante de la escalada cl�sica en la que se emplean los mismos elementos de seguridad pero en la que se emplean tanto para asegurarse como para progresar en la ascensi�n.'),
	(5, 'Dry-tooling', 'Se trata de una variedad de escalada alpina en la que, mediante los piolets y los crampones, se asciende una pared de roca sin nada de nieve.'),
	(6, 'Big wall, grandes paredes o tapias', 'Es aquella escalada en la que la ascensi�n, debido a su longitud, precisa de varios d�as, debiendo de dormir y comer en la pared. Se puede realizar tanto en cl�sica como en artificial.'),
	(7, 'En solitario', 'En esta escalada se asciende autoasegurado con una cuerda pero sin compa�ero.'),
	(8, 'Deportiva', 'Este tipo de escalada se caracteriza por que en la pared podemos encontrar anclajes fijos colocados estrat�gicamente para asegurar nuestros pasos. Podemos encontrar anclajes basados en sistemas mec�nicos -de expansi�n- o qu�micos -resinas-. Por lo general, estas v�as al equiparse, se limpian de maleza y de piedras sueltas o susceptibles de romperse, para ganar en la seguridad del escalador deportivo.'),
	(9, 'Bloque o b�lder', 'Es una escalada de solo integral en la que el escalador nunca sube suficientemente lejos como para que una ca�da pueda suponerle una caida grave, estando �sta asegurada con una colchoneta (crash pad) que evite golpes o un compa�ero atento a la ca�da.'),
	(10, 'Psicobloc', 'Es como la escalada en bloque, pero cuando la pared es un acantilado y la ca�da se hace directamente en el agua. No se utiliza cuerda.'),
	(11, 'Urbana', 'Se realiza en cualquier estructura que se encuentre en la ciudad. Mucha afici�n en Holanda.');
/*!40000 ALTER TABLE `tipo_escalada` ENABLE KEYS */;


-- Volcando estructura para tabla eskalada.via
DROP TABLE IF EXISTS `via`;
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
/*!40000 ALTER TABLE `via` DISABLE KEYS */;
INSERT INTO `via` (`id`, `nombre`, `longitud`, `descripcion`, `id_grado`, `id_tipo_escalada`, `id_sector`) VALUES
	(1, 'Irentxo', 30, 'Mantenida', 10, 8, 1),
	(2, 'Bosque de los Inurios\r\n', 18, 'V�a con 5 movimientos muy duros', 12, 8, 1),
	(3, 'Normal', 120, 'Larga y bonita v�a con pasos de todo tipo. Reequipada con qu�micos en su totalidad', 8, 8, 2),
	(4, 'La Cabra de Judas\r\n', 400, 'A1 de artificial. Roca buena en general. Material: Fisureros, cordinos, estribos', 11, 4, 3),
	(5, 'Mar�a chimenea', 140, '�ltimo largo bastante lavado, t�cnica y de coco', 9, 8, 4),
	(6, 'Arista de Urrestei', 300, 'Grado asequible para utilizar tu chatarra', 9, 2, 5),
	(7, 'Oroimen\r\n', 45, 'Equipada por Carmen Urkiola, Ricardo Garaigordobil y Manu Marcos\r\n', 10, 8, 6),
	(8, 'Amaituezina', 50, 'Reuni�n com�n con la 7', 10, 8, 6),
	(9, 'Abiadura handiko trena KK\r\n', 30, 'Excelente roca\r\n', 13, 8, 6),
	(10, 'Ezinezkoa\r\n', 30, 'Buena roca caliza\r\n', 19, 8, 7),
	(11, 'Gaviotas', 1300, 'Escalar a partir de Septiembre por nidificaci�n de aves\r\n', 9, 8, 8),
	(12, 'Directa de los Mart�nez\r\n', 160, 'Material necesario: 8 cintas expr�s, juego completo de friends desde el 0,3 de los camalot hasta el n2 (n�3 y empotradores opcionales) casco imprescindible\r\n', 8, 2, 9),
	(13, 'Orbayu\r\n', 500, 'La v�a cl�sica m�s dificil del mundo\r\n', 28, 3, 10),
	(14, 'Murciana 78\r\n', 600, 'Dominar el 6b+ a vista. Pasos dif�ciles: A1\r\n', 21, 8, 10);
/*!40000 ALTER TABLE `via` ENABLE KEYS */;


-- Volcando estructura para tabla eskalada.zona
DROP TABLE IF EXISTS `zona`;
CREATE TABLE IF NOT EXISTS `zona` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nombre_UNIQUE` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla eskalada.zona: ~5 rows (aproximadamente)
/*!40000 ALTER TABLE `zona` DISABLE KEYS */;
INSERT INTO `zona` (`id`, `nombre`) VALUES
	(1, 'Atxarte'),
	(3, 'Cabo Ogo�o'),
	(4, 'Naranjo de Bulnes'),
	(5, 'sdf'),
	(2, 'Untzillaitz Sur');
/*!40000 ALTER TABLE `zona` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
