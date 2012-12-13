package rental_system;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;

@XmlEnum
public enum Transmission {
	@XmlEnumValue("manual")
	MANUAL("Manual"), @XmlEnumValue("automatic")
	AUTOMATIC("Automatic");

	private String title;

	Transmission(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return title;
	}
};
