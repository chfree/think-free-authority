create table base_file_template (
    id varchar(50) NOT NULL COMMENT '主键',
    file_id varchar(50) NULL COMMENT '文件id',
    name varchar(200) NULL COMMENT '名称',
    title varchar(1000) NULL COMMENT '标题',
    upload_date datetime  NULL COMMENT '上传时间',
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;