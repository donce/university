import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;



public class Main {
	private static Shop shop;
	private static BufferedReader bufferedReader;
	private static Scanner scanner;
	private static final String[] actions = {
		"Quit",
		"Add component",
		"Add computer",
		"Register customer",
		"Change components in computer",
		"Find customer",
		"Buy"
		};

	
	private static final String HOST = "jdbc:postgresql://pgsql2.mif/studentu";
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
			connection = DriverManager.getConnection(HOST, DB_USER, DB_USER);
		} catch (SQLException e) {
			System.out.println("Can't connect to server!");
			return null;
		}
		return connection;
		
	}
	
	private static String readString(String title) {
		System.out.print(title + ": ");
		try {
			return bufferedReader.readLine();
		} catch (IOException e) {
			throw new InputMismatchException("String expected!");
		}
	}

	private static int readInt(String title) {
		System.out.print(title + ": ");
		try {
			return scanner.nextInt();
		} catch (NoSuchElementException e) {
			throw new InputMismatchException("Int expected!");
		} catch (IllegalStateException e) {
			throw new InputMismatchException("Int expected!");
		}
	}

	private static long readLong(String title) {
		System.out.print(title + ": ");
		try {
			return scanner.nextLong();
		} catch (NoSuchElementException e) {
			throw new InputMismatchException("Long expected!");
		} catch (IllegalStateException e) {
			throw new InputMismatchException("Long expected!");
		}
	}
	
	private static BigDecimal readBigDecimal(String title) {
		System.out.print(title + ": ");
		try {
			return scanner.nextBigDecimal();
		} catch (NoSuchElementException e) {
			throw new InputMismatchException("Float expected!");
		} catch (IllegalStateException e) {
			throw new InputMismatchException("Float expected!");
		}
	}
	
	private static Date readDate(String title) {
		System.out.print(title + "(YYYY-MM-DD): ");
		try {
			String line = bufferedReader.readLine();
			String[] parts = line.split("-");
			if (parts.length != 3)
				throw new IOException();
			int[] values = new int[3];
			for (int i = 0; i < 3; ++i)
				try {
					values[i] = Integer.parseInt(parts[i]);
				} catch (NumberFormatException e) {
					throw new IOException();
				}
			Calendar calendar = Calendar.getInstance();
			calendar.set(values[0], values[1]-1, values[2]);
			return new Date(calendar.getTime().getTime());
			
		} catch (IOException e) {
			throw new InputMismatchException("Date expected!");
		}
	}
	
	private static boolean readBoolean(String title) {
		System.out.println(title + "(y/n): ");
		try {
			String line = bufferedReader.readLine();
			if (line.length() != 1)
				throw new IOException();
			char c = line.charAt(0);
			if (c == 'y')
				return true;
			else if (c == 'n')
				return false;
			throw new IOException();
		} catch (IOException e) {
			throw new InputMismatchException("String expected!");
		}
	}

	private static void executeTask(int number) throws SQLException {
		System.out.println("==========================");
		System.out.println(actions[number+1]);
		System.out.println("==========================");
		
		switch (number) {
		case 0:
			//add component
			shop.addComponent(readString("Title"), readString("Manufacturer"), readBigDecimal("Price"));
			break;
		case 1:
			//add computer
			shop.addComputer(readString("Title"), readString("Description"), readBigDecimal("Additional price"));
			break;
		case 2:
			//register user
			shop.register(readString("First name"), readString("Last name"), readLong("Identification code"), readDate("Birthday"), readString("Address"));
			break;
		case 3:
			//change components in computer
			shop.printComputers();
			int computer = readInt("Computer ID");
			shop.printComponents();
			shop.printComputerComponents(computer);
			shop.changeComputerComponent(computer, readInt("Component ID"), readInt("Change amount of this component in computer by"));
			break;
		case 4:
			//find customer
			shop.findCustomer(readString("First name"), readString("Last name"));
			break;
		case 5:
			//buy
			shop.printCustomers();
			int customer = readInt("Customer ID");
			shop.printComputers();
			shop.buy(customer, readInt("Computer ID"), readBoolean("Deliver"));
			break;
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
				System.out.println("==========================");
				System.out.println("Available actions:");
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
						System.out.println(e.getMessage());
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
