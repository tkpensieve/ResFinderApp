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
	
	@EJB
	RestaurantService rs;
	
	@EJB
	ReviewService revs;
	
	
	
	

}
