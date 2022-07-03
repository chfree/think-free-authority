create table bhv_web_visit_limit (
    id varchar(50) NOT NULL COMMENT '主键',
    visit_type varchar(50) NULL COMMENT '访问类型',
    limit_type varchar(50) NULL COMMENT '控制类型',
    matcher_type varchar(50) NULL COMMENT '匹配类型',
    matcher_rule varchar(50) NULL COMMENT '匹配规则',
    max_count int(11) NULL COMMENT '最大数量',
    status varchar(10) NULL COMMENT '状态',
    enable_dt date  NULL COMMENT '启用日期',
    priority int(11) NULL COMMENT '优先级',
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;


create table bhv_web_visit_log (
    id varchar(50) NOT NULL COMMENT '主键',
    trace_id varchar(50) NULL COMMENT '全局跟踪号',
    start_dt datetime  NULL COMMENT '开始时间',
    end_dt datetime  NULL COMMENT '结束时间',
    url varchar(200) NULL COMMENT '地址',
    duration bigint NULL COMMENT '时长',
    ip varchar(60) NULL COMMENT 'ip地址',
    type varchar(30) NULL COMMENT '日志类型',
    bsn_id varchar(50) NULL COMMENT '业务id',
    user_id varchar(50) NULL COMMENT '用户id',
    role_id varchar(50) NULL COMMENT '角色id',
    mark1 varchar(50) NULL COMMENT '标志1',
    mark2 varchar(50) NULL COMMENT '标志2',
    mark3 varchar(500) NULL COMMENT '标志3',
    mark4 varchar(4000) NULL COMMENT '标志4',
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;