CREATE TABLE `tb_user` (
   `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
   `username` varchar(50) NOT NULL COMMENT '用户名',
   `password` varchar(100) DEFAULT NULL COMMENT '密码',
   `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
   `mobile` varchar(100) DEFAULT NULL COMMENT '手机号',
   `status` tinyint(4) DEFAULT NULL COMMENT '状态  0：禁用   1：正常',
   `create_time` datetime DEFAULT NULL COMMENT '创建时间',
   `manager_id` bigint(20) DEFAULT NULL COMMENT '审批人',
   `remark` varchar(100) NOT NULL COMMENT '备注',
   PRIMARY KEY (`user_id`),
   UNIQUE KEY `username` (`username`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统用户';