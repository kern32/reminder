-- --------------------------------------------------------
-- Хост:                         185.65.246.6
-- Версия сервера:               5.5.28-0ubuntu0.12.04.2 - (Ubuntu)
-- ОС Сервера:                   debian-linux-gnu
-- HeidiSQL Версия:              9.3.0.4984
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Дамп структуры базы данных reminder_db
CREATE DATABASE IF NOT EXISTS `reminder_db` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `reminder_db`;


-- Дамп структуры для таблица reminder_db.delivery
CREATE TABLE IF NOT EXISTS `delivery` (
  `id` int(50) NOT NULL,
  `messageID` int(50) NOT NULL,
  `email` varchar(20) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `skype` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_delivery_messages` (`messageID`),
  CONSTRAINT `FK_delivery_messages` FOREIGN KEY (`messageID`) REFERENCES `reminders` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Дамп данных таблицы reminder_db.delivery: ~0 rows (приблизительно)
/*!40000 ALTER TABLE `delivery` DISABLE KEYS */;
/*!40000 ALTER TABLE `delivery` ENABLE KEYS */;


-- Дамп структуры для таблица reminder_db.reminders
CREATE TABLE IF NOT EXISTS `reminders` (
  `id` int(50) NOT NULL AUTO_INCREMENT,
  `userID` int(50) NOT NULL,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `delivery` enum('Skype','Phone','Email') NOT NULL,
  `subject` varchar(50) NOT NULL,
  `message` varchar(150) NOT NULL,
  `receiver` varchar(50) NOT NULL,
  `status` enum('WAITING','DONE','CANCELED') NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_messages_users` (`userID`),
  CONSTRAINT `FK_messages_users` FOREIGN KEY (`userID`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- Дамп данных таблицы reminder_db.reminders: ~0 rows (приблизительно)
/*!40000 ALTER TABLE `reminders` DISABLE KEYS */;
/*!40000 ALTER TABLE `reminders` ENABLE KEYS */;


-- Дамп структуры для таблица reminder_db.roles
CREATE TABLE IF NOT EXISTS `roles` (
  `id` int(50) NOT NULL AUTO_INCREMENT,
  `role` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Дамп данных таблицы reminder_db.roles: ~2 rows (приблизительно)
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
REPLACE INTO `roles` (`id`, `role`) VALUES
	(1, 'ROLE_USER'),
	(2, 'ROLE_ADMIN');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;


-- Дамп структуры для таблица reminder_db.users
CREATE TABLE IF NOT EXISTS `users` (
  `id` int(50) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `email` varchar(20) NOT NULL,
  `skype` varchar(50) DEFAULT NULL,
  `phone` varchar(50) DEFAULT NULL,
  `password` varchar(100) NOT NULL,
  `enabled` int(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `EMAIL` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- Дамп данных таблицы reminder_db.users: ~1 rows (приблизительно)
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
REPLACE INTO `users` (`id`, `username`, `email`, `skype`, `phone`, `password`, `enabled`) VALUES
	(14, 'user', 'user@gmail.com', 'username', '+38 (097) 9999-9999', '$2a$10$JIJ2RLNnR1tp85AiYOkUW.EzE.dp9n0fdgVS6aRbWImwPdDMQr2.K', 1);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;


-- Дамп структуры для таблица reminder_db.user_roles
CREATE TABLE IF NOT EXISTS `user_roles` (
  `userID` int(50) NOT NULL,
  `roleID` int(50) NOT NULL,
  KEY `user` (`userID`),
  KEY `role` (`roleID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Дамп данных таблицы reminder_db.user_roles: ~1 rows (приблизительно)
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
REPLACE INTO `user_roles` (`userID`, `roleID`) VALUES
	(14, 1);
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
