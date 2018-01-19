DROP SCHEMA IF EXISTS `app_db` ;
CREATE SCHEMA `app_db` ;

GRANT ALL PRIVILEGES ON app_db.* TO 'admin'@'localhost' IDENTIFIED BY 'admin' WITH GRANT OPTION;

GRANT ALL PRIVILEGES ON app_db.* TO 'admin'@'%' IDENTIFIED BY 'admin' WITH GRANT OPTION;

use `app_db` ;

--
-- Table structure for table `appuser`
--
DROP TABLE IF EXISTS `appuser`;
CREATE TABLE `appuser` (
  `id` varchar(45) NOT NULL,
  `password` text NOT NULL,
  `name` varchar(100) NOT NULL,
  `emailid` varchar(100) DEFAULT NULL,
  `is_admin` int(2) NOT NULL,
  `created_time` datetime NOT NULL,
  `created_by` varchar(45) NOT NULL,
  `status` int(2) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_app_user1_idx` (`created_by`),
  CONSTRAINT `fk_app_user1` FOREIGN KEY (`created_by`) REFERENCES `appuser` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table data for `appuser`
--
INSERT INTO `app_db`.`appuser` (
`id`, 
`password`, 
`name`, 
`emailid`, 
`is_admin`, 
`created_time`,
 `created_by`,
 `status`) VALUES (
 'admin', 
 '$2a$10$Cg8yrs0BlbqUzlKFaUIYWuzytjxhfXFqmRBk.6I36DAO3Cp44wzSy',
 'Administrator', 
 'bhaskard@biarca.com', 
 '1', 
 now(), 
 'admin', 
 '1');