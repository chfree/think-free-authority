create table config_properties (
    id varchar(50) NOT NULL COMMENT '主键',
    setting_key varchar(200) NULL COMMENT '键',
    setting_value varchar(1000) NULL COMMENT '值',
    application varchar(100) NULL COMMENT '应用名称',
    profile varchar(50) NULL COMMENT '环境',
    label varchar(50) NULL COMMENT '分支',
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

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
    parent_id varchar(50) NULL COMMENT '父级项目',
    create_date datetime  NULL COMMENT '创建时间',
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
    remark varchar(4000) NULL COMMENT '备注',
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

create table dps_project_puburl (
    id varchar(50) NOT NULL COMMENT '主键',
    project_id varchar(50) NULL COMMENT '项目id',
    url varchar(500) NULL COMMENT '访问地址',
    descr varchar(4000) NULL COMMENT '访问说明',
    url_mark varchar(50) NULL COMMENT '访问标记',
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;
