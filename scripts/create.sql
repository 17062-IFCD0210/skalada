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
  `desc` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla eskalada.grado: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `grado` DISABLE KEYS */;
INSERT INTO `grado` (`id`, `nombre`, `desc`) VALUES
	(1, 'Ab', 'Artificial con buriles. El largo de artificial está equipado con buriles, rivets o chinchetas.'),
	(2, 'Ae', 'Artificial equipado. El largo está equipado con clavos, spits, parabolts o material abandonado (fisureros, plomos, etc.)'),
	(3, 'A0', 'Nos agarramos al seguro y nos superamos para poder progresar. No utilzamos estribos.'),
	(4, 'A1 o C1', 'Artificial fácil. Los emplazamientos suelen ser sencillos y sólidos. Si el escalador se cae el seguro anterior debiera de pararle sin problemas. Imaginémonos el mayor techo del mundo. Si está asegurado mediante parabolts, clavos, fisureros o sistemas de expansión (friends) a prueba de bombas, lo graduaremos con ésta dificultad. Nuestros riñones se quejaran por estar colgados en el vacio, pero las posibilidades de hacerse daño si nos caemos son minimas. El largo nos puede llevar una o dos horas el finalizarlo.'),
	(5, 'A2 o C2', 'Artificial moderado. Los emplazamientos suelen ser sólidos, pero pueden resultar delicados y arduos de colocar. Normalmente hay uno o dos emplazamientos que solo aguantan el peso de nuestro cuerpo entre otros que si aguantarian una buena caida. De 5 a 10 metros de caida potencial pero sin peligro. El largo no puede llevar entre una y tres horas el finalizarlo.'),
	(6, 'A3 o C3', 'Artificial duro. Necesitamos comprobar metodicamente los seguros mediante la utilización del probador. Normalmente se tratará de un largo compuesto de varios seguros débiles, cinco o seis aproximadamente, y los cuales solo aguantan el peso de nuestro cuerpo y no una caida. No obstante en el largo contaremos con buenos seguros que si que aguantarian una posible caida. Unos 20 metros de caida potencial pero sin peligro. El largo nos puede llevar de dos a tres horas el finalizarlo.'),
	(7, 'A4 o C4', 'Artificial serio. Muchos emplazamientos seguidos que solo aguantan el peso de nuestro cuerpo (de seis a ocho). De 20 a 30 metros de caida potencial con peligro de chocar contra repisas y salientes. Finalizar el largo nos puede llevar mas de tres horas'),
	(8, 'A5 o C5', 'Artificial extremo. Mas de diez emplazamientos seguidos que solo aguantan el peso de nuestro cuerpo y no una caida. Practicamente podriamos descoser todo el largo en caso de caida. Es preciso comprobar cada seguro con mucha precisión. Finalizar un largo nos puede llevar mas de cuatro horas.'),
	(9, 'A6', 'Artificial extremo. Igual que el de A5 pero con la posibilidad de que la reunión no aguante el impacto de una caida. El riesgo de caida mortal es real.'),
	(10, '6a', NULL),
	(11, '6b', NULL),
	(12, 'V', NULL),
	(13, 'V+', NULL),
	(14, '6b+', NULL),
	(15, '7b+', NULL),
	(16, '9a', NULL),
	(17, '7c+', NULL);
/*!40000 ALTER TABLE `grado` ENABLE KEYS */;


-- Volcando estructura para tabla eskalada.sector
CREATE TABLE IF NOT EXISTS `sector` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `id_zona` int(11) NOT NULL,
  PRIMARY KEY (`id`,`id_zona`),
  KEY `fk_sector_zona1_idx` (`id_zona`),
  CONSTRAINT `fk_sector_zona1` FOREIGN KEY (`id_zona`) REFERENCES `zona` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla eskalada.sector: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `sector` DISABLE KEYS */;
INSERT INTO `sector` (`id`, `nombre`, `id_zona`) VALUES
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
  `desc` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla eskalada.tipo_escalada: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `tipo_escalada` DISABLE KEYS */;
INSERT INTO `tipo_escalada` (`id`, `nombre`, `desc`) VALUES
	(1, 'Libre', 'En este tipo de escalada sólo se utilizan los pies y las manos para progresar, sin fijar ningún medio de seguridad para evitar accidentes. Se suelen usar pies de gato para una mayor fijación.'),
	(2, 'Clásica', 'Aquí el escalador va metiendo sus propios seguros en la pared para hacer una escalada más segura. Se pueden introducir los seguro en anclajes naturales (árboles, puentes de roca, puntas de roca, etc.) o en anclajes artificiales recuperables (fisureros, friends, clavos, nudos empotrados, etc.).'),
	(3, 'Alpina', 'Se trata de una variante de la escalada clásica en la que se emplean los mismos elementos de seguridad pero en la que se emplean tanto para asegurarse como para progresar en la ascensión.'),
	(4, 'Artificial', 'Es aquella escalada en la que la ascensión, debido a su longitud, precisa de varios días, debiendo de dormir y comer en la pared. Se puede realizar tanto en clásica como en artificial.'),
	(5, 'Dry-tooling', 'En esta escalada se asciende autoasegurado con una cuerda pero sin compañero.'),
	(6, 'Big Wall', 'Este tipo de escalada se caracteriza por que en la pared podemos encontrar anclajes fijos colocados estratégicamente para asegurar nuestros pasos. Podemos encontrar anclajes basados en sistemas mecánicos -de expansión- o químicos -resinas-. Por lo general, estas vías al equiparse, se limpian de maleza y de piedras sueltas o susceptibles de romperse, para ganar en la seguridad del escalador deportivo.'),
	(7, 'Solitaria', 'En esta escalada se asciende autoasegurado con una cuerda pero sin compañero.'),
	(8, 'Deportiva', 'Este tipo de escalada se caracteriza por que en la pared podemos encontrar anclajes fijos colocados estratégicamente para asegurar nuestros pasos. Podemos encontrar anclajes basados en sistemas mecánicos -de expansión- o químicos -resinas-. Por lo general, estas vías al equiparse, se limpian de maleza y de piedras sueltas o susceptibles de romperse, para ganar en la seguridad del escalador deportivo.'),
	(9, 'Bloque', 'Es una escalada de solo integral en la que el escalador nunca sube suficientemente lejos como para que una caída pueda suponerle una caida grave, estando ésta asegurada con una colchoneta (crash pad) que evite golpes o un compañero atento a la caída.'),
	(10, 'Psicobloc', 'Es como la escalada en bloque, pero cuando la pared es un acantilado y la caída se hace directamente en el agua. No se utiliza cuerda.'),
	(11, 'Urbana', 'Se realiza en cualquier estructura que se encuentre en la ciudad. Mucha afición en Holanda.');
/*!40000 ALTER TABLE `tipo_escalada` ENABLE KEYS */;


-- Volcando estructura para tabla eskalada.via
CREATE TABLE IF NOT EXISTS `via` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(200) NOT NULL,
  `longitud` int(11) NOT NULL DEFAULT '0' COMMENT 'Longitud de la via de escalada en metros.',
  `descripcion` text,
  `id_grado` int(11) NOT NULL,
  `id_tipo_escalada` int(11) NOT NULL,
  `id_sector` int(11) NOT NULL,
  PRIMARY KEY (`id`,`id_grado`,`id_tipo_escalada`,`id_sector`),
  KEY `fk_via_grado1_idx` (`id_grado`),
  KEY `fk_via_tipo_escalada1_idx` (`id_tipo_escalada`),
  KEY `fk_via_sector1_idx` (`id_sector`),
  CONSTRAINT `fk_via_grado1` FOREIGN KEY (`id_grado`) REFERENCES `grado` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_via_tipo_escalada1` FOREIGN KEY (`id_tipo_escalada`) REFERENCES `tipo_escalada` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_via_sector1` FOREIGN KEY (`id_sector`) REFERENCES `sector` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla eskalada.via: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `via` DISABLE KEYS */;
INSERT INTO `via` (`id`, `nombre`, `longitud`, `descripcion`, `id_grado`, `id_tipo_escalada`, `id_sector`) VALUES
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

-- Volcando datos para la tabla eskalada.zona: ~0 rows (aproximadamente)
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
