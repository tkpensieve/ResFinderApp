package ejb.Service;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.*;

import ejb.Entity.Restaurant;
import ejb.Entity.Review;

/**
 * Session Bean implementation class ReviewService
 */
@Stateless
@LocalBean
public class ReviewService {
	@PersistenceContext(unitName="resFinder-ejb-entities")
	EntityManager em;

    public String createReview(Review res) {
		try{
			em.persist(res);
			return "";
		}
		catch(Exception e)
		{
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			return errors.toString();
		}		
	}
    public ArrayList<Review>findByUser(String id)
    {
    	TypedQuery<Review> query= em.createNamedQuery("Review.fetchRev",Review.class);
    	query.setParameter("id", id);
    	return (ArrayList<Review>) query.getResultList();	
    }
    public ArrayList<Review>findByUserRes(String id, int resid)
    {
    	TypedQuery<Review> query= em.createNamedQuery("Review.fetchRevRes",Review.class);
    	query.setParameter("id", id);
    	query.setParameter("resid", resid);
    	return (ArrayList<Review>) query.getResultList();
    }
    public Restaurant fetchRes(String id)
    {
    	TypedQuery<Restaurant> query= em.createNamedQuery("Review.fetchRes",Restaurant.class);
    	query.setParameter("id", id);
    	return (Restaurant) query.getSingleResult();
    }
    @SuppressWarnings("unchecked")
	public ArrayList<Review> getReviews(int id)
    {
    	ArrayList<Review> results = (ArrayList<Review>) em.createQuery("SELECT c FROM Review c where c.restaurant.id="+id).getResultList();
		return results;
    }
    public ArrayList<Double> getRatings(int id)
    {
    	TypedQuery<Double> query=em.createNamedQuery("Review.getRatings",Double.class);
    	query.setParameter("id",id);
    	return (ArrayList<Double>)query.getResultList();
    }
	public Review findById(int id)
	{
		Review u=em.find(Review.class,id);
		return u;
	}
	public void delete(int id)
	{
		Review u=em.find(Review.class,id);
		em.remove(u);
	}
	public void update(Review u)
	{
		em.getTransaction().begin();
		em.merge(u);
		em.getTransaction().commit();
	}
}
