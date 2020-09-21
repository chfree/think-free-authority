create table base_file_delete_wait (
    id varchar(50) NOT NULL COMMENT '主键',
    file_id varchar(50) NULL COMMENT '文件id',
    size int(11) NULL COMMENT '大小',
    mime_type varchar(1000) NULL COMMENT '文件类型',
    sha1 varchar(100) NULL COMMENT '文件的sha1',
    store_type varchar(100) NULL COMMENT '存储方式',
    suffix varchar(50) NULL COMMENT '后缀',
    path varchar(2000) NULL COMMENT '路径',
    bsn_id varchar(50) NULL COMMENT '业务id',
    bsn_type varchar(50) NULL COMMENT '业务类型',
    upload_user_id varchar(50) NULL COMMENT '上传人',
    upload_user_name varchar(100) NULL COMMENT '上传人名称',
    upload_date date  NULL COMMENT '上传时间',
    display_name varchar(1000) NULL COMMENT '显示名称',
    add_date date  NULL COMMENT '添加时间',
    wait_day int(11) NULL COMMENT '等待时间',
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;