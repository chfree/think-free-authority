create table bhv_data_edit_log (
    id varchar(50) NOT NULL COMMENT '主键',
    bsn_type varchar(50) NULL COMMENT '业务类型',
    bsn_id varchar(50) NULL COMMENT '业务id',
    oper_type varchar(20) NULL COMMENT '操作类型',
    record_dt datetime  NULL COMMENT '记录时间',
    user_id varchar(50) NULL COMMENT '用户id',
    user_name varchar(200) NULL COMMENT '用户名称',
    user_dept_id varchar(50) NULL COMMENT '用户部门id',
    user_dept_name varchar(200) NULL COMMENT '用户部门名称',
    user_role_id varchar(50) NULL COMMENT '用户角色id',
    user_role_name varchar(50) NULL COMMENT '用户角色名称',
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;


create table bhv_data_edit_dtl (
    id varchar(50) NOT NULL COMMENT '主键',
    edit_id varchar(50) NULL COMMENT '修改记录id',
    level int(11) NULL COMMENT '级别',
    pro_name varchar(50) NULL COMMENT '属性名',
    pro_text varchar(200) NULL COMMENT '属性文本',
    new_val varchar(2000) NULL COMMENT '新值',
    old_val varchar(2000) NULL COMMENT '原始值',
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;