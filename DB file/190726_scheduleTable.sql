SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS user_sche;
DROP TABLE IF EXISTS schedule;

/* Create Tables */

CREATE TABLE schedule
(
	sche_id int NOT NULL AUTO_INCREMENT,
	sche_unique varchar(50) NOT NULL,
	created_time datetime DEFAULT CURRENT_TIMESTAMP NOT NULL,
	updated_time datetime DEFAULT CURRENT_TIMESTAMP NOT NULL,
	start_date varchar(10) NOT NULL,
	end_date varchar(10) NOT NULL,
	start_time varchar(5) NOT NULL,
	end_time varchar(5) NOT NULL,
	title varchar(50) NOT NULL,
	manpower int NOT NULL,
	peoplecount int DEFAULT 0 NOT NULL,
	-- 01 = 휴가
	-- 02 = 조퇴 
	-- 03 = 지각
	state varchar(2) DEFAULT '00' COMMENT '01 = 휴가
02 = 조퇴 
03 = 지각',
	user_id int NOT NULL,
	PRIMARY KEY (sche_id)
);

CREATE TABLE user_sche
(
	user_id int NOT NULL,
	sche_id int DEFAULT 1 NOT NULL,
	-- 1 - 승인 신청중
	-- 2 - 승인 완료
	state int DEFAULT 1 COMMENT '1 - 승인 신청중
2 - 승인 완료'
);




/* Create Foreign Keys */

ALTER TABLE user_sche
	ADD FOREIGN KEY (sche_id)
	REFERENCES schedule (sche_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;



ALTER TABLE schedule
	ADD FOREIGN KEY (user_id)
	REFERENCES user (user_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE user_sche
	ADD FOREIGN KEY (user_id)
	REFERENCES user (user_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;




