
-- 创建权限用户
-- CREATE USER 'monitor'@'%' IDENTIFIED BY 'monitor';
-- 授权
-- GRANT ALL ON *.* TO 'monitor'@'%';

CREATE DATABASE IF NOT EXISTS `monitor`  DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

USE monitor;

DELIMITER

-- START 用户/认证模块功能
CREATE TABLE IF NOT EXISTS `user` (
  `uid` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `name` varchar(20) DEFAULT NULL COMMENT '用户名',
  `phone` int(11) DEFAULT NULL COMMENT '手机号',
  `email` varchar(64) DEFAULT NULL COMMENT '邮箱',
  `nick` varchar(64) DEFAULT NULL COMMENT '昵称',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `type` int(1) NOT NULL DEFAULT 1 COMMENT '类型', /*用户类型:1-超级管理员,2-普通用户*/
  `status` int(1) NOT NULL DEFAULT 1 COMMENT '状态', /*0-正常，1-锁定，2-用户信息损坏，3-用户已删除*/
  `exattr` varchar(10240) DEFAULT  NULL COMMENT '扩展属性',
  `ctime` datetime NOT NULL COMMENT '创建时间',
  `mtime` datetime NOT NULL COMMENT '修改时间',
  `last_time` datetime DEFAULT NULL COMMENT '上次登录时间',

  PRIMARY KEY (`uid`)
)  ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户信息表';

-- CREATE TABLE IF NOT EXISTS `user_recycle` (
--   `uid` bigint(11) NOT NULL COMMENT '用户ID',
--   `name` varchar(20) DEFAULT NULL COMMENT '用户名',
--   `phone` int(11) DEFAULT NULL COMMENT '手机号',
--   `email` varchar(64) DEFAULT NULL COMMENT '邮箱',
--   `nick` varchar(64) DEFAULT NULL COMMENT '昵称',
--   `password` varchar(255) NOT NULL COMMENT '密码',
--   `type` int(1) NOT NULL DEFAULT 1 COMMENT '状态',   /*0-正常，1-锁定，2-用户信息损坏*/
--   `status` int(1) NOT NULL DEFAULT 1 COMMENT '类型', /*用户类型:1-超级管理员,2-普通用户*/
--   `exattr` varchar(10240) DEFAULT NULL COMMENT '扩展属性',
--   `ctime` datetime NOT NULL COMMENT '创建时间',
--   `mtime` datetime DEFAULT NULL COMMENT '修改时间',
--   `last_time` datetime DEFAULT NULL COMMENT '上次登录时间',
--
--   PRIMARY KEY (`uid`)
-- ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户信息回收站表';

-- 这个表的作用主要是减少查询用户表的次数,然后就是对用户登录设备的限制
CREATE TABLE IF NOT EXISTS `login_id` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `uid` bigint(20) NOT NULL COMMENT '用户ID',
  `type` varchar(16) NOT NULL COMMENT '登录类型', /*type=[name,email,phone]*/
  `name` varchar(16) NOT NULL COMMENT '用户名',

  PRIMARY KEY (`id`),
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

CREATE TABLE IF NOT EXISTS `backend_taskinfo` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `file_name` varchar(1024) DEFAULT NULL COMMENT '文件名',
  `business_type` int(1) DEFAULT NULL COMMENT '业务类型', -- 一级行研、二级行研
  `task_type` int(1) DEFAULT NULL COMMENT '任务类型', -- 上传、清洗的失败、需要人工审核
  `e_type` int(1) DEFAULT NULL COMMENT '问题类型', --
  `e_status` int(1) DEFAULT NULL COMMENT '处理状态', -- 等待人工处理、已完成
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `treated_user` varchar(20) DEFAULT NULL COMMENT '处理人',
  `treated_time` datetime DEFAULT NULL COMMENT '处理时间',

  PRIMARY KEY (`id`)
)  ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='任务表';

CREATE TABLE IF NOT EXISTS `backend_tasklog` (
  `id` bigint(11) NOT NULL COMMENT '任务清单表ID',
  `option_time` datetime DEFAULT NULL COMMENT '操作时间',
  `option_user` varchar(20) DEFAULT NULL COMMENT '操作人',
  `option_content` varchar(10240) DEFAULT NULL COMMENT '操作内容'

)  ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='任务操作表';

CREATE TABLE IF NOT EXISTS `backend_files` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '文件ID',
  `file_name` varchar(10240) DEFAULT NULL COMMENT '文件名称',
  `time` datetime DEFAULT NULL COMMENT '发布日期',
  `industryname` varchar(128) DEFAULT NULL COMMENT '行业',
  `file_type` int(1) DEFAULT NULL COMMENT '文件类型',
  `size` bigint(12) DEFAULT NULL COMMENT '文件大小',
  `file_path` varchar(256) DEFAULT NULL COMMENT '存储地址',
  `type` int(1) DEFAULT NULL COMMENT '业务类型', -- 1：一级行研、2：二级行研
  `source` int(1) DEFAULT NULL COMMENT '来源', -- 1：爬虫、2：人工
  `create_time` datetime DEFAULT NULL COMMENT '上传时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `status` int(1) DEFAULT NULL COMMENT '状态', -- 待清洗、待完善、已完成
  `push_status` int(1) DEFAULT NULL COMMENT '推送状态', -- 等待推送、成功、失败
  `flag` int(3) DEFAULT NULL COMMENT '标记', -- 采用linux的权限机制
  `md5` varchar(64) DEFAULT NULL COMMENT 'MD5',

    PRIMARY KEY (`id`)
)  ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文件信息表';

CREATE TABLE IF NOT EXISTS `backend_fileattrs` (
  `id` bigint(11) NOT NULL COMMENT '自增ID',
  `fileId` bigint(11) NOT NULL COMMENT '文件信息表ID',
  `compose_time` datetime DEFAULT NULL COMMENT '报告撰写日期',
  `compose_institution` datetime DEFAULT NULL COMMENT '报告撰写机构',
  `security_code` varchar(32) DEFAULT NULL COMMENT '证券代码',
  `security_abbrev` varchar(128) DEFAULT NULL COMMENT '证券简称',
  `release_institution` varchar(128) DEFAULT NULL COMMENT '报告发布机构',
  `time` datetime DEFAULT NULL COMMENT '发布日期',
  `industryname` varchar(128) DEFAULT NULL COMMENT '行业名称',
  `author` varchar(128) DEFAULT NULL COMMENT '作者',

  PRIMARY KEY (`id`)
)  ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文件属性表';

CREATE TABLE IF NOT EXISTS `backend_filelog` (
  `id` bigint(11) NOT NULL COMMENT '自增ID',
  `fileId` bigint(11) NOT NULL COMMENT '文件信息表ID',
  `option_type` int(1) DEFAULT NULL COMMENT '操作类型',
  `option_time` datetime DEFAULT NULL COMMENT '操作时间',
  `option_user` varchar(32) DEFAULT NULL COMMENT '操作人',
  `option_content` text DEFAULT NULL COMMENT '操作内容',

  PRIMARY KEY (`id`)
)  ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文件日志表';

CREATE TABLE IF NOT EXISTS `backend_file_tables` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `pageIndex` int(4) DEFAULT NULL COMMENT '当前页数',
  `title` varchar(1024) DEFAULT NULL COMMENT '表格标题',
  `pngFile` varchar(1024) DEFAULT NULL COMMENT '图片路径',
  `algorithm` varchar(32) DEFAULT NULL COMMENT '算法版本',
  `rowCount` int(2) DEFAULT NULL COMMENT '行数',
  `columnCount` varchar(32) DEFAULT NULL COMMENT '列数',
  `x` int(4) DEFAULT NULL,
  `y` int(4) DEFAULT NULL,
  `w` int(4) DEFAULT NULL,
  `h` int(4) DEFAULT NULL,
  `data` text DEFAULT NULL COMMENT '表格数据',
  `raw_data` text DEFAULT NULL COMMENT '表格标题',
  `oss_path` varchar(32) DEFAULT NULL COMMENT '图片路径',
  `fileId` bigint(11) DEFAULT NULL COMMENT '文件ID',
  `_id` varchar(128) DEFAULT NULL COMMENT '解析生成ID',

  PRIMARY KEY (`id`)
)  ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文件 Table 表';

CREATE TABLE IF NOT EXISTS `backend_file_charts` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `title` varchar(1024) DEFAULT NULL COMMENT '图片标题',
  `chartType` varchar(1024) DEFAULT NULL COMMENT '图片类型',
  `legends` text DEFAULT NULL,
  `area` text DEFAULT NULL,
  `fonts` text DEFAULT NULL COMMENT '字体',
  `vAxisTextL` text DEFAULT NULL,
  `vAxisTextR` text DEFAULT NULL,
  `hAxisTextD` text DEFAULT NULL,
  `hAxisTextU` text DEFAULT NULL,
  `hAxis` varchar(128) DEFAULT NULL,
  `lvAxis` varchar(128) DEFAULT NULL,
  `rvAxis` varchar(128) DEFAULT NULL,
  `data` text DEFAULT NULL COMMENT '位图数据',
  `filePath` varchar(1024) DEFAULT NULL COMMENT '',
  `pageIndex` int(4) DEFAULT NULL COMMENT '当前页数',
  `pngFile` varchar(1024) DEFAULT NULL COMMENT 'PNG 图片路径',
  `svgFile` varchar(1024) DEFAULT NULL COMMENT 'SVG 图片路径',
  `texts` text DEFAULT NULL COMMENT '文字内容',
  `source` text DEFAULT NULL COMMENT '来源',
  `oss_path` varchar(1024) DEFAULT NULL COMMENT '图片路径',
  `fileId` bigint(11) DEFAULT NULL COMMENT '文件ID',
  `_id` varchar(128) DEFAULT NULL COMMENT '解析生成ID',

  PRIMARY KEY (`id`)
)  ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文件 Chart 表';

CREATE TABLE IF NOT EXISTS `backend_file_text` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `author` varchar(32) DEFAULT NULL COMMENT '作者',
  `keywords` varchar(256) DEFAULT NULL COMMENT '关键字',
  `title` varchar(256) DEFAULT NULL COMMENT '标题',
  `summary` text DEFAULT NULL COMMENT '概述',
  `catalog` text DEFAULT NULL COMMENT '目录',
  `paragraphs` text DEFAULT NULL COMMENT '段落',
  `fulltext` text DEFAULT NULL COMMENT '全文索引',
  `pageCount` int(3) DEFAULT NULL COMMENT '文档页数',
  `paragraphCount` int(3) DEFAULT NULL COMMENT '段落计数',
  `characterCount` bigint(11) DEFAULT NULL COMMENT '字符计数',
  `chart_version` int(3) DEFAULT NULL COMMENT 'Chart 解析版本',
  `table_version` int(3) DEFAULT NULL COMMENT 'Table 解析版本',
  `text_version` int(3) DEFAULT NULL COMMENT 'Text 解析版本',
  `version` varchar(64) DEFAULT NULL COMMENT '算法版本',
  `fileId` bigint(11) DEFAULT NULL COMMENT '文件ID',
  `_id` varchar(128) DEFAULT NULL COMMENT '解析生成ID',

  PRIMARY KEY (`id`)
)  ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文件 Text 表';

-- END 用户/认证模块功能