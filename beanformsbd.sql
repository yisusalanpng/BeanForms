-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Dec 04, 2019 at 05:25 AM
-- Server version: 10.4.6-MariaDB-log
-- PHP Version: 7.3.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `beanformsbd`
--

-- --------------------------------------------------------

--
-- Table structure for table `Forms`
--

CREATE TABLE `Forms` (
  `Forms_ID` int(255) NOT NULL,
  `Titulo` varchar(20) DEFAULT NULL,
  `Expiracion` date DEFAULT NULL,
  `Privado` bit(1) DEFAULT NULL,
  `Codigo` varchar(20) DEFAULT NULL,
  `Creador_FK` varchar(20) DEFAULT NULL,
  `Likes` int(255) DEFAULT NULL,
  `Dislikes` int(255) DEFAULT NULL,
  `Habilitado` bit(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Forms`
--

INSERT INTO `Forms` (`Forms_ID`, `Titulo`, `Expiracion`, `Privado`, `Codigo`, `Creador_FK`, `Likes`, `Dislikes`, `Habilitado`) VALUES
(48, 'Test Disney', '2019-12-06', b'0', '0', 'yisusalan', 12, 5, b'1'),
(64, 'Examen Coco', '2020-01-04', b'1', '1J7TW', 'yisusalan', 0, 0, b'1'),
(68, 'Test', '2019-12-17', b'0', '0', 'yisusalan', 7, 2, b'1');

-- --------------------------------------------------------

--
-- Table structure for table `Opcion`
--

CREATE TABLE `Opcion` (
  `Opcion_ID` int(255) NOT NULL,
  `Opcion` varchar(255) DEFAULT NULL,
  `Pregunta_FK` int(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Opcion`
--

INSERT INTO `Opcion` (`Opcion_ID`, `Opcion`, `Pregunta_FK`) VALUES
(11, 'Tímido', 5),
(12, 'Tontín', 5),
(13, 'Cascarrabias', 5),
(14, 'Feliz', 5),
(15, 'Azul', 6),
(16, 'Gran Louie', 6),
(17, 'Bubba', 6),
(18, 'Baloo', 6),
(19, 'Sombrero loco', 7),
(20, 'Conejo café', 7),
(21, 'Conejo Blanco', 7),
(22, 'Gato sonriente', 7),
(23, 'Arlendale', 8),
(24, 'Ardendale', 8),
(25, 'Airdale', 8),
(26, 'Arendelle', 8),
(27, 'Una mantarraya', 9),
(28, 'Una medusa', 9),
(29, 'Un delfín', 9),
(30, 'Un tiburón', 9),
(31, 'Mapache', 10),
(32, 'Un buhó', 10),
(33, 'Un ciervo', 10),
(34, 'Una ardilla', 10),
(72, 'No sé', 33),
(73, 'Verdadero', 34),
(74, 'Falso', 34),
(81, 'yea', 38);

-- --------------------------------------------------------

--
-- Table structure for table `Preguntas`
--

CREATE TABLE `Preguntas` (
  `Preguntas_ID` int(11) NOT NULL,
  `Pregunta` varchar(255) DEFAULT NULL,
  `Forms_FK` int(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Preguntas`
--

INSERT INTO `Preguntas` (`Preguntas_ID`, `Pregunta`, `Forms_FK`) VALUES
(5, '¿Cuál de los siguientes NO es uno de los Siete Enanitos?', 48),
(6, '¿Cómo se llama el oso de El libro de la selva?', 48),
(7, '¿Cuál de los siguientes NO es un personaje de Alicia en el país de las maravillas?', 48),
(8, '¿Cómo se llama el reino de Frozen?', 48),
(9, '¿Qué animal tiene tatuado en la espalda la abuela de Vaiana?', 48),
(10, '¿Qué clase de animal es Meeko de Pocahontas?', 48),
(33, '¿Qué es un Java Bean?', 64),
(34, '¿Es JSF bueno?', 64),
(38, 'yea', 68);

-- --------------------------------------------------------

--
-- Table structure for table `Respuesta`
--

CREATE TABLE `Respuesta` (
  `Respuesta_ID` int(11) NOT NULL,
  `Opcion_FK` int(255) DEFAULT NULL,
  `Nickname_FK` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Respuesta`
--

INSERT INTO `Respuesta` (`Respuesta_ID`, `Opcion_FK`, `Nickname_FK`) VALUES
(1, 13, 'U7NOLBUNWHRS7B0731AU'),
(2, 17, 'U7NOLBUNWHRS7B0731AU'),
(3, 20, 'U7NOLBUNWHRS7B0731AU'),
(4, 24, 'U7NOLBUNWHRS7B0731AU'),
(5, 29, 'U7NOLBUNWHRS7B0731AU'),
(6, 33, 'U7NOLBUNWHRS7B0731AU'),
(7, 11, 'yisusalan'),
(8, 16, 'yisusalan'),
(9, 21, 'yisusalan'),
(10, 25, 'yisusalan'),
(11, 29, 'yisusalan'),
(12, 33, 'yisusalan'),
(13, 12, 'dougdimmadomme'),
(14, 17, 'dougdimmadomme'),
(15, 20, 'dougdimmadomme'),
(16, 25, 'dougdimmadomme'),
(17, 29, 'dougdimmadomme'),
(18, 33, 'dougdimmadomme'),
(20, 72, 'yisusalan'),
(21, 74, 'yisusalan'),
(22, 14, 'UA0ZDYM7MMJL7HR0N1Q6'),
(23, 18, 'UA0ZDYM7MMJL7HR0N1Q6'),
(24, 20, 'UA0ZDYM7MMJL7HR0N1Q6'),
(25, 26, 'UA0ZDYM7MMJL7HR0N1Q6'),
(26, 27, 'UA0ZDYM7MMJL7HR0N1Q6'),
(27, 31, 'UA0ZDYM7MMJL7HR0N1Q6'),
(28, 14, 'yisusalan'),
(29, 17, 'yisusalan'),
(30, 20, 'yisusalan'),
(31, 25, 'yisusalan'),
(32, 28, 'yisusalan'),
(33, 32, 'yisusalan'),
(34, 13, 'dougdimmadomme'),
(35, 15, 'dougdimmadomme'),
(36, 20, 'dougdimmadomme'),
(37, 25, 'dougdimmadomme'),
(38, 29, 'dougdimmadomme'),
(39, 33, 'dougdimmadomme'),
(40, 81, 'yisusalan');

-- --------------------------------------------------------

--
-- Table structure for table `Usuario`
--

CREATE TABLE `Usuario` (
  `Nickname` varchar(20) NOT NULL,
  `Nombre` varchar(50) DEFAULT NULL,
  `Apellido` varchar(50) DEFAULT NULL,
  `Password` blob DEFAULT NULL,
  `Administrador` bit(1) DEFAULT NULL,
  `Activo` bit(1) DEFAULT NULL,
  `Likes` int(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Usuario`
--

INSERT INTO `Usuario` (`Nickname`, `Nombre`, `Apellido`, `Password`, `Administrador`, `Activo`, `Likes`) VALUES
('dougdimmadomme', 'Doug', 'Dimmadomme', 0x98ee39d97b58f54e6c9ffd385b4b1a1b, b'0', b'1', NULL),
('juanpa', 'juanpa', 'reyes', 0x98ee39d97b58f54e6c9ffd385b4b1a1b, b'0', b'1', NULL),
('U7NOLBUNWHRS7B0731AU', 'Alan', 'Lomeli', 0xb064dd8bcc42947d7e060f1c52a575477ef937f39e8927e7ecad68d19ee978b6, b'0', b'0', NULL),
('UA0ZDYM7MMJL7HR0N1Q6', 'Pepe', 'Loera', 0xda7862a9b7de927dd9b0fe60507832a3ba1b394dfa607f8e27d4b876ffa1efcb, b'0', b'0', NULL),
('yisusalan', 'Alan', 'Lomeli', 0x98ee39d97b58f54e6c9ffd385b4b1a1b, b'1', b'1', NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `Forms`
--
ALTER TABLE `Forms`
  ADD PRIMARY KEY (`Forms_ID`),
  ADD KEY `Creador_FK` (`Creador_FK`);

--
-- Indexes for table `Opcion`
--
ALTER TABLE `Opcion`
  ADD PRIMARY KEY (`Opcion_ID`),
  ADD KEY `Pregunta_FK` (`Pregunta_FK`);

--
-- Indexes for table `Preguntas`
--
ALTER TABLE `Preguntas`
  ADD PRIMARY KEY (`Preguntas_ID`),
  ADD KEY `Forms_FK` (`Forms_FK`);

--
-- Indexes for table `Respuesta`
--
ALTER TABLE `Respuesta`
  ADD PRIMARY KEY (`Respuesta_ID`),
  ADD KEY `Opcion_FK` (`Opcion_FK`),
  ADD KEY `Nickname_FK` (`Nickname_FK`);

--
-- Indexes for table `Usuario`
--
ALTER TABLE `Usuario`
  ADD PRIMARY KEY (`Nickname`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `Forms`
--
ALTER TABLE `Forms`
  MODIFY `Forms_ID` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=72;

--
-- AUTO_INCREMENT for table `Opcion`
--
ALTER TABLE `Opcion`
  MODIFY `Opcion_ID` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=86;

--
-- AUTO_INCREMENT for table `Preguntas`
--
ALTER TABLE `Preguntas`
  MODIFY `Preguntas_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=43;

--
-- AUTO_INCREMENT for table `Respuesta`
--
ALTER TABLE `Respuesta`
  MODIFY `Respuesta_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=41;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `Forms`
--
ALTER TABLE `Forms`
  ADD CONSTRAINT `Forms_ibfk_1` FOREIGN KEY (`Creador_FK`) REFERENCES `Usuario` (`Nickname`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `Opcion`
--
ALTER TABLE `Opcion`
  ADD CONSTRAINT `Opcion_ibfk_1` FOREIGN KEY (`Pregunta_FK`) REFERENCES `Preguntas` (`Preguntas_ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `Preguntas`
--
ALTER TABLE `Preguntas`
  ADD CONSTRAINT `Preguntas_ibfk_1` FOREIGN KEY (`Forms_FK`) REFERENCES `Forms` (`Forms_ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `Respuesta`
--
ALTER TABLE `Respuesta`
  ADD CONSTRAINT `Respuesta_ibfk_1` FOREIGN KEY (`Opcion_FK`) REFERENCES `Opcion` (`Opcion_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `Respuesta_ibfk_2` FOREIGN KEY (`Nickname_FK`) REFERENCES `Usuario` (`Nickname`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
