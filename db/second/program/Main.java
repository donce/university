import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javax.management.RuntimeErrorException;


public class Main {
	private static Shop shop;
	private static BufferedReader bufferedReader;
	private static Scanner scanner;
	private static final String[] actions = {
		"Quit",
		"Add component",
		"Add computer",
		"Register customer",
		"Find customer",
		"Buy",
		};

	
	private static final String DB_USER = "doku9900";
	
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
			connection = DriverManager.getConnection("jdbc:postgresql://localhost/studentu", DB_USER, DB_USER);
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
			throw new InputMismatchException("String expected!");
		}
	}

	private static int readInt() {
		try {
			return scanner.nextInt();
		} catch (NoSuchElementException | IllegalStateException e) {
			throw new InputMismatchException("Int expected!");
		}
	}

	private static long readLong() {
		try {
			return scanner.nextLong();
		} catch (NoSuchElementException | IllegalStateException e) {
			throw new InputMismatchException("Long expected!");
		}
	}
	
	private static BigDecimal readBigDecimal() {
		try {
			return scanner.nextBigDecimal();
		} catch (NoSuchElementException | IllegalStateException e) {
			throw new InputMismatchException("Float expected!");
		}
	}
	
	//TODO: wrong result in database
	private static Date readDate() {
		try {
			String line = bufferedReader.readLine();
			String[] parts = line.split("-");
			if (parts.length != 3)
				throw new IOException();
			int[] values = new int[3];
			for (int i = 0; i < 3; ++i)
				try {
					Integer.parseInt(parts[i]);
				} catch (NumberFormatException e) {
					throw new IOException();
				}
			Calendar calendar = Calendar.getInstance();
			calendar.set(values[0], values[1], values[2]);
			return new Date(calendar.getTime().getTime());
			
		} catch (IOException e) {
			throw new InputMismatchException("Date expected!");
		}
	}
	
	private static List<Object> readInput(String[] titles, Class<?>[] types) {
		if (titles.length != types.length)
			throw new RuntimeException("Different sizes of argument arrays.");
		List<Object> list = new ArrayList<>();
		for (int i = 0; i < titles.length; ++i) {
			Class<?> t = types[i];
			if (t == Date.class)
				System.out.print("(YYYY-MM-DD)");
			System.out.print(titles[i] + ":");
			Object obj = Integer.class;
			if (t == int.class)
				obj = readInt();
			else if (t == long.class)
				obj = readLong();
			else if (t == String.class)
				obj = readString();
			else if (t == Date.class)
				obj = readDate();
			else if (t == BigDecimal.class)
				obj = readBigDecimal();
			else
				throw new RuntimeException("Unknown class asked for input");
			list.add(obj);
		}
		return list;
	}
	
	
	private static void executeTask(int number) throws SQLException {
		System.out.println("==========================");
		System.out.println(actions[number+1]);
		System.out.println("==========================");
		
		String title, manufacturer, firstName, lastName;
		Date birthday;
		long identificationCode;
		String address;
		BigDecimal price;
		
//		String[] titles;
//		Class[]
		List<Object> r;
		
		switch (number) {
		case 0:
//			readInput(titles, types)
			//add component
			System.out.print("Title:");
			title = readString();
			System.out.print("Manufacturer:");
			manufacturer = readString();
			System.out.print("Price:");
			price = readBigDecimal();
			shop.addComponent(title, manufacturer, price);
			break;
		case 1:
			//add computer
			System.out.print("Title:");
			title = readString();
			System.out.print("Description:");
			String description = readString();
			System.out.print("Additional price:");
			price = readBigDecimal();
			
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
			
			break;
		case 2:
			r = readInput(
					new String[] {"First name", "Last name", "Identification code", "Birthday", "Address"},
					new Class<?>[] {String.class, String.class, long.class, Date.class, String.class});
			shop.register((String)r.get(0), (String)r.get(1), (long)r.get(2), (Date)r.get(3), (String)r.get(4));
			System.out.println(r);
			//register user
//			System.out.print("First name:");
//			firstName = readString();
//			System.out.print("Last name:");
//			lastName = readString();
//			System.out.print("Identification code:");
//			identificationCode = readLong();
//			System.out.print("Birthday(YYYY-MM-DD):");
//			birthday = readDate();
//			System.out.print("Address:");
//			address = readString();
//			shop.register(firstName, lastName, identificationCode, birthday, address);
			break;
		case 3:
			//buy
			shop.printCustomers();
			System.out.print("Customer:");
			int customer = readInt();
			shop.printComputers();
			System.out.print("Computer:");
			int computer = readInt();
//			System.out.println("Deliver?(y/n):");
//			shop.buy(computer, customer);
		}
	}
	
	public static void main(String[] args) {
		loadDriver();
		Connection connection = getConnection();
		if (connection != null) {
			try {
				shop = new Shop(connection);
			} catch (SQLException e) {
				System.out.println("Error while initializing statements");
				return;
			}
			InputStreamReader streamReader = new InputStreamReader(System.in);
			bufferedReader = new BufferedReader(streamReader);
			scanner = new Scanner(bufferedReader);
			int number = 1;
			while (number != 0) {
				System.out.println("Available commands:");
				for (int i = 0; i < actions.length; ++i)
					System.out.println(Integer.toString(i) + ") " + actions[i]);
				System.out.print("Selection:");
				number = scanner.nextInt();
				
				if (number < 0 || number >= actions.length)
					System.out.println("Chosen number is out of bounds.");
				else if (number != 0)
					try {
						executeTask(number-1);
					} catch (SQLException e) {
						System.out.println("Database error occured while executing task.");
					}
			}
			shop.close();
			try {
				connection.close();
			} catch (SQLException e) {
			}
		}
	}
}
