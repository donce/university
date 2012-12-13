package system_additions;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import rental_system.Car;

@XmlRootElement
class Cars {
	@XmlElement(name = "car")
	public List<Car> list;

}
