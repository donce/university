package orders;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Orders {
	@XmlElement(name="order")
	private List<Order> list;
	
	public List<Order> getList() {
		return list;
	}
}

