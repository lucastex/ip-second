DROP TABLE IF EXISTS `link`;
CREATE TABLE `link` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `path` varchar(255) NOT NULL DEFAULT '',
  `name` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `link` (`id`, `path`, `name`)
VALUES
  (1,'/link/overview','Overview'),
  (2,'/link/reports','Reports'),
  (3,'/link/analytics','Analytics'),
  (4,'/link/export','Export data'),
  (5,'/link/customers','Customers'),
  (6,'/link/orders','Orders'),
  (7,'/link/products','Products'),
  (8,'/link/deals','Deals'),
  (9,'/link/config','Config'),
  (10,'/link/api','API'),
  (11,'/link/integration','Integration'),
  (12,'/link/leads','Leads'),
  (13,'/link/campaigns','Campaigns');

DROP TABLE IF EXISTS `link_profile`;
CREATE TABLE `link_profile` (
  `link_id` int(11) NOT NULL,
  `profile_id` int(11) NOT NULL,
  PRIMARY KEY (`link_id`,`profile_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `link_profile` (`link_id`, `profile_id`)
VALUES
  (1,1),
  (1,2),
  (1,3),
  (1,4),
  (2,1),
  (2,2),
  (2,3),
  (3,1),
  (3,2),
  (4,1),
  (4,2),
  (5,1),
  (6,1),
  (6,3),
  (7,1),
  (7,3),
  (8,1),
  (8,3),
  (9,1),
  (10,1),
  (10,4),
  (11,1),
  (11,4),
  (12,1),
  (12,2),
  (12,3),
  (13,1),
  (13,2);

DROP TABLE IF EXISTS `profile`;
CREATE TABLE `profile` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `profile` (`id`, `name`)
VALUES
  (1,'admin'),
  (2,'mkt'),
  (3,'sales'),
  (4,'tech');

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL DEFAULT '',
  `password` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `idx_username_password` (`username`,`password`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `user` (`id`, `username`, `password`)
VALUES
  (1,'admin@email.com','321'),
  (2,'mkt@email.com','321'),
  (3,'sales@email.com','321'),
  (4,'tech@email.com','321');

DROP TABLE IF EXISTS `user_profile`;
CREATE TABLE `user_profile` (
  `user_id` int(11) NOT NULL,
  `profile_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`profile_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `user_profile` (`user_id`, `profile_id`)
VALUES
  (1,1),
  (2,2),
  (3,3),
  (4,4);

DROP TABLE IF EXISTS `user_token`;
CREATE TABLE `user_token` (
  `hash` varchar(36) NOT NULL,
  `user_id` int(11) NOT NULL,
  `login_date` datetime NOT NULL,
  `logout_date` datetime DEFAULT NULL,
  PRIMARY KEY (`hash`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;