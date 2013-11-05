package ejb.Service;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import ejb.Entity.Location;
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
	
	public void update(Restaurant u)
	{
		em.getTransaction().begin();
		em.merge(u);
		em.getTransaction().commit();	
	}

	public ArrayList<Restaurant> filter(int locationId)
	{
		TypedQuery<Restaurant> query = em.createNamedQuery("Restaurant.fetchForALocation", Restaurant.class);
        query.setParameter("locationId", locationId);
		ArrayList<Restaurant> results =  (ArrayList<Restaurant>) query.getResultList();
		return results; 
	}
}
