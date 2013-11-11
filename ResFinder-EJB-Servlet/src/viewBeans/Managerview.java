package viewBeans;

import java.util.*;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;


import ejb.Entity.*;
import ejb.Service.*;

@ManagedBean(name="Manview")
@RequestScoped
public class Managerview {
	@EJB
	private RequestService r;
	
	@EJB
	private ManagerService ms;
	
	String userid;

	String password;
	
	AddRestaurantRequest a;
	Manager manager;
	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCuisine() {
		return cuisine;
	}

	public void setCuisine(String cuisine) {
		this.cuisine = cuisine;
	}

	String restaurantName;
	String address;
	String cuisine;
	String added;
	
	public String getAdded() {
		return added;
	}

	public void setAdded(String added) {
		this.added = added;
	}

	RequestStatus status;
	public void createRequest()
	{
		status=RequestStatus.PENDING;
		a=new AddRestaurantRequest();
		ArrayList<AddRestaurantRequest> arlist=r.findByName(restaurantName);
		if(arlist.size()!=0)
		{
			added="couldn't add";
			return;
		}
		added="added";
		
		
		
		a.setAddress(address);
		a.setCuisine(cuisine);
		a.setRestaurantName(restaurantName);
		Manager m=ms.findByUserid("abc");
		a.setManager(m);
		r.createAddRestaurantRequest(a);
		
	}
	
	
	
	
	
	

}
