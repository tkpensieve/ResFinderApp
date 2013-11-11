package viewBeans;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import ejb.Entity.User;
import ejb.Service.UserService;

@ManagedBean(name="LoginDetails")
@SessionScoped
public class LoginDetails {
	@EJB
	private UserService userService;
	
	String userId;
	String password;
	String registerEmail;
	String loginMessage;
	User loggedInUser;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRegisterEmail() {
		return registerEmail;
	}
	public void setRegisterEmail(String registerEmail) {
		this.registerEmail = registerEmail;
	}
	
	public String getLoginMessage() {
		return loginMessage;
	}
	public void setLoginMessage(String loginMessage) {
		this.loginMessage = loginMessage;
	}
	
	public User getLoggedInUser() {
		return loggedInUser;
	}
	public void setLoggedInUser(User loggedInUser) {
		this.loggedInUser = loggedInUser;
	}
	public void login()	{
		User user = userService.findById(userId);
		String message = "";
		if(user.getPassword().toLowerCase().equals(password.toLowerCase())){
			message =  "";
			this.setLoggedInUser(user);
		}
		else{
			message = "Error - Wrong user name or password";
		}
		this.setLoginMessage(message);
	}
}
