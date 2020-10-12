create table base_file_catalog (
    id varchar(50) NOT NULL COMMENT '主键',
    user_id varchar(50) NULL COMMENT '用户id',
    parent_id varchar(50) NULL COMMENT '父级id',
    name varchar(50) NULL COMMENT '名称',
    level int(11) NULL COMMENT '级别',
    rel_scn_dsc varchar(4000) NULL COMMENT '关系场景描述',
    create_date datetime  NULL COMMENT '创建时间',
    update_date datetime  NULL COMMENT '更新时间',
    icon varchar(100) NULL COMMENT '图标',
    mark varchar(100) NULL COMMENT '标记',
    scope varchar(100) NULL COMMENT '作用域',
    comments varchar(1000) NULL COMMENT '备注',
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;