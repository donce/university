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


-- Initial purchase price is remembered
CREATE FUNCTION PurchasePrice()
RETURNS "trigger" AS $$
BEGIN
	NEW.Price := (SELECT price FROM ComputerPrice WHERE ID = NEW.Computer);
	RETURN NEW;
END;
$$
LANGUAGE 'plpgsql';

CREATE TRIGGER PurchasePrice
BEFORE INSERT OR UPDATE OF Computer ON Purchase
	FOR EACH ROW
		EXECUTE PROCEDURE PurchasePrice();
