/*
SQLyog Community v11.11 (64 bit)
MySQL - 5.6.21 : Database - prcasa
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`prcasa` /*!40100 DEFAULT CHARACTER SET latin1 */;

/*Table structure for table `album` */

DROP TABLE IF EXISTS `album`;

CREATE TABLE `album` (
  `album_id` int(10) NOT NULL AUTO_INCREMENT,
  `album_name` varchar(20) NOT NULL,
  `create_user_id` int(10) NOT NULL,
  `create_user` varchar(20) DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `update_user` varchar(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`album_id`),
  KEY `create_user_id_fk` (`create_user_id`),
  CONSTRAINT `fk_create_user_id` FOREIGN KEY (`create_user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `album` */

/*Table structure for table `album_share` */

DROP TABLE IF EXISTS `album_share`;

CREATE TABLE `album_share` (
  `album_id` int(10) NOT NULL,
  `user_id` int(20) NOT NULL,
  `create_user` varchar(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_user` varchar(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  KEY `fk_album_id` (`album_id`),
  KEY `fk_create_user_id` (`user_id`),
  CONSTRAINT `fk_album_id` FOREIGN KEY (`album_id`) REFERENCES `album` (`album_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `album_share` */

/*Table structure for table `photos` */

DROP TABLE IF EXISTS `photos`;

CREATE TABLE `photos` (
  `photo_id` int(10) NOT NULL AUTO_INCREMENT,
  `album_id` int(10) NOT NULL,
  `photo_path` varchar(30) NOT NULL,
  `photo_name` varchar(20) NOT NULL,
  `create_user` varchar(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_user` varchar(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`photo_id`),
  KEY `album_id_fk` (`album_id`),
  CONSTRAINT `album_id` FOREIGN KEY (`album_id`) REFERENCES `album` (`album_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `photos` */

/*Table structure for table `test` */

DROP TABLE IF EXISTS `test`;

CREATE TABLE `test` (
  `name` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `test` */

insert  into `test`(`name`) values ('sfsdfsfsf'),('Pradeep'),('Pradeep'),('xdfsdfsdf');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `user_id` int(20) NOT NULL,
  `email_id` varchar(20) DEFAULT NULL,
  `name` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `create_user` varchar(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_user` varchar(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `user` */

insert  into `user`(`user_id`,`email_id`,`name`,`password`,`create_user`,`create_time`,`update_user`,`update_time`) values (1,'kranthi@gmail.com','kranthi','kranthi',NULL,NULL,NULL,NULL),(2,'kumar1@gmail.com','kumar','kumar1',NULL,NULL,NULL,NULL),(3,'kumar@gmail.com','kumar','kumar',NULL,NULL,NULL,NULL),(4,'mani@gmail.com','mani','mani',NULL,NULL,NULL,NULL),(5,'naveen@gmail.com','naveen','naveen',NULL,NULL,NULL,NULL),(6,'pothan@gmail.com','pothan','pothan',NULL,NULL,NULL,NULL),(7,'pradeep@gmail.com','pradeep','pradeep',NULL,NULL,NULL,NULL),(8,'praveen@gmail.com','praveen','praveen',NULL,NULL,NULL,NULL),(9,'pridvi@gmail.com','pridvi','pridvi',NULL,NULL,NULL,NULL),(10,'samrat@gmail.com','samrat','samrat',NULL,NULL,NULL,NULL),(11,'shanthi@gmail.com','shanthi','shanthi',NULL,NULL,NULL,NULL),(12,'shiva@gmail.com','shiva','shiva',NULL,NULL,NULL,NULL),(13,'uday@gmail.com','uday','uday',NULL,NULL,NULL,NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
