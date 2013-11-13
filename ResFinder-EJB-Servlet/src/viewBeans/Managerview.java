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
	
	@EJB
	private RestaurantService resservice;
	
	@EJB
	private ReviewService revservice;
	
	@ManagedProperty(value="#{LoginDetails}")
	LoginDetails l;
	String userid;
	boolean hasRes;
	public LoginDetails getL() {
		return l;
	}

	public void setL(LoginDetails l) {
		this.l = l;
	}
	
	public boolean isHasRes() {
		
		ArrayList<Restaurant> rlist=(ArrayList<Restaurant>)resservice.fetchByMan(l.getUserId());		//checks if the manager a=has a restaurant. display on his page differs if he does.
		if(rlist.size()==0)
		{
			return false;
		}
		return true;
	}

	public void setHasRes(boolean hasRes) {
		this.hasRes = hasRes;
	}

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
	String location;
	
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getAdded() {
		return added;
	}

	public void setAdded(String added) {
		this.added = added;
	}
	String resaddress;
	public String getUserid() {
		return userid;
		
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getResaddress() {
		return resaddress;
	}

	public void setResaddress(String resaddress) {
		this.resaddress = resaddress;
	}

	public String getResname() {
		return resname;
	}

	public void setResname(String resname) {
		this.resname = resname;
	}

	public List<Cuisine> getRescuislist() {
		return rescuislist;
	}

	public void setRescuislist(List<Cuisine> rescuislist) {
		this.rescuislist = rescuislist;
	}

	public ArrayList<Review> getRevlist() {
		return revlist;
	}

	public void setRevlist(ArrayList<Review> revlist) {
		this.revlist = revlist;
	}

	public RequestStatus getStatus() {
		return status;
	}

	public void setStatus(RequestStatus status) {
		this.status = status;
	}

	String resname;
	List<Cuisine> rescuislist;
	ArrayList<Review> revlist;
	double rating;
	RequestStatus status;
	@PostConstruct
	public void fillman()			//fills the manager view bean with details given the logged in user id. post construct so that the data is available as soon as the page loads.
	{
		String id=l.getUserId();
		ArrayList<Restaurant> res=(ArrayList<Restaurant>)resservice.fetchByMan(id);
		if(res.size()==0)
		{
			return;
		}
		Restaurant r=res.get(0);
		resaddress=r.getAddress();
		resname=r.getName();
		rescuislist=r.getCuisines();
		revlist= revservice.getReviews(r.getId());
		rating=r.getRating();
		
		
		
	}
	
	public void createRequest(String userid)
	{
		status=RequestStatus.PENDING;				//intitial state of a request must always be PENDING
		a=new AddRestaurantRequest();
		ArrayList<AddRestaurantRequest> arlist=r.findByName(restaurantName);
		if(arlist.size()!=0)				//if the request to add this restaurant has already been created, we won't allow addition.
		{
			added="couldn't add";
			return;
		}
		
		ArrayList<Restaurant> rlist=resservice.fetchByName(restaurantName);
		if(rlist.size()!=0)					//if the restaurant already exists in our database we wont allow a manager to create a new request to add it
		{
			added="couldn't add";
			return;
		}
		
		added="added";
		a.setAddress(address);
		a.setCuisine(cuisine);
		a.setRestaurantName(restaurantName);
		a.setStatus(status);
		a.setLocation(location);
		Manager m=ms.findByUserid(userid);
		a.setManager(m);
		r.createAddRestaurantRequest(a);
		
	}
	
	
	
	
	
	

}
