package ejb.Entity;

import java.io.Serializable;

abstract class Person  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String name;
	String password;
	String emailId;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
}


