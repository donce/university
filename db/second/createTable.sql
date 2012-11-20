CREATE TABLE doku9900.Component
(
	ID           SERIAL         NOT NULL PRIMARY KEY,
	Title        VARCHAR(30)    NOT NULL,
	Manufacturer VARCHAR(30),
	Price        NUMERIC(10, 2) DEFAULT 0.00
);

CREATE TABLE doku9900.Belonging
(
	Computer  SMALLINT NOT NULL,
	Component SMALLINT NOT NULL,
	Count     SMALLINT NOT NULL,

	PRIMARY KEY (Computer, Component)
);

CREATE TABLE doku9900.Computer
(
	ID               SERIAL       NOT NULL PRIMARY KEY,
	Title            VARCHAR(100) NOT NULL,
	Description      VARCHAR(254),
	Additional_price NUMERIC(10, 2) DEFAULT 0.00
);

CREATE TABLE doku9900.Purchase
(
	ID         SERIAL   NOT NULL PRIMARY KEY,
	Computer   SMALLINT NOT NULL,
	Customer   SMALLINT NOT NULL,
	Date       TIME     NOT NULL DEFAULT now(),
	Is_deliver BOOLEAN
);

CREATE TABLE doku9900.Customer
(
	ID                  SERIAL      NOT NULL PRIMARY KEY,
	First_name          VARCHAR(30) NOT NULL,
	Last_name           VARCHAR(30) NOT NULL,
	Identification_code CHAR(11),
	Birthday            DATE,
	Address             VARCHAR(100)
);
