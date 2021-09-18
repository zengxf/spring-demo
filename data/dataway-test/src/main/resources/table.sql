CREATE TABLE `interface_info` (
	`api_id` VARCHAR(50) NOT NULL DEFAULT 'AUTO_INCREMENT' COMMENT 'ID' COLLATE 'utf8mb4_0900_ai_ci',
	`api_method` VARCHAR(12) NOT NULL COMMENT 'HttpMethod：GET、PUT、POST' COLLATE 'utf8mb4_0900_ai_ci',
	`api_path` VARCHAR(512) NOT NULL COMMENT '拦截路径' COLLATE 'utf8mb4_0900_ai_ci',
	`api_status` INT(10) NOT NULL COMMENT '状态：0草稿，1发布，2有变更，3禁用',
	`api_comment` VARCHAR(255) NULL DEFAULT NULL COMMENT '注释' COLLATE 'utf8mb4_0900_ai_ci',
	`api_type` VARCHAR(24) NOT NULL COMMENT '脚本类型：SQL、DataQL' COLLATE 'utf8mb4_0900_ai_ci',
	`api_script` MEDIUMTEXT NOT NULL COMMENT '查询脚本：xxxxxxx' COLLATE 'utf8mb4_0900_ai_ci',
	`api_schema` MEDIUMTEXT NULL DEFAULT NULL COMMENT '接口的请求/响应数据结构' COLLATE 'utf8mb4_0900_ai_ci',
	`api_sample` MEDIUMTEXT NULL DEFAULT NULL COMMENT '请求/响应/请求头样本数据' COLLATE 'utf8mb4_0900_ai_ci',
	`api_option` MEDIUMTEXT NULL DEFAULT NULL COMMENT '扩展配置信息' COLLATE 'utf8mb4_0900_ai_ci',
	`api_create_time` BIGINT(19) NULL DEFAULT NULL COMMENT '创建时间',
	`api_gmt_time` BIGINT(19) NULL DEFAULT NULL COMMENT '修改时间',
	PRIMARY KEY (`api_id`) USING BTREE
)
COMMENT='Dataway 中的API'
COLLATE='utf8mb4_0900_ai_ci'
ENGINE=InnoDB
;

CREATE TABLE `interface_release` (
	`pub_id` VARCHAR(50) NOT NULL DEFAULT 'AUTO_INCREMENT' COMMENT 'Publish ID' COLLATE 'utf8mb4_0900_ai_ci',
	`pub_api_id` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '所属API ID' COLLATE 'utf8mb4_0900_ai_ci',
	`pub_method` VARCHAR(12) NOT NULL COMMENT 'HttpMethod：GET、PUT、POST' COLLATE 'utf8mb4_0900_ai_ci',
	`pub_path` VARCHAR(512) NOT NULL COMMENT '拦截路径' COLLATE 'utf8mb4_0900_ai_ci',
	`pub_status` INT(10) NOT NULL COMMENT '状态：0有效，1无效（可能被下线）',
	`pub_type` VARCHAR(24) NOT NULL COMMENT '脚本类型：SQL、DataQL' COLLATE 'utf8mb4_0900_ai_ci',
	`pub_script` MEDIUMTEXT NOT NULL COMMENT '查询脚本：xxxxxxx' COLLATE 'utf8mb4_0900_ai_ci',
	`pub_script_ori` MEDIUMTEXT NOT NULL COMMENT '原始查询脚本，仅当类型为SQL时不同' COLLATE 'utf8mb4_0900_ai_ci',
	`pub_schema` MEDIUMTEXT NULL DEFAULT NULL COMMENT '接口的请求/响应数据结构' COLLATE 'utf8mb4_0900_ai_ci',
	`pub_sample` MEDIUMTEXT NULL DEFAULT NULL COMMENT '请求/响应/请求头样本数据' COLLATE 'utf8mb4_0900_ai_ci',
	`pub_option` MEDIUMTEXT NULL DEFAULT NULL COMMENT '扩展配置信息' COLLATE 'utf8mb4_0900_ai_ci',
	`pub_release_time` BIGINT(19) NULL DEFAULT NULL COMMENT '发布时间（下线不更新）',
	`pub_comment` VARCHAR(50) NULL DEFAULT NULL COLLATE 'utf8mb4_0900_ai_ci',
	PRIMARY KEY (`pub_id`) USING BTREE,
	INDEX `idx_interface_release` (`pub_api_id`) USING BTREE
)
COMMENT='Dataway API 发布历史。'
COLLATE='utf8mb4_0900_ai_ci'
ENGINE=InnoDB
;

CREATE index idx_interface_release ON interface_release (pub_api_id);

CREATE TABLE `user` (
	`id` INT(10) NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(20) NULL DEFAULT NULL COLLATE 'utf8_general_ci',
	`age` INT(10) NULL DEFAULT NULL,
	`email` VARCHAR(20) NULL DEFAULT NULL COLLATE 'utf8_general_ci',
	`createdate` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
	`modifydate` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	PRIMARY KEY (`id`) USING BTREE
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=5
;

