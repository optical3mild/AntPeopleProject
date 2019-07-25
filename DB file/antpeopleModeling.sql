SET SESSION FOREIGN_KEY_CHECKS=0;

CREATE SCHEMA IF NOT EXISTS `antpeople` DEFAULT CHARACTER SET utf8;
use `antpeople`;

/* Drop Tables */

DROP TABLE IF EXISTS bbs;
DROP TABLE IF EXISTS notice;
DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS role;
DROP TABLE IF EXISTS store;

/* Create Tables */

CREATE TABLE bbs
(
	bbs_id int NOT NULL AUTO_INCREMENT,
	title varchar(50) NOT NULL,
	description varchar(255) NOT NULL,
	created_time datetime DEFAULT CURRENT_TIMESTAMP NOT NULL,
	updated_time datetime DEFAULT CURRENT_TIMESTAMP NOT NULL,
	state int NOT NULL,
	user_id int NOT NULL,
	PRIMARY KEY (bbs_id)
);


CREATE TABLE notice
(
	notice_id int NOT NULL AUTO_INCREMENT,
	title varchar(50) NOT NULL,
	description varchar(255) NOT NULL,
	created_time datetime DEFAULT CURRENT_TIMESTAMP NOT NULL,
	updated_time datetime DEFAULT CURRENT_TIMESTAMP NOT NULL,
	state int NOT NULL,
	user_id int NOT NULL,
	PRIMARY KEY (notice_id)
);


CREATE TABLE role
(
	role_id int DEFAULT 100 NOT NULL,
	role varchar(15) NOT NULL,
	PRIMARY KEY (role_id)
);




CREATE TABLE store
(
	store_id int DEFAULT 101 NOT NULL,
	store varchar(15) NOT NULL,
	PRIMARY KEY (store_id)
);


CREATE TABLE user
(
	user_id int NOT NULL AUTO_INCREMENT,
	email varchar(45) NOT NULL,
	password varchar(255) NOT NULL,
	name varchar(15) NOT NULL,
	created_time datetime DEFAULT CURRENT_TIMESTAMP NOT NULL,
	updated_time datetime DEFAULT CURRENT_TIMESTAMP NOT NULL,
	state int,
	store_id int NOT NULL,
	role_id int NOT NULL,
	PRIMARY KEY (user_id),
	UNIQUE (email)
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

ALTER TABLE bbs
	ADD FOREIGN KEY (user_id)
	REFERENCES user (user_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE notice
	ADD FOREIGN KEY (user_id)
	REFERENCES user (user_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;
