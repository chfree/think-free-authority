CREATE TABLE `config_properties` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `key1` varchar(50) COLLATE utf8_bin NOT NULL,
  `value1` varchar(500) COLLATE utf8_bin DEFAULT NULL,
  `application` varchar(50) COLLATE utf8_bin NOT NULL,
  `profile` varchar(50) COLLATE utf8_bin NOT NULL,
  `label` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin

create table dps_project_info (
    id varchar(50) NOT NULL COMMENT '主键',
    project_no varchar(50) NULL COMMENT '项目编号',
    project_code_name varchar(50) NULL COMMENT '项目代号',
    name varchar(50) NULL COMMENT '项目名称',
    catalog_name varchar(200) NULL COMMENT '目录名称',
    server_name varchar(50) NULL COMMENT '服务名称',
    belong_service_registry varchar(50) NULL COMMENT '所属注册中心',
    project_type varchar(50) NULL COMMENT '项目类型',
    git_address varchar(300) NULL COMMENT 'git地址',
    port int(11) NULL COMMENT '端口号',
    context_path varchar(50) NULL COMMENT '虚拟路径',
    language varchar(50) NULL COMMENT '项目语言',
    description varchar(4000) NULL COMMENT '项目说明',
    remark varchar(4000) NULL COMMENT '备注',
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

create table dps_project_profile_setting (
    id varchar(50) NOT NULL COMMENT '主键',
    project_id varchar(50) NULL COMMENT '项目id',
    profile varchar(50) NULL COMMENT '环境',
    label varchar(50) NULL COMMENT '分支',
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;