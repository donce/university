CREATE INDEX CustomerName ON Customer(First_name, Last_name);
CREATE INDEX CustomerIC ON Customer(Identification_code);
CREATE INDEX PurchaseCustomer ON Purchase(Customer);
CREATE UNIQUE INDEX CustomerIdentificationCode ON Customer(Identification_code);
