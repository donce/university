CREATE VIEW ComputerComponent AS
	SELECT Computer as Computer_ID, Computer.Title as Computer, Component as Component_ID, Manufacturer as Component_Manufacturer, Component.Title as Component, Price*Count as Total_price
	FROM Belonging
	JOIN Component ON (Belonging.Component = Component.ID)
	JOIN Computer ON (Belonging.Computer = Computer.ID);
 
CREATE VIEW ComputerPrice AS
	SELECT id, Title, Description, Sum as Components_price, Additional_price, Sum+Additional_price AS Price
	FROM (
		SELECT Computer, SUM(Price*Count)
		FROM Belonging
		JOIN Component ON (Belonging.Component = Component.ID)
		GROUP BY Computer
	) AS ComponentsPrice
	JOIN Computer ON (Computer.ID = Computer);

