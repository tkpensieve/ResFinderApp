package ejb.Entity;

import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "USER")
public class User extends Person {
		
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	String id;
	String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	int rank;
	Date lastLoginTime;
	Date lastLogoutTime;
	@ManyToMany
	@JoinTable(name="USERLOCATIONMAP",
        joinColumns=
            @JoinColumn(name="UserId", referencedColumnName="Id"))
	List<Location> preferredLocations;
	
	@ManyToMany
	@JoinTable(name="USERCUISINEMAP",
        joinColumns=
            @JoinColumn(name="UserId", referencedColumnName="Id"))
	List<Cuisine> preferredCuisines;
	
	@OneToMany(cascade=CascadeType.ALL)
	List<Review> reviews;
	@OneToMany(cascade=CascadeType.ALL)
	List<Recommendation> recommendations;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public Date getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public Date getLastLogoutTime() {
		return lastLogoutTime;
	}
	public void setLastLogoutTime(Date lastLogoutTime) {
		this.lastLogoutTime = lastLogoutTime;
	}
	public List<Location> getPreferredLocations() {
		return preferredLocations;
	}
	public void setPreferredLocations(List<Location> preferredLocations) {
		this.preferredLocations = preferredLocations;
	}
	public List<Cuisine> getPreferredCuisines() {
		return preferredCuisines;
	}
	public void setPreferredCuisines(List<Cuisine> preferredCuisines) {
		this.preferredCuisines = preferredCuisines;
	}
	public List<Review> getReviews() {
		return reviews;
	}
	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}
	public List<Recommendation> getRecommendations() {
		return recommendations;
	}
	public void setRecommendations(List<Recommendation> recommendations) {
		this.recommendations = recommendations;
	}
}
