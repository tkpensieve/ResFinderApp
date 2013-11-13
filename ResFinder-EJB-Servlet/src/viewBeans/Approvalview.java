package viewBeans;
import java.util.*;


import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;


import ejb.Entity.*;
import ejb.Service.*;
/*view bean for approval view*/
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
		
		return rs.getRequests();							//retrieve all pending requests from the request service
	}
	public void setAlist(ArrayList<AddRestaurantRequest> alist) {
		this.alist = alist;
	}
	public void approve(int id)		//function to approvea request. takes the request id as a parameter
	{
		AddRestaurantRequest e;
		e=rs.findById(id);
		
		Restaurant r=new Restaurant();
		r.setAddress(e.getAddress());
		String[] s=e.getCuisine().split(",");
		ArrayList<Cuisine> clist=new ArrayList<Cuisine>();		
		ArrayList<Cuisine> cl;
	
		for(int i=0;i<s.length;i++)				//loop adds cuisine objects to the list of cuisine objects based on the tokenized string. if the cuisine doesn'texist in the database, a new one is created. 
		{
			cl=cs.findCuisine(s[i]);		//find cuisine, returns an arraylist (to check for size)
			if(cl.size()==0)
			{
				Cuisine c=new Cuisine();	//creaate new cuisine because it doesn't exist in the database.
				c.setDescription("");
				c.setName(s[i]);
				cs.createCuisine(c);
				cl.add(c);
			}
			clist.add(cl.get(0));		//intention was to retrieve only one cuisine bu as a list to check if length was 0 i.e whether cuisine exists or not.
		}
		r.setCuisines(clist);
		
		String loc=e.getLocation();
		ArrayList<Location> list=ls.findLoc(loc);		
		if(list.size()==0)				//adds a new location if the one provided in the request doesn't exist.
		{
			Location l=new Location();
			l.setName(loc);
			ls.createLocation(l);
			list.add(l);
		}
		ArrayList<Restaurant> rlist=resservice.fetchByName(e.getRestaurantName());
		if(rlist.size()!=0)			// if the restaurant already exists, it won't be added.
		{
			status="Couldn't add";
			return;
		}
		r.setLocation(list.get(0));
		r.setManager(e.getManager());
		r.setName(e.getRestaurantName());
		status=resservice.createRestaurant(r);			
		if(status.equals("no"))			//if creation fails
		{
			return;
		}
		e.setStatus(RequestStatus.APPROVED);		//set status to approved
		rs.createAddRestaurantRequest(e);
		
		
		
		
		
	}
	
	
	
	

}
