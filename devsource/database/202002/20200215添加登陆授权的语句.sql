create table base_login_auth (
    id varchar(50) NOT NULL COMMENT '主键',
    user_id varchar(50) NOT NULL COMMENT '用户id',
    token varchar(50) NULL COMMENT 'token信息',
    auth_tm datetime  NULL COMMENT '授权时间',
    type varchar(10) NULL COMMENT '授权方式',
    status varchar(10) NULL COMMENT '状态',
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;