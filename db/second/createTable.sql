CREATE TABLE Component
(
	ID           SERIAL         NOT NULL PRIMARY KEY,
	Title        VARCHAR(30)    NOT NULL,
	Manufacturer VARCHAR(30),
	Price        NUMERIC(10, 2) NOT NULL DEFAULT 0.00 CHECK (Price >= 0.00)
);

CREATE TABLE Computer
(
	ID               SERIAL       NOT NULL PRIMARY KEY,
	Title            VARCHAR(100) NOT NULL,
	Description      VARCHAR(254),
	Additional_price NUMERIC(10, 2) DEFAULT 0.00 CHECK (Additional_price >= 0.00)
);

CREATE TABLE Belonging
(
	Computer  SMALLINT NOT NULL REFERENCES Computer
								ON DELETE CASCADE,
	Component SMALLINT NOT NULL REFERENCES Component
								ON DELETE RESTRICT,
	Count     SMALLINT NOT NULL CHECK (Count >= 1),

	PRIMARY KEY (Computer, Component)
);

CREATE TABLE Customer
(
	ID                  SERIAL      NOT NULL PRIMARY KEY,
	First_name          VARCHAR(30) NOT NULL,
	Last_name           VARCHAR(30) NOT NULL,
	Identification_code CHAR(11),
	Birthday            DATE,
	Address             VARCHAR(100)
);

CREATE TABLE Purchase
(
	ID         SERIAL   NOT NULL PRIMARY KEY,
	Computer   SMALLINT NOT NULL REFERENCES Computer
								 ON DELETE SET NULL,
	Customer   SMALLINT NOT NULL REFERENCES Customer
								 ON DELETE SET NULL,
	Date       TIMESTAMP     NOT NULL DEFAULT now(),
	Is_deliver BOOLEAN  NOT NULL DEFAULT FALSE
);
