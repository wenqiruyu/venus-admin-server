DROP TABLE IF EXISTS `venus_user`;
CREATE TABLE `venus_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户表id',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `username` varchar(255) DEFAULT NULL COMMENT '昵称',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `phone` char(11) DEFAULT NULL COMMENT '手机号码',
  `telephone` varchar(16) DEFAULT NULL COMMENT '住宅电话',
  `address` varchar(64) DEFAULT NULL COMMENT '联系地址',
  `userface` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `enabled` tinyint(1) DEFAULT '1' COMMENT '是否有效',
  `create_time` datetime DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `create_by` varchar(20) NOT NULL DEFAULT 'admin' COMMENT '创建者，记录创建者信息',
  `last_update_by` varchar(20) NOT NULL DEFAULT 'admin' COMMENT '修改者，记录修改者信息',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8 COMMENT '启明星用户表';

DROP TABLE IF EXISTS `venus_user_role`;
CREATE TABLE `venus_user_role` (
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户表id',
  `role_id` int(11) DEFAULT NULL COMMENT '角色表id',
  `enabled` tinyint(1) DEFAULT '1' COMMENT '是否有效'
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8 COMMENT '启明星用户角色表';

DROP TABLE IF EXISTS `venus_role`;
CREATE TABLE `venus_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `role_name_en` varchar(64) DEFAULT NULL COMMENT '角色名称',
  `role_name_zh` varchar(64) DEFAULT NULL COMMENT '角色名称 中文',
  `enabled` tinyint(1) DEFAULT '1' COMMENT '是否有效',
  `create_time` datetime DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `create_by` varchar(20) NOT NULL DEFAULT 'admin' COMMENT '创建者，记录创建者信息',
  `last_update_by` varchar(20) NOT NULL DEFAULT 'admin' COMMENT '修改者，记录修改者信息',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8 COMMENT '启明星用户角色表';

INSERT INTO `venus`.`venus_role` (`id`, `role_name_en`, `role_name_zh`, `enabled`, `create_time`, `update_time`, `create_by`, `last_update_by`)
VALUES ('1', 'ordinary', '普通用户', '1', '2019-12-02 10:01:12', '2019-12-02 10:02:30', 'admin', 'admin');
INSERT INTO `venus`.`venus_role` (`id`, `role_name_en`, `role_name_zh`, `enabled`, `create_time`, `update_time`, `create_by`, `last_update_by`)
VALUES ('2', 'member', '会员用户', '1', '2019-12-02 10:01:36', '2019-12-02 10:02:08', 'admin', 'admin');
INSERT INTO `venus`.`venus_role` (`id`, `role_name_en`, `role_name_zh`, `enabled`, `create_time`, `update_time`, `create_by`, `last_update_by`)
VALUES ('3', 'admin', '管理员', '1', '2019-12-02 10:02:25', '2019-12-02 10:02:27', 'admin', 'admin');


DROP TABLE IF EXISTS `venus_menu`;
CREATE TABLE `venus_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(64) DEFAULT NULL,
  `path` varchar(64) DEFAULT NULL,
  `component` varchar(64) DEFAULT NULL,
  `name` varchar(64) DEFAULT NULL,
  `iconCls` varchar(64) DEFAULT NULL,
  `keepAlive` tinyint(1) DEFAULT NULL,
  `requireAuth` tinyint(1) DEFAULT NULL,
  `parentId` int(11) DEFAULT NULL,
  `enabled` tinyint(1) DEFAULT '1',
  `create_time` datetime DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `create_by` varchar(20) NOT NULL DEFAULT 'admin' COMMENT '创建者，记录创建者信息',
  `last_update_by` varchar(20) NOT NULL DEFAULT 'admin' COMMENT '修改者，记录修改者信息',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8 COMMENT '启明星菜单表';

DROP TABLE IF EXISTS `venus_menu_role`;
CREATE TABLE `venus_menu_role` (
  `menu_id` int(11) DEFAULT NULL COMMENT '菜单表id',
  `role_id` int(11) DEFAULT NULL COMMENT '角色表id',
  `enabled` tinyint(1) DEFAULT '1' COMMENT '是否有效'
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8 COMMENT '启明星菜单角色表';

DROP TABLE IF EXISTS `venus_account_log`;
CREATE TABLE `venus_account_log`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '用户账户操作日志主键',
  `log_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '日志名称(login,register,logout)',
  `user_id` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户id',
  `ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户ip',
  `is_success` tinyint(4) NULL DEFAULT NULL COMMENT '是否执行成功(0失败1成功)',
  `message` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '具体消息',
  `enabled` tinyint(1) DEFAULT '1' COMMENT '是否有效',
  `create_time` datetime DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `create_by` varchar(20) NOT NULL DEFAULT 'admin' COMMENT '创建者，记录创建者信息',
  `last_update_by` varchar(20) NOT NULL DEFAULT 'admin' COMMENT '修改者，记录修改者信息',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=100 CHARSET=utf8 COMMENT '启明星账号操作记录';

DROP TABLE IF EXISTS `venus_operation_log`;
CREATE TABLE `venus_operation_log`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户操作日志主键',
  `log_name` varchar(255) NULL DEFAULT NULL COMMENT '日志名称',
  `user_name` varchar(30) NULL DEFAULT NULL COMMENT '用户名称',
  `user_ip` varchar(255) NULL DEFAULT NULL COMMENT '用户ip',
  `api_name` varchar(255) NULL DEFAULT NULL COMMENT 'api名称，url',
  `method_name` varchar(255) NULL DEFAULT NULL COMMENT '方法名称',
  `request_type` varchar(30) NULL DEFAULT NULL COMMENT '请求类型',
  `exception_msg` varchar(255) NULL DEFAULT NULL COMMENT '出现异常记录异常信息',
  `message` varchar(255) NULL DEFAULT NULL COMMENT '返回具体消息',
  `enabled` tinyint(1) DEFAULT '1' COMMENT '是否有效 0删除 1返回成功 2返回失败 3异常日志',
  `create_time` datetime DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `create_by` varchar(20) NOT NULL DEFAULT 'admin' COMMENT '创建者，记录创建者信息',
  `last_update_by` varchar(20) NOT NULL DEFAULT 'admin' COMMENT '修改者，记录修改者信息',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=100 CHARSET=utf8 COMMENT '启明星用户操作日志';