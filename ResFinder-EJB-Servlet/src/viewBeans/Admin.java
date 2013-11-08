package viewBeans;


import java.util.*;


import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;


import ejb.Entity.*;
import ejb.Service.*;

@ManagedBean(name="Admin")
@RequestScoped
public class Admin {
	
	String userName;
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
	String password;
	String auth;
	public String getAuth() {
		return auth;
	}
	public void setAuth(String auth) {
		this.auth = auth;
	}
	public void validate()
	{
		if(userName.equals("admin")&&password.equals("admin"))
		{
			auth= "valid";
		}
		else
			auth= "invalid";
	}
			
		
}
	


