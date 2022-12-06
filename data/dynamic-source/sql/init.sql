CREATE DATABASE `test01` CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_bin';
CREATE DATABASE `test02` CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_bin';

USE test01;
CREATE TABLE `test` (
  `id` int NOT NULL,
  `age` int DEFAULT NULL,
  `enname` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;
INSERT INTO test VALUES
(1, 22, 'zxf'),
(2, 33, 'fa');
SELECT * FROM test;

USE test02;
CREATE TABLE `test_user` (
  `id` int NOT NULL,
  `status` int DEFAULT NULL,
  `enname` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;
INSERT INTO test_user VALUES
(1, 1, 'feng'),
(2, 0, 'Ff');
SELECT * FROM test_user;