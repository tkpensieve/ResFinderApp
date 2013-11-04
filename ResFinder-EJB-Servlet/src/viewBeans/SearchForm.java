package viewBeans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import ejb.Entity.Location;
import ejb.Service.LocationService;
import ejb.Service.RestaurantService;

@ManagedBean
@ApplicationScoped
public class SearchForm {
	@EJB
	private LocationService locationService;
	@EJB
	private RestaurantService restaurantService;
	
	Location selectedLocation;
	Map<String, Object> allLocations = new HashMap<String, Object>();
	
	public SearchForm(Map<String, Object> allLocations) {
		this.allLocations = new HashMap<String,Object>();
		ArrayList<Location> findAll = locationService.findAll();
		for (Location location : findAll) {
			allLocations.put(location.getName(), location.getId()); //label, value
		}
	}
	
	public void filter()
	{
//		restaurantService.findById(1);
	}
}
