ALTER TABLE `think_free`.`base_authority_user_role`
DROP COLUMN `create_date`,
DROP COLUMN `create_user_id`,
DROP COLUMN `create_user_name`,
DROP COLUMN `delete_mark`;

ALTER TABLE `think_free`.`base_authority_group_func`
DROP COLUMN `create_date`,
DROP COLUMN `create_user_id`,
DROP COLUMN `create_user_name`;

ALTER TABLE `think_free`.`base_authority_role_func`
DROP COLUMN `create_date`,
DROP COLUMN `create_user_id`,
DROP COLUMN `create_user_name`;