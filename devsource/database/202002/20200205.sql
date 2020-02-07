-- 添加开发模板表
create table base_develop_code_tmp (
    id varchar(50) NOT NULL COMMENT '主键',
    name varchar(150) NULL COMMENT '模板名称',
    comment varchar(2000) NULL COMMENT '模板描述',
    content text NULL COMMENT '模板内容',
    pub varchar(10) NULL COMMENT '是否公开',
    type varchar(10) NULL COMMENT '类型',
    group_name varchar(50) NULL COMMENT '分组名称',
    create_user_id varchar(50) NULL COMMENT '创建人id',
    create_user_name varchar(150) NULL COMMENT '创建人名称',
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;