ALTER TABLE `product` ADD COLUMN `size` INT NULL AFTER `name`;
update product set size = 1;
ALTER TABLE bzorder ADD COLUMN optName VARCHAR(50) AFTER userName;