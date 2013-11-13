package ejb.Entity;
import java.io.Serializable;
import java.util.*;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
		name="Review.fetchRes",
		query="select res from Review rev inner join rev.restaurant res where rev.user.id=:id"
		),
@NamedQuery(
		name="Review.fetchRev",
		query="select rev from Review rev where rev.user.id=:id"),
@NamedQuery(
				name="Review.fetchRevRes",
				query="select rev from Review rev where rev.user.id=:id and rev.restaurant.id=:resid"),
@NamedQuery(
		name="Review.getRatings",
		query="select rev.rating from Review rev inner join rev.restaurant res where rev.restaurant.id=:id")
})
@Entity
@Table(name = "REVIEW")
public class Review implements Serializable{
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
	//int upVotes;
	//int downVotes;
	String content;
	@Enumerated(EnumType.STRING)
	ReviewCategory category;
	Date dateTimeAdded;
	double rating;
	
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
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
	}/*
	public int getUpVotes() {
		return upVotes;
	}
	public void setUpVotes(int upVotes) {
		this.upVotes = upVotes;
	}
	public int getDownVotes() {
		return downVotes;
	}
	public void setDownVotes(int downVotes) {
		this.downVotes = downVotes;
	}*/
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public ReviewCategory getCategory() {
		return category;
	}
	public void setCategory(ReviewCategory category) {
		this.category = category;
	}
	public Date getDateTimeAdded() {
		return dateTimeAdded;
	}
	public void setDateTimeAdded(Date dateTimeAdded) {
		if(dateTimeAdded==null)
		{
			this.dateTimeAdded=new Date();
		}
		else
		{
			this.dateTimeAdded = dateTimeAdded;
		}
	}
}
