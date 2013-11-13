package viewBeans;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import ejb.Entity.Manager;
import ejb.Entity.User;
import ejb.Service.ManagerService;
import ejb.Service.UserService;
/*session bean for log in*/
@ManagedBean(name="LoginDetails")
//Bean used to store the logged in user details and has lifetime of the user's session
@SessionScoped
public class LoginDetails {
	@EJB
	private UserService userService;
	@EJB
	private ManagerService managerService;
	
	String userId;
	String password;
	String registerEmail;
	String loginMessage;
	User loggedInUser;
	boolean loggedin=false;
	String profileLink;
	
	public boolean isLoggedin() {
		return loggedin;
	}
	public void setLoggedin(boolean loggedin) {
		this.loggedin = loggedin;
	}
	boolean businessUser;
	public LoginDetails() {
		businessUser=false;
	}
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
	public boolean isBusinessUser() {
		return businessUser;
	}
	public void setBusinessUser(boolean businessUser) {
		this.businessUser = businessUser;
	}
	public String getProfileLink() {
		return profileLink;
	}
	public void setProfileLink(String profileLink) {
		this.profileLink = profileLink;
	}
	public void login()	{
		User user = null;
		String profilePageLink = null;
		//Handle business user login
		if(businessUser){
			Manager manager = managerService.findByUserid(userId);
			user = manager.getUser();
			profilePageLink = "managerView";
		}
		else {
			user = userService.findById(userId);
			profilePageLink = "userHome";
		}
		String message = "";
		if(user.getPassword().toLowerCase().equals(password.toLowerCase())){
			message =  "";
			this.setLoggedInUser(user);
			this.setProfileLink(profilePageLink);
			loggedin=true;
		}
		else{
			message = "Error - Wrong user name or password";
		}
		this.setLoginMessage(message);
	}
	
	public String logout(){
		//clear all user related session information on logout
		this.setLoggedInUser(null);
		this.setLoginMessage(null);
		userId=null;
		loggedin=false;
		loggedInUser = null;
		loginMessage = null;
		businessUser=false;
		profileLink = "";
		return "index?faces-redirect=true";
	}
}
