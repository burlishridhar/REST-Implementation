DROP DATABASE IF EXISTS lookup;
CREATE DATABASE IF NOT EXISTS lookup;
USE lookup;

DROP TABLE IF EXISTS `user`; 
CREATE TABLE `user` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `email` VARCHAR(200) NOT NULL,
  `phone` VARCHAR(13) DEFAULT NULL,
  `loginID` VARCHAR(45) NOT NULL,
  `passwd` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;

DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `userid` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`userid`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=INNODB DEFAULT CHARSET=UTF8;

DROP TABLE IF EXISTS `categoryitem`;
CREATE TABLE `categoryitem` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `categoryId` INT(11) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `userid` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`categoryId`) REFERENCES `category` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  FOREIGN KEY (`userid`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=INNODB DEFAULT CHARSET=UTF8;

DROP TABLE IF EXISTS `review`;
CREATE TABLE `review` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `categoryitemid` INT(11) NOT NULL,
  `rating` DOUBLE NOT NULL,
  `comment` VARCHAR(1000) DEFAULT NULL,
  `userid` INT(11) NOT NULL,
  `created_date` DATE NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`categoryitemid`) REFERENCES `categoryitem` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  FOREIGN KEY (`userid`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=INNODB DEFAULT CHARSET=UTF8;

INSERT INTO `user` (`id`,`name`,`email`,`phone`,`loginID`,`passwd`) VALUES (1,'Shridhar','shridharburli@tamu.edu','123456789','shridharburli@tamu.edu','shridhar');
INSERT INTO `user` (`id`,`name`,`email`,`phone`,`loginID`,`passwd`) VALUES (2,'Abhinay','abhinay@tamu.edu','123456789','abhinay@tamu.edu','abhinay');
INSERT INTO `user` (`id`,`name`,`email`,`phone`,`loginID`,`passwd`) VALUES (3,'Ashima','ashima.lodha02@tamu.edu','123456789','ashima.lodha02@tamu.edu','ashima');
INSERT INTO `user` (`id`,`name`,`email`,`phone`,`loginID`,`passwd`) VALUES (4,'Ruchir','ruchir_rege89@tamu.edu','123456789','ruchir_rege89@tamu.edu','ruchir');
INSERT INTO `user` (`id`,`name`,`email`,`phone`,`loginID`,`passwd`) VALUES (5,'Then','thensingar@tamu.edu','123456789','thensingar@tamu.edu','then');

INSERT INTO `category` (`id`,`name`,`userid`) VALUES (1,'University',1);
INSERT INTO `category` (`id`,`name`,`userid`) VALUES (2,'Interview',2);
INSERT INTO `category` (`id`,`name`,`userid`) VALUES (3,'Texas A&M Courses',3);
INSERT INTO `category` (`id`,`name`,`userid`) VALUES (4,'Mobile',4);
INSERT INTO `category` (`id`,`name`,`userid`) VALUES (5,'Laptop',5);

INSERT INTO `categoryitem` (`id`,`categoryId`,`name`,`userid`) VALUES (1,1,'Texas A&M University',1);
INSERT INTO `categoryitem` (`id`,`categoryId`,`name`,`userid`) VALUES (2,1,'Georgia Tech',2);
INSERT INTO `categoryitem` (`id`,`categoryId`,`name`,`userid`) VALUES (3,1,'University of California at Berkeley',3);
INSERT INTO `categoryitem` (`id`,`categoryId`,`name`,`userid`) VALUES (4,1,'University of Texas at Austin',4);
INSERT INTO `categoryitem` (`id`,`categoryId`,`name`,`userid`) VALUES (5,1,'Northeastern University',5);
INSERT INTO `categoryitem` (`id`,`categoryId`,`name`,`userid`) VALUES (6,1,'University of California at Los Angeles',1);
INSERT INTO `categoryitem` (`id`,`categoryId`,`name`,`userid`) VALUES (7,1,'Texas Tech University',2);
INSERT INTO `categoryitem` (`id`,`categoryId`,`name`,`userid`) VALUES (8,1,'SUNY Buffalo',3);
INSERT INTO `categoryitem` (`id`,`categoryId`,`name`,`userid`) VALUES (9,1,'Rochester Institute of Technology',4);
INSERT INTO `categoryitem` (`id`,`categoryId`,`name`,`userid`) VALUES (10,1,'University of Colorado at Boulder',5);
INSERT INTO `categoryitem` (`id`,`categoryId`,`name`,`userid`) VALUES (11,2,'Microsoft',1);
INSERT INTO `categoryitem` (`id`,`categoryId`,`name`,`userid`) VALUES (12,2,'Google',2);
INSERT INTO `categoryitem` (`id`,`categoryId`,`name`,`userid`) VALUES (13,2,'Amazon',3);
INSERT INTO `categoryitem` (`id`,`categoryId`,`name`,`userid`) VALUES (14,2,'Bloomberg LP',4);
INSERT INTO `categoryitem` (`id`,`categoryId`,`name`,`userid`) VALUES (15,2,'Ebay',5);
INSERT INTO `categoryitem` (`id`,`categoryId`,`name`,`userid`) VALUES (16,3,'ISYS 621',1);
INSERT INTO `categoryitem` (`id`,`categoryId`,`name`,`userid`) VALUES (17,3,'ISYS 622',2);
INSERT INTO `categoryitem` (`id`,`categoryId`,`name`,`userid`) VALUES (18,3,'ISYS 623',3);
INSERT INTO `categoryitem` (`id`,`categoryId`,`name`,`userid`) VALUES (19,3,'ISYS 624',4);
INSERT INTO `categoryitem` (`id`,`categoryId`,`name`,`userid`) VALUES (20,3,'ISYS 625',5);
INSERT INTO `categoryitem` (`id`,`categoryId`,`name`,`userid`) VALUES (21,4,'Samsung Note 4',1);
INSERT INTO `categoryitem` (`id`,`categoryId`,`name`,`userid`) VALUES (22,4,'Apple Iphone 6S',2);
INSERT INTO `categoryitem` (`id`,`categoryId`,`name`,`userid`) VALUES (23,4,'Google Nexus 6',3);
INSERT INTO `categoryitem` (`id`,`categoryId`,`name`,`userid`) VALUES (24,4,'Oneplus One',4);
INSERT INTO `categoryitem` (`id`,`categoryId`,`name`,`userid`) VALUES (25,4,'Moto X 2nd Gen',5);
INSERT INTO `categoryitem` (`id`,`categoryId`,`name`,`userid`) VALUES (26,5,'Asus Chromebook',1);
INSERT INTO `categoryitem` (`id`,`categoryId`,`name`,`userid`) VALUES (27,5,'Lenovo Z70',2);
INSERT INTO `categoryitem` (`id`,`categoryId`,`name`,`userid`) VALUES (28,5,'Thinkpad T510',3);
INSERT INTO `categoryitem` (`id`,`categoryId`,`name`,`userid`) VALUES (29,5,'Apple Macbook Pro 13',4);
INSERT INTO `categoryitem` (`id`,`categoryId`,`name`,`userid`) VALUES (30,5,'Apple Macbook Air',5);

INSERT INTO `review` (`id`,`categoryitemid`,`rating`,`comment`,`userid`,`created_date`) VALUES (1,1,5,'This is a test review. This University is amazing. Wafer gummi bears danish candy canes. Halvah jelly-o sweet roll apple pie sugar plum croissant marzipan cookie. Dragée muffin unerdwear.com chupa chups ice cream.',4,'2014-11-23');
INSERT INTO `review` (`id`,`categoryitemid`,`rating`,`comment`,`userid`,`created_date`) VALUES (2,2,5,'This is a test review. Chocolate cake wafer sesame snaps. Powder wafer sugar plum carrot cake pie soufflé cookie halvah. Donut cotton candy gummies caramels sesame snaps oat cake jelly.',4,'2014-11-23');
INSERT INTO `review` (`id`,`categoryitemid`,`rating`,`comment`,`userid`,`created_date`) VALUES (3,3,3.5,'This is a test review. This University is amazing. Toffee cotton candy marzipan jelly beans unerdwear.com pie dragée. Chocolate tart powder apple pie gummi bears candy cheesecake sweet.',2,'2014-11-23');
INSERT INTO `review` (`id`,`categoryitemid`,`rating`,`comment`,`userid`,`created_date`) VALUES (4,4,4,'This is a test review. Tootsie roll dessert candy sweet roll chocolate tart. Dragée powder toffee sesame snaps candy marzipan danish. Tiramisu lollipop dessert cake biscuit ice cream tiramisu jujubes.',3,'2014-11-23');
INSERT INTO `review` (`id`,`categoryitemid`,`rating`,`comment`,`userid`,`created_date`) VALUES (5,5,3,'This is a test review. Chocolate cake wafer sesame snaps. Powder wafer sugar plum carrot cake pie soufflé cookie halvah. Donut cotton candy gummies caramels sesame snaps oat cake jelly.',2,'2014-11-23');
INSERT INTO `review` (`id`,`categoryitemid`,`rating`,`comment`,`userid`,`created_date`) VALUES (6,6,5,'This is a test review. Gummi bears sweet gummi bears. Toffee cotton candy marzipan jelly beans unerdwear.com pie dragée. Chocolate tart powder apple pie gummi bears candy cheesecake sweet.',4,'2014-11-23');
INSERT INTO `review` (`id`,`categoryitemid`,`rating`,`comment`,`userid`,`created_date`) VALUES (7,7,4,'This is a test review. Chocolate cake wafer sesame snaps. Powder wafer sugar plum carrot cake pie soufflé cookie halvah. Donut cotton candy gummies caramels sesame snaps oat cake jelly.',3,'2014-11-23');
INSERT INTO `review` (`id`,`categoryitemid`,`rating`,`comment`,`userid`,`created_date`) VALUES (8,8,5,'This is a test review. Tootsie roll dessert candy sweet roll chocolate tart. Dragée powder toffee sesame snaps candy marzipan danish. Tiramisu lollipop dessert cake biscuit ice cream tiramisu jujubes.',4,'2014-11-23');
INSERT INTO `review` (`id`,`categoryitemid`,`rating`,`comment`,`userid`,`created_date`) VALUES (9,9,5,'This is a test review. Gummi bears sweet gummi bears. Toffee cotton candy marzipan jelly beans unerdwear.com pie dragée. Chocolate tart powder apple pie gummi bears candy cheesecake sweet.',4,'2014-11-23');
INSERT INTO `review` (`id`,`categoryitemid`,`rating`,`comment`,`userid`,`created_date`) VALUES (10,10,4.5,'This is a test review. Jelly beans lemon drops tart donut dessert. Croissant chupa chups chocolate bar marzipan halvah topping. Topping fruitcake gummi bears tart chocolate bonbon.',3,'2014-11-23');
INSERT INTO `review` (`id`,`categoryitemid`,`rating`,`comment`,`userid`,`created_date`) VALUES (11,11,4,'This is a test review. Tootsie roll dessert candy sweet roll chocolate tart. Dragée powder toffee sesame snaps candy marzipan danish. Tiramisu lollipop dessert cake biscuit ice cream tiramisu jujubes.',3,'2014-11-23');
INSERT INTO `review` (`id`,`categoryitemid`,`rating`,`comment`,`userid`,`created_date`) VALUES (12,12,5,'This is a test review. Chocolate cake wafer sesame snaps. Powder wafer sugar plum carrot cake pie soufflé cookie halvah. Donut cotton candy gummies caramels sesame snaps oat cake jelly.',4,'2014-11-23');
INSERT INTO `review` (`id`,`categoryitemid`,`rating`,`comment`,`userid`,`created_date`) VALUES (13,13,5,'This is a test review. Tootsie roll dessert candy sweet roll chocolate tart. Dragée powder toffee sesame snaps candy marzipan danish. Tiramisu lollipop dessert cake biscuit ice cream tiramisu jujubes.',5,'2014-11-23');
INSERT INTO `review` (`id`,`categoryitemid`,`rating`,`comment`,`userid`,`created_date`) VALUES (14,14,4,'This is a test review. Gummi bears sweet gummi bears. Toffee cotton candy marzipan jelly beans unerdwear.com pie dragée. Chocolate tart powder apple pie gummi bears candy cheesecake sweet.',3,'2014-11-23');
INSERT INTO `review` (`id`,`categoryitemid`,`rating`,`comment`,`userid`,`created_date`) VALUES (15,15,5,'This is a test review. Jelly beans lemon drops tart donut dessert. Croissant chupa chups chocolate bar marzipan halvah topping. Topping fruitcake gummi bears tart chocolate bonbon.',4,'2014-11-23');
INSERT INTO `review` (`id`,`categoryitemid`,`rating`,`comment`,`userid`,`created_date`) VALUES (16,16,4,'This is a test review. Chocolate cake wafer sesame snaps. Powder wafer sugar plum carrot cake pie soufflé cookie halvah. Donut cotton candy gummies caramels sesame snaps oat cake jelly.',3,'2014-11-23');
INSERT INTO `review` (`id`,`categoryitemid`,`rating`,`comment`,`userid`,`created_date`) VALUES (17,17,4,'This is a test review. Gummi bears sweet gummi bears. Toffee cotton candy marzipan jelly beans unerdwear.com pie dragée. Chocolate tart powder apple pie gummi bears candy cheesecake sweet.',3,'2014-11-23');
INSERT INTO `review` (`id`,`categoryitemid`,`rating`,`comment`,`userid`,`created_date`) VALUES (18,18,3.5,'This is a test review. Tootsie roll dessert candy sweet roll chocolate tart. Dragée powder toffee sesame snaps candy marzipan danish. Tiramisu lollipop dessert cake biscuit ice cream tiramisu jujubes.',1,'2014-11-23');
INSERT INTO `review` (`id`,`categoryitemid`,`rating`,`comment`,`userid`,`created_date`) VALUES (19,19,4.5,'This is a test review. Chocolate cake wafer sesame snaps. Powder wafer sugar plum carrot cake pie soufflé cookie halvah. Donut cotton candy gummies caramels sesame snaps oat cake jelly.',2,'2014-11-23');
INSERT INTO `review` (`id`,`categoryitemid`,`rating`,`comment`,`userid`,`created_date`) VALUES (20,20,4,'This is a test review. Gummi bears sweet gummi bears. Toffee cotton candy marzipan jelly beans unerdwear.com pie dragée. Chocolate tart powder apple pie gummi bears candy cheesecake sweet.',3,'2014-11-23');
INSERT INTO `review` (`id`,`categoryitemid`,`rating`,`comment`,`userid`,`created_date`) VALUES (21,21,4,'This is a test review. Chocolate cake wafer sesame snaps. Powder wafer sugar plum carrot cake pie soufflé cookie halvah. Donut cotton candy gummies caramels sesame snaps oat cake jelly.',2,'2014-11-23');
INSERT INTO `review` (`id`,`categoryitemid`,`rating`,`comment`,`userid`,`created_date`) VALUES (22,22,4,'This is a test review. Chocolate cake wafer sesame snaps. Powder wafer sugar plum carrot cake pie soufflé cookie halvah. Donut cotton candy gummies caramels sesame snaps oat cake jelly.',4,'2014-11-23');
INSERT INTO `review` (`id`,`categoryitemid`,`rating`,`comment`,`userid`,`created_date`) VALUES (23,23,5,'This is a test review. Gummi bears sweet gummi bears. Toffee cotton candy marzipan jelly beans unerdwear.com pie dragée. Chocolate tart powder apple pie gummi bears candy cheesecake sweet.',4,'2014-11-23');
INSERT INTO `review` (`id`,`categoryitemid`,`rating`,`comment`,`userid`,`created_date`) VALUES (24,24,3.5,'This is a test review. Jelly beans lemon drops tart donut dessert. Croissant chupa chups chocolate bar marzipan halvah topping. Topping fruitcake gummi bears tart chocolate bonbon.',2,'2014-11-23');
INSERT INTO `review` (`id`,`categoryitemid`,`rating`,`comment`,`userid`,`created_date`) VALUES (25,25,4,'This is a test review. Tootsie roll dessert candy sweet roll chocolate tart. Dragée powder toffee sesame snaps candy marzipan danish. Tiramisu lollipop dessert cake biscuit ice cream tiramisu jujubes.',3,'2014-11-23');
INSERT INTO `review` (`id`,`categoryitemid`,`rating`,`comment`,`userid`,`created_date`) VALUES (26,26,3,'This is a test review. Chocolate cake wafer sesame snaps. Powder wafer sugar plum carrot cake pie soufflé cookie halvah. Donut cotton candy gummies caramels sesame snaps oat cake jelly.',2,'2014-11-23');
INSERT INTO `review` (`id`,`categoryitemid`,`rating`,`comment`,`userid`,`created_date`) VALUES (27,27,3.5,'This is a test review. Gummi bears sweet gummi bears. Toffee cotton candy marzipan jelly beans unerdwear.com pie dragée. Chocolate tart powder apple pie gummi bears candy cheesecake sweet.',1,'2014-11-23');
INSERT INTO `review` (`id`,`categoryitemid`,`rating`,`comment`,`userid`,`created_date`) VALUES (28,28,5,'This is a test review. Tootsie roll dessert candy sweet roll chocolate tart. Dragée powder toffee sesame snaps candy marzipan danish. Tiramisu lollipop dessert cake biscuit ice cream tiramisu jujubes.',4,'2014-11-23');
INSERT INTO `review` (`id`,`categoryitemid`,`rating`,`comment`,`userid`,`created_date`) VALUES (29,29,4.5,'This is a test review. Tootsie roll dessert candy sweet roll chocolate tart. Dragée powder toffee sesame snaps candy marzipan danish. Tiramisu lollipop dessert cake biscuit ice cream tiramisu jujubes.',2,'2014-11-23');
INSERT INTO `review` (`id`,`categoryitemid`,`rating`,`comment`,`userid`,`created_date`) VALUES (30,30,4,'This is a test review. Chocolate cake wafer sesame snaps. Powder wafer sugar plum carrot cake pie soufflé cookie halvah. Donut cotton candy gummies caramels sesame snaps oat cake jelly.',3,'2014-11-23');

COMMIT;
