package viewBeans;

import java.util.*;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import com.sun.org.glassfish.gmbal.ManagedAttribute;

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
	@ManagedProperty(value="#{param['search']}")
	boolean search;
	
	public boolean isSearch() {
		return search;
	}
	public void setSearch(boolean search) {
		this.search = search;
	}
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
	@ManagedProperty(value="#{param['usrid']}")
	String usrid;
	String manid;
	
	
	int rating;
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getManid() {
		return manid;
	}
	public void setManid(String manid) {
		this.manid = manid;
	}
	//@ManagedProperty(value="#{param['business']}")
	boolean bus;
	public String getUsrid() {
		return usrid;
	}
	public void setUsrid(String usrid) {
		this.usrid = usrid;
	}
	public boolean isBus() {
		return bus;
	}
	public void setBus(boolean bus) {
		this.bus = bus;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	int defid;
	
	
	
	public int getDefid() {
		return -1;
	}
	public void setDefid(int defid) {
		this.defid = defid;
	}
	@PostConstruct
	public void fillRes()
	{
		
		if(search)
		{
			res=restaurantService.findById(resId);
			name=res.getName();
			address=res.getAddress();
			manid=res.getManager().getUser().getId();
			rev=rs.getReviews(resId);
		}
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
