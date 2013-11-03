package ejb.Service;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

}
