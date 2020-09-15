create table base_file_template (
    id varchar(50) NOT NULL COMMENT '主键',
    file_id varchar(50) NULL COMMENT '文件id',
    name varchar(200) NULL COMMENT '名称',
    title varchar(1000) NULL COMMENT '标题',
    display_name varchar(1000) NULL COMMENT '显示名称',
    upload_date datetime  NULL COMMENT '上传时间',
    upload_user_id varchar(50) NULL COMMENT '上传人',
    upload_user_name varchar(100) NULL COMMENT '上传人名称',
    comments varchar(2000) NULL COMMENT '备注',
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;