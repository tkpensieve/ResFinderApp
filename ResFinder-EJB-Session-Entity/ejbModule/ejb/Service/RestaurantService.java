package ejb.Service;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.jboss.resteasy.core.MediaTypeMap.Typed;

import ejb.Entity.Cuisine;
import ejb.Entity.Restaurant;

/**
 * Session Bean implementation class RestaurantService
 */
@Stateless
@LocalBean
public class RestaurantService {
	 @PersistenceContext(unitName="resFinder-ejb-entities")
		EntityManager em;

    /**
     * Default constructor. 
     */
    public RestaurantService() {
        
    }
    public String createRestaurant(Restaurant res) {
		try{
		em.persist(res);
		String name = res.getName();
		return "Added restaurant:"+name;
		}
		catch(Exception e)
		{
			/*StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			return errors.toString();*/
			return "no";
		}	
	}
    
	public Restaurant findById(int id)
	{
		Restaurant u=em.find(Restaurant.class,id);
		return u;
	}
	
	public void delete(int id)
	{
		Restaurant u=em.find(Restaurant.class,id);
		em.remove(u);
	}
	public int updateRating(double rating,Restaurant res)
	{
		Query q=em.createQuery("update Restaurant r set r.rating=:rating where r.id=:id");
		q.setParameter("rating", rating);
		q.setParameter("id", res.getId());
		return q.executeUpdate();
		
		
	}
	public void update(Restaurant u)
	{
		
		em.merge(u);
		
	}
	
	public ArrayList<Restaurant> filter(int locationId, int cuisineId)
	{
		TypedQuery<Restaurant> query = em.createNamedQuery("Restaurant.fetchForLocationAndCuisine", Restaurant.class);
        query.setParameter("locationId", locationId);
        query.setParameter("cuisineId", cuisineId);
		ArrayList<Restaurant> results =  (ArrayList<Restaurant>) query.getResultList();
		return results; 
	}
	
	public ArrayList<Restaurant> fetchByName(String name)
	{
		TypedQuery<Restaurant> query = em.createNamedQuery("Restaurant.fetchRes", Restaurant.class);
        query.setParameter("name", name);
        
		ArrayList<Restaurant> results =  (ArrayList<Restaurant>) query.getResultList();
		return results; 
		
	}
	public List<Restaurant> fetchByMan(String id)
	{
		TypedQuery<Restaurant> query=em.createNamedQuery("Restaurant.fetchByMan",Restaurant.class);
		query.setParameter("id", id);
		return (List<Restaurant>) query.getResultList();
		 
	
		
	}

}
