------------------------------------------
------------------------------------------
--회원정보
------------------------------------------
------------------------------------------
--DROP SEQUENCE SDUSEQ;
--DROP TABLE SDUSER cascade constraint;

CREATE SEQUENCE SDUSEQ;

CREATE TABLE SDUSER
(
    SDUSEQ          NUMBER           NOT NULL, 
    SDUEMAIL        VARCHAR2(100)    NOT NULL, 
    SDUPW           VARCHAR2(100)    NOT NULL, 
    SDUNAME         VARCHAR2(200)    NULL, 
    SDUSEX          VARCHAR2(5)      NULL, 
    SDUNICK         VARCHAR2(200)    NULL, 
    SDUDOB          DATE             NULL, 
    SDUDEACT        VARCHAR2(5)      NULL, 
    SDUGRADE        VARCHAR2(20)     NULL, 
    SDUDEACTDATE    DATE             NULL, 
    SDUREGDATE      DATE             NULL, 
  	SDUIMGPATH      VARCHAR2(4000)   NULL,
    CONSTRAINT SDUSER_PK PRIMARY KEY (SDUEMAIL)
);
/

ALTER TABLE SDUSER MODIFY SDUIMGPATH VARCHAR2(4000) default '/semi/resources/img/user/padak.jpg';


INSERT INTO SDUSER VALUES
(SDUSEQ.NEXTVAL, 'abc123@gmail.com', '1q2w3e4!', 'abc초콜릿', 'M', '초코초코', TO_DATE('20101010'), 'N', 'U', SYSDATE+730000, SYSDATE, default);
INSERT INTO SDUSER VALUES
(SDUSEQ.NEXTVAL, 'abc456@gmail.com', '1234', 'abc초콜릿', 'M', '초코초코', '2010-10-10', 'N', 'U', null, SYSDATE);

SELECT * FROM SDUSER;
SELECT * FROM SDUSER WHERE SDUEMAIL = 'abcd1234@naver.com' AND SDUPW = '1q2w3e4r!' AND SDUDEACT = 'N';
SELECT * FROM SDUSER WHERE SDUEMAIL = 'abcd1234@naver.com';
UPDATE SDUSER SET SDUNICK=null WHERE SDUSEQ = 7;
DELETE from SDUSER WHERE SDUSEQ=23;

INSERT INTO SDUSER VALUES(SDUSEQ.NEXTVAL,'abcd1234@naver.com','1q2w3e4!','반려견','M',null,TO_DATE('19940101'), 'NO','ADMIN',SYSDATE+730000,SYSDATE, default);

COMMENT ON TABLE SDUSER IS '회원'
/

COMMENT ON COLUMN SDUSER.SDUSEQ IS '유저시퀀스'
/

COMMENT ON COLUMN SDUSER.SDUEMAIL IS '아이디'
/

COMMENT ON COLUMN SDUSER.SDUPW IS '비번'
/

COMMENT ON COLUMN SDUSER.SDUNAME IS '이름'
/

------------------------------------------
------------------------------------------
--게시판
------------------------------------------
------------------------------------------
--DROP SEQUENCE SDBSEQ;
--DROP SEQUENCE SDBGROUPSEQ;
CREATE SEQUENCE SDBSEQ;
CREATE SEQUENCE SDBGROUPSEQ;
DROP TABLE SDBOARD cascade constraint;
--글시퀀스(pk) / 탭 번호 / 그룹시퀀스 / 그룹 번호 / 상위폴더 / 차상위폴더 / 하위폴더 / 중요도
--글 제목 / 작성자(fk) / 내용 / 최종작성일 / 조회수  / 자주보는 글 / 썸네일경로 / 파일 이름 / 파일 경로 
CREATE TABLE SDBOARD
(
    SDBSEQ          NUMBER            NOT NULL, 
    SDBTABNO        NUMBER            NULL, 
    SDBGROUPSEQ     NUMBER            NULL, 
    SDBGROUPNO      NUMBER            NULL, 
    SDBFIRSTFOLDER  VARCHAR2(200)     NOT NULL,
    SDBSECONDFOLDER VARCHAR2(200)     NULL,
    SDBTHIRDFOLDER  VARCHAR2(200)     NULL,
    SDBIMPORTANCE   NUMBER            NULL,
    SDBTITLE        VARCHAR2(200)     NULL, 
    SDUEMAIL        VARCHAR2(100)     NOT NULL, 
    SDBCONTENT      CLOB		      NULL, 
    SDBREGDATE      DATE              NULL, 
    SDBVIEWS        NUMBER            NULL, 
    SDBLIKE         NUMBER            NULL, 
    SDBTHUMBNAIL 	VARCHAR2(2000)	  NULL,
    SDBIMGPATH		VARCHAR2(2000)	  NULL,
    SDBFILENAME     VARCHAR2(200)     NULL, 
    SDBFILEPATH     VARCHAR2(2000)    NULL,
    CONSTRAINT SDBOARD_PK PRIMARY KEY (SDBSEQ)
);
/

COMMENT ON TABLE SDBOARD IS '게시판'
/

COMMENT ON COLUMN SDBOARD.SDBSEQ IS '글시퀀스'
/

--외래키 끊기
--ALTER TABLE SDBOARD DROP CONSTRAINT FK_SDBOARD_SDUEMAIL_SDUSER_SDU;

--외래키 추가
ALTER TABLE SDBOARD
    ADD CONSTRAINT FK_SDBOARD_SDUEMAIL_SDUSER_SDU FOREIGN KEY (SDUEMAIL)
        REFERENCES SDUSER (SDUEMAIL);
/

--디폴트값 넣기
ALTER TABLE SDBOARD MODIFY (SDBFIRSTFOLDER default 'default');

--INSERT 샘플 
INSERT INTO SDBOARD VALUES(SDBSEQ.NEXTVAL, 1, SDBGROUPSEQ.NEXTVAL, 1, 'default', null, null, null, '테스트 01', 'abcd1234@naver.com', '====테스트 글====', sysdate, 0, 0, null, null, null, null);

--insert mapper insertwrite
insert into SDBOARD(SDBSEQ,SDBTABNO,SDBGROUPSEQ,SDBGROUPNO,SDBTITLE,SDUEMAIL,SDBCONTENT,SDBREGDATE,SDBTHUMBNAIL,SDBIMGPATH,SDBFILENAME,SDBFILEPATH) 
values(SDBSEQ.NEXTVAL,1,1,1,'db테스트01','abcd1234@naver.com','db에서 테스트중...01',SYSDATE,'null-able','null-able','null-able','null-able');

--insert mapper insertImg
insert into SDBOARD(SDBSEQ,SDBFIRSTFOLDER,SDUEMAIL,SDBTHUMBNAIL,SDBIMGPATH) 
values(SDBSEQ.NEXTVAL,#{sdbfirstfolder},#{sduemail},#{sdbthumbnail},#{sdbimgpath})

--insert mapper updateBoardYesImg
update SDBOARD set SDBTITLE=#{sdbtitle},SDBCONTENT=#{sdbcontent},SDBFILENAME=#{sdbfilename},SDBFILEPATH=#{sdbfilepath} 
where SDBSEQ =#{sdbseq}

--select mapper getBoardNo
select SDBSEQ from SDBOARD where SDUEMAIL='abcd1234@naver.com' and SDBTHUMBNAIL='/semi/resources/img/board/img\\abcd1234@naver.com\2020\06\01/s/s_c11d4ee8-cfbe-4952-9430-c333b5206135_board_0.jpeg';

SELECT * FROM SDBOARD;
------------------------------------------
------------------------------------------
--댓글
------------------------------------------
------------------------------------------
CREATE SEQUENCE SDRESEQ;
--댓글 번호(PK) / 글 번호(FK) / 댓글 제목 / 댓글 내용 / 글 작성자(FK)
CREATE TABLE SDREPLY
(
	SDRESEQ			NUMBER			NOT NULL,
	SDBSEQ			NUMBER			NOT NULL,
	SDRETITLE		VARCHAR2(200)	NULL,
	SDRECONTENT		VARCHAR2(1000)  NOT NULL,
	SDUEMAIL		VARCHAR2(100)   NULL,
	CONSTRAINT SDREPLY_PK PRIMARY KEY (SDRESEQ)
);

ALTER TABLE SDREPLY
    ADD CONSTRAINT FK_SDREPLY_SDBSEQ_SDBOARD_SDB FOREIGN KEY (SDBSEQ)
        REFERENCES SDBOARD (SDBSEQ);
/

ALTER TABLE SDREPLY
    ADD CONSTRAINT FK_SDREPLY_SDUEMAIL_SDUSER_SDU FOREIGN KEY (SDUEMAIL)
        REFERENCES SDUSER (SDUEMAIL);
/

------------------------------------------
------------------------------------------
--달력일정
------------------------------------------
------------------------------------------

CREATE SEQUENCE SDCSEQ;

CREATE TABLE SDCALENDAR
(
    SDCSEQ         NUMBER            NOT NULL, 
    SDCTITLE       VARCHAR2(200)     NULL, 
    SDCCONTENT     VARCHAR2(4000)    NULL, 
    SDCSARTDATE    DATE              NULL, 
    SDCENDDATE     DATE              NULL, 
    SDUEAMIL       VARCHAR2(100)     NOT NULL, 
    CONSTRAINT SDCALENDAR_PK PRIMARY KEY (SDCSEQ)
);
/

COMMENT ON TABLE SDCALENDAR IS '일정_달력'
/

COMMENT ON COLUMN SDCALENDAR.SDCSEQ IS '일정시퀀스'
/

COMMENT ON COLUMN SDCALENDAR.SDCTITLE IS '일정제목'
/

COMMENT ON COLUMN SDCALENDAR.SDCCONTENT IS '일정내용'
/


ALTER TABLE SDCALENDAR
    ADD CONSTRAINT FK_SDCALENDAR_SDUEAMIL_SDUSER_ FOREIGN KEY (SDUEAMIL)
        REFERENCES SDUSER (SDUEAMIL);
/