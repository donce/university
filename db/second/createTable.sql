CREATE TABLE Component
(
	ID           SERIAL         PRIMARY KEY,
	Title        VARCHAR(30)    NOT NULL,
	Manufacturer VARCHAR(30),
	Price        NUMERIC(10, 2) NOT NULL DEFAULT 0.00 CHECK (Price >= 0.00)
);

CREATE TABLE Computer
(
	ID               SERIAL       PRIMARY KEY,
	Title            VARCHAR(100) NOT NULL,
	Description      VARCHAR(254),
	Additional_price NUMERIC(10, 2) DEFAULT 0.00 CHECK (Additional_price >= 0.00)
);

CREATE TABLE Belonging
(
	Computer  SMALLINT REFERENCES Computer
					   ON DELETE CASCADE
					   ON UPDATE NO ACTION,
	Component SMALLINT REFERENCES Component
					   ON DELETE RESTRICT
					   ON UPDATE NO ACTION,
	Count     SMALLINT NOT NULL CHECK (Count >= 1),

	PRIMARY KEY (Computer, Component)
);

CREATE TABLE Customer
(
	ID                  SERIAL      PRIMARY KEY,
	First_name          VARCHAR(30) NOT NULL,
	Last_name           VARCHAR(30) NOT NULL,
	Identification_code BIGINT CHECK (Identification_code >= 10000000000 AND Identification_code <= 69999999999),
	Birthday            DATE,
	Address             VARCHAR(100),

	CHECK (Identification_code IS NOT NULL OR Birthday IS NOT NULL)
);

CREATE TABLE Purchase
(
	ID         SERIAL   PRIMARY KEY,
	Computer   SMALLINT NOT NULL REFERENCES Computer
								 ON DELETE SET NULL,
	Customer   SMALLINT NOT NULL REFERENCES Customer
								 ON DELETE SET NULL,
	Date       TIMESTAMP     NOT NULL DEFAULT now(),
	Price      NUMERIC(10, 2),
	Is_deliver BOOLEAN  NOT NULL DEFAULT FALSE
);
