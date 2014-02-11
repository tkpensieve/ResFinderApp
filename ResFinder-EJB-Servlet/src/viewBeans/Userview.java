package viewBeans;
/*view bean for user*/
import java.util.*;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;



import ejb.Entity.*;
import ejb.Service.*;

@ManagedBean(name="Userview")
@RequestScoped

public class Userview {
	@EJB
	RestaurantService rs;
	
	@EJB
	ReviewService revs;
	
	@EJB
	RecommendationService recs;
	
	@ManagedProperty(value="#{LoginDetails}")
	LoginDetails l;
	List<Review> revlist;
	List<Recommendation> reclist;
	
	public LoginDetails getL() {
		return l;
	}
	public void setL(LoginDetails l) {
		this.l = l;
	}
	public List<Review> getRevlist() {
		//get users reviews and restaurant details for that review
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
	public List<Recommendation> getReclist() {
		//get users reviews and restaurant details for that review
		reclist=recs.findByUser(l.getUserId());
		return reclist;
	}
	public void setReclist(List<Recommendation> reclist) {
		this.reclist = reclist;
	}
}
