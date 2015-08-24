-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Aug 24, 2015 at 05:38 PM
-- Server version: 5.6.17
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `bas`
--

-- --------------------------------------------------------

--
-- Table structure for table `linija`
--

CREATE TABLE IF NOT EXISTS `linija` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `polaziste` int(11) NOT NULL,
  `odrediste` int(11) NOT NULL,
  `datum` date NOT NULL,
  `prevoznik_id` int(11) NOT NULL,
  `vreme` time NOT NULL,
  PRIMARY KEY (`id`),
  KEY `polaziste` (`polaziste`),
  KEY `odrediste` (`odrediste`),
  KEY `prevoznik_id` (`prevoznik_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=7 ;

--
-- Dumping data for table `linija`
--

INSERT INTO `linija` (`id`, `polaziste`, `odrediste`, `datum`, `prevoznik_id`, `vreme`) VALUES
(1, 37000, 18000, '2015-07-08', 1, '15:00:00'),
(3, 18000, 11000, '2015-07-09', 2, '20:00:00'),
(4, 37000, 21000, '2015-07-16', 1, '11:00:00'),
(5, 11000, 21000, '2015-07-12', 4, '14:00:00'),
(6, 35000, 11000, '2015-07-15', 4, '15:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `mesto`
--

CREATE TABLE IF NOT EXISTS `mesto` (
  `id` int(11) NOT NULL,
  `naziv` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `mesto`
--

INSERT INTO `mesto` (`id`, `naziv`) VALUES
(11000, 'Beograd'),
(18000, 'Nis'),
(21000, 'Novi Sad'),
(35000, 'Jagodina'),
(36000, 'Kraljevo'),
(37000, 'Krusevac');

-- --------------------------------------------------------

--
-- Table structure for table `prevoznici`
--

CREATE TABLE IF NOT EXISTS `prevoznici` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `naziv_prevoznika` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `sediste_prevoznika` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `sediste_prevoznika` (`sediste_prevoznika`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=5 ;

--
-- Dumping data for table `prevoznici`
--

INSERT INTO `prevoznici` (`id`, `naziv_prevoznika`, `sediste_prevoznika`) VALUES
(1, 'Jugoprevoz', 37000),
(2, 'Nis Ekspres', 11000),
(4, 'Lasta', 11000);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `linija`
--
ALTER TABLE `linija`
  ADD CONSTRAINT `linija_ibfk_1` FOREIGN KEY (`polaziste`) REFERENCES `mesto` (`id`),
  ADD CONSTRAINT `linija_ibfk_2` FOREIGN KEY (`odrediste`) REFERENCES `mesto` (`id`),
  ADD CONSTRAINT `linija_ibfk_3` FOREIGN KEY (`prevoznik_id`) REFERENCES `prevoznici` (`id`);

--
-- Constraints for table `prevoznici`
--
ALTER TABLE `prevoznici`
  ADD CONSTRAINT `prevoznici_ibfk_1` FOREIGN KEY (`sediste_prevoznika`) REFERENCES `mesto` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
