ALTER TABLE `antpeople`.`user_sche` 
ADD COLUMN `start_time` VARCHAR(10) NULL AFTER `state`,
ADD COLUMN `end_time` VARCHAR(10) NULL AFTER `start_time`;