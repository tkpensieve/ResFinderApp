package viewBeans;

import java.text.DecimalFormat;
import java.util.*;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

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
	double rating;
	//@ManagedProperty(value="#{param['business']}")
	boolean bus;
	int defid;
	ArrayList<Review> rev;
	List<Cuisine> cuis;
	String newReviewContent;
	double newrating;
	public double getNewrating() {
		return newrating;
	}

	public void setNewrating(double newrating) {
		this.newrating = newrating;
	}

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
			rating=res.getRating();
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
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
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
	
	public String addReview(User user){
		Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		
		int restId = Integer.parseInt(params.get("resId"));
		ArrayList<Review> revlist=rs.findByUserRes(user.getId(),restId);
		if(revlist.size()!=0)
		{
			return "restaurantView?faces-redirect=true&search=true&id="+restId;
		}
		if(newrating>5)
		{
			return "restaurantView?faces-redirect=true&search=true&id="+restId;
		}
		Review newReview = new Review();
		newReview.setContent(newReviewContent);
		newReview.setDateTimeAdded(new Date());
		newReview.setUser(user);
		Restaurant rest = new Restaurant();
		rest.setId(restId);
		newReview.setRestaurant(rest);
		newReview.setRating(newrating);
        rs.createReview(newReview);    
        ArrayList<Double> ratingList;
        ratingList=rs.getRatings(restId);
        
        double sum=0;
        for(Double i :ratingList)
        {
        	sum+=i.doubleValue();
        	System.out.println(i.doubleValue());
        }
        if(ratingList.size()!=0)
        {
        	sum/=ratingList.size();
        }
        System.out.println(sum);
        DecimalFormat df=new DecimalFormat("#.##");
		double sum1=Double.parseDouble(df.format(sum));
        rest=restaurantService.findById(restId);
        System.out.println(restaurantService.updateRating(sum1, rest));
        
        
        //System.out.println(restaurantService.createRestaurant(rest));
        rest=restaurantService.findById(restId);
        System.out.println(rest.getName());
        System.out.println(rest.getRating());
    	String url = "restaurantView?faces-redirect=true&search=true&id="+restId;
    	return url;
	}
}
