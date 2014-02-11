package ejb.Entity;
import java.io.Serializable;
import java.util.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
@NamedQueries({
	@NamedQuery(
		name="Recommendation.fetchRes",			//query to fetch restaurant based on user id of user who wrote the review and restaurant id
		query="select res from Recommendation rec inner join rec.restaurant res where rec.user.id=:id"
		),
		@NamedQuery(
				name="Recommendation.fetchRec",			//fetch recommendation based on user id
				query="select rec from Recommendation rec where rec.user.id=:id")
})

@Entity
@Table(name = "RECOMMENDATION")
public class Recommendation implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	@ManyToOne
	@JoinColumn(name="UserId", nullable=false)
	User user;
	@ManyToOne
	@JoinColumn(name="RestaurantId", nullable=false)
	Restaurant restaurant;
	Date dateAdded;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Restaurant getRestaurant() {
		return restaurant;
	}
	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	public Date getDateAdded() {
		return dateAdded;
	}
	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}
}
