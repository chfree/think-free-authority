-- 添加字段
ALTER TABLE base_param_setting ADD COLUMN `val_type` varchar(10) COMMENT '值类型' AFTER `comments`;
-- 修改字段长度
ALTER TABLE base_param_setting CHANGE COLUMN `param_value` `param_value` varchar(4000) DEFAULT NULL COMMENT '参数值';