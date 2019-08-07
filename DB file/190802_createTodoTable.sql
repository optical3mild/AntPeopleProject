SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */
DROP TABLE IF EXISTS user_todo;
DROP TABLE IF EXISTS todo;


/* Create Tables */

CREATE TABLE todo
(
	todo_id int NOT NULL AUTO_INCREMENT,
	description varchar(255) NOT NULL,
	created_time datetime DEFAULT CURRENT_TIMESTAMP,
	updated_time datetime DEFAULT CURRENT_TIMESTAMP,
	state boolean DEFAULT false NOT NULL,
	checkPerson int DEFAULT 0,
	from_id int NOT NULL,
	PRIMARY KEY (todo_id)
);

CREATE TABLE user_todo
(
	to_id int NOT NULL,
	todo_id int NOT NULL,
	state boolean DEFAULT false NOT NULL
);



/* Create Foreign Keys */

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



