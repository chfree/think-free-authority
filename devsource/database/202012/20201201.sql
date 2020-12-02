ALTER TABLE `base_file_bsn` ADD COLUMN `seq_index` int DEFAULT 0 COMMENT '顺序索引' AFTER `display_name`;

-- 找出第一个不连续的数字
-- 改进了，当没有第一个的时候，会查出第一个，确定第一个，要改写union select中的-1为第一个减去1
select t1.seq_index+1
from (select tt.seq_index,tt.bsn_id from base_file_bsn tt where tt.bsn_id='34996e24-0a8f-11eb-bdef-5cd0fe8cbf19' union select -1,'34996e24-0a8f-11eb-bdef-5cd0fe8cbf19') t1
where not exists
      (select t2.seq_index from (select tt.seq_index from base_file_bsn tt where tt.bsn_id='34996e24-0a8f-11eb-bdef-5cd0fe8cbf19' union select -1) t2  where t2.seq_index=t1.seq_index+1)
and bsn_id='34996e24-0a8f-11eb-bdef-5cd0fe8cbf19'
order by t1.seq_index
limit 1