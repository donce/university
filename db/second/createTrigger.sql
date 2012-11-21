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



-- Customer have at least one of Identification_code and Birthday
CREATE FUNCTION CustomerCheck()
RETURNS "trigger" AS $$
BEGIN
	IF NEW.Identification_code IS NULL AND NEW.Birthday IS NULL THEN
		RAISE EXCEPTION 'Customer has to have at least Identification_code or Birthday';
	END IF;
	RETURN NEW;
END;
$$
LANGUAGE 'plpgsql';

CREATE TRIGGER CustomerCheck
BEFORE INSERT OR UPDATE OF Identification_code, Birthday ON Customer
	FOR EACH ROW
		EXECUTE PROCEDURE CustomerCheck();



-- Initial purchase price is remembered
CREATE FUNCTION PurchasePrice()
RETURNS "trigger" AS $$
BEGIN
	IF NEW.Computer IS NOT NULL THEN
		NEW.Price := (SELECT price FROM ComputerPrice WHERE ID = NEW.Computer);
	END IF;
	RETURN NEW;
END;
$$
LANGUAGE 'plpgsql';

CREATE TRIGGER PurchasePrice
BEFORE INSERT OR UPDATE OF Computer ON Purchase
	FOR EACH ROW
		EXECUTE PROCEDURE PurchasePrice();
