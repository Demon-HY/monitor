
CREATE DATABASE IF NOT EXISTS `monitor`  DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

USE monitor;

DELIMITER

/* START 用户模块功能*/
CREATE TABLE IF NOT EXISTS `user` (
  `uid` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `name` varchar(20) DEFAULT NULL COMMENT '用户名',
  `phone` int(11) DEFAULT NULL COMMENT '手机号',
  `email` varchar(64) DEFAULT NULL COMMENT '邮箱',
  `nick` varchar(64) DEFAULT NULL COMMENT '昵称',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `type` int(1) NOT NULL DEFAULT 0 COMMENT '状态',   /*0-正常，1-锁定，2-用户信息损坏*/
  `status` int(1) NOT NULL DEFAULT 2 COMMENT '类型', /*用户类型:1-超级管理员,2-普通用户*/
  `exattr` varchar(10240) DEFAULT  NULL COMMENT '扩展属性',
  `ctime` datetime NOT NULL COMMENT '创建时间',
  `mtime` datetime NOT NULL COMMENT '修改时间',
  `last_time` datetime DEFAULT NULL COMMENT '上次登录时间',

  PRIMARY KEY (`uid`)
)  ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户信息表';

CREATE TABLE IF NOT EXISTS `user_recycle` (
  `uid` bigint(11) NOT NULL COMMENT '用户ID',
  `name` varchar(20) DEFAULT NULL COMMENT '用户名',
  `phone` int(11) DEFAULT NULL COMMENT '手机号',
  `email` varchar(64) DEFAULT NULL COMMENT '邮箱',
  `nick` varchar(64) DEFAULT NULL COMMENT '昵称',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `type` int(1) NOT NULL DEFAULT 1 COMMENT '状态',   /*0-正常，1-锁定，2-用户信息损坏*/
  `status` int(1) NOT NULL DEFAULT 1 COMMENT '类型', /*用户类型:1-超级管理员,2-普通用户*/
  `exattr` varchar(10240) DEFAULT NULL COMMENT '扩展属性',
  `ctime` datetime NOT NULL COMMENT '创建时间',
  `mtime` datetime DEFAULT NULL COMMENT '修改时间',
  `last_time` datetime DEFAULT NULL COMMENT '上次登录时间',

  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户信息回收站表';

/*这个表的作用主要是减少查询用户表的次数,然后就是对用户登录设备的限制*/
CREATE TABLE IF NOT EXISTS `login_id` (
  `uid` bigint(20) NOT NULL COMMENT '用户ID',
  `type` varchar(16) NOT NULL COMMENT '登录类型', /*type=[name,email,phone]*/
  `name` varchar(16) NOT NULL COMMENT '用户名',

  UNIQUE KEY (`type`, `name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户登录表';

CREATE TABLE IF NOT EXISTS `token` (
  `token` varchar(128) PRIMARY KEY COMMENT '用户登录凭据',
  `uid` bigint(20) UNSIGNED NOT NULL COMMENT '用户ID',
  `expires` datetime NOT NULL COMMENT '过期时间',
  `ctime` datetime NOT NULL COMMENT '创建时间',
  `ip` varchar(32) DEFAULT NULL COMMENT '客户端IP',
  `device` varchar(8) DEFAULT NULL COMMENT '客户端设备' /*device=[Web,PC,Android,IOS]*/
  
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户登录凭证表';

/* END 用户模块功能*/