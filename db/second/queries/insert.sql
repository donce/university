--Components
INSERT INTO Component VALUES
(DEFAULT, 'Core i7', 'Intel', 499.99),
(DEFAULT, 'Core i5', 'Intel', 300.00),
(DEFAULT, 'Athlon II', 'AMD', 150.00),

(DEFAULT, 'Hard Drive Disk 1TB', 'Tochiba', 200.00),
(DEFAULT, 'Hard Drive Disk 500GB', 'iMicro', 100.00),

(DEFAULT, 'Black case', 'Sentey', 120.00),
(DEFAULT, 'White case', 'G-view', 100.00),

(DEFAULT, 'GeForce GTX 650 Ti', 'NVIDIA', 500.00),
(DEFAULT, 'AMD Radeon HD 7870', 'GIGABYTE', 400.00),

(DEFAULT, 'RAM 4GB', 'Corsair', 100.00);

--Computers
INSERT INTO Computer VALUES
(DEFAULT, 'Dell Ultimate', 'Powerful computer.', 100.00),
(DEFAULT, 'ASUS Economic Choice', DEFAULT, 50.00),
(DEFAULT, 'Acer Computer', DEFAULT, 50.00);

--Belongings
INSERT INTO Belonging VALUES
(1, 1, 1),
(1, 4, 1),
(1, 6, 1),
(1, 8, 1),
(1, 10, 2),

(2, 2, 1),
(2, 5, 1),
(2, 7, 1),
(2, 9, 1),
(2, 10, 1),

(3, 3, 1),
(3, 5, 1),
(3, 7, 1),
(3, 9, 1),
(3, 10, 1);

--Customers
INSERT INTO Customer(First_name, Last_name, Birthday) VALUES ('Jonas', 'Petraitis', '1980-05-24');
INSERT INTO Customer(First_name, Last_name, Identification_code, Birthday, Address) VALUES ('Antanas', 'Juozapaitis', '39005131234', '1990-05-13', 'Naugarduko 123, Vilnius');

--Purchases
INSERT INTO Purchase (Computer, Customer, Is_deliver) VALUES
(1, 1, FALSE),
(2, 2, TRUE),
(3, 2, FALSE);
