package viewBeans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import ejb.Entity.Location;
import ejb.Entity.Restaurant;
import ejb.Service.LocationService;
import ejb.Service.RestaurantService;

@ManagedBean(name="SearchForm")
@RequestScoped
public class SearchForm {
	@EJB
	private LocationService locationService;
	@EJB
	private RestaurantService restaurantService;
	
	Location selectedLocation;
	Map<String, Object> allLocations = new HashMap<String, Object>();
	ArrayList<Restaurant> filteredRestaurants;
	
	public SearchForm() { 
		this.filteredRestaurants = new ArrayList<Restaurant>();
	}
	public Map<String, Object> getAllLocations() {
		if(allLocations.isEmpty()){
			HashMap<String, Object> allLocations = new HashMap<String,Object>();
			ArrayList<Location> findAll = locationService.findAll();
			for (Location location : findAll) {
				allLocations.put(location.getName(), location.getId()); //label, value
			}
			setAllLocations(allLocations);
		}
		return this.allLocations;
	}

	public void setAllLocations(Map<String, Object> allLocations) {
		this.allLocations = allLocations;
	}

	public Location getSelectedLocation() {
		return selectedLocation;
	}

	public void setSelectedLocation(Location selectedLocation) {
		this.selectedLocation = selectedLocation;
	}

	public ArrayList<Restaurant> getFilteredRestaurants() {
		return filteredRestaurants;
	}

	public void setFilteredRestaurants(ArrayList<Restaurant> filteredRestaurants) {
		this.filteredRestaurants = filteredRestaurants;
	}
	
	public void filter()
	{
		setFilteredRestaurants(restaurantService.filter(selectedLocation));
	}
}
