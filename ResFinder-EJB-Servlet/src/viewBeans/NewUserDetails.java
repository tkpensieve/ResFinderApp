package viewBeans;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import ejb.Entity.User;
import ejb.Service.UserService;

@ManagedBean(name="NewUserDetails")
@RequestScoped
public class NewUserDetails {
	@EJB
	private UserService userService;
	
	String userId;
	String password;
	String registerEmail;
	String userName;
	
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
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void register()	{
		User user = new User();
		user.setName(userName);
		user.setEmailId(registerEmail);
		user.setId(userId);
		user.setPassword(password);
		user.setRank(2);
		userService.createUser(user);
	}
}
