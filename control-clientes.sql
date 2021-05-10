-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         8.0.24 - MySQL Community Server - GPL
-- SO del servidor:              Win64
-- HeidiSQL Versión:             11.2.0.6213
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Volcando estructura de base de datos para control-clientes
DROP DATABASE IF EXISTS `control-clientes`;
CREATE DATABASE IF NOT EXISTS `control-clientes` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `control-clientes`;

-- Volcando estructura para tabla control-clientes.cliente
DROP TABLE IF EXISTS `cliente`;
CREATE TABLE IF NOT EXISTS `cliente` (
  `id_cliente` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `apellido` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `telefono` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `saldo` double NOT NULL,
  PRIMARY KEY (`id_cliente`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin;

-- Volcando datos para la tabla control-clientes.cliente: ~3 rows (aproximadamente)
DELETE FROM `cliente`;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` (`id_cliente`, `nombre`, `apellido`, `email`, `telefono`, `saldo`) VALUES
	(3, 'Carlos', 'Lara', 'cLara@mail.com', '996541200', 620),
	(4, 'Ana 4', 'Lara', 'ana@mail.com', '9654158', 540),
	(5, 'Juan 5', 'Manga', 'jumang@mail.com', '965847123', 550),
	(9, 'Ana', 'Pérez', 'anap@mail.com', '654127845', 520),
	(10, 'Ana María', 'Del Pilar', 'anama@mail.com', '96512487', 452);
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
