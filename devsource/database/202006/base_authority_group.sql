create table base_authority_group (
    id varchar(50) NOT NULL COMMENT '主键',
    name varchar(100) NULL COMMENT '名称',
    description varchar(2000) NULL COMMENT '描述',
    group_mark varchar(50) NULL COMMENT '逻辑标记',
    level varchar(50) NULL COMMENT '级别',
    status varchar(50) NULL COMMENT '状态',
    sort_code int(11) NULL COMMENT '排序字段',
    create_date datetime  NULL COMMENT '创建时间',
    create_user_id varchar(50) NULL COMMENT '创建人id',
    create_user_name varchar(50) NULL COMMENT '创建人名称',
    modify_date datetime  NULL COMMENT '修改时间',
    modify_user_id varchar(50) NULL COMMENT '修改人id',
    modify_user_name varchar(50) NULL COMMENT '修改人名称',
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;