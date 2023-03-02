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
	`create_time` TIMESTAMP NULL DEFAULT NULL,
	PRIMARY KEY (`id`) USING BTREE
)
COLLATE='utf8mb4_bin'
ENGINE=InnoDB
;


INSERT INTO t_user  VALUES (1, 'test', 1000);
INSERT INTO t_order VALUES (1, 1, 10);
INSERT INTO t_info  VALUES (1, 1, 100, NOW(3));

SELECT * FROM t_user;
SELECT * FROM t_order;
SELECT * FROM t_info;


----------------------
-- undo log
----------------------

DROP TABLE IF EXISTS `undo_log_user`;
CREATE TABLE `undo_log_user` (
	`id` BIGINT(19) NOT NULL AUTO_INCREMENT,
	`branch_id` BIGINT(19) NOT NULL,
	`xid` VARCHAR(100) NOT NULL COLLATE 'utf8mb4_bin',
	`context` VARCHAR(128) NOT NULL COLLATE 'utf8mb4_bin',
	`rollback_info` LONGBLOB NOT NULL,
	`log_status` INT(10) NOT NULL,
	`log_created` DATETIME NOT NULL,
	`log_modified` DATETIME NOT NULL,
	PRIMARY KEY (`id`) USING BTREE,
	UNIQUE INDEX `ux_undo_log` (`xid`, `branch_id`) USING BTREE
)
COLLATE='utf8mb4_bin'
ENGINE=InnoDB
;

DROP TABLE IF EXISTS `undo_log_order`;
CREATE TABLE `undo_log_order` (
	`id` BIGINT(19) NOT NULL AUTO_INCREMENT,
	`branch_id` BIGINT(19) NOT NULL,
	`xid` VARCHAR(100) NOT NULL COLLATE 'utf8mb4_bin',
	`context` VARCHAR(128) NOT NULL COLLATE 'utf8mb4_bin',
	`rollback_info` LONGBLOB NOT NULL,
	`log_status` INT(10) NOT NULL,
	`log_created` DATETIME NOT NULL,
	`log_modified` DATETIME NOT NULL,
	PRIMARY KEY (`id`) USING BTREE,
	UNIQUE INDEX `ux_undo_log` (`xid`, `branch_id`) USING BTREE
)
COLLATE='utf8mb4_bin'
ENGINE=InnoDB
;

DROP TABLE IF EXISTS `undo_log_info`;
CREATE TABLE `undo_log_info` (
	`id` BIGINT(19) NOT NULL AUTO_INCREMENT,
	`branch_id` BIGINT(19) NOT NULL,
	`xid` VARCHAR(100) NOT NULL COLLATE 'utf8mb4_bin',
	`context` VARCHAR(128) NOT NULL COLLATE 'utf8mb4_bin',
	`rollback_info` LONGBLOB NOT NULL,
	`log_status` INT(10) NOT NULL,
	`log_created` DATETIME NOT NULL,
	`log_modified` DATETIME NOT NULL,
	PRIMARY KEY (`id`) USING BTREE,
	UNIQUE INDEX `ux_undo_log` (`xid`, `branch_id`) USING BTREE
)
COLLATE='utf8mb4_bin'
ENGINE=InnoDB
;



SELECT * FROM undo_log_user;
SELECT * FROM undo_log_order;
SELECT * FROM undo_log_info;