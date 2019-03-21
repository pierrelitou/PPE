-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  jeu. 21 mars 2019 à 11:04
-- Version du serveur :  5.7.19
-- Version de PHP :  5.6.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `findyourspot`
--

-- --------------------------------------------------------

--
-- Structure de la table `activity`
--

DROP TABLE IF EXISTS `activity`;
CREATE TABLE IF NOT EXISTS `activity` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nameactivity` varchar(255) NOT NULL,
  `location` varchar(255) NOT NULL,
  `description` text NOT NULL,
  `dateevent` datetime NOT NULL,
  `proposeby` varchar(255) NOT NULL,
  `localproposer` tinyint(1) DEFAULT NULL,
  `lienimg` text NOT NULL,
  `EVENT` tinyint(1) NOT NULL,
  `Lat` float NOT NULL,
  `Lng` float NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `activity`
--

INSERT INTO `activity` (`id`, `nameactivity`, `location`, `description`, `dateevent`, `proposeby`, `localproposer`, `lienimg`, `EVENT`, `Lat`, `Lng`) VALUES
(1, 'Degustation de Vins', 'Les caves de la Villette, Paris XIX', 'Degustation et decouverte de produits du terroir', '2019-03-05 14:00:00', 'Andre', NULL, 'capture.png', 0, 48.888, 2.39216),
(2, 'Randonnee', 'Catacombes de Paris, Paris XIV', 'Une decouverte des catacombes de Paris qui vous fera peur', '2019-03-07 00:00:00', 'Marc', NULL, 'capture.png\r\n', 0, 48.834, 2.33218),
(3, 'Bowling', 'Bowling de Paris Front de Seine, Paris XV', 'Venez vous eclater avec moi au bowling', '2019-03-07 18:00:00', 'Pierre', NULL, '', 1, 48.8495, 2.28444);

-- --------------------------------------------------------

--
-- Structure de la table `preference`
--

DROP TABLE IF EXISTS `preference`;
CREATE TABLE IF NOT EXISTS `preference` (
  `idpreference` int(11) NOT NULL AUTO_INCREMENT,
  `namep` varchar(30) NOT NULL,
  PRIMARY KEY (`idpreference`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `preference`
--

INSERT INTO `preference` (`idpreference`, `namep`) VALUES
(1, 'Sport'),
(2, 'Voyages'),
(3, 'Gastronomie');

-- --------------------------------------------------------

--
-- Structure de la table `rate`
--

DROP TABLE IF EXISTS `rate`;
CREATE TABLE IF NOT EXISTS `rate` (
  `idactivity` int(11) NOT NULL,
  `mailuser` varchar(255) NOT NULL,
  `note` float DEFAULT NULL,
  PRIMARY KEY (`idactivity`,`mailuser`),
  KEY `mailuser` (`mailuser`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `typeactivity`
--

DROP TABLE IF EXISTS `typeactivity`;
CREATE TABLE IF NOT EXISTS `typeactivity` (
  `idactivity` int(11) NOT NULL,
  `idpref` varchar(30) NOT NULL,
  PRIMARY KEY (`idactivity`,`idpref`),
  KEY `idpref` (`idpref`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `userpreference`
--

DROP TABLE IF EXISTS `userpreference`;
CREATE TABLE IF NOT EXISTS `userpreference` (
  `mailuser` varchar(255) NOT NULL,
  `idpref` varchar(30) NOT NULL,
  `importance` float DEFAULT NULL,
  PRIMARY KEY (`mailuser`,`idpref`),
  KEY `idpref` (`idpref`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `userpreference`
--

INSERT INTO `userpreference` (`mailuser`, `idpref`, `importance`) VALUES
('test@test.fr', '1', 1);

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `pseudo` varchar(30) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `firstname` varchar(30) DEFAULT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  `dateofbirth` date DEFAULT NULL,
  `sex` char(1) DEFAULT NULL,
  `userloc` tinyint(1) DEFAULT NULL,
  `photoprofil` text,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `users`
--

INSERT INTO `users` (`id`, `username`, `pseudo`, `password`, `firstname`, `lastname`, `dateofbirth`, `sex`, `userloc`, `photoprofil`) VALUES
(1, 'test@test.fr', 'dada78', '123456', 'Daniel', 'Claro Carvalho', '1997-06-09', 'M', 0, 'daniel'),
(2, 'b@gmail.com', NULL, 'b', NULL, NULL, NULL, NULL, NULL, NULL);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
