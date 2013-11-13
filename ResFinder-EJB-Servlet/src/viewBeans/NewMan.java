package viewBeans;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import ejb.Service.*;
/*TODO implementation. extra feature. for now we thought it is a better idea to manually add managers based on requests, as spam managers can create
 * spam restaurants */
@ManagedBean(name="NewMan")
@RequestScoped
public class NewMan {
	
	boolean man;
	long phno;	
	@EJB
	ManagerService ms;
	String address;
	public void hide()
	{		
	}
	@PostConstruct
	void setVal()
	{
		man=false;
	}
	public boolean isMan() {
		return man;
	}
	public void setMan(boolean isMan) {
		this.man = isMan;
	}
	public long getPhno() {
		return phno;
	}
	public void setPhno(long phno) {
		this.phno = phno;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}
