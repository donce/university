package rental_system;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;

@XmlEnum
public enum WheelSide {
	@XmlEnumValue("left")
	LEFT("Left"), @XmlEnumValue("right")
	RIGHT("Right");

	private String title;

	WheelSide(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return title;
	}
};
