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
	
	String resname;
	
	public String getResname() {
		return resname;
	}
	public void setResname(String resname) {
		this.resname = resname;
	}
	String status;
	public Approvalview()
	{
		status="";
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	ArrayList<AddRestaurantRequest> alist;
	public ArrayList<AddRestaurantRequest> getAlist() {
		
		return rs.getRequests();
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
		ArrayList<Cuisine> cl;
	
		for(int i=0;i<s.length;i++)
		{
			cl=cs.findCuisine(s[i]);
			if(cl.size()==0)
			{
				Cuisine c=new Cuisine();
				c.setDescription("");
				c.setName(s[i]);
				cs.createCuisine(c);
				cl.add(c);
			}
			clist.add(cl.get(0));
		}
		r.setCuisines(clist);
		
		String loc=e.getLocation();
		ArrayList<Location> list=ls.findLoc(loc);
		if(list.size()==0)
		{
			Location l=new Location();
			l.setName(loc);
			ls.createLocation(l);
			list.add(l);
		}
		ArrayList<Restaurant> rlist=resservice.fetchByName(e.getRestaurantName());
		if(rlist.size()!=0)
		{
			status="Couldn't add";
			return;
		}
		r.setLocation(list.get(0));
		r.setManager(e.getManager());
		r.setName(e.getRestaurantName());
		status=resservice.createRestaurant(r);
		if(status.equals("no"))
		{
			return;
		}
		e.setStatus(RequestStatus.APPROVED);
		rs.createAddRestaurantRequest(e);
		
		
		
		
		
	}
	
	
	
	

}
