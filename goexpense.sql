-- phpMyAdmin SQL Dump
-- version 3.2.0.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Dec 13, 2013 at 01:03 PM
-- Server version: 5.1.37
-- PHP Version: 5.3.0

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `goexpense`
--

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE IF NOT EXISTS `category` (
  `day` int(11) NOT NULL,
  `month` int(11) NOT NULL,
  `year` int(11) NOT NULL,
  `categoryName` varchar(100) NOT NULL,
  `description` text NOT NULL,
  `price` int(11) NOT NULL,
  `paymentMode` varchar(100) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`day`, `month`, `year`, `categoryName`, `description`, `price`, `paymentMode`) VALUES
(1, 11, 1, '1', '1', 1, '1'),
(9, 12, 13, 'Entertainment', 'aa', 522, 'Check'),
(9, 12, 13, 'Home', 'rent', 100000, 'Cash'),
(9, 12, 13, 'Groceries', 'a', 20, 'Card '),
(6, 12, 13, 'Groceries', 'tyfgyg', 500, 'Card '),
(6, 12, 13, 'Car', 'nb', 45, 'Cash'),
(6, 12, 13, 'Gift', 'aa', 20, 'Cash'),
(8, 12, 13, 'Charity', 'ABC', 60, 'Cash'),
(6, 12, 13, 'Charity', 'jhg', 44, 'Cash'),
(7, 12, 13, 'Shopping', 'kj', 56, 'Cash'),
(9, 12, 13, 'Gift', 'aaa', 566, 'Check'),
(6, 12, 13, 'Food', 'hj', 50, 'Cash'),
(7, 12, 13, 'Food', 'hj', 10, 'Cash'),
(1, 12, 13, 'Car', 'llhgj', 12, 'Cash'),
(7, 12, 13, 'Car', 'jhg', 50, 'Cash'),
(7, 12, 13, 'Car', 'nb ', 50, 'Cash'),
(6, 12, 13, 'Car', 'yu', 69, 'Cash'),
(6, 12, 13, 'Car', 'f', 50, 'Card '),
(8, 12, 13, 'Car', 'fdg', 20, 'Cash'),
(6, 12, 13, 'Car', 'fd', 20, 'Cash'),
(6, 12, 13, 'Car', 'g', 0, 'Cash'),
(8, 12, 13, 'Shopping', 'vb', 500, 'Cash'),
(8, 12, 13, 'Shopping', 'jnhb', 20, 'Cash'),
(8, 12, 13, 'Charity', 'nm', 20, 'Cash');

-- --------------------------------------------------------

--
-- Table structure for table `onlycat`
--

CREATE TABLE IF NOT EXISTS `onlycat` (
  `name` varchar(255) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `onlycat`
--

INSERT INTO `onlycat` (`name`) VALUES
('Car'),
('Charity'),
('Shopping'),
('Food'),
('Entertainment'),
('Gift'),
('Groceries'),
('Hobbies'),
('Home'),
('Rent'),
('Medical'),
('Utilities'),
('Vacattion');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
