CREATE TABLE APP.SEQUENCE
(
	seq_name		VARCHAR (50)					NOT NULL,
	seq_count		DECIMAL (15)					NOT NULL
);

INSERT INTO SEQUENCE (seq_name, seq_count) values ('SEQ_GEN', 0);

CREATE TABLE APP.EMPLOYEE
(
	id				BIGINT 			PRIMARY KEY		NOT NULL,
	personalnumber	VARCHAR (255)					NOT NULL,
	name			VARCHAR (255)					NOT NULL,
	email			VARCHAR (255)					NOT NULL,
	phone			VARCHAR (255)					NOT NULL,
	enterpriseid	VARCHAR (255)					NOT NULL
);
