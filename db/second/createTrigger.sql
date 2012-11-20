--Bendra kaina > 0
--Turi asmens koda arba gimimo data

--sukuriant asmens koda - sukurti data?
--patikrinti, ar AK su gimimo data sutampa?

--jei deliver, kad būtų adresas
CREATE FUNCTION DeliveryAddress()
RETURNS "trigger" AS $$
BEGIN
		IF NEW.Is_deliver AND (SELECT Address FROM Customer WHERE Customer.ID = NEW.Customer) IS NULL
		THEN
			RAISE EXCEPTION 'Delivery address is unknown';
		END IF;
		RETURN NEW;
END;
$$
LANGUAGE 'plpgsql';

CREATE TRIGGER DeliveryAddress
BEFORE INSERT OR UPDATE OF Is_deliver ON Purchase
	FOR EACH ROW
		EXECUTE PROCEDURE DeliveryAddress();
