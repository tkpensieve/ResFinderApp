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
		Collections.sort(rev, new Comparator<Review>(){
			
			
			public int compare(Review x, Review y) 
			{
		   
		    int startComparison = compare(x.getDateTimeAdded(), y.getDateTimeAdded());
		    return startComparison != 0 ? startComparison
		                                : compare(x.getDateTimeAdded(), y.getDateTimeAdded());
		  }

		 
		  private  int compare(Date a, Date b) {
		    return a.before(b) ? -1
		         : a.after(b) ? 1
		         : 0;
		  }
		});
		return rev;
	}
	String manid;
	
	
	public String getManid() {
		return manid;
	}
	public void setManid(String manid) {
		this.manid = manid;
	}
	@PostConstruct
	public void fillRes()
	{
		res=restaurantService.findById(resId);
		name=res.getName();
		address=res.getAddress();
		manid=res.getManager().getUser().getId();
		rev=rs.getReviews(resId);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	ArrayList<Review> rev;
	List<Cuisine> cuis;
	
	public List<Cuisine> getcuis()
	{
		return res.getCuisines();
	}
}
