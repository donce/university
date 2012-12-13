package rental_system;

public class InvalidFormDataException extends Exception {
	private String field;
	private String mistake;

	public InvalidFormDataException(String field, String mistake) {
		setField(field);
		setMistake(mistake);
	}

	public InvalidFormDataException(String field) {
		this(field, null);
	}

	public InvalidFormDataException() {
		this(null, null);
	}

	public String getField() {
		return field;
	}

	private void setField(String field) {
		this.field = field;
	}

	public String getMistake() {
		return mistake;
	}

	private void setMistake(String mistake) {
		this.mistake = mistake;
	}

	public String getUserMessage() {
		return (getField() != null ? "Field \"" + getField() + "\" "
				: "One field ")
				+ (getMistake() != null ? getMistake() : "is incorrect") + "!";
	}

}
