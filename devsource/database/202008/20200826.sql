create table base_param_setting (
    id varchar(50) NOT NULL COMMENT '主键',
    name varchar(100) NULL COMMENT '参数名称',
    title varchar(100) NULL COMMENT '参数标题',
    param_value varchar(2000) NULL COMMENT '参数值',
    comments varchar(4000) NULL COMMENT '备注',
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

create table base_file_info (
    file_id varchar(50) NOT NULL COMMENT '主键',
    size int(11) NULL COMMENT '大小',
    mime_type varchar(1000) NULL COMMENT '文件类型',
    sha1 varchar(100) NULL COMMENT '文件的sha1',
    store_type varchar(100) NULL COMMENT '存储方式',
    suffix varchar(50) NULL COMMENT '后缀',
    path varchar(2000) NULL COMMENT '路径',
    display_name varchar(1000) NULL COMMENT '显示名称',
    upload_date datetime  NULL COMMENT '上传时间',
    upload_user_id varchar(50) NULL COMMENT '上传人',
    upload_user_name varchar(100) NULL COMMENT '上传人名称',
    delete_mark varchar(10) NULL COMMENT '删除标记',
    PRIMARY KEY (file_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

create table base_file_bsn (
    id varchar(50) NOT NULL COMMENT '主键',
    file_id varchar(50) NULL COMMENT '文件id',
    bsn_id varchar(50) NULL COMMENT '业务id',
    bsn_type varchar(50) NULL COMMENT '业务类型',
    display_name varchar(1000) NULL COMMENT '显示名称',
    upload_user_id varchar(50) NULL COMMENT '上传人',
    upload_user_name varchar(100) NULL COMMENT '上传人名称',
    upload_date datetime  NULL COMMENT '上传时间',
    delete_mark varchar(10) NULL COMMENT '删除标记',
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;