CREATE TABLE `log_controller` (
  `id` BIGINT(15) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `create_by` BIGINT(15) NOT NULL COMMENT '创建人',
  `last_upd_date` TIMESTAMP NULL DEFAULT NULL COMMENT '最后更新日期',
  `last_upd_by` BIGINT(15) DEFAULT NULL COMMENT '最后更新人',
  `modification_num` BIGINT(15) DEFAULT NULL COMMENT '版本号',
  `link_id` VARCHAR(255) DEFAULT NULL COMMENT 'linkid',
  `user_id` VARCHAR(255) DEFAULT NULL COMMENT '用户id',
  `user_name` VARCHAR(255) DEFAULT NULL COMMENT '用户名',
  `class_name` VARCHAR(255) DEFAULT NULL COMMENT '类名',
  `hsr_class_name` VARCHAR(255) DEFAULT NULL COMMENT 'hsr类名',
  `method_name` VARCHAR(255) DEFAULT NULL COMMENT '方法',
  `request_head` VARCHAR(1000) DEFAULT NULL COMMENT 'head',
  `status` VARCHAR(20) DEFAULT NULL COMMENT '状态',
  `request_full_url` VARCHAR(255) DEFAULT NULL COMMENT '完整url',
  `request_url` VARCHAR(255) DEFAULT NULL COMMENT 'url',
  `log_desc` VARCHAR(255) DEFAULT NULL COMMENT '日志说明',
  `spend_time` VARCHAR(255) DEFAULT NULL COMMENT '花费时间',
  `begin_time` VARCHAR(255) DEFAULT NULL COMMENT '开始时间',
  `end_time` VARCHAR(255) DEFAULT NULL COMMENT '结束时间',
  `client_info` VARCHAR(255) DEFAULT NULL COMMENT '客户端信息',
  `server_info` VARCHAR(255) DEFAULT NULL COMMENT '服务端信息',
  PRIMARY KEY (`id`),
  INDEX `log_controller_n1_log_desc` (`log_desc`),
  INDEX `log_controller_n2_log_request_url` (`request_url`),
  INDEX `log_controller_n3_log_user_name` (`user_name`),
  INDEX `log_controller_n4_log_link_id` (`link_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE `log_controller_x` (
  `id` BIGINT(15) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `parent_id` BIGINT(15) NOT NULL COMMENT '父主键',
  `create_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `create_by` BIGINT(15) NOT NULL COMMENT '创建人',
  `last_upd_date` TIMESTAMP NULL DEFAULT NULL COMMENT '最后更新日期',
  `last_upd_by` BIGINT(15) DEFAULT NULL COMMENT '最后更新人',
  `modification_num` BIGINT(15) DEFAULT NULL COMMENT '版本号',
  `exception` VARCHAR(4000) DEFAULT NULL COMMENT '异常',
  `input_params` VARCHAR(4000) DEFAULT NULL COMMENT '输入参数',
  `output_params` VARCHAR(4000) DEFAULT NULL COMMENT '输出参数',
  PRIMARY KEY (`id`),
  INDEX `log_controller_x_n1_parent_id` (`parent_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;




