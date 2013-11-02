package ejb.Entity;
import java.io.Serializable;
import java.util.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "RESTAURANT")
public class Restaurant implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	String name;
	@OneToOne
	@JoinColumn(name = "ManagerId")
	Manager manager;
	@ManyToMany
	@JoinTable(name="RESTAURANTCUISINEMAP",
        joinColumns=
            @JoinColumn(name="RestaurantId", referencedColumnName="Id"))
	List<Cuisine> cuisines;
	
	String address;
	@OneToOne
	@JoinColumn(name = "LocationId")
	Location location;
	int rating;
	@OneToMany(orphanRemoval=true)
	@JoinColumn(name="RestaurantId") 
//	@OneToMany(cascade=CascadeType.ALL)
	List<Review> reviews;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Manager getManager() {
		return manager;
	}
	public void setManager(Manager manager) {
		this.manager = manager;
	}
	public List<Cuisine> getCuisines() {
		return cuisines;
	}
	public void setCuisines(List<Cuisine> cuisines) {
		this.cuisines = cuisines;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public List<Review> getReviews() {
		return reviews;
	}
	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}
}
