SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS bbs;
DROP TABLE IF EXISTS user_todo;
DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS role;
DROP TABLE IF EXISTS store;
DROP TABLE IF EXISTS todo;




/* Create Tables */

CREATE TABLE bbs
(
	bbs_id int NOT NULL,
	title varchar(50) NOT NULL,
	description varchar(255) NOT NULL,
	created_time datetime NOT NULL,
	updated_time datetime NOT NULL,
	state int NOT NULL,
	user_id int NOT NULL,
	PRIMARY KEY (bbs_id)
);


CREATE TABLE role
(
	role_id int NOT NULL,
	role varchar(15) NOT NULL,
	PRIMARY KEY (role_id)
);


CREATE TABLE store
(
	store_id int NOT NULL,
	store varchar(15) NOT NULL,
	PRIMARY KEY (store_id)
);


CREATE TABLE todo
(
	todo_id int NOT NULL AUTO_INCREMENT,
	description varchar(255) NOT NULL,
	created_time datetime,
	updated_time datetime,
	state boolean NOT NULL,
	PRIMARY KEY (todo_id)
);


CREATE TABLE user
(
	user_id int NOT NULL AUTO_INCREMENT,
	email varchar(45) NOT NULL,
	password varchar(255) NOT NULL,
	name varchar(15) NOT NULL,
	created_time datetime DEFAULT NOW(), SYSDATE() NOT NULL,
	updated_time datetime DEFAULT NOW(), SYSDATE() NOT NULL,
	state int,
	store_id int NOT NULL,
	role_id int NOT NULL,
	PRIMARY KEY (user_id),
	UNIQUE (email)
);


CREATE TABLE user_todo
(
	to_id int NOT NULL,
	todo_id int NOT NULL,
	dear_id int NOT NULL,
	PRIMARY KEY (to_id, todo_id, dear_id)
);



/* Create Foreign Keys */

ALTER TABLE user
	ADD FOREIGN KEY (role_id)
	REFERENCES role (role_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE user
	ADD FOREIGN KEY (store_id)
	REFERENCES store (store_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE user_todo
	ADD FOREIGN KEY (todo_id)
	REFERENCES todo (todo_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE bbs
	ADD FOREIGN KEY (user_id)
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


ALTER TABLE user_todo
	ADD FOREIGN KEY (dear_id)
	REFERENCES user (user_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;



