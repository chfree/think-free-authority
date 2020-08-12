ALTER TABLE base_authority_user DROP COLUMN is_locked, DROP COLUMN is_login;
ALTER TABLE base_authority_user ADD COLUMN status varchar(50) AFTER department_id;
update base_authority_user set status='normal';