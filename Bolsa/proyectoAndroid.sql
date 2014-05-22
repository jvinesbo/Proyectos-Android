-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         5.5.24-log - MySQL Community Server (GPL)
-- SO del servidor:              Win64
-- HeidiSQL Versión:             8.3.0.4694
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Volcando estructura de base de datos para proyectoandroid
CREATE DATABASE IF NOT EXISTS `proyectoandroid` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `proyectoandroid`;


-- Volcando estructura para tabla proyectoandroid.cartera_inversiones_particular
CREATE TABLE IF NOT EXISTS `cartera_inversiones_particular` (
  `nombreEmpresa` varchar(100) NOT NULL DEFAULT '0',
  `numeroAcciones` int(11) NOT NULL DEFAULT '0',
  `precioCompra` double NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla proyectoandroid.cartera_inversiones_particular: ~4 rows (aproximadamente)
DELETE FROM `cartera_inversiones_particular`;
/*!40000 ALTER TABLE `cartera_inversiones_particular` DISABLE KEYS */;
INSERT INTO `cartera_inversiones_particular` (`nombreEmpresa`, `numeroAcciones`, `precioCompra`) VALUES
	('ABERTIS SE.A', 1010, 16.79),
	('ACS', 123, 26.34),
	('AMADEUS', 123, 29.7),
	('ARCELORMIT.', 1200, 12.38),
	('BBVA', 123, 8.961);
/*!40000 ALTER TABLE `cartera_inversiones_particular` ENABLE KEYS */;


-- Volcando estructura para tabla proyectoandroid.ibex
CREATE TABLE IF NOT EXISTS `ibex` (
  `id` int(11) NOT NULL,
  `nombreEmpresa` varchar(50) NOT NULL,
  `ultimo` double NOT NULL,
  `diferencia` double NOT NULL,
  `maximo` double NOT NULL,
  `minimo` double NOT NULL,
  `volumen` double NOT NULL,
  `efectivo` double NOT NULL,
  `fecha` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla proyectoandroid.ibex: ~35 rows (aproximadamente)
DELETE FROM `ibex`;
/*!40000 ALTER TABLE `ibex` DISABLE KEYS */;
INSERT INTO `ibex` (`id`, `nombreEmpresa`, `ultimo`, `diferencia`, `maximo`, `minimo`, `volumen`, `efectivo`, `fecha`) VALUES
	(1, 'ABERTIS SE.A', 16.79, 1.33, 16.84, 16.53, 1643991, 27548.41, '06/02/2014'),
	(2, 'ACCIONA', 47.37, 2.91, 47.37, 46.015, 532756, 25053.05, '06/02/2014'),
	(3, 'ACS', 26.34, 3.07, 26.44, 25.595, 1547688, 40445.53, '06/02/2014'),
	(4, 'AMADEUS', 29.7, 0.85, 29.8, 29.535, 5540016, 164641.27, '06/02/2014'),
	(5, 'ARCELORMIT.', 12.38, 1.06, 12.42, 12.17, 1070649, 13188.46, '06/02/2014'),
	(6, 'BA.POPULAR', 5.307, 5.97, 5.31, 5.014, 38593694, 200724.3, '06/02/2014'),
	(7, 'BA.SABADELL', 2.242, 2.84, 2.245, 2.175, 56148520, 124009.11, '06/02/2014'),
	(8, 'BA.SANTANDER', 6.471, 2.1, 6.48, 6.338, 111134206, 713197.22, '06/02/2014'),
	(9, 'BANKIA', 1.369, 2.93, 1.369, 1.333, 76786230, 104051.57, '06/02/2014'),
	(10, 'BANKINTER', 5.935, 3.89, 5.95, 5.713, 9849572, 57549.35, '06/02/2014'),
	(11, 'BBVA', 8.961, 2.06, 8.979, 8.752, 46647343, 415313.43, '06/02/2014'),
	(12, 'BME', 29.795, 1.86, 29.795, 29.195, 268931, 7977.75, '06/02/2014'),
	(13, 'CAIXABANK', 4.8, 3.27, 4.81, 4.648, 23141865, 109280.88, '06/02/2014'),
	(14, 'DIA', 5.979, 1.22, 5.98, 5.863, 5397710, 32036.31, '06/02/2014'),
	(15, 'EBRO FOODS', 16, 0.06, 16.095, 15.915, 1149588, 18411.91, '06/02/2014'),
	(16, 'ENAGAS', 20.46, 1.61, 20.51, 19.93, 2165382, 43863.93, '06/02/2014'),
	(17, 'FCC', 18.98, -2.01, 19.495, 18.875, 1495089, 28473.52, '06/02/2014'),
	(18, 'FERROVIAL', 14.225, 1.53, 14.285, 14.025, 1381768, 19623.74, '06/02/2014'),
	(19, 'GAMESA', 8.08, 3.07, 8.1, 7.856, 2340470, 18763.91, '06/02/2014'),
	(20, 'GAS NATURAL', 18.1, 1.26, 18.15, 17.88, 1357075, 24511.67, '06/02/2014'),
	(21, 'GRIFOLS CL.A', 37.2, 3.23, 37.39, 36.18, 1079991, 39955.59, '06/02/2014'),
	(22, 'IAG', 5.07, 2.14, 5.14, 4.99, 4649857, 23566.83, '06/02/2014'),
	(23, 'IBERDROLA', 4.525, 1.14, 4.543, 4.455, 29895338, 134939.59, '06/02/2014'),
	(24, 'INDITEX', 109.65, 1.53, 111.45, 108.5, 1676875, 183747.01, '06/02/2014'),
	(25, 'INDRA A', 13.015, 1.05, 13.06, 12.795, 852213, 11046.35, '06/02/2014'),
	(26, 'JAZZTEL', 9.151, 2.05, 9.17, 8.977, 1370205, 12479.04, '06/02/2014'),
	(27, 'MAPFRE', 3.089, 1.35, 3.105, 3.04, 8695693, 26808.3, '06/02/2014'),
	(28, 'MEDIASET', 8.962, 3.52, 9.029, 8.66, 1829598, 16273.71, '06/02/2014'),
	(29, 'OHL', 31.43, 1.5, 31.55, 30.955, 438359, 13737.9, '06/02/2014'),
	(30, 'R.E.C.', 53, 1.05, 53.17, 52.48, 564719, 29906.93, '06/02/2014'),
	(31, 'REPSOL', 17.425, 1.63, 17.5, 17.13, 8676298, 150575.12, '06/02/2014'),
	(32, 'SACYR', 3.698, 2.41, 3.719, 3.581, 8903596, 32703.7, '06/02/2014'),
	(33, 'TEC.REUNIDAS', 38.815, 0.18, 39.15, 38.595, 267553, 10405.34, '06/02/2014'),
	(34, 'TELEFONICA', 11.29, 1.67, 11.32, 11.105, 24831663, 279602.65, '06/02/2014'),
	(35, 'VISCOFAN', 37.72, -1.76, 38.54, 37.175, 610495, 23016.15, '06/02/2014');
/*!40000 ALTER TABLE `ibex` ENABLE KEYS */;


-- Volcando estructura para tabla proyectoandroid.usuarios
CREATE TABLE IF NOT EXISTS `usuarios` (
  `id` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `primerApellido` varchar(50) NOT NULL,
  `segundoApellido` varchar(50) NOT NULL,
  `fechaNacimiento` date NOT NULL,
  `dni` varchar(9) NOT NULL,
  `telefono` varchar(12) NOT NULL,
  `email` varchar(75) NOT NULL,
  `tipoVia` varchar(50) NOT NULL,
  `nombreVia` varchar(50) NOT NULL,
  `numeroVia` varchar(50) NOT NULL,
  `poblacion` varchar(50) NOT NULL,
  `provincia` varchar(50) NOT NULL,
  `pais` varchar(50) NOT NULL,
  `nombreLogin` varchar(50) NOT NULL,
  `contrasenya` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nombreLogin_2` (`nombreLogin`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla proyectoandroid.usuarios: ~3 rows (aproximadamente)
DELETE FROM `usuarios`;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
