create table base_message_template (
    id varchar(50) NOT NULL COMMENT '主键',
    name varchar(50) NULL COMMENT '模板名称',
    title varchar(600) NULL COMMENT '标题',
    icon varchar(50) NULL COMMENT '图标',
    title_tpl varchar(500) NULL COMMENT '标题模板',
    content_tpl varchar(4000) NULL COMMENT '内容模板',
    enabled varchar(10) NULL COMMENT '是否启用',
    type varchar(10) NULL COMMENT '类型',
    level int(11) NULL COMMENT '消息级别',
    rmrk varchar(200) NULL COMMENT '备注',
    rmrk1 varchar(200) NULL COMMENT '备注1',
    add_user_id varchar(50) NULL COMMENT '添加人',
    add_date datetime  NULL COMMENT '添加时间',
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;


create table base_message_info (
    id varchar(50) NOT NULL COMMENT '主键',
    title varchar(1000) NULL COMMENT '标题',
    content varchar(8000) NULL COMMENT '正文',
    icon varchar(200) NULL COMMENT '标题图标',
    start_dt datetime  NULL COMMENT '开始提示时间',
    end_dt datetime  NULL COMMENT '消息结束时间',
    type varchar(50) NULL COMMENT '消息类型',
    bus_data varchar(3000) NULL COMMENT '业务数据',
    level int(11) NULL COMMENT '消息级别',
    rmrk varchar(200) NULL COMMENT '备注',
    rmrk1 varchar(200) NULL COMMENT '备注1',
    add_user_id varchar(50) NULL COMMENT '添加人',
    add_date datetime  NULL COMMENT '添加时间',
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

create table base_message_receive (
    id varchar(50) NOT NULL COMMENT '主键',
    message_id varchar(50) NULL COMMENT '消息id',
    receive_type varchar(50) NULL COMMENT '接收范围',
    receive_id varchar(50) NULL COMMENT '接收人id',
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

create table base_message_read (
    id varchar(50) NOT NULL COMMENT '主键',
    message_id varchar(50) NULL COMMENT '消息id',
    user_id varchar(50) NULL COMMENT '用户id',
    read_dt datetime  NULL COMMENT '阅读时间',
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;