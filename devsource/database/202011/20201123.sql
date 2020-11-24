create table base_file_chunk (
    id varchar(50) NOT NULL COMMENT '主键',
    chunk_number int(11) NULL COMMENT '当前文件块',
    chunk_size bigint NULL COMMENT '分块大小',
    current_chunk_size bigint NULL COMMENT '当前分块大小',
    total_size bigint NULL COMMENT '总大小',
    identifier varchar(100) NULL COMMENT '文件标识',
    filename varchar(1000) NULL COMMENT '文件名',
    total_chunks int(11) NULL COMMENT '总块数',
    mime_type varchar(200) NULL COMMENT '文件类型',
    suffix varchar(50) NULL COMMENT '文件后缀',
    bsn_id varchar(50) NULL COMMENT '业务id',
    bsn_type varchar(50) NULL COMMENT '业务类型',
    status varchar(50) NULL COMMENT '状态',
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;