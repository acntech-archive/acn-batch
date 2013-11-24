CREATE TABLE APP.employee
(
    id BIGINT  PRIMARY KEY NOT NULL,
    personal_number VARCHAR (255) NOT NULL,
    name VARCHAR (255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    phone varchar (255) NOT NULL,
    enterprise_id varchar (255) NOT NULL
);