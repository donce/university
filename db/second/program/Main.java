import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
	private static BufferedReader bufferedReader;
	private static Scanner scanner;
	private static final String[] actions = {
		"Add component",
		"Add computer",
		"Register customer",
		"Find customer",
		"Buy",
		};
	
	private static void loadDriver() {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Postgresql driver class not found!");
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	private static Connection getConnection() {
		Connection connection = null;
		try {
			System.out.println("a");
			DriverManager.getConnection("");
//			DriverManager.getConnection("jdbc:postgresql://pgsql.mif/biblio", "stud", "stud");
		} catch (SQLException e) {
			System.out.println("Can't connect to server!");
			return null;
		}
		return connection;
		
	}
	
	private static String readString() {
		try {
			return bufferedReader.readLine();
		} catch (IOException e) {
			throw new RuntimeException();
		}
	}
	
	private static int readInt() {
		return scanner.nextInt();
	}
	
	private static int readPrice() {
		return (int)(scanner.nextFloat()*100);
	}
	
	public static void main(String[] args) {
		loadDriver();
		Connection connection = getConnection();
//		if (connection != null) {
			Shop shop = new Shop(connection);
			InputStreamReader streamReader = new InputStreamReader(System.in);
			bufferedReader = new BufferedReader(streamReader);
			scanner = new Scanner(bufferedReader);
			while (true) {
				System.out.println("Available commands:");
				for (int i = 0; i < actions.length; ++i)
					System.out.println(Integer.toString(i+1) + ") " + actions[i]);
				
				System.out.print("Selection:");
				int nr = scanner.nextInt()-1;
				System.out.println("==========================");
				System.out.println(actions[nr]);
				System.out.println("==========================");
				
				String title;
				int price;
				switch (nr) {
				case 0:
					//add component
					System.out.print("Title:");
					title = readString();
					System.out.print("Manufacturer:");
					String manufacturer = readString();
					System.out.print("Price:");
					price = readPrice();
					shop.addComponent(title, manufacturer, price);
					continue;
				case 1:
					//add computer
					System.out.print("Title:");
					title = readString();
					System.out.print("Description:");
					String description = readString();
					System.out.print("Additional price:");
					price = readPrice();
					
					//print list
					int id;
					
					List<Integer> list = new ArrayList<Integer>();
					do {
						System.out.print("Enter component ID to add (0 to finish):");
						id = readInt();
						if (id != 0)
							list.add(id);
					}
					while (id != 0);
					
					continue;
				}
			}
			
//			try {
//				connection.close();
//			} catch (SQLException e) {
//				System.out.println("Error while closing connection!");
//				e.printStackTrace();
//			}
//		}
	}
}
