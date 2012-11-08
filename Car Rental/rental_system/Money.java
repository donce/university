package rental_system;


class Money {
	public static String toString(int money) {
		return "$" + String.format("%.2f", (float)money / 100);
	}
}