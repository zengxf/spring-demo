
-- 创建、删除表
DROP TABLE IF EXISTS `test_user_1`;
CREATE TABLE `test_user_1` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(20) DEFAULT NULL,
  `age` INT DEFAULT NULL,
  `email` VARCHAR(20) DEFAULT NULL,
  `create_date` DATETIME DEFAULT NULL,
  `modify_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=888;

-- 新增记录
INSERT INTO `test_user_1` VALUES ('1', 'aa', '18', 'aa@126.com', '2019-09-27 22:15:22', '2019-09-28 09:08:12');
INSERT INTO `test_user_1` VALUES ('2', 'yy', '20', 'yy@sina.com', '2019-09-27 22:15:22', '2019-09-28 09:08:18');
INSERT INTO `test_user_1` VALUES ('3', 'dd', '22', 'dd@qq.com', '2019-09-27 22:15:22', '2019-09-28 09:08:26');