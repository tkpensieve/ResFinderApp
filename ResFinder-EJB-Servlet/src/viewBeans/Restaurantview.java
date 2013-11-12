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
	@ManagedProperty(value="#{param['search']}")
	boolean search=false;
	@ManagedProperty(value="#{param['usrid']}")
	String usrid;
	String manName;
	Restaurant res;
	String name;
	String address;
	int rating;
	//@ManagedProperty(value="#{param['business']}")
	boolean bus;
	int defid;
	ArrayList<Review> rev;
	List<Cuisine> cuis;
	String newReviewContent;
	
	@PostConstruct
	public void fillRes()
	{
		if(search)
		{
			res=restaurantService.findById(resId);
			name=res.getName();
			address=res.getAddress();
			manName=res.getManager().getUser().getName();
			rev=rs.getReviews(resId);
		}
	}
	
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
	public String getAddress()
	{
		return address;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getManName() {
		return manName;
	}
	public void setManName(String manName) {
		this.manName = manName;
	}
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
	public int getDefid() {
		return -1;
	}
	public void setDefid(int defid) {
		this.defid = defid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Restaurant getRes() {
		return res;
	}
	public void setRes(Restaurant res) {
		this.res = res;
	}
	public String getNewReviewContent() {
		return newReviewContent;
	}
	public void setNewReviewContent(String newReviewContent) {
		this.newReviewContent = newReviewContent;
	}
	
	public List<Cuisine> getcuis()
	{
		if(search)
		{
			return res.getCuisines();
		}
		else
			return(new ArrayList<Cuisine>());
	}
	
	
	public ArrayList<Review> getRev()
	{
		if(!search)
		{
			return (new  ArrayList<Review>());
		}
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

	public void addReview(User user){
		Review newReview = new Review();
		newReview.setContent(newReviewContent);
		newReview.setDateTimeAdded(new Date());
		newReview.setUser(user);
		Restaurant rest = new Restaurant();
		rest.setId(1);
		newReview.setRestaurant(rest);
        rs.createReview(newReview);
	}

	
}
