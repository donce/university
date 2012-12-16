import java.io.Closeable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

/*
Add/remove component

Register customer(First_name, Last_name, Identification_code?, Birthday?, Address?)

Buy(Computer, Customer, Is_deliver)

From computer:
Add/remove component



SEARCH:
customer first/last name, with his purchases


*/

public class Shop implements Closeable {
	private PreparedStatement
		stInsertComponent,
		stInsertCustomer,
		stInsertPurchase,
		stSelectComponents,
		stSelectComputers,
		stSelectCustomers,
		stInsertComputer,
		stSelectComputerComponents;
	//TODO: sort

	public Shop(Connection connection) throws SQLException {
		stSelectComponents = connection.prepareStatement("SELECT * FROM Component;");
		stSelectComputers = connection.prepareStatement("SELECT id, title, description, price FROM ComputerPrice;");//TODO: *?
		stSelectCustomers = connection.prepareStatement("SELECT * FROM Customers;");

		stInsertCustomer = connection.prepareStatement("INSERT INTO Customer(First_name, Last_name, Identification_code, Birthday, Address) VALUES (?, ?, ?, ?, ?);");
		stInsertPurchase = connection.prepareStatement("INSERT INTO Purchase(Computer, Customer, Is_deliver) VALUES (?, ?, ?);");
		stInsertComponent = connection.prepareStatement("INSERT INTO Component(Title, Manufacturer, Price) VALUES (?, ?, ?);");
		stInsertComputer = connection.prepareStatement("INSERT INTO Computer(Title, Description, Additional_price) VALUES (?, ?, ?);");
		stSelectComputerComponents = connection.prepareStatement("SELECT Component_id AS ID, Component as Title, Component_manufacturer AS Manufacturer, Count, Total_price FROM ComputerComponent WHERE Computer_id = ?;");
	}
	
	public void close() {
		try {
			//TODO: add all
			stInsertComponent.close();
			stSelectComponents.close();
			stSelectComputers.close();
		} catch (SQLException e) {
			System.out.println("Failed closing prepared statements.");
			e.printStackTrace();
		}
	}

	public void buy(int computer, int customer, boolean deliver) throws SQLException {
		stInsertPurchase.setInt(1,  computer);
		stInsertPurchase.setInt(2,  customer);
		stInsertPurchase.setBoolean(3, deliver);
		stInsertPurchase.executeUpdate();
	}
	
	public void addComponent(String title, String manufacturer, BigDecimal price) throws SQLException {
		stInsertComponent.setString(1, title);
		stInsertComponent.setString(2, manufacturer);
		stInsertComponent.setBigDecimal(3, price);
		stInsertComponent.executeUpdate();
	}

	public void register(String firstName, String lastName, long identificationCode, Date birthday, String address) throws SQLException {
		stInsertCustomer.setString(1, firstName);
		stInsertCustomer.setString(2, lastName);
		stInsertCustomer.setLong(3, identificationCode);
		stInsertCustomer.setDate(4, birthday);
		stInsertCustomer.setString(5, address);
		stInsertCustomer.executeUpdate();
	}
	
	public void findCustomer(String firstName, String lastName) {
		
	}
	
	public void addComputer(String title, String description, BigDecimal additionalPrice) throws SQLException {
		stInsertComputer.setString(1, title);
		stInsertComputer.setString(2, description);
		stInsertComputer.setBigDecimal(3, additionalPrice);
		stInsertComputer.executeUpdate();
	}
	
	public void addComponent() {
		
	}

	public void printComponents() throws SQLException {
		printResultSet(stSelectComponents.executeQuery());
	}
	
	public void printComputerComponents(int computer) throws SQLException {
		stSelectComputerComponents.setInt(1, computer);
		printResultSet(stSelectComputerComponents.executeQuery());
	}
	
	public void printCustomers() throws SQLException {
		printResultSet(stSelectCustomers.executeQuery());
	}
	
	public void printComputers() throws SQLException {
		printResultSet(stSelectComputers.executeQuery());
	}
	
	private void printResultSet(ResultSet resultSet) throws SQLException {
		StringBuilder builder = new StringBuilder();
		ResultSetMetaData metaData = resultSet.getMetaData();
		int columns = metaData.getColumnCount();
		int[] len = new int[columns];
		for (int i = 0; i < columns; ++i)
			len[i] = metaData.getColumnLabel(i+1).length();
		ArrayList<String[]> values = new ArrayList<>();
		while (resultSet.next()) {
			String[] row = new String[columns];
			for (int i = 0; i < columns; ++i) {
				row[i] = resultSet.getString(i+1);
				if (row[i] == null)
					row[i] = "NULL";
				len[i] = Math.max(len[i], row[i].length());
			}
			values.add(row);
		}
		//titles
		for (int i = 0; i < columns; ++i) {
			if (i > 0)
				builder.append('|');
			String label = metaData.getColumnLabel(i+1);
			builder.append(' ');
			builder.append(label);
			builder.append(' ');
			for (int j = label.length(); j < len[i]; ++j)
				builder.append(' ');
		}
		builder.append('\n');
		//splitter
		for (int i = 0; i < columns; ++i) {
			if (i > 0)
				builder.append('+');
			for (int j = 0; j < len[i]+2; ++j)
				builder.append('-');
		}
		builder.append('\n');
		//data
		for (String[] row : values) {
			for (int i = 0; i < columns; ++i) {
				if (i > 0)
					builder.append('|');
				builder.append(' ');
				builder.append(row[i]);
				builder.append(' ');
				for (int j = row[i].length(); j < len[i]; ++j)
					builder.append(' ');
			}
			builder.append('\n');
		}
		System.out.println(builder.toString());
	}
}
