-- Constraints

INSERT INTO Component (Title, Price) VALUES ('RAM 2GB', -50);
INSERT INTO Computer (Title, Additional_price) VALUES ('Dell', -50);
INSERT INTO Belonging VALUES (1, 1, 0);
INSERT INTO Customer (First_name, Last_name, Identification_code) VALUES ('Jonas', 'Jonaitis', 5);

-- Triggers

INSERT INTO Purchase (Computer, Customer, Is_deliver) VALUES (1, 1, TRUE);
INSERT INTO Customer (First_name, Last_name) VALUES ('Antanas', 'Antanaitis');

