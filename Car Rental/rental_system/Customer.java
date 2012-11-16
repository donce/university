package rental_system;

public class Customer {
	String firstName;
	String lastName;
	
	String country;
	String city;
	String street;
	int numberA;
	int numberB;
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public int getNumberA() {
		return numberA;
	}

	public void setNumberA(int numberA) {
		this.numberA = numberA;
	}

	public int getNumberB() {
		return numberB;
	}

	public void setNumberB(int numberB) {
		this.numberB = numberB;
	}

	public Customer(String firstName, String lastName, String country, String city, String street, int numberA, int numberB) {
		setFirstName(firstName);
		setLastName(lastName);
		setCountry(country);
		setCity(city);
		setStreet(street);
		setNumberA(numberA);
		setNumberB(numberB);
	}
	
	//TODO: empty constructor?
	
	@Override
	public String toString() {
		return getFirstName() + " " + getLastName();
	}
}
