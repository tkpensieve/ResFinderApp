package viewBeans;
import java.util.*;


import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;


import ejb.Entity.*;
import ejb.Service.*;

@ManagedBean(name="Approval")
@RequestScoped
public class Approvalview {
	
	@EJB 
	private RequestService rs;
	
	@EJB
	private RestaurantService resservice;
	
	@EJB
	private CuisineService cs;
	
	@EJB
	private LocationService ls;
	
	String status;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	ArrayList<AddRestaurantRequest> alist;
	public ArrayList<AddRestaurantRequest> getAlist() {
		
		return rs.getReviews();
	}
	public void setAlist(ArrayList<AddRestaurantRequest> alist) {
		this.alist = alist;
	}
	public void approve(int id)
	{
		AddRestaurantRequest e;
		e=rs.findById(id);
		Restaurant r=new Restaurant();
		r.setAddress(e.getAddress());
		String[] s=e.getCuisine().split(",");
		ArrayList<Cuisine> clist=new ArrayList<Cuisine>();
		for(int i=0;i<s.length;i++)
		{
			clist.add(cs.findCuisine(s[i]));
		}
		r.setCuisines(clist);
		
		Location l=ls.findLoc(e.getLocation());
		r.setLocation(l);
		r.setManager(e.getManager());
		r.setName(e.getRestaurantName());
		status=resservice.createRestaurant(r);
		
		
		
	}
	
	
	
	

}
