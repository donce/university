package rental_system;

public class Customer {
	String firstName;
	String lastName;
	
	String country;
	String city;
	String street;
	int numberA;
	int numberB;
	
	public Customer(String firstName, String lastName, String country, String city, String street, int numberA, int numberB) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.country = country;
		this.city = city;
		this.street = street;
		this.numberA = numberA;
		this.numberB = numberB;
	}
	
	@Override
	public String toString() {
		return firstName + " " + lastName;
	}
}
