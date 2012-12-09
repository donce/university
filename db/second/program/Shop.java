import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
Add/remove component

Register customer(First_name, Last_name, Identification_code?, Birthday?, Address?)

Buy(Computer, Customer, Is_deliver)

From computer:
Add/remove component



SEARCH:
customer first/last name, with his purchases


*/

public class Shop {

	private Connection connection;
	
	public Shop(Connection connection) {
		this.connection = connection;
	}
	
	private ResultSet executeQuery(String query) {
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);
			return rs;
			
		} catch (SQLException e) {
			System.out.println("Error while executing query!");
			e.printStackTrace();
			return null;
		}
	}
	
	public void buy() {
		ResultSet rs = executeQuery("SELECT * FROM Component;");
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				System.out.println("Error while closing result set!");
			}
		}
	}
	
	public void addComponent(String title, String manufacturer, int price) {
		
	}
	
//	public void register(String firstName, String lastName, Number identification_code?, Birthday?, Address?)
	
	public void findCustomer(String firstName, String lastName) {
		
	}
	
	public void addComputer(String title, String description, int additionalPrice) {
		
	}
	
	private void printComponents() throws SQLException {
		Table table = new Table(new String[] {"ID", "Title", "Manufacturer", "Price"});
		ResultSet rs = executeQuery("SELECT id, title, manufacturer, price FROM Component;");
		while (rs.next())
			table.addRow(new String[] {Integer.toString(rs.getInt(1)), rs.getString(2), rs.getString(3), rs.getBigDecimal(4).toString()});
		table.print();
	}
	
	private void printComputers() throws SQLException {
		Table table = new Table(new String[] {"ID", "Title", "Description", "Price"});
		ResultSet rs = executeQuery("SELECT id, title, description, price FROM ComputerPrice;");
		while (rs.next())
			table.addRow(new String[] {Integer.toString(rs.getInt(1)), rs.getString(2), rs.getString(3), rs.getBigDecimal(4).toString()});
		table.print();
	}
	
}
