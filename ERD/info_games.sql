/* Drop Tables */

DROP TABLE FREEBOARD CASCADE CONSTRAINTS;
DROP TABLE REVIEWBOARD CASCADE CONSTRAINTS;
DROP TABLE TIPBOARD CASCADE CONSTRAINTS;
DROP TABLE ACCOUNT CASCADE CONSTRAINTS;
DROP TABLE BOARDFILE CASCADE CONSTRAINTS;
DROP TABLE BOARDID CASCADE CONSTRAINTS;
DROP TABLE WRITEID CASCADE CONSTRAINTS;

/* Drop Sequences */

DROP SEQUENCE ACCOUNT_SEQ;
DROP SEQUENCE BOARD_ID_SEQ;
DROP SEQUENCE WRITE_ID_SEQ;
DROP SEQUENCE FILE_ID_SEQ;

/* Ceate Sequence */

CREATE SEQUENCE ACCOUNT_SEQ;
CREATE SEQUENCE BOARD_ID_SEQ;
CREATE SEQUENCE WRITE_ID_SEQ;
CREATE SEQUENCE FILE_ID_SEQ;

/* Create Tables */

CREATE TABLE ACCOUNT
(
	ACCNUM number NOT NULL,
	ID varchar2(20) NOT NULL UNIQUE,
	PW varchar2(30) NOT NULL,
	NAME varchar2(30) NOT NULL,
	EMAIL varchar2(30) NOT NULL,
	BIRTH date NOT NULL,
	GENDER varchar2(20) NOT NULL,
	REGDATE date DEFAULT SYSDATE NOT NULL,
	NICKNAME varchar2(20) NOT NULL UNIQUE,
	PRIMARY KEY (ACCNUM)
);


CREATE TABLE BOARDID
(
	BOARD_ID number NOT NULL,
	BOARD_NAME varchar2(20) NOT NULL UNIQUE,
	PRIMARY KEY (BOARD_ID)
);


CREATE TABLE BOARDFILE
(
	FILE_ID number NOT NULL,
	SYSTEMNAME varchar2(50) NOT NULL UNIQUE,
	ORIGINALNAME varchar2(50) NOT NULL,
	FILESEQ number NOT NULL,
	BOARD_ID number NOT NULL,
	WRITE_ID number NOT NULL,
	PRIMARY KEY (FILE_ID)
);


CREATE TABLE FREEBOARD
(
	ACCNUM number NOT NULL,
	BOARD_ID number NOT NULL,
	WRITE_ID number NOT NULL,
	PRIMARY KEY (WRITE_ID)
);


CREATE TABLE REVIEWBOARD
(
	ACCNUM number NOT NULL,
	BOARD_ID number NOT NULL,
	WRITE_ID number NOT NULL,
	RATING number NOT NULL,
	PRIMARY KEY (WRITE_ID)
);


CREATE TABLE TIPBOARD
(
	ACCNUM number NOT NULL,
	BOARD_ID number NOT NULL,
	WRITE_ID number NOT NULL,
	PRIMARY KEY (WRITE_ID)
);


CREATE TABLE WRITEID
(
	WRITE_ID number NOT NULL,
	SUBJECT varchar2(500) NOT NULL,
	CONTENT clob NOT NULL,
	REGDATE date DEFAULT SYSDATE NOT NULL,
	VIEWCNT NUMBER DEFAULT 0 NOT NULL,
	PRIMARY KEY (WRITE_ID)
);

/* Create Foreign Keys */

ALTER TABLE FREEBOARD
	ADD FOREIGN KEY (ACCNUM)
	REFERENCES ACCOUNT (ACCNUM)
;


ALTER TABLE REVIEWBOARD
	ADD FOREIGN KEY (ACCNUM)
	REFERENCES ACCOUNT (ACCNUM)
;


ALTER TABLE TIPBOARD
	ADD FOREIGN KEY (ACCNUM)
	REFERENCES ACCOUNT (ACCNUM)
;


ALTER TABLE BOARDFILE
	ADD FOREIGN KEY (BOARD_ID)
	REFERENCES BOARDID (BOARD_ID)
;


ALTER TABLE FREEBOARD
	ADD FOREIGN KEY (BOARD_ID)
	REFERENCES BOARDID (BOARD_ID)
;


ALTER TABLE REVIEWBOARD
	ADD FOREIGN KEY (BOARD_ID)
	REFERENCES BOARDID (BOARD_ID)
;


ALTER TABLE TIPBOARD
	ADD FOREIGN KEY (BOARD_ID)
	REFERENCES BOARDID (BOARD_ID)
;


ALTER TABLE BOARDFILE
	ADD FOREIGN KEY (WRITE_ID)
	REFERENCES WRITEID (WRITE_ID)
;


ALTER TABLE FREEBOARD
	ADD FOREIGN KEY (WRITE_ID)
	REFERENCES WRITEID (WRITE_ID)
;


ALTER TABLE REVIEWBOARD
	ADD FOREIGN KEY (WRITE_ID)
	REFERENCES WRITEID (WRITE_ID)
;


ALTER TABLE TIPBOARD
	ADD FOREIGN KEY (WRITE_ID)
	REFERENCES WRITEID (WRITE_ID)
;

INSERT INTO BOARDID VALUES (1, '자유게시판');
INSERT INTO BOARDID VALUES (2, '팁게시판');
INSERT INTO BOARDID VALUES (3, '리뷰게시판');

--------------------------------------------------------------------------------------

INSERT INTO ACCOUNT (ACCNUM, ID, PW, NAME, EMAIL, BIRTH, GENDER, NICKNAME) VALUES (ACCOUNT_SEQ.nextval, 'TEST01', '1234', '김민준', 'asd1234@naver.com', '1995-04-11', '남자', '곰돌이');
INSERT INTO ACCOUNT (ACCNUM, ID, PW, NAME, EMAIL, BIRTH, GENDER, NICKNAME) VALUES (ACCOUNT_SEQ.nextval, 'TEST02', '1234', '박서준', 'ZXCX1234@naver.com', '1999-04-11', '남자', '메갓');
INSERT INTO ACCOUNT (ACCNUM, ID, PW, NAME, EMAIL, BIRTH, GENDER, NICKNAME) VALUES (ACCOUNT_SEQ.nextval, 'mysoul', '1234', '김수진', 'travelgo@naver.com', '1999-04-11', '여자', '토끼');
INSERT INTO ACCOUNT (ACCNUM, ID, PW, NAME, EMAIL, BIRTH, GENDER, NICKNAME) VALUES (ACCOUNT_SEQ.nextval, 'mysoul2', '1234', '김수레', 'ironman@naver.com', '1999-04-11', '여자', '피자만먹음');

--------------------------------------------------------------------------------------

INSERT INTO WRITEID (WRITE_ID, SUBJECT, CONTENT) VALUES (WRITE_ID_SEQ.nextval, '배틀그라운드 다운로드 어디서 받나요?????', '배틀그라운드 다운로드 어디서 받나요???');

INSERT INTO FREEBOARD VALUES (1, 1, WRITE_ID_SEQ.currval);

INSERT INTO WRITEID (WRITE_ID, SUBJECT, CONTENT) VALUES (WRITE_ID_SEQ.nextval, '배틀그라운드 모바일 조작 팁 알려드립니다 !', '많이 해보세요 ^^');

INSERT INTO TIPBOARD VALUES (1, 2, WRITE_ID_SEQ.currval);

INSERT INTO WRITEID (WRITE_ID, SUBJECT, CONTENT) VALUES (WRITE_ID_SEQ.nextval, '배틀그라운드 모바일 갓겜이네요 ㄷㄷ', '갓겜ㅇㅈ');

INSERT INTO REVIEWBOARD VALUES (1, 3, WRITE_ID_SEQ.currval, 5);





INSERT INTO WRITEID (WRITE_ID, SUBJECT, CONTENT) VALUES (WRITE_ID_SEQ.nextval, '모바일 게임 잘 돌아가는 폰 추천좀요 ~', 'ㅇㅇ');

INSERT INTO FREEBOARD VALUES (2, 1, WRITE_ID_SEQ.currval);

INSERT INTO WRITEID (WRITE_ID, SUBJECT, CONTENT) VALUES (WRITE_ID_SEQ.nextval, '모바일 게임 팁 드림', '손 바꾸셈');

INSERT INTO TIPBOARD VALUES (2, 2, WRITE_ID_SEQ.currval);

INSERT INTO WRITEID (WRITE_ID, SUBJECT, CONTENT) VALUES (WRITE_ID_SEQ.nextval, '쿵야 캐치마인드 노잼 ;;', 'ㄵ');

INSERT INTO REVIEWBOARD VALUES (2, 3, WRITE_ID_SEQ.currval, 1);

INSERT INTO WRITEID (WRITE_ID, SUBJECT, CONTENT) VALUES (WRITE_ID_SEQ.nextval, '오늘 저녁 뭐 먹지?', '추천ㄴ좀');

INSERT INTO FREEBOARD VALUES (3, 1, WRITE_ID_SEQ.currval);

INSERT INTO WRITEID (WRITE_ID, SUBJECT, CONTENT) VALUES (WRITE_ID_SEQ.nextval, '쿵야 캐치마인드 어떻게 연습함?', '연습 방법좀');

INSERT INTO TIPBOARD VALUES (3, 2, WRITE_ID_SEQ.currval);

INSERT INTO WRITEID (WRITE_ID, SUBJECT, CONTENT) VALUES (WRITE_ID_SEQ.nextval, '테라 클래식 돈에 미쳤나봄 ;; ', 'ㅇㅇ');

INSERT INTO REVIEWBOARD VALUES (3, 3, WRITE_ID_SEQ.currval, 3);

INSERT INTO WRITEID (WRITE_ID, SUBJECT, CONTENT) VALUES (WRITE_ID_SEQ.nextval, '피자는 미스터 피자^^', 'ㅇㅁㅇㄴㅁㅇ');

INSERT INTO FREEBOARD VALUES (4, 1, WRITE_ID_SEQ.currval);

INSERT INTO WRITEID (WRITE_ID, SUBJECT, CONTENT) VALUES (WRITE_ID_SEQ.nextval, '팁은 무슨 팁이야 대충해', 'ㅎㅎ');

INSERT INTO TIPBOARD VALUES (4, 2, WRITE_ID_SEQ.currval);

INSERT INTO WRITEID (WRITE_ID, SUBJECT, CONTENT) VALUES (WRITE_ID_SEQ.nextval, '브롤스타즈 진짜 왜케 재밌냐?', 'ㅎㅎ');

INSERT INTO REVIEWBOARD VALUES (4, 3, WRITE_ID_SEQ.currval, 5);
