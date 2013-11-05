package viewBeans;

import java.util.*;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import ejb.Entity.*;
import ejb.Service.*;

@ManagedBean(name="Resview")
@RequestScoped
public class Restaurantview {
	
	@EJB
	private RestaurantService restaurantService;
	@EJB
	private ReviewService rs;
	@ManagedProperty(value="#{param['id']}")
	int resId;
	public int getResId() {
		return resId;
	}
	public void setResId(int resId) {
		this.resId = resId;
	}
	Restaurant res;
	String name;
	String address;
	public String getAddress()
	{
		return address;
		
		
	}
	public ArrayList<Review> getRev()
	{
		return rev;
	}
	int selectedLocationId;
	Map<String, Object> allLocations = new HashMap<String, Object>();
	ArrayList<Restaurant> filteredRestaurants;
	
	@PostConstruct
	public void fillRes()
	{
		res=restaurantService.findById(resId);
		name=res.getName();
		address=res.getAddress();
		rev=rs.getReviews(resId);
	}
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	ArrayList<Review> rev;

	

}
