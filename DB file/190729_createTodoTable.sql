use antpeople;

DROP TABLE IF EXISTS user_todo;
DROP TABLE IF EXISTS todo;

CREATE TABLE todo
(
	todo_id int NOT NULL AUTO_INCREMENT,
	description varchar(255) NOT NULL,
	created_time datetime DEFAULT CURRENT_TIMESTAMP,
	updated_time datetime DEFAULT CURRENT_TIMESTAMP,
	state boolean NOT NULL,
	from_id int NOT NULL,
	PRIMARY KEY (todo_id)
);

CREATE TABLE user_todo
(
	to_id int NOT NULL,
	todo_id int NOT NULL
);

ALTER TABLE user_todo
	ADD FOREIGN KEY (todo_id)
	REFERENCES todo (todo_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;

ALTER TABLE todo
	ADD FOREIGN KEY (from_id)
	REFERENCES user (user_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;

ALTER TABLE user_todo
	ADD FOREIGN KEY (to_id)
	REFERENCES user (user_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;

select * from `role`;
select * from `store`;
select * from `user`;
select * from `bbs`;
select * from `notice`;
select * from `schedule`;
select * from `user_sche`;
