import java.io.Closeable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
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
	PreparedStatement statementInsertComponent;
	PreparedStatement statementSelectComponents;
	PreparedStatement statementSelectComputers;

	public Shop(Connection connection) throws SQLException {
		statementInsertComponent = connection.prepareStatement("INSERT INTO Component(Title, Manufacturer, Price) VALUES (?, ?, ?);");
		statementSelectComponents = connection.prepareStatement("SELECT id, title, manufacturer, price FROM Component;");
		statementSelectComputers = connection.prepareStatement("SELECT id, title, description, price FROM ComputerPrice;");
	}
	
	public void close() {
		try {
			statementInsertComponent.close();
			statementSelectComponents.close();
			statementSelectComputers.close();
		} catch (SQLException e) {
			System.out.println("Failed closing prepared statements.");
			e.printStackTrace();
		}
	}

	public void buy() {
//		ResultSet rs = executeQuery("SELECT * FROM Component;");
//		if (rs != null) {
//			try {
//				rs.close();
//			} catch (SQLException e) {
//				System.out.println("Error while closing result set!");
//			}
//		}
	}
	
	public void addComponent(String title, String manufacturer, int price) {
		
	}
	
//	public void register(String firstName, String lastName, Number identification_code?, Birthday?, Address?)
	
	public void findCustomer(String firstName, String lastName) {
		
	}
	
	public void addComputer(String title, String description, int additionalPrice) {
		
	}
	
	public void printComponents() throws SQLException {
		printResultSet(statementSelectComponents.executeQuery());
	}
	
	public void printComputers() throws SQLException {
		printResultSet(statementSelectComputers.executeQuery());
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
