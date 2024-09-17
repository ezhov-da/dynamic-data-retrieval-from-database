CREATE TABLE SELECT_TEST(
	ID NUMBER,
	NAME VARCHAR(100),
	CREATED_DATE TIMESTAMP
);


INSERT INTO SELECT_TEST VALUES
(1, 'sadvasdvsad', CURRENT_TIMESTAMP),
(2, 'sdfbsdfb', CURRENT_TIMESTAMP),
(3, 'dfsbsdfb', CURRENT_TIMESTAMP),
(4, 'sdfbdsfb', CURRENT_TIMESTAMP),
(5, 'sdfbsdfb', CURRENT_TIMESTAMP),
(6, 'sdfbsdfb', CURRENT_TIMESTAMP)
;


select * from SELECT_TEST;





CREATE TABLE SAMPLE_DESCRIPTION(
	ID VARCHAR(50) PRIMARY KEY,
	NAME VARCHAR(1000),
	OUTPUT_MAPPING VARCHAR(1000),
	QUERY VARCHAR(1000),
	CREATED_DATE TIMESTAMP
);


select * from SAMPLE_DESCRIPTION;

INSERT INTO SAMPLE_DESCRIPTION(ID, NAME, OUTPUT_MAPPING, QUERY, CREATED_DATE) VALUES
(
'd097b1a6-04da-496f-9a37-fb61f83f23fc', 
'test',
'{"NAME":"name"}',
'SELECT NAME FROM SELECT_TEST',
CURRENT_TIMESTAMP
)
;

SELECT ID, NAME FROM SELECT_TEST;





INSERT INTO SAMPLE_DESCRIPTION(ID, NAME, OUTPUT_MAPPING, QUERY, CREATED_DATE) VALUES
(
'd097b1a6-04da-496f-9a37-fb61f83f23f4', 
'test-where',
'{"NAME":"name"}',
'SELECT NAME FROM SELECT_TEST where name like ''%'' || ? || ''%''',
CURRENT_TIMESTAMP
)
;

--DROP TABLE CUSTOMER;


--CREATE TABLE CUSTOMER (
--  ID         INT AUTO_INCREMENT,
--  FIRST_NAME VARCHAR(40) NOT NULL,
--  LAST_NAME  VARCHAR(40) NOT NULL,
--  CITY       VARCHAR(40) NULL,
--  COUNTRY    VARCHAR(40) NULL,
--  PHONE      VARCHAR(20) NULL,
--  CONSTRAINT PK_CUSTOMER PRIMARY KEY (ID)
--);


select CURRENT_DATE() 


--select * from "ORDER";


--INSERT INTO "ORDER" (ID,ORDER_DATE,CUSTOMER_ID,TOTAL_AMOUNT,ORDER_NUMBER)VALUES(1,PARSEDATETIME('Jul 4 2012 12:00:00','MMM d yyyy HH:mm:ss', 'en'),85,440.00,'542378');








select * from CUSTOMER c ;


SELECT
	c.FIRST_NAME,
	c.CITY ,
	c.COUNTRY ,
	t0.SUM_OF_TOTAL
FROM
	(
	SELECT
		o.CUSTOMER_ID,
		SUM(o.TOTAL_AMOUNT) AS SUM_OF_TOTAL
	FROM
		"ORDER" o
	GROUP BY
		o.CUSTOMER_ID 
) t0
INNER JOIN CUSTOMER c ON
	t0.CUSTOMER_ID = c.ID 
;




;