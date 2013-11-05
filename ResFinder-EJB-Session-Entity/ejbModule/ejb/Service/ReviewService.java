package ejb.Service;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.*;

import ejb.Entity.Cuisine;
import ejb.Entity.Review;

/**
 * Session Bean implementation class ReviewService
 */
@Stateless
@LocalBean
public class ReviewService {
	 @PersistenceContext(unitName="resFinder-ejb-entities")
		EntityManager em;

    /**
     * Default constructor. 
     */
   
    public String createReview(Review res) {
		try{
		em.persist(res);
		int beanID = res.getId();
		return "Servlet Session Bean Entity " + "ID =" + beanID;
		}
		catch(Exception e)
		{
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			return errors.toString();
		}
		
		
			
	}
    public ArrayList<Review> getReviews(int id)
    {
    	ArrayList<Review> results = (ArrayList<Review>) em.createQuery("SELECT c FROM Review c where c.restaurant.id="+id).getResultList();
		return results;
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
