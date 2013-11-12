package viewBeans;

import java.util.*;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;



import ejb.Entity.*;
import ejb.Service.*;

@ManagedBean(name="Userview")
@RequestScoped

public class Userview {
	
	public LoginDetails getL() {
		return l;
	}
	public void setL(LoginDetails l) {
		this.l = l;
	}
	@EJB
	RestaurantService rs;
	
	@EJB
	ReviewService revs;
	@ManagedProperty(value="#{LoginDetails}")
	LoginDetails l;
	
	List<Review> revlist;
	
	public List<Review> getRevlist() {
		revlist=revs.findByUser(l.getUserId());
		for(Review r:revlist)
		{
			r.setRestaurant(revs.fetchRes(l.getUserId()));
		}
		return revlist;
	}
	public void setRevlist(List<Review> revlist) {
		this.revlist = revlist;
	}
	
	
	
	
	
	
	
	
	

}
