CREATE TABLE if not exists `user_el` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `userName` varchar(12) DEFAULT NULL COMMENT '名称',
  `passWord` varchar(16) DEFAULT NULL COMMENT '用户手机号',
  `user_sex` varchar(16) DEFAULT NULL COMMENT '创建时间',
  `nick_name` varchar(16) DEFAULT NULL COMMENT '年龄',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;