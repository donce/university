--Bendra kaina > 0
--Turi asmens koda arba gimimo data

--sukuriant asmens koda - sukurti data?
--patikrinti, ar AK su gimimo data sutampa?

-- Customer with deliver has address
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

CREATE FUNCTION CustomerIdentificationCodeBirthday()
RETURNS "trigger" AS $$
BEGIN
	IF NEW.Identification_code IS NOT NULL THEN
		
	END IF;
	RETURN NEW;
END;
$$
LANGUAGE 'plpgsql';

CREATE TRIGGER CustomerIdentificationCodeBirthday
BEFORE INSERT OR UPDATE OF Identification_code, Birthday ON Customer
	FOR EACH ROW
		EXECUTE PROCEDURE CustomerIdentificationCodeBirthday();
