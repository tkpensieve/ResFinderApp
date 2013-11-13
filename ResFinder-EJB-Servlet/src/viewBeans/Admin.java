package viewBeans;
/*View bean for administrator view*/

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name="Admin")
//Backing bean used in admin view to approve add restaurant requests
@RequestScoped
public class Admin {
	String userName;
	String password;
	String auth;
	
	public Admin()
	{
		userName="";
		password="";
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAuth() {
		return auth;
	}
	public void setAuth(String auth) {
		this.auth = auth;
	}
	public String validate()			//bare bones validation
	{
		if(userName.equals("admin")&&password.equals("admin"))
		{
			return "approve.jsf?faces-redirect=true";
		}
		return "a";
	}	
}
	


