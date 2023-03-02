-- ----------
-- 数据库 seata_test
-- 字符集 utf8mb4_bin
-- ----------

DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
	`id` INT(10) NOT NULL AUTO_INCREMENT,
	`cnname` VARCHAR(50) NOT NULL DEFAULT '0' COLLATE 'utf8mb4_bin',
	`money` INT(10) NOT NULL DEFAULT '0',
	PRIMARY KEY (`id`) USING BTREE
)
COLLATE='utf8mb4_bin'
ENGINE=InnoDB
;

DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order` (
	`id` INT(10) NOT NULL AUTO_INCREMENT,
	`uid` INT(10) NOT NULL DEFAULT '0',
	`money` INT(10) NOT NULL DEFAULT '0',
	PRIMARY KEY (`id`) USING BTREE
)
COLLATE='utf8mb4_bin'
ENGINE=InnoDB
;

DROP TABLE IF EXISTS `t_info`;
CREATE TABLE `t_info` (
	`id` INT(10) NOT NULL AUTO_INCREMENT,
	`uid` INT(10) NOT NULL DEFAULT '0',
	`money` INT(10) NOT NULL DEFAULT '0',
	PRIMARY KEY (`id`) USING BTREE
)
COLLATE='utf8mb4_bin'
ENGINE=InnoDB
;


INSERT INTO t_user  VALUES (1, 'test', 1000);
INSERT INTO t_order VALUES (1, 1, 10);
INSERT INTO t_info VALUES  (1, 1, 100);

SELECT * FROM t_user;
SELECT * FROM t_order;
SELECT * FROM t_info;