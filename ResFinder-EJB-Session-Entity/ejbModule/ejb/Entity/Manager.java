package ejb.Entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "MANAGER")
public class Manager extends Person{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//	Restaurant restaurant;
	String phone;
	String address;
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	} 
}
