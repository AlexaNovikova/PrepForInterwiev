CREATE TABLE `films` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  `duration` int NOT NULL,
  PRIMARY KEY (`id`)
) ;

CREATE TABLE `holidays` (
  `id` int NOT NULL AUTO_INCREMENT,
  `dateOfHoliday` date NOT NULL,
  PRIMARY KEY (`id`)
) ;

CREATE TABLE `prices` (
  `id` int NOT NULL AUTO_INCREMENT,
  `filmId` int NOT NULL,
  `timeIntervalId` int NOT NULL,
  `price` decimal(10,0) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `AK_FilmTime` (`filmId`,`timeIntervalId`),
  KEY `filmTitle_idx` (`filmId`),
  KEY `timeInterval_idx` (`timeIntervalId`),
  CONSTRAINT `filmTitle` FOREIGN KEY (`filmId`) REFERENCES `films` (`id`),
  CONSTRAINT `timeInterval` FOREIGN KEY (`timeIntervalId`) REFERENCES `timeintervals` (`id`)
) ;

CREATE TABLE `sessions` (
  `id` int NOT NULL AUTO_INCREMENT,
  `filmId` int NOT NULL,
  `dateTimeStart` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `film_idx` (`filmId`),
  CONSTRAINT `film` FOREIGN KEY (`filmId`) REFERENCES `films` (`id`)
);

CREATE TABLE `tickets` (
  `id` int NOT NULL AUTO_INCREMENT,
  `sessionId` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `sessionId_idx` (`sessionId`),
  CONSTRAINT `sessionId` FOREIGN KEY (`sessionId`) REFERENCES `sessions` (`id`)
) ;

CREATE TABLE `timeintervals` (
  `id` int NOT NULL AUTO_INCREMENT,
  `begin` time NOT NULL,
  `end` time NOT NULL,
  `isHoliday` tinyint NOT NULL,
  PRIMARY KEY (`id`)
);




