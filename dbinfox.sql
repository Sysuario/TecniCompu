-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 27-03-2016 a las 16:15:04
-- Versión del servidor: 5.6.17
-- Versión de PHP: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `dbinfox`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbclientes`
--

CREATE TABLE IF NOT EXISTS `tbclientes` (
  `idclientes` int(100) NOT NULL AUTO_INCREMENT,
  `cliente` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `direccion` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `telefono` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`idclientes`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=3 ;

--
-- Volcado de datos para la tabla `tbclientes`
--

INSERT INTO `tbclientes` (`idclientes`, `cliente`, `direccion`, `telefono`, `email`) VALUES
(1, 'Linus Torvals', 'Merida', '5555555', 'linus@linus.com'),
(2, 'Jose Perez', 'Timotes', '6666666', NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbos`
--

CREATE TABLE IF NOT EXISTS `tbos` (
  `idos` int(200) NOT NULL AUTO_INCREMENT,
  `fecha` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `equipo` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `defecto` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `servicio` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `tecnico` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `valor` decimal(10,2) NOT NULL,
  `idclientes` int(100) NOT NULL,
  PRIMARY KEY (`idos`),
  KEY `idcliente` (`idclientes`),
  KEY `idcliente_2` (`idclientes`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=2 ;

--
-- Volcado de datos para la tabla `tbos`
--

INSERT INTO `tbos` (`idos`, `fecha`, `equipo`, `defecto`, `servicio`, `tecnico`, `valor`, `idclientes`) VALUES
(1, '2016-02-25 04:47:58', 'laptop', 'pila', 'cambio', 'jose', '200.30', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbusuarios`
--

CREATE TABLE IF NOT EXISTS `tbusuarios` (
  `iduser` int(11) NOT NULL AUTO_INCREMENT,
  `usuario` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `telefono` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `login` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `pass` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `nivel` varchar(1) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`iduser`),
  UNIQUE KEY `login` (`login`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=14 ;

--
-- Volcado de datos para la tabla `tbusuarios`
--

INSERT INTO `tbusuarios` (`iduser`, `usuario`, `telefono`, `login`, `pass`, `nivel`) VALUES
(1, 'Johnny Florez', '04265785036', 'shoro', '123456', 'A'),
(13, 'john flo', NULL, 'joharflo', '654321', 'T');

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `tbos`
--
ALTER TABLE `tbos`
  ADD CONSTRAINT `tbos_ibfk_1` FOREIGN KEY (`idclientes`) REFERENCES `tbclientes` (`idclientes`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
