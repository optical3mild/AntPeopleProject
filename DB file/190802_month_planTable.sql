SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */


DROP TABLE IF EXISTS month_plan;


/* Create Tables */

CREATE TABLE month_plan
(
	user_id int NOT NULL,
	month varchar(10) NOT NULL,
	state boolean DEFAULT TRUE,
	PRIMARY KEY (user_id, month)
);

/* Create Foreign Keys */

ALTER TABLE month_plan
	ADD FOREIGN KEY (user_id)
	REFERENCES user (user_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;



